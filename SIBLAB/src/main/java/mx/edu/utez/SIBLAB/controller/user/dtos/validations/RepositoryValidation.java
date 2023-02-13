package mx.edu.utez.SIBLAB.controller.user.dtos.validations;

import mx.edu.utez.SIBLAB.models.role.Role;
import mx.edu.utez.SIBLAB.models.user.User;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositoryValidation extends JpaRepository<User,Long>{
    boolean existsByRole(String role);
    boolean existsByEmail(String email);
    boolean existsByCode(String code);

    @Query(value = "SELECT * FROM users where id_teacher = :id_teacher",nativeQuery = true)
    List<User> findUsersByTeacher(@Param("id_teacher") Long id_teacher);
}
