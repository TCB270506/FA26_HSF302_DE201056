module edu.fpt.de201056.ch08 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;      // T5
    requires javafx.media;    // T5

    opens edu.fpt.de201056.ch08.b4 to javafx.fxml;

    exports edu.fpt.de201056.ch08.b1;
    exports edu.fpt.de201056.ch08.b2;
    exports edu.fpt.de201056.ch08.b3;
    exports edu.fpt.de201056.ch08.b4;
}