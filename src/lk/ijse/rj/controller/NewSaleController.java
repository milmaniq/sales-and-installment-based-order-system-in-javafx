/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.EmployeeBO;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.business.custom.SaleBO;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dto.EmployeeDTO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.OrderItemDTO;
import lk.ijse.rj.dto.SaleDTO;
import lk.ijse.rj.dto.SaleItemDTO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.BillItemTM;
import org.controlsfx.control.textfield.TextFields;

/**
 *
 * @author Ilman Iqbal
 */
public class NewSaleController implements Initializable {

    @FXML
    private Label lblHome;
    @FXML
    private JFXComboBox<String> cmbItemId;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnRemove;
    @FXML
    private TableView<BillItemTM> tblSales;
    @FXML
    private Label lblTotal1;
    @FXML
    private JFXTextField txtSalesId;
    @FXML
    private JFXComboBox<String> cmbEmployeeId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXDatePicker dateDateOfSales;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private JFXTextField txtBuyGold;
    @FXML
    private Label lblTotal2;
    @FXML
    private JFXButton btnMakeSales;
    @FXML
    private Label lblOBalance;
    @FXML
    private JFXComboBox<String> cmbOrderId;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblSaleNewSales;
    @FXML
    private Label lblSaleManageSales;
    @FXML
    private Label lblNTotal;
    @FXML
    private Label lblSaleReports;

    private String weight;

    private EmployeeBO employeeBO;
    private StockBO stockBO;
    private SaleBO saleBO;
    private OrderBO orderBO;
    @FXML
    private Pane paneSaleBill;
    
    

