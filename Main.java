import java.util.ArrayList;
import java.util.Scanner;

// ১. ABSTRACTION
abstract class Vehicle {
    private String modelName; // ২. ENCAPSULATION
    private double dailyRate;

    public Vehicle(String modelName, double dailyRate) {
        this.modelName = modelName;
        this.dailyRate = dailyRate;
    }

    public String getModelName() { return modelName; }
    public double getDailyRate() { return dailyRate; }

    abstract double calculateTotalBill(int days);
}

// ৩. INHERITANCE
class Car extends Vehicle {
    public Car(String modelName, double rate) {
        super(modelName, rate);
    }

    @Override
    double calculateTotalBill(int days) {
        double total = getDailyRate() * days;
        if (days > 5) total *= 0.90; // ৫ দিনের বেশি হলে ১০% ডিসকাউন্ট
        return total + 500; // সার্ভিস চার্জ
    }
}

class Bike extends Vehicle {
    public Bike(String modelName) {
        super(modelName, 400.0);
    }

    @Override
    double calculateTotalBill(int days) {
        double total = getDailyRate() * days;
        if (days > 3) total *= 0.95; // ৩ দিনের বেশি হলে ৫% ডিসকাউন্ট
        return total;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        // গাড়ির লিস্ট তৈরি (Multiple Options)
        ArrayList<Vehicle> garage = new ArrayList<>();
        garage.add(new Car("Toyota Corolla", 1200));
        garage.add(new Car("BMW M4", 5000));
        garage.add(new Car("Audi A6", 4500));
        garage.add(new Bike("Yamaha R15"));
        garage.add(new Bike("Suzuki Gixxer"));

        System.out.println("========= MAMA RENTAL GARAGE =========");
        System.out.println("Available Vehicles:");
        
        // সব গাড়ি প্রিন্ট করা
        for (int i = 0; i < garage.size(); i++) {
            System.out.println((i + 1) + ". " + garage.get(i).getModelName() + " (" + garage.get(i).getDailyRate() + " TK/day)");
        }

        System.out.print("\nSelect a vehicle (1-" + garage.size() + "): ");
        int choice = input.nextInt();

        // ইনপুট ভ্যালিডেশন
        if (choice < 1 || choice > garage.size()) {
            System.out.println("Invalid choice, Mama!");
            return;
        }

        System.out.print("Enter rental days: ");
        int days = input.nextInt();

        // ৪. POLYMORPHISM: রান টাইমে ডিসাইড হবে কোন calculateTotalBill কল হবে
        Vehicle selected = garage.get(choice - 1);

        System.out.println("\n----------- RENTAL INVOICE -----------");
        System.out.println("Vehicle : " + selected.getModelName());
        System.out.println("Days    : " + days);
        System.out.println("Total   : " + selected.calculateTotalBill(days) + " TK");
        System.out.println("--------------------------------------");
        
        input.close();
    }
}