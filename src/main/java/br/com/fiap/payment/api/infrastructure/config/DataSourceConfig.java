package br.com.fiap.payment.api.infrastructure.config;

import java.util.Base64;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

  @Value("${FASTFOOD_DATABASE_USER}")
  private String encodedUsername;
  @Value("${FASTFOOD_DATABASE_PASSWORD}")
  private String encodedPassword;
  @Value("${FASTFOOD_DATABASE_URL}")
  private String dbUrl;
  @Value("${spring.datasource.driver-class-name}")
  private String dbDriver;

  @Bean
  public DataSource dataSource() {
    String decodedUsername = new String(Base64.getDecoder().decode(encodedUsername));
    String decodedPassword = new String(Base64.getDecoder().decode(encodedPassword));

    System.out.println("-----");
    System.out.println(dbUrl);
    System.out.println(decodedUsername);
    System.out.println(decodedPassword);
    System.out.println("-----");

    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setUrl(dbUrl);
    dataSource.setDriverClassName(dbDriver);
    dataSource.setUsername(decodedUsername);
    dataSource.setPassword(decodedPassword);

    return dataSource;
  }

}