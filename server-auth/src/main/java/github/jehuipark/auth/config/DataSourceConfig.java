package github.jehuipark.auth.config;

import com.zaxxer.hikari.HikariDataSource;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by JeHuiPark on 2019-06-09
 * Blog   : https://jehuipark.github.io
 * Github : https://github.com/JeHuiPark
 */
@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource.hikari")
  public DataSource dataSource(){
    return new HikariDataSource();
  }
}
