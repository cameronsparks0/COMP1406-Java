import java.util.Scanner;

public class ElectronicStore {
    private final int MAX_PRODUCTS=10;
    private String name;
    private double revenue;
    private int productCount;
    private Product[] appliances = new Product[MAX_PRODUCTS];

    public ElectronicStore(String name){
        this.name = name;
        this.productCount=0;
        this.revenue=0;
    }

    public String getName(){return this.name;}

    public boolean addProduct(Product p){
        if(productCount!=MAX_PRODUCTS){
            appliances[productCount]= p;
            productCount = productCount +1;
            return true;
        }
        else{
            return false;
        }
    }

    public boolean sellProducts(){
        printStock();
        Scanner usrInput = new Scanner(System.in);
        System.out.print("Enter item:");
        int item = usrInput.nextInt();
        System.out.print("Enter Amount:");
        int amount = usrInput.nextInt();
        return sellProducts(item,amount);
    }

    public boolean sellProducts(int item, int amount){
        if(item < appliances.length && item >=0 && appliances[item]!=null){
            double sellAmount = appliances[item].sellUnits(amount);
            if(sellAmount!=-1.0){
                revenue = revenue + sellAmount;
                return true;
            }
            return false;
        }
            return false;
    }

    public double getRevenue(){
        return revenue;
    }

    public void printStock(){
        for(int x=0;x<productCount;x++){
            System.out.println(x+". "+appliances[x].toString());
        }
    }
}
