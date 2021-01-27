package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreClienti;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Component
public class ICliente {

    @Autowired
    private GestoreClienti gestoreClienti;

    @FXML
    private TextField nomeProdottoRicerca;

    @FXML
    private Button confermaRicercaButton;

    @FXML
    private ListView<?> listaNegoziContenentiProdotto;

    @FXML
    private ListView<?> listViewPromozioni;

    @FXML
    private ChoiceBox<?> categoriePromozioni;

    @FXML
    private ListView<?> promozioniFiltrate;

    @FXML
    private Button confermaFiltroPromozioniButton;

    @FXML
    void confermaFiltroPromozioniButtonEvent(ActionEvent event) {

    }

    @FXML
    void confermaRicerca(ActionEvent event) {

    }


    public void setGestoreClienti(GestoreClienti gestoreClienti) {
        this.gestoreClienti = gestoreClienti;
    }
}
