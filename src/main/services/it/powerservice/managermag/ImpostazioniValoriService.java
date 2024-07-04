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
public class ImpostazioniValoriService {

    @Autowired
    DataSource dataSource;

    public List<ImpostazioniValori> getImpostazioniValori() throws SQLException {
        List<ImpostazioniValori> impostazioniValoriList = new ArrayList<>();

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT codiceImpostazione, valoreMostrato, valoreStringa, valoreNumero, valoreClob " +
                "FROM impostazionivalori";

        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String codiceImpostazione = rs.getString("codiceImpostazione");
                String valoreMostrato = rs.getString("valoreMostrato");
                String valoreStringa = rs.getString("valoreStringa");
                Double valoreNumero = rs.getDouble("valoreNumero");
                String valoreClob = rs.getString("valoreClob");

                ImpostazioniValori impostazioniValori = new ImpostazioniValori(codiceImpostazione, valoreMostrato, valoreStringa, valoreNumero, valoreClob);
                impostazioniValoriList.add(impostazioniValori);
            }

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {
                }
            }
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return impostazioniValoriList;
    }

    public List<ImpostazioniValori> getImpostazioniValori(String codiceImpostazione) throws SQLException {
        List<ImpostazioniValori> impostazioniValoriList = new ArrayList<>();

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT valoreMostrato, valoreStringa, valoreNumero, valoreClob " +
                "FROM impostazionivalori ";

        if (!codiceImpostazione.equals("%")) {
            sql += "WHERE CodiceImpostazione = ?";
        }

        try {
            ps = conn.prepareStatement(sql);

            if (!codiceImpostazione.equals("%")) {
                ps.setString(1, codiceImpostazione);
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                String valoreMostrato = rs.getString("valoreMostrato");
                String valoreStringa = rs.getString("valoreStringa");
                Double valoreNumero = rs.getDouble("valoreNumero");
                String valoreClob = rs.getString("valoreClob");

                ImpostazioniValori impostazioniValori = new ImpostazioniValori(codiceImpostazione, valoreMostrato, valoreStringa, valoreNumero, valoreClob);
                impostazioniValoriList.add(impostazioniValori);
            }

        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException ignore) {
                }
            }
            if (ps != null) {
                try {
                    ps.close();
                } catch (SQLException ignore) {
                }
            }
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return impostazioniValoriList;
    }
}
