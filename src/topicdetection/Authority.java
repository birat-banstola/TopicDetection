/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import twitter4j.Twitter;

/**
 *
 * @author GAURAV
 */
public class Authority {

    public static Connection con;
    public static PreparedStatement ps;
    public static Statement stmt;
    public static ResultSet rs;
    public static Twitter twitter;
    public static void main() throws Exception {
        //String screenName;
        twitter=config.getTwitter();
        double userAuthority;
        con = DatabaseConn.getConnection();
        double dumpFactor = 0.85;
        int followers;
        int friends;
        String userName;
        String key;
        stmt=con.createStatement();
        rs = stmt.executeQuery("select * from count");
        while (rs.next()) {
            followers=rs.getInt(1);
            friends=rs.getInt(2);
            userName=rs.getString(3);
            key=rs.getString(4);
            if(followers!=0 && friends!=0){
                userAuthority=dumpFactor*(friends/followers)+(1-dumpFactor);
            }
            else{
                userAuthority=1-dumpFactor;
            }
            ps=con.prepareStatement("insert into userauthority values(?,?,?)");
            ps.setString(1, userName);
            ps.setDouble(2, userAuthority);
            ps.setString(3, key);
            ps.executeUpdate();
        }
    }
}
