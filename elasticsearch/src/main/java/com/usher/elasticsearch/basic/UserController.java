package com.usher.elasticsearch.basic;

import com.usher.elasticsearch.common.RestResult;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserRepository userRepository;

    private static final Integer PAGE_SIZE = 10;
    /**
     * 添加user
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST, value = "/user")
    public Object saveUser(@RequestBody User user){
        log.info("待添加的user: {}", user);
        userRepository.save(user);
        log.info("添加成功");
        return new RestResult();
    }
    /**
     * 查询所有user
     * @return
     */
    @RequestMapping("/")
    public Object findAllUsers(){
        List<User> result = new ArrayList<>();
        userRepository.findAll().forEach(
                user->result.add(user)
        );
        return result;
    }

    /**
     * 修改用户信息
     * @param user
     * @return
     */
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public Object updateUsser(@RequestBody User user){
        log.info("待更改的user: {}", user);
        userRepository.save(user);
        log.info("修改成功");
        return new RestResult();
    }

    /**
     * 删除user
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Object deleteUser(@PathVariable int id){
        log.info("待删除的user: {}", id);
        userRepository.deleteById(id);
        log.info("删除成功!");
        return new RestResult();
    }

    /**
     * 根据关键字查询
     * @param searchWord
     * @return
     */
    @RequestMapping("/{searchWord}")
    public Object findByKeyWord(@PathVariable String searchWord){
        QueryBuilder queryBuilder = new QueryStringQueryBuilder(searchWord);
        List<User> result = new ArrayList<>();
        userRepository.search(queryBuilder).forEach(
                user-> result.add(user)
        );
        return new RestResult(0, "success", result);
    }

    /**
     * 分页
     * @param page
     * @param pageSize
     * @param searchWord
     * @return
     */
    @RequestMapping("/page")
    public Object findByPage(Integer page, Integer pageSize, String searchWord){
        Pageable pageable = new PageRequest(page, pageSize);
        Page<User> page1 = userRepository.findByNameLike(searchWord, pageable);
        List<User> result = new ArrayList<>();
        page1.forEach(user->
                result.add(user)
        );
        return result;
    }
}
