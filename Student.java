public class Student {
    private String name;
    private int age, rollno;

    Student() {
     //default constructor   
    }

    Student(String name, int age, int rollno) {
        this.setName(name);
        this.setAge(age);
        this.setRollNo(rollno);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    };

    public int getRollNo() {
        return rollno;
    }

    public void setRollNo(int rollno) {
        this.rollno = rollno;
    }

    public String toString() {
        return "Name: "+name+ ", RollNo: "+rollno+ ", Age: "+ age;
    }
}
