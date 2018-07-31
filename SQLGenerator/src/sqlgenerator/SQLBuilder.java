/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

import java.util.Random;

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

        //First Value in a single insert. Eg ( firstValue, SecondValue)
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
                            //test
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
                            // double between the max and min
                            break;
                    }//switch
                } else if (column.getDataType().toString() == "Date") {
                    switch (column.getType().toString()) {
                        case "test":
                            //remove a later use
                            break;
                        case "dd/mm/yyyy":
                            // date between the 2 dates.
                            break;
                    }//switch
                }// end if else

            }//2nd for
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
