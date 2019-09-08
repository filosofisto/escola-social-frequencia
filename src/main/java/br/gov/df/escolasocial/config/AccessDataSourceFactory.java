package br.gov.df.escolasocial.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class AccessDataSourceFactory {

    @Bean
    @Primary
    @ConfigurationProperties(prefix = "database.access")
    @Qualifier("access")
    public DataSource accessDataSource() {
        Map<String, String> env = System.getenv();

        return DataSourceBuilder
                .create()
                .url(env.get("ESCOLA_SOCIAL_ETL_URL_ACCESS"))
                .build();
    }
}
