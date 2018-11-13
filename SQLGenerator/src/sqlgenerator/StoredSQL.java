/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sqlgenerator;

import java.util.Date;

/**
 *
 * @author mjwat
 */
class StoredSQL {
    private int id;
    private Date date;
    private String name;
    private String SQL;

    public StoredSQL(int id, Date date, String name, String SQL) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.SQL = SQL;
    }

    public int getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getSQL() {
        return SQL;
    }

 
    
}
