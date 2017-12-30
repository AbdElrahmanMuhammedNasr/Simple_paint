package z.zproject;
import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Slider;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

public class FXMLDocumentController implements Initializable {

    @FXML
    JFXComboBox<?> shape;

    @FXML
    JFXComboBox<?> comboSize;

    @FXML
    GraphicsContext gc;

    @FXML
    Canvas ca;

    @FXML
    JFXColorPicker color;

    @FXML
    Slider sliderLocation;

    public void easerL() {
        gc = ca.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, 1360, 700);
    }

    double size;
    double location;

    @FXML
    void getLocation() {
        location = sliderLocation.getValue();
    }

    public void shapeT() {

        String s = shape.getSelectionModel().getSelectedItem().toString();
        size = Integer.parseInt(comboSize.getSelectionModel().getSelectedItem().toString());

        if ("Circle".equals(s)) {
            gc = ca.getGraphicsContext2D();
            gc.setFill(color.getValue());
            gc.fillOval(location, location, size, size);
        } else if ("Rectangle".equals(s)) {
            gc = ca.getGraphicsContext2D();
            gc.setFill(color.getValue());
            gc.fillRect(location, location, size, size);

        } else if ("Arc".equals(s)) {
            gc = ca.getGraphicsContext2D();
            gc.setFill(color.getValue());
            gc.fillArc(size, size, size, size, 90, 135, ArcType.OPEN);
        }

    }

    public void dragged() {
        gc = ca.getGraphicsContext2D();
        ca.setOnMouseDragged(e -> {
            gc.lineTo(e.getSceneX(), e.getSceneY());
            gc.setStroke(color.getValue());
            gc.stroke();

        });

    }

    public void press() {
        gc = ca.getGraphicsContext2D();
        ca.setOnMousePressed(e -> {
            gc.beginPath();
            gc.lineTo(e.getSceneX(), e.getSceneY());
            gc.setStroke(color.getValue());
            gc.stroke();

        });

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList list = FXCollections.observableArrayList(" ", "Circle", "Rectangle", "Arc");
        shape.setItems(list);

        ObservableList list2 = FXCollections.observableArrayList("10", "20", "30", "40", "50", "60", "70", "80", "90", "100", "110", "120", "130", "140", "150");
        comboSize.setItems(list2);

        color.setValue(Color.BLACK);

    }

}
