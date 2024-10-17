package it.powerservice.managermag;

import it.powerservice.managermag.utilities.PropertiesReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

@SpringBootApplication
class Application extends SpringBootServletInitializer {

    public static void main(String[] args) throws IOException {
        if (isPortValid()) {
            System.out.println("La porta è 8084. Eseguendo i build...");
            if (executeBuildCommands()) {
                SpringApplication.run(Application.class, args);
            } else {
                System.err.println("Errore durante la build di Angular. L'applicazione Spring Boot non verrà avviata.");
            }
        } else {
            System.out.println(isPortValid());
            System.out.println("La porta non è 8084. Avvio dell'applicazione senza build...");
            SpringApplication.run(Application.class, args);
        }
    }

    private static boolean isPortValid() throws IOException {
        String port = PropertiesReader.getPort();
        System.out.println("PORTAAAAA => " + port);
        return port.equals("8084");
    }

    private static boolean executeBuildCommands() {
        boolean buildSuccess1 = executeBuildCommand("src/indirizzoSedePrincipale");
        boolean buildSuccess2 = executeBuildCommand("src/indirizzoSelezionato");

        // Verifica se entrambe le build hanno avuto successo
        return buildSuccess1 && buildSuccess2;
    }

    private static boolean executeBuildCommand(String angularProject) {
        String[] command = {"npx.cmd", "ng", "build"};
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        processBuilder.directory(new File(angularProject));

        processBuilder.redirectErrorStream(true);

        try {
            Process process = processBuilder.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Codice di uscita per " + angularProject + ": " + exitCode);
            System.out.println("Aggiunto qui di nuovo");

            return exitCode == 0;
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }

}
