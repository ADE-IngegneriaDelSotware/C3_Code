package it.unicam.ids.c3.autenticazione.view;

import it.unicam.ids.c3.autenticazione.gestori.GestoreIscrizione;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
