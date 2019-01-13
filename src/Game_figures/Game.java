package Game_figures;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import Algorithms.ShortestPathAlgo;
import Algorithms.createGameFromCsv;
import Coords.Map;
import Geom.Point3D;

public class Game 
{
	final int COLUMNS_NUM = 7 ;
	private final int GAME_TIME = 100 ;
	public ArrayList<Box> boxSet =new  ArrayList();
	public ArrayList<Ghost> ghostSet = new ArrayList();
	public ArrayList<Packman> pacmanSet = new ArrayList();
	public ArrayList<Fruit> fruitSet =new  ArrayList();

	Point3D playerPoint = new Point3D(0,0) ;
	private Player player  = new Player(0,playerPoint,2,1) ;
	private int pacmanEatenId = -1 ;

	private boolean gameEnd = false ;
	private int score ;
	private int ghostKills ;
	private int PlayerEatenFruit ;
	private int pacmanEatenFruit ;
	private int playerTouchedBox ;
	private int gameTime = GAME_TIME ;

	

	public int getGameTime() {
		return gameTime;
	}

	
	public Game()
	{

	}

	public Game(String filePath)   /*בדקתי ועובד טוב*/
	{
		 score = 0 ;
		 ghostKills = 0 ;
		 PlayerEatenFruit = 0 ;
		 pacmanEatenFruit = 0 ;
		 gameTime = 100 ;
		Map map = new Map() ;
		File game = new File(filePath) ;
		FileReader fr;
		Point3D pixels ;
		Point3D coordinates ;
		Point3D boxCoordinates ;
		String[] elements = new String[COLUMNS_NUM] ;
		int id ;
		double altitude ;
		double latitude ;
		double longtitude ;
		double speed ;
		double radius ;
		if(filePath.length() > 0)
		{
			try 
			{
				fr = new FileReader(game);
				BufferedReader br = new BufferedReader(fr);

				String line = br.readLine() ;
				while((line = br.readLine()) != null)
				{
					elements = line.split(",");
					id = Integer.parseInt(elements[1]) ;
					latitude = Double.parseDouble(elements[2]);
					longtitude = Double.parseDouble(elements[3]);		
					altitude = Double.parseDouble(elements[4]) ;
					speed = Double.parseDouble(elements[5]) ;
					coordinates = new Point3D(latitude , longtitude);
					pixels = new Point3D(map.coordsToPixels(coordinates)) ; /*המרת הנקודה מקורדינטות לפיקסלים*/


					if(elements[0].equals("P"))
					{
						radius = Double.parseDouble(elements[6]) ;
						System.out.println("pacman\t" +id +"\tX-acsis -->\t " +  pixels.ix()  + "\t   Y-acsis -->\t " +pixels.iy() + "\taltitude\t" + altitude+ "\tlongtitude\t"+longtitude );
						pacmanSet.add(new Packman(id, pixels.ix(), pixels.iy() , speed  )) ;
					}

					else if(elements[0].equals("F"))
					{
						System.out.println("fruit\t" +id +" \tX-acsis -->\t" + pixels.ix() + "\t   Y-acsis -->\t" +pixels.iy() + "\taltitude\t" + altitude +  "\tlongtitude\t"+longtitude);
						fruitSet.add(new Fruit(id, pixels.ix(), pixels.iy() , "Images//Batman_logo.png"));
					}

					else if(elements[0].equals("B"))
					{
						radius = Double.parseDouble(elements[6]) ;
						boxCoordinates = new Point3D(speed ,radius );
						boxCoordinates = map.coordsToPixels(boxCoordinates);
						boxSet.add(new Box(pixels , boxCoordinates)) ;
					}

					else if(elements[0].equals("G"))
					{
						radius = Double.parseDouble(elements[6]) ;
						ghostSet.add(new Ghost(id , speed , pixels)) ;
					}



				}	 
				br.close();

			} 	
			catch (IOException e) 
			{
				e.printStackTrace();
			}

		}
	}

