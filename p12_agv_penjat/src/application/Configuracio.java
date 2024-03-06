package application;

public final class Configuracio {
	private static int vides;
	private static boolean mostrarErrors;
	private static boolean comptarRepetits;
	
	private Configuracio() {
		vides = 12;
		mostrarErrors = true;
		comptarRepetits = false;
	}
	
	public static int getVides() {
		return vides;
	}
	public static void setVides(int novesVides) {
		vides = novesVides;
	}
	public static boolean isMostrarErrors() {
		return mostrarErrors;
	}
	public static void setMostrarErrors(boolean nouValor) {
		mostrarErrors = nouValor;
	}
	public static boolean isComptarRepetits() {
		return comptarRepetits;
	}
	public static void setComptarRepetits(boolean nouValor) {
		comptarRepetits = nouValor;
	}
}
