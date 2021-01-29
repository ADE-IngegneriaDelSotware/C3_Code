package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.vendita.MerceVendita;
import it.unicam.ids.c3.vendita.Vendita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VenditaRepository extends JpaRepository<Vendita, Long> {

    Vendita findTopByOrderByIdDesc();
//        Vendita findByPrezzo(double prezzo);
}
