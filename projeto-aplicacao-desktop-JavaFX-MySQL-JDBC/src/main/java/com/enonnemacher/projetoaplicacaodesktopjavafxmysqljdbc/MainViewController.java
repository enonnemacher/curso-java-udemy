package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;

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
        System.out.println("onMenuItemDepartmentAcition");
    }

    @FXML
    public void onMenuItemAbout() {
        System.out.println("onMenuItemAboutAcition");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
