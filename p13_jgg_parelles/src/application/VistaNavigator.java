package application;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

/**
 * Utility class for controlling navigation between vistas.
 *
 * All methods on the navigator are static to facilitate
 * simple access from anywhere in the application.
 */
public class VistaNavigator {

    /**
     * Convenience constants for fxml layouts managed by the navigator.
     */
    public static final String MAIN    		= "main.fxml";
    public static final String JOC 			= "joc.fxml";
    public static final String CONFIGURACIO = "configuracio.fxml";
    public static final String QUANT_A 		= "quantA.fxml";

    /** The main application layout controller. */
    private static MainController mainController;
    private static JocController jocController;
    private static ConfiguracioController configuracioController;

    /**
     * Stores the main controller for later use in navigation tasks.
     *
     * @param mainController the main application layout controller.
     */
    public static void setMainController(MainController mainController) {
        VistaNavigator.mainController = mainController;
    }
    /**
     * Loads the vista specified by the fxml file into the
     * vistaHolder pane of the main application layout.
     *
     * Previously loaded vista for the same fxml file are not cached.
     * The fxml is loaded anew and a new vista node hierarchy generated
     * every time this method is invoked.
     *
     * A more sophisticated load function could potentially add some
     * enhancements or optimizations, for example:
     *   cache FXMLLoaders
     *   cache loaded vista nodes, so they can be recalled or reused
     *   allow a user to specify vista node reuse or new creation
     *   allow back and forward history like a browser
     *
     * @param fxml the fxml file to be loaded.
     */
    public static void loadVista(String fxml) {
        try {
        	Node view=mainController.getView(fxml);
        	if(view!=null) view.toFront();
        	else {
	        	FXMLLoader loader= new FXMLLoader(VistaNavigator.class.getResource(fxml));
	        	AnchorPane childPane=(AnchorPane) loader.load();
	        	Object childController= loader.getController();
	        	mainController.setVista(childPane);
	        	switch (fxml) {
					case JOC:
						if(jocController==null) jocController= (JocController) childController;
					break;
					case CONFIGURACIO:
						if(configuracioController==null) configuracioController= (ConfiguracioController) childController;
					break;	
					default:
					break;
				}
        	}
            mainController.llistarVistes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
