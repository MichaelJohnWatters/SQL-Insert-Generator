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
public class CompanyCreator {
    
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    
    
        public String createCompany() {
        int numOfRecords = databaseConnection.countNumOfRecordsInQuery("tblCompanys");
        String company;

        Random rnd = new Random();
        int rndNum = rnd.nextInt((numOfRecords - 1) + 1) + 1;

        // rnd number, table name , column name;
         company = databaseConnection.getStringFromTable(rndNum, "tblCompanys", "Company");

        return company;
    }//createCompany

    public CompanyCreator() {
    }//constructor

}//class
