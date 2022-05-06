/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import twitter4j.Trend;
import twitter4j.Trends;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import java.util.HashMap;
import java.util.Map;
import java.sql.*;

/**
 *
 * @author GAURAV
 */
public class TopicDetection {

    /**
     * @param args the command line arguments
     */
    public static Connection con;
    public static PreparedStatement ps;
    public static Statement stmt;
    public static ResultSet rs;
    public static void main(String[] args) throws TwitterException, SQLException, Exception {
        // TODO code application logic here
        
        con=DatabaseConn.getConnection();
        try{
            stmt=con.createStatement();
            stmt.executeUpdate("delete from weights");
            stmt.executeUpdate("delete from termfrequency");
            stmt.executeUpdate("delete from count");
            stmt.executeUpdate("delete from UserAuthority");
            stmt.executeUpdate("delete from nutrient");
        }
        catch(SQLException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        
        Twitter twitter=config.getTwitter();
        int hyd_woeid = 2295414;
        int world_woeid = 1;
        int india_woeid = 23424848;
        Map<String,Double> map=new HashMap();
        Trends trends = twitter.getPlaceTrends(india_woeid);
        double tf;
        double count=0;
        double max=0;
        for (Trend trend:trends.getTrends()){
            String keys[]=trend.getName().split(" ");
            for(String key:keys){
                key=key.replaceAll("#", "");
                key=key.replaceAll("vs"," ");
                count=tweetGetter.tweets(key);
                if (map.containsKey(key)){
                    double old=map.get(key);
                    map.replace(key,old+count);
                }
                else{
                    map.put(key,count);
                }
                if (max<map.get(key))
                    max=map.get(key);
                
            }
        }
        System.out.println("before max");
        for (Map.Entry<String,Double> entry : map.entrySet()) {
            String keyInMap=entry.getKey();
            System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
            tf=0.5+(0.5*map.get(keyInMap)/max);
            map.replace(keyInMap, tf);
            //System.out.println("Key = " + entry.getKey() +", Value = " + entry.getValue());
         }
        //System.out.println("after max");
        for(Map.Entry<String,Double> entry : map.entrySet()){
            String key=entry.getKey();
            Double weight=entry.getValue();
            //System.out.println("Key = " + key +", Value = " + weight);
            try{
                ps=con.prepareStatement("insert into weights values(?,?)");
                ps.setString(1,key);
                ps.setDouble(2,weight);
                ps.executeUpdate();
            }
            catch (SQLException e){
                System.out.println(e);
            }
            catch(Exception e){
                System.out.println(e);
            }
        }
          
        //System.out.println(map.toString());
        System.out.println();
        Authority.main();
        
        Nutrient.main();
        Drop.main();
        //con.close();
    }
    
}
