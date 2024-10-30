package it.powerservice.managermag;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Service
public class CategorieRifService {
    @Autowired
    CategorieRifRepository categorieRifRepository;

    @Autowired
    DataSource dataSource;

    public List<CategorieRif> getCategorieRif() {
        return categorieRifRepository.getCategorieRif();
    }

    public CategorieRif getCategoriaRifFromIdCategoriaArrivo(Long idCategoriaArrivo) throws SQLException {
        CategorieRif categoriaRif = null;

        String sql = "SELECT * FROM categorierif WHERE idcategoriaArrivo = ?";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, idCategoriaArrivo);
            rs = ps.executeQuery();

            if (rs.next()) {
                categoriaRif = new CategorieRif();
                categoriaRif.setIdCategoriaArrivo(rs.getLong("idcategoriaArrivo"));
                categoriaRif.setIdCategoriaPartenza(rs.getLong("idcategoriaPartenza"));
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return categoriaRif;
    }


    public boolean checkIdPartenzaExists(Long idPartenza) throws SQLException {
        boolean result = false;

        String sql = "SELECT EXISTS (SELECT 1 FROM categorierif WHERE idcategoriaPartenza = ?);";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, idPartenza);
            rs = ps.executeQuery();

            if (rs.next()) {
                result = rs.getBoolean(1);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return result;
    }

    public void updateCategorieRif(CategorieRif categoriaRif) throws SQLException {
        String sql = "UPDATE categorierif SET idcategoriaPartenza = ? WHERE idcategoriaArrivo = ?";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, categoriaRif.getIdCategoriaPartenza()); // Imposta il nuovo valore
            ps.setLong(2, categoriaRif.getIdCategoriaArrivo()); // Condizione per identificare quale record aggiornare
            ps.executeUpdate(); // Esegui l'aggiornamento
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }
    }
}
