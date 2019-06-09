package github.jehuipark.resource.user;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by JH on 2019-06-05.
 */
@RestController
@RequestMapping("/user")
public class UserRestApi {

  @GetMapping("/me")
  public Object me(Authentication authentication){
    return authentication.getPrincipal();
  }
}
