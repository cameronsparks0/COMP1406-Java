import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

public class ElectronicStoreView extends Pane {
    private TextField numSalesText, revenueText, avgRevenueText; //Creating all the different viewable modules.
    private Button resetStore, addToCart, removeFromCart, completeSale;
    private ListView<String> storeStockView, currentCartView, popularItemsView;
    private Label currentCartLabel;

    public Button getResetStore(){return resetStore;}
    public Button getAddToCart(){return addToCart;}
    public Button getRemoveFromCart(){return removeFromCart;}
    public Button getCompleteSale(){return completeSale;}

    public ListView<String> getStoreStockView() { return storeStockView;}
    public ListView<String> getCurrentCartView() { return currentCartView;}
    public ListView<String> getPopularItemsView() {return  popularItemsView;}


    public ElectronicStoreView(){

        //Text Fields
        numSalesText = new TextField("0"); //Initializing all the different viewable modules (Also setting size and position).
        numSalesText.relocate(80,40);
        numSalesText.setPrefSize(90,25);

        revenueText = new TextField("0.00");
        revenueText.relocate(80,68);
        revenueText.setPrefSize(90,25);

        avgRevenueText = new TextField("N/A");
        avgRevenueText.relocate(80,96);
        avgRevenueText.setPrefSize(90,25);

        //Labels
        Label storeSummaryLabel = new Label("Store Summary:");
        storeSummaryLabel.relocate(50,20);

        Label numSalesLabel = new Label("# Sales:");
        numSalesLabel.relocate(35,45);

        Label revenueLabel = new Label("Revenue:");
        revenueLabel.relocate(27,72);

        Label avgRevenueLabel = new Label("$ / Sale:");
        avgRevenueLabel.relocate(33,99);

        Label storeStockLabel = new Label("Store Stock:");
        storeStockLabel.relocate(285,20);

        currentCartLabel = new Label("Current Cart ($0.00):");
        currentCartLabel.relocate(590,20);

        Label mostPopularItemsLabel = new Label("Most Popular Items:");
        mostPopularItemsLabel.relocate(40,135);

        //Lists
        storeStockView = new ListView<String>();
        storeStockView.relocate(180,40);
        storeStockView.setPrefSize(290,300);

        currentCartView = new ListView<String>();
        currentCartView.relocate(478,40);
        currentCartView.setPrefSize(290,300);

        popularItemsView = new ListView<String>();
        popularItemsView.relocate(10,160);
        popularItemsView.setPrefSize(160,180);

        //Buttons
        resetStore = new Button("Reset Store");
        resetStore.setStyle("-fx-font: 12 arial;");
        resetStore.relocate(20,345);
        resetStore.setPrefSize(130,40);

        addToCart = new Button("Add to Cart");
        addToCart.setStyle("-fx-font: 12 arial;");
        addToCart.relocate(250,345);
        addToCart.setPrefSize(130,40);
        addToCart.setDisable(true);

        removeFromCart = new Button("Remove from Cart");
        removeFromCart.setStyle("-fx-font: 12 arial;");
        removeFromCart.relocate(478,345);
        removeFromCart.setPrefSize(130,40);
        removeFromCart.setDisable(true);

        completeSale = new Button("Complete Sale");
        completeSale.setStyle("-fx-font: 12 arial;");
        completeSale.relocate(608,345);
        completeSale.setPrefSize(130,40);
        completeSale.setDisable(true);

        //Appending all viewable modules to pane
        getChildren().addAll(numSalesText, revenueText, avgRevenueText,storeSummaryLabel,numSalesLabel,revenueLabel,avgRevenueLabel,storeStockView, currentCartView, popularItemsView,storeStockLabel,currentCartLabel,mostPopularItemsLabel, resetStore, addToCart, removeFromCart, completeSale);
        setPrefSize(800,400);
    }

    public void update(ElectronicStore model){ //Base update method that updates some of the viewable modules based on how the model has changed (Which of its attributes changed).

        storeStockView.setItems(FXCollections.observableArrayList(model.getProductNames())); // Updating the ListViews and text-fields based on certain attributes of the model.
        popularItemsView.setItems(FXCollections.observableArrayList(model.getMostPopular()));
        numSalesText.setText(""+model.getNumSales());
        revenueText.setText(String.format("%.2f",model.getRevenue()));
        if(model.getNumSales()>0){ // Making sure nothing is being divided by 0.
            avgRevenueText.setText(String.format("%.2f",model.getRevenue()/model.getNumSales()));
        }
        else{
            avgRevenueText.setText("N/A");
        }
        currentCartView.setItems(FXCollections.observableArrayList(model.getCartNames()));

        if(storeStockView.getSelectionModel().getSelectedItem()!=null){ // Checking to make sure an option is selected within a list view
            addToCart.setDisable(false); //If an option is selected let the user click the button
        }
        else{
            addToCart.setDisable(true);//If the option is not selected, do not let the user click the button.
        }

        if(currentCartView.getSelectionModel().getSelectedItem()!=null){
            removeFromCart.setDisable(false);
        }
        else{
            removeFromCart.setDisable(true);
        }

        completeSale.setDisable(true);
        for(Product p: model.getCartItems()){ // Making sure there are items in the cart for the complete sale button to activate.
            if(p.getSoldQuantity()!=0){
                completeSale.setDisable(false);
            }
        }
        currentCartLabel.setText(String.format("%s%.2f%s","Current Cart ($",model.getCartPrice(),")")); //Updating the total price of the cart.
    }

    public void update(ElectronicStore model, String selectedItem,boolean addingToCart){ //Update method that is used my the addButton and removeButton events to update the current cartItems and stockItems.
        if(addingToCart){ // If the button clicked is the addButton
            int itemIndex=0;
            for(Product p: model.getStock()){ //Finding the index position of the selected item in terms of the entire stock.
                if(p.toString().equals(selectedItem)){
                    break;
                }
                else{
                    itemIndex = itemIndex + 1;
                }
            }
            model.sellProducts(itemIndex,1); // sellProducts has been changed to simply decrease the stock value and increase the soldQuantity of that particular item.

            if(!model.getCartItems().contains(model.getStock()[itemIndex])){ // Adding the item to the cart if its not already there.
                model.getCartItems().add(model.getStock()[itemIndex]);
            }
        }
        else{ // If the button clicked is the remove button
            int itemIndex=0;
            for(Product p: model.getStock()){
                if(selectedItem.contains(p.toString())){ // Because the selected item in the cart listView will have the amount next to it (3 x ....) it cannot be directly be compared to what is in the stock, therefore if one of the stock's strings is whithin it then it must be the same.
                    break;
                }
                else{
                    itemIndex = itemIndex + 1;
                }
            }
            model.getStock()[itemIndex].buyUnits(); //Incrementing stock quantity and decrementing sold quantity (Essentially the store buying the item back out of the cart).
        }
        update(model); // Calling the base update method to fill all the viewable modules with an update model.
    }

}
