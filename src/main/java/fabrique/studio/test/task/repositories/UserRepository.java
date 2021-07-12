package fabrique.studio.test.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fabrique.studio.test.task.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
