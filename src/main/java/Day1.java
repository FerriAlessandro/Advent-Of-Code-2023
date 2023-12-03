import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day1 {

    static String path = "path-to-your-input-file";

    public static void readFile(ArrayList<String> input) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            input.add(scanner.nextLine());
        }

    }

    public static int parseString(String s){
        ArrayList<String> digits = new ArrayList<>();

        digits.add("one");
        digits.add("two");
        digits.add("three");
        digits.add("four");
        digits.add("five");
        digits.add("six");
        digits.add("seven");
        digits.add("eight");
        digits.add("nine");

        StringBuilder b = new StringBuilder();
        StringBuilder res = new StringBuilder();
        int len = s.length();
        int i;
        int count=0;
        char c;
        boolean first = true;
        for(i=0; i<len && first; i++) {

            c = s.charAt(i);
            if (c >= '0' && c <= '9') {
                res.append(c);
                first = false;
            } else {

                for (String digit : digits) {
                    int j = i;
                    if (len - j >= digit.length() && first) {
                        count = 0;
                        boolean match = true;
                        while (count < digit.length() && match) {
                            if (digit.charAt(count) == s.charAt(j)) {
                                count++;
                                j++;
                            } else match = false;
                        }
                        if (match) {
                            first = false;
                            res.append(digits.indexOf(digit) + 1);
                        }

                    }
                }
            }
        }

        first = true;

        for(i=len-1; i>=0 && first; i--){

            c = s.charAt(i);
            if(c >= '0' && c <= '9'){
                res.append(c);
                first = false;
            }
            else{

                for(String digit : digits) {
                    int j = i;
                    if (j >= digit.length() && first) {
                        count = digit.length()-1;
                        boolean match = true;
                        while (count >= 0 && match) {
                            if (digit.charAt(count) == s.charAt(j)) {
                                count--;
                                j--;
                            }
                            else match = false;
                        }
                        if (match) {
                            first = false;
                            res.append(digits.indexOf(digit) + 1);
                        }

                    }
                }
            }
        }


        return Integer.parseInt(res.toString());
    }

    public static void part1(ArrayList<String> input){
        StringBuilder tmp;
        int sum = 0;
        int i = 0;
        char c;
        char digit;
        for(String s : input){
            int len = s.length();
            boolean first = true;
            tmp = new StringBuilder();
            digit = 'a';
            for(i = 0; i < len; i++){
                c = s.charAt(i);
                if(c >= '0' && c <= '9'){
                    if(first) {
                        tmp.append(c);
                        first = false;
                    }
                    else {
                        digit = c;
                    }
                }
            }
            if(digit != 'a')
                tmp.append(digit);
            else
                tmp.append(tmp.charAt(0));
            sum += Integer.parseInt(tmp.toString());

        }
        System.out.print(sum);
    }

    public static void part2(ArrayList<String> input){

        int sum =0;
        for(String s : input){
            sum+= parseString(s);

        }
        System.out.println(sum);



    }


    public static void main(String[] args)  {

        ArrayList<String> input = new ArrayList<>();
        try {
            readFile(input);
        }catch(Exception e){
            e.printStackTrace();
        }
        part2(input);


    }
}
