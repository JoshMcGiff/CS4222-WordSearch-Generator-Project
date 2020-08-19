import java.util.Arrays;


public class WordSearchPuzzleDriver {
    public static void main(String[] args) {
        InstanceA();
        System.out.print("\n");
        InstanceB();
        System.out.print("\n");
        InstanceC();
        System.out.print("\n");
        InstanceGaeilge();
    }

    public static void InstanceA() {
        WordSearchPuzzle newPuzzle = new WordSearchPuzzle("BasicEnglish.txt", 10, 3, 10);
        System.out.println("Instance A:");
        System.out.println("The list of words to be used in the word search:");
        System.out.println(newPuzzle.getWordSearchList());
        System.out.println("\nThe word search puzzle:");
        newPuzzle.showWordSearchPuzzle(true);
        System.out.println("\nThe puzzle as a String:");
        System.out.println(newPuzzle.getPuzzleAsString());
        System.out.println("\nThe puzzle as a 2D array of characters:");
        for(int i = 0; i < newPuzzle.getPuzzleAsGrid().length; i++){
                System.out.println();
            for(int j = 0; j < newPuzzle.getPuzzleAsGrid().length; j++){
                System.out.print(newPuzzle.getPuzzleAsGrid()[i][j] + " ");
            }
        }
    }

    public static void InstanceB() {
        WordSearchPuzzle  newPuzzle = new WordSearchPuzzle(Arrays.asList(new String[]{"clean", "bandit", "best", "band", "world","love", "their", "song", "symphony"})); //"opqrst", "jklmn", "fghi", "cde","ab"
        System.out.println("Instance B:");
        System.out.println("The list of words to be used in the word search:");
        System.out.println(newPuzzle.getWordSearchList());
        System.out.println("\nThe word search puzzle:");
        newPuzzle.showWordSearchPuzzle(true);
        System.out.println("\nThe puzzle as a String:");
        System.out.println(newPuzzle.getPuzzleAsString());
        System.out.println("\nThe puzzle as a 2D array of characters:");
        for(int i = 0; i < newPuzzle.getPuzzleAsGrid().length; i++){
            System.out.println();
            for(int j = 0; j < newPuzzle.getPuzzleAsGrid().length; j++){
                System.out.print(newPuzzle.getPuzzleAsGrid()[i][j] + " ");
            }
        }
    }

    public static void InstanceC() {
        WordSearchPuzzle newPuzzle = new WordSearchPuzzle("BNCwords.txt", 10, 3, 7);
        System.out.println("Instance C:");
        System.out.println("The list of words to be used in the word search:");
        System.out.println(newPuzzle.getWordSearchList());
        System.out.println("\nThe word search puzzle:");
        newPuzzle.showWordSearchPuzzle(false);
        System.out.println("\nThe puzzle as a String:");
        System.out.println(newPuzzle.getPuzzleAsString());
        System.out.println("\nThe puzzle as a 2D array of characters:");
        for(int i = 0; i < newPuzzle.getPuzzleAsGrid().length; i++){
            System.out.println();
            for(int j = 0; j < newPuzzle.getPuzzleAsGrid().length; j++){
                System.out.print(newPuzzle.getPuzzleAsGrid()[i][j] + " ");
            }
        }
    }

    public static void InstanceGaeilge() {
        WordSearchPuzzle  newPuzzle = new WordSearchPuzzle(Arrays.asList(new String[]{"glan", "inniu", "luimneach", "teanga", "aer","mata", "tireolas", "feic", "muinteoir"})); //"opqrst", "jklmn", "fghi", "cde","ab"
        System.out.println("Instance Gaeilge:");
        System.out.println("An liosta d'fhocal a mbeadh in úsáid in san cuardach focal:");
        System.out.println(newPuzzle.getWordSearchList());
        System.out.println("\nAn cuardach focal:");
        newPuzzle.showWordSearchPuzzle(false);
        System.out.println("\nAn puzal mar String:");
        System.out.println(newPuzzle.getPuzzleAsString());
        System.out.println("\nAn puzal mar array de charactair:");
        for(int i = 0; i < newPuzzle.getPuzzleAsGrid().length; i++){
            System.out.println();
            for(int j = 0; j < newPuzzle.getPuzzleAsGrid().length; j++){
                System.out.print(newPuzzle.getPuzzleAsGrid()[i][j] + " ");
            }
        }
    }
}
