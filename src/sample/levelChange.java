package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class levelChange {




    @FXML
    void goToLevels(MouseEvent event) throws Exception {
        String choice = ((Pane) event.getSource()).getId();
        if (choice.equals("lvl1Button")){
            Model.levelName = "level1.txt";
        }else if(choice.equals("lvl2Button")){
            Model.levelName = "level2.txt";
        }else if(choice.equals("lvl3Button")){
            Model.levelName = "level3.txt";
        }

            FXMLLoader loader = new FXMLLoader(getClass().getResource("game_play.fxml"));
            Parent root = loader.load();
            GamePlayController controller = loader.getController();
            Scene scene = new Scene(root);
            Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
            root.setOnKeyPressed(controller);
            root.requestFocus();

    }



}
