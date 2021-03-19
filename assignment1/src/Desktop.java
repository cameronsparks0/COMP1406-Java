public class Desktop {
    double cpuSpeed;
    int ram;
    int storage;
    boolean storageTypeBool;
    String storageType;

    public Desktop(double cpuSpeed, int ram, int storage, boolean storageTypeBool){
        this.cpuSpeed = cpuSpeed;
        this.ram=ram;
        this.storage=storage;
        this.storageTypeBool=storageTypeBool;
        if(!this.storageTypeBool){
            storageType="HDD";
        }
        else{
            storageType="SSD";
        }
    }

    public String toString(){
        return ("Desktop PC with " +cpuSpeed+"ghz CPU, "+ram+"GB RAM, "+storage+"GB "+storageType+" drive.");
    }
}
