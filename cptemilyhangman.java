//Hangman
//Emily Wong
//v#3
import arc.*;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Font;

public class cptemilyhangman{
	public static void main(String[] args){
		Console con = new Console("Hangman", 1280, 720);
		//Theme Variables
		String strTheme;
		String strThemeTxt;
		String strWords[][];
		int intWords;
		//Location Variables
		boolean blnRepeat = true;
		int intMouseXLoc = 0;
		int intMouseYLoc = 0;
		int intClick = 0;		
		//Leaderboard Variables
		String strNumofScores = "";
		String strLeaderboard[][];
		int intCount = 0;
		int intRow;
		int intCol;
		TextOutputFile txtScore = new TextOutputFile("leaderboard.txt");
		TextInputFile txtLeaderboard = new TextInputFile("leaderboard.txt");
		
		//Showing main screen when program is opened
		mainmenubg(con);
		
		//Hovering on options
		while(blnRepeat == true){
			intMouseXLoc = con.currentMouseX();
			intMouseYLoc = con.currentMouseY();
			intClick = con.currentMouseButton();
			//Clicking Play Game
			if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 240 && intMouseYLoc <= 320 && intClick == 1){
				themes(con);
				blnRepeat = false;
				con.println("Enter the theme name:");
				strTheme = con.readLine();
				strThemeTxt = strTheme+".txt";
				intWords = countWords(strThemeTxt);
				strWords = loadThemeWords(intWords, strThemeTxt);
				strWords = sortWords(strWords, intWords);
				printWords(strWords, intWords, con);
				
			//Hover on Play Game
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 240 && intMouseYLoc <= 320){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Play Game", 440, 225);
				con.repaint();
			//Clicking Help
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 340 && intMouseYLoc <= 420 && intClick == 1){
				blnRepeat = false;
			//Hover on Help
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 340 && intMouseYLoc <= 420){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Help", 440, 325);
				con.repaint();
			//Clicking Leaderboard
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 440 && intMouseYLoc <= 520 && intClick == 1){
				blnRepeat = false;
			//Hover on Leaderboard
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 440 && intMouseYLoc <= 520){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Leaderboard", 440, 425);
				con.repaint();
			//Clicking Add Theme
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 540 && intMouseYLoc <= 620 && intClick == 1){
				blnRepeat = false;
			//Hover on Add Theme
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 540 && intMouseYLoc <= 620){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Add Theme", 440, 525);
				con.repaint();
			}else if(intMouseXLoc >= 0 && intMouseXLoc <= 150 && intMouseYLoc >= 630){
				con.setDrawColor(Color.RED);
				con.drawString("Quit", 10, 615);
				con.repaint();
				//Clicking Quit
				if(intClick == 1){
					con.closeConsole();
				}
			}else{
				con.setDrawColor(Color.WHITE);
				con.drawString("◆ Play Game", 440, 225);
				con.drawString("◆ Help", 440, 325);
				con.drawString("◆ Leaderboard", 440, 425);
				con.drawString("◆ Add Theme", 440, 525);
				con.drawString("Quit", 10, 615);
				con.repaint();
			}
		}	
	}
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
	//Header
	public static void header(Console con, String strName, String strTheme){
		
	}
	//Themes Screen
	public static void themes(Console con){
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setTextFont(fntSans);
		TextInputFile txtThemes = new TextInputFile("themes.txt");
		String strThemes;
		con.setDrawColor(Color.BLACK);
		con.fillRect(0, 0, 1280, 720);
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
	//Assigning Random Number to Word
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


}
