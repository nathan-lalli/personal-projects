package droid;

public class stack<E> {
		 
	int size = 0;
	
	    private class Node<E> {
	 
	        E data;
	        Node<E> node;
	    }
	    
	    Node<E> top;
	    
	    stack()
	    {
	        top = null;
	    }
	 
		public void push(E e)
	    {
	        Node<E> temp = new Node<E>();

	        temp.data = e;

	        temp.node = top;

	        top = temp;
	        
	        size++;
	    }

	    public boolean isEmpty()
	    {
	        return top == null;
	    }

	    public E peek()
	    {
	        if (!isEmpty()) {
	            return top.data;
	        }
	        else {
	            System.out.println("Stack is empty");
	            return null;
	        }
	    }

	    public E pop()
	    {
	        	E item = top.data;
	        	
	        	top = (top).node;
	        	
	        	size--;
	        	
	        	return item;
	    }

		public int getSize() {
			
			return size;
		}
}
