package br.gov.df.escolasocial;

import br.gov.df.escolasocial.service.AssiduidadeService;
import br.gov.df.escolasocial.service.FolhaPagamentoService;
import br.gov.df.escolasocial.service.ProducaoService;
import br.gov.df.escolasocial.service.QuadroDespesaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Application implements CommandLineRunner {

    private final AssiduidadeService assiduidadeService;

    private final QuadroDespesaService quadroDespesaService;

    private final ProducaoService producaoService;

    private final FolhaPagamentoService folhaPagamentoService;

    @Autowired
    public Application(AssiduidadeService assiduidadeService,
                       QuadroDespesaService quadroDespesaService,
                       ProducaoService producaoService,
                       FolhaPagamentoService folhaPagamentoService) {
        this.assiduidadeService = assiduidadeService;
        this.quadroDespesaService = quadroDespesaService;
        this.producaoService = producaoService;
        this.folhaPagamentoService = folhaPagamentoService;
    }

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

    @Override
    public void run(String... args) throws IOException {
        if (args.length == 1 && "--absenteismo".equals(args[0])) {
            assiduidadeService.sync();
        } else if (args.length == 2 && "--despesa".equals(args[0])) {
            quadroDespesaService.load(args[1]);
        } else if (args.length == 1 && "--producao".equals(args[0])) {
            producaoService.sync();
        } else if (args.length == 1 && "--folha".equals(args[0])) {
            folhaPagamentoService.sync();
        } else {
            usage();
            System.exit(-1);
        }
    }

    private void usage() {
        System.out.println("Modo de utilizacao:");

        System.out.println("\tjava -jar <jarname> --absenteismo");
        System.out.println("\t\tSincronizacao absenteismo");

        System.out.println("\tjava -jar <jarname> --despesa <planilha>.xlsx");
        System.out.println("\t\tSincronizacao despesa");

        System.out.println("\tjava -jar <jarname> --producao");
        System.out.println("\t\tSincronizacao producao");

        System.out.println("\tjava -jar <jarname> --folha");
        System.out.println("\t\tSincronizacao folha de pagamento");
    }
}
