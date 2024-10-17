package it.powerservice.managermag.utilities;

import org.zkoss.zk.ui.util.Clients;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static String getPort() throws IOException {
        Properties properties = new Properties();

        FileInputStream inputStream = new FileInputStream("C:/Users/CamilloZK/Desktop/Magg/src/main/resources/config.properties");
        properties.load(inputStream);

        String port = properties.getProperty("server.port");

        inputStream.close();

        return port;
    }

    public static void setURI() throws IOException {
        Properties properties = new Properties();

        FileInputStream inputStream = new FileInputStream("C:/Users/CamilloZK/Desktop/Magg/src/main/resources/config.properties");
        properties.load(inputStream);

        String protocol = properties.getProperty("server.protocol");
        String host = properties.getProperty("server.host");
        String port = properties.getProperty("server.port");

        inputStream.close();

        var uri = protocol+"://"+host+":"+port;

        Clients.evalJavaScript("localStorage.setItem('uri', '" + uri + "')");

    }

}
