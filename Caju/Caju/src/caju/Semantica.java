package caju;

import caju.analysis.*;
import caju.node.*;
import cida.Declaration;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class Semantica extends DepthFirstAdapter {
	private List<Bloco> tabelaSimb = new ArrayList<>();
	private int blocos;

	@Override
	public void outStart(Start node) {
		System.out.println("------- Análise Semântica concluída! -------");
	}

	@Override
	public void inStart(Start node) {System.out.println("------- Análise Semântica iniciada! -------");}

	@Override
	public void inABloco(ABloco node) {
		//Quando entra em um bloco, cria um novo escopo
		tabelaSimb.add(new Bloco());
		blocos++;
	}

	@Override
	public void outABloco(ABloco node) {
		//Quando sai de um bloco, exclui o escopo mais recente
		
//		teste para mostrar tabela de simbolos do escopo
		System.out.println(tabelaSimb.get(blocos - 1));
		//
		tabelaSimb.remove(blocos -1);
		blocos--;
	}

	//Adiciona as informações do símbolo alterable a tabela
	@Override
	public void outADecVariavel(ADecVariavel node) {
		var identificadores = node.getListaNomes().toString().split(" ");
		for (String id : identificadores) {
			tabelaSimb.get(blocos-1).addNewDecl(id,
					new Declaracao(node.getTipo().toString(), false));
		}
	}

	//adiciona o nome do simbolo a tabela, os detalhes de declaração sao adicionados por outAType
	@Override
	public void inADecVariavel(ADecVariavel node) {
		var identificadores = node.getListaNomes().toString().split(" ");
		for (String id : identificadores) {
			if (!tabelaSimb.get(blocos-1).isDeclarationInTheScope(id)) {
				tabelaSimb.get(blocos-1).addNewDecl(id, null);
			} else {
				System.out.println("A variável \"" + id + "\" já foi declarada nesse bloco.");
			}
		}
	}

	//adiciona as informações do símbolo ao elemento nulo da tabela
	@Override
	public void outADecFuncao(ADecFuncao node) {
		var id = node.getIdentificador().toString();
		tabelaSimb.get(blocos-1).addNewDecl(id, new Declaracao(node.getTipoRetorno().toString(), true));
	}

	@Override
	public void inADecFuncao(ADecFuncao node) {
		var id = node.getIdentificador().toString();
		if (!tabelaSimb.get(blocos-1).isDeclarationInTheScope(id)) {
			tabelaSimb.get(blocos-1).addNewDecl(id, null);
		} else {
			System.out.println("A função \"" + id + "\" já foi declarada nesse bloco.");
		}
	}

	@Override
	public void inAChamada(AChamada node) {
		if (tabelaSimb.get(blocos-1).isDeclarationInTheScope(node.getIdentificador().toString())) {
			
		} else {
			System.out.println("A função \"" + node.getIdentificador().toString() + "\" não foi declarada.");
		}
	}

	@Override
	public void outADecVetor(ADecVetor node) {
		var identificadores = node.getListaNomes().toString().split(" ");
		for (String id : identificadores) {
			tabelaSimb.get(blocos - 1).addNewDecl(id, new Declaracao(node.getTipoRetorno().toString(), true));
		}
	}


//
//	//Adiciona informações do simbolo unalterable a tabela
//	@Override
//	public void outAUnalterableDeclaration(AUnalterableDeclaration node) {
//		tabelaSimb.get(blocos -1).addNewDecl(node.getId().toString(),
//				new Declaracao(node.getBaseType().toString(), false, false));
//	}
//
//	//adiciona informações do simbolo unalterable inicializado a tabela
//	public void outAUnalterableIniDeclaration(AUnalterableIniDeclaration node) {
//		tabelaSimb.get(blocos -1).addNewDecl(node.getId().toString(),
//				new Declaracao(node.getBaseType().toString(), true, false));
//	}
//
//	//verifica se a atribuição de valor está sendo feita no escopo certo, se o simbolo existe e é alterable
//	@Override
//	public void inAComandoConjunto(ASetCommand node)
//    {
//		String name = node.getLocal().toString().replaceAll("\\s*\\[\\s*\\d+\\s*\\]\\s*"," ");
//		boolean found = false;
//		for (int scopeNum = blocos; scopeNum > 0; scopeNum--) {
//			Bloco d = tabelaSimb.get(scopeNum-1);
//			if (d.isDeclarationInTheScope(name)) {
//				if (d.getDecl(name).isAlterable()) {
//					d.getDecl(name).setInitialized();
//				}
//				else {
//					System.out.print("Não foi possivel definir o valor de "+name+" como \""+node.getExp()+"\". "+name+" nao é alterável.\n");
//					System.exit(1);
//				}
//				found = true;
//				break;
//				}
//			}
//		if (!found) {
//			System.out.println(name+ " não está no escopo do bloco ou não foi inicializada.");
//		}
//    }
}