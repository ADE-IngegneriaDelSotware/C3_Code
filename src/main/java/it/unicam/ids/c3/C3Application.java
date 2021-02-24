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
	}
}
