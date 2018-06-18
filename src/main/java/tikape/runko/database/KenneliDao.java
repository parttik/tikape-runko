
package tikape.runko.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import tikape.runko.domain.Kenneli;

/**
 *
 * @author katarina
 */
public class KenneliDao implements Dao<Kenneli, Integer> {
    
    private Database database;

    public KenneliDao(Database database) {
        this.database = database;
    }
    
    @Override
    public Kenneli findOne(Integer key) throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kenneli WHERE id = ?");
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }
        
        Integer id=rs.getInt("id");
        String nimi = rs.getString("nimi");
        
        
        
        Kenneli k = new Kenneli(id, nimi);
       

        rs.close();
        stmt.close();
        connection.close();

        return k;
    }

    @Override
    public List<Kenneli> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Kenneli");

        ResultSet rs = stmt.executeQuery();
        List<Kenneli> kennelit = new ArrayList<>();
        while (rs.next()) {
            Integer id = rs.getInt("id");
            String nimi = rs.getString("nimi");

            kennelit.add(new Kenneli(id, nimi));
        }

        rs.close();
        stmt.close();
        connection.close();

        return kennelit;}

    @Override
    public void delete(Integer key) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
