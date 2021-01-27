package it.unicam.ids.c3.javafx;

import it.unicam.ids.c3.C3Application;
import it.unicam.ids.c3.autenticazione.IIscrizione;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import java.io.IOException;

public class JavaFxApplication extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() throws Exception {
        ApplicationContextInitializer<GenericApplicationContext> applicationContextInitializer =
          ac -> {
            ac.registerBean(Application.class, () -> JavaFxApplication.this );
            ac.registerBean(Parameters.class, () -> getParameters());
            ac.registerBean(HostServices.class, () -> getHostServices());
        };
        this.context = new SpringApplicationBuilder()
            .sources(C3Application.class)
            .initializers(applicationContextInitializer)
            .run(getParameters().getRaw().toArray(new String[0]));
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.context.publishEvent(new StageReadyEvent(stage));
    }

    @Override
    public void stop() throws Exception {
        this.context.close();
        Platform.exit();
    }
}