package mk.ukim.finki.wp.web.controller;

import mk.ukim.finki.wp.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller()
@RequestMapping("/StudentEnrollmentSummary")
public class StudentEnrollmentController {
    private final CourseService courseService;

    public StudentEnrollmentController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping()
    public String getStudentEnrollment(@SessionAttribute Long courseId, Model model) {
        model.addAttribute("students", courseService.listStudentsByCourse(courseId));
        model.addAttribute("course", courseService.courseNameFromId(courseId));
        model.addAttribute("grades", courseService.getGradesForCourse(courseId));
        return "studentsInCourse";
    }

    @PostMapping
    public String postStudentEnrollment(@RequestParam String size, @SessionAttribute Long courseId,HttpServletRequest request, Model model) {
        try {
            courseService.addStudentInCourse(size, courseId);
            request.getSession().setAttribute("currentStudent",size);
            return "redirect:/StudentEnrollmentSummary";
        } catch (Exception e) {
            return "redirect:/courses?error=" + e.getMessage();

        }


    }
}

