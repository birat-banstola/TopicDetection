/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package topicdetection;

import org.apache.commons.lang.StringUtils;
import java.lang.*;
/**
 *
 * @author GAURAV
 */
public class termFrequency {
    public static int main(String input, String compare){
        input = input.toLowerCase();
        compare = compare.toLowerCase();
        String[] app=compare.split(" ");
        int count=0;
        for (String str:app){
            str=str.replaceAll("#","");
            if(str.equalsIgnoreCase(input)){
                count++;
            }
        }
        int number = StringUtils.countMatches(input, compare);
        return count;
        
    }
}
