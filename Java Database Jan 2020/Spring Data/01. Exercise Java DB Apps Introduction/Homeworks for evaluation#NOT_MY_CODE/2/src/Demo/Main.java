package Demo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner s = new Scanner(System.in);

   /*     System.out.println("Enter user: " );
        String user = s.nextLine().trim();
        user = user.equals("")?"root": user;
        System.out.println("Enter password: " );
        String password = s.nextLine().trim();
        user = password.equals("")?"root":password;

        Properties prop = new Properties();
        prop.setProperty("user", user);
        prop.setProperty("password", password);
// 1. Load jdbc driver(optional)
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }*/
        //connect to db
        System.out.println("Loaded");
       Connection driver =  DriverManager.getConnection("jdbc:mysql://root:root@localhost:3306/soft_uni?useSSL=false"
       );
        System.out.println("connect");

        PreparedStatement ps = driver.prepareStatement("SELECT * FROM employees where salary > ?");
        System.out.println("min salary(default 20000):");
        String salsryStr = s.nextLine();
        double salary = salsryStr.equals("")? 20000: Double.parseDouble(salsryStr);
        ps.setDouble(1,salary);
       ResultSet rs =  ps.executeQuery();

       while (rs.next()){
           System.out.printf("%-15.15s, %-15.15s, %10.2f\n",
                   rs.getString("first_name"),
                   rs.getString("last_name"),
                    rs.getDouble("salary"));
       }
       driver.close();

    }
}
