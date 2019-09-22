/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.User;
import Utilities.TimeFiles;
import appointmentapp_tuannguyen.AppointmentApp_TuanNguyen;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.errorAlert;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.loggedInUser;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.CustomerList;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.AppointmentList;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.UserList;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.CountryList;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.CityList;
import static appointmentapp_tuannguyen.AppointmentApp_TuanNguyen.AddressList;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author tuanxn
 */
public class LoginController implements Initializable {

    @FXML
    private Label label;
    @FXML
    private TextField userName;
    @FXML
    private TextField password;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    

    @FXML
    private void loginButton(ActionEvent event) {
        
        String userNameText = userName.getText();
        String passwordText = password.getText();
        
        try {
            User retrievedUser = DAO.UserDaoImpl.getUser(userNameText);
            if(retrievedUser == null) {
                errorAlert.setContentText("Username not found");
                errorAlert.showAndWait();
            }else if(!passwordText.equals(retrievedUser.getPassword())) {
                errorAlert.setContentText("Incorrect password");
                errorAlert.showAndWait();
            }else {
                // Display loading message
                
                // Assign logged in User to the global variable loggedInUser
                // This will provide context to who is logged in during the session
                loggedInUser = retrievedUser;
                UserList = DAO.UserDaoImpl.getAllUsers();
                // Since country, city, and address cascade (in that order) into Customer, we must load those first
                CountryList = DAO.CountryDaoImpl.getAllCountries();
                CityList = DAO.CityDaoImpl.getAllCities();
                AddressList = DAO.AddressDaoImpl.getAllAddresses();
                CustomerList = DAO.CustomerDaoImpl.getAllCustomers();
                // Since user and customer list are used in appointment list, we must get those first
                AppointmentList = DAO.AppointmentDaoImpl.getAllAppointments();
                
                Parent parent = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
                Scene part_screen_scene = new Scene(parent);
                Stage app_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                app_stage.hide();
                app_stage.setScene(part_screen_scene);
                app_stage.show();
            }
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
        }
        
    }

    @FXML
    private void exitButton(ActionEvent event) {
        System.exit(0);
    }
    
}
