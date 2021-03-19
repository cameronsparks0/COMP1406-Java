import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;

public class DVDCollectionApp2  extends Application {
    private DVDCollection model;
    private DVD eventDvd;

    public DVDCollectionApp2(){
        model = DVDCollection.example1();
    }
    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the labels
        Label label1 = new Label("DVDs");
        label1.relocate(10,10);
        Label label2 = new Label("Title");
        label2.relocate(10,202);
        Label label3 = new Label("Year");
        label3.relocate(10,242);
        Label label4 = new Label("Length");
        label4.relocate(120,242);

        // Create the TextFields
        TextField tField = new TextField();
        tField.relocate(50,200);
        tField.setPrefSize(500,30);
        TextField yField = new TextField();
        yField.relocate(50,240);
        yField.setPrefSize(55,30);
        TextField lField = new TextField();
        lField.relocate(180,240);
        lField.setPrefSize(45,30);

        // Create the lists
        ListView<DVD>    tList = new ListView<DVD>();
        tList.relocate(10,40);
        tList.setPrefSize(540,150);
        tList.setItems(FXCollections.observableArrayList(model.getDVDList()));

        // Create the buttons
        DVDButtonPane buttonPane = new DVDButtonPane();
        buttonPane.relocate(250,240);
        Button add = buttonPane.getAdd();
        Button delete = buttonPane.getDelete();
        add.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                if(tField.getText().length()>0 && yField.getText().length()>0 && lField.getText().length()>0){
                    eventDvd = new DVD();
                    eventDvd.setTitle(tField.getText());
                    eventDvd.setYear(Integer.parseInt(yField.getText()));
                    eventDvd.setDuration(Integer.parseInt(lField.getText()));
                    model.add(eventDvd);
                    tList.setItems(FXCollections.observableArrayList(model.getDVDList()));
                }
            }
        });

        delete.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                eventDvd = new DVD();
                eventDvd = tList.getSelectionModel().getSelectedItem();
                model.remove(eventDvd.getTitle());
                tList.setItems(FXCollections.observableArrayList(model.getDVDList()));
            }
        });

        tList.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                eventDvd = new DVD();
                if(tList.getSelectionModel().getSelectedItem()!=null){
                    eventDvd = tList.getSelectionModel().getSelectedItem();
                    tField.setText(eventDvd.getTitle());
                    yField.setText(""+eventDvd.getYear());
                    lField.setText(""+eventDvd.getDuration());
                }
            }
        });


        // Add all the components to the window
        aPane.getChildren().addAll(label1, label2, label3, label4,tField,yField,lField,tList,buttonPane);

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane, 560,280));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
