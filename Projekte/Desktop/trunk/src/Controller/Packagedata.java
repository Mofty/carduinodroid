package Controller;

public class Packagedata {
	
	int[] valuesInt;
	double[] valuesDouble;
	
	public Packagedata(){
	}
	
	public void receive_package(String data) 
	{		int i = 0;
		String [] tokens = data.split(";",-1);
		valuesInt = new int[tokens.length];
		valuesDouble = new double[tokens.length];
		for (i = 0; i < 4; i++)
			{
			tokens[i] = tokens[i].trim(); //Leerzeichen weg vorn und hinten
			valuesInt[i] = Integer.parseInt(tokens[i]);		
			}
		for (i = 4; i < 6; i++)
		{
			tokens[i] = tokens[i].trim();
			valuesDouble[i] = Double.parseDouble(tokens[i]);
		}
	updateInformationsbox();
	}
	
	public void updateInformationsbox()
	{
		//MobileAvailable tokens[0]; 
		//WLANAvailable tokens[1];
		//Mobile tokens[2];
		//WLAN tokens[3];
		//GPS longitude tokens[4];
		//GPS latitude tokens[5];
		
	}
}
	

