/*
 * JJ Mase
 * The Buckley School
 * Senior-5 Division
 */

import java.util.ArrayList;
import java.util.Scanner;

public class DisMain 
{
	public static void main(String []args)
	{
		Scanner sc = new Scanner(System.in);

		for(int i = 0; i < 5; i++)
		{
			try
			{
				System.out.println("Please enter the input for input " + (i + 1));
				System.out.println(method(sc.nextLine()));

			} catch (Exception e) {
				System.out.println("Sorry, I couldn't get that one");
			}
		}
		
		sc.close();
	}

	public static String method(String input)
	{
		ArrayList<String> value = new ArrayList<>(java.util.Arrays.asList(input.split(" "))); 
		ArrayList<String> suit = new ArrayList<>();

		//Change 10, J, Q, K and A to numbers
		for(int i = 0; i < value.size(); i++)
			if(value.get(i).charAt(0) == 'T') {
				suit.add("" + value.get(i).charAt(1));
				value.set(i, "" + 10);
			}
			else if(value.get(i).charAt(0) == 'J') {
				suit.add("" + value.get(i).charAt(1));
				value.set(i, "" + 11);
			}
			else if(value.get(i).charAt(0) == 'Q') {
				suit.add("" + value.get(i).charAt(1));
				value.set(i, "" + 12);
			}
			else if(value.get(i).charAt(0) == 'K') {
				suit.add("" + value.get(i).charAt(1));
				value.set(i, "" + 13);
			}
			else if(value.get(i).charAt(0) == 'A') {
				suit.add("" + value.get(i).charAt(1));
				value.set(i, "" + 0);
			}
			else {
				suit.add("" + value.get(i).charAt(1));
				value.set(i, "" + value.get(i).charAt(0));
			}

		String key = value.remove(0);
		String keySuit = suit.remove(0);

		//Check for same suit
		boolean can = false;
		for(int i = 0; i < suit.size(); i++)
			if(keySuit.charAt(0) == suit.get(i).charAt(0))
			{
				can = true;
				break;
			}

		if(can)
		{
			for(int i = 0; i < suit.size(); i++)
				if(suit.get(i).charAt(0) != keySuit.charAt(0))
				{
					value.remove(i);
					suit.remove(i);
					i--;
				}

			if(value.size() == 1)
			{
				System.out.println("Place 1\n");
				if(value.get(0) == "0")
					return "Returns: " + "A" + suit.get(0);
				else if(value.get(0) == "10")
					return "Returns: " + "A" + suit.get(0);
				else if(value.get(0) == "11")
					return "Returns: " + "A" + suit.get(0);
				else if(value.get(0) == "12")
					return "Returns: " + "A" + suit.get(0);
				else if(value.get(0) == "13")
					return "Returns: " + "A" + suit.get(0);
				else
					return "Returns: " + value.get(0) + suit.get(0);
			}


			//Checking if has higher card in the same suit
			ArrayList<String> higherCards = new ArrayList<>();
			for(String i: value)
				if(Integer.parseInt(i) >= Integer.parseInt(key))
					higherCards.add(i);

			//If he does have a higher value of the same suit
			if(higherCards.size() == 0)
			{
				int lowest = 100;
				for(String i: value)
					if(lowest > Integer.parseInt(i))
						lowest = Integer.parseInt(i);
				System.out.println("Place 2\n");
				return printProperly(suit, value, lowest);
			}
			else
			{	
				int lowestHighest = 100;
				for(String i: value)
					if(Integer.parseInt(i) > Integer.valueOf(key) && Integer.parseInt(i) < lowestHighest)
						lowestHighest = Integer.parseInt(i);
				System.out.println("Place 3\n");
				return printProperly(suit, value, lowestHighest);
			}
		}

		//If doesn't have a card of the same suit
		else
		{
			int checkForSameLowest = 100;
			for(String i: value)
				if(Integer.parseInt(i) < checkForSameLowest)
					checkForSameLowest = Integer.parseInt(i);
			if(checkForSameLowest == Integer.parseInt(key))
			{
				System.out.println("Place 4\n");
				if(suit.contains("C"))
					return printProperlyValueGivenSuit(value, checkForSameLowest, "C");
				else if(suit.contains("D"))
					return printProperlyValueGivenSuit(value, checkForSameLowest, "D");
				else if(suit.contains("H"))
					return printProperlyValueGivenSuit(value, checkForSameLowest, "H");
				else if(suit.contains("S"))
					return printProperlyValueGivenSuit(value, checkForSameLowest, "S");
			}

			int lowest = 100;
			for(String i: value)
				if(lowest > Integer.parseInt(i))
					lowest = Integer.parseInt(i);
			System.out.println("Place 5\n");
			return printProperly(suit, value, lowest);
		}
	}

	public static String printProperly(ArrayList<String> suit, ArrayList<String> value, int reason)
	{
		System.out.println(suit + "\n" + value + "\n" + reason);
		if(value.get(value.indexOf(reason + "")) == "0")
			return "Returns: " + "A" + suit.get(value.indexOf(reason + ""));
		else if(value.get(value.indexOf(reason + "")) == "10")
			return "Returns: " + "T" + suit.get(value.indexOf(reason + ""));
		else if(value.get(value.indexOf(reason + "")) == "11")
			return "Returns: " + "J" + suit.get(value.indexOf(reason + ""));
		else if(value.get(value.indexOf(reason + "")) == "12")
			return "Returns: " + "Q" + suit.get(value.indexOf(reason + ""));
		else if(value.get(value.indexOf(reason + "")) == "13")
			return "Returns: " + "K" + suit.get(value.indexOf(reason + ""));
		else
			return "Returns: " + value.get(value.indexOf(reason + "")) + suit.get(value.indexOf(reason + ""));
	}

	public static String printProperlyValueGivenSuit(ArrayList<String> value, int reason, String theSuit)
	{
		System.out.println(value + "\n" + reason + "\n" + theSuit);
		if(value.get(value.indexOf(reason + "")) == "0")
			return "Returns: " + "A" + theSuit;
		else if(value.get(value.indexOf(reason + "")) == "10")
			return "Returns: " + "T" + theSuit;
		else if(value.get(value.indexOf(reason + "")) == "11")
			return "Returns: " + "J" + theSuit;
		else if(value.get(value.indexOf(reason + "")) == "12")
			return "Returns: " + "Q" + theSuit;
		else if(value.get(value.indexOf(reason + "")) == "13")
			return "Returns: " + "K" + theSuit;
		else
			return "Returns: " + value.get(value.indexOf(reason + "")) + theSuit;
	}

}
