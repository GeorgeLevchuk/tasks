import java.io.*;

public class task2 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Ошибка: укажите пути к файлам");
            System.out.println("Первый аргумент: файл с эллипсом");
            System.out.println("Второй аргумент: файл с точками");
            return;
        }

        String path1 = args[0];
        String path2 = args[1];
        double[] ellipse = new double[4];

        try (BufferedReader in = new BufferedReader(new FileReader(path1))) {
            String line;
            int i = 0;
            while ((line = in.readLine()) != null && i < 4) {
                if (!line.trim().isEmpty()) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length == 2) {
                        ellipse[i] = Double.parseDouble(parts[0]);
                        ellipse[i + 1] = Double.parseDouble(parts[1]);
                        i += 2;
                    }
                }
            }

            if (ellipse[2] == 0 || ellipse[3] == 0) {
                System.out.println("Ошибка: радиус не может быть 0");
                return;
            }

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
            return;
        }
        StringBuilder output = new StringBuilder(256);

        try (BufferedReader in = new BufferedReader(new FileReader(path2))) {
            String line;
            int pointCount = 0;

            final double cx = ellipse[0];
            final double cy = ellipse[1];
            final double rx2 = ellipse[2] * ellipse[2];
            final double ry2 = ellipse[3] * ellipse[3];
            final double target = rx2 * ry2;
            final double epsilon = 1e-9 * target;
            while ((line = in.readLine()) != null && pointCount < 100) {
                if (!line.isEmpty()) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length == 2) {
                        int result=-1;
                        double dx = Double.parseDouble(parts[0]) - cx;
                        double dy = Double.parseDouble(parts[1]) - cy;
                        double value = dx*dx*ry2 + dy*dy*rx2;

                        if (Math.abs(value - target) < epsilon) {
                            result = 0;
                        } else if (value < 1.0) {
                            result = 1;
                        } else {
                            result = 2;
                        }
                        output.append(result).append('\n');

                        pointCount++;
                    }
                }
            }
            System.out.print(output);

        } catch (IOException e) {
            System.out.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

}