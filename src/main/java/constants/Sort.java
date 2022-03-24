package constants;

import java.util.ArrayList;
import java.util.List;

public class Sort {

	public static void main(String[] args) {
		String input = "edcba";
		String output = "abcde";
		System.out.println(sortingCharacter(input));
		sortingCharacter(input);

	}

	private static List<String> sortingCharacter(String tempInput) {
		List<String> list = new ArrayList<String>();
		char input [] = tempInput.toCharArray();
		for(int i=0;i<input.length;i++) {
			list.add(input.toString());
		}
		
		for(String str: list) {
			System.out.println(str);
		}
		return list;
		

	}
}
