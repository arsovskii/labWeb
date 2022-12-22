package mk.ukim.finki.wp.web;

import mk.ukim.finki.wp.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Create Student", urlPatterns = "/createStudent")
public class CreateStudentServlet extends HttpServlet {

    private final SpringTemplateEngine springTemplateEngine;
    private final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        WebContext context = new WebContext(req, resp, req.getServletContext());


        springTemplateEngine.process("createNewStudent.html", context, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");

        try {
            studentService.save(username, password, name, surname);
            resp.sendRedirect("/AddStudent");
        }catch (Exception e){
            WebContext context = new WebContext(req,resp,req.getServletContext());

            context.setVariable("error",e.getMessage());
            springTemplateEngine.process("createNewStudent.html",context,resp.getWriter());
        }

    }
}
