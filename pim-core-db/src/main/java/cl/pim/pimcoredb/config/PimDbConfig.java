package cl.pim.pimcoredb.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "pimEntityManagerFactory",
        transactionManagerRef = "pimTransactionManager",
        basePackages = {"cl.pim.pimcoredb.pim"}
)
@Slf4j
public class PimDbConfig {

    @Bean()
    @Primary
    @ConfigurationProperties(prefix = "spring.pim")
    public DataSourceProperties pimDataSourceProperties() {
        return new DataSourceProperties();
    }

    @Bean("pimDataSource")
    @Primary
    public DataSource pimDataSource() {
        return pimDataSourceProperties().initializeDataSourceBuilder().build();
    }

    @Primary
    @Bean(name = "pimEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean pimEntityManagerFactory(EntityManagerFactoryBuilder builder,
                                                                          @Qualifier("pimDataSource") DataSource pimDataSource) {
        return builder
                .dataSource(pimDataSource)
                .packages("cl.pim.pimcoredb.pim")
                .persistenceUnit("pimDb")
                .build();
    }

    @Bean(name = "pimTransactionManager")
    public PlatformTransactionManager pimTransactionManager(
            @Qualifier("pimEntityManagerFactory") EntityManagerFactory pimEntityManagerFactory) {
        return new JpaTransactionManager(pimEntityManagerFactory);
    }
}
