package test;


import java.util.List;

import dao.StudentDAOImp;
import model.Student;

public class StudentDAOTest {
  public static void main(String[] args) {
    StudentDAOImp dao = new StudentDAOImp();
	List<Student> lst = dao.getAllStudents();  
	
	for(Student s: lst){
		System.out.println( s );
	}
    
  }

}