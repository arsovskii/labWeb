package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.service.CourseService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Student Enrollment", urlPatterns = "/StudentEnrollmentSummaryServlet")
public class StudentEnrollmentSummary extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final CourseService courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("students", courseService.listStudentsByCourse((long) req.getSession().getAttribute("courseId")));
        context.setVariable("course", courseService.courseNameFromId((long) req.getSession().getAttribute("courseId")));
        springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            courseService.addStudentInCourse(req.getParameter("size"), (long) req.getSession().getAttribute("courseId"));
            resp.sendRedirect("/StudentEnrollmentSummary");
        }catch (Exception e){
//            req.setAttribute("error",e.getMessage());
//            WebContext context = new WebContext(req,resp,req.getServletContext());
//            context.setVariable("error",e.getMessage());
//            springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());

            resp.sendRedirect("/courses?error="+e.getMessage());
        }

    }
}
