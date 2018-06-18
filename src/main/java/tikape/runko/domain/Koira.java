/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katarina
 */
public class Koira {
    private Integer id;
    private String nimi;
    private String omistaja;
    private String syntymaaika;
    private Integer kenneli_id;
    private String rotu;
    
    public Koira(String nimi) {
        this.nimi = nimi;
    }

    public Koira(Integer id, String nimi) {
        this.id = id;
        this.nimi = nimi;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }
    
    public String getOmistaja() {
        return this.omistaja;
    }
    
    public void setOmistaja(String nimi) {
        this.omistaja=nimi;
    }
    
    public String getSyntymaaika() {
        return this.syntymaaika;
    }
    
    public void setSyntymaaika(String dob) {
        this.syntymaaika=dob;
    }
    public int getKenneli_id() {
        return this.kenneli_id;
    }
    
    public void setKenneli_id(int id) {
        this.kenneli_id=id;
    }
    public String getRotu() {
        return this.rotu;
    }
    
    public void setRotu(String rotu) {
        this.rotu=rotu;
    }
    

}

