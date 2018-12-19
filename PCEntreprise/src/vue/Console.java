package vue;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import metier.TraceExec;

import controleur.Controleur;


public class Console
{	
	private static List<String> listVariables = new ArrayList<String>();
	/*public Console()
	{
		while( Controleur.getInstance().getCode().getLigneExec() < 40 )
		{
			System.out.println( this );
			Scanner sc = new Scanner( System.in );
			String   s = sc.nextLine();
			
			sc.close();
		}
		
	}*/
	
	
	private static String repeat( String sequence, int nb )
	{
		String s = "";
		
		for( int i=0; i<nb; i++ )
			s += sequence;
		
		return s;
	}
	
	private static String add( String chaine, String morceauAjoute, int emp )
	{
		String s = "";
		s += chaine.substring( 0, emp );
		s += morceauAjoute;
		s += chaine.substring( emp );
		return s;
	}
	
	private static String fill( String chaine, int longueur )
	{
		String s = chaine;
		
		while( s.length() < longueur )
			s += " ";
		
		return s;
	}
	
	private static String coloration( String ligne )
	{
		String[] tabCmd = { "si" , "sinon" , "fsi" , "alors", "tq" , "ftq", "faire" } ;
		
		String s = ligne;
		Scanner sc = new Scanner( ligne );
		
		while( sc.hasNext() )
		{
			String mot = sc.next();
			
			for (String cmd : tabCmd)
			{
				if (mot.equals(cmd))
					s = Console.add( s, GestionCouleur.getCoulCmd(), s.indexOf( mot ) );
			}
			
			if ( mot.equals( "lire" ) )
				s = Console.add( s, GestionCouleur.getCoulLire(), s.indexOf( mot ) );
			
			if ( mot.equals ( "ecrire" ) )
				s = Console.add( s, GestionCouleur.getCoulEcrire(), s.indexOf( mot ) );
			
						
			s = Console.add( s, GestionCouleur.resetTextColor(), s.indexOf( mot ) + mot.length() );
		}
		
		sc.close();
		return s;
	}
	
	public static String getMenu()
	{
		String sRet = "" ;
		
		sRet += ("+-------------------------------------------------------------------------------------+\n");
		sRet += ("|                                                                                     |\n");
		sRet += ("|                 Bienvenue dans notre interpréteur de pseudo-code                    |\n");
		sRet += ("|    Avant de commencer, veuillez saisir les variables que vous souhaitez tracer      |\n");
		sRet += ("|   en les séparant par des virgules (ex: x,y,z) ou 'all' pour toute les suivre :     |\n");
		sRet += ("|                                                                                     |\n");
		sRet += ("+-------------------------------------------------------------------------------------+\n");
		
		return sRet;		
	}
	
	public static void setListVariables( List<String> listVariables )
	{
		Console.listVariables = listVariables;
	}
	
	public static String afficher()
	{
		try{
			String os = System.getProperty("os.name");
			
			if (os.contains("Windows"))
				Runtime.getRuntime().exec("cls");
			else
			    Runtime.getRuntime().exec("clear");
		}catch(Exception e){ e.printStackTrace(); }
		
		String s = "";
		
		s += "+--------+" + Console.repeat( " ", 74 ) + "+---------+\n";
		s += "|  CODE  |" + Console.repeat( " ", 74 ) + "| DONNEES |\n";;
		
		s += "+" + Console.repeat( "-",  8 ) + "+" +
		           Console.repeat( "-", 74 ) + "+" +
		           Console.repeat( "-",  9 ) + "+" +
		           Console.repeat( "-",  2 ) + "+" +
		           Console.repeat( "-", 12 ) + "+" +
		           Console.repeat( "-", 12 ) + "+\n";
		
		int numLigneDebut;
		
		if( Controleur.getInstance().getCode().getLigneExec() < 20 )
			numLigneDebut = 0;
		else
			if( Controleur.getInstance().getCode().getLigneExec() > Controleur.getInstance().getCode().getLignes().size()-20 )
				numLigneDebut = Controleur.getInstance().getCode().getLignes().size() - 40;
			else
				numLigneDebut = Controleur.getInstance().getCode().getLigneExec() - 20;
			
		
		for( int i=0; i<40; i++ )
		{
			s += "|";
			if( numLigneDebut + i == Controleur.getInstance().getCode().getLigneExec() )
				s += GestionCouleur.getUnderline();
			if( numLigneDebut + i < Controleur.getInstance().getCode().getLignes().size() )
				s += String.format("%2s", numLigneDebut + i) + " " + Console.coloration(Console.fill( Controleur.getInstance().getCode().getLignes().get(numLigneDebut + i), 80) );
			else
				s += Console.repeat( " ", 83 );
			s += GestionCouleur.resetUnderlineColor() + "|";
			
			if( i == 0 ) s += "    Nom     |    TYPE    |   Valeur   |";
			if( i == 1 )
				s += Console.repeat( "-" , 12 ) + "+" +
				     Console.repeat( "-" , 12 ) + "+" +
				     Console.repeat( "-" , 12 ) + "+" ;
			
			if( i-2 >= 0 && i-2 < listVariables.size() )
			{
				String nom    = listVariables.get(i-2);
				String type   = Controleur.getInstance().getCode().getDonnees().getType( nom );
				String valeur = "" + Controleur.getInstance().getCode().getDonnees().get( nom );
				
				s += Console.fill( " " + String.format("%-10s", nom) + " | " + String.format("%-10s", type) + " | " + String.format("%-10s", valeur) + " "  , 29) + "|";
			}
			if( i-2 == listVariables.size() )
				s += Console.repeat( "-" , 12 ) + "+" +
				     Console.repeat( "-" , 12 ) + "+" +
				     Console.repeat( "-" , 12 ) + "+" ;
			
			s += "\n";
		}
		
		s += "+" + Console.repeat( "-", 83 ) + "+\n";
		
		s += "\n";
		
		s += "+-----------+\n" ;
		s += "|  CONSOLE  |\n" ;
		s += "+" + Console.repeat( "-", 83 ) + "+\n";
		for( int i=0; i<TraceExec.getInstance().getSortie().size(); i++ )
		{
			s += "| ";
			s +=  Console.fill(TraceExec.getInstance().getSortie().get(i), 81);
			s += " |\n";
		}
		s += "+" + Console.repeat( "-", 83 ) + "+\n";
		return s;
	}
}