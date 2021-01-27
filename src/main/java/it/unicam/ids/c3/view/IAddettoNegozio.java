package it.unicam.ids.c3.view;


import it.unicam.ids.c3.gestori.GestoreAddetto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class IAddettoNegozio {

    @Autowired
    private GestoreAddetto gestoreAddetto;

    @FXML
    private TextField idMerce;

    @FXML
    private TextField quantitaMerce;

    @FXML
    private TextField scontoMerce;

    @FXML
    private Button inserisciButton;

    @FXML
    private TextField prezzoMerce;

    @FXML
    private Button trovaPrezzoEScontoButton;

    @FXML
    private TextField prezzoCarrello;

    @FXML
    private TextField prezzoTotale;

    @FXML
    private CheckBox siCarta;

    @FXML
    private CheckBox noCarta;

    @FXML
    private Button possessoCartaButton;

    @FXML
    private CheckBox siCartaDisponibile;

    @FXML
    private CheckBox noCartaDisponibile;

    @FXML
    private Label cdLabel;

    @FXML
    private Button cartaDisponibileButton;

    @FXML
    private Button confermaCF;

    @FXML
    private TextField codiceCarta;

    @FXML
    private TextField codiceFiscale;

    @FXML
    private Button verificaCodice;

    @FXML
    private Button iscrizioneClienteCheckout;

    @FXML
    private Label codiceFiscaleLabel;

    @FXML
    private Label codiceCartaLabel;

    @FXML
    private Button applyScontoCartaButton;

    @FXML
    private Label answerRegistraVenditaLabel;

    @FXML
    private CheckBox siRegistraVendita;

    @FXML
    private CheckBox noRegistraVendita;

    @FXML
    private Button registraVenditaButton;

    @FXML
    private Label denaroRicevutoLabel;

    @FXML
    private TextField denaroRicevuto;

    @FXML
    private Label restoLabel;

    @FXML
    private TextField resto;

    @FXML
    private Button calcolaRestoButton;

    @FXML
    private Button noScontoCartaButton;

    @FXML
    private TextField codiceFiscaleAC;

    @FXML
    private Button cercaClienteACButton;

    @FXML
    private ListView<?> clientiFiltratiAC;

    @FXML
    private ChoiceBox<?> tscAC;

    @FXML
    private Button assegnaCartaButton;

    @FXML
    private TextField codiceCartaAC;

    @FXML
    private ListView<?> listViewConsultaInventario;

    @FXML
    private Button confermaConsultaInventarioButton;

    @FXML
    private TextArea infoMerceConsultaInventario;

    @FXML
    private TextField codiceFiscaleConsegnaOrdine;

    @FXML
    private Button confermaClienteConsegnaOrdine;

    @FXML
    private ListView<?> listaVenditeDaConsegnare;

    @FXML
    private Button confermaConsegnaOrdine;

    @FXML
    void applyScontoCartaButtonEvent(ActionEvent event) {

    }

    @FXML
    void assegnaCartaButtonEvent(ActionEvent event) {

    }

    @FXML
    void calcolaRestoButtonEvent(ActionEvent event) {

    }

    @FXML
    void cartaDisponibileButton(ActionEvent event) {

    }

    @FXML
    void cercaClienteACButtonEvent(ActionEvent event) {

    }

    @FXML
    void confermaCFButton(ActionEvent event) {

    }

    @FXML
    void confermaClienteConsegnaOrdineButton(ActionEvent event) {

    }

    @FXML
    void confermaConsegnaOrdineButton(ActionEvent event) {

    }

    @FXML
    void confermaConsultaInventarioEvent(ActionEvent event) {

    }

    @FXML
    void inserisciButtonEvent(ActionEvent event) {

    }

    @FXML
    void iscrizioneClienteCheckoutButtonEvent(ActionEvent event) {

    }

    @FXML
    void noScontoCartaButtonEvent(ActionEvent event) {

    }

    @FXML
    void possessoCartaButtonEvent(ActionEvent event) {

    }

    @FXML
    void registraVenditaButtonEvent(ActionEvent event) {

    }

    @FXML
    void trovaPrezzoEScontoButtonEvent(ActionEvent event) {

    }

    @FXML
    void verificaCodiceCartaButton(ActionEvent event) {

    }

    public void setGestoreAddetto(GestoreAddetto gestoreAddetto) {
        this.gestoreAddetto = gestoreAddetto;
    }
}
