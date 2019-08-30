/*
THIS CODE WAS MY OWN WORK , IT WAS WRITTEN WITHOUT CONSULTING
CODE WRITTEN BY OTHER STUDENTS . _Gene Lee_
*/

public class HashSeparateChaining {

	private class Node {
		Entry entry;
		Node next;

		Node(Entry entry) { this.entry = entry; }
	}

	Node[] hashTable;
	int arraySize;
	int tableSize;

	public HashSeparateChaining(){
		hashTable = new Node[10];
		arraySize = 10;
	}

	/** TODO: Write a resizing method for the hash table.*/
	private void resize() {
		arraySize *= 2;
		Node[] copy = new Node[arraySize]; // Create a copy of the hash table with twice the size
		Node[] hold = hashTable;	// Create a holder for the hash table
		hashTable = copy;	// The copy is now referred to as hashTable

		for (int i = 0; i < arraySize/2; i++)	// For each index of the original array...
			for (Node current = hold[i]; current != null; current = current.next) // Go through each node in the chain
				put(current.entry.getKey(), current.entry.getValue());	// Put the key and value in the new hash table (each key is rehashed)
	}

	/** Computes the index in the hash table from a given key */
	private int hash(String key) {
		int hashCode = key.hashCode();
		return (hashCode & 0x7fffffff) % arraySize;
	}

	/** Returns the number of entries in the hash table. */
	public int size() { return tableSize; }

	/** Checks whether the hash table is empty */
	public boolean isEmpty() { return tableSize == 0; }

	/** Returns the node containing the given key value if it exists in the table.
	    Otherwise, it returns a null value. */
	private Node findEntry(String key) {
		int index = hash(key);

		Node currentNode = hashTable[index];
		while (currentNode != null && !currentNode.entry.getKey().equals(key))
			currentNode = currentNode.next;

		return currentNode;

	}

	/** Returns the integer value paired with the given key, if the key is in the table.
		Otherwise, it returns null. */
	public Integer get(String key) {
		Node searchResult = findEntry(key);

		if (searchResult != null)
			return searchResult.entry.getValue();
		else
			return null;

	}

	/** If the given key is not in the table, creates a new entry and adds it to the table.
		Otherwise, it updates the value associated with the given key. */
	public void put(String key, Integer value) {
		Node searchResult = findEntry(key);

		// The key exists in the table
		if (searchResult != null){ 
			searchResult.entry.setValue(value);
			return;
		}

		// The key does not exist in the table
		Entry newEntry = new Entry(key, value);
		Node newNode = new Node(newEntry);
		tableSize++;

		if (tableSize/arraySize >= 5){	// If the average chain length is over 5, resize
			tableSize = 1;
			resize();
		}

		int index = hash(key);
		if (hashTable[index] != null)
			newNode.next = hashTable[index];

		hashTable[index] = newNode;
	}

	/** Removes the entry containing the given key from the table, if the key exists in the table. */
	public void delete(String key) {
		Node searchResult = findEntry(key);
		// The key does not exist in the table.
		if (searchResult == null)
			return;

		// The key does exist in the table.
		int index = hash(key);
		if (hashTable[index] == searchResult)
			hashTable[index] = searchResult.next;
		else{
			Node currentNode = hashTable[index];
			while (currentNode.next != searchResult)
				currentNode = currentNode.next;
			currentNode.next = searchResult.next;
		}
	}

	/** Produces a string representation of the table. */
	@Override
	public String toString(){
		String output = "";

		for (int i = 0; i < arraySize; i++){
			output += "(" + i + "): ";
			Node currentNode = hashTable[i];
			if (currentNode == null)
				output += currentNode + "\n";
			else{
				while (currentNode != null){
					output += " -> " + currentNode.entry;
					currentNode = currentNode.next;
				}
				output += "\n";
			}
		}

		return output;

	}
}