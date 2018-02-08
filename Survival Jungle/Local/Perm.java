package Local;

import java.util.Arrays;
import java.util.Scanner;

public class Perm {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		boolean finished = false;

		String base;
		String subset;
		String end;

		while (!finished) {

			System.out.println("Please input the base string: ");
			base = scan.nextLine();
			System.out.println("Please input the subset string: ");
			subset = scan.nextLine();

			System.out.println("I found \"" + subset + "\" in \"" + base + "\" " + countPerm(base, subset)
					+ " time(s).\nWould you like to continue?");

			end = scan.nextLine().toLowerCase();

			if (end.equals("yes")) {
				continue;
			} else {
				System.out.println("Bye!");
				finished = true;
			}
		}
		scan.close();
	}

	public static boolean isPerm(String a, String b) {
		if (a.length() == b.length()) {
			int[] word1 = new int[a.length()];
			int[] word2 = new int[b.length()];

			for (int i = 0; i < word1.length; i++) {
				word1[i] = a.charAt(i);
				word2[i] = b.charAt(i);
			}

			Arrays.sort(word1);
			Arrays.sort(word2);

			for (int i = 0; i < word1.length; i++) {
				if (word1[i] != word2[i]) {
					return false;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public static int countPerm(String base, String subset) {
		int amountP = 0;
		int start = 0;
		int end = subset.length();

		base = base.toLowerCase();
		subset = subset.toLowerCase();

		for (int i = 0; i < base.length(); i++) {
			if ((i + subset.length()) <= base.length()) {
				if (isPerm(subset, base.substring(start, end))) {
					amountP++;
				}
				start++;
				end++;
			}
		}
		return amountP;
	}

}