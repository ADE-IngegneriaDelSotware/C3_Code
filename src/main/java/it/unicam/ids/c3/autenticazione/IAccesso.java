package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.gestori.*;
import it.unicam.ids.c3.view.InteractManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

@Component
public class IAccesso implements Initializable{

    private InteractManager im = new InteractManager();
    @Autowired
    private GestoreAccesso ca;
    @Autowired
    private GestoreIscrizione ci;
    @Autowired
    private GestoreClienti gestoreClienti;
    @Autowired
    private GestoreCorrieri gestoreCorrieri;
    @Autowired
    private GestoreAddetti gestoreAddetti;
    @Autowired
    private GestoreAmministratori gestoreAmministratori;
    @Autowired
    private GestoreCommercianti gestoreCommercianti;

    /*******************Accesso cliente********************/

    @FXML
    private TextField emailAccesso;

    @FXML
    private TextField passwordAccesso;

    @FXML
    private Button accessoButton;

    @FXML
    private Label accessoComeLabel;

    @FXML
    private ChoiceBox<String> ruolo;

    @FXML
    private Button confermaAccessoButton;

    @FXML
    private Button registrazioneButton;

    /*******************Accesso cliente********************/

    @FXML
    void registrazioneButtonEvent(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/Iscrizione.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        IIscrizione iscrizione = fxmlLoader.getController();
        iscrizione.setControllerIscrizione(ci);
        Stage stage = new Stage();
        stage.setTitle("Interfaccia di iscrizione");
        stage.setScene(new Scene(root1));
        stage.show();
    }

    private void visibilitaRuolo(boolean flag){
        accessoComeLabel.setVisible(flag);
        confermaAccessoButton.setVisible(flag);
        ruolo.setVisible(flag);
    }

    public String accesso(String email, String password){
         return ca.accesso(email,password);
    }

    @FXML
    void accessoButtonEvent(ActionEvent event) {
        ruolo.getItems().clear();
        try{
            String ruoloAccesso = accesso(emailAccesso.getText(), passwordAccesso.getText());
            if(ruoloAccesso.equals("CLIENTE")){
                openCliente();
            } else {
                visibilitaRuolo(true);
                switch (ruoloAccesso){
                    case "AMMINISTRATORE":
                        ruolo.getItems().add("AMMINISTRATORE");
                        ruolo.getItems().add("CLIENTE");
                        break;
                    case "CORRIERE":
                        ruolo.getItems().add("CORRIERE");
                        ruolo.getItems().add("CLIENTE");
                        break;
                    case "ADDETTONEGOZIO":
                        ruolo.getItems().add("ADDETTO");
                        ruolo.getItems().add("CLIENTE");
                        break;
                    case "COMMERCIANTE":
                        ruolo.getItems().add("ADDETTO");
                        ruolo.getItems().add("COMMERCIANTE");
                        ruolo.getItems().add("CLIENTE");
                        break;
                }
            }
        } catch (IllegalStateException e){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Credenziali di accesso errate", ButtonType.OK);
            alert.show();
        }
    }

    @FXML
    void confermaAccessoButtonEvent(ActionEvent event) {
        switch (ruolo.getValue()){
            case "AMMINISTRATORE":
                openAmministratore();
                break;
            case "COMMERCIANTE":
                openCommerciante();
                break;
            case "ADDETTO":
                openAddetto();
                break;
            case "CORRIERE":
                openCorriere();
                break;
            case "CLIENTE":
                openCliente();
        }
    }

    private void clearFields() {
        emailAccesso.clear();
        passwordAccesso.clear();
        ruolo.getItems().clear();
    }

    private void openCorriere(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/ICorriere.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIc(fxmlLoader.getController());
        im.getIc().setGestoreCorriere(gestoreCorrieri);
        im.getIc().init();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia Accesso Corriere");
        stage.setScene(new Scene(root1));
        stage.show();
        clearFields();
    }



    private void openCliente(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/ICliente.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIcl(fxmlLoader.getController());
        im.getIcl().setGestoreClienti(gestoreClienti);
        im.getIcl().init();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia accesso cliente");
        stage.setScene(new Scene(root1));
        stage.show();
        clearFields();
    }

    private void openAmministratore(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/IAmministratore.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIam(fxmlLoader.getController());
        im.getIam().setGestoreAmministratore(gestoreAmministratori);
        im.getIam().init();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia Accesso Amministratore");
        stage.setScene(new Scene(root1));
        stage.show();
        clearFields();
    }

    private void openAddetto(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/IAddetto.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIa(fxmlLoader.getController());
        im.getIa().setGestoreAddetto(gestoreAddetti);
        im.getIa().initAssegnazioneCartaField();
        im.getIa().initRichiestaCartField();
        im.getIa().getInventario();
        im.getIa().startCarrello();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia commesso");
        stage.setScene(new Scene(root1));
        stage.show();
        clearFields();
    }

    private void openCommerciante() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/it/unicam/ids/c3/ICommerciante.fxml"));
        Parent root1 = null;
        try {
            root1 = (Parent) fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        im.setIcm(fxmlLoader.getController());
        im.getIcm().setGestoreCommerciante(gestoreCommercianti);
        im.getIcm().initFieldPromozioni();
        im.getIcm().getCorrieri();
        im.getIcm().initGestioneInventario();
        Stage stage = new Stage();
        stage.setTitle("Interfaccia commerciante");
        stage.setScene(new Scene(root1));
        stage.show();
        clearFields();
    }

    public void setController(GestoreAccesso ca) {
        this.ca = ca;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visibilitaRuolo(false);
    }

}
