package mk.ukim.finki.wp.bootstrap;

import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Stream;

@Component
public class DataHolder {

    public static List<Student> students = new ArrayList<>();
    public static List<Course> courses = new ArrayList<>();
    public static List<Teacher> teachers = new ArrayList<>();

    @PostConstruct
    public void init() {
        students.add(new Student("arsovski3", "jakpass", "David", "Arsovski", Boolean.FALSE));
        students.add(new Student("serBog", "dzekcesum", "Sergej", "Bogatinoski", Boolean.FALSE));
        students.add(new Student("pastelo", "iLoveSkopje", "Eva", "Smileska", Boolean.FALSE));
        students.add(new Student("kut", "mimoza3", "Mihaela", "Pavleska", Boolean.FALSE));
        students.add(new Student("mrBob", "goAhed3", "Boban", "Bob", Boolean.FALSE));

        teachers.add(new Teacher("Afrodita", "Stefanoska"));
        teachers.add(new Teacher("Marija", "Dulevska"));
        teachers.add(new Teacher("Borce", "Glisero"));
        teachers.add(new Teacher("Tatjana", "Gospel"));
        teachers.add(new Teacher("Zoran", "Nikolov"));


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


        courses.add(new Course(
                "Veb Programiranje",
                "Poceten kurs za backend programiranje so Java Spring",
                first, teachers.get(4)));
        courses.add(new Course(
                "Strukturno Programiranje",
                "Voved vo paradigmata strukturno programiranje i C programskiot jazik",
                second, teachers.get(1)));
        courses.add(new Course(
                "Diskretni Strukturi 1",
                "Samo za jaki matematicari",
                third, teachers.get(0)));
        courses.add(new Course(
                "Inzinerska Matematika",
                "Matematika za pospremni studenti",
                new ArrayList<>(),teachers.get(3)));
        courses.add(new Course(
                "Verojatnost i Statistika",
                "Begaj podaleku",
                fifth, teachers.get(0)));


    }


}
