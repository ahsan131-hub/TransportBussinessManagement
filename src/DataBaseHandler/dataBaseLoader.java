package DataBaseHandler;

import addEmplyee.listEmployeeController;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import vehicleList.listVehicleController;

import javax.swing.*;
import java.io.*;
import java.sql.*;

import static javax.swing.JOptionPane.*;

public class dataBaseLoader {
    static String userName=null;
    static String password=null;
    Connection conn;
    Statement stmt;
    Thread t=new Thread(()->{ creatConnection();
        setUpVechicle();
        setUpClient();
        setUpSoled();
        setUpEmplyee();
        setUpReceipts();
        setUpStatistics();
    }
    );

    private void setUpStatistics() {
        try {
            ResultSet tables = conn.getMetaData().getTables(null, "tms", "Bstatistics", null);

            if (tables.next()) {
                System.out.println("Table already exists statistics");
            } else {
                System.out.println("table doesnot exists statistics ");
                String query = "create table Bstatistics(Vno varChar(100) ,police INT ,diesel INT ,food INT,vMaintanence INT,dDescription varchar(500),returnRent INT,distance varchar(100),profit INT,city varchar(100),timeArrived timestamp default current_timestamp,Diff int)";

                if (execAction(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Table created");
                    alert.setContentText("succesfully created the table for statistics ");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot created");
                    alert.setContentText("Cannot be  created the table for statistics");
                    alert.show();

                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    private dataBaseLoader() {
      t.run();
    }


    static dataBaseLoader handler = null;

    public static dataBaseLoader getInstance() {

        if (handler == null) {
            handler = new dataBaseLoader();

        }
        return handler;
    }

    private boolean creatConnection() {
        try {
        File file=new File("dataBaseInfo.txt");
            if(file.exists()){
                try {
                    FileReader in=new FileReader(file);
                    BufferedReader inB=new BufferedReader(in);
                    userName=inB.readLine();
                    password=inB.readLine();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else {

                TextInputDialog user = new TextInputDialog();
                user.setContentText("writer the userName of the database for Connection ");
                user.setTitle("User Name");
                user.setHeaderText("you have provide only once ");
                TextInputDialog  pass= new TextInputDialog();
                pass.setHeaderText("you have provide only once    ");
                pass.setContentText("writer the password of the database for Connection ");
                pass.setTitle("passWord");
                user.showAndWait();
                pass.showAndWait();

                userName= user.getEditor().getText();password= pass.getEditor().getText();
                System.out.print(password);
                System.out.print(userName);
                FileWriter writer= null;
                try {
                    writer = new FileWriter(file);
                    BufferedWriter bufferedWriter=new BufferedWriter(writer);

                    bufferedWriter.write(userName);
                    bufferedWriter.newLine();
                    bufferedWriter.write(password);

                    bufferedWriter.flush();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }


            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tms", userName,password );
            return true;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


        JOptionPane.showMessageDialog(null, "Error in setting up data base.");
        return false;
    }

    public boolean execAction(String query) {
        try {
            stmt = conn.createStatement();
            stmt.execute(query);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getStackTrace());
            showMessageDialog(null, "Error:" + ex.getMessage(), "Error Occured", ERROR_MESSAGE);
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return false;
        } finally {
        }


    }

    public ResultSet execQuery(String query) {
        ResultSet result;
        try {
            stmt = conn.createStatement();
            result = stmt.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("Exception at execQuery:dataHandler" + ex.getLocalizedMessage());
            return null;
        } finally {
        }
        return result;
    }

    public boolean AddVechicle(String query) {
        return execAction(query);


    }

    public boolean AddClient(String qu) {
        return execAction(qu);
    }

    public void UpdateVehicle(listVehicleController.Vehicle vehicle) {

        PreparedStatement pstmt = null;

        try {
            pstmt = conn.prepareStatement("update vehicles set " +
                    "vehicleName=?, type=?, chasisNo =?, " +
                    "engineNo=?, totalPrice=?, error=?, vehicleDecription=?" +
                    ",lastOwnerName=?,nic =?,phoneNO=?," +
                    " lastOwnerDecription=?  ,address=? where vehicleNo=?");


            pstmt.setString(1, vehicle.getVname());
            pstmt.setString(2, vehicle.getVtype());
            pstmt.setString(3, vehicle.getChasisNo());
            pstmt.setString(4, vehicle.getEngineNo());
            pstmt.setInt(5, vehicle.getPrice());
            pstmt.setString(6, vehicle.getError());
            pstmt.setString(7, vehicle.getVehicleDecription());
            pstmt.setString(8, vehicle.getLastOwnerName());
            pstmt.setString(9, vehicle.getNic());
            pstmt.setString(10, vehicle.getPhoneNO());
            pstmt.setString(11, vehicle.getLastOwnerDecription());
            pstmt.setString(12, vehicle.getAddress());
            pstmt.setString(13, vehicle.getVno());
//            pstmt.setBlob(12, (Blob) vehicle.getImage());
            if (pstmt.executeUpdate() > 0) {
                System.out.println("executed ");
            } else {
                System.out.println("executed  not completed");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    //setClient
    public void setUpClient() {
        try {
            ResultSet tables = conn.getMetaData().getTables(null, "tms", "addClient", null);

            if (tables.next()) {
                System.out.println("Table already exists");
            } else {
                System.out.println("table doesnot exists");
                String query = "create table addClient(companyName varchar(200)PRIMARY KEY,clientName varchar(100),contact varchar(100),address varchar(200))";

                if (execAction(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Table created");
                    alert.setContentText("succesfully created the table for Clients");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot created");
                    alert.setContentText("Cannot be  created the table for clients");
                    alert.show();

                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setUpVechicle() {
        try {
            ResultSet tables = conn.getMetaData().getTables(null, "tms", "vehicles", null);

            if (tables.next()) {
                System.out.println("Table already exists");
            } else {
                String query = "create table vehicles(" +
                        "vehicleNo varchar(100) PRIMARY KEY," +
                        "vehicleName varchar(100)," +
                        "type varchar(100)," +
                        "chasisNo varchar(100)," +
                        "engineNo varchar(100)," +
                        "totalPrice integer," +
                        "error varchar(100)," +
                        "vehicleDecription varchar(1000)," +
                        "timeOfPurchase timestamp default CURRENT_TIMESTAMP," +
                        "lastOwnerName varchar(100)," +
                        "nic varchar(100)," +
                        "phoneNO varchar(100)," +
                        "lastOwnerDecription varchar(1000)," +
                        "isavail Boolean default true," +
                        "images varbinary(1000)," +
                        "address varchar(500)" +
                        ")";

                if (execAction(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Table created");
                    alert.setContentText("succesfully created the table");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot created");
                    alert.setContentText("Cannot be  created the table");
                    alert.show();

                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void setUpSoled() {
        try {
            ResultSet tables = conn.getMetaData().getTables(null, "tms", "soled", null);

            if (tables.next()) {
                System.out.println("Table already exists");
            } else {
                String query = "create table soled(" +
                        "vehicleNo varchar(100) PRIMARY KEY," +
                        "vehicleName varchar(100)," +
                        "type varchar(100)," +
                        "chasisNo varchar(100)," +
                        "engineNo varchar(100)," +
                        "totalPrice integer," +
                        "error varchar(100)," +
                        "vehicleDecription varchar(1000)," +
                        "timeOfPurchase timestamp ," +
                        "lastOwnerName varchar(100)," +
                        "nic varchar(100)," +
                        "phoneNO varchar(100)," +
                        "lastOwnerDecription varchar(1000)," +
                        "isavail Boolean," +
                        //"images varbinary(1000)," +
                        "address varchar(500)," +
                        "saleTo varchar(100)," +
                        "pNic varchar(100)," +
                        "pAddress varchar(200)," +
                        "pPhone varchar(100)," +
                        "saleAmount integer," +
                        "timeOfSale timestamp default CURRENT_TIMESTAMP," +
                        "saleDescription varchar(1000)" +
                        ")";

                if (execAction(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Table created");
                    alert.setContentText("succesfully created the table sale table");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot created");
                    alert.setContentText("Cannot be  created the table sale table");
                    alert.show();

                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }

    public boolean saleVehicle(String num, String saleto, String pnic, String pPhone, String amount, String Paddress, String saledescrption) throws SQLException {

        String queryMetaData = "select * from vehicles where vehicleNo='" + num + "'";

        ResultSet rs = execQuery(queryMetaData);
        String Vname = "";
        String Vno = "";
        String Vtype = "";
        Boolean avail = false;
        int price = 0;
        Timestamp date = null;
        String VLastOwner = "";
        String lastOwnerDecription = "";
        String phoneNO = "";
        String vehicleDecription = "";
        String error = "";
        String chasisNo = "";
        String engineNo = "";
        String nic = "";
        String address = "";
        while (rs.next()) {
            Vname = rs.getString("vehicleName");
            Vno = rs.getString("VehicleNo");
            Vtype = rs.getString("type");
          //  avail = rs.getBoolean("isavail");
            price = rs.getInt("totalPrice");
            date = rs.getTimestamp("timeOfPurchase");
            VLastOwner = rs.getString("lastOwnerName");
            lastOwnerDecription = rs.getString("lastOwnerDecription");
            phoneNO = rs.getString("phoneNO");
            vehicleDecription = rs.getString("vehicleDecription");
            error = rs.getString("error");
            chasisNo = rs.getString("chasisNo");
            engineNo = rs.getString("engineNo");
            nic = rs.getString("nic");
            address = rs.getString("address");
        }


        String qu = "insert into soled(" +
                "vehicleNo," +
                " vehicleName, " +
                "type," +
                " chasisNo," +
                " engineNo," +
                " totalPrice," +
                " error," +
                " vehicleDecription," +
                " timeOfPurchase," +
                " lastOwnerName," +
                " nic," +
                " phoneNO," +
                " lastOwnerDecription," +
                " isavail," +
//                " images," +
                " address," +
                " saleTo," +
                " pNic," +
                " pAddress," +
                " pPhone," +
                " saleAmount," +
                " saleDescription) values(" +
                "'" + Vno + "'," +
                "'" + Vname + "'," +
                "'" + Vtype + "'," +
                "'" + chasisNo + "'," +
                "'" + engineNo + "'," +
                "'" + price + "'," +
                "'" + error + "'," +
                "'" + vehicleDecription + "'," +
                "'" + date + "'," +
                "'" + VLastOwner + "'," +
                "'" + nic + "'," +
                "'" + phoneNO + "'," +
                "'" + lastOwnerDecription+ "'," +
                "'" + 1 + "'," +
                "'" + address + "'," +
                "'" + saleto + "'," +
                "'" + pnic + "'," +
                "'" + Paddress + "'," +
                "'" + pPhone + "'," +
                "'" + Integer.parseInt(amount)+ "'," +
                "'" + saledescrption+ "'" +
                ")";
            if (execAction(qu)) {
                System.out.println("executed ");
                return true;
            } else {
                System.out.println("executed  not completed");

            }
            return false;
        }



    private void setUpEmplyee() {
        try {
            ResultSet tables = conn.getMetaData().getTables(null, "tms", "employees", null);

            if (tables.next()) {
                System.out.println("Table already exists");
            } else {
                System.out.println("table doesnot exists");
                String query = "create table employees(dName varchar(200),dnic varchar(100) PRIMARY KEY,dcontact varchar(100),daddress varchar(200),dsalary integer default 0,dDescription varchar(200))";

                if (execAction(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Table created");
                    alert.setContentText("succesfully created the table for emplyees");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot created");
                    alert.setContentText("Cannot be  created the table for emplyees");
                    alert.show();

                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }



    public void deletVehicle(String queryDel) {
        if(execAction(queryDel)){
            System.out.println("vehicle deleted");
        }else{
            System.out.println("vehicle cannot be deleted");
        }
    }


    public boolean addEmployee(String query) {
        return  execAction(query);
    }

    public void deleteEmployee(listEmployeeController.employee emp) {
        String queryDel="DELETE FROM employees where dnic ="+"'"+emp.getDnic()+"'";
        if(execAction(queryDel)){
            System.out.println("Emplyee deleted");
        }else{
            System.out.println("employee cannot be deleted");
        }
    }


    private void setUpReceipts() {
        try {
            ResultSet tables = conn.getMetaData().getTables(null, "tms", "receipts", null);

            if (tables.next()) {
                System.out.println("Table already exists receipts");
            } else {
                System.out.println("table doesnot exists receipts");
                String query = "create table receipts(" +
                        "digReceipt int  PRIMARY KEY AUTO_INCREMENT," +
                        "mnlReceiptNO varchar(100) default NULL," +
                        "Driver varchar(200)," +
                        "pvtVehicleNo varchar(200) default Null," +
                        "cmpyVehicleNo varchar(100)," +
                        "Client varchar(100)," +
                        "Stufffrom varchar(200)," +
                        "destination varchar(100)," +
                        "Stuff varchar(100)," +
                        "rent int default 0," +
                        "comission int default 0," +
                        "description varchar(500)," +
                        "driverPhone varchar(100)," +
                        "clientphone varchar(100)," +
                        "OfficePhone varchar(100)," +
                        "Rtime varchar(100))";
// "FOREIGN KEY(cmpyVehicleNo) REFERENCES vehicles(vehicleNo)
                if (execAction(query)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Table created receipts");
                    alert.setContentText("succesfully created the table for receipts");
                    alert.show();

                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Cannot receipts");
                    alert.setContentText("Cannot be  created the table for receipts");
                    alert.show();

                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}





