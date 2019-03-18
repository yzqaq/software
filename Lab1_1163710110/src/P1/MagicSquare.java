package src.P1;

import java.io.*;

public class MagicSquare {
    boolean isLegalMagicSquare(String fileName) {
        int square[][] = new int[200][200];
        int m = 0, n = 0;
        try {
            File filename = new File(fileName);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename), "UTF-8"); // 建立一个输入流对象reader
            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
            String line = null;
            String taken[];
            if ((line = br.readLine()) != null) {
                taken = line.split("\t");
                n = taken.length;
                for (int i = 0; i < n; i++) {

                    square[m][i] = Integer.parseInt(taken[i]);
                }
            }
            while ((line = br.readLine()) != null) {
                m++;
                taken = line.split("\t");
                if (n != taken.length)
                    return false;
                for (int i = 0; i < n; i++) {
                    square[m][i] = Integer.parseInt(taken[i]);
                }

            }
            br.close();
            reader.close();
            if ((m + 1) != n)
                return false;
        } catch (Exception e) {
            return false;
        }

        int sum0 = 0, sum = 0, sum1 = 0, sum2 = 0;
        int sum_col[] = new int[n];
        for (int col = 0; col < n; col++) {
            sum0 = sum0 + square[0][col];

        }
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                sum += square[row][col];
                sum_col[col] += square[row][col];
                if (row == col)
                    sum1 += square[row][col];
                if (row + col == n - 1)
                    sum2 += square[row][col];
            }
            if (sum != sum0)
                return false;
            sum = 0;
        }
        if (sum1 != sum0)
            return false;
        if (sum2 != sum0)
            return false;
        for (int i = 0; i < n; i++) {
            if (sum_col[i] != sum0)
                return false;
        }
        return true;

    }

    public static boolean generateMagicSquare(int n) {

        if (n % 2 == 0 || n <= 0)
            return false;
        int magic[][] = new int[n][n];
        int row = 0, col = n / 2, i, j, square = n * n;

        for (i = 1; i <= square; i++) {
            magic[row][col] = i;
            if (i % n == 0)
                row++;
            else {
                if (row == 0)
                    row = n - 1;
                else
                    row--;
                if (col == (n - 1))
                    col = 0;
                else
                    col++;
            }
        }

        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++)
                System.out.print(magic[i][j] + "\t");
            System.out.println();
        }
        try {
            ;

            File file = new File("./src/P1/txt/6.txt");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file);
            for (int k = 0; k < n; k++) {
                for (int l = 0; l < n; l++) {
                    fw.write(magic[k][l] + "\t");
                }
                fw.write("\r");
            }
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    public static void main(String args[]) {
        MagicSquare ms = new MagicSquare();
        System.out.println(ms.isLegalMagicSquare("./src/P1/txt/1.txt"));
        System.out.println(ms.isLegalMagicSquare("./src/P1/txt/2.txt"));
        System.out.println(ms.isLegalMagicSquare("./src/P1/txt/3.txt"));
        System.out.println(ms.isLegalMagicSquare("./src/P1/txt/4.txt"));
        System.out.println(ms.isLegalMagicSquare("./src/P1/txt/5.txt"));
        generateMagicSquare(3);
        System.out.println(ms.isLegalMagicSquare("./src/P1/txt/6.txt"));

    }

}
