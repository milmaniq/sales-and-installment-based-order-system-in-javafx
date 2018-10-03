/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lk.ijse.rj.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
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
import lk.ijse.rj.business.custom.EmployeeBO;
import lk.ijse.rj.business.custom.StockBO;
import lk.ijse.rj.dto.EmployeeDTO;
import lk.ijse.rj.main.StartUp;
import lk.ijse.rj.view.util.tblmodel.EmployeeTM;

/**
 *
 * @author Ilman Iqbal
 */
public class ManageEmployeeController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private Label lblEmployeesManageEmployees;
    @FXML
    private Label lblHome;
    @FXML
    private JFXTextField txtEmployeeId;
    @FXML
    private JFXTextField txtName;
    @FXML
    private JFXTextField txtContact;
    @FXML
    private JFXTextField txtAddress;
    @FXML
    private TableView<EmployeeTM> tblEmployees;
    @FXML
    private JFXButton btnSave;
    @FXML
    private JFXButton btnDelete;

    private boolean isUpdate;
    private EmployeeBO employeeBO;
    @FXML
    private JFXButton btnClear;

    public ManageEmployeeController() {
        employeeBO = (EmployeeBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.EMPLOYEE);
    }

    private void loadAllEmployees() {
        try {
            ArrayList<EmployeeDTO> allEmployees = employeeBO.getAllEmployees();
            ObservableList<EmployeeTM> items = tblEmployees.getItems();
            items.removeAll(items);
            for (EmployeeDTO allEmployee : allEmployees) {
                EmployeeTM employeeTM = new EmployeeTM(allEmployee.getEmployeeId(), allEmployee.getName(), allEmployee.getContact(), allEmployee.getAddress());
                items.add(employeeTM);
            }
        } catch (Exception ex) {
            Logger.getLogger(ManageEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tblEmployees.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("employeeId"));
        tblEmployees.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tblEmployees.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("contact"));
        tblEmployees.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("address"));

        loadAllEmployees();

        tblEmployees.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<EmployeeTM>() {
            @Override
            public void changed(ObservableValue<? extends EmployeeTM> observable, EmployeeTM oldValue, EmployeeTM newValue) {

                if (newValue == null) {
                    return;
                }

                txtEmployeeId.setText(newValue.getEmployeeId());
                txtName.setText(newValue.getName());
                txtContact.setText(newValue.getContact());
                txtAddress.setText(newValue.getAddress());

                isUpdate = true;
                txtEmployeeId.setDisable(true);
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
        txtEmployeeId.clear();
        txtName.clear();
        txtAddress.clear();
        txtContact.clear();
    }

    @FXML
    private void btnSave_OnAction(ActionEvent event) {
        EmployeeDTO employeeDTO = new EmployeeDTO(txtEmployeeId.getText(), txtName.getText(), txtContact.getText(), txtAddress.getText());

        if (!isUpdate) {
            try {
                boolean insertEmployee = employeeBO.insertEmployee(employeeDTO);
                if (insertEmployee) {
                    new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                    loadAllEmployees();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            try {
                boolean updateEmployee = employeeBO.updateEmployee(employeeDTO);
                if (updateEmployee) {
                    new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                    loadAllEmployees();
                    clearFields();
                    isUpdate = false;
                    txtEmployeeId.setDisable(false);
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed", ButtonType.OK).show();
                }
            } catch (Exception ex) {
                Logger.getLogger(ManageEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void btnDelete_OnAction(ActionEvent event) {
        String employeeId = tblEmployees.getSelectionModel().getSelectedItem().getEmployeeId();
        try {
            boolean deleteEmployee = employeeBO.deleteEmployee(employeeId);
            if (deleteEmployee) {
                new Alert(Alert.AlertType.INFORMATION, "Success", ButtonType.OK).show();
                loadAllEmployees();
                clearFields();
                isUpdate = false;
                txtEmployeeId.setDisable(false);

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
            Logger.getLogger(ManageEmployeeController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void btnClear_OnAction(ActionEvent event) {
        clearFields();
        tblEmployees.getSelectionModel().clearSelection();
        txtEmployeeId.setDisable(false);
        isUpdate = false;

    }

}
