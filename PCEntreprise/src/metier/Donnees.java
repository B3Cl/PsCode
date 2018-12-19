package metier;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controleur.Controleur;

import bsh.Interpreter;

/**
 * La classe Donnees contient les données du programme
 * @author  Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Donnees
{
	private Interpreter inter;
	private List<String> listVariables;
	
	/**
	 * Constructeur qui initialise le tableau associatif listVariables
	 */
	public Donnees()
	{
		this.inter = new Interpreter();
		this.listVariables = new ArrayList<String>();
	}
	
	/**
	 * Méthode qui créer une nouvelle variable
	 */
	public void creer( String var, String type )
	{
		Object obj = null;
		try{
			obj = this.inter.eval( var );
		
			if( obj == null )
			{
				switch( type )
				{
					case "entier"    : this.inter.eval( "int "     + var ); break;
					case "reel"      : this.inter.eval( "double "  + var ); break;
					case "booleen"   : this.inter.eval( "boolean " + var ); break;
					case "caractere" : this.inter.eval( "char "    + var ); break;
					case "chaine"    : this.inter.eval( var + "=\"\""    ); break;
				}
			}
			
			this.listVariables.add( var );
		}catch(Exception e){e.printStackTrace();}
	}
	
	public List<String> getListVariables()
	{
		return this.listVariables;
	}
	
	/**
	 * @return la valeur de la variable entrée en parametre
	 */
	public Object get( String var )
	{
		try{
			return this.inter.get( var );
		}catch(Exception e){return null;}
	}
	
	public String getType( String nom )
	{
		try{
			Object var = this.inter.get( nom );
			
			if( var instanceof Integer   ) return "entier"   ;
			if( var instanceof Double    ) return "réel"     ;
			if( var instanceof Boolean   ) return "booleen"  ;
			if( var instanceof Character ) return "caractere";
			if( var instanceof String    ) return "chaine"   ;
		
		}catch(Exception e){e.printStackTrace();}
		return "";
	}
	
	public Object resoudre( String expression )
	{
		String exp = expression;
		String expFinal = "";
		
		while( exp.length() > 0 )
		{
			exp = exp.trim();
			Pattern p = Pattern.compile("^\"([^\"]*)\"");
			Matcher m = p.matcher( exp );
						
			if( m.find() )
			{
				expFinal += "\"" + m.group(1) + "\"";
				exp = exp.substring( exp.indexOf( m.group(1) ) + m.group(1).length()+1 );
			}else {
				
				p = Pattern.compile( "^©" );
				m = p.matcher( exp );
				
				if( m.find() )
				{
					expFinal += "+";
					exp = exp.substring( 1 );
				}else {
					p = Pattern.compile( "^<--" );
					m = p.matcher( exp );
					
					if( m.find() )
					{
						expFinal += "=";
						exp = exp.substring( 3 );
					}else {
						
						p = Pattern.compile( "^([()×+/-<>=,0-9]+)" );
						m = p.matcher( exp );
						if( m.find() )
						{
							expFinal += m.group(1).replaceAll( "×" , "*" ).replaceAll(",", ".");
							exp = exp.substring( m.group(1).length() );
							
						}else {
						
							p = Pattern.compile( "^([a-zA-Z][a-zA-Z0-9]*)" );
							m = p.matcher( exp );
							if( m.find() )
							{
								expFinal += m.group(1);
								exp = exp.substring( m.group(1).length() );
							}
						}
					}
				}
			}
			expFinal += " ";
		}
		try {
			return this.inter.eval(expFinal);
		}catch(Exception e){return null;}
	}
}
