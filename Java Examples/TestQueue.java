import java.util.*;
import java.io.*;

public class TestQueue {
	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(new File("tobe.txt"));
		SimpleQueue<String> q = new SimpleQueue<>();

		while (input.hasNext()){
			String item = input.next();

			if (item.equals("-")) 
				System.out.print(q.dequeue() + " ");
			else
				q.enqueue(item);
		}

		System.out.println();
	}
}