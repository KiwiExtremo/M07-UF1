package application;

import java.util.Collections;
import java.util.LinkedList;

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
	private Configurator config;
	@FXML
	private ImageView restart, lastCard;
	@FXML
	private GridPane imageGrid;
	@FXML
	private TextField scoreTally;

	private LinkedList<ImageView> imageFrontsides, imagePairs;

	private int numPairs;

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

		config = Configurator.getConfig();

		updateMaxPairs();
		populateBacksides();
		populateFrontsides();
		randomizeCards();
	}

	private void updateMaxPairs() {
		if (config.getNumPairs() != null) {
			numPairs = config.getNumPairs();
		} else {
			numPairs = 6;
		}
	}

	@FXML
	private void restartGame() {
		scoreTally.setText("prova");
	}

	private void populateBacksides() {
		for (int row = 0; row < imageGrid.getColumnCount(); row++) {
			for (int col = 0; col < imageGrid.getRowCount(); col++) {
				ImageView placeholder = new ImageView();
				placeholder.setFitHeight(65);
				placeholder.setFitWidth(100);
				placeholder.setImage(backside);

				placeholder.setOnMouseClicked(event -> {
					checkCard(event);
				});

				imageGrid.add(placeholder, row, col);
			}
		}
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

		for (int i = 0; i < numPairs; i++) {
			imagePairs.add(imageFrontsides.get(i));
			imagePairs.add(imageFrontsides.get(i));
		}

		ImageView emptyImage = new ImageView();

		while (imagePairs.size() < 12) {
			imagePairs.add(emptyImage);
		}

		Collections.shuffle(imagePairs);
	}

	@FXML
	private void checkCard(MouseEvent event) {
		Node card = (Node) event.getSource();
		int col = GridPane.getColumnIndex(card);
		int row = GridPane.getRowIndex(card);

		Node currentContent = getNodeFromGrid(col, row);

		if (currentContent != null) {
			ImageView content = (ImageView) currentContent;
			Image image = content.getImage();

			if (image.equals(backside)) {
				flipCard(col, row, true);

			} else {
				flipCard(col, row, false);
			}
		}
	}

	private void flipCard(int col, int row, boolean isBackside) {
		int arrayPosition = (row * imageGrid.getColumnCount()) + col;

		if (isBackside) {
			ImageView flippedImage = imagePairs.get(arrayPosition);

			imageGrid.add(flippedImage, col, row);
		} else {
			ImageView backImage = new ImageView();
			backImage.setFitHeight(65);
			backImage.setFitWidth(100);
			backImage.setImage(backside);
			backImage.setOnMouseClicked(event -> {
				checkCard(event);
			});

			imageGrid.add(backImage, col, row);
		}
	}

	private Node getNodeFromGrid(int col, int row) {
		for (Node child : imageGrid.getChildren()) {
			if (GridPane.getColumnIndex(child) == col && GridPane.getRowIndex(child) == row) {
				return child;
			}
		}
		return null;
	}
}