module edu.fpt.de201056.ch08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;      // T5
    requires javafx.media;    // T5

    opens edu.fpt.de201056.ch08javafx.b4 to javafx.fxml;
    opens edu.fpt.de201056.ch08javafx.b5 to javafx.fxml;
    opens edu.fpt.de201056.ch08javafx.b6 to javafx.fxml;
    opens edu.fpt.de201056.ch08javafx.t1 to javafx.fxml, javafx.base;   // javafx.base: PropertyValueFactory (TableView)
    opens edu.fpt.de201056.ch08javafx.t4 to javafx.fxml, javafx.base;

    exports edu.fpt.de201056.ch08javafx.b1;
    exports edu.fpt.de201056.ch08javafx.b2;
    exports edu.fpt.de201056.ch08javafx.b3;
    exports edu.fpt.de201056.ch08javafx.b4;
    exports edu.fpt.de201056.ch08javafx.b5;
    exports edu.fpt.de201056.ch08javafx.b6;
    exports edu.fpt.de201056.ch08javafx.t1;
    exports edu.fpt.de201056.ch08javafx.t2;
    exports edu.fpt.de201056.ch08javafx.t3;
    exports edu.fpt.de201056.ch08javafx.t4;
    exports edu.fpt.de201056.ch08javafx.t5;
    exports edu.fpt.de201056.ch08javafx.t6;
}