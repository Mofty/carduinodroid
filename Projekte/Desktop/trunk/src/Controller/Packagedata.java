package Controller;

public class Packagedata {
	
	public void receive(String string) 
	{		
		String [] tokens = string.split(";",-1);
		//int[] values = new int[tokens.length];
		for (int i = 0; i < tokens.length; i++)
			{
			tokens[i] = tokens[i].trim();
			//values[i] = Integer.parseInt(tokens[i]);
			System.out.println("i"+i +":"+ tokens[i]); //test			
			}
	}
	
	public void updateInformationsbox()
	{
		
	}
}
	

