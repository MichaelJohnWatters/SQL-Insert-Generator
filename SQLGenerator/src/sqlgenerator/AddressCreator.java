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
 */
public class AddressCreator {

    DatabaseConnection databaseConnection = new DatabaseConnection();
    private final static int maxAddressNumber = 200;

    public AddressCreator() {

    }//constructor

    //Assebles a random number , street name and street post fix into a full address.
    public String AssembleAddress() {
        String fullAddress = "";

        fullAddress += Integer.toString(findAddressNumber()) + " " + findStreetName() + " " + findStreetPostFix();

        return fullAddress;
    }

    //finds a random number between 1 and 200
    public int findAddressNumber() {

        Random rnd = new Random();
        int rndNum = rnd.nextInt((maxAddressNumber - 1) + 1) + 1;

        return rndNum;
    }

    //finds and random StreetName from the database.
    public String findStreetName() {
        String streetName = "";
        int numOfRecords = databaseConnection.countNumOfRecordsInQuery("tblStreetNames");

        Random rnd = new Random();
        int rndNum = rnd.nextInt((numOfRecords - 1) + 1) + 1;

        // rnd number, table name , column name;
        streetName = databaseConnection.getStringFromTable(rndNum, "tblStreetNames", "StreetName");

        return streetName;
    }//findStreetName

    //finds a random StreetPostFix from the Database.
    public String findStreetPostFix() {
        String postFix = "";
        int numOfRecords = databaseConnection.countNumOfRecordsInQuery("tblStreetPostFix");

        Random rnd = new Random();
        int rndNum = rnd.nextInt((numOfRecords - 1) + 1) + 1;

        // rnd number, table name , column name;
        postFix = databaseConnection.getStringFromTable(rndNum, "tblStreetPostFix", "PostFix");

        return postFix;
    }//findStreetPostfix

}//class
