package ru.job4j.accidents.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
public class JdbcConfig {

    /**
     * Загружаем пул соединений.
     * @param driver
     * @param url
     * @param username
     * @param password
     * @return
     */
    @Bean
    public DataSource ds(@Value("${jdbc.driver}") String driver,
                          @Value("${jdbc.url}") String url,
                          @Value("${jdbc.username}") String username,
                          @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    /**
     * Создаем обертку над базой данных.
     * @param ds
     * @return
     */
    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }


}