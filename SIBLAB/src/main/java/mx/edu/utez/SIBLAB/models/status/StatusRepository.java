package mx.edu.utez.SIBLAB.models.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {

    //Validations Dto
    boolean existsByName(String status);
}
