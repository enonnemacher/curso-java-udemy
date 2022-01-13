module com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;
    requires mysql.connector.java;

    opens com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc to javafx.fxml;
    exports com.enonnemacher.projetoaplicacaodesktopjavafxmysqljdbc;
}