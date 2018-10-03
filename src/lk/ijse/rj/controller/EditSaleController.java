/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.business.custom.SaleBO;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.SaleDTO;
import lk.ijse.rj.dto.SaleItemDTO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.view.util.tblmodel.BillItemTM;

/**
 *
 * @author Ilman Iqbal
 */
public class EditSaleController implements Initializable {

    @FXML
    private JFXTextField txtSalesId;
    @FXML
    private JFXComboBox<String> cmbEmployeeId;
    @FXML
    private JFXComboBox<String> cmbOrderId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private Label lblTotal2;
    @FXML
    private Label lblOBalance;
    @FXML
    private JFXTextField txtBuyGold;
    @FXML
    private FontAwesomeIcon btnClose;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXButton btnSave;
    @FXML
    private TableView<BillItemTM> tblSale;
    @FXML
    private Label lblTotal1;
    @FXML
    private AnchorPane root;
    @FXML
    private JFXDatePicker dateDateOfSales;
    @FXML
    private Label lblNTotal;
    @FXML
    private JFXButton btnUpdateSales;
    @FXML
    private JFXDatePicker dateDateOfCancellation;
    @FXML
    private JFXRadioButton rbCancelled;

    private ManageSaleController manageSaleControllerAddress;
    private SaleBO saleBO;
    private StockBO stockBO;
    private OrderBO orderBO;
    @FXML
    private JFXTextField txtItemId;

    public EditSaleController() {
        saleBO = (SaleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SALE);
        stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    void initVariable(String saleId, ManageSaleController manageSaleControllerAddress) {
        this.manageSaleControllerAddress = manageSaleControllerAddress;

        try {
            SaleDTO saleDTO = saleBO.findSale(saleId);

            txtSalesId.setText(saleDTO.getSalesId());

            cmbEmployeeId.setEditable(true);
            cmbEmployeeId.setValue(saleDTO.getEmployeeId());
            cmbEmployeeId.setDisable(true);
            cmbEmployeeId.setStyle("-fx-opacity: 1;");

            cmbOrderId.setEditable(true);
            cmbOrderId.setValue(saleDTO.getOrderId());
            cmbOrderId.setDisable(true);
            cmbOrderId.setStyle("-fx-opacity: 1;");

            txtName.setText(saleDTO.getName());
            txtContact.setText(saleDTO.getContact());
            dateDateOfSales.setValue(saleDTO.getDateOfSales().toLocalDate());
            txtAddress.setText(saleDTO.getAddress());
            lblTotal1.setText(saleDTO.getTotal().toString());
            lblTotal2.setText(saleDTO.getTotal().toString());
            txtBuyGold.setText(saleDTO.getBuyGold().toString());
            lblNTotal.setText(saleDTO.getNetTotal().toString());

            if (saleDTO.getOrderId() != null) {
                OrderDTO orderDTO = orderBO.findOrder(saleDTO.getOrderId());
                lblOBalance.setText(orderDTO.getBalance().toString());
            } else {
                lblOBalance.setText("0");
            }

            if (saleDTO.getDateOfCancellation() != null) {
                rbCancelled.setSelected(true);
                dateDateOfCancellation.setValue(saleDTO.getDateOfCancellation().toLocalDate());
            } else {
                rbCancelled.setSelected(false);
                dateDateOfCancellation.setDisable(true);
            }

            ArrayList<SaleItemDTO> saleItemDTOs = saleBO.getSaleItemBySaleId(saleId);
            ObservableList<BillItemTM> items = tblSale.getItems();
            items.removeAll(items);
            for (SaleItemDTO saleItemDTO : saleItemDTOs) {
                String itemId = saleItemDTO.getItemId();
                StockDTO stockDTO = stockBO.findStock(itemId);
                BillItemTM orderItemTM = new BillItemTM(saleItemDTO.getItemId(), saleItemDTO.getDescription(),
                        stockDTO.getWeight(), saleItemDTO.getPrice());
                items.add(orderItemTM);
            }

        } catch (Exception ex) {
            Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        txtItemId.setDisable(true);
        
        tblSale.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblSale.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblSale.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblSale.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        tblSale.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BillItemTM>() {
            @Override
            public void changed(ObservableValue<? extends BillItemTM> observable, BillItemTM oldValue, BillItemTM newValue) {
                if (newValue == null) {
                    return;
                }

                txtItemId.setText(newValue.getItemId());
                txtDescription.setText(newValue.getDescription());
                txtPrice.setText(newValue.getPrice().toString());

            }
        });

        txtPrice.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }

                if (!newValue.matches("\\d*")) {
                    txtPrice.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

        tblSale.getItems().addListener(new ListChangeListener<BillItemTM>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends BillItemTM> c) {
                BigDecimal total = BigDecimal.ZERO;

                for (BillItemTM item : tblSale.getItems()) {
                    total = total.add(item.getPrice());
                }
                lblTotal1.setText(total.toString());

                lblTotal2.setText(total.toString());

                calculateNetTotal();
            }

        });

