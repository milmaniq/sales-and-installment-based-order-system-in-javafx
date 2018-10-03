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
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.business.custom.OrderBalancePaymentBO;
import lk.ijse.rj.dto.OrderBalancePaymentDTO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.BalancePaymentTM;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author Ilman Iqbal
 */
public class OrderBalancePaymentController implements Initializable {

    @FXML
    private Label lblHome;
    @FXML
    private JFXDatePicker dateDateOfPayment;
    @FXML
    private JFXTextField txtAmount;
    @FXML
    private JFXButton btnClear;
    @FXML
    private TableView<BalancePaymentTM> tblBalancePayments;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblOrderNewOrder;
    @FXML
    private Label lblOrderBalancePayments;
    @FXML
    private Label lblOrderManageOrders;
    @FXML
    private JFXComboBox<String> cmbOrderId;
    @FXML
    private Label lblOrderReports;
    @FXML
    private JFXButton btnAdd;

    private OrderBO orderBO;
    private OrderBalancePaymentBO orderBalancePaymentBO;
    @FXML
    private Label lblBalance;
    @FXML
    private Label lblBal;
    @FXML
    private JFXButton btnRemove;

    public OrderBalancePaymentController() {
        orderBalancePaymentBO = (OrderBalancePaymentBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER_BALANCE_PAYMENT);
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
    }

    /**
     * Initializes the controller class.
     */
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

    private void getBalanceByOrderId(String orderId) {
        try {
            OrderDTO orderDTO = orderBO.findOrder(orderId);
            lblBal.setVisible(true);
            lblBalance.setVisible(true);
            lblBalance.setText(orderDTO.getBalance().toString());
        } catch (Exception ex) {
            Logger.getLogger(OrderBalancePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void loadAllOrderBalancePaymentByOrderId() {
        try {
            ArrayList<OrderBalancePaymentDTO> orderBalancePaymentDTOs = orderBalancePaymentBO.getAllOrderBalancePaymentByOrderId(cmbOrderId.getSelectionModel().getSelectedItem());
            ObservableList<BalancePaymentTM> items = tblBalancePayments.getItems();
            items.removeAll(items);
            for (OrderBalancePaymentDTO orderBalancePaymentDTO : orderBalancePaymentDTOs) {
                BalancePaymentTM balancePaymentTM = new BalancePaymentTM(orderBalancePaymentDTO.getInc(),
                        orderBalancePaymentDTO.getDateOfPayment(), orderBalancePaymentDTO.getAmount());
                items.add(balancePaymentTM);
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderBalancePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tblBalancePayments.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("inc"));
        tblBalancePayments.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("dateOfPayment"));
        tblBalancePayments.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("amountPaid"));

        lblBal.setVisible(false);
        lblBalance.setVisible(false);

        loadAllOrderId();

        cmbOrderId.setEditable(true);
        TextFields.bindAutoCompletion(cmbOrderId.getEditor(), cmbOrderId.getItems());

        cmbOrderId.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }
                getBalanceByOrderId(newValue);
                loadAllOrderBalancePaymentByOrderId();
            }
        });

        txtAmount.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }

                if (!newValue.matches("\\d*")) {
                    txtAmount.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

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
        if (new BigDecimal(lblBalance.getText()).compareTo(BigDecimal.ZERO) == 1) {
            OrderBalancePaymentDTO orderBalancePaymentDTO = new OrderBalancePaymentDTO(
                    cmbOrderId.getSelectionModel().getSelectedItem(),
                    Date.valueOf(dateDateOfPayment.getValue()), new BigDecimal(txtAmount.getText()));

            try {
                boolean result = orderBalancePaymentBO.insertOrderBalancePayment(orderBalancePaymentDTO);
                if (result) {
                    new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                    getBalanceByOrderId(cmbOrderId.getSelectionModel().getSelectedItem());
                    loadAllOrderBalancePaymentByOrderId();
                    txtAmount.clear();
                    dateDateOfPayment.setValue(null);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(OrderBalancePaymentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void btnRemove_OnAction(ActionEvent event) {
        BalancePaymentTM selectedItem = tblBalancePayments.getSelectionModel().getSelectedItem();

        OrderBalancePaymentDTO orderBalancePaymentDTO = new OrderBalancePaymentDTO(
                cmbOrderId.getSelectionModel().getSelectedItem(), selectedItem.getDateOfPayment(),
                selectedItem.getAmountPaid());

        try {
            boolean delete = orderBalancePaymentBO.deleteOrderBalancePayment(orderBalancePaymentDTO);
            if (delete) {
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                getBalanceByOrderId(cmbOrderId.getSelectionModel().getSelectedItem());
                loadAllOrderBalancePaymentByOrderId();
                txtAmount.clear();
                dateDateOfPayment.setValue(null);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                loadAllOrderBalancePaymentByOrderId();
            }
        } catch (Exception ex) {
            Logger.getLogger(OrderBalancePaymentController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnClear_OnAction(ActionEvent event) {
        lblBal.setVisible(false);
        lblBalance.setVisible(false);
        cmbOrderId.getSelectionModel().clearSelection();
        txtAmount.clear();
        dateDateOfPayment.setValue(null);
    }

}
