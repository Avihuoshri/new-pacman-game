package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Algorithms.SaveGameToCsv;
import Algorithms.ShortestPathAlgo;
import Coords.Map;
import Game_figures.Box;
import Game_figures.Fruit;
import Game_figures.Game;
import Game_figures.Ghost;
import Game_figures.Packman;
import Geom.Point3D;
import Threads.ThreadPaint;

public class pacmanBoard extends JFrame implements MouseListener , ComponentListener {
	
	public class paintGame extends JPanel
	{
		
	}

	private JPanel contentPane;
	public	 int WIDTH = 1433 ;/*1433*/
	public  int HEIGHT = 642 ; /*642*/
	
	private final double DEFULT_SPEED = 1;

	File mapFile                = new File("Images/Ariel1comic.png");
	File fruit1File             = new File("Images/orange monster.png");
	String pacmanImage          ="Images/boaz.png" ;
	String amirFigure           ="Images/Amir.png" ;
	String sapirFigure          ="Images/Sapir.png" ;
	String avihuFigure          ="Images/Avihu.png" ;
	String yellowMonsterImage   ="Images/yellow monster.png";
	String orangeMonsterImage   ="C:\\Users\\Avihu\\workspace2\\PACMAN\\Images\\orange monster.png" ;
	String lightBlueMonsterImage="Images/light blue monster.png" ;
	String clickedFruitImage    ="Images/Batman_logo.png" ;
	BufferedImage arielMap ;
	BufferedImage pacmanOrFruitImage ;
	Dimension dimension ;
	paintFruits fruitPanel ;

	Game game = new Game() ;
	MenuBar menuBar  ;
	Menu openOrSave  ;		
	
	MenuItem openItem     ;
	MenuItem saveItem     ;

	Menu drawMenu ;
	MenuItem fruitItem_1 ;
	MenuItem fruitItem_2;
	MenuItem fruitItem_3 ;
	MenuItem fruitItem_4 ;
	MenuItem pacmenItem  ;
	MenuItem boxItem ;
	private int mapWidth;
	private int mapHeight;
	
	boolean pacmanOn   = false ;
	boolean fruit_1_On = false ;
	boolean fruit_2_On = false ;
	boolean fruit_3_On = false ;
	boolean fruit_4_On = false ;
	boolean box_On     = false ;
	boolean pathReady  = false ;
	boolean paintFruit = true ;
	double widthRatio  ;
	double heightRatio ;
	private int fruitIdCounter;
	private int pacmanIdCounter;
	private int save_counter = 1 ;

	
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
//		setBounds(0, 0, WIDTH, HEIGHT);
		MenuBar menuBar = new MenuBar() ;
		Menu openOrSave = new Menu("Open / Save") ;		
		ImageIcon pacmanIcon = new ImageIcon("Pacman-icon .png");
		ImageIcon fruitIcon  = new ImageIcon("orange monster.png");
		MenuItem openItem    = new MenuItem("Open" ) ;
		MenuItem saveItem    = new MenuItem("Save") ;
		
		Menu drawMenu = new Menu("Draw figures");
		 pacmenItem   = new MenuItem("Pacman");
		 fruitItem_1  = new MenuItem("Fruit 1");
		 fruitItem_2  = new MenuItem("Fruit 2");
		 fruitItem_3  = new MenuItem("Fruit 3");
		 fruitItem_4  = new MenuItem("Fruit 4");
		 boxItem  = new MenuItem("Box") ;
		Menu play = new Menu("Play menu");
		MenuItem playGame = new MenuItem("Play game");
		fruitPanel = new paintFruits(this);
		fruitPanel.setSize(new Dimension(WIDTH, HEIGHT));
		openOrSave.add(openItem);
		openOrSave.add(saveItem);
		drawMenu.add(pacmenItem);
		drawMenu.add(fruitItem_1);
		drawMenu.add(fruitItem_2);
		drawMenu.add(fruitItem_3);
		drawMenu.add(fruitItem_4);
		drawMenu.add(boxItem);
		
		play.add(playGame) ;
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
		
		
		openItem.addActionListener(e ->
		{
			JFileChooser fc = new JFileChooser() ; 
			JButton open = new JButton() ;
			fc.setCurrentDirectory(new java.io.File("Games/ex4_games"));
			if(fc.showOpenDialog(open) == JFileChooser.APPROVE_OPTION )
			{				
			}
			String gamePath = fc.getSelectedFile().getAbsolutePath();
			game = new Game(gamePath);
			repaint();
		});
		
