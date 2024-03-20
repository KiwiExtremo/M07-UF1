package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 * Controller class for the first vista.
 */
public class JugarController {
	@FXML
	private ImageView videsRestants;
	@FXML
	private Button bPartidaNova;
	@FXML
	private TextField lletraJugar;
	@FXML
	private Text paraulaEndevinar, lletresFallades;
	
	private Random random = new Random();
	
	private static int vidaActual = 12;
	
	private boolean comptarRepetits = false;
	
	private String[] paraulesPossibles = {"CAÇAFORTUNES", "BARREJAR", "DEPARTAMENT", "FALGUERA", "PASTANAGA", "PUBERTAT"};
    
	private ArrayList<String> llistaLletresJugades, llistaLletresFallades;
	private String paraulaOculta, paraulaEnJoc;
	
	/**
     * Event handler fired when the user requests a new vista.
     *
     * @param event the event that triggered the handler.
     */
//	@FXML
//	void nextPane(ActionEvent event) {
//		VistaNavigator.loadVista(VistaNavigator.VISTA_2);
//	}
	
	@FXML
	void perdreVides() {
		vidaActual--;
		
		actualitzarVides();
	}
	
	@FXML
	private void initialize() {
		paraulaEndevinar.setText("");
		bPartidaNova.setText("Iniciar partida");
		lletresFallades.setText("");

		actualitzarVides();
		
		lletraJugar.setDisable(true);
		
		llistaLletresFallades = new ArrayList<String>();
		llistaLletresJugades = new ArrayList<String>();
	}
	
	private void actualitzarVides() {
		String strVidaActual;
		
		if (vidaActual < 10) {
			strVidaActual = "resource/carta_0" + vidaActual + ".jpg";
		} else {
			strVidaActual = "resource/carta_" + vidaActual + ".jpg";
		}
		
		Image img = new Image(getClass().getResourceAsStream(strVidaActual));
		
		videsRestants.setImage(img);
	}
	
	@FXML
	private void bComencarPartidaAction() {
		if (Configuracio.getVides() >= 6) {
			vidaActual = Configuracio.getVides();
		}
		
		actualitzarVides();
		
		lletresFallades.setVisible(Configuracio.isMostrarErrors());
		comptarRepetits = Configuracio.isComptarRepetits();
		
		paraulaAleatoria();
		
		bPartidaNova.setText("Reiniciar partida");
		
		lletraJugar.setDisable(false);
	}
	
	@FXML
	private void bEndevinarLletra() {
		if (lletraJugar.getText().isEmpty()) {
			return;
		}
		
		String lletra = lletraJugar.getText().toUpperCase();
		
		if (paraulaOculta.contains(lletra)) {
			paraulaEndevinar.setText(canviarLletra(lletra));
			
			return;
			
		} else if (!comptarRepetits) {
			for (String lletraJugada : llistaLletresJugades) {
				if (lletra.equals(lletraJugada)) {
					return;
				}
			}
		}
		
		perdreVides();
	}
	
	private String canviarLletra(String lletra) {
		char lletraCanviar = lletra.charAt(0);
		char caracterActual;
		
		StringBuilder paraulaActualitzada = new StringBuilder();
		
		for (int i = 0; i < paraulaEnJoc.length(); i++) {
			caracterActual = paraulaEnJoc.charAt(i);
			
			if (caracterActual == lletraCanviar) {
				paraulaActualitzada.append(lletraCanviar);
				
			} else {
				paraulaActualitzada.append(caracterActual);
			}
		}
		
		paraulaEnJoc = paraulaActualitzada.toString();
		
		return paraulaActualitzada.toString();
	}
	
	private void paraulaAleatoria() {
		int pos = random.nextInt(paraulesPossibles.length - 1);
		
		paraulaOculta = paraulesPossibles[pos];
		
		paraulaEnJoc = passarBarraBaixa(paraulaOculta);
		
		paraulaEndevinar.setText(paraulaEnJoc);
	}
	
	private String passarBarraBaixa(String paraula) {
		StringBuilder barresBaixes = new StringBuilder();
		
		for (int i = 0; i < paraula.length(); i++) {
			barresBaixes.append("_");
			
			if (i < paraula.length() - 1) {
				barresBaixes.append(" ");
			}
		}
		
		return barresBaixes.toString();
	}
}