 

public class DELinkedList {
	class Node{
		String data;
		Node next;

		Node(String data){
			this.data = data;
		}
	}

	Node first;
	Node last;

	public void insertFirst(String data){
		Node temp = new Node(data);
		temp.next = first;
		first = temp;

		if (last == null)
			last = first;
	}

	public void insertLast(String data){
		Node temp = new Node(data);

		if (first == null){
			first = last = temp;
			return;
		}

		last.next = temp;
		last = temp;
	}

	public void removeFirst(){
		if (first != null){
			first = first.next;
			if (first == null){
				last = null;
			}
		}
		else
			System.out.println("The list is empty!");
	}

	public void removeLast(){
		if (first == null){
			System.out.println("The list is empty!");
			return;
		}

		if (first.next == null){
			first = last = null;
			return;
		}

		Node x;
		for(x = first; x.next.next != null; x = x.next);
		x.next = null;
		last = x;
	}

	public void printList(){
		for (Node x = first; x != null; x = x.next)
			System.out.println(x.data);
	}

	public void searchList(String item){
		for (Node x = first; x != null; x = x.next){
			if (x.data.equals(item)){
				System.out.println("true");
				return;
			}
		}
		System.out.println("false");
	}

}