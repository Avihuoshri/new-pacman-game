package GUI;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game_figures.Fruit;
import Geom.Point3D;

public class Eatting_effect 
{
	final int NUMBER_OF_EFFECTS = 4 ;
	File img_file ;
	BufferedImage bi ;
	private String[] effects ;
	private Random random ;
	boolean activate ;
	Point3D ePoint ;
	int index ;
	
	/**
	 * constract eatting effect
	 */
	
	public Eatting_effect()
	{
		index = 0 ;
		activate = false ;
		effects = new String[NUMBER_OF_EFFECTS] ;
		effects[0] = "Images/pow.png" ;
		effects[1] = "Images/BOOM.png" ;
		effects[2] = "Images/BAM.png" ;
		effects[3] = "Images/POW2.png" ;
		img_file = new File(effects[index]);
		try {
			bi = ImageIO.read(img_file) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * get the image
	 * @return
	 */
	
	public BufferedImage getE_image()
	{
		img_file = new File(effects[index]);
		try {
			bi = ImageIO.read(img_file) ;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		index++;
		if(index == 4)
		{
			index = 0 ;
		}
	return bi ;
	}
	
	/**
	 * set activate as true
	 */
	
	public void setActivate()
	{
		activate = true ;
	}
	
	/**
	 * set E point as fruit
	 * @param fruit
	 */
	
	public void setEpoint(Fruit fruit)
	{
		ePoint = new Point3D(fruit.getFruitLocation()) ;
	}
	
	/**
	 * get E point as fruit
	 * @param fruit
	 */
	
	public Point3D getEpoint()
	{
		return ePoint;
	}
}

//


