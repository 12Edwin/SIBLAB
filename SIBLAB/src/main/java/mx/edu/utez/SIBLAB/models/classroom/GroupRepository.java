package mx.edu.utez.SIBLAB.models.classroom;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Classroom,Long> {


    //Validations Dto
    boolean existsByName(String name);
}
