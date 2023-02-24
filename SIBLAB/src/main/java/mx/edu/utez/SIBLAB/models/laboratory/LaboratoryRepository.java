package mx.edu.utez.SIBLAB.models.laboratory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratoryRepository extends JpaRepository<Laboratory,Long> {

    @Modifying
    @Query(value = "UPDATE laboratories SET status = :status WHERE id = :id",nativeQuery = true)
    int changeStatusById(@Param("id") Long id, @Param("status") Boolean status);
}
