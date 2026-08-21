package employee_management.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BrowserLauncher implements CommandLineRunner {

    @Override
    public void run(String... args) {

        try {

            Runtime.getRuntime().exec(
                    "rundll32 url.dll,FileProtocolHandler http://localhost:8080");

        } catch (Exception e) {

            e.printStackTrace();

        }

    }
}