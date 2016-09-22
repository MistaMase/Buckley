import java.io.FileNotFoundException;

public class TheMain 
{
	public static void main(String []args) throws FileNotFoundException
	{
		String file1 = "first.txt", file2 = "second.txt";
		java.io.File first = new java.io.File(file1);
		java.util.Scanner scanningFirst = new java.util.Scanner(first);
		java.io.File second = new java.io.File(file2);
		java.util.Scanner scanningSecond = new java.util.Scanner(second);
		java.io.File outFile = new java.io.File((file1 + "-" + file2));
		java.io.PrintWriter out = new java.io.PrintWriter(outFile);
		
		while(scanningFirst.hasNextLine() && scanningFirst.hasNextLine())
		{
			out.println(scanningFirst.nextLine());
			out.println(scanningSecond.nextLine());
			
		}
		
		java.io.File leftovers = new java.io.File("leftovers");
		java.io.PrintWriter writeLeftovers = new java.io.PrintWriter(leftovers);
		if(scanningFirst.hasNextLine())
		{
			while(scanningFirst.hasNextLine())
			{
				writeLeftovers.println(scanningFirst.nextLine());
			}

		}
		else if(scanningSecond.hasNextLine())
		{
			while(scanningSecond.hasNextLine())
			{
				writeLeftovers.println(scanningSecond.nextLine());
			}
		}
		
		
		
		
		scanningFirst.close();
		scanningSecond.close();
		out.close();
		out.flush();
		writeLeftovers.flush();
		writeLeftovers.close();
	}
}
