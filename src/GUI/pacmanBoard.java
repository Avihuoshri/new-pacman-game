package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import Algorithms.ShortestPathAlgo;
import Coords.Map;
import Game_figures.Box;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Ghost;
import Game_figures.Packman;
import Game_figures.Player;
import Geom.Point3D;
import Robot.Play;
import Threads.Move;
import Threads.ThreadAutoPlayer;
import Threads.ThreadGhost;
import Threads.ThreadPacman;
import Threads.ThreadPlayer;
import Threads.TimeThread;

public class pacmanBoard extends JFrame implements MouseListener , ComponentListener {

	double azimuth[]= {0,0,0};
	private JPanel contentPane;
	public	 int WIDTH = 1433 ;/*1433*/
	public   int HEIGHT = 642 ; /*642*/

	private final double DEFULT_SPEED = 1;

	File mapFile                = new File("Images/Ariel1comic.png");
	File fruit1File             = new File("Images/orange monster.png");

	String clickedFruitImage    ="Images/Batman_logo.png" ;
	BufferedImage arielMap ;
	BufferedImage pacmanOrFruitImage ;
	Dimension dimension   ;
	paintFruits fruitPanel;

	Game ex4Game = new Game();
	MenuBar menuBar       ;
	Menu openOrSave       ;		
	Menu drawMenu         ;
	MenuItem openItem     ;
	MenuItem saveItem     ;
	MenuItem fruitItem_1  ;
	MenuItem playerItem   ;
	MenuItem pacmenItem   ;
	MenuItem boxItem      ;
	private int mapWidth  ;
	private int mapHeight ;

	boolean pacmanOn    ;
	boolean fruit_1_On  ;
	boolean fruit_2_On  ;
	boolean fruit_3_On  ;
	boolean fruit_4_On  ;
	boolean player_On   ;
	boolean box_On      ;
	boolean pathReady   ;
	boolean paintFruit  ;
	boolean playerClick ;
	boolean ex3         ;
	boolean ex4         ;
	boolean manual      ;
	double widthRatio   ;
	double heightRatio  ;
	private int fruitIdCounter   ;
	private int pacmanIdCounter  ;
	private int save_counter = 1 ;
	Eatting_effect effect ;
	ArrayList<String> board_data ;
	Play EX4play ;
	private boolean firstTime;

