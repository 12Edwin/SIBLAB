package mx.edu.utez.SIBLAB.models.period;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

}
