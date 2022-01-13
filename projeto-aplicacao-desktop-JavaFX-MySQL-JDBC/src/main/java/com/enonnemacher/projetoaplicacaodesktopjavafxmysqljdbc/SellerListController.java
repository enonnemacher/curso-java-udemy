package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.db.DbIntegrityException;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.entities.Seller;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.listeners.DataChangeListener;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.service.DepartmentService;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.service.SellerService;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Alerts;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Utils;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class SellerListController implements Initializable, DataChangeListener {

    private SellerService sellerService;

    @FXML
    private TableView<Seller> tableViewSeller;

    @FXML
    private TableColumn<Seller, Integer> tableColumnId;

    @FXML
    private TableColumn<Seller, String> tableColumnName;

    @FXML
    private TableColumn<Seller, String> tableColumnEmail;

    @FXML
    private TableColumn<Seller, Date> tableColumnBirthDate;

    @FXML
    private TableColumn<Seller, Double> tableColumnBaseSalary;

    @FXML
    private TableColumn<Seller, Seller> tableColumnEDIT;

    @FXML
    private TableColumn<Seller, Seller> tableColumnREMOVE;

    @FXML
    private Button btNew;

    private ObservableList<Seller> observableList;

    @FXML
    public void onBtNewAction(ActionEvent actionEvent) {
        Stage parentStage = Utils.currentStage(actionEvent);
        Seller seller = new Seller();
        createDialogForm(seller, "gui/SellerForm.fxml", parentStage);
    }

    public void setSellerService(SellerService sellerService) {
        this.sellerService = sellerService;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializeNodes();
    }

    private void initializeNodes() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableColumnEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tableColumnBirthDate.setCellValueFactory(new PropertyValueFactory<>("birthDate"));
        Utils.formatTableColumnDate(tableColumnBirthDate, "dd/MM/yyyy");
        tableColumnBaseSalary.setCellValueFactory(new PropertyValueFactory<>("baseSalary"));
        Utils.formatTableColumnDouble(tableColumnBaseSalary, 2);

        Stage stage = (Stage) Main.getMainScene().getWindow();
        tableViewSeller.prefHeightProperty().bind(stage.heightProperty());
    }

    public void updateTableView() {
        if (sellerService == null) {
            throw new IllegalStateException("Service was null");
        }
        List<Seller> list = sellerService.findAll();
        observableList = FXCollections.observableArrayList(list);
        tableViewSeller.setItems(observableList);
        initEditButtons();
        initRemoveButtons();
    }

    private void createDialogForm(Seller seller, String absoluteName, Stage parentStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(absoluteName));
            Pane pane = fxmlLoader.load();

            SellerFormController controller = fxmlLoader.getController();
            controller.setSeller(seller);
            controller.setServices(new SellerService(), new DepartmentService());
            controller.loadAssociatedObjects();
            controller.subscribeDataChangeListener(this);
            controller.updateFormData();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Enter seller data");
            dialogStage.setScene(new Scene(pane));
            dialogStage.setResizable(false);
            dialogStage.initOwner(parentStage);
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.showAndWait();
        } catch (IOException e) {
            Alerts.showAlert("IO Exception", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    @Override
    public void onDataChanged() {
        updateTableView();
    }

    private void initEditButtons() {
        tableColumnEDIT.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnEDIT.setCellFactory(param -> new TableCell<Seller, Seller>() {
            private final Button button = new Button("edit");

            @Override
            protected void updateItem(Seller seller, boolean empty) {
                super.updateItem(seller, empty);
                if (seller == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(
                        event -> createDialogForm(seller, "gui/SellerForm.fxml", Utils.currentStage(event)));
            }
        });
    }

    private void initRemoveButtons() {
        tableColumnREMOVE.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue()));
        tableColumnREMOVE.setCellFactory(param -> new TableCell<Seller, Seller>() {
            private final Button button = new Button("remove");

            @Override
            protected void updateItem(Seller seller, boolean empty) {
                super.updateItem(seller, empty);
                if (seller == null) {
                    setGraphic(null);
                    return;
                }
                setGraphic(button);
                button.setOnAction(event -> removeEntity(seller));
            }
        });
    }

    private void removeEntity(Seller Seller) {
        Optional<ButtonType> result = Alerts.showConfirmation("Confirmation", "Are you sure to delete?");

        if (result.get() == ButtonType.OK) {
            if (sellerService == null) {
                throw new IllegalStateException("Service was null");
            }
            try {
                sellerService.remove(Seller);
                updateTableView();
            } catch (DbIntegrityException e) {
                Alerts.showAlert("Error removing object", null, e.getMessage(), Alert.AlertType.ERROR);
            }
        }
    }
}
