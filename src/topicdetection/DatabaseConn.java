/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author GAURAV
 */
public class DatabaseConn {
    static Connection con;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/proj", "root", "");
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Database Error" + e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return con;
    }
    
    public static void closeConnection(){
        if(con!=null){
            con=null;
        }
    }
}
