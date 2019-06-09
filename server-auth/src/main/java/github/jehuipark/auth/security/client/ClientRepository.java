package github.jehuipark.auth.security.client;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by JH on 2019-06-06.
 */
public interface ClientRepository extends JpaRepository<Client, String> {

}
