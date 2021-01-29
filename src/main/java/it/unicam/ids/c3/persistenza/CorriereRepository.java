package it.unicam.ids.c3.persistenza;

import it.unicam.ids.c3.personale.Corriere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CorriereRepository extends JpaRepository<Corriere, Long> {

    Optional<Corriere> findById(long id);

}