        txtBuyGold.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }

                if (newValue.trim().isEmpty()) {
                    txtBuyGold.setText("0");
                }

                if (!newValue.matches("\\d*")) {
                    txtBuyGold.setText(newValue.replaceAll("[^\\d]", ""));
                }

                calculateNetTotal();
                
//                if (!lblOBalance.getText().trim().isEmpty()) {
//                    calculateNetTotal();
//                }

            }

        });

        rbCancelled.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    dateDateOfCancellation.setDisable(false);
                }
            }
        });
    }
    
    private void calculateNetTotal(){
        try {
            SaleDTO saleDTO = saleBO.findSale(txtSalesId.getText());
            BigDecimal oldTotal = saleDTO.getTotal();
            BigDecimal newTotal = new BigDecimal(lblTotal2.getText());
            BigDecimal orderBalance = new BigDecimal(lblOBalance.getText());
            BigDecimal buyGold = new BigDecimal(txtBuyGold.getText());
            
            if (oldTotal != newTotal) {
                BigDecimal totalDifference = newTotal.subtract(oldTotal);
                lblNTotal.setText((totalDifference.add(orderBalance)).subtract(totalDifference).toString());
            }
            else{
                lblNTotal.setText(orderBalance.subtract(buyGold).toString());
            }
        } catch (Exception ex) {
            Logger.getLogger(EditSaleController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @FXML
    private void btnClose_OnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        tblSale.getSelectionModel().clearSelection();
        ObservableList<BillItemTM> items = tblSale.getItems();

        for (BillItemTM item : items) {
            String itemId = item.getItemId();

            if (itemId.equals(txtItemId.getText())) {
                item.setDescription(txtDescription.getText());
                item.setPrice(new BigDecimal(txtPrice.getText()));
                int indexOf = items.indexOf(item);
                items.set(indexOf, item);
                txtItemId.clear();
                txtDescription.clear();
                txtPrice.clear();
            }
        }
    }


    @FXML
    private void btnUpdateSales_OnAction(ActionEvent event) {
        ObservableList<BillItemTM> items = tblSale.getItems();
        ArrayList<SaleItemDTO> saleItemDTOs = new ArrayList<>();
        
        for (BillItemTM item : items) {
            SaleItemDTO saleItemDTO = new SaleItemDTO(item.getItemId(), item.getDescription(), 
                    item.getPrice());
            saleItemDTOs.add(saleItemDTO);
        }

        SaleDTO saleDTO = new SaleDTO(txtSalesId.getText(), cmbEmployeeId.getSelectionModel().getSelectedItem(),
                cmbOrderId.getSelectionModel().getSelectedItem(), txtName.getText(), txtContact.getText(),
                Date.valueOf(dateDateOfSales.getValue()), txtAddress.getText(), new BigDecimal(lblTotal1.getText()), 
                new BigDecimal(txtBuyGold.getText()), new BigDecimal(lblNTotal.getText()), 
                Date.valueOf(dateDateOfCancellation.getValue()), saleItemDTOs);

        try {
            boolean result = saleBO.updateSale(saleDTO);
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                manageSaleControllerAddress.getSaleBySelection();
                btnClose_OnMouseClicked(null);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

}
