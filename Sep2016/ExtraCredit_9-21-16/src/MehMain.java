import java.io.File;
import java.util.Scanner;

public class MehMain 
{
	public static void main(String []args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the name of a directory");
		File directory = new File(sc.next());
		File[] fileNames = directory.listFiles();
		for(int i = 0; i < fileNames.length; i++)
		{
			fileNames[i].renameTo(new File("file" + i + fileNames[i].getName().substring(fileNames[i].getName().lastIndexOf("."))));
		}
		sc.close();
	}
}
