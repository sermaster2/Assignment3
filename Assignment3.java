import java.util.Scanner;
import java.util.*;
import java.io.*;

public class Assignment3 {

    public static void main(String []args) throws IOException{
        String playerNameFirst;
        String playerNameLast;
        String decision;

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your first name: ");
        playerNameFirst = sc.nextLine();

        File file = new File(playerNameFirst);
        if (file.exists()) { 
            Scanner inputFile = new Scanner(file);
            while (inputFile.hasNext()) {
                String data = inputFile.nextLine();
                System.out.println(data);
            }
            inputFile.close();
        }
        if (!file.exists())

            System.out.println("Enter your last name: ");
            playerNameLast = sc.nextLine();

            Dice dice = new Dice();

            Player player = new Player(playerNameFirst + " " + playerNameLast);
            boolean good = false;

            player.newPerson();

        do{
            System.out.println("Would you like to play a round (y/n) > ");
            decision = sc.nextLine();
            switch (decision){
            case "y":
                player.atZero();
                player.addRound();
                System.out.println("---------------------------------");
                System.out.printf("Now playing round %d.\n", player.getRound());

                player.makeBet();

                dice.rollDice();

                player.makeGuess();

                roundResults(dice, player);
                break;
            case "n":
                good = true;
                break;
            }
        }
        while(!good);

        displayTotalResults(player);

        PrintWriter outputFile = new PrintWriter(playerNameFirst);
        outputFile.println("Welcome back! Here is where you left off last time:");
        outputFile.println("Your name: " + player.getName());
        outputFile.printf("Money left: $%.2f\n", player.getCash());
        outputFile.printf("Rounds played: %d\n", player.getRound());
        outputFile.printf("Rounds won: %d\n", player.getWon());
        outputFile.close();

    }

    public static void roundResults(Dice dice, Player player) {
        System.out.printf("The dice rolled %d and %d.\n", dice.getDie1Value(), dice.getDie2Value());
        System.out.printf("The total is %d.\n", (dice.getDie1Value() + dice.getDie2Value()));
        System.out.printf("Result: %s\n", dice.getOverorUnder());
        checkGuess(player, dice);
    }

    public static void checkGuess(Player player, Dice dice) {
        String guess = player.getGuess();
        String overOrUnderResult = dice.getOverorUnder();

        System.out.printf("The player %s guessed %s.\n", player.getName(), player.getGuess());

        if (guess.equalsIgnoreCase(overOrUnderResult)) {
            player.addCash();
            System.out.println("You WON this round.");
            player.roundsWon();
            System.out.printf("Awarding $%.2f to %s.\n", player.getAddBet(), player.getName());
            System.out.printf("Your current balance $%.2f.\n", player.getCash());
        }
        else {
            player.subtractCash();
            System.out.println("You LOST this round.");
            System.out.printf("Subtracting $%.2f from %s.\n", player.getSubBet(), player.getName());
            System.out.printf("Your current balance $%.2f.\n", player.getCash());
        }

    }

    public static void displayTotalResults(Player player) {
        System.out.println("-----------------------------------");
        System.out.println("Thank you for playing Over and Under!");
        System.out.println("Here is your updated information:");
        System.out.println("Your name: " + player.getName());
        System.out.printf("Money left: $%.2f\n", player.getCash());
        System.out.printf("Rounds played: %d\n", player.getRound());
        System.out.printf("Rounds won: %d\n", player.getWon());

    }

}      