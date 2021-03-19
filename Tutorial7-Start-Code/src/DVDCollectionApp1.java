import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.event.*;
import javafx.scene.input.MouseEvent;

import java.net.Inet4Address;
public class DVDCollectionApp1  extends Application {
    private DVDCollection model;
    public DVDCollectionApp1() {
      model = DVDCollection.example1();
    }

    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the view
        DVDCollectionAppView1 view = new DVDCollectionAppView1();

        view.getButtonPane().getAddButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                String dvdTitle = javax.swing.JOptionPane.showInputDialog("Please enter the DVD Title: ");
                String dvdYear = javax.swing.JOptionPane.showInputDialog("Please enter the DVD Year: ");
                String dvdLength = javax.swing.JOptionPane.showInputDialog("Please enter the DVD Length: ");
                try{
                    DVD newDvd = new DVD();
                    newDvd.setTitle(dvdTitle);
                    newDvd.setYear(Integer.parseInt(dvdYear));
                    newDvd.setDuration(Integer.parseInt(dvdLength));
                    model.add(newDvd);
                    view.update(model,0);
                } catch (NumberFormatException e){

                }
            }
        });

        view.getButtonPane().getDeleteButton().setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent actionEvent) {
                model.remove(view.getTitleList().getSelectionModel().getSelectedItem());
                view.update(model,0);
            }
        });

        view.getTitleList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.update(model,view.getTitleList().getSelectionModel().getSelectedIndex());
            }
        });

        view.getYearList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.update(model,view.getYearList().getSelectionModel().getSelectedIndex());
            }
        });

        view.getLengthList().setOnMousePressed(new EventHandler<MouseEvent>()
        {
            public void handle(MouseEvent mouseEvent) {
                view.update(model,view.getLengthList().getSelectionModel().getSelectedIndex());
            }
        });


        aPane.getChildren().add(view);

        primaryStage.setTitle("My DVD Collection");
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(aPane));
        primaryStage.show();

        view.update(model,0);
    }



    public static void main(String[] args) {
        launch(args);
    }
}