 

import java.util.Scanner;
import java.io.File;


public class testStack {
	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(new File("tobe.txt"));
		ArrayStack<String> stack = new ArrayStack<String>();

		while (input.hasNext()){
			String item = input.next();

			if (item.equals("-")) 
				System.out.print(stack.pop() + " ");
			else
				stack.push(item);
		}

		System.out.println();

		// for (String s : stack) {
		// 	System.out.println(s);
		// }

	}

}