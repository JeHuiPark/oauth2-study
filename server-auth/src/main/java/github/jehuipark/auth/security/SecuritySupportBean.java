package github.jehuipark.auth.security;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.approval.JdbcApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

/**
 * Created by JH on 2019-06-05.
 */
@Configuration
public class SecuritySupportBean {

  @Bean
  public TokenStore tokenStore(DataSource dataSource) {
    return new JdbcTokenStore(dataSource);
  }

  @Bean
  public ApprovalStore approvalStore(DataSource dataSource) {
    return new JdbcApprovalStore(dataSource);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }


}
