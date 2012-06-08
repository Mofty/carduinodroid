package Controller;

public class Packagedata {
	
	public void receive(String string) 
	{		int i = 0;
		String [] tokens = string.split(";",-1);
		// um mit den beiden arrays ausserhalb der methode recieve was anfangen zu können
		// wäre es sinnvoll die eins weiter nach oben unter class zu tun...
		boolean[] valuesB = new boolean[tokens.length];
		double[] valuesD = new double[tokens.length];
		for (i = 0; i < 4; i++)
			{
			tokens[i] = tokens[i].trim();
			valuesB[i] = Boolean.parseBoolean(tokens[i]);		
			}
		for (i = 4; i < 6; i++)
		{
			tokens[i] = tokens[i].trim();
			valuesD[i] = Double.parseDouble(tokens[i]);
		}
	}
	
	public void updateInformationsbox()
	{
		
	}
}
	

