public class Main 
{
	public static void main(String []args)
	{
		java.util.Scanner sc = new java.util.Scanner(System.in);
		System.err.println("Enter your number with each element seperated by a space with ~ to exit \nEnter /T to toggle between Infix to Postfix, Infix Evaluation, and Postfix Evaluation\nDISCLAIMER: Integer Division Happens");
		String input = sc.nextLine();
		java.util.List<String> part = new java.util.ArrayList<>(java.util.Arrays.asList(input.split(" ")));
		int currentMode = 0;
		while(!part.get(0).contains("~"))
		{		
			if(part.get(0).contains("/T"))
			{
				if(currentMode == 2)
					currentMode = 0;
				else
					currentMode++;
				if(currentMode == 0)
					System.out.println("\tYou are currently in Infix to Postfix Mode");
				else if(currentMode == 1)
					System.out.println("\tYou are currently in Infix Evaluation Mode");
				else if(currentMode == 2)
					System.out.println("\tYou are currently in Postfix Evaluation Mode");
				
			}
			else if(currentMode == 0)
				System.err.println("Output: " + infixToPostfix(part));
			else if(currentMode == 1)
				System.err.println("Output: " + postfixEvaluationQueue(infixToPostfix(part)));
			else
				System.err.println("Output: " + postfixEvaluationList(part));
			
			System.err.println("Enter your number with each element seperated by a space with ~ to exit");
			input = sc.nextLine();
			part = new java.util.ArrayList<>(java.util.Arrays.asList(input.split(" ")));
		}

		System.exit(0);

	}

	public static String postfixEvaluationList(java.util.List<String> part)
	{
		java.util.Stack<Integer> s = new java.util.Stack<>();
		try{
			while(part.size() != 0)
			{				
				if(Character.isDigit(part.get(0).charAt(0)))
				{
					int thing = Integer.valueOf(part.remove(0)).intValue();
					s.push(thing);
				}
				else
				{
					int second = s.pop();
					int first = s.pop();
					String operator = part.remove(0);
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
		} catch(Exception e){
			return("You did not enter correctly in postfix");
		}
		return "" + s.peek();

	}
	
	public static String postfixEvaluationQueue(java.util.Queue<String> parts)
	{
		java.util.Stack<Integer> s = new java.util.Stack<>();
		try{
			while(parts.size() != 0)
			{			
				String current = parts.poll();
				if(Character.isDigit(current.charAt(0)))
				{
					int thing = Integer.valueOf(current).intValue();
					s.push(thing);
				}
				else
				{
					int second = s.pop();
					int first = s.pop();
					String operator = current;
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
		} catch(Exception e){
			return("You did not enter correctly in postfix");
		}
		return "" + s.peek();

	}


	public static java.util.Queue<String> infixToPostfix(java.util.List<String> part)
	{
		java.util.Stack<String> operators = new java.util.Stack<>();
		java.util.Queue<String> output = new java.util.LinkedList<>();

		while(!part.isEmpty())
		{	

			String current = part.remove(0);
			if(Character.isDigit(current.charAt(0)))
				output.offer(current);
			else if(current.charAt(0) == '/' || current.charAt(0) == '*' || current.charAt(0) == '+' || current.charAt(0) == '-')
			{
				if(operators.size() >= 1 && !operators.firstElement().equals("(") && opValue(operators.peek()) >= opValue(current))
				{
					output.offer(operators.pop());
					operators.push(current);
				}
				else
					operators.push(current);		
			}
			else if(current.charAt(0) == '(')
				operators.push(current);
			else if(current.charAt(0) == ')')
			{
				while(!operators.peek().equals("("))
				{
					output.offer(operators.pop());
				}
				operators.pop();
				if(operators.size() >= 1)
					output.offer(operators.pop());
			}
		}

		while(!operators.isEmpty())
			output.offer(operators.pop());
		
		return output;


	}

	public static int opValue(String s)
	{
		if(s.charAt(0) == '*' || s.charAt(0) == '/')
			return 2;
		else if(s.charAt(0) == '+' || s.charAt(0) == '-')
			return 1;
		else return 0;
	}
}