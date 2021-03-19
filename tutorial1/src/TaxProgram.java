import java.util.Scanner;
public class TaxProgram {
    public static void main(String args[]) {
        double income, fedTax, provTax, base, deductions, totalTax;
        int dependents;

        Scanner input = new Scanner(System.in);
        System.out.print("Please enter your taxable income: ");
        income = input.nextDouble();
        System.out.print("Please enter your number of dependents: ");
        dependents = input.nextInt();
        System.out.println();
        fedTax = 0.0;
        provTax = 0.0;
        System.out.println("Here is your tax breakdown:");
        System.out.println();
        if(income <= 29590){
            fedTax = 0.17 * income;
        }
        else if (income >= 29590.01 && income <= 59179.99) {
            fedTax = (0.17 * 29590) + (0.26 * (income - 29590));
        }
        else if (income>=59180){
            fedTax = (0.17 * 29590) + (0.26*29590) + (0.29 * (income-59180));
        }
        base = 0.425 * fedTax;
        deductions = 160.50 + (328*dependents);

        if(base>=deductions){
            provTax = base - deductions;
        }
        totalTax = fedTax + provTax;


        System.out.println(String.format("%-14s", "Income") + String.format("%14s", "$"+String.format("%,1.2f",income)));
        System.out.println(String.format("%-14s", "Dependants") + String.format("%14d", dependents));
        System.out.println("----------------------------");
        System.out.println(String.format("%-14s", "Federal Tax") + String.format("%14s", "$"+String.format("%,1.2f",fedTax)));
        System.out.println(String.format("%-14s", "Provincial Tax") + String.format("%14s", "$"+String.format("%,1.2f",provTax)));
        System.out.println("============================");
        System.out.println(String.format("%-14s", "Total Tax") + String.format("%14s", "$"+String.format("%,1.2f",totalTax)));


    }
}