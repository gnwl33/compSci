/* THIS  CODE  WAS MY OWN WORK , IT WAS  WRITTEN  WITHOUT  CONSULTING  ANY
SOURCES  OUTSIDE  OF  THOSE  APPROVED  BY THE  INSTRUCTOR. _Gene Lee_ */

/**
 * Starter code for the Maze path finder problem.
 */

import java.io.*;
import java.util.Scanner;
import java.util.ArrayDeque;

/*
 * Recursive class to represent a position in a path
 */
class Position{
	public int i;     //row
	public int j;     //column
	public char val;  //1, 0, or 'X'
	
	// reference to the previous position (parent) that leads to this position on a path
	Position parent;
	
	Position(int x, int y, char v){
		i=x; j = y; val=v;
	}
	
	Position(int x, int y, char v, Position p){
		i=x; j = y; val=v;
		parent=p;
	}
	
}

public class PathFinder {

	public static ArrayDeque<Position> srchList;	// Declare an ArrayDeque for the search list
	public static int n; // n will store the maze length

	public static void main(String[] args) throws IOException {
		if(args.length<1){
			System.err.println("***Usage: java PathFinder maze_file");
			System.exit(-1);
		}
		
		char [][] maze;
		maze = readMaze(args[0]);
		printMaze(maze);
		Position [] path = stackSearch(maze);
		System.out.println("stackSearch Solution:");
		printPath(path);
		printMaze(maze);
		
		char [][] maze2 = readMaze(args[0]);
		path = queueSearch(maze2);
		System.out.println("queueSearch Solution:");
		printPath(path);
		printMaze(maze2);
	}
	
	public static Position [] stackSearch(char [] [] maze){
		srchList = new ArrayDeque<Position>();	// Create the search list
		n = maze.length;	// Store the maze length in n
		
		char[][] mazeCopy = new char[n][n];	// Create a copy of the maze that can be marked up
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mazeCopy[i][j] = maze[i][j];

		srchList.push(new Position(0, 0, '0'));	// Push [0,0] to the stack

		while(!srchList.isEmpty()){
			Position now = srchList.pop();	// Pop the next position in the search list

			if (now.i == n - 1 && now.j == n - 1){	// If the position is the exit...
				int count = 0;	// Count will be used for the array size

				for (Position p = now; p != null; p = p.parent)	// Count how many positions are in the found path
					count++;

				Position[] path = new Position[count];	// Create the path array
				int k = count - 1;	// k is the array index

				for (Position p = now; p != null; p = p.parent){	// Add each position of the path to the array
					path[k] = p;
					k--;	// Since the linked list goes from the exit to the start, add to the end of the array
					maze[p.i][p.j] = 'X';	// Mark each position as X on the original maze
				}

				return path;
			}

			else{
				mazeCopy[now.i][now.j] = 'X';	//	Mark each visited position on the maze copy

				rowNeighborS(mazeCopy, now, 0, -1);	// Check neighbor to the left

				colNeighborS(mazeCopy, now, 0, -1);	// Check neighbor above
				
				rowNeighborS(mazeCopy, now, n - 1, 1);	// Check neighbor to the right

				colNeighborS(mazeCopy, now, n - 1, 1);	// Check neighbor below
			}
		}

			return null;	// When no path was found
	}
	
	public static Position [] queueSearch(char [] [] maze){
		srchList = new ArrayDeque<Position>();	// Create the search list
		n = maze.length;	// Store the maze length in n
		
		char[][] mazeCopy = new char[n][n];	// Create a copy of the maze that can be marked up
		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				mazeCopy[i][j] = maze[i][j];

		srchList.add(new Position(0, 0, '0'));	// Enqueue [0,0]

		while(!srchList.isEmpty()){
			Position now = srchList.remove();	// Dequeue next position in the search list

			if (now.i == n - 1 && now.j == n - 1){	// If the position is the exit...
				int count = 0;	// Count will be used for the array size

				for (Position p = now; p != null; p = p.parent)	// Count how many positions are in the found path
					count++;

				Position[] path = new Position[count];	// Create the path array
				int k = count - 1;	// k is the array index

				for (Position p = now; p != null; p = p.parent){	// Add each position of the path to the array
					path[k] = p;
					k--;	// Since the linked list goes from the exit to the start, add to the end of the array
					maze[p.i][p.j] = 'X';	// Mark each position as X on the maze
				}

				return path;
			}

			else{
				mazeCopy[now.i][now.j] = 'X';	//	Mark each visited position on the maze copy

				rowNeighborQ(mazeCopy, now, 0, -1);	// Check neighbor to the left

				colNeighborQ(mazeCopy, now, 0, -1);	// Check neighbor above
				
				rowNeighborQ(mazeCopy, now, n - 1, 1);	// Check neighbor to the right

				colNeighborQ(mazeCopy, now, n - 1, 1);	// Check neighbor below
			}
		}

			return null;	// When no path was found
	}

