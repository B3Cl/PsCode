package controleur;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

import vue.Console;
import metier.Code;
import metier.commandes.Lecture;


/**
 * La classe Controleur permet la gestion de l'ensemble du programme
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Controleur
{
	/**
	 * Instance du controleur
	 */
	private static Controleur instance;
	
	/**
	 * Attribut contenant le code
	 */
	private Code code;
	
	/**
	 * accesseur au code
	 */
	public Code getCode()
	{
		return this.code;
	}
	
	private Controleur(String fichierAlgo)
	{
		this.code = new Code(fichierAlgo);
	}
	
	/**
	 * Méthode static permettant de récuperer l'instance de l'interpreteur
	 */
	public static Controleur getInstance()
	{
		return Controleur.instance;
	}
	
	public static void main(String[] args)
	{
		int mode = 1; //0 pour mode normal
		              //1 pour mode pas a pas
		              //2 pour mode goto 20
		Controleur.instance = new Controleur(args[0]);
				
		System.out.println( Console.getMenu() );
		
		String variableAff = "";
		List<String> listVariables = new ArrayList<String>();
		try
		{
			InputStreamReader lecteur = new InputStreamReader (System.in) ;
			BufferedReader entree = new BufferedReader (lecteur) ;
			variableAff = entree.readLine() ;
		}
		catch (Exception e){e.printStackTrace();}
		
		for( int i=0; i<variableAff.split(",").length; i++ )
			if( !variableAff.trim().equals( "all" ) )
				listVariables.add( variableAff.split(",")[i].trim() );
			else
				for( String var:instance.code.getDonnees().getListVariables() )
					listVariables.add( var );
		
		Console.setListVariables(listVariables);
		
		while( instance.code.next() )
		{
			if( mode == 1 )
			{
				System.out.println(Console.afficher());
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String entree = "";
				try{entree = br.readLine();}catch(Exception e){e.printStackTrace();}
				if( instance.code.getLigneExecutable( instance.getCode().getLigneExec() ) instanceof Lecture )
					((Lecture)instance.code.getLigneExecutable( instance.getCode().getLigneExec() ) ).lire(entree);
			}
			if( mode == 2 )
			{
				if( Controleur.getInstance().getCode().getLigneExec() >= 25 )
				{
					System.out.println(Console.afficher());
					mode = 1;
				}
			}
			
		}
		System.out.println(Console.afficher());
	}
}	
