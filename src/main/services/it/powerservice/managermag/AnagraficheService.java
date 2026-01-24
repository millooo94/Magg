package it.powerservice.managermag;

import it.powerservice.managermag.customClass.AnagraficheEstese;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Service;
import org.zkoss.zul.A;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnagraficheService {
    @Autowired
    DataSource dataSource;
    @Autowired
    AnagraficheRepository anagraficheRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Anagrafiche> getAnagrafiche() {
        return anagraficheRepository.getAnagrafiche();
    }

    public List<Anagrafiche> getAnagraficheFromTipo(
            boolean ckCliente,
            boolean ckFornitore,
            boolean ckTrasportatore,
            boolean ckAgente,
            boolean ckPersonale) {

        System.out.println("ckCliente ===> " + ckCliente + " ckFornitore ===> " + ckFornitore + " ckTrasportatore ===> " + ckTrasportatore + " ckAgente ===> " + ckAgente + " ckPersonale ===> " + ckPersonale);
        System.out.println(ckCliente);

        return anagraficheRepository.getAnagraficheFromTipo(
                ckCliente,
                ckFornitore,
                ckTrasportatore,
                ckAgente,
                ckPersonale
        );
    }


    public void saveAnagrafica(Anagrafiche anagrafica) {
        anagraficheRepository.save(anagrafica);
    }

    public List<AnagraficheEstese> getAnagraficheEstese(String filters) {

        List<AnagraficheEstese> anagraficheEsteseList = new ArrayList<>();

        String sql = "SELECT DISTINCT a.id, a.codice, a.cognome, a.nome, a.ragioneSociale, a.sesso, i.pIva, i.codiceFiscale, i.regione, i.provincia, i.comune, i.provaData " +
                "FROM anagrafiche a " +
                "LEFT JOIN indirizzi i ON a.id = i.idAnagrafica";

        if (filters.length() > 6) {
            sql = sql + " " + filters;
        }

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String codice = rs.getString("codice");
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String ragioneSociale = rs.getString("ragionesociale");
                String sesso = rs.getString("sesso");
                String partitaIva = rs.getString("pIva");
                String codiceFiscale = rs.getString("codicefiscale");
                String regione = rs.getString("regione");
                String provincia = rs.getString("provincia");
                String comune = rs.getString("comune");
                Date provaData = rs.getDate("provadata");


                AnagraficheEstese anagrafica = new AnagraficheEstese(id, codice, cognome, nome, ragioneSociale, sesso, partitaIva, codiceFiscale, regione, provincia, comune, provaData);
                anagraficheEsteseList.add(anagrafica);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        System.out.println("ANAGRAFICHE ESTESE ===> " + anagraficheEsteseList);


        return  anagraficheEsteseList;

    }

    public List<AnagraficheEstese> getAnagraficheFromSearch(String searchWord) throws SQLException {
        List<AnagraficheEstese> anagraficheList = new ArrayList<>();

        String sql = "SELECT DISTINCT a.*, i.pIva, i.codiceFiscale, i.provincia, i.comune " +
                "FROM anagrafiche a " +
                "LEFT JOIN indirizzi i ON a.id = i.idAnagrafica " +
                "WHERE (a.nome LIKE CONCAT('%', ?, '%') " +
                "OR a.cognome LIKE CONCAT('%', ?, '%') " +
                "OR a.ragioneSociale LIKE CONCAT('%', ?, '%') " +
                "OR (i.nazione LIKE CONCAT('%', ?, '%')) " +
                "OR (i.regione LIKE CONCAT('%', ?, '%') AND i.regione IS NOT NULL) " +
                "OR (i.provincia LIKE CONCAT('%', ?, '%') AND i.provincia IS NOT NULL) " +
                "OR (i.comune LIKE CONCAT('%', ?, '%') AND i.comune IS NOT NULL) " +
                "OR (i.frazione LIKE CONCAT('%', ?, '%') AND i.frazione IS NOT NULL) " +
                "OR (i.cap LIKE CONCAT('%', ?, '%') AND i.cap IS NOT NULL) " +
                "OR (i.indirizzo LIKE CONCAT('%', ?, '%')) " +
                "OR (i.telefono1 LIKE CONCAT('%', ?, '%') AND i.telefono1 IS NOT NULL) " +
                "OR (i.telefono2 LIKE CONCAT('%', ?, '%') AND i.telefono2 IS NOT NULL) " +
                "OR (i.telefono3 LIKE CONCAT('%', ?, '%') AND i.telefono3 IS NOT NULL) " +
                "OR (i.cellulare LIKE CONCAT('%', ?, '%') AND i.cellulare IS NOT NULL) " +
                "OR (i.email LIKE CONCAT('%', ?, '%') AND i.email IS NOT NULL) " +
                "OR (i.pec LIKE CONCAT('%', ?, '%') AND i.pec IS NOT NULL) " +
                "OR (i.referente LIKE CONCAT('%', ?, '%') AND i.referente IS NOT NULL) " +
                "OR (i.pIva LIKE CONCAT('%', ?, '%') AND i.pIva IS NOT NULL) " +
                "OR (i.codiceFiscale LIKE CONCAT('%', ?, '%') AND i.codiceFiscale IS NOT NULL)) " +
                "OR (i.provaData LIKE CONCAT('%', ?, '%') AND i.provaData IS NOT NULL)) " +
                "AND i.tipoIndirizzo = 'S';";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, searchWord);
            ps.setString(2, searchWord);
            ps.setString(3, searchWord);
            ps.setString(4, searchWord);
            ps.setString(5, searchWord);
            ps.setString(6, searchWord);
            ps.setString(7, searchWord);
            ps.setString(8, searchWord);
            ps.setString(9, searchWord);
            ps.setString(10, searchWord);
            ps.setString(11, searchWord);
            ps.setString(12, searchWord);
            ps.setString(13, searchWord);
            ps.setString(14, searchWord);
            ps.setString(15, searchWord);
            ps.setString(16, searchWord);
            ps.setString(17, searchWord);
            ps.setString(18, searchWord);
            ps.setString(19, searchWord);
            ps.setString(20, searchWord);

            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                Long idTrasportatore = rs.getLong("idtrasportatore");
                Long idListino = rs.getLong("idlistino");
                Long idTipologiaPagamento = rs.getLong("idtipologiapagamento");
                String codice = rs.getString("codice");
                String tipo = rs.getString("tipo");
                Boolean ckCliente = rs.getBoolean("ckcliente");
                Boolean ckFornitore = rs.getBoolean("ckfornitore");
                Boolean ckTrasportatore = rs.getBoolean("cktrasportatore");
                Boolean ckAgente = rs.getBoolean("ckagente");
                Boolean ckPersonale = rs.getBoolean("ckpersonale");
                String soggetto = rs.getString("soggetto");
                String subCategoria = rs.getString("subcategoria");
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");

                String ragioneSociale = rs.getString("ragionesociale");
                String codSDI = rs.getString("codsdi");
                String sesso = rs.getString("sesso");
                String prodottiEServizi = rs.getString("prodottieservizi");
                String codIva = rs.getString("codiva");

                BigDecimal iva = rs.getBigDecimal("iva");
                String descrizioneIva = rs.getString("descrizioneiva");
                BigDecimal fido = rs.getBigDecimal("fido");
                BigDecimal sconto1 = rs.getBigDecimal("sconto1");
                BigDecimal sconto2 = rs.getBigDecimal("sconto2");
                BigDecimal sconto3 = rs.getBigDecimal("sconto3");

                String notes = rs.getString("notes");
                String noteFissedocumento = rs.getString("notefissedocumento");
                String noteRapide = rs.getString("noterapide");
                String lingua = rs.getString("lingua");
                String fasciaAppartenenza = rs.getString("fasciaappartenenza");

                Boolean certificazioneAlimentare = rs.getBoolean("certificazionealimentare");
                Boolean assicurazione = rs.getBoolean("assicurazione");
                java.sql.Date sqlDataAssicurazione = rs.getDate("dataassicurazione");
                LocalDate dataAssicurazione = (sqlDataAssicurazione != null) ? sqlDataAssicurazione.toLocalDate() : null;
                String ggChiusura = rs.getString("ggchiusura");
                String hhChiusura = rs.getString("hhchiusura");
                String status = rs.getString("status");

                java.sql.Date sqlDataNonInUso = rs.getDate("datanoninuso");
                LocalDate dataNonInUso = (sqlDataNonInUso != null) ? sqlDataNonInUso.toLocalDate() : null;
                String cciiaa = rs.getString("cciiaa");
                String enasarco = rs.getString("enasarco");
                Boolean newsLetter = rs.getBoolean("newsletter");
                String testoinvioMail = rs.getString("testoinviomail");
                Boolean revCharge = rs.getBoolean("revcharge");
                Boolean splitPayment = rs.getBoolean("splitpayment");
                Boolean ceeExtraCee = rs.getBoolean("ceeextracee");

                String colore = rs.getString("colore");
                String tipoDocumentoIdentita = rs.getString("tipodocumentoidentita");
                String numeroDocumentoIdentita = rs.getString("numerodocumentoidentita");
                String emessoDaDocumentoIdentita = rs.getString("emessodadocumentoidentita");

                java.sql.Date sqlDataEmissioneDocumentoIdentita = rs.getDate("dataemissionedocumentoidentita");
                LocalDate dataEmissioneDocumentoIdentita = (sqlDataEmissioneDocumentoIdentita != null) ? sqlDataEmissioneDocumentoIdentita.toLocalDate() : null;


                String provinciaNascita = rs.getString("provincianascita");
                String comuneNascita = rs.getString("comunenascita");
                String codiceFidelity = rs.getString("codicefidelity");

                java.sql.Timestamp sqlDataIns = rs.getTimestamp("datains");
                LocalDateTime dataIns = (sqlDataIns != null) ? sqlDataIns.toLocalDateTime() : null;


                java.sql.Timestamp sqlDataUpd = rs.getTimestamp("dataupd");
                LocalDateTime dataUpd = (sqlDataUpd != null) ? sqlDataUpd.toLocalDateTime() : null;

                Boolean eliminato = rs.getBoolean("eliminato");

                String partitaIva = rs.getString("pIva");
                String codiceFiscale = rs.getString("codicefiscale");
                String regione = rs.getString("regione");
                String provincia = rs.getString("provincia");
                String comune = rs.getString("comune");

                AnagraficheEstese anagrafica = new AnagraficheEstese(id, idTrasportatore, idListino, idTipologiaPagamento, codice, tipo, ckCliente, ckFornitore, ckTrasportatore, ckAgente, ckPersonale,soggetto, subCategoria, cognome, nome, ragioneSociale, codSDI, sesso, prodottiEServizi, codIva, iva, descrizioneIva, fido, sconto1, sconto2, sconto3, notes, noteFissedocumento, noteRapide, lingua, fasciaAppartenenza, certificazioneAlimentare, assicurazione, dataAssicurazione, ggChiusura, hhChiusura, status, dataNonInUso, cciiaa, enasarco, newsLetter, testoinvioMail, revCharge, splitPayment, ceeExtraCee, colore, tipoDocumentoIdentita, numeroDocumentoIdentita, emessoDaDocumentoIdentita, dataEmissioneDocumentoIdentita, provinciaNascita, comuneNascita, codiceFidelity, dataIns, dataUpd, eliminato, partitaIva
                , codiceFiscale, comune, regione, provincia);
                anagraficheList.add(anagrafica);
            }
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        return anagraficheList;
    }


    public List<String> autocompleteSearch(String field, String value) {

        List<AnagraficheEstese> anagraficheEsteseList = new ArrayList<>();

        String sql = "SELECT DISTINCT a.id, a.codice, a.cognome, a.nome, a.ragioneSociale, a.sesso, i.pIva, i.codiceFiscale, i.regione, i.provincia, i.comune, i.provaData " +
                "FROM anagrafiche a " +
                "LEFT JOIN indirizzi i ON a.id = i.idAnagrafica";

        Connection conn = DataSourceUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String codice = rs.getString("codice");
                String cognome = rs.getString("cognome");
                String nome = rs.getString("nome");
                String ragioneSociale = rs.getString("ragionesociale");
                String sesso = rs.getString("sesso");
                String partitaIva = rs.getString("pIva");
                String codiceFiscale = rs.getString("codicefiscale");
                String regione = rs.getString("regione");
                String provincia = rs.getString("provincia");
                String comune = rs.getString("comune");
                Date provaData = rs.getDate("provadata");


                AnagraficheEstese anagrafica = new AnagraficheEstese(id, codice, cognome, nome, ragioneSociale, sesso, partitaIva, codiceFiscale, regione, provincia, comune, provaData);

                anagraficheEsteseList.add(anagrafica);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException ignore) {}
            if (ps != null) try { ps.close(); } catch (SQLException ignore) {}
            DataSourceUtils.releaseConnection(conn, dataSource);
        }

        System.out.println("RISULTATO ==> " + field + " " + value + " " + anagraficheEsteseList.stream()
                .map(a -> getFieldValue(a, field))
                .filter(val -> val != null && val.startsWith(value))
                .collect(Collectors.toList()));


        return anagraficheEsteseList.stream()
                .map(a -> getFieldValue(a, field))
                .filter(val -> val != null && val.toLowerCase().startsWith(value.toLowerCase()))
                .distinct()
                .collect(Collectors.toList());

    }

    private String getFieldValue(AnagraficheEstese anagrafica, String field) {
        switch (field.toLowerCase()) {
            case "codice": return anagrafica.getCodice();
            case "cognome": return anagrafica.getCognome();
            case "nome": return anagrafica.getNome();
            case "ragionesociale": return anagrafica.getRagioneSociale();
            case "sesso": return anagrafica.getSesso();
            case "piva": return anagrafica.getPIva();
            case "codicefiscale": return anagrafica.getCodiceFiscale();
            case "regione": return anagrafica.getRegione();
            case "provincia": return anagrafica.getProvincia();
            case "comune": return anagrafica.getComune();
            default: return null;
        }
    }

}
