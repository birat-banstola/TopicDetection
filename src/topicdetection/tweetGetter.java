/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import java.util.ArrayList;
import java.util.List;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import java.sql.*;
import twitter4j.User;
/**
 *
 * @author GAURAV
 */
public class tweetGetter {
    public static List<Status> tweets;
    public static int max;
    public static ArrayList<String> vocab;
    public static Twitter twitter;
    //public static int frequency;
    public static int tf;
    
    public static Connection con;
    public static Statement stmt;
    public static PreparedStatement ps;
    public static ResultSet rs;
    
    public static int tweets(String key){
        //frequency=0;
        tf=0;
        try{
            con=DatabaseConn.getConnection();
            System.out.println("key:"+key);
            Query query=new Query(key);
            QueryResult result;
            twitter=config.getTwitter();
            result=twitter.search(query);
            tweets=result.getTweets();
            for(Status tweet: tweets){
                String t=tweet.getText();
                int frequency=termFrequency.main(key,t);
                if(frequency>0){
                    String user=tweet.getUser().getScreenName();
                    int follower=tweet.getUser().getFollowersCount();
                    int friends=tweet.getUser().getFriendsCount();
                    System.out.println(user);
                    String status=tweet.getText();
                    System.out.println("@"+user+" - "+status);
                    System.out.println("frequency("+key+"): "+frequency);
                    tf++;
                    ps=con.prepareStatement("insert into termfrequency values(?,?,?,?)");
                    ps.setString(1, key);
                    ps.setString(2,user);
                    ps.setInt(3,frequency);
                    ps.setString(4, status);
                    ps.executeUpdate();
                    
                    ps=con.prepareStatement("insert into count values(?,?,?,?)");
                    ps.setInt(1,follower);
                    ps.setInt(2, friends);
                    ps.setString(3, user);
                    ps.setString(4,key);
                    ps.executeUpdate();
                }
                
            }
            System.out.println("tf(key): "+tf);
        }  
        catch(TwitterException e){
            System.out.println(e);
        }
        catch(Exception e){
            System.out.println(e);
        }
        return tf;
    }
}
