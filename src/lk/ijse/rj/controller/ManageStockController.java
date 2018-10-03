/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.rj.business.BOFactory;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dto.StockDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.StockTM;

/**
 *
 * @author Ilman Iqbal
 */
public class ManageStockController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblHome;
    @FXML
    private JFXTextField txtItemId;
    @FXML
    private JFXTextField txtWeight;
    @FXML
    private JFXDatePicker dateDateAdded;
    @FXML
    private JFXTextField txtRate;
    @FXML
    private TableView<StockTM> tblItems;
    @FXML
    private Label lblStockManageStock;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXTextField txtDescription;

    private boolean isUpdate;
    private StockBO stockBO;
    @FXML
    private JFXButton btnClear;

    public ManageStockController() {
        stockBO = (StockBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.STOCK);
    }

    private void loadAllItems() {
        try {
            ArrayList<StockDTO> allStocks = stockBO.getAllStocks();
            ObservableList<StockTM> items = tblItems.getItems();
            items.removeAll(items);
            for (StockDTO allStock : allStocks) {
                StockTM stockTM = new StockTM(allStock.getItemId(), allStock.getDescription(), allStock.getWeight(), allStock.getRate(), allStock.getDateAdded());
                items.add(stockTM);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblItems.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("itemId"));
        tblItems.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("description"));
        tblItems.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("weight"));
        tblItems.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("rate"));
        tblItems.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("dateAdded"));

        loadAllItems();

        tblItems.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<StockTM>() {
            @Override
            public void changed(ObservableValue<? extends StockTM> observable, StockTM oldValue, StockTM newValue) {
                if (newValue == null) {
                    return;
                }
                
                txtItemId.setText(newValue.getItemId());
                txtDescription.setText(newValue.getDescription());
                txtWeight.setText(newValue.getWeight());
                txtRate.setText(newValue.getRate().toString());
                dateDateAdded.setValue(newValue.getDateAdded().toLocalDate());
                isUpdate = true;
                txtItemId.setDisable(true);
            }
        });
        
        txtRate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtRate.setText(newValue.replaceAll("[^\\d]", ""));
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

    private void clearFields() {
        txtItemId.clear();
        txtDescription.clear();
        txtWeight.clear();
        txtRate.clear();
        dateDateAdded.setValue(null);
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        StockDTO dto = new StockDTO(txtItemId.getText(), txtDescription.getText(), txtWeight.getText(), new BigDecimal(txtRate.getText()), Date.valueOf(dateDateAdded.getValue()));
        if (!isUpdate) {

            try {
                boolean insertStock = stockBO.insertStock(dto);
                if (insertStock) {
                    new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                    loadAllItems();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                boolean updateStock = stockBO.updateStock(dto);
                if (updateStock) {
                    new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                    loadAllItems();
                    clearFields();
                    isUpdate = false;
                    txtItemId.setDisable(false);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        String itemId = tblItems.getSelectionModel().getSelectedItem().getItemId();
        try {
            boolean deleteStock = stockBO.deleteStock(itemId);
            if (deleteStock) {
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                loadAllItems();
                clearFields();
                isUpdate = false;
                txtItemId.setDisable(false);
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
            }
        } catch (Exception ex) {
            if (ex instanceof SQLException) {
                System.out.println(((SQLException) ex).getErrorCode());
                if (((SQLException) ex).getErrorCode() == 1451){
                    new Alert(Alert.AlertType.ERROR, "Contains References", ButtonType.OK).show();
                }

            }
            Logger.getLogger(ManageStockController.class.getName()).log(Level.SEVERE, null, ex);
        }

        isUpdate = false;
    }

    @FXML
    private void btnClear_OnAction(ActionEvent event) {
        clearFields();
        tblItems.getSelectionModel().clearSelection();
        txtItemId.setDisable(false);
        isUpdate = false;
    }

}
