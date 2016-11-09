/*
ID: jjjmase1
LANG: JAVA
TASK: ride
 */


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;

class ride 
{
	public static void main(String []args) throws Exception
	{
		BufferedReader in = new BufferedReader(new FileReader("ride.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
		if(getValue(in.readLine()) % 47 == getValue(in.readLine()) % 47)
			out.println("GO");
		else
			out.println("STAY");
		in.close();
		out.close();
		
	}
	
	
	public static int getValue(String input)
	{
		int total = 1;
		for(char s: input.toCharArray())
			total *= (s - ('A' - 1));
		return total;
	}
}
