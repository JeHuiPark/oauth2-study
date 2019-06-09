package github.jehuipark.resource.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by JH on 2019-06-04.
 */
public interface UserRepository extends JpaRepository<User, Long> {
  User findByUsername(String username);
}
