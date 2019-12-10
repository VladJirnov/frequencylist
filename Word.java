import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Word {
    HashMap<String, Integer> words;

    Word(String file) {
        words = countWords(file);

    }

    // prints unique words in order from largest to smallest based on the amount they appear in the txt file
    void printWords() {
        PriorityQueue<Map.Entry<String, Integer>> pq = createPriorityQueue();

        while (!pq.isEmpty()) {
            Map.Entry<String, Integer> head = pq.poll();
            System.out.println(head.getValue() + ": " + head.getKey());
        }

    }

    //creates and returns a priority queue ordered based on the value of each unique word
    PriorityQueue<Map.Entry<String, Integer>> createPriorityQueue() {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(words.size(), (o1, o2) -> o2.getValue() - o1.getValue());
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            pq.add(entry);
        }
        return pq;
    }

    HashMap<String, Integer> countWords(String file) {
        HashMap<String, Integer> w = new HashMap<>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(file));
            while (scanner.hasNextLine()) {

                String wordLine = scanner.nextLine();
                String wordTemp = "";
                for (int i = 0; i < wordLine.length(); i++) {
                    char previous = '[';
                    if (i != 0) {
                        previous = wordLine.charAt(i - 1);
                    }
                    if (wordLine.charAt(i) >= 'a' && wordLine.charAt(i) <= 'z' ||
                            wordLine.charAt(i) >= 'A' && wordLine.charAt(i) <= 'Z' ||
                            wordLine.charAt(i) == ' ' && previous != ' ') {
                        wordTemp += wordLine.charAt(i) + "";
                    }
                }
                wordLine = wordTemp;


                String[] wordArr = wordLine.split(" ");

                for (String word : wordArr) {
                    if (!w.containsKey(word)) {
                        w.put(word, 1);
                    } else {
                        w.put(word, w.get(word) + 1);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
        return w;

    }

    public static void main(String[] args) {
        Word w = new Word("C:\\Users\\M16\\Desktop\\sort.txt");
        w.printWords();
    }
}