package com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc.util;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Utils {

    public static Stage currentStage(ActionEvent actionEvent) {
        return (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    }

    public static Integer tryParseToInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
