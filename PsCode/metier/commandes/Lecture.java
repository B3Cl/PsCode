package fr.pcentreprise.pcode.metier.commandes;

public class Lecture extends Affecter
{

	public Lecture( String var, Object valeur )
	{
		super(var, valeur);
	}
	
	public Lecture( String var )
	{
		super(var);
	}
	
	public void lire( String valeur )
	{
		this.valeur = valeur;
	}
}

/**
 * A FINIR!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 */
