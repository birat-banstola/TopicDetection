/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 *
 * @author GAURAV
 */
public class config {
    public static ConfigurationBuilder configurationBuilder;
    public static TwitterFactory tf;
    public static Twitter twitter;
    public static Twitter getTwitter(){
        configurationBuilder = new ConfigurationBuilder();
        configurationBuilder.setDebugEnabled(true)
                .setOAuthConsumerKey("fK2ESbUFv6Un2pTqyVJKZHNxa")
                .setOAuthConsumerSecret("In9feQczHzRzOoOTVk2EwobBB0pQKD9rkqTya07wQL9dcHrmu7")
                .setOAuthAccessToken("969892374619963392-mUIdWWXrUTKAphKVsrZyUZBbtyAkac7")
                .setOAuthAccessTokenSecret("mPdEKXjAWWVGxllrzJLrGIb10SNbvhZd3Kgb92tZ0wkMj");
        tf = new TwitterFactory(configurationBuilder.build());
        twitter = tf.getInstance();
        return twitter;
    }
}
