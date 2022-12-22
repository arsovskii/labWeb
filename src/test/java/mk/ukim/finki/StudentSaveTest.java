package mk.ukim.finki;


import mk.ukim.finki.wp.model.Student;

import mk.ukim.finki.wp.model.exceptions.NotEnoughInfoForNewStudentException;
import mk.ukim.finki.wp.model.exceptions.UsernameAlreadyInUse;
import mk.ukim.finki.wp.repository.jpa.StudentRepositoryDB;
import mk.ukim.finki.wp.service.StudentService;
import mk.ukim.finki.wp.service.implementation.StudentServiceImplementation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class StudentSaveTest {

    @Mock
    private StudentRepositoryDB studentRepository;

    private StudentService studentService;

    @Before
    public void init(){
        MockitoAnnotations.openMocks(this);
        Student student = new Student("username","password","david","arsovski");
        Mockito.when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);

        studentService = Mockito.spy(new StudentServiceImplementation(studentRepository));
    }

    @Test
    public void testSuccessfulStudentSave(){
        Student student = this.studentService.save("username","password","david","arsovski");
        Mockito.verify(this.studentService).save("username","password","david","arsovski");

        Assert.assertNotNull("Student is null",student);

        Assert.assertEquals("Username doesn't match","username",student.getUsername());
        Assert.assertEquals("Password doesn't match","password",student.getPassword());
        Assert.assertEquals("Name doesn't match","david",student.getName());
        Assert.assertEquals("Surname doesn't match","arsovski",student.getSurname());
        Assert.assertTrue("NewStudent bool doesn't match", student.getNewStudent());

    }

    @Test
    public void testEmptyUsername(){
        Assert.assertThrows(NotEnoughInfoForNewStudentException.class,
                ()->this.studentService.save("","password","david","arsovski"));
        Mockito.verify(this.studentService).save("","password","david","arsovski");
    }
    @Test
    public void testEmptyPassword(){
        Assert.assertThrows(NotEnoughInfoForNewStudentException.class,
                ()->this.studentService.save("username","","david","arsovski"));
        Mockito.verify(this.studentService).save("username","","david","arsovski");
    }
    @Test
    public void testEmptyName(){
        Assert.assertThrows(NotEnoughInfoForNewStudentException.class,
                ()->this.studentService.save("username","password","","arsovski"));
        Mockito.verify(this.studentService).save("username","password","","arsovski");
    }
    @Test
    public void testEmptySurname(){
        Assert.assertThrows(NotEnoughInfoForNewStudentException.class,
                ()->this.studentService.save("username","password","david",""));
        Mockito.verify(this.studentService).save("username","password","david","");
    }

    @Test
    public void testUsernameAlreadyExists(){
        Mockito.when(this.studentRepository.existsByUsername(Mockito.anyString())).thenReturn(true);
        Assert.assertThrows(UsernameAlreadyInUse.class,
                ()->this.studentService.save("username","password","david","arsovski"));
        Mockito.verify(this.studentService).save("username","password","david","arsovski");
    }

}
