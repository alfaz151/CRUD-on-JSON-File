public class Status {
    String status, msg, error;

    Status () {

    }
    
    Status(String status, String msg, String error) {
        this.status = status;
        this.error = error;
        this.msg = msg;
    }
}
