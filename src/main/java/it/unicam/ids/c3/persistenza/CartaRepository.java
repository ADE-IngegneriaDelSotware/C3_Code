package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.negozio.Carta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartaRepository extends JpaRepository<Carta, Long> {
}
