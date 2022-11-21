package droid;

import mazePD.*;
import mazePD.Maze.*;

public class R2D2 implements DroidInterface {

	public String name;
	private stack previousPath;
	private stack backTrack;
	private int depth;
	private int depthGauge;
	
	@SuppressWarnings("rawtypes")
	public R2D2(String n)
	{
		previousPath = new stack();
		backTrack = new stack();
		setName(n);
	}
	
	public void setName(String n)
	{
		name = n;
	}
		
	public String getName()
	{
		return this.name;
	}
	
	public void mazeRun(Maze maze, R2D2 droid)
	{
		depth = maze.getMazeDepth();
		depthGauge = depth;
		maze.enterMaze(droid);
		previousPath.push(maze.getCurrentCoordinates(droid));
		backTrack.push(maze.getCurrentCoordinates(droid));
		
		while(depthGauge != 0)
		{
			decideTurn(maze, droid);
		}
	}
	
	//3=left, 0=forward, 1=right, 2=back
	//D00, D90, D180, D270, UP, DN
	//EMPTY,BLOCK,PORTAL_DN,PORTAL_UP,COIN,END,NA
	private void decideTurn(Maze maze, R2D2 droid)
	{
		Boolean flag = false;
		Content[] list = maze.scanAdjLoc(droid);
		int leftMove;
		int rightMove;
		int forwardMove;
		int backwardMove;
		
		Coordinates currentCoord = maze.getCurrentCoordinates(droid);
		Coordinates checker = (Coordinates) previousPath.peek();
		//Coordinates backer = (Coordinates) backTrack.peek();
		
		leftMove = currentCoord.getX() - 1;
		rightMove = currentCoord.getX() + 1;
		forwardMove = currentCoord.getY() - 1;
		backwardMove = currentCoord.getY() + 1;

		if(list[0] == Content.END || list[1] == Content.END || list[2] == Content.END || list[3] == Content.END)
		{
			if(list[0] == Content.END)
			{
				flag = true;
				moveForward(maze,droid);
				previousPath.push(maze.getCurrentCoordinates(droid));
				depthGauge--;
			}
			else if(list[1] == Content.END)
			{
				flag = true;
				moveRight(maze,droid);
				previousPath.push(maze.getCurrentCoordinates(droid));
				depthGauge--;
			}
			else if(list[2] == Content.END)
			{
				flag = true;
				moveBackward(maze,droid);
				previousPath.push(maze.getCurrentCoordinates(droid));
				depthGauge--;
			}
			else if(list[3] == Content.END)
			{
				flag = true;
				moveLeft(maze,droid);
				previousPath.push(maze.getCurrentCoordinates(droid));
				depthGauge--;
			}
		}
		else if(list[0] == Content.PORTAL_DN || list[1] == Content.PORTAL_DN || list[2] == Content.PORTAL_DN || list[3] == Content.PORTAL_DN)
		{
			if(list[0] == Content.PORTAL_DN)
			{
				flag = true;
				usePortalForward(maze,droid);
			}
			else if(list[1] == Content.PORTAL_DN)
			{
				flag = true;
				usePortalRight(maze,droid);
			}
			else if(list[2] == Content.PORTAL_DN)
			{
				flag = true;
				usePortalBackward(maze,droid);
			}
			else if(list[3] == Content.PORTAL_DN)
			{
				flag = true;
				usePortalLeft(maze,droid);
			}
		}
		else if((list[3] == Content.EMPTY || list[3] == Content.PORTAL_UP) && checkLeft(maze,droid)) //move left
		{
			//move left and add to stack
			flag = true;
			moveLeft(maze,droid);
		}
		else if((list[0] == Content.EMPTY || list[0] == Content.PORTAL_UP) && checkForward(maze,droid))//move forward
		{
			//move forward and add to stack
			flag = true;
			moveForward(maze,droid);
		}
		else if((list[1] == Content.EMPTY || list[1] == Content.PORTAL_UP) && checkRight(maze,droid)) //move right
		{
			//move right and add to stack
			flag = true;
			moveRight(maze,droid);
		}
		else if((list[2] == Content.EMPTY || list[2] == Content.PORTAL_UP) && checkBack(maze,droid)) //moving backwards
		{
			//move backwards and add to stack
			flag = true;
			moveBackward(maze,droid);
		}
		
		if(!flag)
		{
			if((list[3] == Content.EMPTY || list[3] == Content.PORTAL_UP)  && checker.getX() == leftMove)
			{
				backTrackLeft(maze,droid);
			}
			else if((list[0] == Content.EMPTY || list[0] == Content.PORTAL_UP) && checker.getY() == forwardMove)
			{
				backTrackForward(maze,droid);
			}
			else if ((list[1] == Content.EMPTY || list[1] == Content.PORTAL_UP) && checker.getX() == rightMove)
			{
				backTrackRight(maze,droid);
			}
			else if((list[2] == Content.EMPTY || list[2] == Content.PORTAL_UP) && checker.getY() == backwardMove)
			{
				backTrackBackward(maze,droid);
			}
		}
	} 
	
