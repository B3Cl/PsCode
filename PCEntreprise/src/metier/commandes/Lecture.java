package metier.commandes;

public class Lecture extends Affecter
{

	public Lecture( String var, String exp )
	{
		super(var, exp);
	}
	
	public Lecture( String var )
	{
		super(var);
	}
	
	public void lire( String exp )
	{
		this.exp = exp;
	}
}