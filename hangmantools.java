import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class hangmantools{
	//Main Menu Screen
	public static void mainmenubg(Console con){
		BufferedImage imgMenuBG = con.loadImage("mainmenubg.png");
		con.drawImage(imgMenuBG, 0, 0);
		con.setDrawColor(Color.WHITE);
		Font fntCabin = con.loadFont("CabinSketch-Bold.ttf", 120);
		con.setDrawFont(fntCabin);
		con.drawString("Hangman", 375, 35);
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 60);
		con.setDrawFont(fntSans);
		con.drawString("◆ Play Game", 440, 225);
		con.drawString("◆ Help", 440, 325);
		con.drawString("◆ Leaderboard", 440, 425);
		con.drawString("◆ Add Theme", 440, 525);
		con.drawString("Quit", 10, 615);
		con.repaint();
	}
	//Header Name Only
	public static void headerName(Console con, String strName){
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setDrawFont(fntSans);
		con.setDrawColor(Color.WHITE);
		con.fillRect(0, 0, 1280, 60);
		con.setDrawColor(Color.BLACK);
		con.drawString(strName, 0, 5);
		con.repaint();
	}
	//General Header
	public static void headerGeneral(Console con, String strName, String strTheme){
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setDrawFont(fntSans);
		con.setDrawColor(Color.WHITE);
		con.fillRect(0, 0, 1280, 60);
		con.setDrawColor(Color.BLACK);
		con.drawString(strName, 0, 5);
		con.drawString(strTheme, 1090, 5);
		con.repaint();
	}
	//Name Screen
	public static String name(Console con){
		String strName;
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setTextFont(fntSans);
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.setDrawColor(Color.WHITE);
		con.repaint();
		con.println("Enter a name: ");
		strName = con.readLine();
		return strName;
	}
	//Themes Screen
	public static void themes(Console con){
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setTextFont(fntSans);
		TextInputFile txtThemes = new TextInputFile("themes.txt");
		String strThemes;
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		con.repaint();
		con.println("\n\nWhat theme would you like to play?");
		while(txtThemes.eof()==false){
			strThemes = txtThemes.readLine();
			con.println(strThemes);
		}
	}
	//Counting Lines in File
	public static int countWords(String strFileName){
		TextInputFile txtWords = new TextInputFile(strFileName);
		int intLines = 0;
		String strData;
		while(txtWords.eof() == false){
			strData = txtWords.readLine();
			intLines++;
		}
		txtWords.close();
		return intLines;
	}
	//Assigning Random Number to Words
	public static String[][] loadThemeWords(int intCount, String strFileName){
		String strWords[][] = new String[intCount][2];
		TextInputFile txtWords = new TextInputFile(strFileName);
		int intRow;
		int intRand;
		for(intRow = 0; intRow < intCount; intRow++){
			strWords[intRow][0] = txtWords.readLine();
			intRand = (int)(Math.random()*100+1);
			strWords[intRow][1] = Integer.toString(intRand) ;
		}
		txtWords.close();
		return strWords;
	}
	//Assigning Random Number to Letters
	public static String[][] loadWordLetters(int intCount){
		String strLetters[][] = new String[intCount][2];
		TextInputFile txtLetters = new TextInputFile("letters.txt");
		int intRow;
		int intRand;
		for(intRow = 0; intRow < intCount; intRow++){
			strLetters[intRow][0] = txtLetters.readLine();
			intRand = (int)(Math.random()*100+1);
			strLetters[intRow][1] = Integer.toString(intRand) ;
		}
		txtLetters.close();
		return strLetters;
	}
	//Printing Text File from Array
	public static void printWords(String strWords[][], int intCount, Console con){
		int intRow;
		for(intRow = 0; intRow < intCount; intRow++){
			con.print((strWords[intRow][0] + ":                                    ").substring(0,15));
			con.print((strWords[intRow][1] + "                                     ").substring(0,20));
			con.println("");
		}
	}
	//Bubble Sort
	public static String[][] sortWords(String strWords[][], int intWords){
		int intBelow;
		int intCurrent;
		int intCounter;
		int intCounter2;
		String strTemp;
		for(intCounter2 = 0; intCounter2 < intWords-1; intCounter2++){
			for(intCounter = 0; intCounter < intWords-intCounter2-1; intCounter++){
				intBelow = Integer.parseInt(strWords[intCounter+1][1]);
				intCurrent = Integer.parseInt(strWords[intCounter][1]);
				if(intBelow > intCurrent){
					strTemp = strWords[intCounter+1][0];
					strWords[intCounter+1][0] = strWords[intCounter][0];
					strWords[intCounter][0] = strTemp;
					strTemp = strWords[intCounter+1][1];
					strWords[intCounter+1][1] = strWords[intCounter][1];
					strWords[intCounter][1] = strTemp;
				}
			}
		}
		
		return strWords;
	}
	//Hangman Screen
	public static void gameplayScreen(Console con, int intLength){
		int intCount;
		int intX = 550;
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		con.setDrawColor(Color.WHITE);
		con.fillRect(100, 600, 400, 15);
		con.fillRect(275, 125, 15, 475);
		con.fillRect(275, 125, 200, 15);
		con.fillRect(475, 125, 15, 50);
		for(intCount = 1; intCount <= intLength; intCount++){
			con.drawString("__", intX, 500);
			intX = intX + 65;
		}
		con.repaint();
	}
	//Winning Screen
	public static void winScreen(Console con){
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		con.setDrawColor(Color.GREEN);
		Font fntCabin = con.loadFont("CabinSketch-Bold.ttf", 120);
		con.setDrawFont(fntCabin);
		con.drawString("You Win", 375, 135);
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 60);
		con.setDrawFont(fntSans);
		con.setDrawColor(Color.WHITE);
		con.drawString("◆ Play Again", 440, 325);
		con.drawString("◆ Quit", 440, 425);
		con.repaint();
	}
}
