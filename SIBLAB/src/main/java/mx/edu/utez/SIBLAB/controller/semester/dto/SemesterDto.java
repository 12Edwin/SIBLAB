package mx.edu.utez.SIBLAB.controller.semester.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mx.edu.utez.SIBLAB.controller.report.dtos.validations.date.ValidDate;
import mx.edu.utez.SIBLAB.models.period.Period;
import mx.edu.utez.SIBLAB.models.semester.Semester;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto {

    private Long id;

    private String name;

    @ValidDate(dateFormat = "yyyy-MM-dd HH:mm:ss",message = "Campo inválido")
    private String semester_start;

    @ValidDate(dateFormat = "yyyy-MM-dd HH:mm:ss",message = "Campo inválido")
    private String semester_finish;

    private List<Period> periods;

    static Date start;
    static Date finish;


    public Semester castToSave(){
        date();
        return new Semester(null, getName(), start, finish, null);
    }

    public Semester castToUpdate(){
        date();
        return new Semester(getId(), getName(), start, finish, null);
    }

    // Cast to period
    public Semester castToSemesterToPeriod(){
        date();
        return new Semester(getId(), getName(), start, finish, null);
    }

    public void date(){
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setLenient(false);

        try {
            start = sdf.parse(getSemester_start());
            finish = sdf.parse(getSemester_finish());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
