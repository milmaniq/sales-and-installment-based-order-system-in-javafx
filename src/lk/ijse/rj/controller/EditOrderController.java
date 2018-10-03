/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
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
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.OrderBO;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dto.OrderDTO;
import lk.ijse.rj.dto.OrderItemDTO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.view.util.tblmodel.BillItemTM;

/**
 *
 * @author Ilman Iqbal
 */
public class EditOrderController implements Initializable {

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
    private JFXTextField txtBuyGold;
    @FXML
    private JFXTextField txtAdvance;
    @FXML
    private Label lblTotal2;
    @FXML
    private JFXButton btnUpdateOrder;
    @FXML
    private FontAwesomeIcon btnClose;
    @FXML
    private JFXTextField txtItemId;
    @FXML
    private JFXTextField txtDescription;
    @FXML
    private JFXTextField txtPrice;
    @FXML
    private JFXButton btnSave;
    @FXML
    private TableView<BillItemTM> tblOrder;
    @FXML
    private Label lblTotal1;
    @FXML
    private Label lblBalance;
    @FXML
    private JFXDatePicker dateDateOfDelivery;
    @FXML
    private AnchorPane root;
    @FXML
    private Label lblNTotal;
    @FXML
    private JFXRadioButton rbDelivered;
    @FXML
    private ToggleGroup OrderStatus;
    @FXML
    private JFXRadioButton rbCancelled;
    @FXML
    private JFXDatePicker dateDateOfCancellation;
    @FXML
    private JFXDatePicker dateDateOfActualDelivery;

    private OrderBO orderBO;
    private StockBO stockBO;
    private ManageOrderController manageOrderControllerAddress;

    public EditOrderController() {
        orderBO = (OrderBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.ORDER);
        stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
    }

