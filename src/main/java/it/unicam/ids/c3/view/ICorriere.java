package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreCorrieri;
import it.unicam.ids.c3.vendita.VenditaSpedita;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ICorriere {

    private GestoreCorrieri gestore;

    /************Interfaccia Consulta Inventario********************/

    @FXML
    private ListView<VenditaSpedita> listaDaRitirare;

    @FXML
    private ListView<VenditaSpedita> listaRitirate;

    @FXML
    private ListView<VenditaSpedita> listaConsegnate;

    /************Interfaccia Preleva Merce********************/

    @FXML
    private ListView<VenditaSpedita> listaVenditeDaPrelevare;

    @FXML
    private Button prelevaVenditaButton;


    /************Interfaccia Consegna Vendita********************/

    @FXML
    private ListView<VenditaSpedita> listaVenditeDaConsegnare;

    @FXML
    private Button consegnaVenditaButton;

    public void init() {
        getVenditeNonRitirateInventario();
        getVenditeRitirateInventario();
        getVenditeConsegnateInventario();
        getVenditeDaRitirare();
        getVenditePreseInCarico();
    }
    /************Interfaccia Consulta Inventario********************/

    public void getVenditeNonRitirateInventario() {
        listaDaRitirare.getItems().clear();
        listaDaRitirare.getItems().addAll(gestore.getVenditeDaRitirare());
    }
    public void getVenditeRitirateInventario() {
        listaRitirate.getItems().clear();
        listaRitirate.getItems().addAll(gestore.getVenditeRitirate());
    }

    public void getVenditeConsegnateInventario() {
        listaConsegnate.getItems().clear();
        listaConsegnate.getItems().addAll(gestore.getVenditeConsegnate());
    }

    /************Interfaccia Preleva Vendita********************/

    public void getVenditeDaRitirare(){
        listaVenditeDaPrelevare.getItems().clear();
        listaVenditeDaPrelevare.getItems().addAll(gestore.getVenditeDaRitirare());
    }

    public void prelevaVendita(List<VenditaSpedita> list) {
        gestore.prelevaVendita(list);
    }

    @FXML
    void prelevaVenditaButtonEvent(ActionEvent event) {
        prelevaVendita(listaVenditeDaPrelevare.getSelectionModel().getSelectedItems());
        //getVenditeDaRitirare();
        init();
    }
    /************Interfaccia Consegna Vendita********************/

    public void getVenditePreseInCarico() {
        listaVenditeDaConsegnare.getItems().clear();
        listaVenditeDaConsegnare.getItems().addAll(gestore.getVenditeRitirate());
    }

    public void consegnaVendita(List<VenditaSpedita> list) {
        gestore.consegnaVendita(list);
    }

    @FXML
    void consegnaVenditaButtonEvent(ActionEvent event) {
        consegnaVendita(listaVenditeDaConsegnare.getSelectionModel().getSelectedItems());
//        getVenditePreseInCarico();
        init();
    }

    public void setGestoreCorriere(GestoreCorrieri gestoreCorrieri) {
        this.gestore = gestoreCorrieri;
    }
}

