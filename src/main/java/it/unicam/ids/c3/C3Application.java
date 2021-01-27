package it.unicam.ids.c3;

import it.unicam.ids.c3.dbmanager.*;
import it.unicam.ids.c3.javafx.JavaFxApplication;
import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Carta;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.personale.*;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.Vendita;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class C3Application{

	public static void main(final String[] args) {
		Application.launch(JavaFxApplication.class,args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ClienteRepository clienteRepository, RuoloRepository ruoloRepository,
										AmministratoreRepository amministratoreRepository,
										AddettoNegozioRepository addettoNegozioRepository,
										CommercianteRepository commercianteRepository,
										MerceInventarioNegozioRepository merceInventarioNegozioRepository,
										MerceRepository merceRepository,
										CartaRepository cartaRepository,
										MerceVenditaRepository merceVenditaRepository,
										VenditaRepository venditaRepository
										){
		return args -> {
			Cliente cliente1 = new Cliente("Andrea", "Marsili", "ANDMRS", "andreamarsili@gmail.com", "magliano");
			Cliente cliente2 = new Cliente("Davide", "Zeppilli", "DVDZEP", "davidezeppilli@gmail.com", "yag");
			Cliente cliente3 = new Cliente("Stefano", "Tosetto", "STFTST", "stefanotosetto@gmail.com","geova");
			Cliente cliente4 = new Cliente("Chiara","Antifora", "CHRANT", "chiaraantifora@gmai.com","vecchia");
			Cliente cliente5 = new Cliente("Elvira", "Rameti","ELVRMT", "elvirona@gmail.com","ramen");
			Cliente cliente6 = new Cliente("Rebecca", "Montagna", "RBCMNT", "rebeccamontagna@gmail.com","pifferaia");
			Cliente cliente7 = new Cliente("Beatrice", "giovale", "BTCGVL", "beatricegiovale@gmail.com", "maschiaccio");
			Cliente cliente8 = new Cliente("Kristopher", "Porfiri", "KRSPRF", "kristoporfi@gmail.com", "disamuel");
			Cliente cliente9 = new Cliente("Michele", "Mercuri", "MCRMRC", "michelemercuri@gmail.com", "psyco");
			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3,cliente4,cliente5,cliente6,cliente7,cliente8,cliente9));

			Amministratore admin = new Amministratore(RuoloSistema.AMMINISTRATORE);
			cliente1.setRuolo(admin);

			AddettoNegozio addettoNegozio = new AddettoNegozio(RuoloSistema.ADDETTONEGOZIO);
			cliente2.setRuolo(addettoNegozio);

			Commerciante commerciante = new Commerciante(RuoloSistema.COMMERCIANTE);
			cliente3.setRuolo(commerciante);

			Corriere corriere = new Corriere(RuoloSistema.CORRIERE, "Tom corriere", "Via Francigena", "324213212");
			cliente4.setRuolo(corriere);

			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3,cliente4,cliente5,cliente6,cliente7,cliente8,cliente9));

			Merce merce = new Merce("jeans", Categoria.ABBIGLIAMENTO, "jeans slavati");
			Merce merce1 = new Merce("felpa", Categoria.ABBIGLIAMENTO, "felpa aperta");
			Merce merce2 = new Merce("iphone 12", Categoria.TECNOLOGIA, "256 GB , 8 GB di RAM");
			Merce merce3 = new Merce("pane casereccio", Categoria.ALIMENTI, "pane con farina 00");
			Merce merce4 = new Merce("racchetta di tennis", Categoria.SPORT, "racchetta professionale");
			merceRepository.saveAll(List.of(merce,merce1,merce2,merce3,merce4));
			MerceAlPubblico merceAlPubblico = new MerceAlPubblico(23, merce);
			MerceAlPubblico merceAlPubblico1 = new MerceAlPubblico(3, merce1);
			MerceInventarioNegozio merceInventarioNegozio = new MerceInventarioNegozio(3.3, merceAlPubblico);
			MerceInventarioNegozio merceInventarioNegozio1 = new MerceInventarioNegozio(3, merceAlPubblico1);
			merceInventarioNegozioRepository.saveAll(List.of(merceInventarioNegozio,merceInventarioNegozio1));

			Carta carta = new Carta(cliente7, TipoScontoCliente.LAVORATORE);
			cartaRepository.save(carta);

			MerceVendita merceVendita = new MerceVendita(12.3, 2, merceAlPubblico);
			MerceVendita merceVendita1 = new MerceVendita(3,3 , merceAlPubblico1);
			List<MerceVendita> listaMerciVendita = new ArrayList<>();
			listaMerciVendita.add(merceVendita);
			listaMerciVendita.add(merceVendita1);
			Vendita vendita = new Vendita(32.5, listaMerciVendita);
			venditaRepository.save(vendita);

		};
	}
}
