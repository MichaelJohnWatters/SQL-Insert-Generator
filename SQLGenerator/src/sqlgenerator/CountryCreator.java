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
 * This class i used to create a random country from those stored in the
 * Access database.
 */
public class CountryCreator {
    
        private DatabaseConnection databaseConnection = new DatabaseConnection();
    
    
        public String createCountry() {
        int numOfRecords = databaseConnection.countNumOfRecordsInQuery("tblCountrys");
        String country;

        //generate a random number
        Random rnd = new Random();
        int rndNum = rnd.nextInt((numOfRecords - 1) + 1) + 1;

        // rnd number, table name , column name;
         country = databaseConnection.getStringFromTable(rndNum, "tblCountrys", "Country");

        return country;
    }//createCompany

    public CountryCreator() {
    }//constructor
    
}//Class
