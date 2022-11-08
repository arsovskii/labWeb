package mk.ukim.finki.wp.web.controller;


import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.exceptions.CourseDoenstExistException;
import mk.ukim.finki.wp.service.CourseService;
import mk.ukim.finki.wp.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;

@Controller
@RequestMapping("/courses")
public class CourseController {

    private final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }


    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {

        model.addAttribute("courses", courseService.listAll().stream().sorted(Comparator.comparing(i->i.getName().toLowerCase())).toList());
        model.addAttribute("hasError",false);
        if(error != null){
            model.addAttribute("hasError", true);
            model.addAttribute("error",error);
        }

        return "listCourses";
    }

    @GetMapping("/add")
    public String showCourseForm(Model model) {
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("type","add");
        return "add-course";
    }

    @GetMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){

        try{
            model.addAttribute("course", courseService.getCourseFromId(id).orElseThrow(CourseDoenstExistException::new));
        }catch (Exception e){
            return "redirect:/courses?error="+e.getMessage();
        }
        model.addAttribute("teachers", teacherService.findAll());
        model.addAttribute("type","edit");
        return "add-course";
    }

    @PostMapping
    public String postSelectedCourse(@RequestParam Long courseId, HttpServletRequest request) {
        request.getSession().setAttribute("courseId", courseId);
        return "redirect:/AddStudent";
    }

    @PostMapping("/add")
    public String saveCourse(@RequestParam(required = false) Long courseId,@RequestParam Long teacherId, @RequestParam String name, @RequestParam String desc) {

        try{
            if(courseId != null){
                courseService.editCourse(courseId,name,desc,teacherId);
            }else{
                courseService.addNewCourse(name, desc, teacherId);
            }
        }catch (Exception e){
            return "redirect:/courses?error="+e.getMessage();
        }

        return "redirect:/courses";
    }

}
