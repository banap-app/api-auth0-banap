package auth.com.domain.domain.user;

import java.util.Optional;

public interface UserGateway {
    Optional<User> findByEmail(String email);

}
