package mx.edu.utez.SIBLAB.models.machine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {
    @Query(value = "UPDATE machine SET status = :status WHERE id = :id",nativeQuery = true)
    boolean changeStatusById(@Param("id")Long id, @Param("status")Boolean status);
}
