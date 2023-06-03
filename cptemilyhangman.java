//Hangman
//Emily Wong
//v#1
import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class cptemilyhangman{
	public static void main(String[] args){
		Console con = new Console("Hangman", 1280, 720);
		//Showing main screen when program is opened
		mainmenubg(con);
	}
	//Main Menu Screen
	public static void mainmenubg(Console con){
		BufferedImage imgMenuBG = con.loadImage("mainmenubg.png");
		con.drawImage(imgMenuBG, 0, 0);
		BufferedImage imgBulletPoint = con.loadImage("mainmenubg.png");
		con.drawImage(imgBulletPoint, 0, 0);
		con.setDrawColor(Color.WHITE);
		Font fntCabin = con.loadFont("CabinSketch-Bold.ttf", 120);
		con.setDrawFont(fntCabin);
		con.drawString("Hangman", 375, 35);
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 60);
		con.setDrawFont(fntSans);
		con.drawString("Play Game", 475, 225);
		con.drawString("Help", 475, 325);
		con.drawString("Leaderboard", 475, 425);
		con.drawString("Add Theme", 475, 525);
		con.drawString("Quit", 10, 615);
		con.repaint();
	}
}
