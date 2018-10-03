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
import lk.ijse.rj.business.custom.SaleBO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.SaleDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.ManageOrderTM;
import lk.ijse.rj.view.util.tblmodel.ManageSaleTM;

/**
 *
 * @author Ilman Iqbal
 */
public class ManageSaleController implements Initializable{

    @FXML
    private Label lblHome;
    @FXML
    private JFXComboBox<String> cmbSearchBy;
    @FXML
    private JFXTextField txtKeyword;
    @FXML
    private TableView<ManageSaleTM> tblSales;
    @FXML
    private JFXButton btnEdit;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblSaleNewSales;
    @FXML
    private Label lblSaleManageSales;
    @FXML
    private Label lblSaleReports;

    private SaleBO SaleBO;
    private ManageSaleController manageSaleControllerAddress;
    
    public ManageSaleController() {
        SaleBO = (SaleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SALE);
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        manageSaleControllerAddress = this;
        
        cmbSearchBy.getItems().add("Sales ID");
        cmbSearchBy.getItems().add("Name");
        cmbSearchBy.getItems().add("Contact");
        cmbSearchBy.getItems().add("Date of Sales");
        cmbSearchBy.getItems().add("Order ID");
        
        cmbSearchBy.setValue("Sales ID");
        
        tblSales.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("salesId"));
        tblSales.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblSales.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblSales.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("dateOfSales"));
        tblSales.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("total"));
        tblSales.getColumns().get(6).setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tblSales.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("orderId"));
        
        
        getSaleBySelection();

        cmbSearchBy.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getSaleBySelection();
            }
        });
        
        txtKeyword.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                getSaleBySelection();
            }
        });
    }
    
    public void getSaleBySelection(){
        try {
            String value = cmbSearchBy.getValue();
            
            ArrayList<SaleDTO> saleDTOs = null;
            
            ObservableList<ManageSaleTM> items = tblSales.getItems();
            items.removeAll(items);
            
            switch (value) {
                case "Sales ID":
                    saleDTOs = SaleBO.getSaleBySaleId(txtKeyword.getText() + "%");
                    break;
                case "Name":
                    saleDTOs = SaleBO.getSaleByName(txtKeyword.getText() + "%");
                    break;
                case "Contact":
                    saleDTOs = SaleBO.getSaleByContact(txtKeyword.getText() + "%");
                    break;
                case "Date of Sales":
                    saleDTOs = SaleBO.getSaleByDateOfSale(txtKeyword.getText() + "%");
                    break;
                case "Order ID":
                    saleDTOs = SaleBO.getSaleByOrderId(txtKeyword.getText() + "%");
                
            }

            for (SaleDTO saleDTO : saleDTOs) {
                ManageSaleTM managesaleTM = new ManageSaleTM(saleDTO.getSalesId(), saleDTO.getName(), 
                        saleDTO.getContact(), saleDTO.getDateOfSales(), saleDTO.getTotal(), 
                        saleDTO.getEmployeeId(), saleDTO.getOrderId());
                items.add(managesaleTM);

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
        try {
            String saleId = tblSales.getSelectionModel().getSelectedItem().getSalesId();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/lk/ijse/rj/view/EditSale.fxml"));
            Parent parent = loader.load();
            
            EditSaleController editSaleController = loader.getController();
            editSaleController.initVariable(saleId, manageSaleControllerAddress);
            
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
