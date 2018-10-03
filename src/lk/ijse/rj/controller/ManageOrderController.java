/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.ManageOrderTM;

/**
 *
 * @author Ilman Iqbal
 */
public class ManageOrderController implements Initializable {

    @FXML
    private Label lblHome;
    @FXML
    private JFXComboBox<String> cmbSearchBy;
    @FXML
    private JFXTextField txtKeyword;
    @FXML
    private TableView<ManageOrderTM> tblOrders;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblOrderNewOrder;
    @FXML
    private Label lblOrderBalancePayments;
    @FXML
    private Label lblOrderManageOrders;
    @FXML
    private Label lblOrderReports;

    
    private OrderBO orderBO;
    private ManageOrderController manageOrderControllerAddress;
    
    public ManageOrderController() {
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manageOrderControllerAddress = this;
        
        cmbSearchBy.getItems().add("Order ID");
        cmbSearchBy.getItems().add("Name");
        cmbSearchBy.getItems().add("Contact");
        cmbSearchBy.getItems().add("Date of Order");
        cmbSearchBy.getItems().add("Date of Delivery");

        //cmbSearchBy.getSelectionModel().select("Order ID");
        cmbSearchBy.setValue("Order ID");

        tblOrders.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        tblOrders.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblOrders.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblOrders.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateOfOrder"));
        tblOrders.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateOfDelivery"));
        tblOrders.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblOrders.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("balance"));
        tblOrders.getColumns().get(7).setCellValueFactory(new PropertyValueFactory<>("status"));

        getOrderBySelection();

        cmbSearchBy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getOrderBySelection();
            }
        });
        
        txtKeyword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getOrderBySelection();
            }
        });
    }
    
    public void getOrderBySelection() {
        try {
            String value = cmbSearchBy.getValue();
            
            ArrayList<OrderDTO> orderDTOs = null;
            
            ObservableList<ManageOrderTM> items = tblOrders.getItems();
            items.removeAll(items);
            
            switch (value) {
                case "Order ID":
                    orderDTOs = orderBO.getOrderByOrderId(txtKeyword.getText() + "%");
                    break;
                case "Name":
                    orderDTOs = orderBO.getOrderByName(txtKeyword.getText() + "%");
                    break;
                case "Contact":
                    orderDTOs = orderBO.getOrderByContact(txtKeyword.getText() + "%");
                    break;
                case "Date of Order":
                    orderDTOs = orderBO.getOrderByDateOfOrder(txtKeyword.getText() + "%");
                    break;
                case "Date of Delivery":
                    orderDTOs = orderBO.getOrderByDateOfDelivery(txtKeyword.getText() + "%");
                    break;
            }

            for (OrderDTO orderDTO : orderDTOs) {
                String status = null;
                if (orderDTO.getDateOfCancellation() != null) {
                    status = "Cancelled";
                }
                if (orderDTO.getDateOfActualDelivery() != null) {
                    status = "Delivered";
                }
                ManageOrderTM manageOrderTM = new ManageOrderTM(orderDTO.getOrderId(), orderDTO.getName(), 
                        orderDTO.getContact(), orderDTO.getDateOfOrder(), orderDTO.getDateOfDelivery(), 
                        orderDTO.getTotal(), orderDTO.getBalance(), status);
                items.add(manageOrderTM);

            }
        } catch (Exception ex) {
            Logger.getLogger(ManageOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void label_OnMouseClicked(MouseEvent event) {
        if (event.getSource() instanceof Label) {

            Label label = (Label) event.getSource();
            String id = label.getId();

            StartUp.navigateToSection((Stage) root.getScene().getWindow(), id);

        }
    }

    @FXML
    private void btnEdit_OnAction(ActionEvent event) {
        
        if (tblOrders.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Please select an Order from the table", ButtonType.OK).show();
            return;
        }
        
        try {
            String orderId = tblOrders.getSelectionModel().getSelectedItem().getOrderId();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/rj/view/EditOrder.fxml"));
            Parent parent = loader.load();
            
            EditOrderController editOrderController = loader.getController();
            editOrderController.initVariable(orderId, manageOrderControllerAddress);
            
            Scene scene = new Scene(parent);
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.show();
            
        } catch (IOException ex) {
            Logger.getLogger(ManageOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    

}
