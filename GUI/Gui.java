/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Main.DataUpload;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Thomas
 */
public class Gui extends Application {

    public Stage primaryStage;
    public ListView listView;
    public static TextArea textArea;
    public BufferedWriter BuffWriter;
    private FileWriter fileWriter;

    private VirtualFlow flow;

    private final int WIDTH = 800;
    private final int HEIGHT = 600;
    private final int LIST_MARGIN = 5;
    private final int LIST_PADDING = 2;
    TextField searchTextField;
    int i = 3;

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setTitle("Semestrální práce UIR");
        primaryStage.setScene(createScene());
        primaryStage.show();

    }

    private Scene createScene() throws IOException {
        Scene scene = new Scene(getRoot(), WIDTH, HEIGHT);
        return scene;
    }

    public Parent getRoot() throws IOException {
        BorderPane rootPane = new BorderPane();
        rootPane.setCenter(textArea());
        rootPane.setRight(RightControls());
        rootPane.setTop(menu());
        return rootPane;
    }

    private Node textArea() {
        BorderPane borderPane = new BorderPane();
        textArea = new TextArea();
        textArea.setWrapText(true);
        textArea.setPromptText("The information will appear here.");
        BorderPane.setMargin(textArea, new Insets(LIST_MARGIN, LIST_MARGIN / 2, LIST_MARGIN, LIST_MARGIN));
        borderPane.setCenter(textArea);
        textArea. textProperty().addListener((ObservableValue<?> observable, Object oldValue, Object newValue) -> {
            textArea.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
            //use Double.MIN_VALUE to scroll to the top
        });

        return borderPane;
    }

    private Node AlgorithmBtns() {
        VBox btns = new VBox();
        Button btnNaive = new Button("Naive Bayes");
        Button btnNN = new Button("Nearest neighbour");

        btnNaive.setPrefWidth(150);
        btnNN.setPrefWidth(150);

        btns.getChildren().addAll(btnNaive, btnNN);
        btns.setPadding(new Insets(LIST_MARGIN));
        return btns;
    }

    private Node ControlBtns() {
        VBox btns = new VBox();
        Button btnUpload = new Button("Upload");
        btnUpload.setOnAction(event -> {
            textArea.setText(textArea.getText() +"Loading files... \n");
            try {

                DataUpload.readFile();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            textArea.setText(textArea.getText() + "DONE");
            Gui.textArea.appendText("");
        });
        Button btnXXX = new Button("XXX");

        btnUpload.setPrefWidth(150);
        btnXXX.setPrefWidth(150);

        btns.getChildren().addAll(btnUpload, btnXXX);
        btns.setPadding(new Insets(50, LIST_MARGIN, LIST_MARGIN, LIST_MARGIN));
        return btns;
    }

    private Node RightControls() {
        VBox controls = new VBox();
        controls.getChildren().addAll(AlgorithmBtns(), ControlBtns());
        return controls;
    }

    private void SaveFile(String content, File file) {
        try {

            fileWriter = new FileWriter(file);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private Node menu() {

        MenuBar bar = new MenuBar();
        searchTextField = new TextField();

        Menu text = new Menu("_File");
        MenuItem textitem1 = new MenuItem("E_xit");
        //textitem1.setOnAction(event -> Platform.exit());

        MenuItem textitem2 = new MenuItem("_Open File");
        //textitem2.setOnAction(event -> data.uploadText());

        MenuItem textItem3 = new MenuItem("_Save File");
        //textItem3.setOnAction(event -> Platform.exit());

        MenuItem textItem4 = new MenuItem("_Load File/s");
        textItem4.setOnAction(event -> {
            textArea.setText("Loading files... \n");
            try {

                DataUpload.readFile();
            } catch (Exception ex) {
                Logger.getLogger(Gui.class.getName()).log(Level.SEVERE, null, ex);
            }
            textArea.setText(textArea.getText() + "DONE");
        });

        text.getItems().addAll(textitem2, textItem3, textitem1, textItem4);

        Menu dic = new Menu("_Dictionary");
        MenuItem dicItem1 = new MenuItem("_Open");
        //icItem1.setOnAction(event -> data.uploadDictionary());

        MenuItem dicItem2 = new MenuItem("_Save");
        //dicItem1.setOnAction(event -> );

        dic.getItems().addAll(dicItem1, dicItem2);

        bar.getMenus().addAll(text, dic);

        return bar;
    }

}
