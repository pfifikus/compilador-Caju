package caju;

import java.util.HashMap;
import java.util.Map;

public class Bloco {
	private Map<String, Declaracao> bloco;

	public Bloco() {
		bloco = new HashMap<>();
	}

	public void addNewDecl(String name, Declaracao values) {
		bloco.put(name, values);
	}

	public void setDecl(String name, Declaracao values) {
		bloco.put(name, values);
	}

	public Declaracao getDecl(String name) {
		return bloco.get(name);
	}

	public String getDeclarationType(String name) {
		return bloco.get(name).getType();
	}

	public boolean isDeclarationInitialized(String name) {
		return bloco.get(name).isInitialized();
	}
	
	public boolean isDeclarationInTheScope(String name) {
		return bloco.containsKey(name);
	}

	public String getEmptyDecl() {
		for (String decl : bloco.keySet()) {
			if (bloco.get(decl) == null)
				return decl;
		}
		return "";
	}

	//para testes
	@Override
	public String toString() {
		String res = "";
		for (String decl : bloco.keySet()) {
			res += "nome: "+decl + ", tipo: " + getDeclarationType(decl) + ", inicializada: " + isDeclarationInitialized(decl) + "\n";
		}
		return res;
	}
}
