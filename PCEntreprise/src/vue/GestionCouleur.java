package vue;

import org.fusesource.jansi.AnsiConsole;

/**
  * Cette classe permet d'appeler le code ANSI des couleurs pour la console.
  * Le code couleur est renvoye sous forme de chaine de caractère.
  * La coloration fonctionne aussi bien sur Windows que sur Linux.
  */

public class GestionCouleur
{
	public static GestionCouleur instance = new GestionCouleur();
	private String   nomSysteme;
	private static String[] tabCouleur;
	
	/**
	  * Constructeur privee d'une couleur pour l'attribut d'instance instance.
	  */
	private GestionCouleur()
	{
		String nomSysteme = System.getProperty("os.name");
		
		if (nomSysteme.charAt(0) == 'W')
			AnsiConsole.systemInstall();
		
		tabCouleur = new String[] { "\033[44m" , //bleu   en fond	indice 0
		                            "\033[41m" , //rouge  en fond	indice 1
		                            "\033[42m" , //vert   en fond	indice 2
		                            "\033[93m" , //jaune  en texte	indice 3
		                            "\033[94m" , //bleu   en texte	indice 4
		                            "\033[95m" , //violet en texte	indice 5
		                            "\033[96m" , //cyan   en texte	indice 6
		                            "\033[37m" , //retour a la valeur initiale du texte
		                            "\u001b[0m"  //retour a la valeur initiale surlignage (+ texte)
		                          };
	}
	/**
	  * Retourne le code de remise à zéro du texte
	  */
	public static String resetTextColor() { return tabCouleur[7]; }
	/**
	  * Retourne le code de remise à zéro du surlignage
	  */
	public static String resetUnderlineColor() { return tabCouleur[8]; }
	/**
	  * Retourne la couleur du lire
	  */
	public static String getCoulLire() { return tabCouleur[5]; }
	
	/**
	  * Retourne la couleur du ecrire
	  */
	public static String getCoulEcrire() { return tabCouleur[4]; }
	
	/**
	  * Retourne la couleur de la commande
	  */
	public static String getCoulCmd() { return tabCouleur[6]; }
	
	/**
	  * Retourne la couleur de la ligne
	  */
	public static String getUnderline() { return tabCouleur[0]; }
	
	/**
	  * Retourne la couleur de la ligne (si booleen vrai)
	  */
	public static String getUnderlineTrue() { return tabCouleur[2]; }
	
	/**
	  * Retourne la couleur de la ligne (si booleen faux)
	  */
	public static String getUnderlineFalse() { return tabCouleur[1]; }
}