	public Object[] printTurns()
	{
		Object[] turn = new Object[previousPath.getSize()];
		
		for(int i = previousPath.getSize() - 1; i >= 0; i--)
		{
			turn[i] = previousPath.pop();
		}
		
		return turn;
	}
	
	private void backTrackRight(Maze maze, R2D2 droid)
	{
		backTrack.push(maze.getCurrentCoordinates(droid));
		maze.move(droid, Direction.D90);
		Object removal = previousPath.pop();
		System.out.println("Right");
	}
	
	private void backTrackLeft(Maze maze, R2D2 droid)
	{
		backTrack.push(maze.getCurrentCoordinates(droid));
		maze.move(droid, Direction.D270);
		Object removal = previousPath.pop();
		System.out.println("Left");
	}
	
	private void backTrackForward(Maze maze, R2D2 droid)
	{
		backTrack.push(maze.getCurrentCoordinates(droid));
		maze.move(droid, Direction.D00);
		Object removal = previousPath.pop();
		System.out.println("Forward");
	}
	
	private void backTrackBackward(Maze maze, R2D2 droid)
	{
		backTrack.push(maze.getCurrentCoordinates(droid));
		maze.move(droid, Direction.D180);
		Object removal = previousPath.pop();
		System.out.println("Backward");
	}
	
