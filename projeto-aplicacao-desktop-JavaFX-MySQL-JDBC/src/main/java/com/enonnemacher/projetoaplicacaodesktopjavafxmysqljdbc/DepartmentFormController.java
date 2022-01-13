package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Constraints;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class DepartmentFormController implements Initializable {

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

    @FXML
    public void onBtSaveAction() {
    }

    @FXML
    public void onBtCancelAction() {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeNodes();
    }

    private void initializeNodes() {
        Constraints.setTextFieldInteger(textFieldID);
        Constraints.setTextFieldMaxLength(textFieldName, 30);
    }
}
