package mk.ukim.finki.wp.model;

import lombok.Data;
import mk.ukim.finki.wp.model.converters.TeacherFullnameConverter;
import mk.ukim.finki.wp.model.helper.TeacherFullname;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = TeacherFullnameConverter.class)
    private TeacherFullname teacherFullname;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfEmployment;

    public Teacher(String name, String surname) {
        this.teacherFullname = new TeacherFullname();
        this.teacherFullname.setName(name);
        this.teacherFullname.setSurname(surname);
    }

    public Teacher() {
        this.teacherFullname = new TeacherFullname();
    }
    public String getName(){
        return teacherFullname.getName();
    }
    public String getSurname(){
        return teacherFullname.getSurname();
    }
}
