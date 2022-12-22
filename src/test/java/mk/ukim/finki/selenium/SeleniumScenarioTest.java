package mk.ukim.finki.selenium;

import com.gargoylesoftware.htmlunit.html.Html;
import mk.ukim.finki.wp.LabApplication;
import mk.ukim.finki.wp.model.Course;
import mk.ukim.finki.wp.model.Teacher;
import mk.ukim.finki.wp.service.CourseService;
import mk.ukim.finki.wp.service.StudentService;
import mk.ukim.finki.wp.service.TeacherService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(classes= LabApplication.class,webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    CourseService courseService;

    @Autowired
    TeacherService teacherService;


    private HtmlUnitDriver driver;

    private static Course c1;
    private static Course c2;
    private static Teacher t1;
    private static Teacher t2;

    private final static String user = "admin";
    private final static String password = "admin";

    private static boolean dataInitialized = false;

    @BeforeEach
    public void setup(){
        this.driver=new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if (this.driver != null){
            this.driver.close();
        }
    }

    private void initData(){
        if(!dataInitialized){
            t1=teacherService.save("nekoj","daskal");
            t2=teacherService.save("nekojdrug","daskal2");

            c1=courseService.addNewCourse("course1","desc:D",t1.getId());
            c2=courseService.addNewCourse("course2","desc2:D",t2.getId());
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario(){

        CoursesPage coursesPage = CoursesPage.to(this.driver);
        coursesPage.assertElems(2,0,0,0);
        LoginPage loginPage = LoginPage.openLogin(this.driver);
        coursesPage = LoginPage.doLogin(this.driver,loginPage,user,password);
        coursesPage.assertElems(2,2,2,1);
    }

}
