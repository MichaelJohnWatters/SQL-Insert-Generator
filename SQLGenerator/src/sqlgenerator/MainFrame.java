/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

import javax.swing.*;

/**
 *
 * @author mjwat
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    //Database Connections and arrays of data retrieved from the Database.
    DatabaseConnection databaseConnection = new DatabaseConnection();
    String[] arrDataTypes = databaseConnection.getAllDataTypes();
    String[] arrStringTypes = databaseConnection.getAllStringTypes();
    String[] arrIntTypes = databaseConnection.getAllIntTypes();
    String[] arrDateTypes = databaseConnection.getAllDateTypes();
    boolean frameGeneratedSQLActive = false;
    frameGeneratedSQL frameGeneratedSQL = null;
    SQLBuilder sqlBuilder = null;

    //this method sets values in the type columns dependant on the selection on the dataType column.
    public void DataTypeActionPerformedMethod(JComboBox datatype, JComboBox type) {
        try {
            switch (datatype.getSelectedItem().toString()) {
                case "String":
                    type.removeAllItems();
                    for (String str : arrStringTypes) {
                        type.addItem(str);
                    }
                    break;
                case "int":
                    type.removeAllItems();
                    for (String str : arrIntTypes) {
                        type.addItem(str);
                    }
                    break;
                case "date":
                    type.removeAllItems();
                    for (String str : arrDateTypes) {
                        type.addItem(str);
                    }
                    break;
                default:
                    System.out.println("ERROR DataTypeActionPerformedMethod datatype: " + datatype.getName() + " type: " + type.getName());
                    break;
            }//switch
        } catch (Exception ex) {
            System.out.println(ex);
        }//catch
    }//DateTypeActionPerformed

    //
    public void cboTypeActionPerformedMethod(JComboBox cboDataType, JTextField max, JTextField min, JComboBox cboType, int colNumber) {
        try {
            //temp values
            if (cboDataType.getSelectedItem().toString() == "int") {
                //column label title
                lblMax.setVisible(true);
                lblMin.setVisible(true);

                max.setVisible(true);
                min.setVisible(true);
                max.setText("1");
                min.setText("0");
            } else if (cboDataType.getSelectedItem().toString() == "date") {
                //column label title
                lblMax.setVisible(true);
                lblMin.setVisible(true);

                max.setVisible(true);
                min.setVisible(true);
                max.setText("01/01/2018");
                min.setText("01/01/1990");
            } else {
                max.setVisible(false);
                min.setVisible(false);
                max.setText("0");
                min.setText("0");
                lblMax.setVisible(false);
                lblMin.setVisible(false);
            }
        } catch (Exception ex) {
            System.out.println("cboTypeActionPerformedMethod ERROR " + cboDataType.getName() + ex);
        }
    }

    //Assigns all required components to arrays
    public void setupMainFrame(JComboBox[] cboDataType, JComboBox[] cboType, JLabel[] lblColLabels, JTextField[] colMax, JTextField[] colMin, JTextField[] colName) {
        cboDataType[0] = cboColumn1DataType;
        cboDataType[1] = cboColumn2DataType;
        cboDataType[2] = cboColumn3DataType;
        cboDataType[3] = cboColumn4DataType;
        cboDataType[4] = cboColumn5DataType;
        cboDataType[5] = cboColumn6DataType;
        cboDataType[6] = cboColumn7DataType;
        cboDataType[7] = cboColumn8DataType;
        cboType[0] = cboColumn1Type;
        cboType[1] = cboColumn2Type;
        cboType[2] = cboColumn3Type;
        cboType[3] = cboColumn4Type;
        cboType[4] = cboColumn5Type;
        cboType[5] = cboColumn6Type;
        cboType[6] = cboColumn7Type;
        cboType[7] = cboColumn8Type;
        lblColLabels[0] = lblCol1;
        lblColLabels[1] = lblCol2;
        lblColLabels[2] = lblCol3;
        lblColLabels[3] = lblCol4;
        lblColLabels[4] = lblCol5;
        lblColLabels[5] = lblCol6;
        lblColLabels[6] = lblCol7;
        lblColLabels[7] = lblCol8;
        //arrColumnMaxValue
        colMax[0] = txtMaxCol1;
        colMax[1] = txtMaxCol2;
        colMax[2] = txtMaxCol3;
        colMax[3] = txtMaxCol4;
        colMax[4] = txtMaxCol5;
        colMax[5] = txtMaxCol6;
        colMax[6] = txtMaxCol7;
        colMax[7] = txtMaxCol8;
        //arrColumnMinValue
        colMin[0] = txtMinCol1;
        colMin[1] = txtMinCol2;
        colMin[2] = txtMinCol3;
        colMin[3] = txtMinCol4;
        colMin[4] = txtMinCol5;
        colMin[5] = txtMinCol6;
        colMin[6] = txtMinCol7;
        colMin[7] = txtMinCol8;
        //arrColumnNames
        colName[0] = txtColName1;
        colName[1] = txtColName2;
        colName[2] = txtColName3;
        colName[3] = txtColName4;
        colName[4] = txtColName5;
        colName[5] = txtColName6;
        colName[6] = txtColName7;
        colName[7] = txtColName8;

        lblMax.setVisible(false);
        lblMin.setVisible(false);

    }//setupMainFrame

    //Sets the number of columns that are required by the user. And displays them.
    public void setColumns(JComboBox[] cboDataType, JComboBox[] cboType, JLabel[] lblColLabels, JTextField[] colMax, JTextField[] colMin, JTextField[] colName) {
        //Number of columns user wants.
        int numOfColumns = (int) spnNumberOfColumns.getValue();

        for (int i = 0; i < cboDataType.length; i++) {
            if (i < numOfColumns) {
                //sets visable if the columns are required
                cboDataType[i].setVisible(true);
                cboType[i].setVisible(true);
                lblColLabels[i].setVisible(true);
                colName[i].setVisible(true);

                //Manage Values for Data Types
                cboDataType[i].removeAllItems();
                cboDataType[i].addItem(arrDataTypes[1]);
                cboDataType[i].addItem(arrDataTypes[2]);
                cboDataType[i].addItem(arrDataTypes[3]);

                //Manage Values for String Types
                cboType[i].removeAllItems();
                for (String str : arrStringTypes) {
                    cboType[i].addItem(str);
                }

            } else {
                //sets not visable if the columns are not required.
                cboDataType[i].setVisible(false);
                cboType[i].setVisible(false);
                lblColLabels[i].setVisible(false);
                colMax[i].setVisible(false);
                colMin[i].setVisible(false);
                colName[i].setVisible(false);

                cboDataType[i].removeAllItems();
                cboType[i].removeAllItems();
            }//else

        }//for

    }//setColumns

    //this object will hold all the values for each column.
    public Column CreateColumnObject(JComboBox cboDataType, JTextField max, JTextField min, JComboBox cboType, JTextField colName) {
        Column column = new Column();
        column.setDataType(cboDataType.getSelectedItem().toString());
        column.setType(cboType.getSelectedItem().toString());
        column.setMax(max.getText());
        column.setMin(min.getText());
        column.setName(colName.getText());
        return column;
    }//CreateColumnObject

    //This method returns an array for the column values that need to be processed.
    public Column[] PopulateSelectedColumnValues() {
        int numColumns = (int) spnNumberOfColumns.getValue();
        Column[] arrColumnValues = new Column[numColumns];

        switch (numColumns) {
            case 1:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                break;
            case 2:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                break;
            case 3:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                arrColumnValues[2] = CreateColumnObject(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, txtColName3);
                break;
            case 4:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                arrColumnValues[2] = CreateColumnObject(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, txtColName3);
                arrColumnValues[3] = CreateColumnObject(cboColumn4DataType, txtMaxCol4, txtMinCol4, cboColumn4Type, txtColName4);
                break;
            case 5:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                arrColumnValues[2] = CreateColumnObject(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, txtColName3);
                arrColumnValues[3] = CreateColumnObject(cboColumn4DataType, txtMaxCol4, txtMinCol4, cboColumn4Type, txtColName4);
                arrColumnValues[4] = CreateColumnObject(cboColumn5DataType, txtMaxCol5, txtMinCol5, cboColumn5Type, txtColName5);
                break;
            case 6:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                arrColumnValues[2] = CreateColumnObject(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, txtColName3);
                arrColumnValues[3] = CreateColumnObject(cboColumn4DataType, txtMaxCol4, txtMinCol4, cboColumn4Type, txtColName4);
                arrColumnValues[4] = CreateColumnObject(cboColumn5DataType, txtMaxCol5, txtMinCol5, cboColumn5Type, txtColName5);
                arrColumnValues[5] = CreateColumnObject(cboColumn6DataType, txtMaxCol6, txtMinCol6, cboColumn6Type, txtColName6);
                break;
            case 7:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                arrColumnValues[2] = CreateColumnObject(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, txtColName3);
                arrColumnValues[3] = CreateColumnObject(cboColumn4DataType, txtMaxCol4, txtMinCol4, cboColumn4Type, txtColName4);
                arrColumnValues[4] = CreateColumnObject(cboColumn5DataType, txtMaxCol5, txtMinCol5, cboColumn5Type, txtColName5);
                arrColumnValues[5] = CreateColumnObject(cboColumn6DataType, txtMaxCol6, txtMinCol6, cboColumn6Type, txtColName6);
                arrColumnValues[6] = CreateColumnObject(cboColumn7DataType, txtMaxCol7, txtMinCol7, cboColumn7Type, txtColName7);
                break;
            case 8:
                arrColumnValues[0] = CreateColumnObject(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, txtColName1);
                arrColumnValues[1] = CreateColumnObject(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, txtColName2);
                arrColumnValues[2] = CreateColumnObject(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, txtColName3);
                arrColumnValues[3] = CreateColumnObject(cboColumn4DataType, txtMaxCol4, txtMinCol4, cboColumn4Type, txtColName4);
                arrColumnValues[4] = CreateColumnObject(cboColumn5DataType, txtMaxCol5, txtMinCol5, cboColumn5Type, txtColName5);
                arrColumnValues[5] = CreateColumnObject(cboColumn6DataType, txtMaxCol6, txtMinCol6, cboColumn6Type, txtColName6);
                arrColumnValues[6] = CreateColumnObject(cboColumn7DataType, txtMaxCol7, txtMinCol7, cboColumn7Type, txtColName7);
                arrColumnValues[7] = CreateColumnObject(cboColumn8DataType, txtMaxCol8, txtMinCol8, cboColumn8Type, txtColName8);
                break;
        }//switch
        //This for loop will display to the console the values of each column.
        for (int i = 0; i < arrColumnValues.length; i++) {
            System.out.println("Column "
                    + i
                    + " DataType: "
                    + arrColumnValues[i].getDataType().toString()
                    + " type: " + arrColumnValues[i].getType());
        }//for loop
        return arrColumnValues;
    }//PopulateColumnValues

    public MainFrame() {
        initComponents();
        MainFrame.this.setSize(800, 300);
        cboColumn1DataType.setEnabled(false);
        cboColumn1Type.setEnabled(false);
        txtColName1.setEnabled(false);
        btnGenerate.setEnabled(false);
        JComboBox[] arrColumnsDataType = new JComboBox[8];
        JComboBox[] arrColumnsType = new JComboBox[8];
        JLabel[] arrColumnLabels = new JLabel[8];
        JTextField[] arrColumnMaxValue = new JTextField[8];
        JTextField[] arrColumnMinValue = new JTextField[8];
        JTextField[] arrColumnName = new JTextField[8];
        setupMainFrame(arrColumnsDataType, arrColumnsType, arrColumnLabels, arrColumnMaxValue, arrColumnMinValue, arrColumnName);
        setColumns(arrColumnsDataType, arrColumnsType, arrColumnLabels, arrColumnMaxValue, arrColumnMinValue, arrColumnName);
    }//MainFrame

    /**
     * This method is called from within the constructor to the form. WARNING:
     * Do NOT modify this code. The content of this method is always regenerated
     * by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblSelectParameters = new javax.swing.JLabel();
        lblNumberOfColumns = new javax.swing.JLabel();
        lblNumberOfRows = new javax.swing.JLabel();
        spnNumberOfColumns = new javax.swing.JSpinner();
        spnNumberOfRows = new javax.swing.JSpinner();
        lblTitle = new javax.swing.JLabel();
        lblDatabaseName = new javax.swing.JLabel();
        txtDatabaseName = new javax.swing.JTextField();
        lblTableName = new javax.swing.JLabel();
        txtTableName = new javax.swing.JTextField();
        btnConfirmParameters = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblCol1 = new javax.swing.JLabel();
        lblCol2 = new javax.swing.JLabel();
        lblCol3 = new javax.swing.JLabel();
        lblCol4 = new javax.swing.JLabel();
        lblCol5 = new javax.swing.JLabel();
        lblCol6 = new javax.swing.JLabel();
        lblCol7 = new javax.swing.JLabel();
        lblCol8 = new javax.swing.JLabel();
        lblDataType = new javax.swing.JLabel();
        lblType = new javax.swing.JLabel();
        cboColumn1DataType = new javax.swing.JComboBox<>();
        cboColumn2DataType = new javax.swing.JComboBox<>();
        cboColumn1Type = new javax.swing.JComboBox<>();
        cboColumn2Type = new javax.swing.JComboBox<>();
        cboColumn3DataType = new javax.swing.JComboBox<>();
        cboColumn3Type = new javax.swing.JComboBox<>();
        cboColumn4DataType = new javax.swing.JComboBox<>();
        cboColumn4Type = new javax.swing.JComboBox<>();
        cboColumn5DataType = new javax.swing.JComboBox<>();
        cboColumn5Type = new javax.swing.JComboBox<>();
        cboColumn6DataType = new javax.swing.JComboBox<>();
        cboColumn6Type = new javax.swing.JComboBox<>();
        cboColumn7DataType = new javax.swing.JComboBox<>();
        cboColumn7Type = new javax.swing.JComboBox<>();
        cboColumn8DataType = new javax.swing.JComboBox<>();
        cboColumn8Type = new javax.swing.JComboBox<>();
        lblMax = new javax.swing.JLabel();
        txtMaxCol1 = new javax.swing.JTextField();
        lblMin = new javax.swing.JLabel();
        txtMinCol1 = new javax.swing.JTextField();
        txtMaxCol2 = new javax.swing.JTextField();
        txtMinCol2 = new javax.swing.JTextField();
        txtMaxCol3 = new javax.swing.JTextField();
        txtMinCol3 = new javax.swing.JTextField();
        txtMaxCol4 = new javax.swing.JTextField();
        txtMinCol4 = new javax.swing.JTextField();
        txtMaxCol5 = new javax.swing.JTextField();
        txtMinCol5 = new javax.swing.JTextField();
        txtMaxCol6 = new javax.swing.JTextField();
        txtMinCol6 = new javax.swing.JTextField();
        txtMaxCol7 = new javax.swing.JTextField();
        txtMinCol7 = new javax.swing.JTextField();
        txtMaxCol8 = new javax.swing.JTextField();
        txtMinCol8 = new javax.swing.JTextField();
        lblColNames = new javax.swing.JLabel();
        txtColName1 = new javax.swing.JTextField();
        txtColName2 = new javax.swing.JTextField();
        txtColName3 = new javax.swing.JTextField();
        txtColName4 = new javax.swing.JTextField();
        txtColName5 = new javax.swing.JTextField();
        txtColName6 = new javax.swing.JTextField();
        txtColName7 = new javax.swing.JTextField();
        txtColName8 = new javax.swing.JTextField();
        btnGenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("SQL Insert Generator");
        setMinimumSize(new java.awt.Dimension(500, 250));
        setResizable(false);

        lblSelectParameters.setText("Select Parameters (not including Primary keys), current Max columns = 8 and Max rows = 1000");

        lblNumberOfColumns.setText("Number of Columns");

        lblNumberOfRows.setText("Number of Rows");

        spnNumberOfColumns.setModel(new javax.swing.SpinnerNumberModel(1, 1, 8, 1));

        spnNumberOfRows.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        lblTitle.setText("SQL Insert Generator");

        lblDatabaseName.setText("Database name:");

        txtDatabaseName.setNextFocusableComponent(txtTableName);

        lblTableName.setText("Table name:");

        txtTableName.setNextFocusableComponent(txtColName1);

        btnConfirmParameters.setText("Confirm Parameters");
        btnConfirmParameters.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmParametersActionPerformed(evt);
            }
        });

        lblCol1.setText("Column 1");

        lblCol2.setText("Column 2");

        lblCol3.setText("Column 3");

        lblCol4.setText("Column 4");

        lblCol5.setText("Column 5");

        lblCol6.setText("Column 6");

        lblCol7.setText("Column 7");

        lblCol8.setText("Column 8");

        lblDataType.setText("Data Type");

        lblType.setText("Type");

        cboColumn1DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn1DataTypeActionPerformed(evt);
            }
        });

        cboColumn2DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn2DataTypeActionPerformed(evt);
            }
        });

        cboColumn1Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn1TypeActionPerformed(evt);
            }
        });

        cboColumn2Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn2TypeActionPerformed(evt);
            }
        });

        cboColumn3DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn3DataTypeActionPerformed(evt);
            }
        });

        cboColumn3Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn3TypeActionPerformed(evt);
            }
        });

        cboColumn4DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn4DataTypeActionPerformed(evt);
            }
        });

        cboColumn4Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn4TypeActionPerformed(evt);
            }
        });

        cboColumn5DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn5DataTypeActionPerformed(evt);
            }
        });

        cboColumn5Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn5TypeActionPerformed(evt);
            }
        });

        cboColumn6DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn6DataTypeActionPerformed(evt);
            }
        });

        cboColumn6Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn6TypeActionPerformed(evt);
            }
        });

        cboColumn7DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn7DataTypeActionPerformed(evt);
            }
        });

        cboColumn7Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn7TypeActionPerformed(evt);
            }
        });

        cboColumn8DataType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn8DataTypeActionPerformed(evt);
            }
        });

        cboColumn8Type.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboColumn8TypeActionPerformed(evt);
            }
        });

        lblMax.setText("Max");

        lblMin.setText("Min");

        lblColNames.setText("Column Name");

        txtColName1.setNextFocusableComponent(txtColName2);

        txtColName2.setNextFocusableComponent(txtColName3);

        txtColName3.setNextFocusableComponent(txtColName4);

        txtColName4.setNextFocusableComponent(txtColName5);

        txtColName5.setNextFocusableComponent(txtColName6);

        txtColName6.setNextFocusableComponent(txtColName7);

        txtColName7.setNextFocusableComponent(txtColName8);

        txtColName8.setNextFocusableComponent(btnGenerate);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName2, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName3, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName5, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName6, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName7, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(lblColNames, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(lblCol8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtColName8, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cboColumn3DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboColumn4DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboColumn5DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboColumn6DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboColumn7DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboColumn8DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboColumn1DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDataType)
                    .addComponent(cboColumn2DataType, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblType)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(cboColumn8Type, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn7Type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn6Type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn5Type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn4Type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn3Type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn2Type, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cboColumn1Type, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMin)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtMinCol2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMinCol1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblMax)
                    .addComponent(txtMaxCol7, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol8, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol6, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol5, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol3, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 51, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblMin)
                    .addComponent(lblMax)
                    .addComponent(lblType)
                    .addComponent(lblDataType)
                    .addComponent(lblColNames))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboColumn1DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboColumn1Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMinCol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtMaxCol1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCol1)
                            .addComponent(txtColName1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboColumn2DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboColumn2Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblCol2)
                            .addComponent(txtColName2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMinCol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMaxCol2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboColumn3DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColumn3Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinCol3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCol3)
                    .addComponent(txtColName3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboColumn4DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColumn4Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinCol4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCol4)
                    .addComponent(txtColName4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboColumn5DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColumn5Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinCol5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCol5)
                    .addComponent(txtColName5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboColumn6DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColumn6Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinCol6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCol6)
                    .addComponent(txtColName6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboColumn7DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColumn7Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinCol7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCol7)
                    .addComponent(txtColName7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboColumn8DataType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboColumn8Type, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMinCol8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMaxCol8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCol8)
                    .addComponent(txtColName8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        btnGenerate.setText("Generate SQL ");
        btnGenerate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnGenerate.setNextFocusableComponent(txtDatabaseName);
        btnGenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerateActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblTitle)
                            .addComponent(lblSelectParameters)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblNumberOfColumns)
                                    .addComponent(lblDatabaseName, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblTableName, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(spnNumberOfColumns, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(lblNumberOfRows)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(spnNumberOfRows, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                                    .addComponent(txtTableName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDatabaseName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(btnConfirmParameters)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnGenerate, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(lblTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lblSelectParameters)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNumberOfColumns)
                    .addComponent(lblNumberOfRows)
                    .addComponent(spnNumberOfColumns, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnNumberOfRows, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDatabaseName)
                    .addComponent(txtDatabaseName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTableName)
                    .addComponent(txtTableName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmParameters)
                    .addComponent(btnGenerate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnConfirmParametersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmParametersActionPerformed
        lblMax.setVisible(false);
        lblMin.setVisible(false);
        JComboBox[] arrColumnsDataType = new JComboBox[8];
        JComboBox[] arrColumnsType = new JComboBox[8];
        JLabel[] arrColumnLabels = new JLabel[8];
        JTextField[] arrColumnMaxValue = new JTextField[8];
        JTextField[] arrColumnMinValue = new JTextField[8];
        JTextField[] arrColumnName = new JTextField[8];

        // add the array of col names
        setupMainFrame(arrColumnsDataType, arrColumnsType, arrColumnLabels, arrColumnMaxValue, arrColumnMinValue, arrColumnName);
        setColumns(arrColumnsDataType, arrColumnsType, arrColumnLabels, arrColumnMaxValue, arrColumnMinValue, arrColumnName);

        //Manage the size of the Jframe dependant on how many columns the user asks for.
        switch ((int) spnNumberOfColumns.getValue()) {
            case 1:
                MainFrame.this.setSize(800, 300);
                break;
            case 2:
                MainFrame.this.setSize(800, 330);
                break;
            case 3:
                MainFrame.this.setSize(800, 360);
                break;
            case 4:
                MainFrame.this.setSize(800, 390);
                break;
            case 5:
                MainFrame.this.setSize(800, 420);
                break;
            case 6:
                MainFrame.this.setSize(800, 450);
                break;
            case 7:
                MainFrame.this.setSize(800, 480);
                break;
            case 8:
                MainFrame.this.setSize(800, 515);
                break;
        }//switch
        //check the input parameters are valid, before setting the btnGenerate to setVisable == true.
        if ((int) spnNumberOfColumns.getValue() < 0 || (int) spnNumberOfColumns.getValue() > 8) {
            btnGenerate.setEnabled(false);
            // add dialog boxes.
            JOptionPane dialogBox = new JOptionPane();
            JOptionPane.showMessageDialog(dialogBox, "Invalid parameters", "Warning!", JOptionPane.WARNING_MESSAGE);
        } else if ((int) spnNumberOfRows.getValue() < 1 || (int) spnNumberOfRows.getValue() > 1000) {
            btnGenerate.setEnabled(false);
            JOptionPane dialogBox = new JOptionPane();
            JOptionPane.showMessageDialog(dialogBox, "Invalid parameters", "Warning!", JOptionPane.WARNING_MESSAGE);
        } 
        else if (txtDatabaseName.getText().equalsIgnoreCase("") || txtTableName.getText().equalsIgnoreCase("")) {
            btnGenerate.setEnabled(false);
            System.out.println("here" + txtDatabaseName.getText());
            JOptionPane dialogBox = new JOptionPane();
            JOptionPane.showMessageDialog(dialogBox, "Invalid parameters", "Warning!", JOptionPane.WARNING_MESSAGE);
        } else {
            btnGenerate.setEnabled(true);
            cboColumn1DataType.setEnabled(true);
            cboColumn1Type.setEnabled(true);
            txtColName1.setEnabled(true);
        }

    }//GEN-LAST:event_btnConfirmParametersActionPerformed

    private void cboColumn2DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn2DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn2DataType, cboColumn2Type);
    }//GEN-LAST:event_cboColumn2DataTypeActionPerformed

    private void cboColumn1TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn1TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn1DataType, txtMaxCol1, txtMinCol1, cboColumn1Type, 1);
    }//GEN-LAST:event_cboColumn1TypeActionPerformed

    private void cboColumn1DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn1DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn1DataType, cboColumn1Type);
    }//GEN-LAST:event_cboColumn1DataTypeActionPerformed

    private void cboColumn2TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn2TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn2DataType, txtMaxCol2, txtMinCol2, cboColumn2Type, 2);
    }//GEN-LAST:event_cboColumn2TypeActionPerformed

    private void cboColumn3DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn3DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn3DataType, cboColumn3Type);
    }//GEN-LAST:event_cboColumn3DataTypeActionPerformed

    private void cboColumn3TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn3TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn3DataType, txtMaxCol3, txtMinCol3, cboColumn3Type, 3);
    }//GEN-LAST:event_cboColumn3TypeActionPerformed

    private void cboColumn4DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn4DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn4DataType, cboColumn4Type);
    }//GEN-LAST:event_cboColumn4DataTypeActionPerformed

    private void cboColumn4TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn4TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn4DataType, txtMaxCol4, txtMinCol4, cboColumn4Type, 4);
    }//GEN-LAST:event_cboColumn4TypeActionPerformed

    private void cboColumn5DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn5DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn5DataType, cboColumn5Type);
    }//GEN-LAST:event_cboColumn5DataTypeActionPerformed

    private void cboColumn5TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn5TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn5DataType, txtMaxCol5, txtMinCol5, cboColumn5Type, 5);
    }//GEN-LAST:event_cboColumn5TypeActionPerformed

    private void cboColumn6DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn6DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn6DataType, cboColumn6Type);
    }//GEN-LAST:event_cboColumn6DataTypeActionPerformed

    private void cboColumn6TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn6TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn6DataType, txtMaxCol6, txtMinCol6, cboColumn6Type, 6);
    }//GEN-LAST:event_cboColumn6TypeActionPerformed

    private void cboColumn7DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn7DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn7DataType, cboColumn7Type);
    }//GEN-LAST:event_cboColumn7DataTypeActionPerformed

    private void cboColumn7TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn7TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn7DataType, txtMaxCol7, txtMinCol7, cboColumn7Type, 7);
    }//GEN-LAST:event_cboColumn7TypeActionPerformed

    private void cboColumn8DataTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn8DataTypeActionPerformed
        DataTypeActionPerformedMethod(cboColumn8DataType, cboColumn8Type);
    }//GEN-LAST:event_cboColumn8DataTypeActionPerformed

    private void cboColumn8TypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboColumn8TypeActionPerformed
        cboTypeActionPerformedMethod(cboColumn8DataType, txtMaxCol8, txtMinCol8, cboColumn8Type, 8);
    }//GEN-LAST:event_cboColumn8TypeActionPerformed

    private void btnGenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerateActionPerformed

        if (frameGeneratedSQLActive == false) {
            //These 2 lines create the SQL insert from  arrColumnValues.
            sqlBuilder = new SQLBuilder(PopulateSelectedColumnValues(), txtTableName.getText(), txtDatabaseName.getText());
            String sql = sqlBuilder.BuildSQLScript(sqlBuilder.processColumnsValues((int) spnNumberOfRows.getValue()));
            //Creates the Frame used to display the generated SQL Insert Statement created above.
            frameGeneratedSQL = new frameGeneratedSQL(sql);
            frameGeneratedSQL.setVisible(true);
            frameGeneratedSQLActive = true;
        } else if (frameGeneratedSQLActive == true) {
            sqlBuilder = new SQLBuilder(PopulateSelectedColumnValues(), txtTableName.getText(), txtDatabaseName.getText());
            String sql = sqlBuilder.BuildSQLScript(sqlBuilder.processColumnsValues((int) spnNumberOfRows.getValue()));
            frameGeneratedSQL.dispose();
            frameGeneratedSQL = new frameGeneratedSQL(sql);
            frameGeneratedSQL.setVisible(true);
        }

    }//GEN-LAST:event_btnGenerateActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }//run
        });
    }//main

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnConfirmParameters;
    private javax.swing.JButton btnGenerate;
    private javax.swing.JComboBox<String> cboColumn1DataType;
    private javax.swing.JComboBox<String> cboColumn1Type;
    private javax.swing.JComboBox<String> cboColumn2DataType;
    private javax.swing.JComboBox<String> cboColumn2Type;
    private javax.swing.JComboBox<String> cboColumn3DataType;
    private javax.swing.JComboBox<String> cboColumn3Type;
    private javax.swing.JComboBox<String> cboColumn4DataType;
    private javax.swing.JComboBox<String> cboColumn4Type;
    private javax.swing.JComboBox<String> cboColumn5DataType;
    private javax.swing.JComboBox<String> cboColumn5Type;
    private javax.swing.JComboBox<String> cboColumn6DataType;
    private javax.swing.JComboBox<String> cboColumn6Type;
    private javax.swing.JComboBox<String> cboColumn7DataType;
    private javax.swing.JComboBox<String> cboColumn7Type;
    private javax.swing.JComboBox<String> cboColumn8DataType;
    private javax.swing.JComboBox<String> cboColumn8Type;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCol1;
    private javax.swing.JLabel lblCol2;
    private javax.swing.JLabel lblCol3;
    private javax.swing.JLabel lblCol4;
    private javax.swing.JLabel lblCol5;
    private javax.swing.JLabel lblCol6;
    private javax.swing.JLabel lblCol7;
    private javax.swing.JLabel lblCol8;
    private javax.swing.JLabel lblColNames;
    private javax.swing.JLabel lblDataType;
    private javax.swing.JLabel lblDatabaseName;
    private javax.swing.JLabel lblMax;
    private javax.swing.JLabel lblMin;
    private javax.swing.JLabel lblNumberOfColumns;
    private javax.swing.JLabel lblNumberOfRows;
    private javax.swing.JLabel lblSelectParameters;
    private javax.swing.JLabel lblTableName;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblType;
    private javax.swing.JSpinner spnNumberOfColumns;
    private javax.swing.JSpinner spnNumberOfRows;
    private javax.swing.JTextField txtColName1;
    private javax.swing.JTextField txtColName2;
    private javax.swing.JTextField txtColName3;
    private javax.swing.JTextField txtColName4;
    private javax.swing.JTextField txtColName5;
    private javax.swing.JTextField txtColName6;
    private javax.swing.JTextField txtColName7;
    private javax.swing.JTextField txtColName8;
    private javax.swing.JTextField txtDatabaseName;
    private javax.swing.JTextField txtMaxCol1;
    private javax.swing.JTextField txtMaxCol2;
    private javax.swing.JTextField txtMaxCol3;
    private javax.swing.JTextField txtMaxCol4;
    private javax.swing.JTextField txtMaxCol5;
    private javax.swing.JTextField txtMaxCol6;
    private javax.swing.JTextField txtMaxCol7;
    private javax.swing.JTextField txtMaxCol8;
    private javax.swing.JTextField txtMinCol1;
    private javax.swing.JTextField txtMinCol2;
    private javax.swing.JTextField txtMinCol3;
    private javax.swing.JTextField txtMinCol4;
    private javax.swing.JTextField txtMinCol5;
    private javax.swing.JTextField txtMinCol6;
    private javax.swing.JTextField txtMinCol7;
    private javax.swing.JTextField txtMinCol8;
    private javax.swing.JTextField txtTableName;
    // End of variables declaration//GEN-END:variables
}
