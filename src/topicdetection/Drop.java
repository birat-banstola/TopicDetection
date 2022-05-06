/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author GAURAV
 */


public class Drop {
    public static Connection con;
    public static PreparedStatement ps;
    public static Statement stmt;
    public static ResultSet rs;
    public static void main() throws SQLException{
        double delta=1;
        int keyCount=1;
        double sumEnergy=1;
        con=DatabaseConn.getConnection();
        stmt=con.createStatement();
        rs=stmt.executeQuery("select COUNT(keyword) from nutrient");
        while(rs.next()){
            keyCount=rs.getInt(1);
        }
        rs=stmt.executeQuery("select SUM(keyNutrient) from nutrient");
        while(rs.next()){
            sumEnergy=rs.getDouble(1);
        }
        
        double drop=delta*(sumEnergy/keyCount);
        
        System.out.println(drop);
        rs=stmt.executeQuery("select keyword,keyNutrient from nutrient where keyNutrient>"+drop+" order by keyNutrient DESC");
        while(rs.next()){
            System.out.println(rs.getString(1)+"\t"+rs.getDouble(2));
        }
              
    }
}
