import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class VehicleManagement {
    public static void main(String[] args) {
        try {
            Scanner scan = new Scanner(System.in);
            DatabaseConnection databaseConnection = new DatabaseConnection();
            Statement statement = databaseConnection.databaseConnection();
            VehicleManagement vehicleManagement = new VehicleManagement();
            vehicleManagement.init1(scan, statement);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void init1(Scanner scan,Statement statement){
        System.out.println("...............Vehicle Management...............\n");
        System.out.println("Please Select An Option"+"\n");
        System.out.println("1.Add Vehicle ");
        System.out.println("2.Update Vehicle Details");
        System.out.println("3.Remove Vehicle Details");
        System.out.println("4.Vehicle Status");
        System.out.println("5.Show Vehicle Details");
        System.out.println("6.Back");

        switch (scan.nextInt()){
            case 1:{
                addVehicle(scan,statement);
                break;
            }
            case 2:{
               updateVehicle(scan,statement);
                break;
            }
            case 3:{
                removeVehicle(scan,statement);
                break;
            }
            case 4:{

                break;
            }
            case 5:{
                showVehicleDetails(scan,statement);
                break;
            }
            case 6:{
                existmessage(scan, statement);
                break;
            }
            default:{
                break;
            }
        }

    }

    public static void addVehicle(Scanner scan,Statement statement){
        System.out.println("Enter Vehicle Type");
        String vehicle_name = scan.next();

        System.out.println("Enter Vehicle No.");
        String vehicle_no = scan.next();

        System.out.println("Enter Vehicle Status (Occupied / Vacant)");
        String vehicle_status = scan.next();

        try {
            int result = statement.executeUpdate(
                    "insert into vehicle (veh_name,veh_no,veh_status) values(" +
                            "'"+vehicle_name+"',"+
                            "'"+vehicle_no+"',"+
                            "'"+vehicle_status+"'"+
                            ")");
            if (result > 0)
                System.out.println("successfully inserted");
            else
                System.out.println("unsuccessful insertion ");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        existmessage(scan,statement);
    }

    public static void updateVehicle(Scanner scan,Statement statement){
        int vehicle_id;
        String vehicle_name,vehicle_no,vehicle_status ;

        System.out.println("..........Update Vehicle Details..........");

        System.out.println("\nWarning!! You want to update Vehicle data from Data Base");
        System.out.println("Press 1 to Continue");
        System.out.println("Press 2 to Exit");

        switch(scan.nextInt()){
            case 1:{
                break;

            }
            case 2:{
                init1(scan,statement);
                break;
            }
            default:
                break;
        }

        try{
            ResultSet rs=statement.executeQuery("select * from vehicle");
            while(rs.next()) {
                System.out.println("Vehicle ID. : " + rs.getInt(1));
                System.out.println("Vehicle Name : " + rs.getString(2));
                System.out.println("Vehicle No. : " + rs.getString(3));
                System.out.println("Vehicle Status: " + rs.getString(4) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Enter Vehicle ID");
        vehicle_id = scan.nextInt();


        System.out.println("Please Select What Details You Want to Update");
        System.out.println("1.Vehicle Name");
        System.out.println("2.Vehicle No ");
        System.out.println("3.Vehicle Status");


        try {
            switch (scan.nextInt()) {
                case 1: {
                    System.out.println("Enter New Vehicle Name");
                    vehicle_name = scan.next();
                    String sql = "UPDATE vehicle " + "SET veh_name = '" + vehicle_name + "' WHERE veh_id  in (" + vehicle_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showVehicleDetails(scan, statement);
                    break;
                }
                case 2: {
                    System.out.println("Enter New Vehicle no");
                    vehicle_no  = scan.next();
                    String sql = "UPDATE vehicle " + "SET veh_no = '" + vehicle_no + "' WHERE veh_id in (" + vehicle_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showVehicleDetails(scan, statement);
                    break;
                }
                case 3: {
                    System.out.println("Enter New Vehicle Status");
                    vehicle_status = scan.next();
                    String sql = "UPDATE vehicle " + "SET veh_status = '" + vehicle_status + "' WHERE veh_id in (" + vehicle_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showVehicleDetails(scan, statement);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        existmessage(scan, statement);


    }

    public static void removeVehicle(Scanner scan,Statement statement) {


        System.out.println("..........Remove Vehicle Details..........");

        System.out.println("\nWarning!! You want to remove Vehicle data from Data Base");
        System.out.println("Press 1 to Continue");
        System.out.println("Press 2 to Exit");

        switch(scan.nextInt()){
            case 1:{
                break;

            }
            case 2:{
                init1(scan,statement);
                break;
            }
            default:
                break;
        }

        try{
            ResultSet rs=statement.executeQuery("select * from vehicle");
            while(rs.next()) {
                System.out.println("Vehicle ID: " + rs.getInt(1));
                System.out.println("Vehicle Name : " + rs.getString(2));
                System.out.println("Vehicle No. " + rs.getString(3));
                System.out.println("Vehicle Status " + rs.getString(4) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Enter Vehicle ID");
        int veh_id = scan.nextInt();


        try {
            String sql = "DELETE FROM vehicle " + "WHERE veh_id  ="+veh_id ;
            statement.executeUpdate(sql);
            System.out.println("Data Removed Successfully!!"+"\n\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        existmessage(scan, statement);

    }

//    public static void VehicleStatus(Scanner scan,Statement statement){
//        try {
//            System.out.println("Vehicle Status (Occupied / Vacant)");
//            ResultSet rs = statement.executeQuery("select * from vehicle");
//            while(rs.next()){
//                System.out.println("Vehicle Id : "+rs.getInt(1) +
//                        "|  Vehicle_Name : "+rs.getString(2) +
//                        "|  Vehicle_Status : "+rs.getString(4));
//                System.out.println("\n");
//            }
//
//        }catch (Exception e){
//            System.out.println(e.getMessage());
//
//        }
//        existmessage(scan,statement);
//
//    }

    public static void showVehicleDetails(Scanner scan,Statement statement){
        try {
            ResultSet rs = statement.executeQuery("select * from vehicle");
            while(rs.next()){
                System.out.println("Vehicle Id : "+rs.getInt(1) + "|  Vehicle_Name : "+rs.getString(2) + "|  Vehicle_NO : "+rs.getString(3) + "|  Vehicle_Status : "+rs.getString(4));
                System.out.println("\n");
            }

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        existmessage(scan,statement);

    }

    public static void existmessage(Scanner scan,Statement statement) {
        System.out.println("\nEnter 1 to Resume");
        System.out.println("Enter 2 to go back Main System");
        System.out.println("Enter 3 to Exit!!");

        switch(scan.nextInt()){
            case 1:{
                init1(scan,statement);
                break;
            }
            case 2:{
                MainClass.init(scan,statement);
            }
            default:{
                System.out.println("Exit");
            }
        }



    }
}
