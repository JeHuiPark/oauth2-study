package github.jehuipark.auth.user;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by JH on 2019-06-04.
 */
public class UserDetailsImpl implements UserDetails {

  private static final long serialVersionUID = 1896473489480771179L;

  private UserDetailsImpl(){}

  private String username;
  private String password;
  private Collection<? extends GrantedAuthority> authorities;

  public static UserDetailsImpl of(User user){
    UserDetailsImpl userDetails = new UserDetailsImpl();
    userDetails.username = user.getUsername();
    userDetails.password = user.getPassword();
    userDetails.authorities = AuthorityUtils.createAuthorityList("ROLE_USER");
    return userDetails;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return authorities;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true; // TODO
  }

  @Override
  public boolean isAccountNonLocked() {
    return true; // TODO
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true; // TODO
  }

  @Override
  public boolean isEnabled() {
    return true; // TODO
  }
}
