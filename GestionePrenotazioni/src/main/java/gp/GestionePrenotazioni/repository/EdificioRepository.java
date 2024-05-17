package gp.GestionePrenotazioni.repository;

import gp.GestionePrenotazioni.entities.Edificio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdificioRepository extends JpaRepository<Edificio, String> {
}
