package github.jehuipark.auth.security.client;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Created by JH on 2019-06-06.
 */
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter @Setter
@Table(name="OAUTH_CLIENT_DETAILS")
@Entity
public class Client {

  @Id
  private String clientId;
  private String resourceIds;
  private String clientSecret;
  private String scope;
  private String authorizedGrantTypes;
  private String webServerRedirectUri;
  private String authorities;
  private Integer accessTokenValidity;
  private Integer refreshTokenValidity;
  private String additionalInformation;
  private String autoapprove;

}
