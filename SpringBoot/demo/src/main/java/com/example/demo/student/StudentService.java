package com.example.demo.student;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }


    public List<Student> getStudents() {
        return studentRepository.findAll();
    }

    public void addNewStudent(Student student) {
        studentExistsByEmail(student.getEmail());

        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
       studentExistsById(studentId);

        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentExistsById(studentId);
        if(name != null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email != null && email.length()>0 && !Objects.equals(student.getEmail(),email)){
            studentExistsByEmail(email);
            student.setEmail(email);
        }
    }

    private Student studentExistsById(Long studentId){
        Student student = studentRepository.findById(studentId).orElseThrow(()->new IllegalStateException(
                "student with id " +studentId+ " does not exists."
        ));
        return student;
    }

    private void studentExistsByEmail(String email){
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
        if(studentOptional.isPresent()){
            throw new IllegalStateException("email exists");
        }
    }
}
