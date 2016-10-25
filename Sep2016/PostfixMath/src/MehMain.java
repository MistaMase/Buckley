public class MehMain 
{
	public static void main(String []args)
	{
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.err.println("Enter your number with each element seperated by a space with ~ to exit \nDISCLAIMER: Integer Division Happens\n");
		String input = sc.nextLine();
		java.util.List<String> parts = new java.util.ArrayList<>(java.util.Arrays.asList(input.split(" ")));
		while(!parts.get(0).contains("~"))
		{
			try{
			java.util.Stack<Integer> s = new java.util.Stack<>();
			while(parts.size() != 0)
			{				
				if(Character.isDigit(parts.get(0).charAt(0)))
				{
					int thing = Integer.valueOf(parts.remove(0)).intValue();
					s.push(thing);
				}
				else
				{
					int second = s.pop();
					int first = s.pop();
					String operator = parts.remove(0);
					if(operator.charAt(0) == '/')
						s.push((int)(first / second));
					else if(operator.charAt(0) == '*')
						s.push(first * second);
					else if(operator.charAt(0) == '+')
						s.push(first + second);
					else if(operator.charAt(0) == '-')
						s.push(first - second);
				}
			}
			
			System.err.println("\n" + s.peek());
			} catch(Exception e){
				System.err.println("You did not enter correctly in postfix");
			}
			
			
			
			
			System.err.println("Enter your number with each element seperated by a space with ~ to exit\n");
			input = sc.nextLine();
			parts = new java.util.ArrayList<>(java.util.Arrays.asList(input.split(" ")));
		}
		
		System.exit(0);
		
	}
}
