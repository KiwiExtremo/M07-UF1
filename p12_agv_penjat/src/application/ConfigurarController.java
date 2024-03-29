package application;

import java.io.ObjectInputFilter.Config;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * Controller class for the second vista.
 */
public class ConfigurarController {
	@FXML
	private Spinner<Integer> videsSpinner;
	@FXML
	private CheckBox repeticionsCheckBox;
	@FXML
	private CheckBox lletresCheckBox;

	@FXML
	private void initialize() {
		videsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(6, 12, Configuracio.getVides())); // min, max, default values
		repeticionsCheckBox.setSelected(Configuracio.isComptarRepetits());
		lletresCheckBox.setSelected(Configuracio.isMostrarErrors());
		
		videsSpinner.valueProperty().addListener(change -> {
			guardarConfiguracio();
		});
		
		repeticionsCheckBox.setOnAction(event -> {
			guardarConfiguracio();
		});
		
		lletresCheckBox.setOnAction(event -> {
			guardarConfiguracio();
		});
	}
	
	private void guardarConfiguracio() {
		Configuracio.setVides(videsSpinner.getValue());
		Configuracio.setComptarRepetits(repeticionsCheckBox.isSelected());
		Configuracio.setMostrarErrors(lletresCheckBox.isSelected());
	}
	
	@FXML
	private void bTornarAction() {
		VistaNavigator.loadVista(VistaNavigator.JUGAR);
	}
}