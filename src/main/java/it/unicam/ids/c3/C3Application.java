package it.unicam.ids.c3;

import it.unicam.ids.c3.persistenza.CartaRepository;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.persistenza.*;
import it.unicam.ids.c3.javafx.JavaFxApplication;
import it.unicam.ids.c3.merce.*;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.personale.*;
import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.Vendita;
import it.unicam.ids.c3.vendita.VenditaSpedita;
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
	CommandLineRunner commandLineRunner(ClienteRepository clienteRepository,
										RuoloRepository ruoloRepository,
										AmministratoreRepository amministratoreRepository,
										AddettoNegozioRepository addettoNegozioRepository,
										CommercianteRepository commercianteRepository,
										MerceInventarioNegozioRepository merceInventarioNegozioRepository,
										MerceAlPubblicoRepository merceAlPubblicoRepository,
										MerceRepository merceRepository,
										PromozioneRepository promozioneRepository,
										CartaRepository cartaRepository,
										MerceVenditaRepository merceVenditaRepository,
										VenditaRepository venditaRepository,
										VenditaSpeditaRepository venditaSpeditaRepository,
										NegozioRepository negozioRepository
										){
		return args -> {

			/*********Parte del personale********************/

			Cliente cliente1 = new Cliente("Andrea", "Marsili", "andreamarsili@gmail.com", "magliano");
			Cliente cliente2 = new Cliente("Davide", "Zeppilli", "davidezeppilli@gmail.com", "yag");
			Cliente cliente3 = new Cliente("Stefano", "Tosetto",  "stefanotosetto@gmail.com","geova");
			Cliente cliente4 = new Cliente("Chiara","Antifora", "chiaraantifora@gmail.com","vecchia");
			Cliente cliente5 = new Cliente("Elvira", "Rameti", "elvirona@gmail.com","ramen");
			Cliente cliente6 = new Cliente("Rebecca", "Montagna", "rebeccamontagna@gmail.com","pifferaia");
			Cliente cliente7 = new Cliente("Beatrice", "giovale", "beatricegiovale@gmail.com", "maschiaccio");
			Cliente cliente8 = new Cliente("Kristopher", "Porfiri", "kristoporfi@gmail.com", "disamuel");
			Cliente cliente9 = new Cliente("Michele", "Mercuri", "michelemercuri@gmail.com", "psyco");
			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3,cliente4,cliente5,cliente6,cliente7,cliente8,cliente9));

			Amministratore admin = new Amministratore(RuoloSistema.AMMINISTRATORE);
			cliente1.setRuolo(admin);

			AddettoNegozio addettoNegozio = new AddettoNegozio(RuoloSistema.ADDETTONEGOZIO);
			cliente2.setRuolo(addettoNegozio);

			Commerciante commerciante = new Commerciante(RuoloSistema.COMMERCIANTE);
			cliente3.setRuolo(commerciante);

			Corriere corriere = new Corriere(RuoloSistema.CORRIERE, "Tom corriere", "Via Francigena", "324213212");
			cliente4.setRuolo(corriere);

			Corriere corriere1 = new Corriere(RuoloSistema.CORRIERE, "Fulmine Corriere", "Via del Sale", "543883");
			cliente5.setRuolo(corriere1);

			Corriere corriere2 = new Corriere(RuoloSistema.CORRIERE, "Impresa Fast", "Via della transumanza", "435253443");
			cliente6.setRuolo(corriere2);
			ruoloRepository.saveAll(List.of(corriere,commerciante,admin,addettoNegozio,corriere1, corriere2));
			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3,cliente4,cliente5,cliente6,cliente7,cliente8,cliente9));

			/*********************Parte Merce********************/
			Merce merce = new Merce("jeans", Categoria.ABBIGLIAMENTO, "jeans slavati");
			Merce merce1 = new Merce("felpa", Categoria.ABBIGLIAMENTO, "felpa aperta");
			Merce merce2 = new Merce("iphone 12", Categoria.TECNOLOGIA, "256 GB , 8 GB di RAM");
			Merce merce3 = new Merce("pane casereccio", Categoria.ALIMENTI, "pane con farina 00");
			Merce merce4 = new Merce("racchetta di tennis", Categoria.SPORT, "racchetta professionale");
			merceRepository.saveAll(List.of(merce, merce1, merce2, merce3, merce4));
			MerceAlPubblico merceAlPubblico = new MerceAlPubblico(23, merce, 2);
			MerceAlPubblico merceAlPubblico1 = new MerceAlPubblico(3, merce1);
			MerceAlPubblico merceAlPubblico2 = new MerceAlPubblico(10, merce2);


