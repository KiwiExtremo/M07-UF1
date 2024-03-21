package application;

public class Configurator {
	private static Configurator config;
	private Integer numPairs;

	private Configurator() {

	}

	public static Configurator getConfig() {
		if (config == null) {
			config = new Configurator();
		}
		return config;
	}

	public Integer getNumPairs() {
		return numPairs;
	}

	public void setNumPairs(Integer numPairs) {
		this.numPairs = numPairs;
	}

}
