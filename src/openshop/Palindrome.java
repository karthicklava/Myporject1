package openshop;

import java.util.Scanner;

public class Palindrome {

	public static void main(String[] args) {
				// TODO Auto-generated method stub
             Scanner scn = new Scanner(System.in);
             int n = scn.nextInt();
             int temp = n;
             int r;
             int sum = 0;
             while(n>0){
            	 r=n%10;
            	 sum= (sum*10)+r;
            	 n=n/10;
            
             }
       	  System.out.println(sum);
       	  if(sum==temp){
       		  System.out.println("great to go");
       	  }
       	  else{
       		  System.out.println("Sorry oops");
       	  }
           
	}

}
