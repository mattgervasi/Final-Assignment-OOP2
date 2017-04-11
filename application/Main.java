package FinalAssignment.application;

import java.util.Date;

public class Main {

    public static void main(String[] args) throws Exception {
//        Database db = new Database("data.txt");
//        System.out.println(db.toString());
        Date time = new Date();
        time.setHours(25);
        System.out.println(time.getHours());
    }
    
}
