import java.util.*;
public class DisMain 
{	
	public static void main(String []args)
	{
		Scanner sc = new Scanner(System.in);
		int input = sc.nextInt();
		List<Integer> l = new LinkedList<>();
		while(input != 0)
		{
			l.add(input);
			input = sc.nextInt();
		}
		
		System.out.println(maxOccurrences(l));
	}
	
	public static List<Integer> maxOccurrences(List<Integer> input)
	{
		if(input.isEmpty())
			return new LinkedList<Integer>();
		Map<Integer, Integer> nums = new HashMap<Integer, Integer>();
		for(Integer s: input)
		{
			if(nums.containsKey(s))
				nums.put(s, nums.get(s) + 1);
			else
				nums.put(s, 1);
		}
		List<Integer> most = new LinkedList<>();
		most.add(input.get(0));
		for(Integer s: nums.keySet())
		{
			if(nums.get(s) > nums.get(most.get(0)))
			{
				most.clear();
				most.add(s);
			}
			else if(nums.get(s) == nums.get(most.get(0)))
				most.add(s);
		}
		
		return most; 
	}
}
