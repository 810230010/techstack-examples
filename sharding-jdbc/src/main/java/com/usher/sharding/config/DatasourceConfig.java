package com.usher.sharding.config;

import io.shardingsphere.api.config.ShardingRuleConfiguration;
import io.shardingsphere.api.config.strategy.StandardShardingStrategyConfiguration;
import io.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DatasourceConfig {
    /**
     * 配置分库分表策略
     *
     * @return
     * @throws SQLException
     */

}
