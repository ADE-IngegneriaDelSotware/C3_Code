package it.unicam.ids.c3.autenticazione;

import it.unicam.ids.c3.gestori.GestoreClienti;
import it.unicam.ids.c3.view.ICliente;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class IIscrizione {

    @Autowired
    private GestoreIscrizione ci;

    @FXML
    private TextField nomeIscrizione;

    @FXML
    private TextField cognomeIscrizione;

    @FXML
    private TextField emailIscrizione;

    @FXML
    private TextField passwordIscrizione;

    @FXML
    private Button iscrizioneButton;

    /******************Iscrizione cliente******************/

    public void iscrizione(String nome,String cognome,String email, String password){
        ci.iscrizione(nome,cognome,email,password);
    }

    @FXML
    void iscrizioneButtonEvent(ActionEvent event) {
        iscrizione(nomeIscrizione.getText(),cognomeIscrizione.getText(),emailIscrizione.getText(), passwordIscrizione.getText());
        Stage stage1 = (Stage) iscrizioneButton.getScene().getWindow();
        stage1.close();
    }

    public void setControllerIscrizione(GestoreIscrizione ci) {
        this.ci = ci;
    }

}
