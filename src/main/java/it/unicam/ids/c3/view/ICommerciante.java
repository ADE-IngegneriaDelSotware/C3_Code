package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreCommerciante;
import it.unicam.ids.c3.merce.Categoria;
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
import javafx.scene.control.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ICommerciante {

    @Autowired
    private GestoreCommerciante gestoreCommerciante;

    @FXML
    private TabPane tabPaneCommerciante;

    @FXML
    private Button confermaAssunzioneAddettoButton;

    @FXML
    private TextField emailAA;

    @FXML
    private ListView<Cliente> clientiFiltratiAA;

    @FXML
    private Button cercaClienteAAButton;

    @FXML
    private ListView<MerceInventarioNegozio> listaPromozioni;

    @FXML
    private Button aggiungiPromozioneButton;

    @FXML
    private Button rimuoviPromozioneButton;

    @FXML
    private ListView<MerceInventarioNegozio> listaPromozioniPossibili;

    @FXML
    private DatePicker dataI;

    @FXML
    private DatePicker dataF;

    @FXML
    private TextField percentualePromozione;

    @FXML
    private Button aggiuntaPromozione;

    @FXML
    private ListView<Corriere> corrieriDaAggiungere;

    @FXML
    private Button confermAggiuntaCorriereButton;

    @FXML
    private ListView<MerceInventarioNegozio> merciInventario;

    @FXML
    private Button aggiungiButton;

    @FXML
    private Button rimuoviMerceButton;

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
    private TextField nomeMerceGI;

    @FXML
    private TextField prezzoMerceGI;

    @FXML
    private TextField scontoMerceGI;

    @FXML
    private ChoiceBox<Categoria> categoriaMerceGI;

    @FXML
    private TextField quantitaMerceGI;

    @FXML
    private Button confermaRimozioneMerceButton;

    @FXML
    private Label dataInizioPromozione;

    @FXML
    private Label dataFinePromozione;

    @FXML
    private Label scontoPromozione;

    @FXML
    private Tab tabAssunzioneAddetto;

    @FXML
    private Tab tabGestionePromozioni;

    @FXML
    private Tab tabGestioneCorrieri;

    @FXML
    private Tab tabGestioneInventario;

    /******************Interfaccia GestionePromozioni***************/

    public void getPromozioniAttive(){
        listaPromozioni.getItems().clear();
        listaPromozioni.getItems().addAll(gestoreCommerciante.getPromozioniAttive());
    }

    public void getMerciDoveApplicarePromozioni(){
        listaPromozioniPossibili.getItems().clear();
        listaPromozioniPossibili.getItems().addAll(gestoreCommerciante.getPromozioniPossibili());
    }

    @FXML
    void aggiungiPromozioneButtonEvent(ActionEvent event) {
        listaPromozioniPossibili.setVisible(true);
        dataF.setVisible(true);
        dataI.setVisible(true);
        percentualePromozione.setVisible(true);
        aggiungiPromozioneButton.setVisible(true);
        aggiuntaPromozione.setVisible(true);
        dataInizioPromozione.setVisible(true);
        dataFinePromozione.setVisible(true);
        scontoPromozione.setVisible(true);
        getMerciDoveApplicarePromozioni();
    }

    public void addPromozione(MerceInventarioNegozio miv, LocalDate di, LocalDate df, double pp){
        gestoreCommerciante.addPromozione(miv,di,df,pp);
    }

    @FXML
    void addPromozioneButtonEvent(ActionEvent event){
        addPromozione(listaPromozioniPossibili.getSelectionModel().getSelectedItem(),dataI.getValue(), dataF.getValue(),Double.parseDouble(percentualePromozione.getText()));
        getPromozioniAttive();
        initFieldPromozioni();
    }

    public void rimuoviPromozione(List<MerceInventarioNegozio> lista){
        gestoreCommerciante.rimuoviPromozione(lista);
    }
    @FXML
    void rimuoviPromozioneButtonEvent(ActionEvent event) {
        listaPromozioniPossibili.setVisible(false);
        dataF.setVisible(false);
        dataI.setVisible(false);
        percentualePromozione.setVisible(false);
        aggiuntaPromozione.setVisible(false);
        rimuoviPromozione(listaPromozioni.getSelectionModel().getSelectedItems());
        getPromozioniAttive();
    }

    public void initFieldPromozioni(){
        listaPromozioni.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listaPromozioniPossibili.setVisible(false);
        dataF.setVisible(false);
        dataI.setVisible(false);
        percentualePromozione.setVisible(false);
        aggiuntaPromozione.setVisible(false);
        dataInizioPromozione.setVisible(false);
        dataFinePromozione.setVisible(false);
        scontoPromozione.setVisible(false);
        getPromozioniAttive();
    }

    /******************Interfaccia Gestione Corriere***************/

    public void getCorrieri() {
        corrieriDaAggiungere.getItems().clear();
        corrieriDaAggiungere.getItems().addAll(gestoreCommerciante.getCorrieri());
    }

    public void addCorrieri(List<Corriere> corrieriDaAggiungere) {
        gestoreCommerciante.addCorrieri(corrieriDaAggiungere);
    }

    @FXML
    void confermAggiuntaCorriereButtonEvent(ActionEvent event) {
        addCorrieri(corrieriDaAggiungere.getSelectionModel().getSelectedItems());
        getCorrieri();
    }

    /******************Interfaccia assunzione addetto***************/

    public void getClienti(String email){
        clientiFiltratiAA.getItems().clear();
        clientiFiltratiAA.getItems().addAll(gestoreCommerciante.getCliente(email));
    }

    public void assunzioneAddetto(Cliente cliente){
        gestoreCommerciante.assunzioneAddetto(cliente);
    }

    @FXML
    void cercaClienteAAButtonEvent(ActionEvent event) {
        getClienti(emailAA.getText());
    }

    @FXML
    void confermaAssunzioneAddettoButtonEvent(ActionEvent event) {
        assunzioneAddetto(clientiFiltratiAA.getSelectionModel().getSelectedItem());
        clientiFiltratiAA.getItems().clear();
    }

    /****************Gestione inventario**************/

    public void initGestioneInventario() {
        getMerciInventario();
        categoriaMerceGI.getItems().addAll(Categoria.values());
        nomeLabelGI.setVisible(false);
        nomeMerceGI.setVisible(false);
        descrizioneLabelGI.setVisible(false);
        descrizioneMerceGI.setVisible(false);
        categoriaLabelGI.setVisible(false);
        categoriaMerceGI.setVisible(false);
        quantitaLabelGI.setVisible(false);
        quantitaMerceGI.setVisible(false);
        prezzoLabelGI.setVisible(false);
        prezzoMerceGI.setVisible(false);
        scontoLabelGI.setVisible(false);
        scontoMerceGI.setVisible(false);
        confermaAggiuntaMerceButton.setVisible(false);
        confermaRimozioneMerceButton.setVisible(false);
    }

    public void getMerciInventario() {
        merciInventario.getItems().clear();
        merciInventario.getItems().addAll(gestoreCommerciante.getInventario());
    }
    public void rimuoviMerce(List<MerceInventarioNegozio> min,double quantita) {
        gestoreCommerciante.removeMerce(min,quantita);
    }
    private void aggiungiMerce(String nome, String descrizione, Categoria categoria, double quantita, double prezzo, double sconto) {
        gestoreCommerciante.addMerce(nome,descrizione,categoria,quantita,prezzo,sconto);
    }

    @FXML
    void aggiungiButtonEvent(ActionEvent event) {
        nomeLabelGI.setVisible(true);
        nomeMerceGI.setVisible(true);
        descrizioneLabelGI.setVisible(true);
        descrizioneMerceGI.setVisible(true);
        categoriaLabelGI.setVisible(true);
        categoriaMerceGI.setVisible(true);
        quantitaLabelGI.setVisible(true);
        quantitaMerceGI.setVisible(true);
        prezzoLabelGI.setVisible(true);
        prezzoMerceGI.setVisible(true);
        scontoLabelGI.setVisible(true);
        scontoMerceGI.setVisible(true);
        confermaAggiuntaMerceButton.setVisible(true);

    }

    @FXML
    void confermaAggiuntaMerceButtonEvent(ActionEvent event) {
        aggiungiMerce(nomeMerceGI.getText(),
                descrizioneMerceGI.getText(),
                categoriaMerceGI.getValue(),
                Double.parseDouble(quantitaMerceGI.getText()),
                Double.parseDouble(prezzoMerceGI.getText()),
                Double.parseDouble(scontoMerceGI.getText()));
        initGestioneInventario();
    }
    @FXML
    void confermaRimozioneMerceButtonEvent(ActionEvent event) {
        rimuoviMerce(merciInventario.getSelectionModel().getSelectedItems(),Double.parseDouble(quantitaMerceGI.getText()));
        initGestioneInventario();
    }

    @FXML
    void rimuoviMerceButtonEvent(ActionEvent event) {
        quantitaLabelGI.setVisible(true);
        quantitaMerceGI.setVisible(true);
        confermaRimozioneMerceButton.setVisible(true);
    }

    public void setGestoreCommerciante(GestoreCommerciante gestoreCommerciante) {
        this.gestoreCommerciante = gestoreCommerciante;
    }
}
