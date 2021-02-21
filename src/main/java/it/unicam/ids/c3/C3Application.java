package it.unicam.ids.c3;

import it.unicam.ids.c3.javafx.JavaFxApplication;
import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.persistenza.ClienteRepository;
import it.unicam.ids.c3.persistenza.NegozioRepository;
import it.unicam.ids.c3.persistenza.RuoloRepository;
import it.unicam.ids.c3.personale.Amministratore;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.Commerciante;
import it.unicam.ids.c3.personale.RuoloSistema;
import javafx.application.Application;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class C3Application{

	public static void main(final String[] args) {
		Application.launch(JavaFxApplication.class,args);
	}


	@Bean
	CommandLineRunner commandLineRunner(ClienteRepository clienteRepository,
										RuoloRepository ruoloRepository,
										NegozioRepository negozioRepository){
		return args -> {

			/*********Parte del personale********************/

			Cliente cliente1 = new Cliente("Andrea", "Rossi", "andrearossi@gmail.com", "rossi");
			Cliente cliente2 = new Cliente("Davide", "Bianchi", "davidebianchi@gmail.com", "bianchi");
			Cliente cliente3 = new Cliente("Alberto", "Neri", "albertoneri@gmail.com", "neri");
			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3));

			Amministratore admin = new Amministratore(RuoloSistema.AMMINISTRATORE);
			cliente1.setRuolo(admin);
			Commerciante commerciante = new Commerciante(RuoloSistema.COMMERCIANTE);
			cliente2.setRuolo(commerciante);
			Commerciante commerciante1 = new Commerciante(RuoloSistema.COMMERCIANTE);
			cliente3.setRuolo(commerciante1);
			ruoloRepository.saveAll(List.of(admin, commerciante,commerciante1));
			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3));

			Negozio negozio = new Negozio("MadStore","Via Palmiro Togliatti", "2141234314", List.of(Categoria.ABBIGLIAMENTO));
			negozio.addAddettoNegozio(commerciante);
			negozioRepository.save(negozio);

			Negozio negozio1 = new Negozio("Jeans & Co", "Via Campiglione", "3525235", List.of(Categoria.ABBIGLIAMENTO));
			negozio1.addAddettoNegozio(commerciante1);
			negozioRepository.save(negozio1);

		};
//			Merce merce = new Merce("jeans", Categoria.ABBIGLIAMENTO, "jeans slavati");
//			Merce merce1 = new Merce("felpa", Categoria.ABBIGLIAMENTO, "felpa aperta");
//			Merce merce2 = new Merce("iphone 12", Categoria.TECNOLOGIA, "256 GB , 8 GB di RAM");
//			Merce merce3 = new Merce("pane casereccio", Categoria.ALIMENTI, "pane con farina 00");
//			Merce merce4 = new Merce("racchetta di tennis", Categoria.SPORT, "racchetta professionale");
//			merceRepository.saveAll(List.of(merce, merce1, merce2, merce3, merce4));
//			MerceAlPubblico merceAlPubblico = new MerceAlPubblico(23, merce, 2);
//			MerceAlPubblico merceAlPubblico1 = new MerceAlPubblico(3, merce1);
//			MerceAlPubblico merceAlPubblico2 = new MerceAlPubblico(10, merce2);
//			merceAlPubblicoRepository.saveAll(List.of(merceAlPubblico, merceAlPubblico1, merceAlPubblico2));
//			MerceInventarioNegozio merceInventarioNegozio = new MerceInventarioNegozio(3.3, merceAlPubblico);
//			MerceInventarioNegozio merceInventarioNegozio1 = new MerceInventarioNegozio(3, merceAlPubblico1);
//			MerceInventarioNegozio merceInventarioNegozio2 = new MerceInventarioNegozio(6, merceAlPubblico2);
//			merceInventarioNegozioRepository.saveAll(List.of(merceInventarioNegozio, merceInventarioNegozio1, merceInventarioNegozio2));
//
//			List<Categoria> categoryList = new ArrayList<>();
//			categoryList.addAll(List.of(Categoria.TECNOLOGIA, Categoria.GIOCHI));
//			Negozio negozio = new Negozio("MadStore","Via Palmiro Togliatti", "2141234314", categoryList);
//			Negozio negozio1 = new Negozio("SamsungStore","Via Enrico Mattei", "4643524", categoryList);
//			negozio.addAddettoNegozio(addettoNegozio);
//			negozio.addMerceInventarioNegozio(merceInventarioNegozio);
//			negozio1.addAddettoNegozio(commerciante);
//			negozio1.addMerceInventarioNegozio(merceInventarioNegozio1);
//			negozio1.addMerceInventarioNegozio(merceInventarioNegozio2);
//			negozioRepository.saveAll(List.of(negozio,negozio1));
//			clienteRepository.saveAll(List.of(cliente6,cliente7));
//			negozio.addCorriere(corriere);
//			negozio.addCorriere(corriere1);
//			negozio1.addCorriere(corriere);
//			negozio1.addCorriere(corriere2);
//			negozioRepository.save(negozio);
//			negozioRepository.save(negozio1);

	}
}
