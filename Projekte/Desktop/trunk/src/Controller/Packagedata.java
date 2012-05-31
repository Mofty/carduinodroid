package Controller;


public class Packagedata {

	public void Split() 
	{
		String [] tokens = test.split(";",-1);
		for (int i = 0; i < tokens.length; i++)
			{
			tokens[i] = tokens[i].trim();
			System.out.println("i"+i +":"+ tokens[i]); //test
			}
	}
}
