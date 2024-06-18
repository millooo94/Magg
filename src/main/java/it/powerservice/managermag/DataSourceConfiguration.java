package it.powerservice.managermag;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }
}
