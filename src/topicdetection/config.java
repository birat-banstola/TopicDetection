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
                .setOAuthConsumerKey("consumer_key") // to be replaced 
                .setOAuthConsumerSecret("secret_key") // to be replaced 
                .setOAuthAccessToken("access_token") // to be replaced 
                .setOAuthAccessTokenSecret("access_secret_token"); // to be replaced 
        tf = new TwitterFactory(configurationBuilder.build());
        twitter = tf.getInstance();
        return twitter;
    }
}