	private void moveRight(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D90);
		System.out.println("Right");
	}
	
	private void moveLeft(Maze maze, R2D2 droid)
	{
		Coordinates move =  maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D270);
		System.out.println("Left");
	}
	
	private void moveForward(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D00);
		System.out.println("Forward");
	}
	
	private void moveBackward(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D180);
		System.out.println("Backward");
	}
	
	private void usePortalLeft(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D270);
		previousPath.push(maze.getCurrentCoordinates(droid));
		maze.usePortal(droid, Direction.DN);
		depth+=1;
		depthGauge--;
	}
	
	private void usePortalRight(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D90);
		previousPath.push(maze.getCurrentCoordinates(droid));
		maze.usePortal(droid, Direction.DN);
		depth+=1;
		depthGauge--;
	}
	
	private void usePortalForward(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D00);
		previousPath.push(maze.getCurrentCoordinates(droid));
		maze.usePortal(droid, Direction.DN);
		depth+=1;
		depthGauge--;
	}
	
	private void usePortalBackward(Maze maze, R2D2 droid)
	{
		Coordinates move = maze.getCurrentCoordinates(droid);
		previousPath.push(move);
		maze.move(droid, Direction.D180);
		previousPath.push(maze.getCurrentCoordinates(droid));
		maze.usePortal(droid, Direction.DN);
		depth+=1;
		depthGauge--;
	}
	
	private Boolean checkLeft(Maze maze, R2D2 droid)
	{
		Boolean path = true;
		Coordinates setter = maze.getCurrentCoordinates(droid);
		Coordinates current = new Coordinates(setter.getX(),setter.getY(),setter.getZ());	
		
		current.setX((current.getX()-1));
		
		int size1 = previousPath.getSize();
		int size2 = backTrack.getSize();
		
		Coordinates[] a = new Coordinates[size1];
		Coordinates[] b = new Coordinates[size2];
		
		//Check first stack
		for(int i = 0; i < size1; i++)
		{
			a[i] = (Coordinates) previousPath.pop();
		}
		
		for(int i = 0; i < a.length; i++)
		{
			if(current.equals(a[i]))
			{
				path = false;
			}
		}
		
		for(int i = a.length-1; i >= 0; i--)
		{
			previousPath.push(a[i]);
		}
		
		
		//Check second stack
		for(int i = 0; i < size2; i++)
		{
			b[i] = (Coordinates) backTrack.pop();
		}
		
		for(int i = 0; i < b.length; i++)
		{
			if(current.equals(b[i]))
			{
				path = false;
			}
		}
		
		for(int i = b.length-1; i >= 0; i--)
		{
			backTrack.push(b[i]);
		}
		
		
		return path;
	}
	
	private Boolean checkRight(Maze maze, R2D2 droid)
	{
		Boolean path = true;
		Coordinates setter = maze.getCurrentCoordinates(droid);
		Coordinates current = new Coordinates(setter.getX(),setter.getY(),setter.getZ());		
		
		current.setX((current.getX()+1));
		
		int size1 = previousPath.getSize();
		int size2 = backTrack.getSize();
		
		Coordinates[] a = new Coordinates[size1];
		Coordinates[] b = new Coordinates[size2];
		
		//Check first stack
		for(int i = 0; i < size1; i++)
		{
			a[i] = (Coordinates) previousPath.pop();
		}
		
		for(int i = 0; i < a.length; i++)
		{
			if(current.equals(a[i]))
			{
				path = false;
			}
		}
		
		for(int i = a.length-1; i >= 0; i--)
		{
			previousPath.push(a[i]);
		}
		
		
		//Check second stack
		for(int i = 0; i < size2; i++)
		{
			b[i] = (Coordinates) backTrack.pop();
		}
		
		for(int i = 0; i < b.length; i++)
		{
			if(current.equals(b[i]))
			{
				path = false;
			}
		}
		
		for(int i = b.length-1; i >= 0; i--)
		{
			backTrack.push(b[i]);
		}
		
		
		return path;
	}

	private Boolean checkForward(Maze maze, R2D2 droid)
	{
		Boolean path = true;
		Coordinates setter = maze.getCurrentCoordinates(droid);
		Coordinates current = new Coordinates(setter.getX(),setter.getY(),setter.getZ());
		
		current.setY((current.getY()-1));
		
		int size1 = previousPath.getSize();
		int size2 = backTrack.getSize();
		
		Coordinates[] a = new Coordinates[size1];
		Coordinates[] b = new Coordinates[size2];
		
		//Check first stack
		for(int i = 0; i < size1; i++)
		{
			a[i] = (Coordinates) previousPath.pop();
		}
		
		for(int i = 0; i < a.length; i++)
		{
			if(current.equals(a[i]))
			{
				path = false;
			}
		}
		
		for(int i = a.length-1; i >= 0; i--)
		{
			previousPath.push(a[i]);
		}
		
		
		//Check second stack
		for(int i = 0; i < size2; i++)
		{
			b[i] = (Coordinates) backTrack.pop();
		}
		
		for(int i = 0; i < b.length; i++)
		{
			if(current.equals(b[i]))
			{
				path = false;
			}
		}
		
		for(int i = b.length-1; i >= 0; i--)
		{
			backTrack.push(b[i]);
		}
		
		
		return path;
	}

	private Boolean checkBack(Maze maze, R2D2 droid)
	{
		Boolean path = true;
		Coordinates setter = maze.getCurrentCoordinates(droid);
		Coordinates current = new Coordinates(setter.getX(),setter.getY(),setter.getZ());	
		
		current.setY((current.getY()+1));
		
		int size1 = previousPath.getSize();
		int size2 = backTrack.getSize();
		
		Coordinates[] a = new Coordinates[size1];
		Coordinates[] b = new Coordinates[size2];
		
		//Check first stack
		for(int i = 0; i < size1; i++)
		{
			a[i] = (Coordinates) previousPath.pop();
		}
		
		for(int i = 0; i < a.length; i++)
		{
			if(current.equals(a[i]))
			{
				path = false;
			}
		}
		
		for(int i = a.length-1; i >= 0; i--)
		{
			previousPath.push(a[i]);
		}
		
		
		//Check second stack
		for(int i = 0; i < size2; i++)
		{
			b[i] = (Coordinates) backTrack.pop();
		}
		
		for(int i = 0; i < b.length; i++)
		{
			if(current.equals(b[i]))
			{
				path = false;
			}
		}
		
		for(int i = b.length-1; i >= 0; i--)
		{
			backTrack.push(b[i]);
		}
		
		
		return path;
	}
}








