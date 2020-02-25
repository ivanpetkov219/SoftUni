import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;

public class DatingApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        int[] malesArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] femalesArr = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        ArrayDeque<Integer> malesStack = new ArrayDeque<>();

        ArrayDeque<Integer> femalesQueue = new ArrayDeque<>();

        for (int male : malesArr) {
            malesStack.push(male);
        }

        for (int female : femalesArr) {
            femalesQueue.offer(female);
        }

        int matches = 0;

        while (!malesStack.isEmpty() && !femalesQueue.isEmpty()) {

            int firstMale = malesStack.pop();

            if (firstMale <= 0){
                continue;
            }
            int firstFemale = femalesQueue.poll();

            if (firstFemale <= 0){
                malesStack.push(firstMale);
                continue;
            }

            if (firstFemale % 25 == 0){
                malesStack.push(firstMale);
                if(!femalesQueue.isEmpty()) {
                    femalesQueue.poll();
                    if(!femalesQueue.isEmpty()) {
                        firstFemale = femalesQueue.poll();
                    }else {break;}
                }else {break;}
            }
            if (firstMale % 25 == 0){
                femalesQueue.offer(firstFemale);
                if(!malesStack.isEmpty()) {
                    malesStack.pop();
                    if(!malesStack.isEmpty()) {
                        firstMale = malesStack.pop();
                    }else {break;}
                }else {break;}
            }
            if (firstFemale == firstMale){
                matches++;
            }else {
                firstMale -= 2;
                malesStack.push(firstMale);
            }

        }

        System.out.println(String.format("Matches: %d", matches));

        if (malesStack.isEmpty()){
            System.out.println("Males left: none");
        }else {
            System.out.print("Males left: ");
            String joined = String.join(", ", malesStack + "");

            System.out.println(joined.substring(1, joined.length() - 1));
        }

        if (femalesQueue.isEmpty()){
            System.out.println("Females left: none");
        }else {
            System.out.print("Females left: ");

            String joined = String.join(", ", femalesQueue + "");

            System.out.println(joined.substring(1, joined.length() - 1));
            }
        }
    }