    void initVariable(String orderId, ManageOrderController manageOrderControllerAddress) {
        this.manageOrderControllerAddress = manageOrderControllerAddress;

        try {
            OrderDTO orderDTO = orderBO.findOrder(orderId);

            if (orderDTO == null) {
                return;
            }

            txtOrderId.setText(orderDTO.getOrderId());
            
            cmbEmployeeId.setEditable(true);
            cmbEmployeeId.setValue(orderDTO.getEmployeeId());
            
            txtName.setText(orderDTO.getName());
            txtAddress.setText(orderDTO.getAddress());
            txtContact.setText(orderDTO.getContact());

            if (orderDTO.getDateOfOrder() == null) {
                dateDateOfOrder.setValue(null);
            } else {
                dateDateOfOrder.setValue(orderDTO.getDateOfOrder().toLocalDate());
            }

            if (orderDTO.getDateOfDelivery() == null) {
                dateDateOfDelivery.setValue(null);
            } else {
                dateDateOfDelivery.setValue(orderDTO.getDateOfDelivery().toLocalDate());
            }

            lblTotal1.setText(orderDTO.getTotal().toString());
            lblTotal2.setText(orderDTO.getTotal().toString());
            txtBuyGold.setText(orderDTO.getBuyGold().toString());
            txtAdvance.setText(orderDTO.getAdvance().toString());
            lblNTotal.setText(orderDTO.getNetTotal().toString());
            lblBalance.setText(orderDTO.getBalance().toString());

            if (orderDTO.getDateOfActualDelivery() != null) {
                rbDelivered.setSelected(true);
                dateDateOfActualDelivery.setValue(orderDTO.getDateOfActualDelivery().toLocalDate());
            }

            if (orderDTO.getDateOfCancellation() != null) {
                rbCancelled.setSelected(true);
                dateDateOfCancellation.setValue(orderDTO.getDateOfCancellation().toLocalDate());
            }

            ArrayList<OrderItemDTO> orderItemDTOs = orderBO.getOrderItemsByOrderId(orderId);
            ObservableList<BillItemTM> items = tblOrder.getItems();
            items.removeAll(items);
            for (OrderItemDTO orderItemDTO : orderItemDTOs) {
                String itemId = orderItemDTO.getItemId();

                StockDTO stockDTO = stockBO.findStock(itemId);
                
                if (stockDTO == null) {
                    return;
                }

                BillItemTM billItemTM = new BillItemTM(orderItemDTO.getItemId(), orderItemDTO.getDescription(),
                        stockDTO.getWeight(), orderItemDTO.getPrice());
                items.add(billItemTM);
            }

        } catch (Exception ex) {
            Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbEmployeeId.setDisable(true);
        txtOrderId.setDisable(true);
        txtItemId.setDisable(true);
        dateDateOfActualDelivery.setDisable(true);
        dateDateOfCancellation.setDisable(true);

        tblOrder.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblOrder.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblOrder.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblOrder.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("price"));

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

                if (!txtAdvance.getText().trim().isEmpty()) {
                    calculateBalance();
                }

            }

        });

        txtAdvance.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue == null) {
                    return;
                }

                if (!newValue.matches("\\d*")) {
                    txtAdvance.setText(newValue.replaceAll("[^\\d]", ""));
                }
                calculateBalance();
            }
        });

        tblOrder.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<BillItemTM>() {
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

        rbCancelled.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    dateDateOfCancellation.setDisable(false);
                    dateDateOfActualDelivery.setDisable(true);
                    dateDateOfActualDelivery.setValue(null);
                }
            }
        });

        rbDelivered.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if (newValue) {
                    dateDateOfActualDelivery.setDisable(false);
                    dateDateOfCancellation.setDisable(true);
                    dateDateOfCancellation.setValue(null);
                }
            }
        });

    }

    public void calculateBalance() {
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
    private void btnClose_OnMouseClicked(MouseEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        
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
        
        tblOrder.getSelectionModel().clearSelection();
        ObservableList<BillItemTM> items = tblOrder.getItems();

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
    private void btnUpdateOrder_OnAction(ActionEvent event) {

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

        Date dateOfDelivery;
        if (dateDateOfDelivery.getValue() == null) {
            dateOfDelivery = null;
        } else {
            dateOfDelivery = Date.valueOf(dateDateOfDelivery.getValue());
        }
        
        Date dateOfActualDelivery;
        if (dateDateOfActualDelivery.getValue() == null) {
            dateOfActualDelivery = null;
        }
        else{
            dateOfActualDelivery = Date.valueOf(dateDateOfActualDelivery.getValue());
        }
        
        Date dateOfCancellation;
        if (dateDateOfCancellation.getValue() == null) {
            dateOfCancellation = null;   
        }
        else{
            dateOfCancellation = Date.valueOf(dateDateOfCancellation.getValue());
        }

        ObservableList<BillItemTM> billItemTMs = tblOrder.getItems();
        ArrayList<OrderItemDTO> orderItemDTOs = new ArrayList<>();

        for (BillItemTM billItemTM : billItemTMs) {
            OrderItemDTO orderItemDTO = new OrderItemDTO(billItemTM.getItemId(), billItemTM.getDescription(),
                    billItemTM.getPrice());
            orderItemDTOs.add(orderItemDTO);
        }

        OrderDTO orderDTO = new OrderDTO(txtOrderId.getText(), cmbEmployeeId.getValue(), txtName.getText(),
                txtAddress.getText(), txtContact.getText(), Date.valueOf(dateDateOfOrder.getValue()),
                dateOfDelivery, new BigDecimal(lblTotal2.getText()),
                new BigDecimal(txtBuyGold.getText()), new BigDecimal(txtAdvance.getText()),
                new BigDecimal(lblNTotal.getText()), new BigDecimal(lblBalance.getText()),
                dateOfActualDelivery, dateOfCancellation, orderItemDTOs);
        try {
            boolean update = orderBO.updateOrder(orderDTO);
            if (update) {
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                manageOrderControllerAddress.getOrderBySelection();
                btnClose_OnMouseClicked(null);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            Logger.getLogger(EditOrderController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
