package github.jehuipark.auth.user;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Created by JH on 2019-06-04.
 */
@RequiredArgsConstructor
@Service("userService")
public class UserServiceImpl implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    User user = Optional
        .ofNullable(userRepository.findByUsername(username))
        .orElseThrow(()-> new UsernameNotFoundException("not exists"));
    return UserDetailsImpl.of(user);
  }
}
