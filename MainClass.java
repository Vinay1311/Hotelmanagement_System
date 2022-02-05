import java.sql.Statement;
import java.util.Scanner;

public class MainClass {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Statement statement = databaseConnection.databaseConnection();
        MainClass mainClass = new MainClass();
        mainClass.init(scan, statement);

    }

    public static void init(Scanner scan, Statement statement) {
        System.out.println(".......--> Hotel Management System <--......." + "\n");

        System.out.println("Please Select Option ;" + "\n");

        System.out.println("1. Staff Management");
        System.out.println("2. Room Management");
        System.out.println("3. Food Management");
        System.out.println("4. Vehicle Management");

        switch (scan.nextInt()) {
            case 1: {
                StaffManagement.init1(scan, statement);
                break;
            }
            case 2: {
                RoomManagement.init1(scan, statement);
                break;
            }
            case 3: {
                FoodManagement.init1(scan, statement);
                break;
            }
            case 4: {
                VehicleManagement.init1(scan, statement);
                break;
            }
            default: {
                break;
            }
        }
    }

}



