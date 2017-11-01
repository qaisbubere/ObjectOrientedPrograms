package com.bridgelabz.util;
import java.util.Scanner;



/*  Class Node  */

class Node

{
	protected int data;
    protected Node link;

 
    /*  Constructor  */

    public Node()

    {

        link = null;

        data = 0;

    }    

    /*  Constructor  */

    public Node(int d,Node n)

    {
        data = d;
        link = n;
    }    

    /*  Function to set link to next Node  */

    public void setLink(Node n)

    {
        link = n;
    }    

    /*  Function to set data to current Node  */

    public void setData(int d)

    {
        data = d;
    }    

    /*  Function to get link to next node  */

    public Node getLink()

    {
        return link;
    }    

    /*  Function to get data from current Node  */

    public int getData()

    {
        return data;
    }

}

class linkedList

{

    protected Node start;
    protected Node end ;
    public int size ;

 

    /*  Constructor  */

    public linkedList()

    {

        start = null;
        end = null;
        size = 0;

    }

    /*  Function to check if list is empty  */

    public boolean isEmpty()

    {

        return start == null;

    }

    /*  Function to get size of list  */

    public int getSize()

    {

        return size;

    }    
    
    public void insertAtStart(int val)

    {

        Node nptr = new Node(val, null);    

        size++ ;    
        if(start == null) 
        {
            start = nptr;
            end = start;
        }

        else 
        {
            nptr.setLink(start);
            start = nptr;
        }

    }
    
    public void display()

    {
        System.out.print("\nSingly Linked List = ");
        if (size == 0) 
        {
            System.out.print("empty\n");
            return;
        }    

        if (start.getLink() == null) 
        {
            System.out.println(start.getData() );
            return;
        }

        Node ptr = start;
        System.out.print(start.getData()+ "->");
        ptr = start.getLink();

        while (ptr.getLink() != null)
        {
            System.out.print(ptr.getData()+ "->");
            ptr = ptr.getLink();
        }

        System.out.print(ptr.getData()+ "\n");
    }
}
