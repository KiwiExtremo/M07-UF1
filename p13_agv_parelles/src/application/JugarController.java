package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

/**
 * Controller class for the first vista.
 */
public class JugarController {
	@FXML
	private ImageView restart;
	@FXML
	private GridPane imageGrid;
	@FXML
	private TextField scoreTally;
	
	private LinkedList<ImageView> imageFrontsides, imagePairs;
	
	private Image backside = new Image(getClass().getResourceAsStream("/application/resource/img_off.jpg"));
    /**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
//    @FXML
//    void nextPane(ActionEvent event) {
//        VistaNavigator.loadVista(VistaNavigator.VISTA_2);
//    }
	@FXML
	private void initialize() {
		imageFrontsides = new LinkedList<>();
		imagePairs = new LinkedList<>();
		
		populateFrontsides();
		randomizeCards();
	}
	
	@FXML
	private void restartGame() {
		scoreTally.setText("prova");
	}
	
	@FXML
	private void populateFrontsides() {
		for (int i = 1; i <= 6; i++) {
			ImageView frontside = new ImageView();
			frontside.setFitHeight(65);
			frontside.setFitWidth(100);
			frontside.setImage(new Image(getClass().getResourceAsStream("/application/resource/img0" + i + ".jpg")));
			
			imageFrontsides.add(frontside);
		}
	}
	
	@FXML
	private void randomizeCards() {
		for (int j = 0; j <= 1; j++) {
			for (int i = 0; i < 6 /* update value later with config */; i++) {
				imagePairs.add(imageFrontsides.get(i));
			}
		}
		
		while (imagePairs.size() < 12) {
			imagePairs.add(new ImageView());
		}
		
		Collections.shuffle(imagePairs);
	}
	
	@FXML
	private void flipCard(MouseEvent event) {
		Node card = (Node) event.getSource();
		int col = GridPane.getColumnIndex(card);
		int row = GridPane.getRowIndex(card);
		
		imageGrid.re; // Swap grid cell content with linkedList card
}