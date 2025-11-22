package tn.esprit.studentmanagement.services;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.util.List;

@Service
@AllArgsConstructor        // generates a constructor for studentRepository
@Slf4j                     // generates a private static final Logger log
public class StudentService implements IStudentService {

    private final StudentRepository studentRepository;
    public List<Student> getAllStudents() {
        log.info("Request to get all students");
        List<Student> students = studentRepository.findAll();
        log.debug("Found {} students", students.size());
        return students;
    }
    public Student getStudentById(Long id) {
        log.info("Request to get student with id={}", id);
        return studentRepository.findById(id).map(student -> {
            log.debug("Student found: {}", student);
            return student;
        }).orElseGet(() -> {
            log.warn("No student found with id={}", id);
            return null;
        });
    }
    public Student saveStudent(Student student) {
        log.info("Request to save student: {}", student);
        Student saved = studentRepository.save(student);
        log.debug("Student saved with id={}", saved.getIdStudent());
        return saved;
    }
    public void deleteStudent(Long id) {
        log.info("Request to delete student with id={}", id);
        if (!studentRepository.existsById(id)) {
            log.warn("Delete called but no student exists with id={}", id);
            return;
        }
        studentRepository.deleteById(id);
        log.info("Student with id={} deleted successfully", id);
    }
}