	public Game(ArrayList<String> board_data)
	{
		Map map = new Map() ;
		FileReader fr;
		Point3D pixels ;
		Point3D coordinates ;
		Point3D boxCoordinates ;
		String[] elements = new String[COLUMNS_NUM] ;
		int id ;
		double altitude ;
		double latitude ;
		double longtitude ;
		double speed ;
		double radius ;
		int score  = 0;
		Player player ;
		if(board_data.size() > 0)
		{			
			String line = board_data.get(0) ;				
			elements = line.split(",");

			id = Integer.parseInt(elements[1]) ;
			latitude = Double.parseDouble(elements[2]);
			longtitude = Double.parseDouble(elements[3]);		
			altitude = Double.parseDouble(elements[4]) ;
			speed = Double.parseDouble(elements[5]) ;
			coordinates = new Point3D(latitude  , longtitude);
			pixels = new Point3D(0,0,0) ; /*המרת הנקודה מקורדינטות לפיקסלים*/
			radius = Double.parseDouble(elements[6]) ;

			player = new Player(id , pixels , speed , radius);
			System.out.println("in game : player was created in location ---> " + player.getPlayerLocation());


			for(int i = 1 ; i < board_data.size() ; i++)
			{
				line = board_data.get(i) ;				

				elements = line.split(",");
				id = Integer.parseInt(elements[1]) ;
				latitude = Double.parseDouble(elements[2]);
				longtitude = Double.parseDouble(elements[3]);		
				altitude = Double.parseDouble(elements[4]) ;
				speed = Double.parseDouble(elements[5]) ;
				coordinates = new Point3D(latitude , longtitude);
				pixels = new Point3D(map.coordsToPixels(coordinates)) ; /*המרת הנקודה מקורדינטות לפיקסלים*/


				if(elements[0].equals("P"))
				{
					radius = Double.parseDouble(elements[6]) ;
					pacmanSet.add(new Packman(id, pixels.ix(), pixels.iy() , speed  )) ;
				}

				else if(elements[0].equals("F"))
				{
					fruitSet.add(new Fruit(id, pixels.ix(), pixels.iy() , "Images//Batman_logo.png"));
				}

				else if(elements[0].equals("B"))
				{
					radius = Double.parseDouble(elements[6]) ;
					boxCoordinates = new Point3D(speed ,radius );
					boxCoordinates = map.coordsToPixels(boxCoordinates);
					boxSet.add(new Box(pixels , boxCoordinates)) ;
				}

				else if(elements[0].equals("G"))
				{
					radius = Double.parseDouble(elements[6]) ;
					ghostSet.add(new Ghost(id , speed , pixels)) ;
				}
			}	 
		}
	}

	public void setPlayer(Point3D initialPoint)
	{
		player.setPlayerLocation(initialPoint);
	}
	public int getScore() {
		return score;
	}

	public void setScore(int score) 
	{
		if((this.score += score) < 0)
		{
			this.score = 0 ;
		}
		else
			this.score += score;

	}

	public void removefFruits(Packman pacman)
	{
		while(pacman.getP_Path().getFruitsPath().size() > 0)
		{
			pacman.getP_Path().getFruitsPath().remove(0);
		}
	}

	public String toString()
	{
		return "Score  : " +(score  + gameTime) + "\nKilled by ghost " + ghostKills + "\nPlayer eat " + PlayerEatenFruit + " fruits"
				+"\nPacman eat " + pacmanEatenFruit + " fruits" +"\nTouched " + playerTouchedBox + " tims the boxes";
	}
	public void pacmanWasEaten(Packman pacman)
	{
		pacmanEatenId = pacman.getP_id() ;
	}

	public int getPacmanEatenId()
	{
		return pacmanEatenId ; 
	}
	public boolean getGameEnd()
	{
		return gameEnd ;
	}

	public void gameOver()
	{
		gameEnd = true  ;
		System.out.println(this.toString());
	}
	public Player getPlayer() {
		return this.player;
	}


	public int getGhostKills() {
		return ghostKills;
	}

	public void setGhostKills(int ghostKills) {
		this.ghostKills += ghostKills;
	}

	public int getPlayerEatenFruit() {
		return PlayerEatenFruit;
	}

	public void setPlayerEatenFruit(int playerEatenFruit) {
		PlayerEatenFruit += playerEatenFruit;
	}

	public int getPacmanEatenFruit() {
		return pacmanEatenFruit;
	}

	public void setPacmanEatenFruit(int pacmanEatenFruit) {
		this.pacmanEatenFruit += pacmanEatenFruit;
	}
	
	public int getPlayerTouchedBox() {
		return playerTouchedBox;
	}

	public void setPlayerTouchedBox(int playerTouchedBox) {
		this.playerTouchedBox += playerTouchedBox;
	}

	public void setgameTime(int gameTime) {
		this.gameTime += gameTime;
	}

	public int getgameTime() {
		return gameTime;
	}

}
