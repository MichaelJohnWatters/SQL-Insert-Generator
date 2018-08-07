/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 * @author mjwat
 *
 * This Class is used to process an array of columns and generate the SQL
 * Statement from that array of columns.
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

        //Tracks if the column is the first value in a single insert. 
        //Eg ( firstValue, secondValue)
        //   ( firstValue, secondValue, thridValue)
        boolean firstValue = true;

        //Tracks the location last Row of the insert statement
        //Eg    (first row , first row),
        //      (second row, second row),
        //      (last row, last row);
        boolean lastValueRow = false;

        String value = "";
        String processedValue = "(";
        //These are objects used to create Different kinds of random data.
        NameCreator NameCreator = new NameCreator();
        CompanyCreator companyCreator = new CompanyCreator();
        AddressCreator addressCreator = new AddressCreator();
        Currency currency = new Currency();
        CountryCreator countryCreator = new CountryCreator();

        for (int i = 0; i < rows; i++) {
            if (i == rows - 1) {
                lastValueRow = true;
            }
            //This loop
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

                } else if (column.getDataType().toString() == "Number") {
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
                        case "Currency":
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
                        case "dd/mm/yyyy":
                            try {
                                //Generates a random date with a range in the min/max column values
                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

                                // gets the column min max date values
                                String minDate = column.getMin();
                                String maxDate = column.getMax();

                                // Formats the user input dates to a ISO 8601 format.
                                LocalDate localDateMin = LocalDate.parse(minDate, formatter);
                                LocalDate localDateMax = LocalDate.parse(maxDate, formatter);

                                // used to create a random date between the two user given dates.
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
                            } catch (Exception ex) {
                                JOptionPane dialogBox = new JOptionPane();
                                JOptionPane.showMessageDialog(dialogBox, "Error - Invalid Min or Max data format.\n Try dd/MM/yyyy", "Warning - Invalid data.", JOptionPane.WARNING_MESSAGE);
                                break;
                            }
                            break;
                    }//switch

                }// end if else - date

            }//2nd for loop

            //reset firstValue
            firstValue = true;

            //checking if the current column being processed is the last in the insert rows.
            //And appends the correct ending to the insert statement row.
            if (lastValueRow == true) {
                processedValue += ")";
            } else {
                processedValue += "),\n(";
            }
        }//1st for

        //reset lastValueRow
        lastValueRow = false;

        return processedValue + ";";
    }//processColumnsValues

    //Assembles and returns the COMPLETED SQL insert statement as a string.
    public String BuildSQLScript(String values) {
        //use Stringbuilder later
        String insert = "INSERT \nINTO " + tableName + " \n" + CreateColumnHeadings() + " \nVALUES\n";
        insert += values;
        return insert;
    }

    //returns a string of the column headings to be used as part of the SQL generation
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

    //Used to add the correct SQL syntax surrounding a piecec of data.
    //Depending on if the data is the first value in a single insert row or not.
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
