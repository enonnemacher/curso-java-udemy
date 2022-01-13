package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc;

import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.service.DepartmentService;
import com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util.Alerts;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML
    private MenuItem menuItemSeller;
    @FXML
    private MenuItem menuItemDepartment;
    @FXML
    private MenuItem menuItemAbout;

    @FXML
    public void onMenuItemSeller() {
        System.out.println("onMenuItemSellerAcition");
    }

    @FXML
    public void onMenuItemDepartment() {
        loadView("gui/DepartmentList.fxml");
//        loadView2("gui/DepartmentList.fxml");
    }

    @FXML
    public void onMenuItemAbout() {
        loadView("gui/About.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private synchronized void loadView(String absoluteName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox vBox = fxmlLoader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(vBox.getChildren());
        } catch (IOException e) {
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }

    private synchronized void loadView2(String absoluteName) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(absoluteName));
            VBox vBox = fxmlLoader.load();

            Scene mainScene = Main.getMainScene();
            VBox mainVBox = (VBox) ((ScrollPane) mainScene.getRoot()).getContent();

            Node mainMenu = mainVBox.getChildren().get(0);
            mainVBox.getChildren().clear();
            mainVBox.getChildren().add(mainMenu);
            mainVBox.getChildren().addAll(vBox.getChildren());

            DepartmentListController departmentListController = fxmlLoader.getController();
            departmentListController.setDepartmentService(new DepartmentService());
            departmentListController.updateTableView();
        } catch (IOException e) {
            Alerts.showAlert("IOException", "Error loading view", e.getMessage(), Alert.AlertType.ERROR);
        }
    }
}