	/**
	 * Create the frame.
	 */
	public pacmanBoard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		init() ;
		getContentPane().addMouseListener( this);

	}


	private void init()
	{
		this.addComponentListener(this);

		effect = new Eatting_effect();
		pacmanOn   = false ;
		fruit_1_On = false ;
		fruit_2_On = false ;
		fruit_3_On = false ;
		fruit_4_On = false ;
		box_On     = false ;
		pathReady  = false ;
		playerClick = false;
		paintFruit = true  ;
		ex3 = true         ;
		ex4 = true         ;
		firstTime = true   ;
		manual = false     ;
		MenuBar menuBar = new MenuBar() ;
		Menu openOrSave = new Menu("Open / Save") ;		

		MenuItem loadEx3    = new MenuItem("Load Ex3 games" ) ;
		MenuItem loadEx4    = new MenuItem("Load Ex4 games" ) ;
		saveItem    = new MenuItem("Save") ;

		drawMenu = new Menu("Draw figures");
		pacmenItem   = new MenuItem("Pacman");
		fruitItem_1  = new MenuItem("Fruit");
		playerItem = new MenuItem("Player") ;

		boxItem  = new MenuItem("Box") ;

		Menu play = new Menu("Play menu");
		MenuItem playManual = new MenuItem("Play manual");
		MenuItem playAuto = new MenuItem("Play algorithem") ;
		fruitPanel = new paintFruits(this);
		fruitPanel.setSize(new Dimension(WIDTH, HEIGHT));

		openOrSave.add(loadEx3);
		openOrSave.add(loadEx4) ;
		openOrSave.add(saveItem);
		drawMenu.add(pacmenItem);
		drawMenu.add(fruitItem_1);
		drawMenu.add(playerItem);
		drawMenu.add(boxItem);

		play.add(playManual) ;
		play.add(playAuto) ;
		menuBar.add(openOrSave);
		menuBar.add(drawMenu);
		menuBar.add(play);
		this.add(fruitPanel) ;
		this.setMenuBar(menuBar);	
		setTitle("Pacman");    
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		pack();

		try {
			arielMap  = ImageIO.read(mapFile);
			mapWidth  = arielMap.getWidth() ;
			mapHeight = arielMap.getHeight() ;
			setPreferredSize(new Dimension(mapWidth, mapHeight));
		} catch (IOException e) {
			e.printStackTrace();
		}

		playAuto.addActionListener(e ->
		{
			playAuto();
		});
		loadEx3.addActionListener(e ->
		{
			ex3 = true  ;
			ex4 = false ;
			JFileChooser fc = new JFileChooser() ; 
			JButton open = new JButton() ;
			fc.setCurrentDirectory(new java.io.File("Games/ex4_games"));
			if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION )
			{		
				String gamePath = fc.getSelectedFile().getAbsolutePath();
				ex4Game = new Game(gamePath);

				//למחוק את זה
				Point3D p1 = new Point3D(500 , 350);
				Point3D p2 = new Point3D(700 , 400);
				System.out.println("LINE CUT : "+  checkIfCutting(p1,p2 )) ;

				repaint();
			}

		});
		//היום תתמקד על בנייה של האלגוריתם
		loadEx4.addActionListener(e ->
		{
			ex4 = true  ;
			ex3 = false ;
			JFileChooser fc = new JFileChooser() ; 
			JButton open = new JButton() ;
			fc.setCurrentDirectory(new java.io.File("Games/ex4_games"));
			if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION )
			{				
				String gamePath = fc.getSelectedFile().getAbsolutePath();
				EX4play = new Play(gamePath) ;
				EX4play.setIDs(203458484,205695901);
				//				                                      
				String map_data = EX4play.getBoundingBox();
				System.out.println("Bounding Box info: "+map_data);
				board_data = EX4play.getBoard();
				ex4Game = new Game(board_data);
				System.out.println("in pacmanboard : "+ex4Game.getPlayer());

				for(int i=0;i<board_data.size();i++) {
					System.out.println(board_data.get(i));
				}


				System.out.println();
				System.out.println("Init Player Location should be set using the bounding box info");

				EX4play.setInitLocation(32.1040,35.2061);			

				// 8) stop the server - not needed in the real implementation.
				//play1.stop();
				System.out.println("**** Done Game (user stop) ****");

				// 9) print the data & save to the course DB
				String info = EX4play.getStatistics();
				System.out.println(info);

				repaint();
			}

		});

		playManual.addActionListener(e ->
		{
			play();
			manual = true ;

		});
		//		saveItem.addActionListener(e ->
		//		{
		//			SaveGameToCsv saveGame =new SaveGameToCsv(game , save_counter);
		//			kml_path.readPath(game, this);
		//			save_counter++;
		//		});
		playerItem.addActionListener(e -> 
		{
			pacmanOn = false   ;
			fruit_1_On = false ;
			fruit_2_On = false ;
			fruit_3_On = false ;
			fruit_4_On = false ;
			box_On = false ;
			playerClick = true;


		});
		pacmenItem.addActionListener(e ->{
			System.out.println("in pacman");
			fruit_1_On = false ;
			fruit_2_On = false ;
			fruit_3_On = false ;
			fruit_4_On = false ;
			box_On = false ;
			if(pacmanOn == true)
			{
				pacmanOn = false ;
			}
			else if(pacmanOn == false)
			{
				pacmanOn = true ;
			}
			repaint();

		});

		fruitItem_1.addActionListener(e ->{
			pacmanOn   = false ;
			fruit_2_On = false ;
			fruit_3_On = false ;
			fruit_4_On = false ;
			box_On = false ;
			if(fruit_1_On == true)
			{
				fruit_1_On = false ;
			}
			else if(fruit_1_On == false)
			{
				fruit_1_On = true ;
			}
		});


		boxItem.addActionListener(e ->{
			pacmanOn   = false ;
			fruit_1_On = false ;
			fruit_2_On = false ;
			fruit_3_On = false ;
			fruit_4_On = false ;
			if(box_On  == true)
			{
				box_On = false ;
			}
			else if(box_On == false)
			{
				box_On = true ;
			}
		});		 

	}



	public Eatting_effect getEffect() {
		return effect;
	}




	public void nextMove() {
		board_data=EX4play.getBoard();
		ex4Game=new Game(board_data);
		EX4play.rotate(azimuth[0]);
		repaint();
	}
	public void paint(Graphics g)
	{
		Image image = createImage(2000,2000);
		Image effectI = createImage(5000,5000);
		Graphics g1 = image.getGraphics();
		Graphics g2 = effectI.getGraphics();


		if(effect.activate == true)
		{
			g2.drawImage(arielMap,10 ,55, this.getWidth()-17, this.getHeight()-64, this) ;
		}
		else
			g1.drawImage(arielMap,10 ,55, this.getWidth()-17, this.getHeight()-64, this) ;

//		g1.setColor(Color.RED);
//		g1.drawLine(200, 500, 900, 500);
//		g2.drawLine(200, 500, 900, 500);
		for (Box box : ex4Game.boxSet) 
		{
			if(effect.activate == true)
			{  
				final int VERTIX = 20 ;
				/**//**//*box.getBoxImage(), (box.getLowerPoint().ix()+58)*this.getWidth()/WIDTH, (box.getUpperPoint().iy()+20)* this.getHeight()/HEIGHT , box.getBoxWidth(), box.getBoxHeight(), this*/
				g2.drawImage(box.getBoxImage(), (box.getLowerPoint().ix())*this.getWidth()/WIDTH, (box.getUpperPoint().iy())* this.getHeight()/HEIGHT , box.getBoxWidth()*this.getWidth()/WIDTH, box.getBoxHeight()* this.getHeight()/HEIGHT, this);
				g2.fillOval(box.getUpper_L_Point().ix()-10, box.getUpper_L_Point().iy()-10, VERTIX, VERTIX);
				g2.fillOval(box.getUpper_R_Point().ix()-10, box.getUpper_L_Point().iy()-10, VERTIX, VERTIX);
				g2.fillOval(box.getLower_L_Point().ix()-10, box.getLower_L_Point().iy()-10, VERTIX, VERTIX);
				g2.fillOval(box.getLower_R_Point().ix()-10, box.getLower_R_Point().iy()-10, VERTIX, VERTIX);
			} 
			else
				g1.drawImage(box.getBoxImage(), (box.getLowerPoint().ix())*this.getWidth()/WIDTH, (box.getUpperPoint().iy())* this.getHeight()/HEIGHT , box.getBoxWidth()*this.getWidth()/WIDTH, box.getBoxHeight()* this.getHeight()/HEIGHT, this);
			final int VERTIX = 20 ;
			g1.fillOval(box.getUpper_L_Point().ix()-10, box.getUpper_L_Point().iy()-10, VERTIX, VERTIX);
			g1.fillOval(box.getUpper_R_Point().ix()-10, box.getUpper_L_Point().iy()-10, VERTIX, VERTIX);
			g1.fillOval(box.getLower_L_Point().ix()-10, box.getLower_L_Point().iy()-10, VERTIX, VERTIX);
			g1.fillOval(box.getLower_R_Point().ix()-10, box.getLower_R_Point().iy()-10, VERTIX, VERTIX);


		}
		for (Ghost ghost : ex4Game.ghostSet) 
		{
			if(effect.activate == true)
			{
				g2.drawImage(ghost.getG_image(), ghost.getG_point().ix()*this.getWidth()/WIDTH , (ghost.getG_point().iy()) *this.getHeight()/HEIGHT, 50, 50, this) ;
			}
			else
				g1.drawImage(ghost.getG_image(), ghost.getG_point().ix()*this.getWidth()/WIDTH , (ghost.getG_point().iy()) *this.getHeight()/HEIGHT, 50, 50, this) ;

		}

		if(effect.activate == true)
		{

			int x = effect.getEpoint().ix()*this.getWidth()/WIDTH ;
			int y = effect.getEpoint().iy()*this.getHeight()/HEIGHT;
			g2.drawImage(effect.getE_image(),x,y,50,50,this);			
		}

		for (Packman pacman : ex4Game.pacmanSet) 
		{
			AffineTransform at = AffineTransform.getTranslateInstance(pacman.getP_Location().ix()*this.getWidth()/WIDTH, (pacman.getP_Location().iy())*this.getHeight()/HEIGHT);
			at.rotate(Math.toRadians(45));
			Graphics2D g2d = (Graphics2D) g ;
			if(effect.activate == true)
			{
				g2.drawImage(pacman.getP_Image(), pacman.getP_Location().ix()*this.getWidth()/WIDTH, (pacman.getP_Location().iy())*this.getHeight()/HEIGHT,55,55, this);
			}
			else
				g1.drawImage(pacman.getP_Image(), pacman.getP_Location().ix()*this.getWidth()/WIDTH, (pacman.getP_Location().iy())*this.getHeight()/HEIGHT,55 , 55, this);

		}

		Player player = ex4Game.getPlayer() ;
		if(playerClick == true)
		{
			g1.drawImage(player.getP_Image(),player.getPlayerLocation().ix()*this.getWidth()/WIDTH,(player.getPlayerLocation().iy())*this.getHeight()/HEIGHT,this);
			//			g1.fillOval(player.getPlayerLocation().ix()*this.getWidth()/WIDTH,(player.getPlayerLocation().iy())*this.getHeight()/HEIGHT , 50 , 50);
			g2.drawImage(player.getP_Image(),player.getPlayerLocation().ix()*this.getWidth()/WIDTH,(player.getPlayerLocation().iy())*this.getHeight()/HEIGHT,this);

			//			g.drawImage(player.getP_Image(), player.getPlayerLocation().ix()*this.getWidth()/WIDTH, player.getPlayerLocation().iy()*this.getHeight(), 40 , 40 , this);
		}
		//		if(pathReady == true)
		//		{
		//			
		//			for (Packman pacman : game.pacmanSet) 
		//			{
		//				if(pacman.getP_Path().getFruitsPath().size() >0)
		//				{
		//				int fruit_X = pacman.getP_Path().getFruitsPath().get(0).getFruitLocation().ix() ;
		//				int furit_Y = pacman.getP_Path().getFruitsPath().get(0).getFruitLocation().iy() ;
		//				int pacman_X =pacman.getP_Location().ix() ;
		//				int pacman_Y =pacman.getP_Location().iy() ; /*pacman_Y+40*/														/*furit_Y+40*/
		//				g1.drawLine(pacman_X*this.getWidth()/WIDTH , (pacman_Y) * this.getHeight()/HEIGHT,fruit_X*this.getWidth()/WIDTH, (furit_Y) * this.getHeight()/HEIGHT);
		//				}
		//				for (Fruit fruitset : pacman.getP_Path().getFruitsPath()) 
		//				{
		//					
		//					int size = pacman.getP_Path().getFruitsPath().size() ;
		//					int fruitIndex = pacman.getP_Path().getFruitsPath().indexOf(fruitset) ;
		//					if(fruitIndex != (size-1))
		//					{
		//						Fruit nextFruit = pacman.getP_Path().getFruitsPath().get(fruitIndex + 1) ;						/*iy()+40*/
		//						g1.drawLine(fruitset.getFruitLocation().ix()*this.getWidth()/WIDTH, (fruitset.getFruitLocation().iy()+40)* this.getHeight()/HEIGHT, nextFruit.getFruitLocation().ix()*this.getWidth()/WIDTH, (nextFruit.getFruitLocation().iy()+40)* this.getHeight()/HEIGHT);
		//					}
		//				}
		//				
		//			}
		//			
		//		}
		//		


		for (Fruit fruit : ex4Game.fruitSet) 
		{
			int x = fruit.getFruitLocation().ix()*this.getWidth()/WIDTH ;
			int y = (fruit.getFruitLocation().iy())*this.getHeight()/HEIGHT;
			int width  = 20  ;
			int height = 20 ;
			BufferedImage fruitImage = fruit.getFruitImage() ;
			if(effect.activate == true)
			{
				g2.drawImage(fruitImage,x , y, width, height, this) ;
			}
			else
				g1.drawImage(fruitImage,x , y, width, height, this) ;

		}

		if(effect.activate == true)
		{
			g.drawImage(effectI,0,0,this);
		}
		else
			g.drawImage(image,0,0,this);

	}

	public void playAuto()
	{
		ShortestPathAlgo spa = new ShortestPathAlgo(ex4Game);

		while(ex4Game.getGameEnd() == false) 
		{
			for (Packman pacman : ex4Game.pacmanSet) 
			{

				ThreadPacman tp = new ThreadPacman(this, WIDTH, HEIGHT , pacman);
				tp.start();

			}
		}

		for (Ghost ghost : ex4Game.ghostSet) 
		{
			if(ex4Game.fruitSet.size() != 0)
			{
				ThreadGhost tg = new ThreadGhost(this , ghost) ; /*לשנות כדי שמטלה 3 תעבוד*/
				tg.start();
			}
		}

		if(playerClick == true)
		{
			System.out.println("starting algorithm");
			ThreadAutoPlayer tap = new ThreadAutoPlayer(this);
		}

	}
	public void play()
	{
		if(ex3 == true)
		{
			ShortestPathAlgo spa = new ShortestPathAlgo(ex4Game);
			pathReady = true ;
			for (Packman pacman : ex4Game.pacmanSet) 
			{
				int index = ex4Game.pacmanSet.indexOf(pacman);
				
			}
			//			repaint();

			for (Packman pacman : ex4Game.pacmanSet) 
			{

				ThreadPacman tp = new ThreadPacman(this, WIDTH, HEIGHT , pacman);
				tp.start();

			}
			for (Ghost ghost : ex4Game.ghostSet) 
			{
				if(ex4Game.fruitSet.size() != 0)
				{
					ThreadGhost tg = new ThreadGhost(this , ghost) ; /*לשנות כדי שמטלה 3 תעבוד*/
					tg.start();
				}
			}
			TimeThread time = new TimeThread(this);
			time.start();



		}
		else if(ex4 == true)
		{

			EX4play.start();
			Move move=new Move(this);
			move.start();
		}
	}

	public Game getGame()
	{
		return ex4Game ;
	}

	public int getMapHeight()
	{
		return HEIGHT ;
	}

	public int getMapWidth()
	{
		return WIDTH ;
	}
	public void setPaintFruit()
	{
		paintFruit = true ;
	}
	@Override
	public void componentResized(ComponentEvent e) {
		widthRatio   = e.getComponent().getWidth() /WIDTH  ;
		heightRatio  = e.getComponent().getHeight() /HEIGHT ;
		repaint();
		WIDTH =  e.getComponent().getWidth() ;
		HEIGHT = e.getComponent().getHeight() ;
	}



	@Override
	public void mouseClicked(MouseEvent e) {
		Map map=new Map();
		int x1=(int)(e.getX()*(this.getWidth()/WIDTH));
		int y1=(int)(e.getY()*(this.getHeight()/HEIGHT));
		// TODO Auto-generated method stub
		//if(craetPlayer.equals("yes")){
		//}
		if(firstTime == true)
		{
			Point3D PlayerClicked = new Point3D(e.getX() ,e.getY() );
			ex4Game.getPlayer().setPlayerLocation(PlayerClicked);
			PlayerClicked = map.pixelToCoords(PlayerClicked.ix(), PlayerClicked.iy(), mapWidth, mapHeight) ;
			//			EX4play.setInitLocation(PlayerClicked.iy(), PlayerClicked.ix()) ;

			firstTime = false ;
			playerClick = true ;
			repaint() ;
		}
		else if (firstTime == false && manual == true)  // צריך לטפל בפלייר
		{
			Point3D playerdir = new Point3D(e.getX() ,e.getY() +44) ;		
			ex4Game.getPlayer().setDirection(playerdir);

			ThreadPlayer tplayer = new ThreadPlayer(this);    
			tplayer.start();

			repaint();
		}

		if(pacmanOn == true)
		{
			Packman  pacman = new Packman(pacmanIdCounter , e.getX()*WIDTH/this.getWidth() , e.getY()*HEIGHT/this.getHeight() , DEFULT_SPEED) ;
			pacmanIdCounter++ ;
			ex4Game.pacmanSet.add(pacman) ;
			repaint();
		}

		if(fruit_1_On)
		{
			Fruit fruit = new Fruit(fruitIdCounter , e.getX()*WIDTH/this.getWidth() , e.getY()*HEIGHT/this.getHeight() ,clickedFruitImage ) ;
			ex4Game.fruitSet.add(fruit) ;
			fruitIdCounter++ ;
			repaint();
		}
		//		Point3D targetClicked=new Point3D(x1,y1,0);
		//		targetClicked=map.pixelToCoords(targetClicked.ix(), targetClicked.iy(), mapWidth, mapHeight);
		//		System.out.println(targetClicked);
		//		Point3D playerPoint=new Point3D(ex4Game.getPlayer().getPlayerLocation());
		//		playerPoint=map.pixelToCoords(playerPoint.ix(), playerPoint.iy(), mapWidth, mapHeight);
		//		System.out.println(playerPoint);
		//		CoordsConverter converter=new CoordsConverter();
		//		azimuth=converter.azimuth_elevation_dist(playerPoint, targetClicked);
		//		azimuth[0]=360-(azimuth[0])+90;
		//
		//		while(azimuth[0]<0) {
		//			azimuth[0] =(azimuth[0]+ 360);
		//		}
		//		azimuth[0]=(azimuth[0]%360);
	}


	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void componentHidden(ComponentEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}


	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
	public Play getEX4play()
	{
		return EX4play ;
	}

	public boolean checkIfCutting(Point3D L , Point3D R)
	{
		Point3D linearPointA = new Point3D(200 , 500) ;
		Point3D linearPointB = new Point3D(900 , 500) ;
		for (Box box : ex4Game.boxSet) 
		{
			Point3D LeftUp    = new Point3D(box.getUpper_L_Point()) ;
			Point3D LeftDown  = new Point3D(box.getLower_L_Point()) ; 
			Point3D RightUp   = new Point3D(box.getUpper_R_Point()) ;
			Point3D RightDown = new Point3D(box.getLower_R_Point()) ;

			int linear =smartLinear(linearPointA, linearPointB, RightDown.iy(), 'y') ;
			if(linear == RightDown.ix())
			{
				if(linear < RightDown.iy() && linear > RightUp.iy())
				{
					return true ;
				}
			}
			linear =smartLinear(linearPointA, linearPointB, LeftUp.ix(), 'x') ;
			if(linear == LeftUp.iy())
			{
				if(linear > LeftUp.ix() && linear < RightUp.ix())
				{
					return true ;
				}
			}


			linear =smartLinear(linearPointA, linearPointB, LeftDown.iy(), 'y') ;
			if(linear == LeftDown.ix())
			{
				if(linear < LeftDown.iy() && linear > LeftUp.iy())
				{
					return true ;
				}
			}

			linear =smartLinear(linearPointA, linearPointB, LeftDown.ix(), 'x') ;
			if(linear == LeftDown.iy())
			{
				if(linear > LeftDown.ix() && linear < RightDown.ix())
				{
					return true ;
				}
			}
		}

		return false ;
	}


	public int smartLinear(Point3D L , Point3D R , int num , char X_Or_Y)
	{
		if(L.ix() == R.ix())
		{
			return L.iy();
		}
		double incline = (L.y() - R.y()) / (L.x() - R.x());


		if(X_Or_Y == 'x')
		{
			int y = ( int ) (incline*(num - L.x()) + L.y()) ;
			return y ;
		}
		else
		{
			int x = (int) (((num - L.y()) / incline) + L.x()) ;
			return x ;
		}
	}


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try {
					pacmanBoard frame = new pacmanBoard();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
