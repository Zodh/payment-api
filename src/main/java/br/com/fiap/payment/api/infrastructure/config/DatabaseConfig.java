package br.com.fiap.payment.api.infrastructure.config;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {

  @Value("${FASTFOOD_DATABASE_USER}")
  private String encodedUsername;

  @Value("${FASTFOOD_DATABASE_PASSWORD}")
  private String encodedPassword;

  @Bean
  public String decodedDbUsername() {
    return new String(Base64.getDecoder().decode(encodedUsername));
  }

  @Bean
  public String decodedDbPassword() {
    return new String(Base64.getDecoder().decode(encodedPassword));
  }
}