	public static void rowNeighborS (char[][] maze, Position now, int bound, int incr) {	// Check neighbors to the left or right for a stack
		if (now.j != bound)	// If the position's column is not 0 or n - 1...
			if (maze[now.i][now.j + incr] == '0')	// If the neighbor is a 0 (and has not been visited -- mazeCopy is being used here)...
				srchList.push(new Position(now.i, now.j + incr, '0', now));	// Push neighbor into the search list, with the parent being the current position
	}

	public static void rowNeighborQ (char[][] maze, Position now, int bound, int incr) {	// Check neighbors to the left or right for a queue
		if (now.j != bound)
			if (maze[now.i][now.j + incr] == '0')
				srchList.add(new Position(now.i, now.j + incr, '0', now));
	}


	public static void colNeighborS (char[][] maze, Position now, int bound, int incr) {	// Check neighbors above or below for a stack
		if (now.i != bound) // If the position's row is not 0 or n - 1...
			if (maze[now.i + incr][now.j] == '0')
				srchList.push(new Position(now.i + incr, now.j, '0', now));
	}

	public static void colNeighborQ (char[][] maze, Position now, int bound, int incr) {	// Check neighbors above or below for a queue
		if (now.i != bound)
			if (maze[now.i + incr][now.j] == '0')
				srchList.add(new Position(now.i + incr, now.j, '0', now));
	}
	
	public static void printPath(Position [] path){
		if (path == null)	// When no solution was found	
			System.out.println("No path found");

		else{
			System.out.println("Success!");
			System.out.print("Path: ([0][0], ");

			for (int k = 1; k < path.length - 1; k++)	// Print each position in the path (except for [0][0] and [n-1][n-1])
				System.out.print("[" + path[k].i + "][" + path[k].j + "], ");

			System.out.println("[" + (n - 1) + "][" + (n - 1) + "])");	// Print [n-1][n-1]
		}
	}
	
	/**
	 * Reads maze file in format:
	 * N  -- size of maze
	 * 0 1 0 1 0 1 -- space-separated 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public static char [][] readMaze(String filename) throws IOException{
		char [][] maze;
		Scanner scanner;
		try{
			scanner = new Scanner(new FileInputStream(filename));
		}
		catch(IOException ex){
			System.err.println("*** Invalid filename: " + filename);
			return null;
		}
		
		int N = scanner.nextInt();
		scanner.nextLine();
		maze = new char[N][N];
		int i=0;
		while(i < N && scanner.hasNext()){
			String line =  scanner.nextLine();
			String [] tokens = line.split("\\s+");
			int j = 0;
			for (; j< tokens.length; j++){
				maze[i][j] = tokens[j].charAt(0);
			}
			if(j!=N){
				System.err.println("*** Invalid line: " + i + " has wrong # columns: " + j);
				return null;
			}
			i++;
		}
		if(i!=N){
			System.err.println("*** Invalid file: has wrong number of rows: " + i);
			return null;
		}
		return maze;
	}
	
	public static void printMaze(char[][] maze){
		
		if(maze==null || maze[0] == null){
			System.err.println("*** Invalid maze array");
			return;
		}
		
		for(int i=0; i< maze.length; i++){
			for(int j = 0; j< maze[0].length; j++){
				System.out.print(maze[i][j] + " ");	
			}
			System.out.println();
		}
		
		System.out.println();
	}

}
