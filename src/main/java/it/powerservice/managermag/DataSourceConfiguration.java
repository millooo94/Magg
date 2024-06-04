package it.powerservice.managermag;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import javax.sql.DataSource;


@Configuration
public class DataSourceConfiguration {
    @ConfigurationProperties(prefix = "spring.datasource")
    @Primary
    DataSource dataSource() {
        return DataSourceBuilder.create().build();

        //todo 1 ....  provi a mettere la stringa di connessione sullo spring.datasource dell'appl.prop e lanci il prj e vedi se va

        //todo 2 per camillo.... crei la domain che punta (per adesso) alla tabella clienti

        //todo 3 come nello smartpnd fai un repository sulla tabella clienti

        //todo 4 come nello smartpnd fai un service che richiama il repository per lanciare la query che torna la lista di clienti

        //todo 5 fai controller (vedi smartpnd) che chiama il service del punto 4 e stampa la il risultato della lista
    }
}
