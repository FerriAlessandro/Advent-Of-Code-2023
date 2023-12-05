import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day2 {


    static String path = "path-to-input-here";

    static final int RED_CUBES = 12, GREEN_CUBES = 13, BLUE_CUBES = 14;
    static final String red = "red", green = "green", blue = "blue";

    public static void readFile(ArrayList<String> input) throws FileNotFoundException {
        File file = new File(path);
        Scanner scanner = new Scanner(file);
        while(scanner.hasNext()){
            input.add(scanner.nextLine());
        }

    }

    public static void Part1(ArrayList<String> input){

        StringBuilder b, color;
        int i;
        int id;
        int sum = 0;
        boolean ok = true;
        for(String s : input){
            ok = true;
            b= new StringBuilder();
            i=0;
            while(s.charAt(i) != ' '){
                i++;
            }
            i++;

            while(s.charAt(i) >= '0' && s.charAt(i) <= '9'){
                b.append(s.charAt(i));
                i++;
            }
            id = Integer.parseInt(b.toString());
            color = new StringBuilder();
            b = new StringBuilder();
            while(i <= s.length() && ok){

                if(i == s.length() || s.charAt(i) == ',' || s.charAt(i) ==';'){
                    if((color.toString().equals(red) && Integer.parseInt(b.toString()) > RED_CUBES)
                    || (color.toString().equals(green) && Integer.parseInt(b.toString()) > GREEN_CUBES)
                    || (color.toString().equals(blue) && Integer.parseInt(b.toString()) > BLUE_CUBES)){
                        ok = false;
                    }

                    color = new StringBuilder();
                    b = new StringBuilder();
                    i++;
                }
                else{
                    while(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                        b.append(s.charAt(i));
                        i++;
                    }
                    i++;
                    while(i<s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                        color.append(s.charAt(i));
                        i++;
                    }
                    i--;
                }
                i++;

            }
            if(ok)
                sum+=id;


        }
        System.out.println(sum);


    }


    public static void Part2(ArrayList<String> input){

        StringBuilder b, color;
        int i;
        int maxRed, maxBlue, maxGreen;
        int id;
        int sum = 0;

        boolean foundColor = false, foundNumber = false;

        color = new StringBuilder();
        b = new StringBuilder();
        for(String s : input){

            i=0;
            maxRed=0;
            maxBlue=0;
            maxGreen=0;

            while(s.charAt(i)!= ':')
                i++;
            i+=2;

            while(i < s.length()){

                while(s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    b.append(s.charAt(i));
                    i++;
                    foundNumber = true;
                }
                if(foundNumber) {
                    i++;
                    foundNumber = false;
                }
                while(i<s.length() && s.charAt(i) >= 'a' && s.charAt(i) <= 'z') {
                    color.append(s.charAt(i));
                    i++;
                    foundColor = true;
                }
                if(foundColor){
                    if(color.toString().equals(red) && Integer.parseInt(b.toString()) > maxRed)
                        maxRed=Integer.parseInt(b.toString());
                    else if(color.toString().equals(green) && Integer.parseInt(b.toString()) > maxGreen)
                        maxGreen=Integer.parseInt(b.toString());
                    else if(color.toString().equals(blue) && Integer.parseInt(b.toString()) > maxBlue)
                        maxBlue=Integer.parseInt(b.toString());

                    foundColor=false;

                    color = new StringBuilder();
                    b = new StringBuilder();
                }
                i++;

            }
            sum+=(maxRed*maxGreen*maxBlue);


        }
        System.out.println(sum);


    }

    public static void main(String [] args) throws FileNotFoundException {
        ArrayList<String> input = new ArrayList<>();
        readFile(input);
        //Part1(input);
        Part2(input);

    }


}
