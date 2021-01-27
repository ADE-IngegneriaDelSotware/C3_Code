package it.unicam.ids.c3.autenticazione;

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

//    private InteractManager im = new InteractManager()
    @Autowired
    private ControllerAccesso ca;
    @Autowired
    private ControllerIscrizione ci;


    /*******************Accesso cliente********************/

    @FXML
    private TextField userNameAccesso;

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
        iscrizione.setController(ci);
        iscrizione.setControllerAccesso(ca);
        Stage stage = new Stage();
        stage.setTitle("Interfaccia di iscrizione");
        stage.setScene(new Scene(root1));
        stage.show();
        Stage stage1 = (Stage) registrazioneButton.getScene().getWindow();
        stage1.close();
    }

    private void visibilitaRuolo(boolean flag){
        accessoComeLabel.setVisible(flag);
        confermaAccessoButton.setVisible(flag);
        ruolo.setVisible(flag);
    }

    public boolean accesso(String email, String password){
         return ca.accesso(email,password);
    }

    @FXML
    void accessoButtonEvent(ActionEvent event) {
        if(accesso(userNameAccesso.getText(), passwordAccesso.getText())){
            System.out.println("accesso effettuato");
        }
    }

    private void openCorriere(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ICorriere.fxml"));
//        Parent root1 = null;
//        try {
//            root1 = (Parent) fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        IM.setIc(fxmlLoader.getController());
//        IM.getIc().setControllerCorriere(controllerCorriere);
//        Stage stage = new Stage();
//        stage.setTitle("Interfaccia Accesso Corriere");
//        stage.setScene(new Scene(root1));
//        stage.show();
    }

    private void openCliente(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ICliente.fxml"));
//        Parent root1 = null;
//        try {
//            root1 = (Parent) fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        IM.setIcl(fxmlLoader.getController());
//        IM.getIcl().setControllerCliente(controllerCliente);
//        Stage stage = new Stage();
//        stage.setTitle("Interfaccia accesso cliente");
//        stage.setScene(new Scene(root1));
//        stage.show();
    }

    private void openAmministratore(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IAmministratore.fxml"));
//        Parent root1 = null;
//        try {
//            root1 = (Parent) fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        IM.setIam(fxmlLoader.getController());
//        IM.getIam().setController(controllerAmministratore);
//        Stage stage = new Stage();
//        stage.setTitle("Interfaccia Accesso Amministratore");
//        stage.setScene(new Scene(root1));
//        stage.show();
    }

    private void openAddetto(){
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IANCommesso.fxml"));
//        Parent root1 = null;
//        try {
//            root1 = (Parent) fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        IM.setIa(fxmlLoader.getController());
//        IM.getIa().setControllerNegozio(controllerNegozio);
//        Stage stage = new Stage();
//        stage.setTitle("Interfaccia commesso");
//        stage.setScene(new Scene(root1));
//        stage.show();
    }

    private void openCommerciante() {
//        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("IANCommerciante.fxml"));
//        Parent root1 = null;
//        try {
//            root1 = (Parent) fxmlLoader.load();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        IM.setIa(fxmlLoader.getController());
//        IM.getIa().setControllerNegozio(controllerNegozio);
//        Stage stage = new Stage();
//        stage.setTitle("Interfaccia commerciante");
//        stage.setScene(new Scene(root1));
//        stage.show();
    }

//    @FXML
//    void accessoButtonEvent(ActionEvent event) {
//        accesso(userNameAccesso.getText(), passwordAccesso.getText());
//        if(accesso(userNameAccesso.getText(), passwordAccesso.getText()) != null){
//            Cliente cliente = accesso(userNameAccesso.getText(), passwordAccesso.getText());
//            if(cliente.getRuolo()!=null){
//                visibilitaRuolo(true);
//                switch (cliente.getRuolo().getRuoloSistema()){
//                    case AMMINISTRATORE:
//                        ruolo.getItems().add("AMMINISTRATORE");
//                        ruolo.getItems().add("CLIENTE");
//                        break;
//                    case CORRIERE:
//                        ruolo.getItems().add("CORRIERE");
//                        ruolo.getItems().add("CLIENTE");
//                        break;
//                    case ADDETTONEGOZIO:
//                        ruolo.getItems().add("ADDETTO");
//                        ruolo.getItems().add("CLIENTE");
//                        break;
//                    case COMMERCIANTE:
//                        ruolo.getItems().add("COMMERCIANTE");
//                        ruolo.getItems().add("CLIENTE");
//                        break;
//                }
//            } else {
//                openCliente();
//            }
//        } else {
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Utente non valido", ButtonType.FINISH);
//            alert.show();
//        }
//    }

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

    public void setController(ControllerAccesso ca) {
        this.ca = ca;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        visibilitaRuolo(false);
    }

}
