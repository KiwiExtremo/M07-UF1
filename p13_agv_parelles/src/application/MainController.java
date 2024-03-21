package application;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.application.Platform;

/**
 * Main controller class for the entire layout.
 */
public class MainController {

    /** Holder of a switchable vista. */
    @FXML
    private AnchorPane vistaHolder;

    /**
     * Replaces the vista displayed in the vista holder with a new vista.
     *
     * @param node the vista node to be swapped in.
     */
    public void setVista(Node node) {
        vistaHolder.getChildren().add(node);
    }
    
    public void llistarVistes() {
    	int i = 1;
    	for (Node view : vistaHolder.getChildren()) {
    		System.out.println("núm " + i++ + " vista: " + view.getId());
    	}
    	
    	System.out.println("---------");
    }
    
    public Node getView(String fxml) {
    	for (Node view : vistaHolder.getChildren()) {
    		if (fxml.equals(view.getId() + ".fxml")) return view;
    	}
    	return null;
    }
    		
    @FXML
    private void sortirAction() {
    	Platform.exit();
    }

    @FXML
    private void jugarAction() {
        VistaNavigator.loadVista(VistaNavigator.JUGAR);
    }

    @FXML
    private void configurarAction() {
        VistaNavigator.loadVista(VistaNavigator.CONFIGURAR);
    }

    @FXML
    private void quantAAction() {
        VistaNavigator.loadVista(VistaNavigator.QUANT_A);
    }
}