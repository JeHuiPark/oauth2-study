package github.jehuipark.resource.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.token.TokenService;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

/**
 * Created by JH on 2019-06-04.
 */
@EnableResourceServer
@Configuration
@RequiredArgsConstructor
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

  private final TokenStore tokenStore;

  @Override
  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
    resources   .resourceId("my-resource").tokenStore(tokenStore);
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    // @formatter:off
    http
          .authorizeRequests()
            .antMatchers("/user/**")
              .access("#oauth2.hasScope('read')")
            .antMatchers("**")
              .permitAll();
    // @formatter:on

  }
}
