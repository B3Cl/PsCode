package fr.pcentreprise.pcode.vue;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.pcentreprise.pcode.controleur.Controleur;
import fr.pcentreprise.pcode.metier.TraceExec;

public class Console
{
	private List<String> fichier;
	private static ArrayList<String> listVariables = new ArrayList<String>(); //nom des variables pour l'affichage en ordonnée
	
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
	
	
	public static String afficher()
	{
		for( String var:Controleur.getInstance().getCode().getDonnees().getListVariables() )
			Console.listVariables.add( var );
		
		String s = "";
		
		s += "+--------+" + Console.repeat( " ", 74 ) + "+---------+\n";
		s += "|  CODE  |" + Console.repeat( " ", 74 ) + "| DONNEES |\n";;
		
		s += "+" + Console.repeat( "-",  8 ) + "+" +
		           Console.repeat( "-", 74 ) + "+" +
		           Console.repeat( "-",  9 ) + "+" +
		           Console.repeat( "-",  2 ) + "+" +
		           Console.repeat( "-", 12 ) + "+" +
		           Console.repeat( "-", 12 ) + "+\n";
		
		for( int i=0; i<40; i++ )
		{
			s += "|";
			if( i == Controleur.getInstance().getCode().getLigneExec() ) ;
			if( i < Controleur.getInstance().getCode().getLignes().size() )
				s += String.format("%2s", i) + " " + Console.fill( Controleur.getInstance().getCode().getLignes().get(i), 80);
			else
				s += Console.repeat( " ", 83 );
			s += "|";
			
			if( i == 0 ) s += "    Nom     |    TYPE    |   Valeur   |";
			if( i == 1 )
				s += Console.repeat( "-" , 12 ) + "+" +
				     Console.repeat( "-" , 12 ) + "+" +
				     Console.repeat( "-" , 12 ) + "+" ;
			
			if( i-2 >= 0 && i-2 < Console.listVariables.size() )
			{
				String nom    = Console.listVariables.get(i-2);
				String type   = Console.getType( nom );
				String valeur = "" + Controleur.getInstance().getCode().getDonnees().get( nom );
				
				s += Console.fill( " " + String.format("%-10s", nom) + " | " + String.format("%-10s", type) + " | " + String.format("%-10s", valeur) + " "  , 29) + "|";
			}
			if( i-2 == Console.listVariables.size() )
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
			s += "|";
			s +=  Console.fill(TraceExec.getInstance().getSortie().get(i), 83);
			s += "|\n";
		}
		s += "+" + Console.repeat( "-", 83 ) + "+\n";
		return s;
	}
	
	private static String getType( String nom )
	{
		Object var = Controleur.getInstance().getCode().getDonnees().get( nom );
		
		if( var instanceof Integer   ) return "entier"   ;
		if( var instanceof Double    ) return "réel"     ;
		if( var instanceof Boolean   ) return "booleen"  ;
		if( var instanceof Character ) return "caractere";
		if( var instanceof String    ) return "chaine"   ;
		
		return "";
	}
}