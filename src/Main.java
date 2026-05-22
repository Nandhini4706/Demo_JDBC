import java.util.*;

import java.sql.*;

import static java.lang.Class.forName;

public class Main{
    public static void main(String[]args){
       Scanner sc=new Scanner(System.in);
       try{
           //Load My sql
           Class.forName("com.mysql.cj.jdbc.Driver");
           //Connect mysql
           Connection con = DriverManager.getConnection(
                   "jdbc:mysql://localhost:3306/",
                   "root",
                   "Nandhu07"
           );
           Statement stmt=con.createStatement();
           stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS students_db");
           System.out.println("DataBase Created Successfully");
           stmt.executeUpdate("Use students_db");

           String createTable="CREATE TABLE IF NOT EXISTS students_db("+ "id INT PRIMARY KEY, " +
           "name VARCHAR(50)," +
           "age INT" + ")";

           stmt.executeUpdate(createTable);
           System.out.println("created successfully");

           System.out.println("id");
           int id=sc.nextInt();
           sc.nextLine();
           System.out.println("Enter name");
           String name=sc.nextLine();
           System.out.println("Enter age");
           int age =sc.nextInt();
           sc.nextLine();
           // insert query
           String insertQuery = "INSERT INTO students_db(id,name,age) VALUES(?,?,?)" ;
           PreparedStatement ps = con.prepareStatement(insertQuery);
           ps.setInt(1,id);
           ps.setString(2,name);
           ps.setInt(3,age);

           int rows = ps.executeUpdate();

           if (rows > 0) {
               System.out.println("Student details inserted successfully");
           }
           ResultSet rs = stmt.executeQuery("SELECT * FROM students_db");
           System.out.println("\nStudent Details:");
           while (rs.next()) {
               System.out.println(
                       rs.getInt("id") + " " +
                               rs.getString("name") + " " +
                               rs.getInt("age")
               );
           }
           con.close();
           sc.close();
       }catch (Exception e){
           System.out.println(e);
       }
    }
}