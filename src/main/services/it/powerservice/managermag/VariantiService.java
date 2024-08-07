package it.powerservice.managermag;

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
public class VariantiService {
    @Autowired
    DataSource dataSource;
    @Autowired
    VariantiRepository variantiRepository;
    @Autowired
    VariantiRifRepository variantiRifRepository;
    public List<Varianti> findByType(String type) {
        return variantiRepository.findByTipo(type);
    }

    public List<Varianti> findChildrenById(Long variantId) throws SQLException {
        List<Varianti> variants = new ArrayList<>();

        String sql = "SELECT VARIANTI.* \n" +
                "FROM varianti \n" +
                "LEFT JOIN variantirif ON varianti.id = variantirif.idvarianteArrivo \n" +
                "WHERE variantirif.idvariantePartenza = ?;\n";


        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, variantId);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long idArticolo = rs.getLong("idarticolo");
                String tipo = rs.getString("tipo");
                String descrizione = rs.getString("descrizione");
                String descrizioneEng = rs.getString("descrizioneeng");
                Boolean obbligatorio = rs.getBoolean("obbligatorio");
                Boolean escludiStampa = rs.getBoolean("escludistampa");
                Boolean multiselezioneFigli = rs.getBoolean("multiselezionefigli");
                Boolean filtroWeb = rs.getBoolean("filtroweb");
                Boolean chkzeroArticolo = rs.getBoolean("chkzeroarticolo");
                String codCertAlimentare = rs.getString("codcertalimentare");
                String note = rs.getString("note");
                Boolean eliminato = rs.getBoolean("eliminato");
                Boolean invisibile = rs.getBoolean("invisibile");

                    var variant = new Varianti(id, idArticolo, tipo, descrizione, descrizioneEng, obbligatorio, escludiStampa, multiselezioneFigli, filtroWeb, chkzeroArticolo, codCertAlimentare, note, eliminato, invisibile);
                    variants.add(variant);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return variants;
    }

    public void createVariant(String descrizione, String descrizioneEng, String tipo, Long idVariantePartenza)  {
        var variantToSave = new Varianti(descrizione, descrizioneEng, tipo);
        Varianti savedVariant = variantiRepository.save(variantToSave);
        if (idVariantePartenza != null) {
            var varianteRif = new VariantiRif(idVariantePartenza, savedVariant.getId());
            variantiRifRepository.save(varianteRif);
        }
    }

    public void updateVariant(Varianti variantToUpdate)  {
        variantiRepository.save(variantToUpdate);
    }
    public void deleteById(Long variantId) throws Exception {
        Varianti variant = variantiRepository.findById(variantId).orElseThrow(() -> new Exception("categoria non trovata"));
        variant.setEliminato(true);
        variantiRepository.save(variant);
    }

}
