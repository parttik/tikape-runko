
package tikape.runko.database;

import java.sql.Connection;
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
        stmt.setObject(1, key);

        ResultSet rs = stmt.executeQuery();
        boolean hasOne = rs.next();
        if (!hasOne) {
            return null;
        }

        Integer id = rs.getInt("id");
        String nimi = rs.getString("nimi");
        
        
        
        Koira k = new Koira(id, nimi);
       

        rs.close();
        stmt.close();
        connection.close();

        return k;}

    @Override
    public List<Koira> findAll() throws SQLException {
        Connection connection = database.getConnection();
        PreparedStatement stmt = connection.prepareStatement("SELECT * FROM Koira");

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
    
}
