import java.io.FileNotFoundException;

public class MehMain 
{
	public static void main(String []args) throws FileNotFoundException
	{
		String wordToRemove = "blah";
		java.io.File in = new java.io.File("textFile.txt");
		java.io.File out = new java.io.File("modifiedTextFile.txt");
		java.util.Scanner sc = new java.util.Scanner(in);
		java.io.PrintWriter output = new java.io.PrintWriter(out);
		
			while(sc.hasNext())
			{
				String s1 = sc.nextLine();
				String s2 = s1.replaceAll((" " + wordToRemove), "");
				s2 = s2.replaceAll((wordToRemove + " "), "");
				s2 = s2.replaceAll(wordToRemove, "");
				output.println(s2);
			}
			
		sc.close();
		output.close();
	}
}
