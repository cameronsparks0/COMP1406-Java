import java.io.*;

public class CustomerTestProgram {
    public static void main(String args[]) {
        Customer c1 = new Customer("Amie", 14, 100);
        Customer c2 = new Customer("Brad", 15, 0);
        try {
            PrintWriter out;
            out = new PrintWriter(new FileWriter("customer1.txt"));
            c1.saveTo(out);
            out.close();
            out = new PrintWriter(new FileWriter("customer2.txt"));
            c2.saveTo(out);
            out.close();
        } catch (FileNotFoundException e) {
            // Do Nothing
        } catch (IOException e) {
            // Do Nothing
        }

        try {
            BufferedReader in;
            in = new BufferedReader(new FileReader("customer1.txt"));
            System.out.println(c1.readFrom(in));
            in.close();
            in = new BufferedReader(new FileReader("customer2.txt"));
            System.out.println(c1.readFrom(in));
            in.close();
        } catch (FileNotFoundException e) {
            // Do Nothing
        } catch (IOException e) {
            // Do Nothing
        }

    }
}
