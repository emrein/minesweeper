/* x-y axis are inverted in some situations 
1234
5678 
 * */

import java.util.Scanner;
import java.util.Random;

public class Main {

	public static boolean gameFinish;
	
	public static int[] space(){
		Scanner reader = new Scanner(System.in);
		int[] infoHolder= new int[3];
		
		int xAxis = reader.nextInt();
		int yAxis = reader.nextInt();
		int bombCount = reader.nextInt();
		
		if(xAxis*yAxis>bombCount){
			
		}
		else{
			//The map you want can't be created because there are not enough spaces for bombs.
			//Enter the values again.
			space();
		}
		
		infoHolder[0]= xAxis;
		infoHolder[1]= yAxis;
		infoHolder[2]= bombCount;
		
		return infoHolder;
		
	}
	
	public static int nearBombCount(Unit[][] units, int y, int x, int yAxis, int xAxis){
		// it is not 9 so don't worry about that
		int count = 0;
		
		if(x==0 && y==0)//left top
		{
			count += units[y+1][x].type == 9 ? 1:0;//alt
			count += units[y+1][x+1].type == 9 ? 1:0;//sað alt
			count += units[y][x+1].type == 9 ? 1:0;//sað
		}
		else if(y==0 && x!=0 && x!=xAxis-1)//top
		{
			count += units[y+1][x+1].type == 9 ? 1:0;//sað alt
			count += units[y+1][x-1].type == 9 ? 1:0;//sol alt
			count += units[y][x-1].type == 9 ? 1:0;//sol
			count += units[y][x+1].type == 9 ? 1:0;//sað
			count += units[y+1][x].type == 9 ? 1:0;//alt
		}
		else if(y==0 && x==xAxis-1)//right top
		{
			count += units[y+1][x].type == 9 ? 1:0;//alt
			count += units[y+1][x-1].type == 9 ? 1:0;//sol alt
			count += units[y][x-1].type == 9 ? 1:0;//sol
		
		}
		else if(y!=0 && y!=yAxis-1 && x==xAxis-1)//right
		{
			count += units[y-1][x-1].type == 9 ? 1:0;//sol üst 
			count += units[y+1][x-1].type == 9 ? 1:0;//sol alt
			count += units[y][x-1].type == 9 ? 1:0;//sol
			count += units[y-1][x].type == 9 ? 1:0;//üst
			count += units[y+1][x].type == 9 ? 1:0;//alt
		}
		else if(y==yAxis-1 && x==xAxis-1)//bottom right
		{
			count += units[y-1][x-1].type == 9 ? 1:0;//sol üst 
			count += units[y][x-1].type == 9 ? 1:0;//sol
			count += units[y-1][x].type == 9 ? 1:0;//üst
		}
		else if(y==yAxis-1 && x!=xAxis-1 && x!=0)//bottom
		{
			count += units[y-1][x-1].type == 9 ? 1:0;//sol üst 
			count += units[y-1][x+1].type == 9 ? 1:0;//sað üst
			count += units[y][x-1].type == 9 ? 1:0;//sol
			count += units[y][x+1].type == 9 ? 1:0;//sað
			count += units[y-1][x].type == 9 ? 1:0;//üst
		}
		else if(y==yAxis-1 && x==0)//bottom left
		{
			count += units[y-1][x+1].type == 9 ? 1:0;//sað üst
			count += units[y-1][x].type == 9 ? 1:0;//üst
			count += units[y][x+1].type == 9 ? 1:0;//sað
		}
		else if(y!=yAxis-1 && y!=0 && x==0)//left
		{
			count += units[y-1][x+1].type == 9 ? 1:0;//sað üst
			count += units[y-1][x].type == 9 ? 1:0;//üst
			count += units[y][x+1].type == 9 ? 1:0;//sað
			count += units[y+1][x+1].type == 9 ? 1:0;//sað alt
			count += units[y+1][x].type == 9 ? 1:0;//alt
		}
		else{
			count += units[y-1][x-1].type == 9 ? 1:0;//sol üst 
			count += units[y+1][x+1].type == 9 ? 1:0;//sað alt
			count += units[y-1][x+1].type == 9 ? 1:0;//sað üst
			count += units[y+1][x-1].type == 9 ? 1:0;//sol alt
			count += units[y][x-1].type == 9 ? 1:0;//sol
			count += units[y][x+1].type == 9 ? 1:0;//sað
			count += units[y-1][x].type == 9 ? 1:0;//üst
			count += units[y+1][x].type == 9 ? 1:0;//alt
		}
		
		return count;
	}
	
