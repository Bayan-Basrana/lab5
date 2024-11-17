package com.example.student.Controller;

import com.example.student.ApiResponse.ApiResponse;
import com.example.student.Model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    ArrayList<Student> students = new ArrayList<>();
@GetMapping("/get")
    public ArrayList<Student> getStudent(){
        return students;
    }

@PostMapping("/add")
    public ApiResponse addStudent(@RequestBody Student student){
    students.add(student);
    return new ApiResponse("student added successfully");
    }
    @PutMapping("/update/{id}")
    public ApiResponse updateStudent(@PathVariable int id,   @RequestBody Student student){
    for (Student s : students) {
        if (s.getId() == id) {
            s.setName(student.getName());
            s.setAge(student.getAge());
            s.setDegree(student.getDegree());
            s.setGPA(student.getGPA());
        }
    }return new ApiResponse("student updated successfully");
    }
@DeleteMapping("/delete/{id}")
public ApiResponse deleteStudent(@PathVariable int id){
    students.removeIf(s -> s.getId() == id);
    return new ApiResponse("student deleted successfully");
}


@GetMapping("/classify/{id}")
public ApiResponse classifyStudents (@PathVariable int id) {
    for (Student s : students) {
        if (s.getId() == id) {
            if (s.getGPA() >= 4.5 && s.getGPA() <= 5) {
                return new ApiResponse("student First Class Honors");
            }
            if (s.getGPA() >= 3.50 && s.getGPA() <= 4.49) {
                return new ApiResponse("student Second Class Honors");
            }
            if (s.getGPA() >= 3 && s.getGPA() <= 3.49) {
                return new ApiResponse("student Third Class Honors");
            }
            if (s.getGPA() >= 2 && s.getGPA() <= 2.99) {
                return new ApiResponse("student pass Class.");
            }
        }

    }return null;

}


    @GetMapping("/greaterThanAvr")
public ArrayList<Student> Students_GPA_greaterThanAvr (){
    double totalGPA = 0;
    for (Student s : students) {
        totalGPA += s.getGPA();}
    double averageGPA = totalGPA / students.size();
    ArrayList<Student> students_greaterThanAvr = new ArrayList<>();
    for (Student s : students) {
        if (s.getGPA() > averageGPA) {
            students_greaterThanAvr.add(s);
        }
    }        return students_greaterThanAvr;

}



}

