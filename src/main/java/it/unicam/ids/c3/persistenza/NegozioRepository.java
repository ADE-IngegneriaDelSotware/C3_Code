package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.negozio.Negozio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NegozioRepository extends JpaRepository<Negozio, Long> {
}
