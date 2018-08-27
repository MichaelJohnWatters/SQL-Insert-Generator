/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;
import java.util.Random;

/**
 *
 * @author mjwat
 * 
 * This class is used to create a random currency value within a range.
 */
public class Currency {

    public Currency() {

    }//constructor

    public int createRangedCurrency(int min, int max) {
        Random rnd = new Random();
        int value = rnd.nextInt((max - min) + 1) + min;
        return  value;
    }//createRangedCurrency

}//class
