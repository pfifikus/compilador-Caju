/*package cida;
import cida.lexer.*;
import cida.node.*;
import java.io.*;

public class Main
{
	public static void main(String[] args)
	{
		try
		{
			String arquivo = "teste/teste.ci";

			Lexer lexer =
					new Lexer(
							new PushbackReader(  
									new FileReader(arquivo), 1024)); 
			Token token;
			while(!((token = lexer.next()) instanceof EOF)) {
				System.out.println(token.getClass());
				System.out.println(" ( "+token.toString()+")");
			}
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
}*/

package cida;
import cida.parser.*;
import cida.lexer.*;
import cida.node.*;
import java.io.*;

public class Main
{
 public static void main(String[] args)
 {
  try
  {
   String arquivo = "teste/02_2_delarações_primitivas_inicialização_constante.ci";
  
   Lexer lex = new Lexer(
		    new PushbackReader(  
		    new FileReader(arquivo), 1024));
   
   Parser p = new Parser(lex); 
   
   Start tree = p.parse();
   //Imprime árvore na saída padrão
   //tree.apply(new ASTPrinter());
   //Imprime árvore em interface gráfica
   //tree.apply(new ASTDisplay());
   //aplicação da análise semântica em minha AST
   tree.apply(new Semantica());
  }
  catch(Exception e)
  {
   System.out.println(e.getMessage());
  }
 }
}