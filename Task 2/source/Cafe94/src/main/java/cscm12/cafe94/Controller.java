package cscm12.cafe94;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

/**[Controller]
 * Where you set up your Action Events to swap scenes and stuff upon clicking buttons.
 * @author Sumi Sunuwar
 * @version 1.0
 */
public class Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML private static String staffSession;
    @FXML private TextField staffLoginUsername;
    @FXML private PasswordField staffLoginPassword;
    @FXML private Label staffType;
    @FXML private Label staffFName;
    @FXML private Label staffLName;
    @FXML private TextField newStaffFName;
    @FXML private TextField newStaffLName;
    @FXML private TextField newStaffType;
    @FXML private TextField newHoursToWork;
    @FXML private TextField newStaffUsername;
    @FXML private TextField newStaffPassword;
    @FXML private Button insertStaffButton;
    @FXML private Button updateStaffButton;
    @FXML private Button deleteStaffButton;
    @FXML private TableView<ManageStaff> staffTable;
    @FXML private TableColumn<ManageStaff, String> fieldStaffFName;
    @FXML private TableColumn<ManageStaff, String> fieldStaffLName;
    @FXML private TableColumn<ManageStaff, String> fieldStaffType;
    @FXML private TableColumn<ManageStaff, Integer> fieldHoursToWork;
    @FXML private TableColumn<ManageStaff, String> fieldStaffUsername;
    @FXML private TableColumn<ManageStaff, String> fieldStaffPassword;

    public Connection staffDatabase(){
        Connection StaffDatabase;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            StaffDatabase = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/staffdatabase", "root", "");
            return StaffDatabase;
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @FXML
    public void switchToStaffAccount(ActionEvent event) throws IOException {
        String fxml = "";
        Connection connect = staffDatabase();

        try{
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM ManageStaff WHERE StaffUsername='"
                    + staffLoginUsername.getText() + "'" + " AND " +
                    "StaffPassword='" + staffLoginPassword.getText() + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getString("StaffType").equals("Manager")) {
                    fxml = ("Manager.fxml");
                    staffSession = resultSet.getString("StaffUsername");
                }
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if(fxml != null){
            root = FXMLLoader.load(getClass().getResource(fxml));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void getStaffHomeInfo(){
        Connection connect = staffDatabase();

        try {
            Statement statement = connect.createStatement();
            String sql = "SELECT * FROM ManageStaff WHERE StaffUsername='" + staffSession + "'";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();

            while (resultSet.next()) {
                staffType.setText(resultSet.getString("StaffType"));
                staffFName.setText(resultSet.getString("First_Name"));
                staffLName.setText(resultSet.getString("Last_Name"));
            }
            statement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<ManageStaff> getManageStaffTable(){
        ObservableList<ManageStaff> staffList = FXCollections.observableArrayList();
        Connection connect = staffDatabase();

        try{
            String sql = "SELECT * FROM ManageStaff";
            PreparedStatement checkDatabase = connect.prepareStatement(sql);
            ResultSet resultSet = checkDatabase.executeQuery();
            ManageStaff staff;

            while (resultSet.next()) {
                staff = new ManageStaff(resultSet.getString("First_Name"),
                        resultSet.getString("Last_Name"),
                        resultSet.getString("StaffType"),
                        resultSet.getInt("HoursToWork"),
                        resultSet.getString("StaffUsername"),
                        resultSet.getString("StaffPassword"));
                        System.out.println("Arrays filled.");

                staffList.add(staff);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return staffList;
    }

    //TODO: Doesn't work for now, null pointer, will try to fix.
    public void getStaffTable(){
        System.out.println("Getting staff table...");
        ObservableList<ManageStaff> staff = getManageStaffTable();
        System.out.println("Staff list: " + staff);
        fieldStaffFName.setCellValueFactory(new PropertyValueFactory<>("staffFName"));
        fieldStaffLName.setCellValueFactory(new PropertyValueFactory<>("staffLName"));
        fieldStaffType.setCellValueFactory(new PropertyValueFactory<>("staffType"));
        fieldHoursToWork.setCellValueFactory(new PropertyValueFactory<>("hoursToWork"));
        fieldStaffUsername.setCellValueFactory(new PropertyValueFactory<>("staffUsername"));
        fieldStaffPassword.setCellValueFactory(new PropertyValueFactory<>("staffPassword"));

        System.out.println("Staff Name: " + fieldStaffFName);
        staffTable.setItems(staff);
    }


    private void staffTableSQLCommand(String staff) {
        Connection connect = staffDatabase();

        try {
            Statement statement = connect.createStatement();
            statement.executeUpdate(staff);
        } catch (SQLIntegrityConstraintViolationException e){
            System.out.println("Username already exists.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    private void editStaff(){
        String addNewStaff = "INSERT INTO ManageStaff SET StaffUsername = '" + newStaffUsername.getText() +
                "', StaffType = '" + newStaffType.getText() + "', First_Name = '" + newStaffFName.getText() +
                "',Last_Name = '" + newStaffLName.getText() + "', StaffPassword = '" + newStaffPassword.getText() +
                "', HoursToWork = '" + newHoursToWork.getText() + "', " + "WHERE StaffUsername =" +
                newStaffUsername.getText() + "'";
        staffTableSQLCommand(addNewStaff);
    }


    private void newStaff(){
        String staff = "UPDATE ManageStaff SET StaffUsername = ('" + newStaffUsername.getText() + "',  '" +
                newStaffType.getText() + "','" + newStaffFName.getText() + "','" + newStaffLName.getText() +
                "','" + newStaffPassword.getText() + "','" + newHoursToWork.getText() + "')";
        staffTableSQLCommand(staff);
        getStaffTable();
    }


    private void deleteStaff(){
        String staff = "DELETE FROM ManageStaff WHERE StaffUsername = '" + newStaffUsername.getText() + "'";
        staffTableSQLCommand(staff);
        getStaffTable();
    }

    @FXML
    private void ManageStaffButtonActionEvents(ActionEvent e){
        if(e.getSource() == insertStaffButton){
            newStaff();
        } else if(e.getSource() == updateStaffButton){
            editStaff();
        } else if(e.getSource() == deleteStaffButton){
            deleteStaff();
        }
    }

    @FXML
    public void switchToStaffLogin(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StaffLogin.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToManager(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("Manager.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToManageStaff(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("ManageStaff.fxml"));
        getStaffTable();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getStaffHomeInfo();
        getStaffTable();
    }
}
