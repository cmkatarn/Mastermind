package MastermindGame;

import java.util.Scanner;

/**
 * This class is a version of Mastermind, a classic game of
 * combination deduction.
 *
 * @author malloneec
 * @since 8/8/16
 */
public class Mastermind {
    public static void main(String[] args){
        Game myGame = new Game();
        myGame.begin();
    }

    private static class Game {
        private char[] sequence;
        private int turnsTaken;
        private Scanner reader = new Scanner(System.in);
        boolean correctCombinationNotFound;

        private Game(){
            turnsTaken = 1;
            sequence = new char[4];
            correctCombinationNotFound = true;
            generateCombination();
        }

        private void generateCombination(){
            for(int i=0 ; i<4; i++){
                int color = (int)(Math.random()*4.0);
                switch(color){
                    case 0:
                        sequence[i] = 'R';
                        break;
                    case 1:
                        sequence[i] = 'G';
                        break;
                    case 2:
                        sequence[i] = 'B';
                        break;
                    case 3:
                        sequence[i] = 'Y';
                        break;
                    default:
                        sequence[i] = 'R';
                }
            }
        }

        public void begin(){
            while(correctCombinationNotFound){
                requestGuess();
            }
        }

        /**
         * No error-checking for correct "colors".
         */
        private void requestGuess(){
            String guess = "";
            while(guess.length()!=4){
                System.out.println("[Turn# "+ turnsTaken + "]\tPlease enter a guess (XXXX from RGBY): ");
                guess = reader.next();
                if(guess.length()!=4){
                    System.out.println("ERROR: Supplied guess is in incorrect format!");
                }else{
                    checkAgainstSolution(guess);
                }
            }
            turnsTaken++;

        }

        private void checkAgainstSolution(String guess){
            String fixed = guess.toUpperCase();
            int matches = 0;
            int misses = 0;
            for(int i = 0; i<4; i++){
                if(fixed.charAt(i)==sequence[i]){
                    matches++;
                }else{
                    for(int j = 0; j<=i; j++) {
                        if(sequence[j]==fixed.charAt(i) && j!=i) {
                            misses++;
                        }
                    }
                }
            }
            if(matches==4){
                correctCombinationNotFound = false;
                System.out.println("You found the correct combination on turn " + turnsTaken + "!!! Congratulations!");
            }else {
                System.out.println("There were " + matches + " matching positions, and " + misses + " misses.");
            }
        }
    }
}
