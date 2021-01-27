package it.unicam.ids.c3.persistenza;


import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerceInventarioNegozioRepository extends JpaRepository<MerceInventarioNegozio, Long> {
}
