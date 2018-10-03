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
import com.jfoenix.validation.RequiredFieldValidator;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.EmployeeBO;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dto.EmployeeDTO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.OrderItemDTO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.BillItemTM;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Ilman Iqbal
 */
public class NewOrderController implements Initializable {

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
    private TableView<BillItemTM> tblOrder;
    @FXML
    private Label lblTotal1;
    @FXML
    private JFXTextField txtOrderId;
    @FXML
    private JFXComboBox<String> cmbEmployeeId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXDatePicker dateDateOfOrder;
    @FXML
    private JFXTextField txtAddress;
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
    @FXML
    private Label lblNTotal;
    @FXML
    private JFXTextField txtBuyGold;
    @FXML
    private JFXTextField txtAdvance;
    @FXML
    private Label lblTotal2;
    @FXML
    private Label lblBalance;
    @FXML
    private JFXDatePicker dateDateOfDelivery;
    @FXML
    private JFXButton btnPlaceOrder;

    /**
     * Initializes the controller class.
     */
    private String weight;

    private OrderBO orderBO;
    private EmployeeBO employeeBO;
    private StockBO stockBO;
    

    public NewOrderController() {
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
        employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
        stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
    }

    private void generateOrderId() {
        try {
            ArrayList<OrderDTO> orderDTOs = orderBO.getAllOrders();
            int max = 0;
            for (OrderDTO orderDTO : orderDTOs) {
                int val = Integer.parseInt(orderDTO.getOrderId().substring(1));
                if (val > max) {
                    max = val;
                }
            }

            max = max + 1;
            txtOrderId.setText("O" + max);

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
    public void initialize(URL url, ResourceBundle rb) {

        txtOrderId.setDisable(true);

        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

        generateOrderId();
        loadAllEmployeeId();
        loadAvailableItemId();

        cmbEmployeeId.setEditable(true);
        cmbItemId.setEditable(true);

        TextFields.bindAutoCompletion(cmbEmployeeId.getEditor(), cmbEmployeeId.getItems());
        TextFields.bindAutoCompletion(cmbItemId.getEditor(), cmbItemId.getItems());

        lblTotal1.setText("0");
        lblTotal2.setText("0");
        txtBuyGold.setText("0");
        txtAdvance.setText("0");
        lblNTotal.setText("0");
        lblBalance.setText("0");

        cmbEmployeeId.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == null) {
                    return;
                }

                if (newValue) {
                    cmbEmployeeId.hide();
                }

            }
        });

        cmbItemId.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue == null) {
                    return;
                }

                if (newValue) {
                    cmbItemId.hide();
                }

            }
        });
        
