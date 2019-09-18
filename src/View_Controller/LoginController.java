/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.User;
import Utilities.TimeFiles;
import appointmentapp_tuannguyen.AppointmentApp_TuanNguyen;
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
    
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private Button test;
    @FXML
    private Label test2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
                
                // Assign logged in User to the global variable loggedInUser
                // This will provide context to who is logged in during the session
                AppointmentApp_TuanNguyen.loggedInUser = retrievedUser;
                
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
    
}
