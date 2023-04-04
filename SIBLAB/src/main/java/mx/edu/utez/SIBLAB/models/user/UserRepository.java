package mx.edu.utez.SIBLAB.models.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    @Query(value = "SELECT * FROM users where id_teacher = :id_teacher",nativeQuery = true)
    List<User> findUsersByTeacher(@Param("id_teacher") Long id_teacher);
    @Query(value = "SELECT * FROM users where role = :role",nativeQuery = true)
    List<User> findUsersByRol(@Param("role") String role);
    @Modifying
    @Query(value = "UPDATE users SET status = :status WHERE id = :id",nativeQuery = true)
    int changeStatusById(@Param("id") Long id, @Param("status") Boolean status);

    @Modifying
    @Query(value = "UPDATE users SET name = :name, password = :pass, role = :role, surname = :sur WHERE id = :id",
    nativeQuery = true)
            int updateUser(@Param("name")String name, @Param("pass")String pass, @Param("role")String role, @Param("sur")String sur, @Param("id") Long id);

    //Validation Dto
    boolean existsByCode(String code);

    //Auth
    User findByEmail(String email);
}
