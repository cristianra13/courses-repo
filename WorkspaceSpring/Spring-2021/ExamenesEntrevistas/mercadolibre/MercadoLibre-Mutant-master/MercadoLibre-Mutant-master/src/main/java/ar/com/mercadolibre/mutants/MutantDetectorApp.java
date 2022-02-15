package ar.com.mercadolibre.mutants;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * App Launcher
 *
 * @author vfuentes
 */
@SpringBootApplication
public class MutantDetectorApp extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(MutantDetectorApp.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(MutantDetectorApp.class);
    }

}
