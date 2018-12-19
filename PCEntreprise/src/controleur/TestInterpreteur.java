package controleur;

import bsh.Interpreter;

public class TestInterpreteur
{
	public static void main( String[] args )
	{
		Interpreter inter = new Interpreter();
		try{
			inter.eval("x = \"test\"");
			System.out.println( inter.get("x"));
		}catch(Exception e){e.printStackTrace();}
	}
}
