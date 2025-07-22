package project.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.model.User;

import java.util.Optional;

@Repository
@Service
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
