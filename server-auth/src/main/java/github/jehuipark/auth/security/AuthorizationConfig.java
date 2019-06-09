package github.jehuipark.auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.approval.ApprovalStore;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by JH on 2019-06-04.
 */
@Primary
@Configuration
@EnableAuthorizationServer
@RequiredArgsConstructor
public class AuthorizationConfig extends AuthorizationServerConfigurerAdapter {

  private final ClientDetailsService clientDetailsService;
  private final AuthenticationManager authenticationManager;
  private final TokenStore tokenStore;
  private final UserDetailsService userService;
  private final ApprovalStore approvalStore;


  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.withClientDetails(clientDetailsService);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
    security
            .tokenKeyAccess("hasAuthority('ROLE_TRUSTED_CLIENT')")
            .checkTokenAccess("hasAuthority('ROLE_TRUSTED_CLIENT')");
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    // @formatter:off
    endpoints
              .authenticationManager(authenticationManager)
              .tokenStore(tokenStore)
              .userDetailsService(userService)
              .approvalStore(approvalStore);
//              .tokenEnhancer(jwtAccessTokenConverter);
    // @formatter:on
  }

}
