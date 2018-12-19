package metier;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controleur.Controleur;

import metier.commandes.Affecter;
import metier.commandes.Ecriture;
import metier.commandes.Lecture;
import metier.structures.FinStructure;
import metier.structures.Si;
import metier.structures.Sinon;
import metier.structures.Structure;
import metier.structures.Tantque;



/**
 * La classe Code contient le programme et gere si il est valide
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Code
{
	/**
	 * Liste des lignes du programme
	 */
	private List<String> listLignes;
	
	/**
	 * Liste des commandes du programme associées à leur ligne
	 */
	private Map<Integer, IExecutable> listCommandes;
	
	/**
	 * Attribut contenant les données
	 */
	private Donnees donnees;
	
	/**
	 * Ligne du programme actuellement lu
	 */
	private int ligneExec;
	
	private List<Integer> deroulement;
	
	/**
	 * Fabrique qui vérifie la validité du programme à interpréter
	 */
	/*public static Code fabriqueCode(String nomFichier)
	{
		try
		{
			String sRet = "" ;
			
			InputStream ipsP = .getClass().getResourceAsStream(nomFichier);
			InputStreamReader ipsrP = new InputStreamReader(ipsP);
			BufferedReader fichierP = new BufferedReader(ipsrP);
			
			String partie = "Algo"; //prend la valeur Algo puis Constantes puis Variables et enfin Programme pour définir les différentes parties du programme
			String ligne  = null  ;
			
			while ((ligne = fichierP.readLine()) != null)
			{
				sRet += ligne + "\n";
			}
				switch( partie )
				{
					case "Algo"      : if( ligne.equals( "constante:" ) ) partie = "Constante"; break ;
					case "Constante" : if( ligne.equals( "variable:"  ) ) partie = "Variable" ; break ;
					case "Variable"  : if( ligne.equals( "DEBUT"      ) ) partie = "Programme"; break ;
					case "Programme" : if( ligne.equals( "FIN"        ) ) partie = "Fini"     ; break ;
				}
			}
			fichierP.close();
			
			if (partie.equals("Fini"))
				return new Code ( sRet );
			else
				return null;
		}
		catch (Exception exc) {}
	}*/
	
	/**
	 * Constructeur qui vérifie si le programme est bien écrit à partir d'une chaine de caractere
	 */
	public Code( String nomFichier )
	{
		this.donnees       = new Donnees();
		this.listCommandes = new HashMap<Integer, IExecutable>();
		this.listLignes    = new ArrayList<String>();
		this.ligneExec     = 0;
		this.deroulement   = new ArrayList<Integer>();
		
		Stack<Structure> ouvertureStructures = new Stack<Structure>();
		
		
		try
		{
			InputStream ipsP = this.getClass().getResourceAsStream("/" + nomFichier);
			InputStreamReader ipsrP = new InputStreamReader(ipsP);
			BufferedReader fichierP = new BufferedReader(ipsrP);
			
			String ligne  = null  ;
			String partie = "Algo"; //prend la valeur Algo puis Constantes puis Variables et enfin Programme pour définir les différentes parties du programme
		
			while ((ligne = fichierP.readLine()) != null)
			{
				ligne = ligne.replaceAll( "\t", "    " );
				this.listLignes.add( ligne );
				
				switch( partie )
				{
					case "Algo"      : if( ligne.equals( "constante:" ) ) partie = "Constante"; break;
					case "Constante" : if( ligne.equals( "variable:"  ) ) partie = "Variable" ; break;
					case "Variable"  : if( ligne.equals( "DEBUT"      ) ) partie = "Programme"; else this.creerVariable  ( ligne ); break;
					case "Programme" : if( ligne.equals( "FIN"        ) ) partie = "Fini"     ; else this.creerExecutable( ligne, ouvertureStructures ); break;
				}
				
				
				
				this.ligneExec++;
			}
			fichierP.close();
			this.ligneExec = 0;
		}
		catch (Exception e) {e.printStackTrace();}
		
		/*for( int i=0; i<this.listLignes.size(); i++ )
			if( this.listCommandes.get(i) != null )
				System.out.println( i + " " + this.listCommandes.get( i ).toString() );
			else
				System.out.println( i + "" );
		*/
	}
	
	/**
	 * accesseur au donnees
	 */
	public Donnees getDonnees()
	{
		return this.donnees;
	}
	
	/**
	 * @return la ligne avec le numero passé en parametre
	 */
	public String getLigne( int num )
	{
		return new String( this.listLignes.get( num ) );
	}
	
	public IExecutable getLigneExecutable( int num )
	{
		return this.listCommandes.get( num );
	}
	
	/**
	 * @return la liste des lignes
	 */
	public List<String> getLignes()
	{
		return new ArrayList<String>( this.listLignes );
	}
	
	/**
	 * méthode utilisé par les IExecutables pour récupérer la ligneExec
	 */
	public int getLigneExec()
	{
		return this.ligneExec;
	}
	
	/**
	 * méthode utilisé par les IExecutables pour modifier la ligneExec
	 */
	public void setLigneExec( int ligneExec )
	{
		this.ligneExec = ligneExec;
	}
	
	public boolean next()
	{
		this.deroulement.add( this.ligneExec );
		if( this.ligneExec < this.listLignes.size() )
		{
			if( this.listCommandes.get(this.ligneExec) != null )
				this.listCommandes.get(this.ligneExec).executer();
			
			while( this.listCommandes.get(this.ligneExec) == null )
				this.ligneExec++;
			
			return true;
		}else
			return false;
	}
	
	/**
	 * créé les variables dans les Données
	 */
	public void creerVariable( String expression )
	{
		if( expression.length() > 0 )
		{
			String exp = expression;
			String type = exp.substring( exp.indexOf(':')+1 ).trim();
			exp = expression.substring(0, exp.indexOf(':') ).trim();
			String[] listeVariables = exp.split(",");
			for( String var:listeVariables )
				this.donnees.creer(var.trim(), type);
		}
	}
	
	/**
	 * créé un IExecutable en fonction du @param
	 */
	public void creerExecutable( String expression, Stack<Structure> ouvertureStructure )
	{
		Pattern p;
		Matcher m;
		
		if( expression.length() > 0 )
		{
			String exp = expression.trim();
			if( expression.contains( "<--" ) && !expression.startsWith("ecrire") )
			{
				String var = expression.substring( 0, expression.indexOf( "<--" ) ).trim();
				String cha = exp.substring( exp.indexOf( "<--" )+3 ).trim();
				this.listCommandes.put(this.ligneExec, new Affecter( var, cha ));
			}
			
			if( exp.startsWith( "lire" ) )
			{
				p = Pattern.compile( "[(](.*)[)]$" );
				m = p.matcher( exp );
				if( m.find() )
					exp = m.group( 1 ).trim();
				this.listCommandes.put(this.ligneExec, new Lecture( exp ));
			}
			
			if( exp.trim().startsWith("ecrire") )
			{
				p = Pattern.compile( "[(](.*)[)]$" );
				m = p.matcher( exp );
				if( m.find() )
					exp = m.group( 1 ).trim();
				this.listCommandes.put(this.ligneExec, new Ecriture( exp ));
			}
			
			if( exp.trim().startsWith( "si " ) )
			{
				int start = exp.indexOf("si ")+3;
				int end   = exp.indexOf("alors");
				exp = exp.substring(start, end);
				Si c = new Si( this.ligneExec,  exp.trim() );
				ouvertureStructure.push( c );
				this.listCommandes.put( this.ligneExec, c );
			}
			
			if( exp.trim().equals( "sinon" ) )
			{
				this.listCommandes.put(this.ligneExec, new Sinon( (Si) ouvertureStructure.lastElement() ) );
				((Si) ouvertureStructure.peek()).setNumSinon( this.ligneExec );
			}
			
			if( exp.trim().equals( "fsi" ) )
			{
				this.listCommandes.put(this.ligneExec, new FinStructure( (Si) ouvertureStructure.lastElement() ) );
				((Si) ouvertureStructure.pop()).setNumFin( this.ligneExec );
			}
			
			if( exp.trim().startsWith( "tq " ) )
			{
				int start = exp.indexOf("tq ")+2;
				int end   = exp.indexOf(" faire");
				exp = exp.substring(start, end);
				Tantque c = new Tantque( this.ligneExec,  exp.trim() );
				ouvertureStructure.push( c );
				this.listCommandes.put( this.ligneExec, c );
			}
			
			if( exp.trim().equals( "ftq" ) )
			{
				this.listCommandes.put(this.ligneExec, new FinStructure( (Tantque) ouvertureStructure.lastElement() ) );
				((Tantque) ouvertureStructure.pop()).setNumFin( this.ligneExec );
			}
		}
	}
}
