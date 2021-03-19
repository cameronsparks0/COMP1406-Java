import java.util.Locale;

public class ElectronicStore {
    String name;
    Desktop[] desktops = new Desktop[3];
    Fridge[] fridges = new Fridge[3];
    Laptop[] laptops = new Laptop[3];

    public ElectronicStore(String name){
        this.name = name;
        desktops[0] = new Desktop(3.2,8,200,true);
        desktops[1] = new Desktop(2.7,16,500,false);
        desktops[2] = new Desktop(4.0,4,100,true);
        laptops[0] = new Laptop(2.5,8,350,true,17);
        laptops[1] = new Laptop(2.2,12,500,false,19);
        laptops[2] = new Laptop(3.1,16,700,false,18);
        fridges[0] = new Fridge(20,true,"Red");
        fridges[1] = new Fridge(19,false,"Black");
        fridges[2] = new Fridge(18,false,"Grey");
    }
    public void printStock(){
        for(int x=0;x<3;x++){
            System.out.println(desktops[x].toString());
        }
        for(int x=0;x<3;x++){
            System.out.println(fridges[x].toString());
        }
        for(int x=0;x<3;x++){
            System.out.println(laptops[x].toString());
        }
    }

    public boolean searchStock(String search){
        for(int x=0;x<3;x++){
            String temp = fridges[x].toString().toLowerCase();
            if(temp.contains(search.toLowerCase())){
                return true;
            }
            temp = laptops[x].toString().toLowerCase();
            if(temp.contains(search.toLowerCase())){
                return true;
            }
            temp = desktops[x].toString().toLowerCase();
            if(temp.contains(search.toLowerCase())){
                return true;
            }
        }
        return false;
    }

}
