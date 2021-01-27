package it.unicam.ids.c3.dbmanager;

import it.unicam.ids.c3.merce.MerceAlPubblico;
import it.unicam.ids.c3.merce.Promozione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromozioneRepository extends JpaRepository<Promozione, Long> {
}
