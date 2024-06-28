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

        String sql = "SELECT codice, impostazionicampi.EtichettaCampo, Categoria, aspettocampo, valorestringa, valoreclob, valorenumero, tipoCampo, " +
                "if( isnull(impostazionioggetto.CodiceImpostazione), 1, 0) as isNew " +
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
                String etichettaCampo = rs.getString("EtichettaCampo") + ":";
                String categoria = rs.getString("Categoria");
                String aspettoCampo = rs.getString("aspettocampo");
                String valoreStringa = rs.getString("valorestringa");
                String valoreClob = rs.getString("valoreclob");
                Double valoreNumero = rs.getDouble("valorenumero");
                String tipoCampo = rs.getString("tipoCampo");
                Short isNew = rs.getShort("isNew");

                //1. ti chiami questo metodo di questo service
                //2. fai una griglia template sulla lista ritornata
                //3. fai vedere una riga fatta così:
                //      EtichettaCampo | campo con aspetto = aspettocampo con contenuto valoreStringa o valoreClob o valoreNumero in base al tipoCampo

                //4. in basso alla griglia devi mostrare il tasto salva.
                //5. cosa fa il salva? cerca con il service relativo nella impostazioniOggetto per quel dodice con GEN e iDOGGETTO=0 e tira giù la riga della domani
                //   per fare salva (insert o update)

                CustomImpostazioniRow c = new CustomImpostazioniRow(codice, tipoCampo, etichettaCampo, categoria, aspettoCampo,  valoreStringa,  valoreClob,  valoreNumero, isNew);

                customImpostazioniRows.add(c);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return customImpostazioniRows;
    }
    public void saveImpostazioniRows(List<CustomImpostazioniRow> impostazioniRows) throws SQLException {
        String sqlUpdate = "UPDATE impostazionioggetto " +
                "SET valoreStringa = ?, valoreClob = ?, valoreNumero = ? " +
                "WHERE codiceImpostazione = ? AND tipoOggetto = 'GEN' AND idOggetto = 0";

        String sqlInsert = "INSERT INTO impostazionioggetto (CodiceImpostazione, tipoOggetto, iDoggetto, valoreStringa, valoreClob, valoreNumero) " +
                "VALUES (?, 'GEN', 0, ?, ?, ?)";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement psi = null;
        PreparedStatement psu = null;


        try {
            conn.setAutoCommit(false);

            psi = conn.prepareStatement(sqlInsert);
            psu = conn.prepareStatement(sqlUpdate);
            for (CustomImpostazioniRow row : impostazioniRows) {
                if (row.getIsNew() != null && row.getIsNew() == 1) {
                    psi.setString(1, row.getCodice());
                    psi.setString(2, row.getValoreStringa());
                    psi.setString(3, row.getValoreClob());
                    psi.setDouble(4, row.getValoreNumero());
                    psi.addBatch();
                } else {
                    psu.setString(1, row.getValoreStringa());
                    psu.setString(2, row.getValoreClob());
                    psu.setDouble(3, row.getValoreNumero());
                    psu.setString(4, row.getCodice());
                    psu.addBatch();
                }
            }

            if (psi != null) {
                psi.executeBatch();
            }

            if (psu != null) {
                psu.executeBatch();
            }
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
            if (psi != null) try { psi.close(); } catch (SQLException ignore) {}
            if (psu != null) try { psu.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }
    }
}
