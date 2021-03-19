import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.ListView;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;

public class DVDCollectionApp  extends Application {
    public void start(Stage primaryStage) {
        Pane  aPane = new Pane();

        // Create the labels
        Label movieTitle = new Label("Title");
        movieTitle.relocate(10,10);
        Label yearTitle = new Label("Year");
        yearTitle.relocate(220,10);
        Label lengthTitle = new Label("Length");
        lengthTitle.relocate(290,10);
        // ... ADD CODE HERE ... //

        // Create the lists
        String[]    titles = {"Star Wars", "Java is cool", "Mary Poppins", "The Green Mile"};
        String[]    years = {"1978", "2002", "1968", "1999"};
        String[]    lengths = {"124", "93", "126", "148"};
        ListView<String> movieList = new ListView<String>();
        movieList.setItems(FXCollections.observableArrayList(titles));
        movieList.relocate(10, 30);
        movieList.setPrefSize(200, 150);
        ListView<String> yearList = new ListView<String>();
        yearList.setItems(FXCollections.observableArrayList(years));
        yearList.relocate(220, 30);
        yearList.setPrefSize(60, 150);
        ListView<String> lengthList = new ListView<String>();
        lengthList.setItems(FXCollections.observableArrayList(lengths));
        lengthList.relocate(290, 30);
        lengthList.setPrefSize(60, 150);

        // ... ADD CODE HERE ... //

        // Create the buttons
        // The following code shows how to set the font,
        // background color and text color of a button:
        // b.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,100,0); " +
        //     "-fx-text-fill: rgb(255,255,255);");
        //the 3 rgb values represent the red/green/blue values for the color your want
        Button add = new Button("Add");
        add.setAlignment(Pos.CENTER);
        add.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,150,0); -fx-text-fill: rgb(255,255,255);");
        add.relocate(10,190);
        add.setPrefSize(95,30);
        Button delete = new Button("Delete");
        delete.setAlignment(Pos.CENTER);
        delete.setStyle("-fx-font: 12 arial; -fx-base: rgb(150,0,0); -fx-text-fill: rgb(255,255,255);");
        delete.relocate(115,190);
        delete.setPrefSize(95,30);
        Button stats = new Button("Stats");
        stats.setAlignment(Pos.CENTER);
        stats.setStyle("-fx-font: 12 arial; -fx-base: rgb(255,255,255); -fx-text-fill: rgb(0,0,0);");
        stats.relocate(290,190);
        stats.setPrefSize(60,30);

        // ... ADD CODE HERE ... //

        // Don't forget to add the components to the window, set the title,
        // make it non-resizable, set Scene dimensions and then show the stage
        aPane.getChildren().addAll(movieTitle,yearTitle,lengthTitle,movieList,yearList,lengthList,add,delete,stats);
        primaryStage.setTitle("My DVD Collection");
        primaryStage.setScene(new Scene(aPane,360,240));
        primaryStage.setResizable(false);
        primaryStage.show();
        // ... ADD CODE HERE ... //
    }

    public static void main(String[] args) {
        launch(args);
    }
}
