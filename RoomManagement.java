import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class RoomManagement {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DatabaseConnection databaseConnection=new DatabaseConnection();
        Statement statement = databaseConnection.databaseConnection();
        RoomManagement roomManagement=new RoomManagement();
        roomManagement.init1(scan,statement);

    }

    public static void init1(Scanner scan,Statement statement) {
        System.out.println("...............Room Management...............\n");
        System.out.println("Please Select An Option"+"\n");
        System.out.println("1.Add Room Category");
        System.out.println("2.Update Room Category");
        System.out.println("3.Remove Room Category");
        System.out.println("4.Room Status");
        System.out.println("5.Show Room Data");
        System.out.println("6.Add Room");
        System.out.println("7.Back");

        switch (scan.nextInt()){
            case 1:{
                addRoomCat(scan,statement);
                break;
            }
            case 2:{
                updateRoomcat(scan,statement);

                break;
            }
            case 3:{
                removeRoomcat(scan,statement);
                break;
            }
            case 4:{

                break;
            }
            case 5:{
                showRoom(scan,statement);
                break;
            }
            case 6:{
               addRoom(scan,statement);
                break;
            }
            case 7:{
                existmessage(scan, statement);
                break;
            }
            default:{
                break;
            }
        }

    }

    public static void addRoomCat(Scanner scan,Statement statement){
        System.out.println("Enter Room Category");
        String room_cat = scan.next();

        System.out.println("Enter Room Capacity");
        int room_capacity = scan.nextInt();

        try {
            int result = statement.executeUpdate(
                    "insert into room_category(room_cat_name,room_capacity) values(" +
                            "'"+room_cat+"',"+
                            ""+room_capacity+""+
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

    public static void updateRoomcat(Scanner scan,Statement statement){
        int room_cat_id;
        String room_cat_name,room_capacity ;

        System.out.println("..........Update Room Category Details..........");

        System.out.println("\nWarning!! You want to update Room data from Data Base");
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
            ResultSet rs=statement.executeQuery("select * from room_category ");
            while(rs.next()) {
                System.out.println("Category ID. : " + rs.getInt(1));
                System.out.println("Room Category Name : " + rs.getString(2) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Category ID");
        room_cat_id = scan.nextInt();




        System.out.println("Please Select What Details You Want to Update");
        System.out.println("1. Room Category Name");
        System.out.println("2. Room Capacity ");


        try {
            switch (scan.nextInt()) {
                case 1: {
                    System.out.println("Enter New Room_Category_Name");
                    room_cat_name= scan.next();
                    String sql = "UPDATE room_category " + "SET room_cat_name = '" + room_cat_name + "' WHERE room_cat_id in (" + room_cat_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showRoom(scan, statement);
                    break;
                }
                case 2: {
                    System.out.println("Enter Staff New Mobile no");
                    room_capacity  = scan.next();
                    String sql = "UPDATE room_category " + "SET room_capacity = '" + room_capacity + "' WHERE room_cat_id in (" + room_cat_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showRoom(scan, statement);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        existmessage(scan, statement);


    }

    public static void removeRoomcat(Scanner scan,Statement statement) {


        System.out.println("..........Remove Room Category Details..........");

        System.out.println("\nWarning!! You want to remove Room data from Data Base");
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
            ResultSet rs=statement.executeQuery("select * from room_category");
            while(rs.next()) {
                System.out.println("Room Cat ID: " + rs.getInt(1));
                System.out.println("Room Cat Name : " + rs.getString(2));
                System.out.println("Room Capacity: " + rs.getString(3) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Enter Room Category ID");
        int room_cat_id = scan.nextInt();


        try {
            String sql = "DELETE FROM room_category " + "WHERE room_cat_id ="+room_cat_id;
            statement.executeUpdate(sql);
            System.out.println("Room Category Data Removed Successfully!!"+"\n\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        existmessage(scan, statement);

    }

    public static void addRoom(Scanner scan,Statement statement){
        try {
            ResultSet rs=statement.executeQuery("select * from room_category");
            while(rs.next()){
                System.out.println("Cat ID. : "+rs.getInt(1) + " | Name : "+rs.getString(2)+" | Capacity : "+rs.getInt(3));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        System.out.println("\n");

        System.out.println("Enter Room Category ID");
        int room_cat_ID = scan.nextInt();

        System.out.println("Enter Room Status (Occupied / Vacant)");
        String room_Status = scan.next();

        try {
            int result = statement.executeUpdate(
                    "insert into rooms(room_cat_id,room_status) values(" +
                            ""+room_cat_ID+","+
                            "'"+room_Status+"'"+
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

    public static void showRoom(Scanner scan,Statement statement){
        try {
            ResultSet rs1 = statement.executeQuery("select * from room_category");
            while(rs1.next()){
                System.out.println("Room Cat_ID : "+rs1.getInt(1) + "|   Room Cat_Name : "+rs1.getString(2));
                System.out.println("\n");
            }

            ResultSet rs=statement.executeQuery("select * from rooms");
            while(rs.next()){
                System.out.println("Room ID : "+rs.getInt(1) + "|   Room Cat ID : "+rs.getInt(2) + "|   Room Status : "+rs.getString(3));

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