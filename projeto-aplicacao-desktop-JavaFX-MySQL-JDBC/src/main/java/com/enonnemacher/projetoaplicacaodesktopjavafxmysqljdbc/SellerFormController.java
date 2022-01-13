package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.db.DbException;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Seller;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.exceptions.ValidationException;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.listeners.DataChangeListener;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.service.SellerService;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Alerts;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Constraints;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Utils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.*;

public class SellerFormController implements Initializable {

    private Seller seller;

    private SellerService sellerService;

    private final List<DataChangeListener> dataChangeListenerList = new ArrayList<>();

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private TextField textFieldEmail;

    @FXML
    private DatePicker dpBirthDate;

    @FXML
    private TextField textFieldBaseSalary;

    @FXML
    private Label labelErrorName;

    @FXML
    private Label labelErrorEmail;

    @FXML
    private Label labelErrorBirthDate;

    @FXML
    private Label labelErrorBaseSalary;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListenerList.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent actionEvent) {
        if (seller == null) {
            throw new IllegalStateException("Seller was null");
        }
        if (sellerService == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            seller = getFormData();
            sellerService.saveOrUpdate(seller);
            notifyDataChangeListeners();
            Utils.currentStage(actionEvent).close();
        } catch (ValidationException e) {
            setErrorMessages(e.getErrors());
        } catch (DbException e) {
            Alerts.showAlert("Error saving object", null, e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private void notifyDataChangeListeners() {
        for (DataChangeListener listener : dataChangeListenerList) {
            listener.onDataChanged();
        }
    }

    private Seller getFormData() {
        Seller seller = new Seller();

        ValidationException exception = new ValidationException("Validation error!");

        seller.setId(Utils.tryParseToInt(textFieldID.getText()));

        if (textFieldName.getText() == null || textFieldName.getText().trim().equals("")) {
            exception.addError("name", "Field can't be empty");
        }
        seller.setName(textFieldName.getText());

        if (exception.getErrors().size() > 0) {
            throw exception;
        }

        return seller;
    }

    @FXML
    public void onBtCancelAction(ActionEvent actionEvent) {
        Utils.currentStage(actionEvent).close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(textFieldID);
        Constraints.setTextFieldMaxLength(textFieldName, 70);
        Constraints.setTextFieldDouble(textFieldBaseSalary);
        Constraints.setTextFieldMaxLength(textFieldEmail, 60);
        Utils.formatDatePicker(dpBirthDate, "dd/MM/yyyy");
    }

    public void updateFormData() {
        if (seller == null) {
            throw new IllegalStateException("Seller was null");
        }
        textFieldID.setText(String.valueOf(seller.getId()));
        textFieldName.setText(seller.getName());
        textFieldEmail.setText(seller.getEmail());
        Locale.setDefault(Locale.US);
        textFieldBaseSalary.setText(String.format("%.2f", seller.getBaseSalary()));
        if (seller.getBirthDate() != null) {
            dpBirthDate.setValue(LocalDate.ofInstant(seller.getBirthDate().toInstant(), ZoneId.systemDefault()));
        }
    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }
}
