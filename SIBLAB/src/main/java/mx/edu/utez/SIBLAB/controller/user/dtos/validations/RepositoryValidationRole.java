package mx.edu.utez.SIBLAB.controller.user.dtos.validations;

import mx.edu.utez.SIBLAB.models.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryValidationRole extends JpaRepository<Role, Long> {
    boolean existsByName(String name);
}
