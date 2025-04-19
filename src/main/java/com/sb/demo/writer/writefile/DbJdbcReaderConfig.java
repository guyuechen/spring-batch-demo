package com.sb.demo.writer.writefile;

import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

//@Configuration
public class DbJdbcReaderConfig {
    @Autowired
    private DataSource dataSource;

    @Bean
    public JdbcPagingItemReader<Customer> dbJdbcReader() {
        JdbcPagingItemReader<Customer> reader = new JdbcPagingItemReader<Customer>();
        reader.setDataSource(dataSource);
        // 设置读取缓存, 每次取5个
        reader.setFetchSize(5);
        // 把读取到的记录转换成 Customer对象
        reader.setRowMapper(new RowMapper<Customer>() {
            // 结果集的映射
            @Override
            public Customer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                Customer customer = new Customer();
                customer.setId(resultSet.getLong(1));
                customer.setFirstName(resultSet.getString(2));
                customer.setLastName(resultSet.getString(3));
                customer.setBirthday(resultSet.getString(4));
                return customer;
            }
        });
        // 指定sq1语句
        MySqlPagingQueryProvider provider = new MySqlPagingQueryProvider();
        provider.setSelectClause("id,firstName,lastName,birthday");
        provider.setFromClause("from customer");
        // 指定根据哪个字段进行排序
        Map<String, Order> sort = new HashMap<>(1);
        sort.put("id", Order.ASCENDING);
        provider.setSortKeys(sort);
        reader.setQueryProvider(provider);
        return reader;
    }
}
