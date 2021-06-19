package sample;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class View extends Group {
    public final static double CELL_WIDTH = 450/19;

    @FXML
    private int rowCount;
    @FXML private int columnCount;
    private ImageView[][] cellViews;
    private Image pacmanRightImage;
    private Image pacmanUpImage;
    private Image pacmanDownImage;
    private Image pacmanLeftImage;
    private Image ghost1Image;
    private Image ghost2Image;
    private Image blueGhostImage;
    private Image wallImage;
    private Image bigDotImage;
    private Image smallDotImage;



    public View() {
        this.pacmanRightImage = new Image(getClass().getResourceAsStream("/resources/pacmanRight.gif"));
        this.pacmanUpImage = new Image(getClass().getResourceAsStream("/resources/pacmanUp.gif"));
        this.pacmanDownImage = new Image(getClass().getResourceAsStream("/resources/pacmanDown.gif"));
        this.pacmanLeftImage = new Image(getClass().getResourceAsStream("/resources/pacmanLeft.gif"));

        this.ghost1Image = new Image(getClass().getResourceAsStream("/resources/redghost.gif"));
        this.ghost2Image = new Image(getClass().getResourceAsStream("/resources/ghost2.gif"));
        this.blueGhostImage = new Image(getClass().getResourceAsStream("/resources/blueghost.gif"));
        this.wallImage = new Image(getClass().getResourceAsStream("/resources/wall.png"));
        this.bigDotImage = new Image(getClass().getResourceAsStream("/resources/whitedot.png"));
        this.smallDotImage = new Image(getClass().getResourceAsStream("/resources/smalldot.png"));
    }

    private void initializeGrid() {
        if (this.rowCount > 0 && this.columnCount > 0) {
            this.cellViews = new ImageView[this.rowCount][this.columnCount];
            for (int row = 0; row < this.rowCount; row++) {
                for (int column = 0; column < this.columnCount; column++) {
                    ImageView imageView = new ImageView();
                    imageView.setX((double)column * CELL_WIDTH);
                    imageView.setY((double)row * CELL_WIDTH);
                    imageView.setFitWidth(CELL_WIDTH);
                    imageView.setFitHeight(CELL_WIDTH);
                    this.cellViews[row][column] = imageView;
                    this.getChildren().add(imageView);
                }
            }
        }
    }

    public void update(Model model) {
        assert model.getRowCount() == this.rowCount && model.getColumnCount() == this.columnCount;
        for (int row = 0; row < this.rowCount; row++){
            for (int column = 0; column < this.columnCount; column++){
                Model.CellValue value = model.getCellValue(row, column);
                if (value == Model.CellValue.WALL) {
                    this.cellViews[row][column].setImage(this.wallImage);
                }
                else if (value == Model.CellValue.BIGDOT) {
                    this.cellViews[row][column].setImage(this.bigDotImage);
                }
                else if (value == Model.CellValue.SMALLDOT) {
                    this.cellViews[row][column].setImage(this.smallDotImage);
                }
                else {
                    this.cellViews[row][column].setImage(null);
                }
                if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && (model.getLastDirection() == Model.Direction.RIGHT || model.getLastDirection() == Model.Direction.NONE)) {
                    this.cellViews[row][column].setImage(this.pacmanRightImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && model.getLastDirection() == Model.Direction.LEFT) {
                    this.cellViews[row][column].setImage(this.pacmanLeftImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && model.getLastDirection() == Model.Direction.UP) {
                    this.cellViews[row][column].setImage(this.pacmanUpImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && model.getLastDirection() == Model.Direction.DOWN) {
                    this.cellViews[row][column].setImage(this.pacmanDownImage);
                }

                if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && (model.getLastDirection() == Model.Direction.RIGHT || model.getLastDirection() == Model.Direction.NONE)) {
                    this.cellViews[row][column].setImage(this.pacmanRightImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && model.getLastDirection() == Model.Direction.LEFT) {
                    this.cellViews[row][column].setImage(this.pacmanLeftImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && model.getLastDirection() == Model.Direction.UP) {
                    this.cellViews[row][column].setImage(this.pacmanUpImage);
                }
                else if (row == model.getPacmanLocation().getX() && column == model.getPacmanLocation().getY() && model.getLastDirection() == Model.Direction.DOWN) {
                    this.cellViews[row][column].setImage(this.pacmanDownImage);
                }
                if (Model.isGhostEatingMode() && (GamePlayController.getGhostEatingModeCounter() == 6 || GamePlayController.getGhostEatingModeCounter() == 4 || GamePlayController.getGhostEatingModeCounter() == 2)) {
                    if (row == model.getGhost1Location().getX() && column == model.getGhost1Location().getY()) {
                        this.cellViews[row][column].setImage(this.ghost1Image);
                    }
                    if (row == model.getGhost2Location().getX() && column == model.getGhost2Location().getY()) {
                        this.cellViews[row][column].setImage(this.ghost2Image);
                    }

                }
                //display blue ghosts in ghostEatingMode
                else if (Model.isGhostEatingMode()) {
                    if (row == model.getGhost1Location().getX() && column == model.getGhost1Location().getY()) {
                        this.cellViews[row][column].setImage(this.blueGhostImage);
                    }
                    if (row == model.getGhost2Location().getX() && column == model.getGhost2Location().getY()) {
                        this.cellViews[row][column].setImage(this.blueGhostImage);
                    }


                }
                //dispaly regular ghost images otherwise
                else {
                    if (row == model.getGhost1Location().getX() && column == model.getGhost1Location().getY()) {
                        this.cellViews[row][column].setImage(this.ghost1Image);
                    }
                    if (row == model.getGhost2Location().getX() && column == model.getGhost2Location().getY()) {
                        this.cellViews[row][column].setImage(this.ghost2Image);
                    }

                }
            }
        }
    }


    public int getRowCount() {
        return this.rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
        this.initializeGrid();
    }

    public int getColumnCount() {
        return this.columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
        this.initializeGrid();
    }

}
