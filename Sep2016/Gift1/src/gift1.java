/*
ID: jjjmase1
LANG: JAVA
TASK: gift1
 */
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class gift1 
{
	public static void main(String []args) throws Exception
	{
		BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
		PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
		int numNames = Integer.valueOf(in.readLine());
		Map<String, Integer> people = new LinkedHashMap<>();
		System.out.println(numNames);
		for(int i = 0; i < numNames; i++)
		{
			System.out.println(i);
			people.put(in.readLine(), 0);
		}
		for(int i = 0; i < numNames; i++)
		{
			System.out.println("\t" + i);
			String currentPerson = in.readLine();
			System.out.println(currentPerson);
			String line = in.readLine();
			int money = Integer.parseInt(line.substring(0, line.indexOf(" ")));
			int numPpl = Integer.parseInt(line.substring(line.indexOf(" ") + 1));
			if(numPpl != 0 || money != 0)
			{
				people.put(currentPerson, people.get(currentPerson) - (numPpl * (money / numPpl)));
				for(int j = 0; j < numPpl; j++)
				{
					String thisPerson = in.readLine();
					people.put(thisPerson, people.get(thisPerson) + (money / numPpl));
				}
			}
		}
		
		for(String s: people.keySet())
		{
			out.println(s + " " + people.get(s));
		}
		in.close();
		out.close();
	}
}
