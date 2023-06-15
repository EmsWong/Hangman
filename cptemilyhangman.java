//Hangman
//Emily Wong
//v#5
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
		boolean blnPlayAgain = true;
		String strName;
		String strWordToGuess;
		String strGuess = "";
		int intCorrect = 0;
		int intRight = 0;
		int intSpaces;
		int intLength;
		int intCount;
		int intCount2 = 0;
		int intCounter;
		int intWrong = 0;
		String strLetter;
		String strLetters[][];
		String strReveals[][];
		String strReveal;
		TextOutputFile txtLetters = new TextOutputFile("letters.txt");
		
		//Location Variables
		boolean blnRepeat = true;
		int intMouseXLoc = 0;
		int intMouseYLoc = 0;
		int intClick = 0;	
		int intMouseXLoc1 = 0;
		int intMouseYLoc1 = 0;
		int intClick1 = 0;
		
		//Leaderboard Variables
		String strLeaderboard[][];
		int intLeaderboard;
		
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
				//Getting theme
				con.println("Enter the theme name:");
				strTheme = con.readLine();
				strThemeTxt = strTheme+".txt";
				intWords = hangmantools.countWords(strThemeTxt);
				strWords = hangmantools.loadThemeWords(intWords, strThemeTxt);
				strWords = hangmantools.sortWords(strWords, intWords);
				while(blnPlayAgain == true){
					//Getting word to guess
					strWordToGuess = strWords[intCount2][0];
					System.out.println(strWordToGuess);
					intLength = strWordToGuess.length();
					//Getting individual letters
					for(intCount = 1; intCount <= intLength; intCount++){
						strLetter = strWordToGuess.substring(intCount-1, intCount);
						txtLetters.println(strLetter);
					}
					txtLetters.close();
					hangmantools.gameplayScreen(con);
					strLetters = hangmantools.loadWordLetters(intLength);
					hangmantools.headerGeneral(con, strName, strTheme);
					hangmantools.sortWords(strLetters, intLength);
					strReveals = hangmantools.noDoubleLetter(strLetters, intLength);
					//Guessing the word
					for(intCounter = 1; intCounter <= 6; intCounter++){
						if(intCorrect == 0){
							con.print("\n\nGuess: ");
							strGuess = con.readLine();
							if(strGuess.equals(strWordToGuess)){
								hangmantools.winScreen(con);
								intMouseXLoc1 = con.currentMouseX();
								intMouseYLoc1 = con.currentMouseY();
								intClick1 = con.currentMouseButton();
								if(intMouseXLoc1 >= 440 && intMouseXLoc1 <= 845 && intMouseYLoc1 >= 410 && intMouseYLoc1 <= 435 && intClick1 == 1){
									blnPlayAgain = true;
								}else if(intMouseXLoc1 >= 440 && intMouseXLoc1 <= 845 && intMouseYLoc1 >= 440 && intMouseYLoc1 <= 465 && intClick1 == 1){
									blnPlayAgain = false;
									con.closeConsole();
								}
								intCorrect++;
								intRight++;
							}else{
								hangmantools.letterReveal(con, strReveals, intLength, intWrong, strWordToGuess);
								intWrong = intWrong + 1;
								hangmantools.drawWrong(con, intWrong);
								con.clear();
								if(intWrong == 6){
									hangmantools.loseScreen(con, strWordToGuess);
									intMouseXLoc1 = con.currentMouseX();
									intMouseYLoc1 = con.currentMouseY();
									intClick1 = con.currentMouseButton();
									if(intMouseXLoc1 >= 440 && intMouseXLoc1 <= 845 && intMouseYLoc1 >= 410 && intMouseYLoc1 <= 435 && intClick1 == 1){
										blnPlayAgain = true;
									}else if(intMouseXLoc1 >= 440 && intMouseXLoc1 <= 845 && intMouseYLoc1 >= 440 && intMouseYLoc1 <= 465 && intClick1 == 1){
										blnPlayAgain = false;
										con.closeConsole();
									}
								}
							}
						}
					}
					intCount2++;
				}
				
			//Hover on Play Game
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 240 && intMouseYLoc <= 320){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Play Game", 440, 225);
				con.repaint();
			//Clicking Help
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 340 && intMouseYLoc <= 420 && intClick == 1){
				blnRepeat = false;
				hangmantools.helpScreen(con);
			//Hover on Help
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 340 && intMouseYLoc <= 420){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Help", 440, 325);
				con.repaint();
			//Clicking Leaderboard
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 440 && intMouseYLoc <= 520 && intClick == 1){
				blnRepeat = false;
				intLeaderboard = hangmantools.countLeaderboard();
				strLeaderboard = hangmantools.loadLeaderboard(intLeaderboard);
				hangmantools.printWords(strLeaderboard, intLeaderboard, con);
			//Hover on Leaderboard
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 440 && intMouseYLoc <= 520){
				con.setDrawColor(Color.RED);
				con.drawString("◆ Leaderboard", 440, 425);
				con.repaint();
			//Clicking Add Theme
			}else if(intMouseXLoc >= 420 && intMouseXLoc <= 845 && intMouseYLoc >= 540 && intMouseYLoc <= 620 && intClick == 1){
				blnRepeat = false;
				hangmantools.addTheme(con);
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
