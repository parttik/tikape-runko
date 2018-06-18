/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

import java.sql.Date;

/**
 *
 * @author katarina
 */
public class Koira {
    private Integer id;
    private String nimi;
    private String omistaja;
    private Date syntymaaika;
    private Integer kenneli_id;

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
    
    public Date getSyntymaaika() {
        return this.syntymaaika;
    }
    
    public void setSyntymaaika(Date dob) {
        this.syntymaaika=dob;
    }
    public int getKenneli_id() {
        return this.kenneli_id;
    }
    
    public void setKenneli_id(int id) {
        this.kenneli_id=id;
    }

}

