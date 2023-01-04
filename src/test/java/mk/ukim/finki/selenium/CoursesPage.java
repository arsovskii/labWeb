package mk.ukim.finki.selenium;

import lombok.Getter;
import mk.ukim.finki.wp.model.Course;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
@Getter
public class CoursesPage extends AbstractPage {

    @FindBy(className = "course")
    private List<WebElement> courseList;

    @FindBy(className = "editButton")
    private List<WebElement> editButtons;

    @FindBy(className = "deleteButton")
    private List<WebElement> deleteButtons;

    @FindBy(className = "addButton")
    private List<WebElement> addButtons;

    public CoursesPage(WebDriver driver) {
        super(driver);
    }

    public static CoursesPage to(WebDriver driver) {
        get(driver, "/courses");
        return PageFactory.initElements(driver, CoursesPage.class);

    }

    public void assertElems(int coursesNum, int deleteButtons, int editButtons, int addButtons) {
        Assert.assertEquals("Error in courses", coursesNum, this.getCourseList().size());
        Assert.assertEquals("Error in delete buttons", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("Error in edit butttons", editButtons, this.getEditButtons().size());
        Assert.assertEquals("Error in add button", addButtons, this.getAddButtons().size());


    }

}
