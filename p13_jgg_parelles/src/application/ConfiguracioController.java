package application;

import dao.Configuracio;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * Controller class for the second vista.
 */
public class ConfiguracioController {
	@FXML
    private Button bBack;

    @FXML
    private AnchorPane configuracio;

    @FXML
    private ToggleGroup parelles;

    @FXML
    private RadioButton quatre;

    @FXML
    private RadioButton cinc;
    
    @FXML
    private RadioButton sis;

    
    private Configuracio conf;

    @FXML
	private void initialize() {
    	sis.setSelected(true);
    	conf=Configuracio.getInstance();
    	listener();
    }
    @FXML
    private void tornarJoc() {
    	VistaNavigator.loadVista(VistaNavigator.JOC);
    }
    private void listener() {
		parelles.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
			RadioButton btn= (RadioButton) newValue;
			conf.setParelles(Integer.valueOf(btn.getText()));
		});
	}
}