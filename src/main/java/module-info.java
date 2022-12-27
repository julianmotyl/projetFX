module co.mycompany.javafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.mycompany.projetfx to javafx.fxml;
    exports com.mycompany.projetfx;
}