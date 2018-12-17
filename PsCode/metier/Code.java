import java.io.File;
import java.io.BufferedReader;
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

package fr.pcentreprise.pcode.metier;
import fr.pcentreprise.pcode.metier.commandes.Affecter;
import fr.pcentreprise.pcode.metier.commandes.Ecriture;
import fr.pcentreprise.pcode.metier.structures.FinStructure;
import fr.pcentreprise.pcode.metier.structures.Si;
import fr.pcentreprise.pcode.metier.structures.Sinon;
import fr.pcentreprise.pcode.metier.structures.Structure;

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
	 * Liste des commandes du programme associ�es � leur ligne
	 */
	private Map<Integer, IExecutable> listCommandes;
	
	/**
	 * Attribut contenant les donn�es
	 */
	private Donnees donnees;
	
	/**
	 * Ligne du programme actuellement lu
	 */
	private int ligneExec;
	
	private List<Integer> deroulement;
	
	/**
	 * Fabrique qui v�rifie la validit� du programme � interpr�ter
	 */
	public static Code fabriqueCode(File fichier)
	{
		try
		{
			String sRet = "" ;
			
			InputStream ipsP = this.getClass().getResourceAsStream(fichier);
			InputStreamReader ipsrP = new InputStreamReader(ipsP);
			BufferedReader fichierP = new BufferedReader(ipsrP);
			
			String partie = "Algo"; //prend la valeur Algo puis Constantes puis Variables et enfin Programme pour d�finir les diff�rentes parties du programme
			String ligne  = null  ;
			
			while ((ligne = fichierP.readLine()) != null)
			{
				sRet += ligne + "\n";
			
				switch( partie )
				{
					case "Algo"      : if( ligne.equals( "constante:" ) ) partie = "Constante"; break ;
					case "Constante" : if( ligne.equals( "variable:"  ) ) partie = "Variable" ; break ;
					case "Variable"  : if( ligne.equals( "DEBUT"      ) ) partie = "Programme"; break ;
					case "Programme" : if( ligne.equals( "FIN"        ) ) partie = "Fini"     ; break ;
				}
			}
			sc.close();
			
			if (partie.equals("Fini"))
				return new Code ( sRet );
			else
				return null;
		}
		catch (Exception exc) {}
	}
	
	/**
	 * Constructeur qui construit le code � partir d'une chaine de caract�re envoy�e par la fabriqueCode
	 */
	private Code( String fichier )
	{
		this.donnees       = new Donnees();
		this.listCommandes = new HashMap<Integer, IExecutable>();
		this.listLignes    = new ArrayList<String>();
		this.ligneExec     = 0;
		this.deroulement   = new ArrayList<Integer>();
		
		Scanner sc = new Scanner( fichier );
		String partie = "Algo"; //prend la valeur Algo puis Constantes puis Variables et enfin Programme pour d�finir les diff�rentes parties du programme
		
		Stack<Structure> ouvertureStructures = new Stack<Structure>();
		while( sc.hasNext() )
		{
			String s = sc.nextLine();
			switch( partie )
			{
				case "Algo"      : if( s.equals( "constante:" ) ) partie = "Constante"; break;
				case "Constante" : if( s.equals( "variable:"  ) ) partie = "Variable" ; break;
				case "Variable"  : if( s.equals( "DEBUT"      ) ) partie = "Programme"; else this.creerVariable  ( s ); break;
				case "Programme" : if( s.equals( "FIN"        ) ) partie = "Fini"     ; else this.creerExecutable( s, ouvertureStructures ); break;
			}
			
			this.listLignes.add( s );
			this.ligneExec++;
		}
		sc.close();
		this.ligneExec = 0;
		
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
	 * @return la ligne avec le numero pass� en parametre
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
	 * m�thode utilis� par les IExecutables pour r�cup�rer la ligneExec
	 */
	public int getLigneExec()
	{
		return this.ligneExec;
	}
	
	/**
	 * m�thode utilis� par les IExecutables pour modifier la ligneExec
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
			else
				this.ligneExec++;
			return true;
		}else
			return false;
	}
	
	/**
	 * cr�� les variables dans les Donn�es
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
	 * cr�� un IExecutable en fonction du @param
	 */
	public void creerExecutable( String expression, Stack<Structure> ouvertureStructure )
	{
		if( expression.length() > 0 )
		{
			String exp = expression;
			if( exp.contains( "<--" ) && !exp.startsWith("ecrire") )
			{
				String var = exp.substring( 0, exp.indexOf( "<--" ) ).trim();
				Object obj = Interpreteur.resoudre( exp.substring( exp.indexOf( "<--" )+3 ).trim() );
				this.listCommandes.put(this.ligneExec, new Affecter( var, obj ));
			}
			
			if( exp.trim().startsWith("ecrire") )
			{
				Pattern p = Pattern.compile( "[(](.*)[)]$" );
				Matcher m = p.matcher( exp );
				while( m.find() )
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
		}
	}
}
