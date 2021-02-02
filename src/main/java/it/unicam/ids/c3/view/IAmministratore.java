package it.unicam.ids.c3.view;

import it.unicam.ids.c3.gestori.GestoreAmministratori;
import it.unicam.ids.c3.merce.Categoria;
import it.unicam.ids.c3.personale.Cliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IAmministratore {

    @Autowired
    private GestoreAmministratori gestoreAmministratori;

    @FXML
    public TextField emailClienteRicerca;

    @FXML
    public TextField emailRegistrazioneCorriere;

    @FXML
    private Button ricercaRegistrazioneCorriereButton;

    @FXML
    private ListView<Cliente> listClientiRegistrazioneCorriere;

    @FXML
    private Button confermaRegistraCorriereButton;

    @FXML
    private TextField nomeDittaRegistrazioneCorriere;

    @FXML
    private TextField pivaRegistrazioneCorriere;

    @FXML
    private TextField indirizzoRegistrazioneCorriere;

    @FXML
    private TextField nomeNegozio;

    @FXML
    private TextField partitaIVA;

    @FXML
    private TextField indirizzoNegozio;

    @FXML
    private ListView<Categoria> settoriList;

    @FXML
    private Button ricercaEmailClienteButton;

    @FXML
    private ListView<Cliente> clientiList;

    @FXML
    private Button confermaRegistrazioneNegozioButton;

    public void init() {
        settoriList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        settoriList.getItems().addAll(Categoria.values());
    }
    public Cliente ricercaCliente(String email) {
        return gestoreAmministratori.ricercaCliente(email);
    }
    public void registraCorriere(Cliente cliente,String nomeDitta,String piva,String indirizzoRegistrazione) {
        gestoreAmministratori.registraCorriere(cliente,nomeDitta,piva,indirizzoRegistrazione);
    }
    public void registraNegozio(List<Categoria> categorie, Cliente cliente, String nomeDitta, String piva, String indirizzoRegistrazione) {
        gestoreAmministratori.registraNegozio(categorie,cliente,nomeDitta,piva,indirizzoRegistrazione);
    }
    @FXML
    void confermaRegistraCorriereEvent(ActionEvent event) {
        registraCorriere(listClientiRegistrazioneCorriere.getSelectionModel().getSelectedItem(),nomeDittaRegistrazioneCorriere.getText(),pivaRegistrazioneCorriere.getText(),indirizzoRegistrazioneCorriere.getText());
    }

    @FXML
    void confermaRegistrazioneNegozioButtonEvent(ActionEvent event) {
        registraNegozio(settoriList.getSelectionModel().getSelectedItems(),clientiList.getSelectionModel().getSelectedItem(),nomeNegozio.getText(),partitaIVA.getText(),indirizzoNegozio.getText());
    }

    @FXML
    void ricercaEmailClienteButtonEvent(ActionEvent event) {
        clientiList.getItems().add(ricercaCliente(emailClienteRicerca.getText()));
    }

    @FXML
    void ricercaRegistrazioneCorriereEvent(ActionEvent event) {
        listClientiRegistrazioneCorriere.getItems().add(ricercaCliente(emailRegistrazioneCorriere.getText()));
    }

    public void setGestoreAmministratore(GestoreAmministratori gestoreAmministratori) {
        this.gestoreAmministratori = gestoreAmministratori;
    }

}
