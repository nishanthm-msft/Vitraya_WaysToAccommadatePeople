package com.nishanth.vitraya;

import java.util.*;

//Used Backtracking algorithm to find the total number of ways to accommodate people with given constraints.
public class CountWays {
	
	static int count=0;
	static int m=0;
	static int n=0;
	static int x=0;
	
	//Checks if the seat at (i,j) is occupied
	static boolean canSit(int i, int j, boolean[][] occupied)
	{
	   return !occupied[i][j] ; 
	}
	
	//Modifies neighboring seats in the auditorium according to the social distancing constraints after current person has occupied a seat
	static void prohibitted(int i, int j, boolean[][] occupied)
	{
	   if ((i + 1) < m ) {
	      occupied[i + 1][j] = true;
	   }
	   if ((i - 1) >= 0) {
	      occupied[i - 1][j] = true;
	   }
	   if ((j + 1) < n ) {
		  occupied[i][j+1] = true;
	   }
	   if ((j - 1) >= 0) {
	      occupied[i][j-1] = true;
	   }
	}
	
	//Current person occupies an unoccupied seat in the auditorium
	static void sit(int i, int j, boolean[][] board, boolean[][] temp_occupied)
	{
	   for (int y = 0; y < m; y++) {
	      for (int z = 0; z < n; z++) {
	         temp_occupied[y][z] = board[y][z];
	      }
	   }
	   temp_occupied[i][j] = true;
	   prohibitted(i, j, temp_occupied);
	}
	
	//Function to count the number of ways to accommodate people in the auditorium with social distancing norms
	static void countWaysToSit(boolean[][] occupied, int sti, int stj, int p)
	{
	   if (p == 0) {
	      count++;
	   } else {
	      for (int i = sti; i < m; i++) {
	         for (int j = stj; j < n; j++) {
	            if (canSit(i, j, occupied)) {
	               boolean[][] temp_occupied = new boolean[m][n];
	               sit(i, j, occupied, temp_occupied);
	               countWaysToSit(temp_occupied, i, j, p-1);
	            }
	         }
	         stj = 0;
	      }
	   }
	}
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of rows m : ");
		m = sc.nextInt();
		
		System.out.print("Enter number of cols n : ");
		n = sc.nextInt();
		
		System.out.print("Enter number of people x : ");
		x = sc.nextInt();
		
		boolean[][] occupied = new boolean[m][n];
		countWaysToSit(occupied, 0, 0, x);
		
		System.out.println("Total ways to accommodate people with social distancing = " + count);
	}
	
}
