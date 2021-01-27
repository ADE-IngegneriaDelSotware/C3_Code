package it.unicam.ids.c3.dbmanager;


import it.unicam.ids.c3.merce.MerceAlPubblico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerceAlPubblicoRepository extends JpaRepository<MerceAlPubblico, Long> {
}
