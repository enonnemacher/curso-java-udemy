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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.*;

public class SellerFormController implements Initializable {

    private Seller Seller;

    private SellerService SellerService;

    private List<DataChangeListener> dataChangeListenerList = new ArrayList<>();

    @FXML
    private TextField textFieldID;

    @FXML
    private TextField textFieldName;

    @FXML
    private Label labelErrorName;

    @FXML
    private Button buttonSave;

    @FXML
    private Button buttonCancel;

    public void setSeller(Seller Seller) {
        this.Seller = Seller;
    }

    public void setSellerService(SellerService SellerService) {
        this.SellerService = SellerService;
    }

    public void subscribeDataChangeListener(DataChangeListener listener) {
        dataChangeListenerList.add(listener);
    }

    @FXML
    public void onBtSaveAction(ActionEvent actionEvent) {
        if (Seller == null) {
            throw new IllegalStateException("Seller was null");
        }
        if (SellerService == null) {
            throw new IllegalStateException("Service was null");
        }
        try {
            Seller = getFormData();
            SellerService.saveOrUpdate(Seller);
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
        Seller obj = new Seller();

        ValidationException exception = new ValidationException("Validation error!");

        obj.setId(Utils.tryParseToInt(textFieldID.getText()));

        if (textFieldName.getText() == null || textFieldName.getText().trim().equals("")) {
            exception.addError("name", "Field can't be empty");
        }
        obj.setName(textFieldName.getText());

        if (exception.getErrors().size() > 0) {
            throw exception;
        }

        return obj;
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
        Constraints.setTextFieldMaxLength(textFieldName, 30);
    }

    public void updateFormData() {
        if (Seller == null) {
            throw new IllegalStateException("Seller was null");
        }
        textFieldID.setText(String.valueOf(Seller.getId()));
        textFieldName.setText(Seller.getName());
    }

    private void setErrorMessages(Map<String, String> errors) {
        Set<String> fields = errors.keySet();

        if (fields.contains("name")) {
            labelErrorName.setText(errors.get("name"));
        }
    }
}
