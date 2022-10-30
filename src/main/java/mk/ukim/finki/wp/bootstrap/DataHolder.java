package mk.ukim.finki.wp.bootstrap;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@Component
public class DataHolder {

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("arsovski3", "jakpass", "David", "Arsovski"));
        students.add(new Student("serBog", "dzekcesum", "Sergej", "Bogatinoski"));
        students.add(new Student("pastelo", "iLoveSkopje", "Eva", "Smileska"));
        students.add(new Student("kut", "mimoza3", "Mihaela", "Pavleska"));
        students.add(new Student("mrBob", "goAhed3", "Boban", "Bob"));

        List<Student> first = new ArrayList<>();
        List<Student> second = new ArrayList<>();
        List<Student> third = new ArrayList<>();
        List<Student> fifth = new ArrayList<>();

        first.add(students.get(0));
        first.add(students.get(1));
        first.add(students.get(3));

        second.add(students.get(2));

        third.add(students.get(0));
        third.add(students.get(3));

        for (int i = 0; i < 5; i++) {
            fifth.add(students.get(i));
        }


        courses.add(new Course(4513123L,
                "Veb Programiranje",
                "Poceten kurs za backend programiranje so Java Spring",
                first));
        courses.add(new Course(7156871L,
                "Strukturno Programiranje",
                "Voved vo paradigmata strukturno programiranje i C programskiot jazik",
                second));
        courses.add(new Course(3874562L,
                "Diskretni Strukturi 1",
                "Samo za jaki matematicari",
                third));
        courses.add(new Course(9414731L,
                "Inzinerska Matematika",
                "Matematika za pospremni studenti",
                new ArrayList<>()));
        courses.add(new Course(1000415L,
                "Verojatnost i Statistika",
                "Begaj podaleku",
                fifth));


    }


}
