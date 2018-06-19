
package tikape.runko.database;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Koira;
/**
 *
 * @author katarina
 */
public class KoiraDao implements Dao<Koira, Integer> {
    private Database database;

    public KoiraDao(Database database) {
        this.database = database;
    }

    @Override
    public Koira findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Koira WHERE id = ?");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        
        
        
        Koira k = new Koira(id, nimi);
        k.setRotu(rs.getString("rotu"));
        k.setOmistaja(rs.getString("omistaja"));
        k.setSyntymaaika(rs.getString("syntymaaika"));
        k.setKenneli_id(rs.getInt("kenneli_id"));
        rs.close();
        stmt.close();
        connection.close();

        return k;}

    //@Override
    public List<Koira> findAll(Integer kennel_id) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Koira WHERE kenneli_id=?");
        stmt.setObject(1, kennel_id);

        ResultSet rs = stmt.executeQuery();
        List<Koira> koirat = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            koirat.add(new Koira(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return koirat;}

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public List<String> getNayttelyt(Integer key) throws SQLException {
        List<String>sijoitukset=new ArrayList<>();
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Nayttelykoira, Koiranayttely WHERE Nayttelykoira.koira_id = ? AND Nayttelykoira.koiranayttely_id=Koiranayttely.id");
        stmt.setInt(1, key);

        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            String pvm = rs.getString("paivamaara");
            String sijainti = rs.getString("sijainti");
            Integer sijoitus = rs.getInt("sijoitus");
            
            String tieto=pvm + " " + sijainti + " Sijoitus: " + sijoitus + ".";
            
            
            sijoitukset.add((tieto));
        }
        rs.close();
        stmt.close();
        connection.close();
        
        return sijoitukset;
    }

    @Override
    public Koira save(Koira obj) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Koira"
                + " (nimi, rotu, omistaja, syntymaaika, kenneli_id)"
                + " VALUES (?, ?, ?, ?, ?)");
        stmt.setString(1, obj.getNimi());
        stmt.setString(2, obj.getRotu());
        stmt.setString(3, obj.getOmistaja());
        stmt.setString(4, obj.getSyntymaaika());
        stmt.setInt(5, obj.getKenneli_id());

        stmt.executeUpdate();
        ResultSet avaimet=stmt.getGeneratedKeys();
        avaimet.next();
        stmt.close();
        
        stmt = conn.prepareStatement("SELECT * FROM Koira"
                + " WHERE id = ?");
        stmt.setInt(1, avaimet.getInt(1));
        

        ResultSet rs = stmt.executeQuery();
        rs.next(); 

        Koira k= new Koira(rs.getInt("id"), rs.getString("nimi"));
        k.setRotu(rs.getString("rotu"));
        k.setOmistaja(rs.getString("omistaja"));
        k.setSyntymaaika(rs.getString("syntymaaika"));
        k.setKenneli_id(rs.getInt("kenneli_id"));
        
        stmt.close();
        rs.close();

        conn.close();

        return k;
    }

    @Override
    public List<Koira> findAll() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void insertNayttely(Integer koira_id, String pvm, String sijainti, Integer sijoitus) throws SQLException {
        Connection conn = database.getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO Koiranayttely"
                + " (paivamaara, sijainti)"
                + " VALUES (?, ?)");
        stmt.setString(1, pvm);
        stmt.setString(2, sijainti);
        stmt.executeUpdate();
        
        ResultSet avaimet=stmt.getGeneratedKeys();
        avaimet.next();
        
        stmt.close();
        
        
        PreparedStatement stmt2 = conn.prepareStatement("INSERT INTO Nayttelykoira"
                + " (koiranayttely_id, koira_id, sijoitus)"
                + " VALUES (?, ?, ?)");
        stmt2.setInt(1, avaimet.getInt(1));
        stmt2.setInt(2, koira_id);
        stmt2.setInt(3, sijoitus);
        stmt2.executeUpdate();
        stmt2.close();
        
     

        conn.close();
    }
      
    
}
