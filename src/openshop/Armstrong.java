package openshop;

import java.util.Scanner;

public class Armstrong {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scn = new Scanner(System.in);
        int n = scn.nextInt();
        int r ;
        int arm =0 ;
        while(n>0){
        	r=n%10;
        	arm = arm+(r*r*r);
        	n=n/10;
        }
System.out.println(arm);
	}

}
