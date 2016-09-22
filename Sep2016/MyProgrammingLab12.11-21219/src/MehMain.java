import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class MehMain 
{
	public static void main(String []args) throws FileNotFoundException
	{
		File master = new File("master");
		Scanner sc = new Scanner(master);
		File f1 = new File(sc.nextLine());
		PrintWriter printF1 = new PrintWriter(f1);
		Scanner newSC;
		
		while(sc.hasNextLine())
		{
			newSC = new Scanner(sc.nextLine());
			while(newSC.hasNextLine())
			{
				printF1.println(newSC.nextLine());
			}
			
			newSC.close();
		}
		
		printF1.flush();
		printF1.close();
		sc.close();
	}
}
