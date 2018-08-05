/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author mjwat
 */
public class SQLBuilder {

    private Column[] arrColumnValues;
    private String tableName;
    private String databaseName;

    public SQLBuilder(Column[] arrColumnValues, String tableName, String databaseName) {
        this.arrColumnValues = arrColumnValues;
        this.tableName = tableName;
        this.databaseName = databaseName;
    }//constructor

    // This method processes all the Column Objects in arrColumnValues.
    // It Creates A SQL Insert Statement based on the Objects properties.
    public String processColumnsValues(int rows) {

        //Tracks if the First Value in a single insert. Eg ( firstValue, SecondValue)
        boolean firstValue = true;

        //Tracks the last insert row 
        //Eg (first row , first row),
        //(last row, last row);
        boolean lastValueRow = false;

        String value = "";
        String processedValue = "(";
        NameCreator NameCreator = new NameCreator();
        CompanyCreator companyCreator = new CompanyCreator();
        AddressCreator addressCreator = new AddressCreator();
        Currency currency = new Currency();
        CountryCreator countryCreator = new CountryCreator();

        for (int i = 0; i < rows; i++) {
            if (i == rows - 1) {
                lastValueRow = true;
            }
            for (Column column : arrColumnValues) {
                if (column.getDataType().toString() == "String") {
                    switch (column.getType().toString()) {
                        case "First Name":
                            if (firstValue == true) {
                                value = createValue(firstValue, NameCreator.createFirstName());
                                firstValue = false;
                            } else {
                                value = createValue(firstValue, NameCreator.createFirstName());
                            }
                            processedValue += value;
                            break;
                        case "Second Name":
                            if (firstValue == true) {
                                value = createValue(firstValue, NameCreator.createSecondName());
                                firstValue = false;
                            } else {
                                value = createValue(firstValue, NameCreator.createSecondName());
                            }
                            processedValue += value;
                            break;
                        case "Full Name":
                            if (firstValue == true) {
                                value = createValue(firstValue, NameCreator.createFullName());
                                firstValue = false;
                            } else {
                                value = createValue(firstValue, NameCreator.createFullName());
                            }
                            processedValue += value;
                            break;
                        case "Company":
                            if (firstValue == true) {
                                value = createValue(firstValue, companyCreator.createCompany());
                                firstValue = false;
                            } else {
                                value = createValue(firstValue, companyCreator.createCompany());
                            }
                            processedValue += value;
                            break;
                        case "Country":
                            if (firstValue == true) {
                                value = createValue(firstValue, countryCreator.createCountry());
                                firstValue = false;
                            } else {
                                value = createValue(firstValue, countryCreator.createCountry());
                            }
                            processedValue += value;
                            break;
                        case "Address":
                            if (firstValue == true) {
                                value = createValue(firstValue, addressCreator.AssembleAddress());
                                firstValue = false;
                            } else {
                                value = createValue(firstValue, addressCreator.AssembleAddress());
                            }
                            processedValue += value;
                            break;
                        default:
                            System.out.println("Error ProcessColumnValues ");
                            break;
                    }//switch

                } else if (column.getDataType().toString() == "int") {
                    switch (column.getType().toString()) {
                        case "Integer":
                            Random rnd = new Random();
                            int max = Integer.parseInt(column.getMax());
                            System.out.println("Max: " + max);
                            int min = Integer.parseInt(column.getMin());
                            System.out.println("Min: " + min);

                            int rndNum = rnd.nextInt((max - min) + 1) + min;
                            System.out.println("rndNum:" + rndNum);

                            if (firstValue == true) {
                                value = Integer.toString(rndNum);
                                firstValue = false;
                            } else {
                                value = ", " + Integer.toString(rndNum);
                            }
                            processedValue += value;
                            break;
                        case "Curreny":
                            int curr = 0;
                            curr = currency.createRangedCurrency(Integer.parseInt(column.getMin()), Integer.parseInt(column.getMax()));
                            if (firstValue == true) {
                                value = Double.toString(curr);
                                firstValue = false;
                            } else {
                                value = ", " + Double.toString(curr);
                            }
                            processedValue += value;
                            break;
                    }//switch
                } else if (column.getDataType().toString() == "date") {
                    switch (column.getType().toString()) {
                        case "test":
                            System.out.println("TEST");
                            break;
                        case "dd/mm/yyyy":
                            //Generates a random date with a range in the min/max column values
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                            String minDate = column.getMin();
                            String maxDate = column.getMax();

                            LocalDate localDateMin = LocalDate.parse(minDate, formatter);
                            LocalDate localDateMax = LocalDate.parse(maxDate, formatter);

                            long start = localDateMin.toEpochDay();
                            long end = localDateMax.toEpochDay();
                            long randomEpochDay = ThreadLocalRandom.current().longs(start, end).findAny().getAsLong();

                            System.out.println(LocalDate.ofEpochDay(randomEpochDay));
                            if (firstValue == true) {
                                value = "'" + LocalDate.ofEpochDay(randomEpochDay).toString() + "'";
                                firstValue = false;
                            } else {
                                value = ", " + "'" + LocalDate.ofEpochDay(randomEpochDay).toString() + "'";
                            }
                            processedValue += value;
                            break;
                    }//switch

                }// end if else

            }//2nd for loop
            //check if the value is the first in the insert statement
            firstValue = true;
            if (lastValueRow == true) {
                processedValue += ")";
            } else {
                processedValue += "),\n(";
            }
        }//1st for
        lastValueRow = false;
        return processedValue + ";";
    }//processColumnsValues

    //Assembles and returns the COMPLETED SQL insert statement.
    public String BuildSQLScript(String values) {
        //use Stringbuilder later
        String insert = "INSERT \nINTO " + tableName + " \n" + CreateColumnHeadings() + " \nVALUES\n";
        insert += values;
        return insert;
    }

    //creates a string of the column headings in the Database for the SQL Insert
    public String CreateColumnHeadings() {
        boolean firstColumnHeader = true;
        String value = "";
        for (int i = 0; i < arrColumnValues.length; i++) {
            if (firstColumnHeader == true) {
                value = value + (arrColumnValues[i].getName());
                firstColumnHeader = false;
            } else {
                value = value + " ," + (arrColumnValues[i].getName());
            }
        }//for
        return "(" + value + ")\n";
    }//CreateColumnHeadings

    //add description
    public String createValue(boolean firstValue, Object myMethod) {
        String createValue = "";
        if (firstValue == true) {
            createValue = "'" + myMethod + "'";
        } else {
            createValue = ", " + "'" + myMethod + "'";
        }
        return createValue;
    }//createValue
}//class
