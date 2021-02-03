package it.unicam.ids.c3.persistenza;


import it.unicam.ids.c3.merce.Merce;
import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MerceInventarioNegozioRepository extends JpaRepository<MerceInventarioNegozio, Long> {

    Optional<MerceInventarioNegozio> findMerceInventarioNegozioByMerceAlPubblico(MerceAlPubblico merce);

}
