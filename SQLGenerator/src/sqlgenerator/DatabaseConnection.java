/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author mjwat
 *
 * This Class is used to connect to the Access database stored within the
 * program.
 *
 */
public class DatabaseConnection {

    //Path of the database in use.
    private final String path = "src/database/SQLGeneatorDB.accdb";
    //building the connection String
    private final String constr = "jdbc:ucanaccess://" + path;
    private final String NAME = "net.ucanaccess.jdbc.UcanaccessDriver";

    //use to build connection to database
    private Connection connection = null;
    private PreparedStatement prepStatement = null;
    private ResultSet resultSet = null;

    public DatabaseConnection() {
        try {
            Class.forName(NAME);
            connection = DriverManager.getConnection(constr);
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            System.exit(1);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }//DatabaseConnection

    public void closeDatabaseConnection() {
        try {
            connection.close();
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }//closeDatabaseConnection

    public int countNumOfRecordsInQuery(String table) {
        int count = 0;
        try {
            String sqlString = "SELECT * FROM " + table;
            prepStatement = connection.prepareCall(sqlString);
            resultSet = prepStatement.executeQuery();

            //Count the number of results
            while (resultSet.next() == true) {
                count++;
            }//while

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return count;
    }//countNumOfRecordsInQuery

    public String getStringFromTable(int ID, String table, String columnName) {
        String value = "";
        try {
            String sqlString = "SELECT * FROM " + table + " WHERE ID =" + ID + ";";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next() == true) {
                value = resultSet.getString(columnName);
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return value;
    }

    public ArrayList getStoredSQL() {
        ArrayList<StoredSQL> arraySQL = new ArrayList<StoredSQL>();

        try {
            String sqlString = "SELECT * FROM tblSavedSQL;";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                //Create result set.
                int id = resultSet.getInt("ID");
                Date date = resultSet.getDate("dateCreated");
                String name = resultSet.getString("nameOfInsert");
                String SQL = resultSet.getString("SQL");
                //Create Object, add to ArrayList.
                StoredSQL tempSQL = new StoredSQL(id, date, name, SQL);
                arraySQL.add(tempSQL);

                //TEST
                System.out.println("Add Row to array " + tempSQL.getName());
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return arraySQL;
    }

    public ArrayList getStoredSQLFilter(String value) {
        ArrayList<StoredSQL> arraySQL = new ArrayList<StoredSQL>();

        try {
            String sqlString = "SELECT * FROM tblSavedSQL WHERE nameOfInsert = '" + value + "';";
            System.out.println(sqlString);
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();
            while (resultSet.next()) {
                //Create result set.
                int id = resultSet.getInt("ID");
                Date date = resultSet.getDate("dateCreated");
                String name = resultSet.getString("nameOfInsert");
                String SQL = resultSet.getString("SQL");
                //Create Object, add to ArrayList.
                StoredSQL tempSQL = new StoredSQL(id, date, name, SQL);
                arraySQL.add(tempSQL);

                //TEST
                System.out.println("Add Row to array " + tempSQL.getName());
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return arraySQL;
    }

    public int countFirstNamesInDatabase() {
        int count = 0;
        try {
            // for each id in table greater than 0 ++ count
            String sqlString = "SELECT * FROM tblFirstNames WHERE ID > 0";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            //Counts the number of Names
            while (resultSet.next() == true) {
                count++;
            }
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return count;
    }//countFirstNamesInDatabase

    public int countSecondNamesInDatabase() {
        int count = 0;
        try {
            // for each id in table greater than 0 ++ count
            String sqlString = "SELECT * FROM tblSecondNames WHERE ID > 0;";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            //Counts the number of Names
            while (resultSet.next() == true) {
                count++;
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return count;
    }//countSecondNamesInDatabase

    public String getFirstName(int ID) {
        String firstName = null;
        try {
            String sqlString = "SELECT Names FROM tblFirstNames WHERE ID =" + ID + ";";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next() == true) {
                firstName = resultSet.getString("Names");
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return firstName;
    }//getFirstName

    public String getSecondName(int ID) {
        String secondName = null;
        try {
            String sqlString = "SELECT Names FROM tblSecondNames WHERE ID =" + ID + ";";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next() == true) {
                secondName = resultSet.getString("Names");
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return secondName;
    }//getSecondName

    public String getCompany(int ID) {
        String company = null;
        try {
            String sqlString = "SELECT Company FROM tblCompanys WHERE ID =" + ID + ";";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next() == true) {
                company = resultSet.getString("Company");
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return company;
    }//getCompany

    public String getCountry(int ID) {
        String company = null;
        try {
            String sqlString = "SELECT Company FROM tblCountrys WHERE ID =" + ID + ";";
            prepStatement = connection.prepareStatement(sqlString);
            resultSet = prepStatement.executeQuery();

            while (resultSet.next() == true) {
                company = resultSet.getString("Country");
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
        return company;
    }//getCountry

    //Creates a insert for the access database to store the generated SQL.
    public void insertSavedSQL(String sql, String name) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            String myDate = dtf.format(now);
            java.sql.Date javaSqlDate = java.sql.Date.valueOf(myDate);

            String sqlString = "INSERT INTO tblSavedSQL (dateCreated,nameOfInsert, SQL) VALUES(?,?,?)";
            prepStatement = connection.prepareStatement(sqlString);
            prepStatement.setDate(1, new java.sql.Date(javaSqlDate.getTime()));
            prepStatement.setString(2, name);
            prepStatement.setString(3, sql);

            int result = prepStatement.executeUpdate();
            //if the result is 1 then the record has been inserted successfully             
            if (result == 1) {
                JOptionPane dialogBox = new JOptionPane();
                JOptionPane.showMessageDialog(dialogBox, "Saved Completed", "Save Completed", JOptionPane.PLAIN_MESSAGE);

            } else {
                JOptionPane dialogBox = new JOptionPane();
                JOptionPane.showMessageDialog(dialogBox, "Error save failed.", "Warning", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            closeDatabaseConnection();
        }
    }//insertSavedSQL

    //TEMPORARY
    public String[] getAllDataTypes() {
        String[] arrTemp = new String[4];
        arrTemp[0] = "noValue";
        arrTemp[1] = "String";
        arrTemp[2] = "Number";
        arrTemp[3] = "date";
        return arrTemp;
    }//getCountry

    public String[] getAllStringTypes() {
        String[] arrTemp = new String[6];
        arrTemp[0] = "First Name";
        arrTemp[1] = "Second Name";
        arrTemp[2] = "Full Name";
        arrTemp[3] = "Company";
        arrTemp[4] = "Country";
        arrTemp[5] = "Address";
        return arrTemp;
    }//getCountry

    public String[] getAllIntTypes() {
        String[] arrTemp = new String[2];
        arrTemp[0] = "Integer";
        arrTemp[1] = "Currency";
        return arrTemp;
    }//getCountry

    public String[] getAllDateTypes() {
        String[] arrTemp = new String[1];
        arrTemp[0] = "dd/mm/yyyy";
        return arrTemp;
    }//getCountry

}//class
