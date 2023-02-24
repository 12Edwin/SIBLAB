package mx.edu.utez.SIBLAB.models.machine;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MachineRepository extends JpaRepository<Machine,Long> {
    @Modifying
    @Query(value = "UPDATE machines SET status = :status WHERE id = :id",nativeQuery = true)
    boolean changeStatusById(@Param("id")Long id, @Param("status")Boolean status);

    @Modifying
    @Query(value = "UPDATE machines SET path_image = :path_image WHERE id = :id", nativeQuery = true)
    int updateImageById(@Param("id")Long id, @Param("path_image")String path_image);
}
