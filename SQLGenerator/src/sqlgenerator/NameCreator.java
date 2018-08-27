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
 * This class is used to create different types of names and
 * return them as a String to be used by other Objects.
 */
public class NameCreator {

    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public NameCreator() {

    }

    //Finds the number of names in the table in the database
    public int findNumOfFirstNames() {
        return databaseConnection.countFirstNamesInDatabase();
    }

    //Finds the number of names in the table in the database
    public int findNumOfSecondNames() {
        return databaseConnection.countSecondNamesInDatabase();
    }

    //Returns a String of combined first and second Names;
    public String createFullName() {
        //Find the Number of entries for each name type.
        int numOfFirstNames = findNumOfFirstNames();
        int numOfSecondNames = findNumOfSecondNames();
        String firstName;
        String secondName;
        String fullName;

        Random rnd = new Random();
        int rndNumFirstNames = rnd.nextInt((numOfFirstNames - 1) + 1) + 1;
        int rndNumSecondNames = rnd.nextInt((numOfSecondNames - 1) + 1) + 1;

        firstName = databaseConnection.getFirstName(rndNumFirstNames);
        secondName = databaseConnection.getSecondName(rndNumSecondNames);

        fullName = firstName + " " + secondName;

        return fullName;
    }

    public String createFirstName() {
        int numOfFirstNames = findNumOfFirstNames();
        String firstName;

        Random rnd = new Random();
        int rndNumFirstNames = rnd.nextInt((numOfFirstNames - 1) + 1) + 1;

        firstName = databaseConnection.getFirstName(rndNumFirstNames);

        return firstName;
    }

    public String createSecondName() {
        int numOfSecondNames = findNumOfSecondNames();
        String secondName;

        Random rnd = new Random();
        int rndNumSecondNames = rnd.nextInt((numOfSecondNames - 1) + 1) + 1;

        secondName = databaseConnection.getSecondName(rndNumSecondNames);
        return secondName;
    }

}//FulNameCreator
