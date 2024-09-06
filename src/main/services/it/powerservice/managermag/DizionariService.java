package it.powerservice.managermag;

import com.powerservice.managermag.dizionari.utilities.DizionariUI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DizionariService {
    @Autowired
    DataSource dataSource;
    @Autowired
    DizionariRepository dizionariRepository;
    public List<Dizionari> getDizionari() {
        return dizionariRepository.getDizionari();
    }

    public List<DizionariUI> getDizionariUi(String dizionarioCategoria) throws SQLException {
        List<DizionariUI> dizionariUI = new ArrayList<>();

        // Updated SQL query to filter by the given `dizionarioCategoria`
        String sql = "SELECT \n" +
                "    d.Codice,\n" +
                "    d.Categoria,\n" +
                "    d.Descrizione AS DescrizioneDizionario,\n" +
                "    d.modificabile,\n" +
                "    c.descrizione AS DescrizioneCategoria,\n" +
                "    c.colonnaRestituita,\n" +
                "    c.mostraCodice\n" +
                "FROM \n" +
                "    dizionari d\n" +
                "INNER JOIN \n" +
                "    dizionaricategorie c ON d.Categoria = c.Categoria\n" +
                "WHERE \n" +
                "    d.Categoria = ?;";  // Filter condition for `dizionarioCategoria`

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            // Setting the parameter value for `dizionarioCategoria`
            ps.setString(1, dizionarioCategoria);
            rs = ps.executeQuery();

            // Processing the result set
            while (rs.next()) {
                String codice = rs.getString("Codice");
                String categoria = rs.getString("Categoria");
                String descrizioneDizionario = rs.getString("DescrizioneDizionario");
                boolean modificabile = rs.getBoolean("modificabile");
                String descrizioneCategoria = rs.getString("DescrizioneCategoria");
                String colonnaRestituita = rs.getString("colonnaRestituita");
                boolean mostraCodice = rs.getBoolean("mostraCodice");

                // Creating a DizionariUI object and setting its properties
                DizionariUI dizionario = new DizionariUI();
                dizionario.setCodice(codice);
                dizionario.setCategoria(categoria);
                dizionario.setDescrizioneDizionario(descrizioneDizionario);
                dizionario.setModificabile(modificabile);
                dizionario.setDescrizioneCategoria(descrizioneCategoria);
                dizionario.setColonnaRestituita(colonnaRestituita);
                dizionario.setMostraCodice(mostraCodice);

                // Adding the object to the list
                dizionariUI.add(dizionario);
            }
        } finally {
            // Closing resources properly
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return dizionariUI;
    }

    public  List<Dizionari> getDizionariFromCategoria(String categoria) {
        return dizionariRepository.getDizionariFromCategoria(categoria);
    }

    public void saveDizionari(Dizionari dizionario) {
        dizionariRepository.save(dizionario);
    }


    public void updateDescrizioneDizionario(String codice, String newDescrizione) {
        dizionariRepository.updateDescrizioneDizionario(codice, newDescrizione);
    }
}
