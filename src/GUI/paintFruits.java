package GUI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import Game_figures.Fruit;

public class paintFruits extends JPanel
{
	pacmanBoard pacmanBoard ;
	
	
	/**
	 * construct the paint fruit
	 * @param pb
	 */
	
	public paintFruits(pacmanBoard pb)
	{
		pacmanBoard = pb ;
	}
	
	
	/**
	 * default constactor
	 */
	
	
	public void paint()
	{
		repaint();
	}
	
	/**
	 * paint component functions
	 */
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		for (Fruit fruit : pacmanBoard.ex4Game.fruitSet) 
		{
			int x = fruit.getFruitLocation().ix()*this.getWidth()/WIDTH ;
			int y = (fruit.getFruitLocation().iy()+40)*this.getHeight()/HEIGHT;
			int width  = 20  ;
			int height = 20 ;
			BufferedImage fruitImage = fruit.getFruitImage() ;
			
			g.drawImage(fruit.getFruitImage(),x , y, width, height, this) ;
		
		}
	}
}
