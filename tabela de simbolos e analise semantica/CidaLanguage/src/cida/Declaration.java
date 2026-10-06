package cida;

//Classe para informações de símbolos guardados na tabela de símbolos
public class Declaration {
	String type;
	boolean initialized;
	final boolean alterable;

	public Declaration(String type, boolean initialized, boolean alterable) {
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

	public boolean isAlterable() {
		return alterable;
	}

	public void setInitialized() {
		this.initialized = true;
	}
}
