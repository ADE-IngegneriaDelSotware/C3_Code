package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreCommerciante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ICommerciante {

    @Autowired
    private GestoreCommerciante gestoreCommerciante;

    @FXML
    private Button checkoutCompletedButton;

    @FXML
    private Button annullaCheckoutButton;

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
    private Button confermaAssunzioneAddettoButton;

    @FXML
    private TextField codiceFiscaleAA;

    @FXML
    private ListView<?> clientiFiltratiAA;

    @FXML
    private Button cercaClienteAAButton;

    @FXML
    private ListView<?> listaPromozioni;

    @FXML
    private Button aggiungiPromozioneButton;

    @FXML
    private Button rimuoviPromozioneButton;

    @FXML
    private ListView<?> listaPromozioniPossibili;

    @FXML
    private DatePicker dataI;

    @FXML
    private DatePicker dataF;

    @FXML
    private TextField percentualePromozione;

    @FXML
    private Button aggiuntaPromozione;

    @FXML
    private ListView<?> corrieriDaAggiungere;

    @FXML
    private Button confermAggiuntaCorriereButton;

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
    private ListView<?> merciInventario;

    @FXML
    private Button aggiungiButton;

    @FXML
    private Button rimuoviMerceButton;

    @FXML
    private Label idLabelGI;

    @FXML
    private Label nomeLabelGI;

    @FXML
    private Label descrizioneLabelGI;

    @FXML
    private Label categoriaLabelGI;

    @FXML
    private Label quantitaLabelGI;

    @FXML
    private Label prezzoLabelGI;

    @FXML
    private Label scontoLabelGI;

    @FXML
    private Button confermaAggiuntaMerceButton;

    @FXML
    private TextArea descrizioneMerceGI;

    @FXML
    private TextField idMerceGI;

    @FXML
    private TextField nomeMerceGI;

    @FXML
    private TextField prezzoMerceGI;

    @FXML
    private TextField scontoMerceGI;

    @FXML
    private ChoiceBox<?> categoriaMerceGI;

    @FXML
    private TextField quantitaMerceGI;

    @FXML
    private Button confermaRimozioneMerceButton;

    @FXML
    private ListView<?> merciDaOrdinare;

    @FXML
    private TextField cercaMerceField;

    @FXML
    private Button cercaMerceButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Button addMerceDaOrdinareButton;

    @FXML
    private Button removeMerceDaOrdinareButtton;

    @FXML
    private ListView<?> merceSearched;

    @FXML
    private TextField quantitaOrdine;

    @FXML
    void addMerceDaOrdinareButtonEvent(ActionEvent event) {

    }

    @FXML
    void addPromozioneButtonEvent(ActionEvent event) {

    }

    @FXML
    void aggiungiButtonEvent(ActionEvent event) {

    }

    @FXML
    void aggiungiPromozioneButtonEvent(ActionEvent event) {

    }

    @FXML
    void annullaCheckoutButtonEvent(ActionEvent event) {

    }

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
    void cercaClienteAAButtonEvent(ActionEvent event) {

    }

    @FXML
    void cercaClienteACButtonEvent(ActionEvent event) {

    }

    @FXML
    void cercaMerceButtonEvent(ActionEvent event) {

    }

    @FXML
    void checkoutCompletedButtonEvent(ActionEvent event) {

    }

    @FXML
    void confermAggiuntaCorriereButtonEvent(ActionEvent event) {

    }

    @FXML
    void confermaAggiuntaMerceButtonEvent(ActionEvent event) {

    }

    @FXML
    void confermaAssunzioneAddettoButtonEvent(ActionEvent event) {

    }

    @FXML
    void confermaButtonEvent(ActionEvent event) {

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
    void confermaRimozioneMerceButtonEvent(ActionEvent event) {

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
    void removeMerceDaOrdinareButttonEvent(ActionEvent event) {

    }

    @FXML
    void rimuoviMerceButtonEvent(ActionEvent event) {

    }

    @FXML
    void rimuoviPromozioneButtonEvent(ActionEvent event) {

    }

    @FXML
    void trovaPrezzoEScontoButtonEvent(ActionEvent event) {

    }

    @FXML
    void verificaCodiceCartaButton(ActionEvent event) {

    }

    public void setGestoreCommerciante(GestoreCommerciante gestoreCommerciante) {
        this.gestoreCommerciante = gestoreCommerciante;
    }
}
