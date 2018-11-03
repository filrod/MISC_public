package javaAlgorithms;

import java.util.Arrays;

public class TheStack {
	
	private String[] stackArray;
	private int       stackSize;
	private int             top = -1;
	

	public TheStack(int size) {
		// TODO Auto-generated constructor stub
		
		stackSize = size;
		stackArray = new String[size];
		Arrays.fill(stackArray, "-1");
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TheStack stack = new TheStack(10);
		stack.push("5");
		stack.push("4");
		stack.push("7");
		stack.push("2");
		stack.push("10");
		stack.push("50");
		stack.push("25");

	}
	
	public void push(String input) {
		
		if (top+1<stackSize) {
			
			top++;
			stackArray[top] = input;
			
		} else {
			System.out.println("Stack is full");
		}
		
		displayTheStack();
		
	}
	
	public String pop() {
		if (top>=0) {
			displayTheStack();
			stackArray[top] = "-1";
			return stackArray[top--];
		}else {
			displayTheStack();
			System.out.println("Stack is empty");
			return "-1";
		} 
	}
	
	public String viewTop() {
		displayTheStack();
		return stackArray[top];
	}

	private void displayTheStack() {
		for (int i=0; i<this.stackSize; i++)
			System.out.print(this.stackArray[i]+"|||");
		
		System.out.println();
	}
	

}
