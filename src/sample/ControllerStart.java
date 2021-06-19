package sample;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerStart {



    @FXML
    void playHandle(MouseEvent event) throws IOException {
        changeScene(event,"levels.fxml");

    }
    void changeScene(MouseEvent event, String newScene) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource(newScene));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

}
