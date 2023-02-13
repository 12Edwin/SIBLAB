package mx.edu.utez.SIBLAB.controller.user.dtos.validations;

import mx.edu.utez.SIBLAB.models.classroom.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RepositoryValidationClassroom extends JpaRepository<Classroom,Long> {
    boolean existsByName(String name);
}
