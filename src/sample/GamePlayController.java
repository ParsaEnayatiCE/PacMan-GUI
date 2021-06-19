package sample;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class GamePlayController implements EventHandler<KeyEvent> {
    final private static double FRAMES_PER_SECOND = 5.0;

    @FXML
    private View pacManView;
    @FXML
    private BorderPane playingBorder;
    @FXML
    private Text showScore;
    private Model pacManModel;

    private Timer timer;
    private static int ghostEatingModeCounter;
    private boolean paused;
    private MediaPlayer player;

    private boolean isAlertOpen = false;

    public GamePlayController() {
        this.paused = false;
    }

    public void initialize() throws IOException {
        String musicFile = "src\\sample\\mp3\\song.mp3";
        Media sound = new Media(new File(musicFile).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        this.player = mediaPlayer;
        mediaPlayer.play();
        String file = Model.levelName;
        this.pacManModel = new Model();
        this.update(Model.Direction.NONE);
        ghostEatingModeCounter = 25;
        this.startTimer();
    }

    private void startTimer() {
        this.timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        try {
                            update(pacManModel.getCurrentDirection());
                            if (pacManModel.isGameOver()) {
                                player.stop();
                                Stage stage = (Stage) playingBorder.getScene().getWindow();
                                stage.close();
                                Parent root = FXMLLoader.load(getClass().getResource("gameOver.fxml"));
                                Scene scene = new Scene(root);
                                stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                cancel();

                            }
                            if (pacManModel.isYouWon()) {
                                player.stop();
                                Stage stage = (Stage) playingBorder.getScene().getWindow();
                                stage.close();
                                Parent root = FXMLLoader.load(getClass().getResource("youWon.fxml"));


                                Scene scene = new Scene(root);
                                stage = new Stage();
                                stage.setScene(scene);
                                stage.show();
                                cancel();
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        };
        long frameTimeInMilliseconds = (long)(1000.0 / FRAMES_PER_SECOND);
        this.timer.schedule(timerTask, 0, frameTimeInMilliseconds);

    }

    private void update(Model.Direction direction) throws IOException {
        this.showScore.setText(Integer.toString(pacManModel.score));
        this.pacManModel.step(direction);
        this.pacManView.update(pacManModel);
        if (pacManModel.isGhostEatingMode()) {
            ghostEatingModeCounter--;
        }
        if (ghostEatingModeCounter == 0 && pacManModel.isGhostEatingMode()) {
            pacManModel.setGhostEatingMode(false);
        }
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        boolean keyRecognized = true;
        KeyCode code = keyEvent.getCode();
        Model.Direction direction = Model.Direction.NONE;
        if (code == KeyCode.LEFT) {
            direction = Model.Direction.LEFT;
        } else if (code == KeyCode.RIGHT) {
            direction = Model.Direction.RIGHT;
        } else if (code == KeyCode.UP) {
            direction = Model.Direction.UP;
        } else if (code == KeyCode.DOWN) {
            direction = Model.Direction.DOWN;
        } else if (code == KeyCode.R) {
            player.stop();
            pause();
            this.pacManModel.startNewGame();

            paused = false;
            this.startTimer();
            player.play();
        }
        else if(code == KeyCode.M){
            if (!player.isMute()){
                player.setMute(true);
            }
            else{
                player.setMute(false);
            }
        }

        else if (code == KeyCode.P && paused == false){
            player.pause();
            paused = true;
            pause();
        }
        else if (code == KeyCode.P && paused ==true){
            player.play();
            paused = false;
            this.startTimer();
        }
        else if(code == KeyCode.ESCAPE){
            System.exit(0);
        }
        else {
            keyRecognized = false;
        }
        if (keyRecognized) {
            keyEvent.consume();
            pacManModel.setCurrentDirection(direction);
        }
    }

    public void pause() {
        this.timer.cancel();
        this.paused = true;
    }

    public static void setGhostEatingModeCounter() {
        ghostEatingModeCounter = 25;
    }

    public static int getGhostEatingModeCounter() {
        return ghostEatingModeCounter;
    }

}
