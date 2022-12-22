package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.model.Student;
import mk.ukim.finki.wp.repository.StudentRepository;
import mk.ukim.finki.wp.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "New Student Servlet List", urlPatterns = "/newStudentList")
public class NewStudentLister extends HttpServlet {

    private final StudentService studentService;
    private final SpringTemplateEngine springTemplateEngine;

    public NewStudentLister(StudentService studentService, SpringTemplateEngine springTemplateEngine) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        WebContext context = new WebContext(req, resp, req.getServletContext());

        context.setVariable("students",studentService.listAll().stream().filter(Student::getNewStudent).toList());

        springTemplateEngine.process("newUserList.html",context,resp.getWriter());

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
