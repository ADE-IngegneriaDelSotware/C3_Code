package it.unicam.ids.c3;

import it.unicam.ids.c3.javafx.JavaFxApplication;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.ClienteRepository;
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
	CommandLineRunner commandLineRunner(ClienteRepository clienteRepository){
		return args -> {
			Cliente cliente1 = new Cliente("Andrea", "Marsili", "ANDMRS", "andreamarsili@gmail.com", "brunomars", "magliano");
			Cliente cliente2 = new Cliente("Davide", "Zeppilli", "DVDZEP", "davidezeppilli@gmail.com", "davidezep", "yag");
			Cliente cliente3 = new Cliente("Stefano", "Tosetto", "STFTST", "stefanotosetto@gmail.com", "stefi","geova");
			Cliente cliente4 = new Cliente("Chiara","Antifora", "CHRANT", "chiaraantifora@gmai.com","sposata","vecchia");
			Cliente cliente5 = new Cliente("Elvira", "Rameti","ELVRMT", "elvirona@gmail.com","elvria","ramen");
			Cliente cliente6 = new Cliente("Rebecca", "Montagna", "RBCMNT", "rebeccamontagna@gmail.com", "reby","pifferaia");
			Cliente cliente7 = new Cliente("Beatrice", "giovale", "BTCGVL", "beatricegiovale@gmail.com", "grezza", "maschiaccio");
			Cliente cliente8 = new Cliente("Kristopher", "Porfiri", "KRSPRF", "kristoporfi@gmail.com", "cugino", "disamuel");
			Cliente cliente9 = new Cliente("Michele", "Mercuri", "MCRMRC", "michelemercuri@gmail.com", "strunz", "psyco");
			clienteRepository.saveAll(List.of(cliente1,cliente2,cliente3,cliente4,cliente5,cliente6,cliente7,cliente8,cliente9));
		};
	}
}
