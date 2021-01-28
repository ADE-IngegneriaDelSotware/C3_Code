package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreCommerciante;
import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.persistenza.VenditaRepository;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ICommerciante {

    @Autowired
    private GestoreCommerciante gestoreCommerciante;

    @FXML
    private TabPane tabPaneCommerciante;

    @FXML
    private Tab tabCheckout;

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
    private Button checkoutCompletedButton;

    @FXML
    private Button annullaCheckoutButton;

    @FXML
    private Tab assegnaCartaTab;

    @FXML
    private TextField emailAC;

    @FXML
    private Button cercaClienteACButton;

    @FXML
    private ListView<Cliente> clientiFiltratiAC;

    @FXML
    private ChoiceBox<TipoScontoCliente> tscAC;

    @FXML
    private Button assegnaCartaButton;

    @FXML
    private TextField codiceCartaAC;

    @FXML
    private Button inviaCodiceAlCheckoutButton;

    @FXML
    private ListView<MerceInventarioNegozio> listViewConsultaInventario;

    @FXML
    private Button confermaConsultaInventarioButton;

    @FXML
    private TextArea infoMerceConsultaInventario;

    @FXML
    private TextField emailConsegnaOrdine;

    @FXML
    private Button ccoButton;

    @FXML
    private ListView<VenditaSpedita> listaVenditeDaConsegnare;

    @FXML
    private Button confermaConsegnaOrdine;

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


    public void initRichiestaCartField() {
        siCartaDisponibile.setVisible(false);
        noCartaDisponibile.setVisible(false);
        cdLabel.setVisible(false);
        cartaDisponibileButton.setVisible(false);
        codiceCarta.setVisible(false);
        codiceFiscale.setVisible(false);
        confermaCF.setVisible(false);
        verificaCodice.setVisible(false);
        iscrizioneClienteCheckout.setVisible(false);
        codiceCartaLabel.setVisible(false);
        codiceFiscaleLabel.setVisible(false);
        //codiceCarta.setText("0");
        applyScontoCartaButton.setVisible(false);
        answerRegistraVenditaLabel.setVisible(false);
        siRegistraVendita.setVisible(false);
        noRegistraVendita.setVisible(false);
        registraVenditaButton.setVisible(false);
        noScontoCartaButton.setVisible(false);
        denaroRicevutoLabel.setVisible(false);
        denaroRicevuto.setVisible(false);
        calcolaRestoButton.setVisible(false);
        resto.setVisible(false);
        restoLabel.setVisible(false);
        checkoutCompletedButton.setVisible(false);
        annullaCheckoutButton.setVisible(false);
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
    void calcolaRestoButtonEvent(ActionEvent event) {

    }

    @FXML
    void cartaDisponibileButton(ActionEvent event) {

    }

    @FXML
    void cercaClienteAAButtonEvent(ActionEvent event) {

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
    void confermaCFButton(ActionEvent event) {

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

    /***********************Assegnazione Carta****************************/

    public void initAssegnazioneCartaField(){
        inviaCodiceAlCheckoutButton.setVisible(false);
        tscAC.getItems().addAll(TipoScontoCliente.values());
    }

    public void getClientiFiltered(String email){
        clientiFiltratiAC.getItems().clear();
        clientiFiltratiAC.getItems().add(gestoreCommerciante.getCliente(email));
    }

    public long assegnaCarta(Cliente cliente, TipoScontoCliente tsc){
        return gestoreCommerciante.assegnaCarta(cliente,tsc);
    }

    @FXML
    void cercaClienteACButtonEvent(ActionEvent event) {
        getClientiFiltered(emailAC.getText());
    }

    @FXML
    void assegnaCartaButtonEvent(ActionEvent event) {
        codiceCartaAC.setText(String.valueOf(assegnaCarta(clientiFiltratiAC.getSelectionModel().getSelectedItem(), tscAC.getValue())));
    }

    @FXML
    void inviaCodiceAlCheckoutButtonEvent(ActionEvent event) {
        codiceCarta.setText(codiceCartaAC.getText());
        tabPaneCommerciante.getSelectionModel().select(tabCheckout);
    }

    /******************Interfaccia Consulta Inventario*********************/

    public void getInventario(){
        listViewConsultaInventario.getItems().clear();
        listViewConsultaInventario.getItems().addAll(gestoreCommerciante.getInventario());
    }

    public void getInfoMerce(MerceInventarioNegozio min){
        infoMerceConsultaInventario.clear();
        infoMerceConsultaInventario.setText(gestoreCommerciante.getInfoMerce(min));
    }

    @FXML
    void confermaConsultaInventarioEvent(ActionEvent event) {
        getInfoMerce(listViewConsultaInventario.getSelectionModel().getSelectedItem());
    }

    /****************Interfaccia Consegna Vendita Assegnata**************/

    public void getAcquistiClienteDaRitirare(String email){
        listaVenditeDaConsegnare.getItems().clear();
        listaVenditeDaConsegnare.getItems().addAll(gestoreCommerciante.getAcquistiClienteDaRitirare(email));
    }

    public void confermaConsegnaVenditaAssegnata(List<VenditaSpedita> vendite) {
        gestoreCommerciante.confermaConsegnaVenditaAssegnata(vendite);
    }

    @FXML
    void ccoButtonEvent(ActionEvent event) {
        getAcquistiClienteDaRitirare(emailConsegnaOrdine.getText());
    }

    @FXML
    void confermaConsegnaOrdineButton(ActionEvent event) {
        confermaConsegnaVenditaAssegnata(listaVenditeDaConsegnare.getSelectionModel().getSelectedItems());
        getAcquistiClienteDaRitirare(emailConsegnaOrdine.getText());
    }

    public void setGestoreCommerciante(GestoreCommerciante gestoreCommerciante) {
        this.gestoreCommerciante = gestoreCommerciante;
    }
}
