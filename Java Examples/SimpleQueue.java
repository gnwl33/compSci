@SuppressWarnings("unchecked")
public class SimpleQueue<Thing>{
	Thing[] q;
	int n = 0;    // size of queue
	int head = 0;
	int tail = 0;

	SimpleQueue(){
		q = (Thing[]) new Object[1];
	}

	public void enqueue(Thing item){
		if (n == q.length){
			resize(2*n);
		}

		q[tail] = item;
		tail++;

		if (tail == q.length)
			tail = 0;
		n++;
	}

	public Thing dequeue(){
		if (isEmpty())
			return null;

		Thing item = q[head];
		q[head] = null;
		head++;

		if (head == q.length)
			head = 0;
		n--;

		return item;
	}

	public boolean isEmpty(){
		return n == 0;
	}

	public int size(){
		return n;
	}

	public void resize(int newSize){
		Thing[] newQ = (Thing[]) new Object[newSize];

		for (int i = 0; i < n; i++){
			newQ[i] = q[tail++];
			if (tail == q.length)
				tail = 0;
		}

		tail = n;
		head = 0;
		q = newQ;
	}
}