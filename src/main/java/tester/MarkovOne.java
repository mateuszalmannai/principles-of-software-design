package tester;
/**
 * Write a description of MarkovOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import duke.FileResource;

import java.util.ArrayList;
import java.util.Random;

public class MarkovOne extends AbstractMarkovModel {

    private static final String BASE_PATH = "/Users/mateusz/IdeaProjects/PrinciplesOfSoftwareDesign/src/main/resources/data/";
    private static final String ROMEO = BASE_PATH + "romeo.txt";
    private static final String CONFUCIUS = BASE_PATH + "confucius.txt";
    
    public MarkovOne() {
        myRandom = new Random();
    }
    
    public void setTraining(String s){
        myText = s;
    }
    
    public String getRandomText(int numChars){
        StringBuilder sb = new StringBuilder();
        int index = myRandom.nextInt(myText.length()-2);
        String key = myText.substring(index, index+1);
        sb.append(key);
        
        for(int k=0; k < numChars-1; k++){
            ArrayList<String> follows = getFollows(key);
            if (follows.size() == 0) {
                break;
            }
            index = myRandom.nextInt(follows.size());
            String next = follows.get(index);
            sb.append(next);
            key = next;
        }
        
        return sb.toString();
    }
    
    public String toString() {

        return "MarkovModel of order one";
    }

    public static void main(String[] args) {
        MarkovOne markovOne = new MarkovOne();
        FileResource fileResource = new FileResource(CONFUCIUS);
        markovOne.setTraining(fileResource.asString());
        ArrayList<String> follows = markovOne.getFollows("he");
        System.out.println(follows.size());
    }
}
