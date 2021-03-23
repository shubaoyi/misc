package com.byshu.shardingjdbc;

import com.zaxxer.hikari.HikariDataSource;
import org.apache.shardingsphere.driver.api.ShardingSphereDataSourceFactory;
import org.apache.shardingsphere.infra.config.algorithm.ShardingSphereAlgorithmConfiguration;
import org.apache.shardingsphere.sharding.api.config.ShardingRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.rule.ShardingTableRuleConfiguration;
import org.apache.shardingsphere.sharding.api.config.strategy.sharding.StandardShardingStrategyConfiguration;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Main {

    public static void main(String[] args) throws SQLException {
        // 配置真实数据源
        Map<String, DataSource> dataSourceMap = new HashMap<>();

        // 配置第 1 个数据源
        HikariDataSource dataSource1 = new HikariDataSource();
        dataSource1.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource1.setJdbcUrl("jdbc:mysql://localhost:3306/ds0");
        dataSource1.setUsername("root");
        dataSource1.setPassword("root");
        dataSourceMap.put("ds0", dataSource1);

        // 配置第 2 个数据源
        HikariDataSource dataSource2 = new HikariDataSource();
        dataSource2.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource2.setJdbcUrl("jdbc:mysql://localhost:3306/ds1");
        dataSource2.setUsername("root");
        dataSource2.setPassword("root");
        dataSourceMap.put("ds1", dataSource2);

        // 配置 t_order 表规则
        ShardingTableRuleConfiguration orderTableRuleConfig = new ShardingTableRuleConfiguration(
                "t_order", "ds${0..1}.t_order${0..1}");

        // 配置分库策略
        orderTableRuleConfig.setDatabaseShardingStrategy(new StandardShardingStrategyConfiguration(
                "user_id", "dbShardingAlgorithm"));

        // 配置分表策略
        orderTableRuleConfig.setTableShardingStrategy(new StandardShardingStrategyConfiguration(
                "order_id", "tableShardingAlgorithm"));

        // 省略配置 t_order_item 表规则...
        // ...

        // 配置分片规则
        ShardingRuleConfiguration shardingRuleConfig = new ShardingRuleConfiguration();
        // ShardingRuleConfiguration可聚合多个rule
        shardingRuleConfig.getTables().add(orderTableRuleConfig);

        // 配置分库算法
        Properties dbShardingAlgorithmrProps = new Properties();
        dbShardingAlgorithmrProps.setProperty("algorithm-expression", "ds${user_id % 2}");
        // 为前面声明的"dbShardingAlgorithm"算法 指定具体的实现（行表达式）
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", dbShardingAlgorithmrProps));

        // 配置分表算法
        Properties tableShardingAlgorithmrProps = new Properties();
        tableShardingAlgorithmrProps.setProperty("algorithm-expression", "t_order${order_id % 2}");
        // 为前面声明的"tableShardingAlgorithm"算法 指定具体的实现（行表达式）
        shardingRuleConfig.getShardingAlgorithms().put("dbShardingAlgorithm",
        shardingRuleConfig.getShardingAlgorithms().put("tableShardingAlgorithm",
                new ShardingSphereAlgorithmConfiguration("INLINE", tableShardingAlgorithmrProps)));

        // 创建 ShardingSphereDataSource
        DataSource dataSource = ShardingSphereDataSourceFactory.createDataSource(dataSourceMap,
                Collections.singleton(shardingRuleConfig), new Properties());

        String sql = "SELECT i.* FROM t_order o JOIN t_order_item i ON o.order_id=i.order_id WHERE o.user_id=? AND o.order_id=?";
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, 10);
            ps.setInt(2, 1000);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // ...
                }
            }
        }
    }

}
