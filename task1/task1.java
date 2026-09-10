
public class task1 {
    public static void main(String[] args) {

        if (args.length != 4) {
            System.out.println("Ошибка: укажите n1, m1, n2 и m2");
            System.out.println("Первый аргумент: первый круговой массив размером n1");
            System.out.println("Второй аргумент: первый отрезок m1");
            System.out.println("Третий аргумент: второй круговой массив размером n2");
            System.out.println("Четвертый аргумент: второй отрезок m2");
            return;
        }

        int divide1 = Integer.parseInt(args[0]);
        int segment1 = Integer.parseInt(args[1]);
        int divide2 = Integer.parseInt(args[2]);
        int segment2 = Integer.parseInt(args[3]);

        System.out.println(getPath(divide1,segment1) + getPath(divide2,segment2));


    }

    public static String getPath(int n, int m){
        int start = 1;
        int end = (m - 1) % n + 1;
        boolean flag = false;

        StringBuilder result = new StringBuilder();

        while (!flag){
            result.append(start);
            if(end == 1){
                flag = true;
            } else{
                start = end;
                end = (end + m-1)%n;
                if (end == 0) end = n;
            }
        }
        return result.toString();
    }
}