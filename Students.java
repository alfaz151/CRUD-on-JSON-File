import java.util.ArrayList;
import java.util.Iterator;

public class Students {
    private ArrayList<Student> students;
    
    public Students() {
        students = new ArrayList<Student>();
    }

    // getter and setter
    public ArrayList<Student> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Student> s) {
        students = s;
    }

    public void addStudent(Student s) {
        students.add(s);
    }

    public boolean checkStudentExist(int rollno) {
        int count = 0;
        Iterator<Student> itr = students.iterator();
        while (itr.hasNext()) {
            Student student = (Student) itr.next();
            if (student.getRollNo() == rollno) {
                count++;
            }
        }
        if (count > 0) {
            return true;
        }
        return false;
    }
}
