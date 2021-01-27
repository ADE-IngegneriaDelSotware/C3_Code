package it.unicam.ids.c3.javafx;

import javafx.stage.Stage;
import org.springframework.context.ApplicationEvent;

public class StageReadyEvent extends ApplicationEvent {

    public StageReadyEvent(Object source) {
        super(source);
    }

    public Stage getStage(){
        return Stage.class.cast( getSource());
    }
}
