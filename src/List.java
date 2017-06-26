public class List{
	protected class Node{
		public Object obj;
		public Node next;
		public Node back;
	}
	
	protected Node firstNode;
	protected Node lastNode;
	
	public Object pop(){				// retira o primeiro elemento da lista
		if (firstNode.next == null){
			Object tempObj = firstNode.obj;
			firstNode.obj = null;
			return tempObj;
		}
		
		Node tempNode = firstNode;
		firstNode = firstNode.next;
		firstNode.back = null;
		return tempNode.obj;
	}
	
	public void push(Object obj){		// insere no comeco da lista - LIFO
		if(firstNode.obj == null){
			firstNode.obj = obj;
		}else{
			Node tempNode = new Node();
			firstNode.back = tempNode;
			tempNode.next = this.firstNode;
			tempNode.back = null;
			tempNode.obj = obj;
			this.firstNode = tempNode;
		}
	}
	
	public void insert(Object obj){		// insere no final da lista - FIFO
		if(lastNode.obj == null){
			lastNode.obj = obj;
		}else{
			Node tempNode = new Node();
			lastNode.next = tempNode;
			tempNode.next = null;
			tempNode.back = lastNode;
			tempNode.obj = obj;
			this.lastNode = tempNode;
		}
	}
	
	public Object viewFirstElement(){
		return firstNode.obj;
	}
	
	public Object viewLastElement(){
		return lastNode.obj;
	}
	
	public int len(){
		int i;
		Node tempNode = firstNode;
		
		for(i = 0; tempNode != null; i++)
			tempNode = tempNode.next;
		
		return i;
	}
	
	public List(){
		this.firstNode = new Node();
		this.lastNode = firstNode;
		firstNode.obj = null;
		firstNode.next = null;
		firstNode.back = null;
	}
}