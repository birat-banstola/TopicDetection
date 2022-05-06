/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author GAURAV
 */
public class Nutrient {
    public static Connection con;
    public static PreparedStatement ps;
    public static Statement stmt;
    public static ResultSet rs;
    public static void main() throws SQLException{
        con=DatabaseConn.getConnection();
        double nutrient;
        
        Map<String,Double> map=new HashMap();
        Map<String,Double> nut=new HashMap();
        
        
        stmt=con.createStatement();
        String query="select keyword, termFrequency from weights order by keyword";
        rs=stmt.executeQuery(query);
        
        while(rs.next()){
            if(map.containsKey(rs.getString(1))){
                map.replace(rs.getString(1), rs.getDouble(2));
            }
            else{
                map.put(rs.getString(1), rs.getDouble(2));
            }
        }
        
        
        for (Map.Entry<String,Double> entry : map.entrySet()){
            nutrient=0;
            String key=entry.getKey();
            stmt=con.createStatement();
            rs=stmt.executeQuery("select authority from userauthority where keyword='"+key+"'");
            while(rs.next()){
                nutrient+=entry.getValue()*rs.getDouble(1);
            }
            nut.put(key, nutrient);
        }
        
        for(Map.Entry<String,Double> entry:nut.entrySet()){
            ps=con.prepareStatement("insert into nutrient values(?,?)");
            ps.setString(1, entry.getKey());
            ps.setDouble(2,entry.getValue());
            ps.executeUpdate();
        }
        
        
    }
}
