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
import java.util.*;

@Service
public class ImpostazioniValoriService {
    @Autowired
    DataSource dataSource;
    public List<ImpostazioniValori> getImpostazioniValori() throws SQLException {
        return getImpostazioniValori("%");
    }

    public List<ImpostazioniValori> getImpostazioniValori(String codiceImpostazione) throws SQLException  {
        //todo qui devo fare la query
        //CodiceImpostazione
        List<ImpostazioniValori> impostazioniValoris = new ArrayList<ImpostazioniValori>();

        Connection conn = DataSourceUtils.getConnection(dataSource);

        StringBuffer sql = new StringBuffer("");

        sql.append("SELECT valoreMostrato,valoreStringa,valoreNumero,valoreClob ");
        sql.append("FROM impostazionivalori ");
        if (!codiceImpostazione.equals("%")) {
            sql.append("where CodiceImpostazione=?");
        }

        try {
            PreparedStatement ps = null;
            ResultSet rs = null;

            ps = conn.prepareStatement(sql.toString());

            if (!codiceImpostazione.equals("%")) {
                //todo 1 passare parametri in ?
            }

            rs = ps.executeQuery();

            while (rs.next()) {
                String valoreMostrato = rs.getString("ValoreMostrato");
                String valoreStringa = rs.getString("EtichettaCampo") + ":";
                String valoreNumero = rs.getString("Categoria");
                String valoreClob = rs.getString("aspettocampo");

                //todo 2 fare fpr e creare la classe piena riempiendo la impostazioniValoris
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }


        return  impostazioniValoris;
    }
}
