import java.util.ArrayList;
import java.util.Iterator;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class CrudMethods {
    static String fileName = "DB.json";

    public static Status create(Student s) {
        String jsonString = "";
        try {
            jsonString = FileHandling.readFileToString(fileName);
            ObjectMapper mapper = new ObjectMapper();
            Students students = mapper.readValue(jsonString, Students.class);
            if (students.checkStudentExist(s.getRollNo())) {
                return new Status("unsuccess", "", "Student with rollno " + s.getRollNo() + " already exists");
            }
            students.addStudent(s);
            FileHandling.writeStringToFile(fileName, mapper.writeValueAsString(students));
            return new Status("success", "Student detail added successfully", "");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status("unsuccess", "", "Error occurred while adding student detail!");
        }
    }

    public static void read() {
        String jsonString = "";
        try {
            jsonString = FileHandling.readFileToString(fileName);
            ObjectMapper mapper = new ObjectMapper();
            Students students = mapper.readValue(jsonString, Students.class);
            if (students.getStudents().size() > 0) {
                Iterator<Student> itr = students.getStudents().iterator();
                while (itr.hasNext()) {
                    Student student = (Student) itr.next();
                    Log.info(student.toString());
                }
            } else {
                Log.info("The system doesnt have any student data!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Status update(int rollno, String field, String value) {
        String jsonString;
        try {
            jsonString = FileHandling.readFileToString(fileName);
            ObjectMapper mapper = new ObjectMapper();
            Students students = mapper.readValue(jsonString, Students.class);
            if(!students.checkStudentExist(rollno)) {
                return new Status("unsuccess", "", "Student with rollno " + rollno + " doesn't exists");
            }
            Students newUpdatedStudents = new Students();
            Iterator<Student> itr = students.getStudents().iterator();
            while(itr.hasNext()) {
                Student student = (Student)itr.next();
                if(student.getRollNo() == rollno) {
                    if(field.equals("fullname")) {
                        student.setName(value);
                        newUpdatedStudents.addStudent(student);
                    } else if(field.equals("age")) {
                        student.setAge(Integer.parseInt(value));
                        newUpdatedStudents.addStudent(student);
                    }else {
                        Log.info("Field " + field + " doesn't exist! please specify any of [name, age]");
                    }        
                }
                newUpdatedStudents.addStudent(student);
            }
            if(newUpdatedStudents.getStudents().size() > 0) {
                FileHandling.writeStringToFile(fileName, mapper.writeValueAsString(students));
                return new Status("success", "Student detail updated successfully", "");
            }

            throw new Error("Error occured while updating data!");
        } catch (Exception e) {
            e.printStackTrace();
            return new Status("unsuccess", "", "Error occurred while updating student detail!");
        }
    }

    public static Status delete(int rollno) {
        String jsonString;
        int count = 0;
        try {
            jsonString = FileHandling.readFileToString(fileName);
            ObjectMapper mapper = new ObjectMapper();
            Students students = mapper.readValue(jsonString, Students.class);
            if(!students.checkStudentExist(rollno)) {
                return new Status("unsuccess", "", "Student with rollno " + rollno + " doesn't exists");
            }
            Students newUpdatedStudents = new Students();
            Iterator<Student> itr = students.getStudents().iterator();
            while (itr.hasNext()) {
                Student student = (Student)itr.next();
                if(student.getRollNo() != rollno) {
                    newUpdatedStudents.addStudent(student);
                } else {
                    count++;
                } 
            }
            if(count > 0) {
                FileHandling.writeStringToFile(fileName, mapper.writeValueAsString(newUpdatedStudents));
                return new Status("success", "Student data deleted successfully", "");
            }
            throw new Error("Roll no not found!");

        } catch (Exception e) {
            return new Status("unsuccess", "", "Error occurred while deleting student data!");
        }
    }
}
