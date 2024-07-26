package it.powerservice.managermag;

import it.powerservice.managermag.customClass.CustomImpostazioniRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CategorieService {
    @Autowired
    DataSource dataSource;
    @Autowired
    CategorieRepository categorieRepository;
    @Autowired
    CategorieRifRepository categorieRifRepository;
    public Categorie getCategoriesRoot() throws SQLException {
        Categorie category = null;

        String sql = "SELECT * FROM categorie WHERE codice='ALL'";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String codice = rs.getString("codice");
                String descrizione = rs.getString("descrizione");
                String descrizioneEng = rs.getString("descrizioneEng");
                String descrizioneExtraEng = rs.getString("descrExtraEng");
                Boolean visibileWeb = rs.getBoolean("visibileWeb");
                Boolean abilitaScPerModPag = rs.getBoolean("abilitaScPerModPag");
                String percorsoCompleto = rs.getString("percorsoCompleto");

                category = new Categorie(id, codice, descrizione, descrizioneEng, descrizioneExtraEng, visibileWeb, abilitaScPerModPag, percorsoCompleto);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return category;
    }

    public Categorie getCategoriaById(Long id) throws SQLException {
        Categorie category = null;

        String sql = "SELECT * FROM categorie WHERE id = ?";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                Long categoryId = rs.getLong("id");
                String codice = rs.getString("codice");
                String descrizione = rs.getString("descrizione");
                String descrizioneEng = rs.getString("descrizioneEng");
                String descrizioneExtraEng = rs.getString("descrExtraEng");
                Boolean visibileWeb = rs.getBoolean("visibileWeb");
                Boolean abilitaScPerModPag = rs.getBoolean("abilitaScPerModPag");
                String percorsoCompleto = rs.getString("percorsoCompleto");

                category = new Categorie(categoryId, codice, descrizione, descrizioneEng, descrizioneExtraEng, visibileWeb, abilitaScPerModPag, percorsoCompleto);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return category;
    }

    public List<Categorie> getChildrenCategories(Long categoryId) throws SQLException {
        List<Categorie> categories = new ArrayList<>();

        String sql = "SELECT categorie.*, \n" +
                "(SELECT COUNT(*) \n" +
                " FROM categorierif \n" +
                " WHERE categorierif.idcategoriaPartenza = categorie.id) AS numeroFigli \n" +
                "FROM categorie \n" +
                "LEFT JOIN categorierif ON categorie.id = categorierif.idcategoriaArrivo \n" +
                "WHERE categorierif.idcategoriaPartenza = ?;\n";


        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, categoryId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String codice = rs.getString("codice");
                String descrizione = rs.getString("descrizione");
                String descrizioneEng = rs.getString("descrizioneEng");
                String descrizioneExtraEng = rs.getString("descrExtraEng");
                Boolean visibileWeb = rs.getBoolean("visibileWeb");
                Boolean abilitaScPerModPag = rs.getBoolean("abilitaScPerModPag");
                String percorsoCompleto = rs.getString("percorsoCompleto");
                Integer numeroFigli = rs.getInt("numeroFigli");

                var category = new Categorie(id, codice, descrizione, descrizioneEng, descrizioneExtraEng, visibileWeb, abilitaScPerModPag, percorsoCompleto, numeroFigli);
                categories.add(category);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return categories;
    }

    public void createCategory(String codice, String descrizione, Long idCategoriaPartenza)  {
        var categoryToSave = new Categorie(codice, descrizione, false, false);
        Categorie savedCategory = categorieRepository.save(categoryToSave);
        CategorieRif categoryRefToSave = new CategorieRif(idCategoriaPartenza, savedCategory.getId());
        categorieRifRepository.save(categoryRefToSave);
    }
    public void updateCategory(String codice, String descrizione, Long idCategoriaPartenza)  {
        Categorie categoryToUpdate = categorieRepository.findById(idCategoriaPartenza).orElseThrow(() -> new RuntimeException("Categoria non trovata"));
        categoryToUpdate.setCodice(codice);
        categoryToUpdate.setDescrizione(descrizione);
        categorieRepository.save(categoryToUpdate);
    }


    public void updateCategorieRif(Long idCategoriaPartenza, Long idCategoriaArrivo) throws SQLException {
        String sqlUpdate = "UPDATE categorierif SET idCategoriaPartenza = ? WHERE idCategoriaArrivo = ?";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;

        try {
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sqlUpdate);
            ps.setLong(1, idCategoriaPartenza);
            ps.setLong(2, idCategoriaArrivo);
            ps.executeUpdate();
            conn.commit();
        } catch (SQLException e) {
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
            throw e;
        } finally {
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }
    }
}
