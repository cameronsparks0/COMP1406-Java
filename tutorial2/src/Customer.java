public class Customer {
    String name;
    int age;
    float money;
    boolean admitted;

    public Customer(){
        name = "";
        age =0;
        money = 0.0f;
        admitted = false;
    }

    public Customer(String initName){
        name = initName;
        age =0;
        money = 0.0f;
        admitted = false;
    }

    public Customer(String initName, int initAge){
        name = initName;
        age = initAge;
        money = 0.0f;
        admitted = false;
    }

    public Customer(String initName, int initAge, float initMoney){
        name = initName;
        age =initAge;
        money = initMoney;
        admitted = false;
    }

    public float computeFee(){
        if(age>=18){
            if(age>=65){
                return (12.75f*(0.50f));
            }
            else{
                return 12.75f;
            }
        }
        else if(age>=4){
            return 8.50f;
        }
        else {
            return 0.0f;
        }
    }

    public boolean spend(float amount){
        if(amount>=this.computeFee() && amount<=this.money){
            this.money = this.money - amount;
            return true;
        }
        return false;
    }

    public boolean hasMoreMoneyThan(Customer c){
        if(this.money>c.money){
            return true;
        }
        return false;
    }

    public void payAdmission(){
        if(this.spend(this.computeFee())){
            admitted = true;
        }
    }

    public String toString() {
        if(admitted){
            return "Customer "+this.name+": "+"a "+this.age+" year old with $"+this.money+" who has been admitted";
        }
        else{
            return "Customer "+this.name+": "+"a "+this.age+" year old with $"+this.money+" who has not been admitted";
        }
    }
}
