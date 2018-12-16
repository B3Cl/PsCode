package fr.pcentreprise.pcode.metier;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * La classe Interpreteur permet de lire une expression (boolean, mathématique ou encore de chaine )
 * @author Equipe 11
 * @version 1.0, 2018-12-17
 */
public class Interpreteur
{
	public static Object resoudre( String expression )
	{
		Object res = null;
		
		/*Pattern p1 = Pattern.compile( "\"([^\"]*)\"" );
		Pattern p2 = Pattern.compile( "\"[^\"]*\"([^\"]*)" );
		
		
		Matcher m = p1.matcher(expression);
		while( m.find() )
			System.out.println(m.group(1));
		
		m = p2.matcher( expression );
		while( m.find() )
			System.out.println(m.group(1).trim());*/
		
		//Si l'expression est une chaine de caractere du debut à la fin
		Pattern p = Pattern.compile( "^\"([^\"]*)\"$" );
		Matcher m = p.matcher(expression);
		while( m.find() )
			return new String( m.group(1) );
		
		//Si l'expression est une concaténation de chaine de caractere
		p = Pattern.compile( "\"[^\"]*\"([^\"]*)\"[^\"]*\"" );
		m = p.matcher(expression);
		Boolean isConcatenationChaine = true;
		while( m.find() )
			if( !m.group(1).trim().contains("©") )
				isConcatenationChaine = false;
		if( isConcatenationChaine )
		{
			String s = "";
			
			p = Pattern.compile( "\"([^\"]*)\"" );
			m = p.matcher(expression);
			while( m.find() )
				s += m.group(1);
			
			return s;
		}
		
		return res;
	}
	
	
	public static void main(String[] args)
	{
		System.out.println( resoudre( "\"Ceci est une chaine de caractere. Elle ne doit pas etre intepreté. Voila.\"" ) );
		System.out.println( resoudre( "\"Ceci est une chaine de caractere.\" © \" Elle ne doit pas etre intepreté. Voila.\"" ) );
		System.out.println( resoudre( "5+6" ) );
	}
}