    public NewSaleController() {
        employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
        stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
        saleBO = (SaleBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SALE);
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    private void generateSaleId() {
        try {
            ArrayList<SaleDTO> saleDTOs = saleBO.getAllSales();
            int max = 0;
            for (SaleDTO saleDTO : saleDTOs) {
                int val = Integer.parseInt(saleDTO.getSalesId().substring(1));
                if (val > max) {
                    max = val;
                }
            }

            max = max + 1;
            txtSalesId.setText("S" + max);

        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllEmployeeId() {
        try {
            ArrayList<EmployeeDTO> employeeDTOs = employeeBO.getAllEmployees();
            ObservableList<String> items = cmbEmployeeId.getItems();
            items.removeAll(items);
            for (EmployeeDTO employeeDTO : employeeDTOs) {
                items.add(employeeDTO.getEmployeeId());
            }
        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void loadAllOrderId() {
        try {
            ArrayList<OrderDTO> orderDTOs = orderBO.getAllOrders();
            ObservableList<String> items = cmbOrderId.getItems();
            items.removeAll(items);
            for (OrderDTO orderDTO : orderDTOs) {
                items.add(orderDTO.getOrderId());
            }
        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAvailableItemId() {
        try {
            ArrayList<StockDTO> stockDTOs = stockBO.getAvailableStocks();
            ObservableList<String> items = cmbItemId.getItems();
            items.removeAll(items);
            for (StockDTO stockDTO : stockDTOs) {
                items.add(stockDTO.getItemId());
            }
        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblSales.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblSales.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblSales.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblSales.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        generateSaleId();
        loadAllEmployeeId();
        loadAvailableItemId();
        loadAllOrderId();

        cmbEmployeeId.setEditable(true);
        cmbItemId.setEditable(true);

        TextFields.bindAutoCompletion(cmbEmployeeId.getEditor(), cmbEmployeeId.getItems());
        TextFields.bindAutoCompletion(cmbItemId.getEditor(), cmbItemId.getItems());

        lblTotal1.setText("0");
        lblTotal2.setText("0");
        lblOBalance.setText("0");
        txtBuyGold.setText("0");
        lblNTotal.setText("0");

        cmbOrderId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                
                if (newValue == null) {
                    //paneSaleBill.setDisable(false);
                    return;
                }
                
                try {
                    OrderDTO orderDTO = orderBO.findOrder(newValue);
                    lblOBalance.setText(orderDTO.getBalance().toString());
                    
                    ArrayList<OrderItemDTO> orderItemDTOs = orderBO.getOrderItemsByOrderId(newValue);
                    ObservableList<BillItemTM> items = tblSales.getItems();
                    items.removeAll(items);
                    for (OrderItemDTO orderItemDTO : orderItemDTOs) {
                        String itemId = orderItemDTO.getItemId();
                        StockDTO stockDTO = stockBO.findStock(itemId);
                        BillItemTM orderItemTM = new BillItemTM(orderItemDTO.getItemId(), orderItemDTO.getDescription(),
                                stockDTO.getWeight(), orderItemDTO.getPrice());
                        items.add(orderItemTM);
                    }
                    paneSaleBill.setDisable(true);
                    
                } catch (Exception ex) {
                    Logger.getLogger(NewSaleController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        cmbItemId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

                if (newValue == null) {
                    return;
                }
                try {
                    StockDTO stockDTO = stockBO.findStock(newValue);
                    if (stockDTO == null){
                        return;
                    }
                    weight = stockDTO.getWeight();
                } catch (Exception ex) {
                    Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        tblSales.getItems().addListener(new ListChangeListener<BillItemTM>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends BillItemTM> c) {
                BigDecimal total = BigDecimal.ZERO;

                for (BillItemTM item : tblSales.getItems()) {
                    total = total.add(item.getPrice());
                }
                lblTotal1.setText(total.toString());

                lblTotal2.setText(total.toString());
                
                calculateNetTotal();
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
            }
        });
        
        tblSales.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BillItemTM>() {
            @Override
            public void changed(ObservableValue<? extends BillItemTM> observable, BillItemTM oldValue, BillItemTM newValue) {
                if (newValue == null) {
                    return;
                }

                cmbItemId.getSelectionModel().select(newValue.getItemId());
                txtDescription.setText(newValue.getDescription());
                txtPrice.setText(newValue.getPrice().toString());

            }
        });
    }

    private void calculateNetTotal() {
        BigDecimal total = new BigDecimal(lblTotal2.getText());
        BigDecimal orderBalance = new BigDecimal(lblOBalance.getText());
        BigDecimal buyGold = new BigDecimal(txtBuyGold.getText());
        
        if (cmbOrderId.getSelectionModel().getSelectedItem() == null) {
            
            lblNTotal.setText(total.subtract(buyGold).toString());
        }
        else{
            lblNTotal.setText(orderBalance.subtract(buyGold).toString());
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
    private void btnAdd_OnAction(ActionEvent event) {
        for (String item : cmbItemId.getItems()) {
            if (cmbItemId.getValue().equals(item)) {

                ObservableList<BillItemTM> items = tblSales.getItems();
                
                BillItemTM orderItemTM = new BillItemTM(cmbItemId.getValue(), txtDescription.getText(), weight,
                        new BigDecimal(txtPrice.getText()));
                
                items.add(orderItemTM);
                
                int selectedIndex = cmbItemId.getSelectionModel().getSelectedIndex();
                cmbItemId.getItems().remove(selectedIndex);

                cmbItemId.getSelectionModel().clearSelection();
                txtDescription.clear();
                txtPrice.clear();

                cmbItemId.requestFocus();
            }
        }

    }

    @FXML
    private void btnRemove_OnAction(ActionEvent event) {
        int selectedIndex = tblSales.getSelectionModel().getSelectedIndex();
        
        cmbItemId.getItems().add(tblSales.getSelectionModel().getSelectedItem().getItemId());
        
        tblSales.getItems().remove(selectedIndex);
    }

    

    @FXML
    private void btnMakeSales_OnAction(ActionEvent event) {
        ObservableList<BillItemTM> items = tblSales.getItems();
        ArrayList<SaleItemDTO> saleItemDTOs = new ArrayList<>();

        for (BillItemTM item : items) {
            SaleItemDTO saleItemDTO = new SaleItemDTO(item.getItemId(),
                    item.getDescription(), item.getPrice());
            saleItemDTOs.add(saleItemDTO);

        }
        
        String orderId = null;
        
        if (cmbEmployeeId.getSelectionModel().getSelectedItem() != null) {
            orderId = cmbEmployeeId.getSelectionModel().getSelectedItem();
        }

        
        SaleDTO saleDTO = new SaleDTO(txtSalesId.getText(), orderId,
                cmbOrderId.getSelectionModel().getSelectedItem(), txtName.getText(), txtContact.getText(),
                Date.valueOf(dateDateOfSales.getValue()), txtAddress.getText(),
                new BigDecimal(lblTotal1.getText()), new BigDecimal(txtBuyGold.getText()),
                new BigDecimal(lblNTotal.getText()), null, saleItemDTOs);

        try {
            boolean result = saleBO.insertSale(saleDTO);
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                clearAllFields();
                txtSalesId.requestFocus();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void clearAllFields(){
        txtSalesId.clear();
        cmbEmployeeId.getSelectionModel().clearSelection();
        cmbOrderId.getSelectionModel().clearSelection();
        txtName.clear();
        txtContact.clear();
        dateDateOfSales.setValue(null);
        txtAddress.clear();
        cmbItemId.getSelectionModel().clearSelection();
        txtDescription.clear();
        txtPrice.clear();
        lblTotal1.setText("0");
        lblTotal2.setText("0");
        lblOBalance.setText("0");
        txtBuyGold.clear();
        lblNTotal.setText("0");
    }


   

}
