package tn.esprit.studentmanagement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;
import tn.esprit.studentmanagement.services.StudentService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents_shouldReturnListOfStudents() {
        // GIVEN
        List<Student> students = List.of(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        // WHEN
        List<Student> result = studentService.getAllStudents();

        // THEN
        assertEquals(2, result.size());
        verify(studentRepository, times(1)).findAll();
    }

    @Test
    void getStudentById_existingId_shouldReturnStudent() {
        // GIVEN
        Long id = 1L;
        Student student = new Student();
        when(studentRepository.findById(id)).thenReturn(Optional.of(student));

        // WHEN
        Student result = studentService.getStudentById(id);

        // THEN
        assertNotNull(result);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void getStudentById_nonExistingId_shouldReturnNull() {
        // GIVEN
        Long id = 99L;
        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        Student result = studentService.getStudentById(id);

        // THEN
        assertNull(result);
        verify(studentRepository, times(1)).findById(id);
    }

    @Test
    void saveStudent_shouldCallRepositorySave() {
        // GIVEN
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        // WHEN
        Student result = studentService.saveStudent(student);

        // THEN
        assertNotNull(result);
        verify(studentRepository, times(1)).save(student);
    }

    @Test
    void deleteStudent_shouldCallRepositoryDeleteById() {
        // GIVEN
        Long id = 1L;
        when(studentRepository.existsById(id)).thenReturn(true);

        // WHEN
        studentService.deleteStudent(id);

        // THEN
        verify(studentRepository, times(1)).existsById(id);
        verify(studentRepository, times(1)).deleteById(id);
    }
}
