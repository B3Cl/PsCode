package fr.pcentreprise.pcode.metier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import bsh.Interpreter;
import fr.pcentreprise.pcode.controleur.Controleur;

/**
 * La classe Interpreteur permet de lire une expression (boolean, mathématique ou encore de chaine )
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Interpreteur
{
	private static Interpreter inter = new Interpreter();
	
	public static Object resoudre( String expression )
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
							String var = "";
							if( Controleur.getInstance().getCode().getDonnees().getListVariables().contains( m.group(1) ) )
								var += Controleur.getInstance().getCode().getDonnees().get( m.group(1) );
							else
								var += m.group(1);
							
							expFinal += var;
							exp = exp.substring( m.group(1).length() );
						}
					
					}
				}
			}
		}
		try {
			return Interpreteur.inter.eval(expFinal);
		}catch(Exception e){return null;}
	}
	
	public static void main(String[] args)
	{
		System.out.println("Résultat: " + resoudre( "\"Ceci est une chaine de caractere. Elle ne doit pas etre interpreté. Voila.\"" ) );
		System.out.println("Résultat: " + resoudre( "\"Ceci est une © chaine de caractere.\" © \" Elle ne doit pas etre interpreté. Voila.\"" ) );
		System.out.println("Résultat: " + resoudre( "\"Ceci est une © chaine de caractere.\" © (8+5) " ) );
		System.out.println("Résultat: " + resoudre( "5,0 == 5 " ) );
		System.out.println("Résultat: " + resoudre( "(4+5)+6×6 +( 7×(8×6))" ) );
		System.out.println("Résultat: " + resoudre( "x" ) );
		System.out.println("Résultat: " + resoudre( "x >= 5 " ) );
	}
}
