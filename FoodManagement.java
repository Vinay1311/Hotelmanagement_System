import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class FoodManagement {
        public static void main(String[] args) {
            try {
                Scanner scan = new Scanner(System.in);
                DatabaseConnection databaseConnection = new DatabaseConnection();
                Statement statement = databaseConnection.databaseConnection();
                FoodManagement foodManagement = new FoodManagement();
                foodManagement.init1(scan, statement);
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        public static void init1(Scanner scan,Statement statement){
            System.out.println("...............Food Management...............\n");
            System.out.println("Please Select An Option"+"\n");
            System.out.println("1.Add Food Category");
            System.out.println("2.Update Food Category");
            System.out.println("3.Remove Food Category");
            System.out.println("4.Food Status");
            System.out.println("5.Add Food");
            System.out.println("6.Show Food Data");

            System.out.println("7.Back");

            switch (scan.nextInt()){
                case 1:{
                    addFoodCat(scan,statement);
                    break;
                }
                case 2:{
                    updateFoodcat(scan,statement);
                    break;
                }
                case 3:{
                    removeFoodcat(scan,statement);
                    break;
                }
                case 4:{

                    break;
                }
                case 5:{
                    addFood(scan,statement);
                    break;
                }
                case 6:{
                    showFoodDetails(scan,statement);
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

        public static void addFoodCat(Scanner scan,Statement statement){
            System.out.println("Enter Food Category");
            String food_cat = scan.next();

            System.out.println("Enter Food Description");
            String food_description = scan.next();

            try {
                int result = statement.executeUpdate(
                        "insert into food_category (food_cat_name,food_description) values(" +
                                "'"+food_cat+"',"+
                                "'"+food_description+"'"+
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

        public static void updateFoodcat(Scanner scan,Statement statement){
        int food_cat_id;
        String food_cat_name,food_description ;

        System.out.println("..........Update Food Category Details..........");

        System.out.println("\nWarning!! You want to update Food data from Data Base");
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
            ResultSet rs=statement.executeQuery("select * from food_category ");
            while(rs.next()) {
                System.out.println("Category ID. : " + rs.getInt(1));
                System.out.println("Food Category Name : " + rs.getString(2));
                System.out.println("Food Description: " + rs.getString(3) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Category ID");
        food_cat_id = scan.nextInt();


        System.out.println("Please Select What Details You Want to Update");
        System.out.println("1. Food Category Name");
        System.out.println("2. Food Description ");


        try {
            switch (scan.nextInt()) {
                case 1: {
                    System.out.println("Enter New Food_Category_Name");
                    food_cat_name= scan.next();
                    String sql = "UPDATE room_category " + "SET room_cat_name = '" + food_cat_name + "' WHERE room_cat_id in (" + food_cat_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showFoodDetails(scan, statement);
                    break;
                }
                case 2: {
                    System.out.println("Enter Staff New Mobile no");
                    food_description  = scan.next();
                    String sql = "UPDATE room_category " + "SET room_capacity = '" + food_description + "' WHERE room_cat_id in (" + food_cat_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showFoodDetails(scan, statement);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        existmessage(scan, statement);


    }

        public static void removeFoodcat(Scanner scan,Statement statement) {


        System.out.println("..........Remove Food Category Details..........");

        System.out.println("\nWarning!! You want to remove Food data from Data Base");
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
            ResultSet rs=statement.executeQuery("select * from food_category");
            while(rs.next()) {
                System.out.println("Food Cat ID: " + rs.getInt(1));
                System.out.println("Food Cat Name : " + rs.getString(2));
                System.out.println("Food Description " + rs.getString(3) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Enter Food Category ID");
        int food_cat_id = scan.nextInt();


        try {
            String sql = "DELETE FROM food_category " + "WHERE food_cat_id ="+food_cat_id;
            statement.executeUpdate(sql);
            System.out.println("Food Category Data Removed Successfully!!"+"\n\n");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        existmessage(scan, statement);

    }

        public static void addFood(Scanner scan,Statement statement){
            try {
                ResultSet rs=statement.executeQuery("select * from food_category");
                while(rs.next()){
                    System.out.println("Food Category ID. : "+rs.getInt(1) + " | Food Category Name : "+rs.getString(2)+" | Description: "+rs.getString(3));
                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }
            System.out.println("\n");
            System.out.println("Enter Food Category ID");
            int food_cat_ID  = scan.nextInt();

            System.out.println("Enter Food Name");
            String food = scan.next();

            System.out.println("Enter Food Price (in Rs.)");
            String food_price = scan.next();

            System.out.println("Enter Food Status (Avl / Not Avl)");
            String food_Status = scan.next();



            try {
                int result = statement.executeUpdate(
                        "insert into food(food_name,food_cat_id,food_price,food_status) values(" +
                                "'"+food+"',"+
                                ""+food_cat_ID+","+
                                ""+food_price+","+
                                "'"+food_Status+"'"+
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

        public static void showFoodDetails(Scanner scan,Statement statement){
            try {
                ResultSet rs1 = statement.executeQuery("select * from food_category");
                while(rs1.next()){
                    System.out.println("Food_Cat_ID : "+rs1.getInt(1) + "|  Food_Cat_Name : "+rs1.getString(2)+ "|  Food_Description : "+rs1.getString(2));
                }
                System.out.println();
                ResultSet rs=statement.executeQuery("select * from food");
                while(rs.next()){
                    System.out.println("Food ID : "+rs.getInt(5) + "|   Food Name : "+rs.getString(1) + "|   Food Price : "+rs.getString(3) + "|   Food Status : "+rs.getString(4));

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

