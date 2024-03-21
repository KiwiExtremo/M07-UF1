package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the second vista.
 */
public class QuantAController {
	@FXML
    private AnchorPane quantA;

    /**
     * Event handler fired when the user requests a previous vista.
     *
     * @param event the event that triggered the handler.
     */
    @FXML
    void previousPane(ActionEvent event) {
        VistaNavigator.loadVista(VistaNavigator.CONFIGURACIO);
    }

}