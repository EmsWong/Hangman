//Hangman
//Emily Wong
//v#4
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
		//Gameplay
		String strName;
		String strWordToGuess;
		String strGuess;
		int intLength;
		int intCount;
		String strLetter;
		String strLetters[][];
		TextOutputFile txtLetters = new TextOutputFile("letters.txt");
		//Location Variables
		boolean blnRepeat = true;
		int intMouseXLoc = 0;
		int intMouseYLoc = 0;
		int intClick = 0;		
		//Leaderboard Variables
		String strNumofScores = "";
		String strLeaderboard[][];
		int intCount2;
		int intRow;
		int intCol;
		TextOutputFile txtScore = new TextOutputFile("leaderboard.txt");
		TextInputFile txtLeaderboard = new TextInputFile("leaderboard.txt");
		
		//Showing main screen when program is opened
		hangmantools.mainmenubg(con);
		
		//Hovering on options
		while(blnRepeat == true){
			intMouseXLoc = con.currentMouseX();
			intMouseYLoc = con.currentMouseY();
			intClick = con.currentMouseButton();
			//Clicking Play Game
			if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 240 && intMouseYLoc <= 320 && intClick == 1){
				blnRepeat = false;
				strName = hangmantools.name(con);
				con.sleep(300);
				hangmantools.themes(con);
				hangmantools.headerName(con, strName);
				con.println("Enter the theme name:");
				strTheme = con.readLine();
				strThemeTxt = strTheme+".txt";
				intWords = hangmantools.countWords(strThemeTxt);
				strWords = hangmantools.loadThemeWords(intWords, strThemeTxt);
				strWords = hangmantools.sortWords(strWords, intWords);
				strWordToGuess = strWords[0][0];
				intLength = strWordToGuess.length();
				hangmantools.gameplayScreen(con, intLength);
				hangmantools.headerGeneral(con, strName, strTheme);
				con.print("\n\nGuess: ");
				strGuess = con.readLine();
				if(strGuess.equals(strWordToGuess)){
					hangmantools.winScreen(con);
				}else{
					while(!strGuess.equals(strWordToGuess)){
						for(intCount = 1; intCount <= intLength; intCount++){
							strLetter = strWordToGuess.substring(intCount-1, intCount);
							txtLetters.println(strLetter);
						}
						txtLetters.close();
						strLetters = hangmantools.loadWordLetters(intLength);
						hangmantools.headerGeneral(con, strName, strTheme);
					}
				}
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
}
