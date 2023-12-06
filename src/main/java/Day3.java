import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day3 {

    static final Set<Character> symbols = new HashSet<Character>(Arrays.asList('#', '$', '%', '&', '*', '+', '-', '/', '=', '@'));

    static String path = "C:/Users/alefe/OneDrive/Desktop/Coding/Advent-Of-Code-2023/src/main/resources/input3.txt";

    public static void readFile(ArrayList<String> input) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            input.add(scanner.nextLine());
        }

    }

    public static boolean isDigit(char c){
        return (c>='0' && c<='9');
    }

    public static void part1(ArrayList<String> input){
        int i = 0, j=0, sum=0;
        StringBuilder number = new StringBuilder();
        boolean foundSymbol = false;
        for(i=0; i<input.size(); i++) {
            String s = input.get(i);
            j=0;
            while (j < s.length()) {

                while (j<s.length() && (s.charAt(j) < '0' || s.charAt(j) > '9')) {
                    j++;
                }

                while(j < s.length() && s.charAt(j) >= '0' && s.charAt(j) <= '9') {

                    if (i != 0) {
                        if (symbols.contains(input.get(i - 1).charAt(j))) {
                            foundSymbol = true;
                        }
                        if(j!=0){
                            if(symbols.contains(input.get(i - 1).charAt(j - 1)))
                                foundSymbol=true;
                        }
                        if(j!=s.length()-1){
                            if(symbols.contains(input.get(i-1).charAt(j+1)))
                                foundSymbol = true;
                        }

                    }
                    if(j!=0) {
                        if (symbols.contains(input.get(i).charAt(j - 1))) {
                            foundSymbol = true;
                        }
                    }
                    if(j!=s.length()-1){
                        if(symbols.contains(input.get(i).charAt(j+1)))
                            foundSymbol = true;
                    }

                    if (i != s.length() - 1) {
                        if (symbols.contains(input.get(i + 1).charAt(j))) {
                            foundSymbol = true;
                        }
                        if(j!=0){
                            if(symbols.contains(input.get(i + 1).charAt(j - 1)))
                                foundSymbol=true;
                        }
                        if(j!=s.length()-1){
                            if(symbols.contains(input.get(i+1).charAt(j+1)))
                                foundSymbol = true;
                        }

                    }
                    number.append(s.charAt(j));
                    j++;

                    if(foundSymbol){
                        while(j<s.length() && s.charAt(j)>='0' && s.charAt(j)<='9') {
                            number.append(s.charAt(j));
                            j++;
                        }
                        sum+=Integer.parseInt(number.toString());
                    }

                }
                foundSymbol = false;
                number = new StringBuilder();

            }
        }
        System.out.println(sum);

    }

    public static void part2(ArrayList<String> input){
        int i = 0, j=0, sum=0, num_adjacent = 0, k;
        StringBuilder number = new StringBuilder();
        ArrayList<String> adjacents = new ArrayList<>();
        boolean foundSymbol = false;
        for(i=0; i<input.size(); i++) {
            String s = input.get(i);
            j = 0;
            while (j < s.length()) {

                while (j < s.length() && s.charAt(j) != '*') {
                    j++;
                }

                num_adjacent = 0;
                if(j<s.length()) {
                    if (i != 0) {
                        if (j != 0) {
                            if (input.get(i - 1).charAt(j - 1) >= '0' && input.get(i - 1).charAt(j - 1) <= '9') {
                                k = j - 1;
                                while (k > 0 && input.get(i - 1).charAt(k - 1) >= '0' && input.get(i - 1).charAt(k - 1) <= '9')
                                    k--;
                                while (k < s.length() && input.get(i - 1).charAt(k) >= '0' && input.get(i - 1).charAt(k) <= '9') {
                                    number.append(input.get(i - 1).charAt(k));
                                    k++;
                                }
                                num_adjacent++;
                                adjacents.add(number.toString());
                                number = new StringBuilder();
                            }
                        }
                        if (input.get(i - 1).charAt(j) >= '0' && input.get(i - 1).charAt(j) <= '9') {
                            if (j == 0 || (j > 0 && !isDigit(input.get(i - 1).charAt(j - 1)))) {
                                k = j;
                                while (isDigit(input.get(i - 1).charAt(k))) {
                                    number.append(input.get(i - 1).charAt(k));
                                    k++;
                                }
                                num_adjacent++;
                                adjacents.add(number.toString());
                                number = new StringBuilder();
                            }
                        }
                        if (j < s.length() - 1 && isDigit(input.get(i - 1).charAt(j + 1)) && !isDigit(input.get(i - 1).charAt(j))) {
                            k = j + 1;
                            while (k < s.length() && isDigit(input.get(i - 1).charAt(k))) {
                                number.append(input.get(i - 1).charAt(k));
                                k++;
                            }
                            num_adjacent++;
                            adjacents.add(number.toString());
                            number = new StringBuilder();

                        }
                    }

                    if (j != 0 && num_adjacent < 2) {
                        if (isDigit(input.get(i).charAt(j - 1))) {
                            k = j - 1;
                            while (k > 0 && isDigit(input.get(i).charAt(k - 1)))
                                k--;
                            while (k < s.length() && isDigit(input.get(i).charAt(k))) {
                                number.append(input.get(i).charAt(k));
                                k++;
                            }
                            num_adjacent++;
                            adjacents.add(number.toString());
                            number = new StringBuilder();
                        }
                    }
                    if (j < s.length() - 1 && num_adjacent < 2) {
                        if (isDigit(input.get(i).charAt(j + 1))) {
                            k = j + 1;
                            while (k < s.length() && isDigit(input.get(i).charAt(k))) {
                                number.append(input.get(i).charAt(k));
                                k++;
                            }
                            num_adjacent++;
                            adjacents.add(number.toString());
                            number = new StringBuilder();
                        }
                    }

                    if (i < input.size() - 1 && num_adjacent < 2) {
                        if (j != 0) {
                            if (input.get(i + 1).charAt(j - 1) >= '0' && input.get(i + 1).charAt(j - 1) <= '9') {
                                k = j - 1;
                                while (k > 0 && input.get(i + 1).charAt(k - 1) >= '0' && input.get(i + 1).charAt(k - 1) <= '9')
                                    k--;
                                while (k < s.length() && input.get(i + 1).charAt(k) >= '0' && input.get(i + 1).charAt(k) <= '9') {
                                    number.append(input.get(i + 1).charAt(k));
                                    k++;
                                }
                                num_adjacent++;
                                adjacents.add(number.toString());
                                number = new StringBuilder();
                            }
                        }
                        if (input.get(i + 1).charAt(j) >= '0' && input.get(i + 1).charAt(j) <= '9') {
                            if (j == 0 || (j > 0 && !isDigit(input.get(i + 1).charAt(j - 1)))) {
                                k = j;
                                while (isDigit(input.get(i + 1).charAt(k))) {
                                    number.append(input.get(i + 1).charAt(k));
                                    k++;
                                }
                                num_adjacent++;
                                adjacents.add(number.toString());
                                number = new StringBuilder();
                            }
                        }
                        if (j < s.length() - 1 && isDigit(input.get(i + 1).charAt(j + 1)) && !isDigit(input.get(i + 1).charAt(j))) {
                            k = j + 1;
                            while (k < s.length() && isDigit(input.get(i + 1).charAt(k))) {
                                number.append(input.get(i + 1).charAt(k));
                                k++;
                            }
                            num_adjacent++;
                            adjacents.add(number.toString());
                            number = new StringBuilder();

                        }
                    }
                }
                if (num_adjacent == 2) {
                    sum += (Integer.parseInt(adjacents.get(0)) * Integer.parseInt(adjacents.get(1)));
                }
                adjacents = new ArrayList<>();
                num_adjacent = 0;
                j++;
            }
        }
        System.out.println(sum);
    }

    public static void main(String [] args) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        readFile(input);
        //part1(input);
        part2(input);
    }

}
