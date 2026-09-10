import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length != 1){
            System.out.println("Ошибка:укажите путь к файлу с элементами массива");
            return;
        }
        String path = args[0];
        ArrayList<Integer> nums = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new FileReader(path))) {
            String line;

            while ((line = in.readLine()) != null){
                if(!line.isEmpty()){
                    nums.add(Integer.parseInt(line));
                }
            }
        }

        Collections.sort(nums);
        int median = nums.get(nums.size()/2);

        int sumSteps = 0;
        for (Integer num : nums) {
            sumSteps += Math.abs(num-median);
        }

        if(sumSteps <21){
            System.out.println("Минимальное количество ходов: " + sumSteps);
        } else System.out.println("20 ходов недостаточно для приведения всех элементов массива к одному числу");


    }
}