public class SimpleLinkedList {
	class Node{
		String data;
		Node next;

		Node(String data){
			this.data = data;
		}
	}

	Node first;

	public void insertFirst(String data){
		Node temp = new Node(data);
		temp.next = first;
		first = temp;
	}

	public void insertLast(String data){
		Node temp = new Node(data);

		if (first == null){
			first = temp;
			return;
		}
		Node x;
		for(x = first; x.next != null; x = x.next);
		x.next = temp;
	}

	public void removeFirst(){
		if (first == null){
			System.out.println("The list is empty");
			return;
		}

		first = first.next;
	}

	public void removeLast(){
		if (first == null){
			System.out.println("The list is empty!");
			return;
		}

		if (first.next == null){
			first = null;
			return;
		}

		Node x;
		for(x = first; x.next.next != null; x = x.next);
		x.next = null;
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