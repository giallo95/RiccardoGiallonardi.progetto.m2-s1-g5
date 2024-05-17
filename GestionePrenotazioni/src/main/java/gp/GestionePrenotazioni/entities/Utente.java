package gp.GestionePrenotazioni.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Utente {
    @Id
    private String username;

    private String nomeCompleto;
    private String email;

}

