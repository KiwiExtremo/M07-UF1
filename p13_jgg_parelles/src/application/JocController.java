package application;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import dao.Configuracio;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

/**
 * Controller class for the first vista.
 */
public class JocController {
	private static final int NUMERO_COLUMNES = 4;

	private Configuracio conf;

	@FXML
	private AnchorPane joc;

	@FXML
	private TextField txtPunts;

	@FXML
	private GridPane gpTauler;

	private int nombreParelles;
	private int punts;
	private boolean finish = false;
	private Image[] images;
	private int cont = 0;
	private Image tempImage;
	private List<Image> acertades;
	private int[] tempCoords = new int[2];

	@FXML
	private void initialize() {
		acertades = new ArrayList<>();
		conf = Configuracio.getInstance();
		actualitzarConfiguracio();
		reiniciarImatges();
		emplenarImatges();
		randomitzarImatges();
		punts = nombreParelles * 4;
		txtPunts.setText(String.valueOf(punts));
	}

	@FXML
	private void girarImatge(MouseEvent evt) {
		if (!finish) {
			if (cont == 2) {
				cont = 0;
				reiniciarImatges();
			}
			Node src = (Node) evt.getSource();
			Integer f = GridPane.getRowIndex(src);
			Integer c = GridPane.getColumnIndex(src);
			int fila = f == null ? 0 : f;
			int columna = c == null ? 0 : c;
			int pos = fila * NUMERO_COLUMNES + columna;
			if (src instanceof ImageView img) {
				if (pos < nombreParelles * 2) {
					img.setImage(images[pos]);
					if (cont == 0) {
						tempImage = img.getImage();
						tempCoords[0] = fila;
						tempCoords[1] = columna;
					}
					if (cont == 1) {
						if (tempImage.equals(img.getImage()) && (tempCoords[0] != fila || tempCoords[1] != columna))
							acertades.add(tempImage);
					}
				}
			}
			/*
			 * for (Node fill: gpTauler.getChildren()) { if(fill instanceof ImageView img) {
			 * if(f== GridPane.getRowIndex(fill) && c==GridPane.getColumnIndex(fill)) {
			 * if(pos<=nombreParelles*2) { img.setImage(images[pos]); if(cont==0) {
			 * tempImage=img.getImage(); tempCoords[0]=fila; tempCoords[1]=columna; }
			 * if(cont==1) { if (tempImage.equals(img.getImage()) && (tempCoords[0]!=fila ||
			 * tempCoords[1]!=columna)) acertades.add(tempImage); } } } } }
			 */
			if (images.length == acertades.size() * 2)
				finish = true;
			if (pos < nombreParelles * 2)
				cont++;
			if (!finish && pos < nombreParelles * 2)
				punts--;
			if (punts == 0)
				finish = true;
			txtPunts.setText(String.valueOf(punts));
			if (finish) {
				Alert alert = new Alert(AlertType.NONE);
				alert.setTitle("El joc ha finalitzat!");
				String content = "";
				if (images.length == acertades.size() * 2)
					content = "Has Guanyat!";
				else if (punts == 0 && images.length != acertades.size() * 2)
					content = "Has Perdut!";
				alert.setContentText(content);
				alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
				alert.showAndWait();
			}
		}
	}

	@FXML
	private void reiniciar(MouseEvent event) {
		finish = false;
		actualitzarConfiguracio();
		acertades = new ArrayList<>();
		reiniciarImatges();
		emplenarImatges();
		randomitzarImatges();
		punts = nombreParelles * 4;
		txtPunts.setText(String.valueOf(punts));
	}

	private void reiniciarImatges() {
		int maxPos = nombreParelles * 2 - 1;
		int maxFila = maxPos / NUMERO_COLUMNES;
		int maxColumna = maxPos % NUMERO_COLUMNES;
		for (Node fill : gpTauler.getChildren()) {
			if (fill instanceof ImageView img) {
				Integer f = GridPane.getRowIndex(fill);
				Integer c = GridPane.getColumnIndex(fill);
				int fila = f == null ? 0 : f;
				int columna = c == null ? 0 : c;
				if (!acertades.contains(img.getImage())) {
					if (fila <= maxFila) {
						img.setImage(new Image(getClass().getResourceAsStream("/application/resources/img_off.jpg")));
						if (fila == maxFila && columna > maxColumna)
							img.setImage(null);
					} else
						img.setImage(null);
				}
			}
		}
	}

	private void emplenarImatges() {
		images = new Image[nombreParelles * 2];
		for (int i = 0; i < nombreParelles; i++) {
			Image img = new Image(
					getClass().getResourceAsStream(String.format("/application/resources/img0%d.jpg", i + 1)));
			images[i * 2] = img;
			images[i * 2 + 1] = img;
		}
	}

	private void actualitzarConfiguracio() {
		if (conf.getParelles() != null) {
			nombreParelles = conf.getParelles();
		} else {
			nombreParelles = 6;
		}
	}

	private void randomitzarImatges() {
		// Con un shuffle no me parecía bien mezclado
		for (int i = 0; i < 4; i++) {
			Collections.shuffle(Arrays.asList(images));
		}
	}
}