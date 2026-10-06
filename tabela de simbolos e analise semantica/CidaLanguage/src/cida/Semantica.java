package cida;

import cida.analysis.*;
import cida.node.*;
import java.util.List;
import java.util.ArrayList;


public class Semantica extends DepthFirstAdapter {
	private List<Scope> tabelaSimb = new ArrayList<>();
	private int scopes;

	@Override
	public void outStart(Start node) {
		System.out.println("Análise Semântica concluída!");
	}

	@Override
	public void inABlock(ABlock node) {
		//Quando entra em um bloco, cria um novo escopo
		tabelaSimb.add(new Scope());
		scopes++;
	}

	@Override
	public void outABlock(ABlock node) {
		//Quando sai de um bloco, exclui o escopo mais recente
		
		//teste para mostrar tabela de simbolos do escopo
		//System.out.println(tabelaSimb.get(scopes-1));
		//
		tabelaSimb.remove(scopes-1);
		scopes--;
	}

	//Adiciona as informações do símbolo alterable a tabela
	@Override
	public void outAAlterableDeclaration(AAlterableDeclaration node) {
		tabelaSimb.get(scopes-1).addNewDecl(node.getId().toString(),
				new Declaration(node.getBaseType().toString(), false, true));
	}

	//adiciona o nome do simbolo a tabela, os detalhes de declaração sao adicionados por outAType
	@Override
	public void inAAlterableVecDeclaration(AAlterableVecDeclaration node) {
		tabelaSimb.get(scopes-1).addNewDecl(node.getId().toString(), null);
	}

	//adiciona as informações do símbolo ao elemento nulo da tabela
	@Override
	public void outAType(AType node) {
		tabelaSimb.get(scopes-1).setDecl(tabelaSimb.get(scopes-1).getEmptyDecl(),
				new Declaration(node.getBaseType().toString(), false, true));
	}

	//Adiciona informações do simbolo unalterable a tabela
	@Override
	public void outAUnalterableDeclaration(AUnalterableDeclaration node) {
		tabelaSimb.get(scopes-1).addNewDecl(node.getId().toString(),
				new Declaration(node.getBaseType().toString(), false, false));
	}

	//adiciona informações do simbolo unalterable inicializado a tabela
	public void outAUnalterableIniDeclaration(AUnalterableIniDeclaration node) {
		tabelaSimb.get(scopes-1).addNewDecl(node.getId().toString(),
				new Declaration(node.getBaseType().toString(), true, false));
	}
	
	//verifica se a atribuição de valor está sendo feita no escopo certo, se o simbolo existe e é alterable
	@Override
	public void inASetCommand(ASetCommand node)
    {
		String name = node.getLocal().toString().replaceAll("\\s*\\[\\s*\\d+\\s*\\]\\s*"," ");
		boolean found = false;
		for (int scopeNum = scopes; scopeNum > 0; scopeNum--) {
			Scope d = tabelaSimb.get(scopeNum-1);
			if (d.isDeclarationInTheScope(name)) {
				if (d.getDecl(name).isAlterable()) {
					d.getDecl(name).setInitialized();
				}
				else {
					System.out.print("Não foi possivel definir o valor de "+name+" como \""+node.getExp()+"\". "+name+" nao é alterável.\n");
					System.exit(1);
				} 
				found = true;
				break;
				}
			}
		if (!found) {
			System.out.println(name+ " não está no escopo do bloco ou não foi inicializada.");
		}
    }
}