	public static Unit[][] zeroReveal(Unit[][] units, int y, int x, int yAxis, int xAxis){
		
		units[y][x].surroundings = true;
		
		if(x==0 && y==0)//left top
		{
			units[y+1][x].revealed = true;;//alt
			units[y+1][x+1].revealed = true;;//sað alt
			units[y][x+1].revealed = true;;//sað
		}
		else if(y==0 && x!=0 && x!=xAxis-1)//top
		{
			units[y+1][x+1].revealed = true;;//sað alt
			units[y+1][x-1].revealed = true;;//sol alt
			units[y][x-1].revealed = true;;//sol
			units[y][x+1].revealed = true;;//sað
			units[y+1][x].revealed = true;;//alt
		}
		else if(y==0 && x==xAxis-1)//right top
		{
			units[y+1][x].revealed = true;;//alt
			units[y+1][x-1].revealed = true;;//sol alt
			units[y][x-1].revealed = true;;//sol
		
		}
		else if(y!=0 && y!=yAxis-1 && x==xAxis-1)//right
		{
			units[y-1][x-1].revealed = true;;//sol üst 
			units[y+1][x-1].revealed = true;;//sol alt
			units[y][x-1].revealed = true;;//sol
			units[y-1][x].revealed = true;;//üst
			units[y+1][x].revealed = true;;//alt
		}
		else if(y==yAxis-1 && x==xAxis-1)//bottom right
		{
			units[y-1][x-1].revealed = true;;//sol üst 
			units[y][x-1].revealed = true;;//sol
			units[y-1][x].revealed = true;;//üst
		}
		else if(y==yAxis-1 && x!=xAxis-1 && x!=0)//bottom
		{
			units[y-1][x-1].revealed = true;;//sol üst 
			units[y-1][x+1].revealed = true;;//sað üst
			units[y][x-1].revealed = true;;//sol
			units[y][x+1].revealed = true;;//sað
			units[y-1][x].revealed = true;;//üst
		}
		else if(y==yAxis-1 && x==0)//bottom left
		{
			units[y-1][x+1].revealed = true;;//sað üst
			units[y-1][x].revealed = true;;//üst
			units[y][x+1].revealed = true;;//sað
		}
		else if(y!=yAxis-1 && y!=0 && x==0)//left
		{
			units[y-1][x+1].revealed = true;;//sað üst
			units[y-1][x].revealed = true;;//üst
			units[y][x+1].revealed = true;;//sað
			units[y+1][x+1].revealed = true;;//sað alt
			units[y+1][x].revealed = true;;//alt
		}
		else{
			units[y-1][x-1].revealed = true;;//sol üst 
			units[y+1][x+1].revealed = true;;//sað alt
			units[y-1][x+1].revealed = true;;//sað üst
			units[y+1][x-1].revealed = true;;//sol alt
			units[y][x-1].revealed = true;;//sol
			units[y][x+1].revealed = true;;//sað
			units[y-1][x].revealed = true;;//üst
			units[y+1][x].revealed = true;;//alt
		}
		
		
		return units;
	}
	
	public static void gameOver(Unit[][] units){
		//GAME OVER
		System.out.println("GAME OVER");
		/*for (int i = 0; i< yAxis; i++)
		{
			for (int s = 0; s < xAxis; s++)
			{
				if(units[i][s].type ==0 && units[i][s].revealed == true && units[i][s].surroundings != true)
				{
					zeroSurroundingDone = false;
					units = zeroReveal(units, i ,s, yAxis, xAxis);
				}
			}
		}*/
		
		gameFinish = true;
	}
	
	public static void gameWin(Unit[][] units){
		//GAME WIN
		System.out.println("YOU WIN");
		gameFinish = true;
	}
	
