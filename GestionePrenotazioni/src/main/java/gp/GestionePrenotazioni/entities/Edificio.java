package gp.GestionePrenotazioni.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Edificio {
    @Id
    private String nome;
    private String indirizzo;
    private String citta;

}
