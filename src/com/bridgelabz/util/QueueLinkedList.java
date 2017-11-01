package com.bridgelabz.util;


	class node<T>{
		private node<T> link;
		public T data;
		
		public node(T mData, node<T> node){
		data = mData;
		link = node;
		}
		
		/*
		 * method to get link of a particular node
		 */
		public node<T> getLink(){
			return link;
		}
		
		/*
		 * method to set link in a node
		 */
		public void setLink(node<T> rear){
			this.link=rear;
		}
		
		/*
		 * method to get data of a node
		 */
		public T getData(){
			return data;
		}
		
		/*
		 * method to set data of a node
		 */
		public void setData(T data){
			this.data = data;
		}			
	}
	
public class QueueLinkedList<T>{
		public node<T> front;
		public node<T> rear;
		public int sizeOfQueue;
		
		/*
		 * method to add elements in a queue linked list
		 */
		public void enQueue(T element){
			node<T> node = new node<T>(element,null);
			if(rear == null){
				rear = node;
				front = node;
			}
			else{
				rear.setLink(node);
				rear = rear.getLink();
			}
			sizeOfQueue++;				
		}		
		
		
		/*
		 * method to delete elements from queue linked list
		 */
		public T deQueue(){
			node<T> node = null;
			T element = null;
			
			if(isEmpty()){
				sizeOfQueue=0;
				System.out.println("Queue is empty");
			}		
				node = front;
				front = node.getLink();
				element = node.getData();
				return element;
			}
		
		
		/*
		 * this method return whether queue is empty or not
		 */
		public boolean isEmpty(){
			return front==null ;
		}
		
		
		/*
		 * this method returns size of queue
		 */
		public int size(){
			return sizeOfQueue;
		}
		
		/*
		 * this method returns the element which is on the top
		 */
		public T peek(){
			if(isEmpty())
				System.out.println("queue is empty");
			
			return front.getData();
		}
	}



