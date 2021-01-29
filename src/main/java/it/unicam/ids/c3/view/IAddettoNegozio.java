package it.unicam.ids.c3.view;


import it.unicam.ids.c3.gestori.GestoreAddetto;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Negozio;
import it.unicam.ids.c3.negozio.TipoScontoCliente;
import it.unicam.ids.c3.personale.Cliente;
import it.unicam.ids.c3.personale.Corriere;
import it.unicam.ids.c3.vendita.LuogoDiRitiro;
import it.unicam.ids.c3.vendita.TipoDiRitiro;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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

    @FXML
    private ChoiceBox<TipoDiRitiro> tipoRitiro;

    @FXML
    private Label corrieriDisponibiliLabel;

    @FXML
    private ListView<Corriere> corrieriDisponibili;

    @FXML
    private Label trcLabel;

    @FXML
    private ChoiceBox<LuogoDiRitiro> luogoDiRitiro;

    @FXML
    private ListView<Negozio> puntiDiRitiroDisponibili;

    @FXML
    private Label prLabel;

    @FXML
    private Label indirizzoLabel;

    @FXML
    private TextField indirizzoRitiro;

    @FXML
    private Button venditaButton;

    @FXML
    private Button confermaTipoRitiroButton;

    @FXML
    private Button confermaLuogoDiRitiroButton;

    @FXML
    private TextField codiceClienteInRegistrazione;

    @FXML
    private Label codiceClienteInRegistrazioneLabel;

    @FXML
    private Tab tabRegistraVendita;

    @FXML
    private Button inviaCodiceAllaRegistrazioneButton;

    public void startCarrello(){
        gestoreAddetto.startCarrello();
    }

    public double getPrezzo(long id, double quantita){
        return gestoreAddetto.getPrezzo(id, quantita);
    }

    public double getSconto(long id) {
        return gestoreAddetto.getSconto(id);
    }

    @FXML
    void trovaPrezzoEScontoButtonEvent(ActionEvent event) {
        prezzoMerce.setText(String.valueOf(getPrezzo(Long.parseLong(idMerce.getText()), Double.parseDouble(quantitaMerce.getText()))));
        if(prezzoMerce.getText()=="0"){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Merce inserita non presente, inserire il prezzo manualmente", ButtonType.OK);
            alert.show();
        }
        scontoMerce.setText(String.valueOf(getSconto(Long.parseLong(idMerce.getText()))));
        if(scontoMerce.getText()=="0"){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Merce inserita non ha uno sconto, puoi inserire sconto manualmente", ButtonType.OK);
            alert.show();
        }
    }

    public double aggiuntaMerceNelCarrello(double prezzo, double sconto, long id, double quantita){
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
        tabRegistraVendita.setDisable(true);
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

    public long searchCodiceCartaByEmail(String email) {
        return gestoreAddetto.searchCodiceCartaFromEmail(email);
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
        codiceCarta.setText(String.valueOf(searchCodiceCartaByEmail(codiceFiscale.getText())));
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

    public double applyScontoCarta(long cc){
        return gestoreAddetto.applyScontoCarta(cc);
    }

    @FXML
    void applyScontoCartaButtonEvent(ActionEvent event) {
        prezzoTotale.setText(String.valueOf(applyScontoCarta(Long.parseLong(codiceCarta.getText()))));
        answerRegistraVenditaLabel.setVisible(true);
        siRegistraVendita.setVisible(true);
        noRegistraVendita.setVisible(true);
        registraVenditaButton.setVisible(true);
    }

    @FXML
    void noScontoCartaButtonEvent(ActionEvent event) {
        prezzoTotale.setText(String.valueOf(applyScontoCarta(Long.parseLong(codiceCarta.getText()))));
        answerRegistraVenditaLabel.setVisible(true);
        siRegistraVendita.setVisible(true);
        noRegistraVendita.setVisible(true);
        registraVenditaButton.setVisible(true);
    }

    private void registraVendita(boolean flag,long cc){
        if(flag && cc==0){
            openAssegnazioneCarta();
            changeVisibilityFieldRegistraVendita();
        } else {
            if(flag){
                openRegistraVendita();
                changeVisibilityFieldRegistraVendita();
            } else {
                changeVisibilityFieldRegistraVendita();
                addVenditaInventario();
            }
        }
    }

    @FXML
    void registraVenditaButtonEvent(ActionEvent event) {
        boolean flag = true;
        if(siRegistraVendita.isSelected()){
            flag = true;
        } else {
            if (noRegistraVendita.isSelected()){
                flag = false;
            }
        }
        registraVendita(flag,Long.parseLong(codiceCarta.getText()));
    }

    private void openAssegnazioneCarta(){
        inviaCodiceAllaRegistrazioneButton.setVisible(true);
        tabPaneAddetto.getSelectionModel().select(assegnaCartaTab);
    }

    private void openRegistraVendita(){
        tabRegistraVendita.setDisable(false);
        initRegistrazioneVenditaField();
        codiceClienteInRegistrazione.setText(codiceCarta.getText());
        tabPaneAddetto.getSelectionModel().select(tabRegistraVendita);
    }

    public void addVenditaInventario(){
        gestoreAddetto.addVenditaInventario();
    }

    public double calcolaResto(double denaro){
        return gestoreAddetto.calcoraResto(denaro);
    }

    @FXML
    void calcolaRestoButtonEvent(ActionEvent event) {
        resto.setText(String.valueOf(calcolaResto(Double.parseDouble(denaroRicevuto.getText()))));
    }

    public void checkoutCompletato(){
        gestoreAddetto.checkoutCompletato();
    }

    @FXML
    void checkoutCompletedButtonEvent(ActionEvent event) {
        checkoutCompletato();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vendita andata a buon fine",ButtonType.FINISH);
        alert.show();
    }

    public void annullaCheckout(){
        gestoreAddetto.annullaCheckout();
    }

    @FXML
    void annullaCheckoutButtonEvent(ActionEvent event) {
        annullaCheckout();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Vendita non andata a buon fine",ButtonType.FINISH);
        alert.show();
    }

    public void changeVisibilityFieldRegistraVendita(){
        denaroRicevutoLabel.setVisible(true);
        denaroRicevuto.setVisible(true);
        calcolaRestoButton.setVisible(true);
        resto.setVisible(true);
        restoLabel.setVisible(true);
        checkoutCompletedButton.setVisible(true);
        annullaCheckoutButton.setVisible(true);
    }

    /***********************Interfaccia registrazione vendita************************/

    private void initRegistrazioneVenditaField() {
        tipoRitiro.getItems().addAll(TipoDiRitiro.values());
        luogoDiRitiro.getItems().addAll(LuogoDiRitiro.values());
        prLabel.setVisible(false);
        corrieriDisponibiliLabel.setVisible(false);
        corrieriDisponibili.setVisible(false);
        trcLabel.setVisible(false);
        luogoDiRitiro.setVisible(false);
        confermaLuogoDiRitiroButton.setVisible(false);
        indirizzoLabel.setVisible(false);
        indirizzoRitiro.setVisible(false);
        puntiDiRitiroDisponibili.setVisible(false);
//        codiceClienteInRegistrazione.setText(String.valueOf(gestoreAddetto.getCc()));
        codiceClienteInRegistrazioneLabel.setVisible(false);
        codiceClienteInRegistrazione.setVisible(false);
        venditaButton.setVisible(false);
    }

    private void selectTipoRitiroVendita(TipoDiRitiro tr){
        if(tr.equals(TipoDiRitiro.CORRIERE)){
            corrieriDisponibiliLabel.setVisible(true);
            corrieriDisponibili.getItems().addAll(getCorrieriDisponibili());
            corrieriDisponibili.setVisible(true);
            trcLabel.setVisible(true);
            luogoDiRitiro.setVisible(true);
            confermaLuogoDiRitiroButton.setVisible(true);
        } else {
            codiceClienteInRegistrazioneLabel.setVisible(true);
            codiceClienteInRegistrazione.setVisible(true);
            venditaButton.setVisible(true);
        }
    }

    private List<Corriere> getCorrieriDisponibili(){
        return gestoreAddetto.getCorrieriDisponibili();
    }

    private void getNegoziDisponibili(){
        puntiDiRitiroDisponibili.getItems().clear();
        puntiDiRitiroDisponibili.getItems().addAll(gestoreAddetto.getNegoziDisponibili());
    }

    @FXML
    void confermaTipoRitiroButtonEvent(ActionEvent event) {
        selectTipoRitiroVendita(tipoRitiro.getSelectionModel().getSelectedItem());
    }

    private void selectLuogoDiRitiro(LuogoDiRitiro ldr){
        if(ldr.equals(LuogoDiRitiro.NEGOZIO)){
            getNegoziDisponibili();
            puntiDiRitiroDisponibili.setVisible(true);
            prLabel.setVisible(true);
        } else {
            indirizzoLabel.setVisible(true);
            indirizzoRitiro.setVisible(true);
        }
        codiceClienteInRegistrazioneLabel.setVisible(true);
        codiceClienteInRegistrazione.setVisible(true);
        venditaButton.setVisible(true);
    }

    @FXML
    void confermaLuogoDiRitiroButtonEvent(ActionEvent event) {
        selectLuogoDiRitiro(luogoDiRitiro.getSelectionModel().getSelectedItem());
    }

    private void registraAcquistoCliente(long cc, Negozio pdr, String indirizzo , Corriere corriere){
        gestoreAddetto.registraAcquistoCliente(cc,pdr,indirizzo, corriere);
    }

    private void registraAcquistoCliente(long cc){
        gestoreAddetto.registraAcquistoCliente(cc);
    }

    @FXML
    void venditaButtonEvent(ActionEvent event) {
        if(tipoRitiro.getSelectionModel().getSelectedItem().equals(TipoDiRitiro.CORRIERE)){
            registraAcquistoCliente(Long.parseLong(codiceClienteInRegistrazione.getText()),puntiDiRitiroDisponibili.getSelectionModel().getSelectedItem(),indirizzoRitiro.getText(),corrieriDisponibili.getSelectionModel().getSelectedItem());
        } else {
            registraAcquistoCliente(Long.parseLong(codiceClienteInRegistrazione.getText()));
        }
    }

    /***********************Assegnazione Carta****************************/

    public void initAssegnazioneCartaField(){
        inviaCodiceAlCheckoutButton.setVisible(false);
        inviaCodiceAllaRegistrazioneButton.setVisible(false);
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

    @FXML
    void inviaCodiceAllaRegistrazioneButtonEvent(ActionEvent event){
        tabRegistraVendita.setDisable(false);
        initRegistrazioneVenditaField();
        codiceClienteInRegistrazione.setText(codiceCartaAC.getText());
        tabPaneAddetto.getSelectionModel().select(tabRegistraVendita);
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