//			merceAlPubblico.setPromozione(LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(1), 10);
//			merceAlPubblico1.setPromozione(LocalDate.now().minusMonths(1), LocalDate.now().plusMonths(1), 10);


			merceAlPubblicoRepository.saveAll(List.of(merceAlPubblico, merceAlPubblico1, merceAlPubblico2));
			MerceInventarioNegozio merceInventarioNegozio = new MerceInventarioNegozio(3.3, merceAlPubblico);
			MerceInventarioNegozio merceInventarioNegozio1 = new MerceInventarioNegozio(3, merceAlPubblico1);
			MerceInventarioNegozio merceInventarioNegozio2 = new MerceInventarioNegozio(6, merceAlPubblico2);
			merceInventarioNegozioRepository.saveAll(List.of(merceInventarioNegozio, merceInventarioNegozio1, merceInventarioNegozio2));

			/***********************Parte Vendita**********************/
			MerceVendita merceVendita = new MerceVendita(12.3, 2, merceAlPubblico);
			MerceVendita merceVendita1 = new MerceVendita(3, 3, merceAlPubblico1);
			MerceVendita merceVendita2 = new MerceVendita(10, 1, merceAlPubblico);
			MerceVendita merceVendita3 = new MerceVendita(3, 1, merceAlPubblico1);
			merceVenditaRepository.saveAll(List.of(merceVendita, merceVendita1, merceVendita2, merceVendita3));
			List<MerceVendita> listaMerciVendita = new ArrayList<>();
			listaMerciVendita.add(merceVendita);
			listaMerciVendita.add(merceVendita1);
//			Vendita vendita = new Vendita(32.5, listaMerciVendita);
//			venditaRepository.save(vendita);
//			List<MerceVendita> lista = new ArrayList<>();
//			lista.addAll(List.of(merceVendita2, merceVendita3));
//			VenditaSpedita venditaSpedita = new VenditaSpedita(12, lista, "Viale della transumanza");
//			VenditaSpedita venditaSpedita1 = new VenditaSpedita(10, lista, "Viale del boschetto");
//			venditaSpeditaRepository.save(venditaSpedita);
//			venditaSpeditaRepository.save(venditaSpedita1);
//			corriere.addMerceDaSpedire(venditaSpedita);
//			corriere.addMerceDaSpedire(venditaSpedita1);
//			ruoloRepository.save(corriere);
//			venditaSpeditaRepository.save(venditaSpedita);
//			venditaSpeditaRepository.save(venditaSpedita1);


			/****************************Parte Negozio************************************/
//			Carta carta = new Carta(cliente7, TipoScontoCliente.LAVORATORE);
//			Carta carta1 = new Carta(cliente2, TipoScontoCliente.STUDENTE);
//			Carta carta2 = new Carta(cliente7, TipoScontoCliente.LAVORATORE);
//			Carta carta3 = new Carta(cliente8, TipoScontoCliente.STUDENTE);
//			cartaRepository.saveAll(List.of(carta,carta1,carta2,carta3));

			List<Categoria> categoryList = new ArrayList<>();
			categoryList.addAll(List.of(Categoria.TECNOLOGIA, Categoria.GIOCHI));
			Negozio negozio = new Negozio("MadStore","Via Palmiro Togliatti", "2141234314", categoryList);
			Negozio negozio1 = new Negozio("SamsungStore","Via Enrico Mattei", "4643524", categoryList);

			Vendita vendita = new Vendita(46.7, listaMerciVendita);
			venditaRepository.save(vendita);
			cliente7.addAcquisto(vendita);
			clienteRepository.save(cliente7);
			negozio.addVendita(vendita);

			negozio.addAddettoNegozio(addettoNegozio);
			negozio.addMerceInventarioNegozio(merceInventarioNegozio);
			negozio1.addAddettoNegozio(commerciante);
			negozio1.addMerceInventarioNegozio(merceInventarioNegozio1);
			negozio1.addMerceInventarioNegozio(merceInventarioNegozio2);
			negozioRepository.save(negozio1);

//			negozio.addCarta(carta);
//			negozio.addCarta(carta1);
//			negozio1.addCarta(carta2);
			negozioRepository.saveAll(List.of(negozio,negozio1));
//			negozio1.addCarta(carta3);
			negozioRepository.save(negozio1);

//			venditaSpedita.setStatoConsegna(StatoConsegna.CONSEGNATO_AL_NEGOZIO);
//			venditaSpedita1.setStatoConsegna(StatoConsegna.CONSEGNATO_AL_NEGOZIO);
//
//			negozio.addVendita(vendita);
//			negozio.addVendita(venditaSpedita);
//			negozioRepository.save(negozio);
//
//			negozio1.addVendita(venditaSpedita1);
			negozioRepository.save(negozio1);
//			corriere.addMerceDaSpedire(venditaSpedita);
//			venditaSpeditaRepository.save(venditaSpedita);
//			negozio.addVenditaInNegozioRitiro(venditaSpedita);
//			negozio1.addVenditaInNegozioRitiro(venditaSpedita1);
//			cliente7.addAcquisto(venditaSpedita);
//			cliente6.addAcquisto(venditaSpedita1);
			clienteRepository.saveAll(List.of(cliente6,cliente7));

			negozio.addCorriere(corriere);
			negozio.addCorriere(corriere1);

			negozio1.addCorriere(corriere);
			negozio1.addCorriere(corriere2);
			negozioRepository.save(negozio);
			negozioRepository.save(negozio1);


			VenditaSpedita vs = new VenditaSpedita(34, listaMerciVendita, "Via morelle 4");
			venditaSpeditaRepository.save(vs);
			corriere.addMerceDaSpedire(vs);
			ruoloRepository.save(corriere);
		};
	}
}
