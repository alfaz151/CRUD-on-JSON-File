import java.util.Scanner;

class StudentOperations { 
    public static void main(String[] args) {
        String action, name, field;
        int rollno, age;
        try {
            Status result = new Status();
            new FileHandling();
            Log.info("-------Welcome to Student Management App-------");
            Log.info("Action to perform [create, read, update, delete]");
            Scanner s = new Scanner(System.in);
            Log.info("Please mention which action do want to perform by specifying action's name");
            action = s.nextLine();
            switch (action) {
                case "create":
                    Log.info("Enter fullname of the student");
                    name = s.nextLine();
                    Log.info("Enter age of the student");
                    age = s.nextInt();
                    Log.info("Enter rollno of the student");
                    rollno = s.nextInt();
                    Log.info("Adding student details....");
                    Student studentObj = new Student(name, age, rollno);
                    result = CrudMethods.create(studentObj);
                    if (result.status == "success") {
                        Log.info(result.msg);
                    } else {
                        Log.info("Error: " + result.error);
                    }
                    break;
                case "read":
                    CrudMethods.read();
                    break;
                case "update":
                    Log.info("Enter which field you want to update [fullname or age]");
                    field = s.nextLine();
                    Log.info("Enter rollno of the student to update its detail.");
                    rollno = s.nextInt();
                    Log.info(field);
                    if (field.equals("fullname")) {
                        Log.info("Enter fullname of the student");
                        String fullname = new Scanner(System.in).nextLine();
                        result = CrudMethods.update(rollno, field, fullname);
                    } else if (field.equals("age")) {
                        Log.info("Enter age of the student");
                        String updatedAge = new Scanner(System.in).nextLine();
                        result = CrudMethods.update(rollno, field, updatedAge);
                    }
                    if (result.status.equals("success")) {
                        Log.info(result.msg);
                    } else {
                        Log.info(result.error);
                    }
                    break;
                case "delete":
                    Log.info("Warning: this action will permanantly delete the data of student");
                    Log.info("Enter rollno of the student to delete data.");
                    rollno = s.nextInt();
                    Log.info("Deleting student details....");
                    result = CrudMethods.delete(rollno);
                    if (result.status.equals("success")) {
                        Log.info(result.msg);
                    } else {
                        Log.info(result.error);
                    }
                    break;
                default:
                    Log.info("Invalid action! please specify any one action from the [create, read, update, delete]");
                    break;
            }
            s.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
