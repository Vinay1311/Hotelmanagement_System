import Model.Attandencemodel;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class  StaffManagement {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        DatabaseConnection databaseConnection = new DatabaseConnection();
        Statement statement = databaseConnection.databaseConnection();
        StaffManagement staffManagement = new StaffManagement();
        staffManagement.init1(scan, statement);


    }

    public static void init1(Scanner scan,Statement statement) {
        System.out.println(".....Staff Management.....\n");
        System.out.println("Please Select An Option"+"\n");
        System.out.println("1. Add Staff");
        System.out.println("2. Show Staff Data");
        System.out.println("3. Update Staff Data");
        System.out.println("4. Remove Staff Data");
        System.out.println("5. Attandence");
        System.out.println("6. Back");

        switch (scan.nextInt()) {
            case 1: {
                addstaff(scan, statement);
                break;
            }
            case 2:{
                showdata(scan, statement);
                break;
            }
            case 3:{
                updateStaffdetails(scan, statement);
                break;
            }
            case 4:{
                removeStaffdata(scan,statement);
                break;
            }
            case 5:{
                attandence(scan,statement);
                break;
            }
            case 6:{
                existmessage(scan, statement);
                break;
            }
        }

    }

    public static void addstaff(Scanner scan, Statement statement)  {
        String staff_name,staff_mobileno,staff_address,staff_designation;
        int age,staff_salary;

        System.out.println("Enter Staff Name");
        staff_name = scan.next();
        System.out.println("Enter Mobile no");
        staff_mobileno = scan.next();
        System.out.println("Enter Address");
        staff_address = scan.next();
        System.out.println("Enter Age");
        age = scan.nextInt();
        if (age<=18&&age>=60){

        }
        System.out.println("Enter Designation");
        staff_designation = scan.next();
        System.out.println("Enter Staff Salary");
        staff_salary = scan.nextInt();

        String timestamp =  new SimpleDateFormat("dd/MM/yyyy").format(new Date());

        try {

            int result = statement.executeUpdate("insert into staffmanagement (staff_name,staff_mobileno,staff_address,staff_age,staff_designation,timestamp,staff_salary) values('"+staff_name+"',"+
                    "'"+staff_mobileno+"',"+
                    "'"+staff_address+"',"+
                    "'"+String.valueOf(age)+"',"+
                    "'"+staff_designation+"',"+
                    "'"+timestamp+"',"+
                    ""+staff_salary+""+
                    ")");
            if (result > 0)
                System.out.println("successfully inserted");

            else
                System.out.println("unsuccessful insertion ");

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        existmessage(scan,statement);


    }

    public static void showdata(Scanner scan, Statement statement)  {
        try {
            ResultSet rs=statement.executeQuery("select * from staffmanagement");
            while(rs.next()){
                System.out.println("Staff ID : "+rs.getInt("staff_id"));
                System.out.println("Staff Name : "+rs.getString("staff_name"));
                System.out.println("Mobile no : "+rs.getString("staff_mobileno"));
                System.out.println("Address : "+rs.getString("staff_address"));
                System.out.println("Age : "+rs.getInt("staff_age"));
                System.out.println("Designation: "+rs.getString("staff_designation"));
                System.out.println("Salary : "+rs.getInt("staff_salary"));
                System.out.println("Staff Joining Date: "+rs.getString("timestamp")+"\n\n");


            }

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        existmessage(scan, statement);

    }

    public static void updateStaffdetails(Scanner scan, Statement statement) {
        int age,staff_salary;
        String staff_name,staff_mobileno,staff_address,staff_designation;

        System.out.println("..........Update Staff Details..........");

        System.out.println("\nWarning!! You want to update staff data from Data Base");
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
            ResultSet rs=statement.executeQuery("select * from staffmanagement");
            while(rs.next()) {
                System.out.println("Staff ID. : " + rs.getInt(1));
                System.out.println("Name : " + rs.getString(2) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Staff ID");
        int staff_id =scan.nextInt();




        System.out.println("Please Select What Details You Want to Update");
        System.out.println("1. Staff Name");
        System.out.println("2. Mobile no.");
        System.out.println("3. Address ");
        System.out.println("4. Age");
        System.out.println("5. Designation");
        System.out.println("6. Salary");

        try {
            switch (scan.nextInt()) {
                case 1: {
                    System.out.println("Enter Staff New Name");
                    staff_name = scan.next();
                    String sql = "UPDATE staffmanagement " + "SET staff_name = '" + staff_name + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showdata(scan, statement);
                    break;
                }
                case 2: {
                    System.out.println("Enter Staff New Mobile no");
                    staff_mobileno = scan.next();
                    String sql = "UPDATE staffmanagement " + "SET staff_mobileno = '" + staff_mobileno + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showdata(scan, statement);
                    break;
                }
                case 3: {
                    System.out.println("Enter Staff New Address");
                    staff_address = scan.next();
                    String sql = "UPDATE staffmanagement " + "SET staff_address = '" + staff_address + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showdata(scan, statement);
                    break;
                }
                case 4: {
                    System.out.println("Enter Staff New Age");
                    age = scan.nextInt();
                    String sql = "UPDATE staffmanagement " + "SET age = '" + age + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showdata(scan, statement);
                    break;
                }
                case 5: {
                    System.out.println("Enter Staff New Designation");
                    staff_designation = scan.next();
                    String sql = "UPDATE staffmanagement " + "SET staff_designation = '" + staff_designation + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showdata(scan, statement);
                    break;
                }
                case 6: {
                    System.out.println("Enter Staff New Salary Amount");
                    staff_salary = scan.nextInt();
                    String sql = "UPDATE staffmanagement " + "SET staff_salary = '" + staff_salary + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showdata(scan, statement);
                    break;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        existmessage(scan, statement);


    }

    public static void removeStaffdata(Scanner scan, Statement statement) {


        System.out.println("..........Remove Staff Data..........");

        System.out.println("\nWarning!! You want to remove staff data from Data Base");
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
            ResultSet rs=statement.executeQuery("select * from staffmanagement");
            while(rs.next()) {
                System.out.println("Staff ID. : " + rs.getInt(1));
                System.out.println("Name : " + rs.getString(2) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Staff ID");
        int staff_id = scan.nextInt();


        try {
            String sql = "DELETE FROM staffmanagement " + "WHERE staff_id ="+staff_id;
            statement.executeUpdate(sql);
            System.out.println("Staff Data Removed Successfully!!"+"\n\n");
            showdata(scan, statement);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        existmessage(scan, statement);

    }

    public static void attandence(Scanner scan, Statement statement){
        System.out.println(".....Attendance Management.....");

        System.out.println("1.Add Attendance");
        System.out.println("2.Update Attendance");
        System.out.println("3.Show Attendance");

        switch(scan.nextInt()){
            case 1:{
                addAttendence(scan,statement);
                break;

            }
            case 2:{
                updateAttendance(scan,statement);
                break;

            }
            case 3:{
                showAttendance(scan,statement);
                break;

            }
            default:{
                break;
            }
        }


    }

    public static void addAttendence(Scanner scan, Statement statement){
        List<Attandencemodel> attandencemodelList = new ArrayList<>();
        try {
            ResultSet rs=statement.executeQuery("select * from staffmanagement");
            while(rs.next()){
                attandencemodelList.add(new Attandencemodel(rs.getInt(1),rs.getString(2)));
                System.out.println("Staff ID : "+rs.getInt("staff_id")+";"+" Staff Name : "+rs.getString("staff_name"));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());

        }
        String staff_status ="",remark="";
        int staff_id,flag=0,staff_index =0;

        try{
        System.out.println("Enter Staff id ");
        staff_id = scan.nextInt();

        System.out.println("Enter Staff Status(P or A)");
        staff_status= scan.next();

        if (staff_status.equals("P")){
            remark ="";
        }
        else {
            System.out.println("Enter Absent Remark ");
            remark = scan.next();
        }

        for (int i = 0; i < attandencemodelList.size() ; i++) {
            if (staff_id == attandencemodelList.get(i).getId()){
                flag =0;
                staff_index = i;
                break;
            }

            else {
                flag =1;
            }
        }
        if (flag==1){
            System.out.println("Invalid Staff id Please retry");
            attandence(scan, statement);
        }
        else {

            String timestamp = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

            try {

                int result = statement.executeUpdate("insert into attendance (staff_id,staff_name,staff_attandence_status,date,remark) values(" + staff_id + "," +
                        "'" + attandencemodelList.get(staff_index).getName() + "'," +
                        "'" + staff_status + "'," +
                        "'" + timestamp + "'," +
                        "'" + remark + "'" +
                        ")");
                if (result > 0)
                    System.out.println("successfully inserted");

                else
                    System.out.println("unsuccessful insertion ");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Press 1 To Add Another Staff Attendance");
        System.out.println("Press 2 To Exit");
        switch (scan.nextInt()){
            case 1:{
                addAttendence(scan,statement);
                break;
            }
            default:{
                showAttendance(scan,statement);
                break;
            }
        }

    }

    public static void updateAttendance(Scanner scan, Statement statement){
        int staff_id;
        String staff_attandence_status,remark;

        System.out.println("..........Update Staff Attendance Details..........");

        System.out.println("\nWarning!! You want to update staff data from Data Base");
        System.out.println("Press 1 to Continue");
        System.out.println("Press 2 to Exit");

        switch(scan.nextInt()){
            case 1:{
                break;

            }
            case 2:{
              attandence(scan,statement);
                break;
            }
            default:
                break;
        }

        try{

            ResultSet rs=statement.executeQuery("select * from attendance");
            while(rs.next()) {
                System.out.println("Staff ID. : " + rs.getInt(1));
                System.out.println("Name : " + rs.getString(2) + "\n");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Please Staff ID");
        staff_id =scan.nextInt();

        System.out.println("Please Select What Details You Want to Update");
        System.out.println("1. Attendance status");
        System.out.println("2. Remark");

        try {
            switch (scan.nextInt()) {
                case 1: {
                    System.out.println("Enter Staff New Attendance Status");
                    staff_attandence_status = scan.next();
                    String sql = "UPDATE attendance " + "SET staff_attandence_status = '" + staff_attandence_status + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showAttendance(scan, statement);
                    break;
                }
                case 2: {
                    System.out.println("Enter New Remark");
                    remark = scan.next();
                    String sql = "UPDATE staffmanagement " + "SET remark= '" + remark + "' WHERE staff_id in (" + staff_id + ")";
                    statement.executeUpdate(sql);
                    System.out.println("Data Updated Successfully!!"+"\n\n");
                    showAttendance(scan, statement);
                    break;
                }
                default:{
                    break;
                }
            }
            showAttendance(scan,statement);
        }catch (Exception e){
            System.out.println(e.getMessage());

        }
    }
    public static void showAttendance(Scanner scan, Statement statement){
        String date;
        System.out.println("Please enter date (dd/mm/yyyy) : ");
        date = scan.next();
        try {
            ResultSet rs=statement.executeQuery("select * from attendance WHERE date in ('"+date+"')");
            while(rs.next()){
                System.out.print("ID : "+rs.getInt("staff_id")+ ";");
                System.out.print(" Name : "+rs.getString("staff_name")+ ";");
                System.out.print(" Status : "+rs.getString("staff_attandence_status"));
                if (rs.getString(3).equals("A")){
                    System.out.print(";"+" Remark : "+rs.getString("remark"));
                }
                System.out.println();
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        existmessage(scan,statement);
    }

    public static void existmessage(Scanner scan, Statement statement) {
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
                break;
            }
            default:{
                System.out.println("Exit");
                break;
            }
        }



    }
}
