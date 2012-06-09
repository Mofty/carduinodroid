package Controller;

public class Packagedata {
	
	
	
	public void receive_package(String data) 
	{		int i = 0;
		String [] tokens = data.split(";",-1);
		// um mit den beiden arrays ausserhalb der methode recieve was anfangen zu können
		// wäre es sinnvoll die eins weiter nach oben unter class zu tun...
		int[] valuesInt = new int[tokens.length];
		double[] valuesDouble = new double[tokens.length];
		for (i = 0; i < 4; i++)
			{
			tokens[i] = tokens[i].trim();
			valuesInt[i] = Integer.parseInt(tokens[i]);		
			}
		for (i = 4; i < 6; i++)
		{
			tokens[i] = tokens[i].trim();
			valuesDouble[i] = Double.parseDouble(tokens[i]);
		}
	}
	
	public void updateInformationsbox()
	{
		
	}
}
	

