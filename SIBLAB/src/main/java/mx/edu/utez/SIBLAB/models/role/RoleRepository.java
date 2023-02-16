package mx.edu.utez.SIBLAB.models.role;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {


    //Validation Dto
    boolean existsByName(String name);
}
