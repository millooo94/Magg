package it.powerservice.managermag.utilities;

import org.zkoss.zk.ui.util.Clients;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    public static String getPort() throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertiesReader.class.getResourceAsStream("/config.properties")) {
            if (inputStream == null) {
                throw new FileNotFoundException("File 'config.properties' non trovato nel classpath.");
            }
            properties.load(inputStream);
        }

        return properties.getProperty("server.port");
    }

    public static void setURI() throws IOException {
        Properties properties = new Properties();

        try (InputStream inputStream = PropertiesReader.class.getResourceAsStream("/config.properties")) {
            if (inputStream == null) {
                throw new FileNotFoundException("File 'config.properties' non trovato nel classpath.");
            }
            properties.load(inputStream);
        }

        String protocol = properties.getProperty("server.protocol");
        String host = properties.getProperty("server.host");
        String port = properties.getProperty("server.port");

        var uri = protocol + "://" + host + ":" + port;

        Clients.evalJavaScript("localStorage.setItem('uri', '" + uri + "')");
    }
}
