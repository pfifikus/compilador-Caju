package caju;

//Classe para informações de símbolos guardados na tabela de símbolos
public class Declaracao {
	String type;
	boolean initialized;
	final boolean alterable;

	public Declaracao(String type, boolean initialized) {
		this.type = type;
		this.initialized = initialized;
		this.alterable = alterable;
	}

	public String getType() {
		return type;
	}

	public boolean isInitialized() {
		return initialized;
	}

	public boolean isAlterable() { return alterable; }

	public void setInitialized() {
		this.initialized = true;
	}
}
