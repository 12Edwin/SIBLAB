package mx.edu.utez.SIBLAB.models.report;

import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report,Long> {
    @Query(value = "SELECT * FROM reports WHERE id_user = :id",nativeQuery = true)
    List<Report> findAllReportsByStudent (@Param("id") Long id);

    @Query(value = "SELECT * FROM reports WHERE id_teacher = :id",nativeQuery = true)
    List<Report> findAllById_teacher(@Param("id") Long id);

    @Query(value = "SELECT * FROM reports WHERE status = :status", nativeQuery = true)
    List<Report> findAllByStatus(@Param("status") String status);
    @Query(value = "update reports SET status = :status WHERE id = :id",nativeQuery = true)
    boolean changeStatusById(@Param("id")Long id, @Param("status")String status);
}
