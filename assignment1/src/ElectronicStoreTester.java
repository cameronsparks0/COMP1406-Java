import java.util.*;
public class ElectronicStoreTester {
    public static void main(String[] args){
        ElectronicStore store = new ElectronicStore("Test Store");
        System.out.println("Welcome to "+store.name+"!\nThis store includes:");
        store.printStock();
        boolean quit = false;
        Scanner usrInput = new Scanner(System.in);
        String usrInputString;
        while(!quit){
            System.out.print("Enter a term to search for: ");
            usrInputString = usrInput.nextLine();
            if(store.searchStock(usrInputString)){
                System.out.println("A matching item is contained in the store's stock.");
            }
            else if(usrInputString.toLowerCase().equals("quit")){
                quit = true;
            }
            else{
                System.out.println("No items in the store's stock match that term.");
            }
        }
    }
}
