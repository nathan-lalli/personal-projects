package main;

import java.util.Scanner;

import droid.*;
import mazePD.*;
import mazePD.Maze.*;

public class Test {

	public static void main(String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		System.out.print("How many levels do you want to make the maze? ");
		int levels = Integer.parseInt(in.nextLine());
		
		R2D2 droid = new R2D2("R2D2");
		Maze maze = new Maze(10,levels,MazeMode.NORMAL);
		
		droid.mazeRun(maze, droid);
		
		for(int i = 0; i < levels; i++)
		{
			System.out.println("Maze "+(i+1)+": ");
			String[] printMaze = maze.toStringLevel(i);
			for(int j = 0; j < printMaze.length; j++)
			{
				System.out.println(printMaze[j]);
			}
		}
		
		Object[] droidPath = droid.printTurns();
		
		for(int i = 0; i < droidPath.length; i++)
		{
			System.out.println(droidPath[i]);
		}
	}
}
