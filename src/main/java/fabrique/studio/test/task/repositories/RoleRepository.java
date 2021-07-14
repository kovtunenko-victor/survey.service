package fabrique.studio.test.task.repositories;

import org.springframework.data.repository.CrudRepository;

import fabrique.studio.test.task.models.Role;

public interface RoleRepository extends CrudRepository<Role, Long> {
    
}
