import java.util.ArrayList;
import java.util.Collections;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordSearchPuzzle {
    private char[][] puzzle ;
    private String puzzleAsString;
    private List<String> puzzleWords ;
    private int lengthAllWords;
    private int dimensions;
    private int currentWord;
    private int[][] indexClues;
    private char[] directionClues;

    public WordSearchPuzzle(List<String> userSpecifiedWords) {
        sortBiggest(userSpecifiedWords);
        convertToUpper(userSpecifiedWords);
        puzzleWords = userSpecifiedWords;
        lengthAllWords = 0;
        for(int i = 0; i < puzzleWords.size(); i++){
            lengthAllWords += puzzleWords.get(i).length();
        }
        dimensions = (int) (Math.ceil(Math.sqrt(lengthAllWords*2)));
        if(dimensions < puzzleWords.get(0).length()) {
            dimensions = puzzleWords.get(0).length();
            }
        generateWordSearchPuzzle();
    }

    public void convertToUpper(List<String> userSpecifiedWords) { // I made this method because I needed to convert all the elements of a given list of Strings to uppercase multiple times.
        for(int j = 0; j < userSpecifiedWords.size(); j++){
            userSpecifiedWords.set(j, userSpecifiedWords.get(j).toUpperCase());
        }
    }

    public  void sortBiggest(List<String> userSpecifiedWords){ // This method sorts the contents of a list of Strings by biggest to smallest. This is to help with the placement of words.
        for(int i = 0; i < userSpecifiedWords.size()-1; i++){
            for( int j = i+1; j < userSpecifiedWords.size(); j++){
                if(userSpecifiedWords.get(j).length() > userSpecifiedWords.get(i).length()){
                    Collections.swap(userSpecifiedWords, i, j);
                }
            }
        }
    }

    public WordSearchPuzzle(String wordFile, int wordCount,
                            int shortest, int longest){
        List<String> tempList = new ArrayList();
        puzzleWords = new ArrayList();
        try{
            File wordsFile = new File(wordFile);
            Scanner myReader = new Scanner(wordsFile);
            while(myReader.hasNextLine()){
                String temp = myReader.nextLine();
                if(temp.length() >= shortest && temp.length() <= longest){
                    tempList.add(temp);
                }
            }
            myReader.close();
            int random;
            for(int a = 0; a < wordCount; a++){
                random = (int) (Math.random()* tempList.size());
                puzzleWords.add(tempList.get(random));
            }
            sortBiggest(puzzleWords);
            convertToUpper(puzzleWords);
            lengthAllWords = 0;
            for(int i = 0; i < puzzleWords.size(); i++){
                lengthAllWords += puzzleWords.get(i).length();
            }
            dimensions = (int) (Math.ceil(Math.sqrt(lengthAllWords*2)));
            generateWordSearchPuzzle();
        }
        catch(FileNotFoundException e){
            System.out.println("Error");
            e.printStackTrace();
        }
    }

    public List<String> getWordSearchList(){
        return puzzleWords;
    }
    public char[][] getPuzzleAsGrid(){
        for(int a = 0; a < dimensions; a++){
            for(int b = 0; b < dimensions; b++){
            }
        }
        return puzzle;
    }

    public String getPuzzleAsString(){
        puzzleAsString = "";
        for(int a = 0; a < dimensions; a++){
            puzzleAsString = puzzleAsString + "\n";
            for(int b = 0; b < dimensions; b++){
                puzzleAsString = puzzleAsString + puzzle[a][b] + " ";
            }
        }
        return puzzleAsString;
    }

    public void showWordSearchPuzzle(boolean hide){
        Random r = new Random();
        for(int a = 0; a < dimensions; a++){
            System.out.println();
            for(int b = 0; b < dimensions; b++){
                if(puzzle[a][b] == 0){
                    puzzle[a][b] = (char)(r.nextInt(26) + 'A');
                }
                System.out.print(puzzle[a][b] + " ");
            }
        }
        System.out.print("\n\n" + "The words in order of their length (longest to shortest)" + (hide == false ? " showing their [row][col] positions and directions" : "...") + "\n");
        for(int i = 0; i < puzzleWords.size(); i++){
            System.out.print(puzzleWords.get(i));
            if (hide == false) {
                System.out.print("["+indexClues[i][0]+"]"+"[" +indexClues[i][1]+"]"+directionClues[i]);
            }
            System.out.println();
        }
    }
    private void generateWordSearchPuzzle(){
        puzzle = new char[dimensions][dimensions];
        indexClues = new int[puzzleWords.size()][2];
        directionClues = new char[puzzleWords.size()];
        boolean flag = false;
        System.out.println(" ");
        for(currentWord = 0 ; currentWord < puzzleWords.size(); currentWord++){
            if(flag == true){
                currentWord--;
                flag = false;
            }
            char[] tempChar = puzzleWords.get(currentWord).toCharArray();
            Random rand = new Random();
            int startRow = rand.nextInt(dimensions);
            int startCol = rand.nextInt(dimensions);
            int tries = (dimensions * dimensions) * 2; // The probability that a position will be found within this amount of tries is almost certain... if not it will repeat for a different direction
            int direction = rand.nextInt(4);
            if( direction == 0) { // I chose to randomise the directions to make the word search more interesting & difficult
                int count = 0;
                while (count < tries) {

                    if (horizontalCheck(startRow, startCol, tempChar)) {
                        horizontal(startRow, startCol, tempChar);
                        indexClues[currentWord][0] = startRow;
                        indexClues[currentWord][1] = startCol;
                        directionClues[currentWord] = 'R';
                        count = tries;
                    }
                    else{
                        startRow = rand.nextInt(dimensions);
                        startCol = rand.nextInt(dimensions);
                        count++;
                    }
                }
            }
            else if( direction == 1){
                    int count = 0;
                    while(count < tries) {
                        if (verticalCheck(startRow,startCol,tempChar)) {
                            vertical(startRow, startCol, tempChar);
                            indexClues[currentWord][0] = startRow;
                            indexClues[currentWord][1] = startCol;
                            directionClues[currentWord] = 'D';
                            count = tries;
                        }
                        else {
                            startRow = rand.nextInt(dimensions);
                            startCol = rand.nextInt(dimensions);
                            count++;
                        }
                    }
            }
            else if( direction == 2){
                int count = 0;
                int index = 0;
                char[] reverseTemp = new char[tempChar.length];
                for(int i = tempChar.length-1 ; i >= 0; i--) {
                    reverseTemp[index] = tempChar[i];
                    index++;
                }
                while(count < tries) {
                    if (horizontalCheck(startRow, startCol, reverseTemp)) {
                        horizontal(startRow, startCol, reverseTemp);
                        indexClues[currentWord][0] = startRow;
                        indexClues[currentWord][1] = startCol + (reverseTemp.length-1);
                        directionClues[currentWord] = 'L';
                        count = tries;
                    } else {
                        startRow = rand.nextInt(dimensions);
                        startCol = rand.nextInt(dimensions);
                        count++;
                    }
                    }
                }
            else if( direction == 3){
                int count = 0;
                int index = 0;
                char[] reverseTemp = new char[tempChar.length];
                for(int i = tempChar.length-1 ; i >= 0; i--) {
                    reverseTemp[index] = tempChar[i];
                    index++;
                }
                while(count < tries) {
                    if(verticalCheck(startRow,startCol,reverseTemp)){
                        vertical(startRow,startCol,reverseTemp);
                        indexClues[currentWord][0] = startRow + (reverseTemp.length-1);
                        indexClues[currentWord][1] = startCol;
                        directionClues[currentWord] = 'U';
                        count = tries;
                    } else {
                        startRow = rand.nextInt(dimensions);
                        startCol = rand.nextInt(dimensions);
                        count++;
                    }
                }
            }
            else{
                flag = true; // I'm using a flag system just in case it doesn't find an appropriate position to place the word. (This is very unlikely but is a safety mechanism)
            }
        }
    }
    public boolean horizontalCheck(int startRow, int startCol, char[] tempChar){ // This a method that I use to check if there is enough space to place a word horizontally AND if there is not a word/letter occupying that range of space.
        int spacesAvail = (dimensions) - startCol;
        int range = startCol + tempChar.length ;
        if(spacesAvail >= tempChar.length){
            int check;
            for(check = startCol; check < range; check++){
                if(puzzle[startRow][check] != 0){
                    return false;
                }
            }
            if(check == range ){
                return true;
            }
        }
        return false;
    }

    public void horizontal(int startRow, int startCol, char[] tempChar){ // This actually deploys the placing of the word horizontally.
        int spacesAvail = (dimensions) - startCol;
        int range = startRow + tempChar.length;
        if(spacesAvail >= tempChar.length){
            int j = 0;
            for(int k = startCol; k <= range ; k++){
                if(j != tempChar.length){
                    puzzle[startRow][k] = tempChar[j];
                    j++;
                }
            }
        }
    }

    public boolean verticalCheck(int startRow, int startCol, char[] tempChar){ // This a method that I use to check if there is enough space to place a word vertically AND if there is not a word/letter occupying that range of space.
        int spacesAvail = (dimensions) - startRow;
        int range = startRow + tempChar.length ;
        if(spacesAvail >= tempChar.length){
            int check;
            for(check = startRow; check < range; check++){
                if(puzzle[check][startCol] != 0){
                    return false;
                }
            }
            if(check == range ){
                return true;
            }
        }
        return false;
    }

    public void vertical(int startRow, int startCol, char[] tempChar){ // This actually deploys the placing of the word vertically.
        int spacesAvail = (dimensions) - startRow;
        int range = startRow + tempChar.length;
        if(spacesAvail >= tempChar.length){
            int j = 0;
            for(int k = startRow; k < range ; k++){
                if(j != tempChar.length){
                    puzzle[k][startCol] = tempChar[j];
                    j++;
                }
            }
        }
    }
}





