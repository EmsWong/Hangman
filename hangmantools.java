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
		String strLetters[][] = new String[intCount][3];
		TextInputFile txtLetters = new TextInputFile("letters.txt");
		int intRow;
		int intRand;
		int intLoc = 550;
		for(intRow = 0; intRow < intCount; intRow++){
			strLetters[intRow][0] = txtLetters.readLine();
			if(strLetters[intRow][0].equals(" ")){
				strLetters[intRow][1] = Integer.toString(0);
				strLetters[intRow][2] = Integer.toString(intLoc);
			}else{
				intRand = (int)(Math.random()*100+1);
				strLetters[intRow][1] = Integer.toString(intRand);
				strLetters[intRow][2] = Integer.toString(intLoc);
			}
			intLoc = intLoc + 65;
		}
		txtLetters.close();
		return strLetters;
	}
	//Printing Text File from Array
	public static void printWords(String strWords[][], int intCount, Console con){
		Font fntSans = con.loadFont("CamingoCode-Bold.ttf", 30);
		con.setTextFont(fntSans);
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.repaint();
		con.clear();
		int intRow;
		for(intRow = 0; intRow < intCount; intRow++){
			con.print("                            ");
			con.print((strWords[intRow][0] + ":                                    ").substring(0,16));
			con.print((strWords[intRow][1] + "                                     ").substring(0,20));
			con.println("");
		}
	}
	//Bubble Sort Words
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
	//Bubble Sort Letters
	public static String[][] sortLetters(String strWords[][], int intWords){
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
					strTemp = strWords[intCounter+1][2];
					strWords[intCounter+1][2] = strWords[intCounter][2];
					strWords[intCounter][2] = strTemp;
				}
			}
		}
		
		return strWords;
	}
	//Hangman Screen
	public static void gameplayScreen(Console con){
		TextInputFile txtLetters = new TextInputFile("letters.txt");
		String strCheck;
		int intX = 550;
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		con.setDrawColor(Color.WHITE);
		con.fillRect(100, 600, 400, 15);
		con.fillRect(275, 125, 15, 475);
		con.fillRect(275, 125, 150, 15);
		con.fillRect(425, 125, 15, 50);
		Font fntSans = con.loadFont("CamingoCode-Bold.ttf", 30);
		con.setDrawFont(fntSans);
		while(txtLetters.eof()==false){
			strCheck = txtLetters.readLine();
			if(strCheck.equals(" ")){
				con.drawString("/", intX, 500);
				con.repaint();
			}else{
				con.drawString("__", intX, 500);
				con.repaint();
			}
			intX = intX + 65;
		}
		txtLetters.close();
	}
	//Winning Screen
	public static void winScreen(Console con){
		BufferedImage imgWin = con.loadImage("hangmanwin.jpg");
		con.drawImage(imgWin, 350, 160);
		con.repaint();
		con.sleep(3000);
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
	//Losing Screen
	public static void loseScreen(Console con, String strWordToGuess){
		BufferedImage imgWin = con.loadImage("hangmanlost.jpg");
		con.drawImage(imgWin, 350, 160);
		con.repaint();
		con.sleep(3000);
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		con.setDrawColor(Color.RED);
		Font fntCabin = con.loadFont("CabinSketch-Bold.ttf", 120);
		con.setDrawFont(fntCabin);
		con.drawString("You Lose", 375, 100);
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 60);
		con.setDrawFont(fntSans);
		con.drawString("The correct word was: ", 290, 250);
		con.drawString(strWordToGuess, 525, 300);
		con.setDrawColor(Color.WHITE);
		con.drawString("◆ Play Again", 440, 400);
		con.drawString("◆ Quit", 440, 500);
		con.repaint();
	}
	//Revealing letter
	public static void letterReveal(Console con, String strReveals[][], int intLength, int intWrong, String strWordToGuess){
		String strReveal;
		boolean blnReveal;
		int intCount;
		int intX;
		int intA;
		int intRevealed = 0;
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 60);
		con.setDrawFont(fntSans);
		strReveal = strReveals[intWrong][0];
		intX = Integer.parseInt(strReveals[intWrong][2]);
		intA = Integer.parseInt(strReveals[intWrong][1]);
		System.out.println(strReveal);
		System.out.println(intA);
		for(intCount = 0; intCount < intLength; intCount++){
			if(strReveal.equals(strWordToGuess.substring(intCount, intCount+1)) && intRevealed == 0){
				intRevealed = intRevealed + 1;
				con.setDrawColor(Color.WHITE);
				con.drawString(strReveal, intX, 455);
				con.repaint();
			}
		}
	}
	
	//Drawing body parts
	public static void drawWrong(Console con, int intRevealed){
		if(intRevealed == 1){
			con.setDrawColor(Color.WHITE);
			con.drawOval(373, 175, 120, 120);
			con.repaint();
		}else if(intRevealed == 2){
			con.setDrawColor(Color.WHITE);
			con.drawLine(433, 295, 433, 455);
			con.repaint();
		}else if(intRevealed == 3){
			con.setDrawColor(Color.WHITE);
			con.drawLine(433, 375, 483, 355);
			con.repaint();
		}else if(intRevealed == 4){
			con.setDrawColor(Color.WHITE);
			con.drawLine(433, 375, 383, 355);
			con.repaint();
		}else if(intRevealed == 5){
			con.setDrawColor(Color.WHITE);
			con.drawLine(433, 455, 483, 555);
			con.repaint();
		}else if(intRevealed == 6){
			con.setDrawColor(Color.WHITE);
			con.drawLine(433, 455, 383, 555);
			con.repaint();
		}
	}
	//Help Screen
	public static void helpScreen(Console con){
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		Font fntCabin = con.loadFont("CabinSketch-Bold.ttf", 60);
		con.setTextFont(fntCabin);
		con.println("How To Play:");
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setTextFont(fntSans);
		con.println("- You are trying to guess a secret word based on the topic you've chosen");
		con.println("- The number of blank underscores represents the number of letters in the word");
		con.println("- You are trying to guess the FULL word not just letters");
		con.println("- Each time a guess is wrong, a random letter will reveal helping you guess the word");
		con.println("- You get 6 guess, one head, one body, two legs, and two arms");
		con.println("- Good Luck and Have Fun Playing Hangman!");
	}
	//Add Theme
	public static void addTheme(Console con){
		String strTheme;
		String strThemeTxt;
		String strWord = "";
		con.setDrawColor(new Color(10, 21, 30));
		con.fillRect(0, 0, 1280, 720);
		con.clear();
		Font fntSans = con.loadFont("SourceSansPro-Black.ttf", 30);
		con.setTextFont(fntSans);
		TextOutputFile txtThemes = new TextOutputFile("themes.txt", true);
		con.println("What is the theme name?");
		strTheme = con.readLine();
		strThemeTxt = strTheme + ".txt";
		txtThemes.println(strThemeTxt);
		TextOutputFile txtNewTheme = new TextOutputFile(strThemeTxt, true);
		while(!strWord.equals("stop")){
			con.println("What word would you like to add?");
			strWord = con.readLine();
			if (strWord.equals("stop")){
				txtNewTheme.close();
			}else{
				txtNewTheme.println(strWord);
			}
		}
	}
	//Leaderboard Counter
	public static int countLeaderboard(){
		int intCount = 0;
		String strName;
		int intScore;
		TextInputFile txtLeaderboard = new TextInputFile("leaderboard.txt");
		while(txtLeaderboard.eof()==false){
			strName = txtLeaderboard.readLine();
			intScore = txtLeaderboard.readInt();
			intCount = intCount + 1;
		}
		txtLeaderboard.close();
		return intCount;
	}
	//Leaderboard in Array
	public static String[][] loadLeaderboard(int intCount){
		countLeaderboard();
		int intRow;
		int intCol = 0;
		TextInputFile txtLeaderboard = new TextInputFile("leaderboard.txt");
		String strLeaderboard[][] = new String[intCount][2];
		for(intRow = 0; intRow < intCount; intRow++){
			strLeaderboard[intRow][0] = txtLeaderboard.readLine();
			intCol = txtLeaderboard.readInt();
			strLeaderboard[intRow][1] = Integer.toString(intCol);
		}
		txtLeaderboard.close();
		return strLeaderboard;
	}
}