//        cmbItemId.valueProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (newValue == null) {
//                    return;
//                }
//                
//                if (!newValue.matches("\\w*")) {
//                    cmbItemId.setValue(newValue.replaceAll("[^\\w]", ""));
//                }
//            }
//        });
        

        cmbItemId.getEditor().textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }
                
                if (!newValue.matches("\\w*")) {
                    cmbItemId.getEditor().setText(newValue.replaceAll("[^\\w]", ""));
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
                    if (stockDTO == null) {
                        return;
                    }
                    weight = stockDTO.getWeight();
                } catch (Exception ex) {
                    Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
                }
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

        tblOrder.getItems().addListener(new ListChangeListener<BillItemTM>() {
            @Override
            public void onChanged(ListChangeListener.Change<? extends BillItemTM> c) {
                BigDecimal total = BigDecimal.ZERO;

                for (BillItemTM item : tblOrder.getItems()) {
                    total = total.add(item.getPrice());
                }
                lblTotal1.setText(total.toString());

                lblTotal2.setText(total.toString());

                calculateBalance();
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

                calculateBalance();
            }
        });

        txtAdvance.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }

                if (newValue.trim().isEmpty()) {
                    txtAdvance.setText("0");
                }

                if (!newValue.matches("\\d*")) {
                    txtAdvance.setText(newValue.replaceAll("[^\\d]", ""));
                }

                calculateBalance();
            }
        });
    }
    
    private void calculateBalance() {
        BigDecimal total = new BigDecimal(lblTotal2.getText());
        BigDecimal buyGold = new BigDecimal(txtBuyGold.getText());
        BigDecimal advance = new BigDecimal(txtAdvance.getText());

        BigDecimal received = buyGold.add(advance);

        BigDecimal netTotal = total.subtract(received);
        lblNTotal.setText(netTotal.toString());

        if (netTotal.intValue() > 0) {
            lblBalance.setText(netTotal.toString());
        } else {
            lblBalance.setText("0");
        }
    }

    @FXML
    private void btnAdd_OnAction(ActionEvent event) {

        if (cmbItemId.getValue() == null || cmbItemId.getValue().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter Item ID", ButtonType.OK).show();
            cmbItemId.requestFocus();
            return;
        }

        if (txtDescription.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter Description", ButtonType.OK).show();
            txtDescription.requestFocus();
            return;
        }

        if (txtPrice.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter Price", ButtonType.OK).show();
            txtPrice.requestFocus();
            return;
        }

        boolean isValid = false;
        for (String item : cmbItemId.getItems()) {

            if (cmbItemId.getValue().equals(item)) {
                isValid = true;
            }
        }
        
        if (isValid == false) {
            new Alert(Alert.AlertType.ERROR, "Please enter an Item ID from the Stock", ButtonType.OK).show();
            cmbItemId.requestFocus();
            return;
        }
        
        for (String item : cmbItemId.getItems()) {

//            Platform.runLater(() -> {
                if (cmbItemId.getValue().equals(item)) {
                    ObservableList<BillItemTM> items = tblOrder.getItems();

                    BillItemTM billItemTM = new BillItemTM(cmbItemId.getValue(), txtDescription.getText(), weight,
                            new BigDecimal(txtPrice.getText()));

                    items.add(billItemTM);

                    int selectedIndex = cmbItemId.getSelectionModel().getSelectedIndex();
                    cmbItemId.getItems().remove(selectedIndex);
                    cmbItemId.getSelectionModel().clearSelection();

                    txtDescription.clear();
                    txtPrice.clear();

                    cmbItemId.requestFocus();
                    return;
                }
//            });
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
    private void btnRemove_OnAction(ActionEvent event) {
        int selectedIndex = tblOrder.getSelectionModel().getSelectedIndex();

        if (selectedIndex == -1) {
            return;
        }

        cmbItemId.getItems().add(tblOrder.getSelectionModel().getSelectedItem().getItemId());

        tblOrder.getItems().remove(selectedIndex);

        tblOrder.getSelectionModel().clearSelection();

    }

    @FXML
    private void btnPlaceOrder_OnAction(ActionEvent event) {

        if (txtOrderId.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter Order ID", ButtonType.OK).show();
            txtOrderId.requestFocus();
            return;
        }

        if (cmbEmployeeId.getSelectionModel().getSelectedIndex() == -1) {
            new Alert(Alert.AlertType.ERROR, "Please enter Employee ID", ButtonType.OK).show();
            cmbEmployeeId.requestFocus();
            return;
        }

        if (txtName.getText().trim().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please enter Customer Name", ButtonType.OK).show();
            txtName.requestFocus();
            return;
        }

        if (dateDateOfOrder.getValue() == null) {
            new Alert(Alert.AlertType.ERROR, "Please enter Date Of Order", ButtonType.OK).show();
            dateDateOfOrder.requestFocus();
            return;
        }

        if (tblOrder.getItems().size() == 0) {
            new Alert(Alert.AlertType.ERROR, "Please add Order Items", ButtonType.OK).show();
            cmbItemId.requestFocus();
            return;
        }

        Date dateOfDelivery;
        if (dateDateOfDelivery.getValue() == null) {
            dateOfDelivery = null;
        } else {
            dateOfDelivery = Date.valueOf(dateDateOfDelivery.getValue());
        }

        ObservableList<BillItemTM> billItemTMs = tblOrder.getItems();
        ArrayList<OrderItemDTO> orderItemDTOs = new ArrayList<>();

        for (BillItemTM billItemTM : billItemTMs) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(billItemTM.getItemId(),
                    billItemTM.getDescription(), billItemTM.getPrice());
            orderItemDTOs.add(orderItemDTO);

        }

        OrderDTO orderDTO = new OrderDTO(txtOrderId.getText(), cmbEmployeeId.getSelectionModel().getSelectedItem(),
                txtName.getText(), txtAddress.getText(), txtContact.getText(), Date.valueOf(dateDateOfOrder.getValue()),
                dateOfDelivery, new BigDecimal(lblTotal1.getText()),
                new BigDecimal(txtBuyGold.getText()), new BigDecimal(txtAdvance.getText()),
                new BigDecimal(lblNTotal.getText()), new BigDecimal(lblBalance.getText()),
                null, null, orderItemDTOs);

        try {
            boolean result = orderBO.insertOrder(orderDTO);
            if (result) {
                new Alert(Alert.AlertType.INFORMATION, "Order Placed Successfully", ButtonType.OK).show();
                clearAllFields();
                generateOrderId();
                txtOrderId.requestFocus();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to Place Order", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(NewOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void clearAllFields() {
        txtOrderId.clear();
        cmbEmployeeId.getSelectionModel().clearSelection();
        txtName.clear();
        txtContact.clear();
        dateDateOfOrder.setValue(null);
        txtAddress.clear();
        cmbItemId.getSelectionModel().clearSelection();
        txtDescription.clear();
        txtPrice.clear();
        lblTotal1.setText("0");
        lblTotal2.setText("0");
        txtBuyGold.clear();
        txtAdvance.clear();
        lblNTotal.setText("0");
        lblBalance.setText("0");
        dateDateOfDelivery.setValue(null);

    }

}
