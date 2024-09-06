package it.powerservice.managermag;

import com.powerservice.managermag.depositiMarketplace.utilities.CustomDepositiMarketplace;
import jakarta.transaction.Transactional;
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
public class DepositiMarketplaceService {
    @Autowired
    DepositiMarketplaceRepository depositiMarketplaceRepository;
    @Autowired
    DataSource dataSource;
    public List<DepositiMarketplace> getDepositiMarketplace() {
        return depositiMarketplaceRepository.getDepositiMarketplace();
    }
    public List<CustomDepositiMarketplace> getDepositiByIdMarketplace(Long idMarketplace) throws SQLException {
        List<CustomDepositiMarketplace> customDepositiMarketplaces = new ArrayList<>();

        String sql = "SELECT d.id, d.nome, d.codice, dm.nonInviare " +
                "FROM depositi d " +
                "JOIN depositimarketplace dm ON d.id = dm.idDeposito " +
                "WHERE dm.idMarketplace = ?";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setLong(1, idMarketplace); // Imposta il parametro idMarketplace nella query SQL
            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String codice = rs.getString("codice");
                String nome = rs.getString("nome");
                Boolean nonInviare = rs.getBoolean("nonInviare");

                CustomDepositiMarketplace c = new CustomDepositiMarketplace(id, codice, nome, nonInviare);
                customDepositiMarketplaces.add(c);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return customDepositiMarketplaces;
    }

    public DepositiMarketplace findDepositoMarketplaceByIdDepositoAndIdMarketplace(Long idDepositp, Long idMarketplace) {
        return depositiMarketplaceRepository.findDepositoMarketplaceByIdDepositoAndIdMarketplace(idDepositp, idMarketplace);
    }

    public void saveDepositoMarketplace(DepositiMarketplace depositiMarketplace) { depositiMarketplaceRepository.save(depositiMarketplace);}


    @Transactional
    public void deleteByIdDeposito(Long idDeposito, Long idMarketplace) {
        depositiMarketplaceRepository.deleteByIdDeposito(idDeposito, idMarketplace);
    }
}