	public static Unit[][] reveal(Unit[][] units, int y, int x, int yAxis, int xAxis, int whichClick,int bombCount){
		// it is not 9 so don't worry about that
		boolean zeroSurroundingDone;
		int revealedCount = 0;
		
		if(whichClick==1)
		{
			units[y][x].flaged = false;
			if( 0 < units[y][x].type &&units[y][x].type < 9)
			{
				units[y][x].revealed=true;
			}
			else if( units[y][x].type == 9)
			{
				gameOver(units);
			}
			else if(units[y][x].type == 0)
			{
				units[y][x].revealed=true;
				zeroSurroundingDone = false;
				while(zeroSurroundingDone == false)
				{
					zeroSurroundingDone=true;
					for (int i = 0; i< yAxis; i++)
					{
						for (int s = 0; s < xAxis; s++)
						{
							if(units[i][s].type ==0 && units[i][s].revealed == true && units[i][s].surroundings != true)
							{
								zeroSurroundingDone = false;
								units = zeroReveal(units, i ,s, yAxis, xAxis);
							}
						}
					}
				}
				
			}
		}
		else if(whichClick==2 && units[y][x].revealed != true)
		{
			units[y][x].flaged = true;
		}
		
		for (int i = 0; i< yAxis; i++)
		{
			for (int s = 0; s < xAxis; s++)
			{
				if(units[i][s].revealed == true)
				{
					revealedCount++;
				}
			}
		}
		if(revealedCount == yAxis*xAxis-bombCount)
			gameWin(units);
		
		return units;
	}
	
	public static boolean emptyPlace(int place, int[] places){
		boolean correction= true;
		for(int i = 0; i < places.length; i ++)
		{
			if(places[i] == place){
				correction = false;
			}
		}
		return correction;
	}
	
	public static void main(String[] args) {

		Scanner reader = new Scanner(System.in);
		Random rand = new Random();

		//0 == normal, 1 == custom
		int playSNum = reader.nextInt();
		//classic == true, custom == false
		boolean playStyle = playSNum == 0 ? true: false;
		
		int xAxis = 0;
		int yAxis = 0;
		int bombCount = 0;
		
		int[] infoHolder= new int[3];
		
		if(playStyle == true) //classic
		{
			//1 beginner 2 intermediate 3 expert
			int levelSelection = reader.nextInt();
			
			if(levelSelection==1)//Beginner
			{
				xAxis = 8;
				yAxis = 8;
				bombCount= 10;
			}
			
			if(levelSelection==2)//Intermediate
			{
				xAxis = 16;
				yAxis = 16;
				bombCount= 40;
			}
			
			if(levelSelection==3)//Expert
			{
				xAxis = 30;
				yAxis = 16;
				bombCount= 99;
			}
		}
		else if(playStyle == false) //custom
		{
			//x-axis, y-axis, bomb count
			infoHolder= space();
			xAxis = infoHolder[0];
			yAxis = infoHolder[1];
			bombCount = infoHolder[2];
		}
		
		
		Unit[][] units= new Unit[yAxis][xAxis];// y satýr x sütun
		
		for (int x = 0; x < xAxis; x++)
			for (int y = 0; y < yAxis; y++)
				units[y][x] = new Unit();
				
				
		
		int randomPlace;
		int[] bombPlaces = new int[bombCount];
		for(int i= 0; i < bombCount; i++)
		{
			randomPlace = rand.nextInt(xAxis*yAxis-1) + 0;
			if(emptyPlace(randomPlace,bombPlaces) == true)
			{
				bombPlaces[i] = randomPlace;
			}
			else{
				i--;
			}
		}
		
		for(int i = 0; i < bombCount; i++)
		{
			units[bombPlaces[i]/xAxis][bombPlaces[i]%xAxis].type = 9;
		}
		
		

		for (int y = 0; y < yAxis; y++)
		{
			System.out.println();
			for (int x = 0; x < xAxis; x++)
				units[y][x].type = units[y][x].type == 9 ? 9: nearBombCount(units,y,x,yAxis,xAxis);
		}
		
		//////////////////////////////////////////////
		for (int y = 0; y < yAxis; y++)
		{
			System.out.println();
			for (int x = 0; x < xAxis; x++)
				System.out.print(units[y][x].type);
		}
		//////////////////////////////////////////////
		
		//PLAY STARTS
		int clickX;
		int clickY;
		int whichClick;
		while(gameFinish != true){
			
			//////////////////////////////////////////////
			for (int y = 0; y < yAxis; y++)
			{
			System.out.println();
			for (int x = 0; x < xAxis; x++)
				if(units[y][x].revealed == true)
					System.out.print(units[y][x].type);
				else if(units[y][x].flaged == true)
					System.out.print("F");
				else
					System.out.print("-");
			}
			//////////////////////////////////////////////
			
			clickX= reader.nextInt();
			clickY= reader.nextInt();
			whichClick = reader.nextInt(); //1 = left 2 = right
			units = reveal(units,clickY,clickX,yAxis,xAxis,whichClick,bombCount);
		}
	}

}
