import java.io.FileNotFoundException;

public class MehMain 
{
	public static void main(String []args) throws FileNotFoundException
	{
		java.util.Scanner inputSC = new java.util.Scanner(System.in);
		System.out.println("Please enter the name of the file you wish to change");
		String fileInput = inputSC.nextLine();
		java.io.File in = new java.io.File(fileInput);
		java.util.Scanner sc = new java.util.Scanner(in);
		java.io.File out = new java.io.File("ThisJavaFile.java");
		java.io.PrintWriter output = new java.io.PrintWriter(out);


		boolean firstTime = true;
		while(sc.hasNextLine())
		{
			String tempString = sc.nextLine();
			if(tempString.contains("{"))
			{
				output.print(" {");
			}
			else if(tempString.length() == 0) {}
			else if(firstTime)
				output.print(tempString);
			else
			{
				output.println();
				output.print(tempString);
			}
			firstTime = false;
		}
		
		inputSC.close();
		sc.close();
		output.close();
	}
}