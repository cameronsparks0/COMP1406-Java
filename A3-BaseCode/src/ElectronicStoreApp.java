import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


public class ElectronicStoreApp extends Application {
    private ElectronicStore model;
    private ElectronicStoreView view;

    public ElectronicStoreApp(){
        model = ElectronicStore.createStore();
        view = new ElectronicStoreView();
    }

    public void start(Stage primaryStage){
        Pane mainPane = new Pane();

        view.setOnMousePressed(new EventHandler<MouseEvent>() { // Creating all the different events to handle the different possible user interactions.
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getStoreStockView().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getCurrentCartView().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getPopularItemsView().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                view.update(model);
            }
        });

        view.getAddToCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(view.getStoreStockView().getSelectionModel().getSelectedItem()!=null){ //Making sure something from the Stock is selected before the user is able to click the add button.
                    view.update(model, view.getStoreStockView().getSelectionModel().getSelectedItem(),true);
                    view.getStoreStockView().getSelectionModel().select(-1);
                    view.update(model);
                }
                else {
                    view.getAddToCart().setDisable(true);
                }
            }
        });

        view.getRemoveFromCart().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                if(view.getCurrentCartView().getSelectionModel().getSelectedItem()!=null){ //Making sure something from the Cart is selected before the user is able to click the remove button.
                    view.update(model, view.getCurrentCartView().getSelectionModel().getSelectedItem(),false);
                }
                else{
                    view.getRemoveFromCart().setDisable(true);
                }
            }
        });

        view.getCompleteSale().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model.incrementNumSales(); //Using different methods within the model to finalize its values after a purchase.
                model.calculateRevenue();
                model.completeTransaction();
                view.update(model);
            }
        });

        view.getResetStore().setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent mouseEvent) {
                model = ElectronicStore.createStore();
                view.update(model);
            }
        });

        mainPane.getChildren().addAll(view);
        primaryStage.setTitle(model.getName());
        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.show();
        view.update(model);
    }
}
