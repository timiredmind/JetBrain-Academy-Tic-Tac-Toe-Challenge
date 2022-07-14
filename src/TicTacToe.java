import java.util.Arrays;
import java.util.Scanner;



public class TicTacToe {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] gameArray = new char[3][3];
        for (char[] array: gameArray) {
            Arrays.fill(array, 0, 3, '_');
        }
        char player = 'X';
        printBoard(gameArray);
        while (true) {
            int coordinate1;
            int coordinate2;
            while (true) {
                try {
                    System.out.print("Enter the coordinates: ");
                    String userInput1 = scanner.next();
                    String userInput2 = scanner.nextLine().strip();
                    coordinate1 = Integer.parseInt(userInput1);
                    coordinate2 = Integer.parseInt(userInput2);
                } catch (NumberFormatException e) {
                    System.out.println("You should enter numbers!");
                    continue;
                }

                if ((coordinate1 < 1 || coordinate1 > 3) || (coordinate2 < 1 || coordinate2 > 3)) {
                    System.out.println("Coordinates should be from 1 to 3!");
                    continue;
                }

                if (verifyCoordinate(coordinate1, coordinate2, gameArray)) {
                    assignElementToCoordinate(coordinate1, coordinate2, gameArray, player);
                    printBoard(gameArray);
                    break;
                } else System.out.println("This cell is occupied! Choose another one!");
            }
            if (analyzeGameState(gameArray).equals("X")) {
                System.out.println("X wins");
                break;
            } else if (analyzeGameState(gameArray).equals("O")) {
                System.out.println("O wins");
                break;
            } else if (analyzeGameState(gameArray).equals("draw")) {
                System.out.println("Draw");
                break;
            }
            if (player == 'X') player = 'O';
            else player = 'X';
        }


    }


    public static void printBoard(char[][] userInput) {
        System.out.println("---------");
        for (char[] array: userInput){
            System.out.print("| ");
            for (char element : array) {
                if (element == '_') element = ' ';
                System.out.print(element + " ");
            }
            System.out.println("|");
        }

        System.out.println("---------");

    }


    public static String analyzeGameState(char[][] userInput) {
        //Check for equal Horizontal Row
        if (checkForEqualHorizontalRow(userInput) == 'X') return "X";
        else if (checkForEqualHorizontalRow(userInput) == 'O') return "O";

        // Check if 3 vertical  rows are equal
        if (checkForEqualVerticalRows(userInput) == 'X') return "X";
        else if (checkForEqualVerticalRows(userInput) == 'O') return "O";

        //Check if diagonal rows are equal
        if (checkForEqualDiagonalRows(userInput)  == 'X') return "X";
        else if (checkForEqualDiagonalRows(userInput) == 'O') return "O";

        //Check if there are any empty cells
        for (char[] array : userInput) {
            for (char arrayElement : array) {
                if (arrayElement == '_') {
                    return "incomplete";
                }
            }
        }
        return "draw";
    }
    public static boolean verifyCoordinate(int cor1, int cor2, char[][] array) {
        return array[cor1 - 1][cor2 - 1] == '_';
    }

    public static void assignElementToCoordinate(int cor1, int cor2, char[][] array, char element){
        array[cor1 - 1][cor2 - 1] = element;
    }

    public static char checkForEqualHorizontalRow(char[][] array) {

        for (int i = 0; i < 3; i++) {
            if (array[i][0] == array[i][1] && array[i][1] == array[i][2]) return array[i][1];
        }
        return ' ';
    }

    public static char checkForEqualVerticalRows(char[][] array) {
        for (int i = 0; i < 3;  i++) {
            if (array[0][i] == array[1][i] && array[1][i] == array[2][i]) return array[1][i];
        }
        return ' ';
    }

    public static char checkForEqualDiagonalRows(char[][] array) {
        if ((array[0][0] == array[1][1] && array[1][1] == array[2][2]) || (array[0][2] == array[1][1] && array[1][1] == array[2][0])) {
            return array[1][1];
        }
        return ' ';
    }

}