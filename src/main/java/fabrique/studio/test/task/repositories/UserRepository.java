package fabrique.studio.test.task.repositories;

import org.springframework.data.repository.CrudRepository;

import fabrique.studio.test.task.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
