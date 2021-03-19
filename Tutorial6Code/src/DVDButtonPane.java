import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class DVDButtonPane extends Pane {
    private Button add;
    private Button delete;
    private Button stats;
    public DVDButtonPane(){
        Pane  subPane = new Pane();

        //Adding buttons
        add = new Button("Add");
        add.setStyle("-fx-font: 12 arial; -fx-base: rgb(0,150,0); -fx-text-fill: rgb(255,255,255);");
        add.setAlignment(Pos.CENTER);
        add.relocate(0,0);
        add.setPrefSize(90,30);
        delete = new Button("Delete");
        delete.setAlignment(Pos.CENTER);
        delete.setStyle("-fx-font: 12 arial; -fx-base: rgb(150,0,0); -fx-text-fill: rgb(255,255,255);");
        delete.relocate(100,0);
        delete.setPrefSize(90,30);
        stats = new Button("Stats");
        stats.setAlignment(Pos.CENTER);
        stats.setStyle("-fx-font: 12 arial; -fx-base: rgb(255,255,255); -fx-text-fill: rgb(0,0,0);");
        stats.relocate(210,0);
        stats.setPrefSize(90,30);

        //Adding buttons to pane
        subPane.getChildren().addAll(add,delete,stats);
        getChildren().addAll(subPane);
    }

    public Button getAdd(){return add;}
    public Button getDelete(){return delete;}
    public Button getStats(){return stats;}
}
