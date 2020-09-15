public class TestList {
	public static void main(String[] args) {
		SimpleLinkedList list = new SimpleLinkedList();

		list.insertLast("to");
		list.insertLast("be");
		list.insertLast("or");
		list.insertLast("not");
		list.insertLast("to");
		list.insertLast("be");

		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();

		list.removeLast();
		list.printList();
		System.out.println();
	}
}