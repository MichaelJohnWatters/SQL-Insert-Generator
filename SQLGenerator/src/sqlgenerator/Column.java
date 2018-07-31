/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

/**
 * Used to as an object to store the information about each rows requirements.
 *
 * @author mjwat
 *
 */
public class Column {

    private String dataType;
    private String type;
    private String max;
    private String min;
    private String name;

    public Column(){
        
    }
    public Column(String dataType, String type, String max, String min, String name) {
        this.dataType = dataType;
        this.type = type;
        this.max = max;
        this.min = min;
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
