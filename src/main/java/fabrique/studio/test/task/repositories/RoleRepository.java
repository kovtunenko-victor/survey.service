package fabrique.studio.test.task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fabrique.studio.test.task.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
}
