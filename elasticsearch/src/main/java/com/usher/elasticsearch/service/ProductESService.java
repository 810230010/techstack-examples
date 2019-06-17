package com.usher.elasticsearch.service;

import com.usher.elasticsearch.entity.Product;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesResponse;
import org.elasticsearch.action.admin.indices.alias.get.GetAliasesRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.get.GetIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.GetAliasesResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class ProductESService {
    public static RestHighLevelClient esClient;
    public static void setClient(RestHighLevelClient client){
        esClient = client;
    }
    @Autowired
    private ProductService productService;
    public void refreshDBProducts() {
        List<Product> productList = productService.listProduct();
        String index = "product" + System.currentTimeMillis();
        String alias = "product-alias";
        String type = "product-type";
        String mapping = "  {\n" +
                "    \"product-type\": {\n" +
                "      \"properties\": {\n" +
                "        \"id\": {\n" +
                "          \"type\": \"integer\"\n" +
                "        },\n" +
                "        \"name\": {\n" +
                "          \"type\": \"text\"\n" +
                "        },\n" +
                "        \"description\": {\n" +
                "          \"type\": \"text\"\n" +
                "        },\n" +
                "        \"subTitle\": {\n" +
                "          \"type\": \"text\"\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }";
        try {
            createIndex(index, type, mapping);
            batchAddProduct(index, type, productList);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            switchAlias(alias, index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public IndicesAliasesResponse switchAlias(String alias, String newIndex) throws IOException{
        GetAliasesRequest aliasesRequest = new GetAliasesRequest(alias);
        IndicesAliasesRequest request = new IndicesAliasesRequest();


        GetAliasesResponse response = esClient.indices().getAlias(aliasesRequest, RequestOptions.DEFAULT);
        Iterator iterator = response.getAliases().entrySet().iterator();
        while(iterator.hasNext()) {
            Map.Entry aliasMetaData = (Map.Entry)iterator.next();
            String oldIndex = (String)aliasMetaData.getKey();
            System.out.println("旧索引: " + oldIndex);
            //删除旧索引
            IndicesAliasesRequest.AliasActions deleteAliasIndexAction = (new IndicesAliasesRequest.AliasActions(IndicesAliasesRequest.AliasActions.Type.REMOVE_INDEX)).index(oldIndex);
            request.addAliasAction(deleteAliasIndexAction);
        }
        //添加新索引
        IndicesAliasesRequest.AliasActions addAliasIndexAction = (new IndicesAliasesRequest.AliasActions(IndicesAliasesRequest.AliasActions.Type.ADD)).index(newIndex).alias(alias);
        request.addAliasAction(addAliasIndexAction);

        return esClient.indices().updateAliases(request, RequestOptions.DEFAULT);
    }

    public void batchAddProduct(String index, String type, List<Product> products) throws IOException{
        BulkRequest bulkRequest = new BulkRequest();
        if(!CollectionUtils.isEmpty(products)){
            for(Product product : products){

                int id = product.getId();
                Map<String, Object> source = new HashMap<>();
                source.put("id", id);
                source.put("name", product.getName());
                source.put("description", product.getDescription());
                source.put("subTitle", product.getSubTitle());
                IndexRequest request = new IndexRequest(index, type, id + "").source(source);
                bulkRequest.add(request);
            }
        }
        BulkResponse bulkResponse = esClient.bulk(bulkRequest, RequestOptions.DEFAULT);
        if(!bulkResponse.hasFailures()){
            System.out.println("插入成功...");
        }else {
            System.out.println("插入失败...");
        }
    }

    public CreateIndexResponse createIndex(String index, String type, String mapping) throws IOException {
        return createIndex(index, type, mapping, null);
    }

    public CreateIndexResponse createIndex(String index, String type, String mapping, String setting) throws IOException {
        CreateIndexRequest request = new CreateIndexRequest(index);
        if (!StringUtils.isEmpty(setting)) {
            request.settings(setting, XContentType.JSON);
        }

        request.mapping(type, mapping, XContentType.JSON);
        return esClient.indices().create(request, RequestOptions.DEFAULT);
    }
    public void createProductIndex(String index){
        CreateIndexRequest request = new CreateIndexRequest(index);
        //create mapping if necessary
        /**
         * {"properties":
         *      {"product":
         *          {
         *              "id": "int",
         *              "brandId": "int"
         *          }
         *      }
         * }
         */
//        try {
//            XContentBuilder jsonBuilder = XContentFactory.jsonBuilder();
//            jsonBuilder.startObject("properties")
//                            .startObject("product")
//                                .field("id", "int")
//                                .field("brandId", "int")
//                            .endObject()
//                        .endObject();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        request.mapping("_doc",
//                "{\n" +
//                        "  \"_doc\": {\n" +
//                        "    \"properties\": {\n" +
//                        "      \"product\": {\n" +
//                        "        \"id\": \"int\"\n" +
//                        "        \"brandId\": \"int\"\n" +
//                        "      }\n" +
//                        "    }\n" +
//                        "  }\n" +
//                        "}",
//                XContentType.JSON);
        try {
            esClient.indices().create(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean existIndex(String index){
        GetIndexRequest request = new GetIndexRequest();
        request.indices(index);
        try {
            return esClient.indices().exists(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
