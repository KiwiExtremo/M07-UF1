package dao;

public final class Configuracio {
	private static Configuracio INSTANCE;
	private Integer parelles;
	
	private Configuracio() {
		
	}
	public static Configuracio getInstance() {
		if(INSTANCE==null) INSTANCE= new Configuracio();
		return INSTANCE;
	}
	public Integer getParelles() {
		return parelles;
	}
	public void setParelles(Integer parelles) {
		this.parelles = parelles;
	}
	
}
