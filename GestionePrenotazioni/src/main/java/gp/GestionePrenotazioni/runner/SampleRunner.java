package gp.GestionePrenotazioni.runner;

import gp.GestionePrenotazioni.entities.*;
import gp.GestionePrenotazioni.repository.PrenotazioniRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import gp.GestionePrenotazioni.repository.UtenteRepository;
import gp.GestionePrenotazioni.repository.EdificioRepository;
import gp.GestionePrenotazioni.repository.PostazioneRepository;

import java.time.LocalDate;

@Component
@Slf4j
public class SampleRunner implements CommandLineRunner {


    @Autowired
    private UtenteRepository utenteRepository;

    @Autowired
    private EdificioRepository edificioRepository;

    @Autowired
    private PostazioneRepository postazioneRepository;

    @Autowired
    private PrenotazioniRepository prenotazioniRepository;

    @Override
    public void run(String... args) throws Exception {
        log.info("Inserimento dati di esempio nel database...");
        Utente utente1 = new Utente();
        utente1.setUsername("utente1");
        utente1.setNomeCompleto("Mario Rossi");
        utente1.setEmail("mario.rossi@example.com");

        Utente utente2 = new Utente();
        utente2.setUsername("utente2");
        utente2.setNomeCompleto("Luca Bianchi");
        utente2.setEmail("luca.bianchi@example.com");

        Utente utente3 = new Utente();
        utente3.setUsername("utente3");
        utente3.setNomeCompleto("Giulia Verdi");
        utente3.setEmail("giulia.verdi@example.com");

        utenteRepository.save(utente1);
        utenteRepository.save(utente2);
        utenteRepository.save(utente3);


        Edificio edificio1 = new Edificio();
        edificio1.setNome("Edificio A");
        edificio1.setIndirizzo("Via Roma 1");
        edificio1.setCitta("Roma");

        Edificio edificio2 = new Edificio();
        edificio2.setNome("Edificio B");
        edificio2.setIndirizzo("Via Milano 2");
        edificio2.setCitta("Milano");

        Edificio edificio3 = new Edificio();
        edificio3.setNome("Edificio C");
        edificio3.setIndirizzo("Via Napoli 3");
        edificio3.setCitta("Napoli");

        edificioRepository.save(edificio1);
        edificioRepository.save(edificio2);
        edificioRepository.save(edificio3);


        Postazione postazione1 = new Postazione();
        postazione1.setCodice("POST1");
        postazione1.setDescrizione("Postazione Open Space");
        postazione1.setTipo(TipoPostazione.OPENSPACE);
        postazione1.setNumeroMassimoOccupanti(4);
        postazione1.setEdificio(edificio1);

        Postazione postazione2 = new Postazione();
        postazione2.setCodice("POST2");
        postazione2.setDescrizione("Sala Riunioni Grande");
        postazione2.setTipo(TipoPostazione.SALA_RIUNIONI);
        postazione2.setNumeroMassimoOccupanti(10);
        postazione2.setEdificio(edificio2);

        Postazione postazione3 = new Postazione();
        postazione3.setCodice("POST3");
        postazione3.setDescrizione("Ufficio Privato");
        postazione3.setTipo(TipoPostazione.PRIVATO);
        postazione3.setNumeroMassimoOccupanti(1);
        postazione3.setEdificio(edificio3);

        postazioneRepository.save(postazione1);
        postazioneRepository.save(postazione2);
        postazioneRepository.save(postazione3);

        log.info("Dati di esempio inseriti con successo.");

        Postazione postazione = postazioneRepository.findById(1L).orElseThrow(() -> new RuntimeException("Postazione non trovata"));
        Utente utente = utenteRepository.findById("utente1").orElseThrow(() -> new RuntimeException("Utente non trovato"));


        LocalDate dataPrenotazione = LocalDate.now();
        if (postazioneDisponibile(postazione, dataPrenotazione)) {

            Prenotazione prenotazione = new Prenotazione();
            prenotazione.setPostazione(postazione);
            prenotazione.setUtente(utente);
            prenotazione.setData(dataPrenotazione);


            prenotazioniRepository.save(prenotazione);
            System.out.println("Prenotazione effettuata con successo!");
        } else {
            System.out.println("La postazione non è disponibile per la data specificata");
        }
    }


    private boolean postazioneDisponibile(Postazione postazione, LocalDate dataPrenotazione) {
        return prenotazioniRepository.findByPostazioneAndData(postazione, dataPrenotazione).isEmpty();
    }

}
