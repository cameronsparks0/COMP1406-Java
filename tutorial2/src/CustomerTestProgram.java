public class CustomerTestProgram {
    public static void main(String args[]){
        Customer c,c2;

        c = new Customer("Bob");
        c2 = new Customer("Jim");
        c.name = "Bob";
        c.age = 27;
        c.money = 50;
        c2.name = "Jim";
        c2.age = 18;
        c2.money = 25;
        System.out.println(c.name);
        System.out.println(c.age);
        System.out.println(c.money);
        System.out.println(c2.name);
        System.out.println(c2.age);
        System.out.println(c2.money);
    }
}
