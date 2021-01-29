package it.unicam.ids.c3.view;


import it.unicam.ids.c3.gestori.GestoreAddetto;
import it.unicam.ids.c3.gestori.GestoreCommerciante;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IAddettoNegozio {

    @Autowired
    private GestoreAddetto gestoreAddetto;

    @FXML
    private TabPane tabPaneAddetto;

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
    private ListView<MerceInventarioNegozio> listViewConsultaInventario;

    @FXML
    private Button confermaConsultaInventarioButton;

    @FXML
    private TextArea infoMerceConsultaInventario;

    @FXML
    private TextField emailConsegnaOrdine;

    @FXML
    private Button confermaClienteConsegnaOrdine;

    @FXML
    private ListView<VenditaSpedita> listaVenditeDaConsegnare;

    @FXML
    private Button confermaConsegnaOrdine;

    @FXML
    private Tab assegnaCartaTab;

    @FXML
    private Tab tabCheckout;

    @FXML
    private Button checkoutCompletedButton;

    @FXML
    private Button annullaCheckoutButton;

    @FXML
    private Button inviaCodiceAlCheckoutButton;

    public void startCarrello(){
        gestoreAddetto.startCarrello();
    }

    public double getPrezzo(long id, double quantita){
        return gestoreAddetto.getPrezzo(id, quantita);
    }

    public double getSconto(int id) {
        return gestoreAddetto.getSconto(id);
    }

    @FXML
    void trovaPrezzoEScontoButtonEvent(ActionEvent event) {
        startCarrello();
        prezzoMerce.setText(String.valueOf(getPrezzo(Long.parseLong(idMerce.getText()), Double.parseDouble(quantitaMerce.getText()))));
        if(prezzoMerce.getText()=="0"){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Merce inserita non presente, inserire il prezzo manualmente", ButtonType.OK);
            alert.show();
        }
        scontoMerce.setText(String.valueOf(getSconto(Integer.parseInt(idMerce.getText()))));
        if(scontoMerce.getText()=="0"){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Merce inserita non ha uno sconto, puoi inserire sconto manualmente", ButtonType.OK);
            alert.show();
        }
    }

    public double aggiuntaMerceNelCarrello(double prezzo, double sconto, int id, double quantita){
        return gestoreAddetto.aggiuntaMerceNelCarrello(prezzo,sconto,id,quantita);
    }

    @FXML
    void inserisciButtonEvent(ActionEvent event) {
        prezzoCarrello.setText(String.valueOf(aggiuntaMerceNelCarrello(Double.parseDouble(prezzoMerce.getText()),Double.parseDouble(scontoMerce.getText()),Integer.parseInt(idMerce.getText()),Double.parseDouble(quantitaMerce.getText()))));
        clearCheckoutFields();
    }



    private void clearCheckoutFields() {
        idMerce.clear();
        quantitaMerce.clear();
        prezzoMerce.clear();
        scontoMerce.clear();
    }

    /********************Richiesta Carta******************/

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



    /********************Richiesta Carta******************/

    private void possessoCarta(boolean flag){
        if(flag){
            siCartaDisponibile.setVisible(true);
            noCartaDisponibile.setVisible(true);
            cartaDisponibileButton.setVisible(true);
            cdLabel.setVisible(true);
        } else {
            codiceCarta.setText("0");
            iscrizioneClienteCheckout.setVisible(true);
            codiceCarta.setVisible(true);
            codiceCartaLabel.setVisible(true);
            noScontoCartaButton.setVisible(true);
        }
    }

    @FXML
    void possessoCartaButtonEvent(ActionEvent event) {
        if(siCarta.isSelected()){
            possessoCarta(true);
        } else {
            if(noCarta.isSelected()){
                possessoCarta(false);
            }
        }
    }

    private void disponibilitaCarta(boolean disponibilita){
        if(disponibilita){
            codiceCarta.setVisible(true);
            verificaCodice.setVisible(true);
            codiceCartaLabel.setVisible(true);
        } else {
            codiceFiscale.setVisible(true);
            confermaCF.setVisible(true);
            codiceFiscaleLabel.setVisible(true);
        }
    }

    @FXML
    void cartaDisponibileButton(ActionEvent event) {
        if(siCartaDisponibile.isSelected()) {
            disponibilitaCarta(true);
        } else {
            if(noCartaDisponibile.isSelected()) {
                disponibilitaCarta(false);
            }
        }
    }

    public boolean verificaCodiceCarta(long cc) {
        return gestoreAddetto.verificaCodiceCarta(cc);
    }

    public long searchCodiceCartaFromCodiceFiscale(String CF) {
        return gestoreAddetto.searchCodiceCartaFromCodiceFiscale(CF);
    }

    @FXML
    void verificaCodiceCartaButton(ActionEvent event) {
        if(verificaCodiceCarta(Long.parseLong(codiceCarta.getText()))) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Codice Carta valido!", ButtonType.OK);
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Codice Carta non valido!", ButtonType.OK);
            alert.show();
        }
        applyScontoCartaButton.setVisible(true);
    }

    @FXML
    void confermaCFButton(ActionEvent event) {
        codiceCarta.setVisible(true);
        codiceCartaLabel.setVisible(true);
        codiceCarta.setText(String.valueOf(searchCodiceCartaFromCodiceFiscale(codiceFiscale.getText())));
        if(codiceFiscale.getText()=="0") {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Codice Carta non trovato!", ButtonType.OK);
            alert.show();
        }
        applyScontoCartaButton.setVisible(true);
    }

    private void richiestaAssegnazioneCarta(boolean flag){
        if(flag){
            applyScontoCartaButton.setVisible(true);
            noScontoCartaButton.setVisible(false);
            inviaCodiceAlCheckoutButton.setVisible(true);
            tabPaneAddetto.getSelectionModel().select(assegnaCartaTab);
        } else {
            codiceCarta.setText("0");
        }
    }

    @FXML
    void iscrizioneClienteCheckoutButtonEvent(ActionEvent event) {
        richiestaAssegnazioneCarta(true);
    }

    /********************Fine Richiesta Carta*******************/

    @FXML
    void applyScontoCartaButtonEvent(ActionEvent event) {

    }


    @FXML
    void calcolaRestoButtonEvent(ActionEvent event) {

    }

    @FXML
    void noScontoCartaButtonEvent(ActionEvent event) {

    }

    @FXML
    void registraVenditaButtonEvent(ActionEvent event) {

    }

    @FXML
    void annullaCheckoutButtonEvent(ActionEvent event) {

    }

    @FXML
    void checkoutCompletedButtonEvent(ActionEvent event) {

    }
    /***********************Assegnazione Carta****************************/

    public void initAssegnazioneCartaField(){
        inviaCodiceAlCheckoutButton.setVisible(false);
        tscAC.getItems().addAll(TipoScontoCliente.values());
    }

    public void getClientiFiltered(String email){
        clientiFiltratiAC.getItems().clear();
        clientiFiltratiAC.getItems().add(gestoreAddetto.getCliente(email));
    }

    public long assegnaCarta(Cliente cliente, TipoScontoCliente tsc){
        return gestoreAddetto.assegnaCarta(cliente,tsc);
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
        tabPaneAddetto.getSelectionModel().select(tabCheckout);
    }

    /******************Interfaccia Consulta Inventario*********************/

    public void getInventario(){
        listViewConsultaInventario.getItems().clear();
        listViewConsultaInventario.getItems().addAll(gestoreAddetto.getInventario());
    }

    public void getInfoMerce(MerceInventarioNegozio min){
        infoMerceConsultaInventario.clear();
        infoMerceConsultaInventario.setText(gestoreAddetto.getInfoMerce(min));
    }

    @FXML
    void confermaConsultaInventarioEvent(ActionEvent event) {
        getInfoMerce(listViewConsultaInventario.getSelectionModel().getSelectedItem());
    }

    /****************Interfaccia Consegna Vendita Assegnata**************/

    public void getAcquistiClienteDaRitirare(String email){
        listaVenditeDaConsegnare.getItems().clear();
        listaVenditeDaConsegnare.getItems().addAll(gestoreAddetto.getAcquistiClienteDaRitirare(email));
    }

    public void confermaConsegnaVenditaAssegnata(List<VenditaSpedita> vendite) {
        gestoreAddetto.confermaConsegnaVenditaAssegnata(vendite);
    }

    @FXML
    void confermaClienteConsegnaOrdineButton(ActionEvent event) {
        getAcquistiClienteDaRitirare(emailConsegnaOrdine.getText());
    }

    @FXML
    void confermaConsegnaOrdineButton(ActionEvent event) {
        confermaConsegnaVenditaAssegnata(listaVenditeDaConsegnare.getSelectionModel().getSelectedItems());
        getAcquistiClienteDaRitirare(emailConsegnaOrdine.getText());
    }


























    public void setGestoreAddetto(GestoreAddetto gestoreAddetto) {
        this.gestoreAddetto = gestoreAddetto;
    }
}
