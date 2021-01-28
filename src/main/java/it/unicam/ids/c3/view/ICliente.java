package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreClienti;
import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.merce.MerceInventarioNegozio;
import it.unicam.ids.c3.negozio.Negozio;
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
import java.util.List;


@Component
public class ICliente {

    @Autowired
    private GestoreClienti gestoreClienti;

    @FXML
    private TextField nomeProdottoRicerca;

    @FXML
    private Button confermaRicercaButton;

    @FXML
    private ListView<Negozio> listaNegoziContenentiProdotto;

    @FXML
    private ListView<MerceInventarioNegozio> listViewPromozioni;

    @FXML
    private ChoiceBox<Categoria> categoriePromozioni;

    @FXML
    private ListView<MerceInventarioNegozio> promozioniFiltrate;

    @FXML
    private Button confermaFiltroPromozioniButton;

    public void init() {
        getPromozioni();
        categoriePromozioni.getItems().addAll(Categoria.values());
    }
    /*****************Consulta Promozioni******************/
    private void getPromozioni(){
        listViewPromozioni.getItems().addAll(gestoreClienti.getPromozioni());
        categoriePromozioni.getItems().addAll(Categoria.values());
    }
    public List<MerceInventarioNegozio> filtraPromozioniPerCategoria(Categoria categoria){
        return gestoreClienti.filtraPromozioniPerCategoria(categoria);
    }
    @FXML
    void confermaFiltroPromozioniButtonEvent(ActionEvent event) {
        promozioniFiltrate.getItems().clear();
        promozioniFiltrate.getItems().addAll(filtraPromozioniPerCategoria(categoriePromozioni.getValue()));
    }
    /*****************Ricerca Prodotto******************/
    public List<Negozio> ricercaProdotto(String nome) {
        return gestoreClienti.ricercaProdotto(nome);
    }

    @FXML
    void confermaRicerca(ActionEvent event) {
        listaNegoziContenentiProdotto.getItems().clear();
        listaNegoziContenentiProdotto.getItems().addAll(ricercaProdotto(nomeProdottoRicerca.getText()));
    }


    public void setGestoreClienti(GestoreClienti gestoreClienti) {
        this.gestoreClienti = gestoreClienti;
    }
}
