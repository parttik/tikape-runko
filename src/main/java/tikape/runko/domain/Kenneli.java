/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tikape.runko.domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author katarina
 */
public class Kenneli {
    private List<Koira>kennelinkoirat=new ArrayList<>();
    private Integer id;
    private String nimi;
    
    public Kenneli(Integer id, String nimi) {
        this.id=id;
        this.nimi=nimi;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNimi() {
        return this.nimi;
    }
    
    public void lisaaKoira(Koira koira) {
        kennelinkoirat.add(koira);
    }
    
    public List<Koira> getKoirat() {
        return kennelinkoirat;
    }
}
