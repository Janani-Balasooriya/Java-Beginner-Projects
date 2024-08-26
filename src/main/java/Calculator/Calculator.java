package Calculator;

import java.util.Scanner;

public class Calculator
	{

	public static void main(String[] args) 
		{
		// TODO Auto-generated method stub

		// result will be in float form 
		float res = 0;

		
		Scanner userInput = new Scanner(System.in);
		System.out.println("Enter the 1st number : ");
		float num1 = userInput.nextFloat();
		
		System.out.println("Enter the 2nd number :");
		float num2 = userInput.nextFloat();

		// print basic mathematical operators
		System.out.println("Enter the operation :");
		System.out.println("1. +");
		System.out.println("2. -");
		System.out.println("3. *");
		System.out.println("4. /");

		// using the scanner class to take input which will only be in int form
		int optr = userInput.nextInt();

		//using control statement to print the operator statements and also handle some errors statements 
		if(optr>4)
		{
			System.out.println("Error");
		}
		else
		{
		if(optr==1)
		{
			res=num1+num2;
		}
		else if(optr==2)
		{
			res=num1-num2;
		}
		else if(optr==3)
		{
			res=num1*num2;
		}
		else
		{
			res=num1/num2;
			
		}
		System.out.println("The result is " + res);
		}

		// close scanner to avoid data leak
		Scanner.close();
	}

}
