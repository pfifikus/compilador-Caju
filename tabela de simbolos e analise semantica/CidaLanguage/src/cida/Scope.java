package cida;

import java.util.HashMap;
import java.util.Map;

public class Scope {
	private Map<String, Declaration> scope;

	public Scope() {
		scope = new HashMap<>();
	}

	public void addNewDecl(String name, Declaration values) {
		scope.put(name, values);
	}

	public void setDecl(String name, Declaration values) {
		scope.put(name, values);
	}

	public Declaration getDecl(String name) {
		return scope.get(name);
	}

	public String getDeclarationType(String name) {
		return scope.get(name).getType();
	}

	public boolean isDeclarationInitialized(String name) {
		return scope.get(name).isInitialized();
	}

	public boolean isDeclarationAlterable(String name) {
		return scope.get(name).isAlterable();
	}
	
	public boolean isDeclarationInTheScope(String name) {
		return scope.containsKey(name);
	}

	public String getEmptyDecl() {
		for (String decl : scope.keySet()) {
			if (scope.get(decl) == null)
				return decl;
		}
		return "";
	}

	//para testes
	@Override
	public String toString() {
		String res = "";
		for (String decl : scope.keySet()) {
			res += "nome: "+decl + ", tipo: " + getDeclarationType(decl) + ", inicializada: " + isDeclarationInitialized(decl) + ", alterável: "
					+ isDeclarationAlterable(decl) +"\n";
		}
		return res;
	}
}
