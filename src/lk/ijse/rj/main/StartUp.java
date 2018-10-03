/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.main;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author Ilman Iqbal
 */
public class StartUp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/rj/view/MainForm.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void navigateToSection(Stage mainStage, String labelText) {
        Platform.runLater(() -> {

            try {
                Parent root = null;

                switch (labelText) {
                    case "lblHome":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/MainForm.fxml"));
                        break;
                    case "lblSaleNewSales":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/NewSale.fxml"));
                        break;
                    case "lblOrderNewOrder":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/NewOrder.fxml"));
                        break;
                    case "lblSaleBalancePayments":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/SaleBalancePayment.fxml"));
                        break;
                    case "lblOrderBalancePayments":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/OrderBalancePayment.fxml"));
                        break;
                    case "lblSaleManageSales":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/ManageSale.fxml"));
                        break;
                    case "lblOrderManageOrders":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/ManageOrder.fxml"));
                        break;
                    case "lblEmployeesManageEmployees":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/ManageEmployee.fxml"));
                        break;
                    case "lblStockManageStock":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/ManageStock.fxml"));
                        break;
                    case "lblSaleReports":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/SaleReport.fxml"));
                        break;
                    case "lblOrderReports":
                        root = FXMLLoader.load(StartUp.class.getResource("/lk/ijse/rj/view/OrderReport.fxml"));
                        break;
                }

                Scene scene = null;
                if (root != null) {
                    scene = new Scene(root);
                    mainStage.setScene(scene);
                }

                if (labelText.equals("lblHome")) {
                    TranslateTransition tt1 = new TranslateTransition(Duration.millis(500), root.lookup("AnchorPane"));
                    tt1.setFromX(-scene.getWidth());
                    tt1.setToX(0);
                    tt1.play();
                }
            } catch (IOException ex) {
                Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