		playGame.addActionListener(e ->
		{
			play();
		});
//		saveItem.addActionListener(e ->
//		{
//			SaveGameToCsv saveGame =new SaveGameToCsv(game , save_counter);
//			kml_path.readPath(game, this);
//			save_counter++;
//		});
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
		 fruitItem_2.addActionListener(e ->{

				pacmanOn    = false ;
				fruit_1_On  = false ;
				 fruit_3_On = false ;
				 fruit_4_On = false ;
				box_On = false ;
				if(fruit_2_On == true)
				{
					fruit_2_On = false ;
				}
				else if(fruit_2_On == false)
				{
					fruit_2_On = true ;
				}
    	 });
		 fruitItem_3.addActionListener(e ->{

				 pacmanOn    = false ;
				 fruit_1_On  = false ;
				 fruit_2_On = false ;
				 fruit_4_On = false ;
				 box_On     = false ;
				if(fruit_3_On == true)
				{
					fruit_3_On = false ;
				}
				else if(fruit_3_On == false)
				{
					fruit_3_On = true ;
				}
    	 });
		 fruitItem_4.addActionListener(e ->{
			     pacmanOn   = false ;
			     fruit_1_On = false ;
				 fruit_2_On = false ;
				 fruit_3_On = false ;
				box_On = false ;
				if(fruit_4_On == true)
				{
					fruit_4_On = false ;
				}
				else if(fruit_4_On == false)
				{
					fruit_4_On = true ;
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

	
	public void paint(Graphics g)
	{
		Image image = createImage(5000,5000);
		Graphics g1 = image.getGraphics();
		g1.drawImage(arielMap,10 ,55, this.getWidth()-17, this.getHeight()-64, this) ;
		
		for (Box box : game.boxSet) 
		{
			g1.fillRect((box.getLowerPoint().ix()+58)*this.getWidth()/WIDTH, (box.getUpperPoint().iy()+20)* this.getHeight()/HEIGHT , box.getBoxWidth(), box.getBoxHeight());
		}
		for (Ghost ghost : game.ghostSet) 
		{
			g1.drawImage(ghost.getG_image(), ghost.getG_point().ix()*this.getWidth()/WIDTH , ghost.getG_point().iy() *this.getHeight()/HEIGHT, 40, 40, this) ;
		}
//		if(paintFruit == true )
//		{
//		fruitPanel.paint();
		for (Packman pacman : game.pacmanSet) 
		{
			AffineTransform at = AffineTransform.getTranslateInstance(pacman.getP_Location().ix()*this.getWidth()/WIDTH, (pacman.getP_Location().iy()+40)*this.getHeight()/HEIGHT);
			at.rotate(Math.toRadians(45));
			Graphics2D g2d = (Graphics2D) g ;
			g1.drawImage(pacman.getP_Image(), pacman.getP_Location().ix()*this.getWidth()/WIDTH, (pacman.getP_Location().iy()+40)*this.getHeight()/HEIGHT, this);
		}
		if(pathReady == true)
		{
//			for (Packman pacman : game.pacmanSet) 
//			{
//				int fruit_X = pacman.getP_Path().getFruitsPath().get(0).getFruitLocation().ix() ;
//				int furit_Y = pacman.getP_Path().getFruitsPath().get(0).getFruitLocation().iy() ;
//				int pacman_X =pacman.getP_Location().ix() ;
//				int pacman_Y =pacman.getP_Location().iy() ;
//				g.drawLine(pacman_X*this.getWidth()/WIDTH , (pacman_Y+40) * this.getHeight()/HEIGHT,fruit_X*this.getWidth()/WIDTH, (furit_Y+40) * this.getHeight()/HEIGHT);
//			
//				for (Fruit fruitset : pacman.getP_Path().getFruitsPath()) 
//				{
//					
//					int size = pacman.getP_Path().getFruitsPath().size() ;
//					int fruitIndex = pacman.getP_Path().getFruitsPath().indexOf(fruitset) ;
//					if(fruitIndex != (size-1))
//					{
//						Fruit nextFruit = pacman.getP_Path().getFruitsPath().get(fruitIndex + 1) ;
//						g.drawLine(fruitset.getFruitLocation().ix()*this.getWidth()/WIDTH, (fruitset.getFruitLocation().iy()+40)* this.getHeight()/HEIGHT, nextFruit.getFruitLocation().ix()*this.getWidth()/WIDTH, (nextFruit.getFruitLocation().iy()+40)* this.getHeight()/HEIGHT);
//					}
//				}
//			}
			
		}
		
		

		for (Fruit fruit : game.fruitSet) 
		{
			int x = fruit.getFruitLocation().ix()*this.getWidth()/WIDTH ;
			int y = (fruit.getFruitLocation().iy()+40)*this.getHeight()/HEIGHT;
			int width  = 20  ;
			int height = 20 ;
			BufferedImage fruitImage = fruit.getFruitImage() ;
			
			g1.drawImage(fruit.getFruitImage(),x , y, width, height, this) ;
//			paintFruit = false ;
//		
//		}
	}
		g.drawImage(image,0,0,this);
	}
		
	
	public void play()
	{
		ShortestPathAlgo spa = new ShortestPathAlgo(game);
		pathReady = true ;
		for (Packman pacman : game.pacmanSet) 
		{
			int index = game.pacmanSet.indexOf(pacman);
			System.out.println(game.pacmanSet.get(index).getP_Path().toString());

		}
		repaint();
		Point3D point;
		Point3D pacmanPoint ;
		Thread th = new Thread();
		for (Packman pacman : game.pacmanSet) 
		{
			
			ThreadPaint tp = new ThreadPaint(this, WIDTH, HEIGHT , pacman);
			tp.start();
			
		}
		
	}
	
	public void drawPath(Packman pacman)
	{
		
	}
		
	public Game getGame()
	{
		return game ;
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
		// TODO Auto-generated method stub
		System.out.println(e.getX() + "       "  + e.getY());
		
		if(pacmanOn == true)
		{
			Packman  pacman = new Packman(pacmanIdCounter , e.getX()*WIDTH/this.getWidth() , e.getY()*HEIGHT/this.getHeight() , DEFULT_SPEED) ;
			System.out.println(pacman.getP_Location());
			pacmanIdCounter++ ;
			game.pacmanSet.add(pacman) ;
			repaint();
			
		}
		
		if(fruit_1_On)
		{
			Fruit fruit = new Fruit(fruitIdCounter , e.getX()*WIDTH/this.getWidth() , e.getY()*HEIGHT/this.getHeight() ,clickedFruitImage ) ;
			game.fruitSet.add(fruit) ;
			fruitIdCounter++ ;
			repaint();
		}
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


}
