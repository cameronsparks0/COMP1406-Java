public class Laptop {
    double cpuSpeed;
    int ram;
    int storage;
    boolean storageTypeBool;
    String storageType;
    int screenSize;

    public Laptop(double cpuSpeed, int ram, int storage, boolean storageTypeBool, int screenSize){
        this.cpuSpeed = cpuSpeed;
        this.ram=ram;
        this.storage=storage;
        this.storageTypeBool=storageTypeBool;
        this.screenSize = screenSize;
        if(!this.storageTypeBool){
            storageType="HDD";
        }
        else{
            storageType="SSD";
        }
    }

    public String toString(){
        return (screenSize+"\" Laptop PC with " +cpuSpeed+"ghz CPU, "+ram+"GB RAM, "+storage+"GB "+storageType+" drive.");
    }
}
