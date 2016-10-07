public class GenericStack<E> 
{
	private E[] arr = (E[])new Object[10];
	private int last = -1;

	public GenericStack() {}

	public int getSize()
	{
		return last + 1;
	}

	public E peek()
	{
		if(last == -1)
			return null;
		return arr[last];
	}

	public E pop()
	{
		if(last != -1)
		{
			E thingToReturn = arr[last];
			arr[last--] = null;
			return thingToReturn;
		}
		return null;
	}

	public void push(E given)
	{
		checkIfFullAndFix();
		arr[++last] = given;
	}

	public boolean isEmpty()
	{
		if(last == -1)
			return true;
		return false;
	}

	public String toString()
	{
		String toReturn = "[";
		for(int i = 0; i <= last; i++)
		{
			toReturn += arr[i];
			if(i != last)
				toReturn += ", ";
		}
		toReturn += "]";
		return toReturn;
		
	}

	private void checkIfFullAndFix()
	{
		if(last == arr.length-1)
		{
			E[] tempArr = (E[])new Object[2 * arr.length];
			for(int i = 0; i <= last; i++)
			{
				tempArr[i] = arr[i];
			}
			arr = tempArr;
		}
	}
}
