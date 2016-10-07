public class GenericStackTester 
{
	public static void main(String[] args) 
	{
		GenericStack<String> stack1 = new GenericStack<>(); 
		System.out.println("Stack1 size before adding elements: " + stack1.getSize());
		stack1.push("London");
		stack1.push("Paris");
		stack1.push("LA");
		System.out.println(stack1);
		System.out.println("Stack1 size after adding elements: " + stack1.getSize());

		GenericStack<Integer> stack2 = new GenericStack<>();
		stack2.push(2);
		stack2.push(-9);
		System.out.println("\n" + stack2.peek());
		stack2.push(21);
		System.out.println(stack2.pop());
		stack2.push(29);
		System.out.println(stack2);
		System.out.println("Stack2 size: " + stack2.getSize());

		GenericStack<Integer> stack3 = new GenericStack<>();
		for (int i = 0; i < 100; i++)
			stack3.push(i);
		System.out.println("\nStack3 size: " + stack3.getSize());
	}

}