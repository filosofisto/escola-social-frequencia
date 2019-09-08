package br.gov.df.escolasocial.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;

@Configuration
public class PostgresDataSourceFactory {

    @Bean
    @ConfigurationProperties(prefix = "database.postgres")
    @Qualifier("postgres")
    public DataSource postgresDataSource() {
        Map<String, String> env = System.getenv();

        return DataSourceBuilder
                .create()
                .url(env.get("ESCOLA_SOCIAL_ETL_URL"))
                .username(env.get("ESCOLA_SOCIAL_ETL_USERNAME"))
                .password(env.get("ESCOLA_SOCIAL_ETL_PASSWORD"))
                .build();
    }
}
