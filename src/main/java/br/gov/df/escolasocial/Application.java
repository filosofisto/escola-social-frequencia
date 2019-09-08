package br.gov.df.escolasocial;

import br.gov.df.escolasocial.exception.InvalidFrequencyData;
import br.gov.df.escolasocial.service.AssiduidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final AssiduidadeService assiduidadeService;

    @Autowired
    public Application(AssiduidadeService assiduidadeService) {
        this.assiduidadeService = assiduidadeService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws IOException, InvalidFrequencyData {
        if (args.length == 1) {
            assiduidadeService.importSheet(args[0]);
        } else {
            usage();
            System.exit(-1);
        }
    }

    private void usage() {
        System.out.println("Modo de utilizacao:");

        System.out.println("\tjava -jar <jarname> <planilha>");
    }
}
