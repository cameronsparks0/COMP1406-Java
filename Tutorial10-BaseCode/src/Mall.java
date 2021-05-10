public class Mall {
    public static final int MAX_STORES = 100;
    private String   name;
    private Store[]  stores;
    private int      storeCount;

    public Mall(String n) {
        name = n;
        stores = new Store[MAX_STORES];
        storeCount = 0;
    }

    public void addStore(Store s) {
        if (storeCount < MAX_STORES) {
            stores[storeCount++] = s;
        }
    }
       
    public int getUniqueCustomerCount(){
      // First determine how many customers are in the stores
        int total = 0;
        for (int i=0; i<storeCount; i++) {
            total += stores[i].getCustomerCount();
        }

        // Now go through and add all the customers to an array, as long as they are not already in there
        // Keep track of unique ones
        int unique = 0;
        Customer[]      uniqueCustomers = new Customer[total];

        for (int i=0; i<storeCount; i++) {
            Customer[]   customersInStore = stores[i].getCustomers();
            int          numInStore = stores[i].getCustomerCount();
            for (int j=0; j<numInStore; j++) {
                boolean found = false;
                for(int k=0; k<unique; k++) {
                    if (customersInStore[j] == uniqueCustomers[k])
                        found = true;
                }
                if (!found) {
                    uniqueCustomers[unique] = customersInStore[j];
                    unique++;
                }

            }
        }
        return unique;
    }
    
    public boolean shoppedAtSameStore(Customer c1, Customer c2){
      for(int i = 0; i < storeCount; i++){
       Customer[] temp = stores[i].getCustomers();
       
      boolean didC1 = false;
      boolean didC2 = false;
      for(int j = 0; j < temp.length; j++){
        if(temp[j] == c1){
          didC1 = true;
        }
        if(temp[j] == c2){
          didC2 = true;
        }
        
      }
      if(didC1 && didC2){
          return true;
        }
     
    }
       return false;
    }
}
