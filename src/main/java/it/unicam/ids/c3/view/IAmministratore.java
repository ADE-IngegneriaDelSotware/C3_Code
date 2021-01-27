package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreAmministratore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IAmministratore {

    @Autowired
    private GestoreAmministratore gestoreAmministratore;

    @FXML
    private TextField cfRegistrazioneCorriere;

    @FXML
    private Button ricercaRegistrazioneCorriereButton;

    @FXML
    private ListView<?> listClientiRegistrazioneCorriere;

    @FXML
    private Button confermaRegistraCorriereButton;

    @FXML
    private TextField nomeDittaRegistrazioneCorriere;

    @FXML
    private TextField pivaRegistrazioneCorriere;

    @FXML
    private TextField indirizzoRegistrazioneCorriere;

    @FXML
    private TextField nomeNegozio;

    @FXML
    private TextField partitaIVA;

    @FXML
    private TextField indirizzoNegozio;

    @FXML
    private TextField cfClienteRicerca;

    @FXML
    private ListView<?> settoriList;

    @FXML
    private Button ricercaCFCLienteButton;

    @FXML
    private ListView<?> clientiList;

    @FXML
    private Button confermaRegistrazioneNegozioButton;

    @FXML
    void confermaRegistraCorriereEvent(ActionEvent event) {

    }

    @FXML
    void confermaRegistrazioneNegozioButtonEvent(ActionEvent event) {

    }

    @FXML
    void ricercaCFCLienteButtonEvent(ActionEvent event) {

    }

    @FXML
    void ricercaRegistrazioneCorriereEvent(ActionEvent event) {

    }

    public void setGestoreAmministraotre(GestoreAmministratore gestoreAmministratore) {
        this.gestoreAmministratore = gestoreAmministratore;
    }
}
