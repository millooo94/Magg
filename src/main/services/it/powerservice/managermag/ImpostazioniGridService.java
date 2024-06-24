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
public class ImpostazioniGridService {
    @Autowired
    DataSource dataSource;

    public List<CustomImpostazioniRow> getImpostazioniRows() throws SQLException {
        List<CustomImpostazioniRow> customImpostazioniRows = new ArrayList<CustomImpostazioniRow>();

        String sql = "SELECT codice, impostazionicampi.EtichettaCampo, Categoria, aspettocampo, valorestringa, valoreclob, valorenumero, tipoCampo " +
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
                String codice = rs.getString("codice");
                String etichettaCampo = rs.getString("EtichettaCampo");
                String categoria = rs.getString("Categoria");
                String aspettoCampo = rs.getString("aspettocampo");
                String valoreStringa = rs.getString("valorestringa");
                String valoreClob = rs.getString("valoreclob");
                Double valoreNumero = rs.getDouble("valorenumero");
                String tipoCampo = rs.getString("tipoCampo");

                //1. ti chiami questo metodo di questo service
                //2. fai una griglia template sulla lista ritornata
                //3. fai vedere una riga fatta così:
                //      EtichettaCampo | campo con aspetto = aspettocampo con contenuto valoreStringa o valoreClob o valoreNumero in base al tipoCampo

                //4. in basso alla griglia devi mostrare il tasto salva.
                //5. cosa fa il salva? cerca con il service relativo nella impostazioniOggetto per quel dodice con GEN e iDOGGETTO=0 e tira giù la riga della domani
                //   per fare salva (insert o update)

                CustomImpostazioniRow c = new CustomImpostazioniRow(codice, tipoCampo, etichettaCampo, categoria, aspettoCampo,  valoreStringa,  valoreClob,  valoreNumero);

                customImpostazioniRows.add(c);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return customImpostazioniRows;
    }
}
