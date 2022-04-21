package cl.pim.pimcoredb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "authEntityManagerFactory",
        transactionManagerRef = "authTransactionManager",
        basePackages = {"cl.pim.pimcoredb.auth"}
)
@Slf4j
public class AuthDbConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.pim-auth")
    public DataSourceProperties authDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("authDataSource")
    public DataSource authDataSource() {
        return authDataSourceProperties().initializeDataSourceBuilder().build();
    }


    @Bean(name = "authEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean authEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                           @Qualifier("authDataSource") DataSource authDataSource) {
        log.info(authDataSource.toString());
        return builder
                .dataSource(authDataSource)
                .packages("cl.pim.pimcoredb.auth")
                .build();
    }

    @Bean(name = "authTransactionManager")
    public PlatformTransactionManager authTransactionManager(
            @Qualifier("authEntityManagerFactory") EntityManagerFactory authEntityManagerFactory) {
        return new JpaTransactionManager(authEntityManagerFactory);
    }
}
