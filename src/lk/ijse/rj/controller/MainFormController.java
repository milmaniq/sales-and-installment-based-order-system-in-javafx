/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Ilman Iqbal
 */
public class MainFormController implements Initializable {

    @FXML
    private Pane paneStock;
    @FXML
    private Pane paneOrders;
    @FXML
    private Pane paneEmployees;
    @FXML
    private Pane paneSales;
    @FXML
    private AnchorPane root;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void pane_OnMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Pane) {
            try {
                Pane pane = (Pane) event.getSource();

                Parent root = null;

                switch (pane.getId()) {
                    case "paneSales":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/rj/view/NewSale.fxml"));
                        break;
                    case "paneOrders":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/rj/view/NewOrder.fxml"));
                        break;
                    case "paneStock":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/rj/view/ManageStock.fxml"));
                        break;
                    case "paneEmployees":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/rj/view/ManageEmployee.fxml"));
                        break;
                    case "paneReports":
                        root = FXMLLoader.load(this.getClass().getResource("/lk/ijse/rj/view/NewSale.fxml"));
                        break;
                }
                
                if(root != null){
                    Stage stage = (Stage) this.root.getScene().getWindow();
                    Scene scene = new Scene(root);
                    stage.setScene(scene);
                    
                    TranslateTransition tt = new TranslateTransition(Duration.millis(350), scene.getRoot());
                    tt.setFromX(+scene.getWidth());
                    tt.setToX(0);
                    tt.play();
                }
            } catch (IOException ex) {
                Logger.getLogger(MainFormController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
