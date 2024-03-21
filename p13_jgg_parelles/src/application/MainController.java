package application;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

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
    
    public void joc() {
    	VistaNavigator.loadVista(VistaNavigator.JOC);
    }
    
    public void configuracio() {
    	VistaNavigator.loadVista(VistaNavigator.CONFIGURACIO);
    }
    
    public void quantA() {
    	VistaNavigator.loadVista(VistaNavigator.QUANT_A);
    }
    
    public void exit() {
    	Platform.exit();
    }
    
    public void llistarVistes(){
    	int i=1;
    	for(Node view: vistaHolder.getChildren()) {
    		System.out.println("núm: "+i++ +" vista: "+view.getId());
    	}
    	System.out.println("----------");
    }
    
    public Node getView(String fxml){
		for(Node view: vistaHolder.getChildren()) {
			if(fxml.equals(view.getId()+".fxml")) return view;
    	}
    	return null;
    }
}
