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
public class ImpostazioniCampiService {
    @Autowired
    DataSource dataSource;

    public List<ImpostazioniCampi> getImpostazioniCampi() throws SQLException {
        List<ImpostazioniCampi> impostazioniCampi = new ArrayList<>();

        String sql = "SELECT codice, impostazionicampi.EtichettaCampo, Categoria, aspettocampo, valorestringa, valoreclob, valorenumero " +
                "FROM impostazionicampi " +
                "LEFT JOIN impostazionioggetto ON (impostazionicampi.Codice = impostazionioggetto.CodiceImpostazione AND tipoOggetto = 'GEN') " +
                "WHERE impostazionicampi.Categoria LIKE '%'";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Long codice = rs.getLong("codice");
                String etichettaCampo = rs.getString("EtichettaCampo");
                String categoria = rs.getString("Categoria");
                String aspettoCampo = rs.getString("aspettocampo");
                String valoreStringa = rs.getString("valorestringa");
                String valoreClob = rs.getString("valoreclob");
                Long valoreNumero = rs.getLong("valorenumero");

                ImpostazioniCampi impostazioneCampo = new ImpostazioniCampi(codice, categoria, etichettaCampo, tipoCampo, aspettoCampo, valoreStringa, valoreClob, valoreNumero);
                impostazioniCampi.add(impostazioneCampo);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return impostazioniCampi;
    }
    }
}
