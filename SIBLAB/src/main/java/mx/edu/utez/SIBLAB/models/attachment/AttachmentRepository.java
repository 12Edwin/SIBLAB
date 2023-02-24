package mx.edu.utez.SIBLAB.models.attachment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,Long> {

    @Query(value = "UPDATE attachments SET status = :status WHERE id = :id",nativeQuery = true)
    int changeStatusById(@Param("id") Long id, @Param("status") String status);
}
