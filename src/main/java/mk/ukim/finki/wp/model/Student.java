package mk.ukim.finki.wp.model;

import lombok.Data;
import org.apache.xpath.operations.Bool;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Student {
    @Id
    private String username;
    private String password;
    private String name;
    private String surname;
    private Boolean newStudent;

    public Student(String username, String password, String name, String surname, Boolean newStudent) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.newStudent = newStudent;
    }

    public Student() {
    }
}
