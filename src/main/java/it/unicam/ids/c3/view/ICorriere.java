package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreClienti;
import it.unicam.ids.c3.gestori.GestoreCorrieri;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ICorriere {

    @Autowired
    private GestoreCorrieri gestore;
    
    /************Interfaccia Consulta Inventario********************/

    @FXML
    private ListView<?> listaDaRitirare;

    @FXML
    private ListView<?> listaRitirate;

    @FXML
    private ListView<?> listaConsegnate;

    /************Interfaccia Preleva Merce********************/

    @FXML
    private ListView<?> listaVenditeDaPrelevare;

    @FXML
    private Button prelevaVenditaButton;


    /************Interfaccia Consegna Vendita********************/

    @FXML
    private ListView<?> listaVenditeDaConsegnare;

    @FXML
    private Button consegnaVenditaButton;


    public void prelevaVenditaButtonEvent(ActionEvent event) {
    }

    public void consegnaVenditaButtonEvent(ActionEvent event) {
    }

    public void setGestoreCorriere(GestoreCorrieri gestoreCorrieri) {
        this.gestore = gestoreCorrieri;
    }
}

