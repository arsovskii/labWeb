package mk.ukim.finki.wp.web.controller;


import mk.ukim.finki.wp.model.exceptions.CourseDoesntExistException;
import mk.ukim.finki.wp.service.CourseService;
import mk.ukim.finki.wp.service.GradeService;
import mk.ukim.finki.wp.service.TeacherService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;
    private final GradeService gradeService;

    public CourseController(CourseService courseService, TeacherService teacherService, GradeService gradeService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
        this.gradeService = gradeService;
    }


    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, @RequestParam(required = false) Long teacherId, Model model) {

        model.addAttribute("courses", courseService.listAll().stream().sorted(Comparator.comparing(i -> i.getName().toLowerCase())).toList());
        model.addAttribute("teachers", teacherService.findAll());
        if (error != null) {
            model.addAttribute("error", error);
        }
        if (teacherId != null) {
            model.addAttribute("selectedTeacher", teacherId);

        }

        return "listCourses";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAddCoursePage(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        return "add-course";
    }

    @GetMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getEditCoursePage(@PathVariable Long id, Model model) {

        try {
            model.addAttribute("course", courseService.getCourseFromId(id).orElseThrow(CourseDoesntExistException::new));
        } catch (Exception e) {
            return "redirect:/courses?error=" + e.getMessage();
        }
        model.addAttribute("teachers", teacherService.findAll());
        return "add-course";
    }
    @GetMapping("/grades")
    public String getGradePage( @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime from,
                                @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime to,
                                @RequestParam(required = false) Character selectedFirstGrade,
                                @RequestParam(required = false) Character selectedSecondGrade,
                                @RequestParam(required = false) Long selectedCourse,
                                Model model){
        model.addAttribute("courses",courseService.listAll());
        model.addAttribute("firstGrade",selectedFirstGrade);
        model.addAttribute("secondGrade",selectedSecondGrade);
        model.addAttribute("selectedCourse",selectedCourse);

        model.addAttribute("grades",gradeService.showAllGrades(from,to,selectedFirstGrade,selectedSecondGrade,selectedCourse));
        return "listGrades";
    }

    @PostMapping
    public String postSelectedCourse(@RequestParam Long courseId, HttpServletRequest request) {
        request.getSession().setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }

    @PostMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String saveCourse(@RequestParam(required = false) Long courseId, @RequestParam Long teacherId, @RequestParam String name, @RequestParam String desc) {

        try {
            if (courseId != null) {
                courseService.editCourse(courseId, name, desc, teacherId);
            } else {
                courseService.addNewCourse(name, desc, teacherId);
            }
        } catch (Exception e) {
            return "redirect:/courses?error=" + e.getMessage();
        }

        return "redirect:/courses";
    }

    @PostMapping("/add-grade")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addGrade(@RequestParam String selectedStudent, @RequestParam Long selectedCourse,@RequestParam Character selectedGrade, @RequestParam(required = false) @DateTimeFormat(iso=DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateGrade){
        if(dateGrade == null){
            dateGrade = LocalDateTime.now();

        }
        courseService.addGrade(selectedGrade,dateGrade,selectedCourse,selectedStudent);
        return "redirect:/StudentEnrollmentSummary";
    }
}
