package github.jehuipark.auth.security.client;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;

/**
 * Created by JH on 2019-06-06.
 */
public interface ClientServiceAdapter {
   StringBuffer emptyPassword = new StringBuffer();
  default ClientDetails clientToClientDetails(Client client, PasswordEncoder passwordEncoder){
    BaseClientDetails details = new BaseClientDetails(
        client.getClientId(),
        client.getResourceIds(),
        client.getScope(),
        client.getAuthorizedGrantTypes(),
        client.getAuthorities(),
        client.getWebServerRedirectUri()
    );
    if(client.getClientSecret() == null || client.getClientSecret().trim().isEmpty()) {
      if(emptyPassword.length() == 0){
        emptyPassword.append(passwordEncoder.encode(""));
      }
      details.setClientSecret(emptyPassword.toString());
    }else{
      details.setClientSecret(client.getClientSecret());
    }
    return details;
  }
}
