package v1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import v1.ladder.Ladder;

/**
 * 猴子过的河
 * 
 * @author yz
 *
 */
public class River {
    /*
     * 梯子数 1~5
     */
    private final int n;
    /*
     * 梯子长度
     */
    private final int h;
    // 1~5 每隔时间 秒

    private final int t;
    // 2~200
    private final int N;
    // 1~3 产生 个猴子
    private final int k;
    // 最大速度
    private final int MV;
    //
    private final List<Long> monkeyTime;
    //
    private List<Ladder> ladders;
    {
        h = 20;
        MV = 5;
        ladders = new ArrayList<>();
        monkeyTime = new ArrayList<>();
    }

    /**
     * 
     * @param n
     *            梯子数
     * @param t
     *            每隔 秒时间
     * @param n2
     *            猴子总数
     * @param k
     *            单位时间生多少猴子
     */
    public River(int n, int t, int n2, int k) {
        this.n = n;
        this.t = t;
        N = n2;
        this.k = k;
    }

    public River(String corpus) {
        File file = new File(corpus);
        int n = 1, t = 1, N = 1, k = 1;
        try {

            Scanner fileIn = new Scanner(file);
            while (fileIn.hasNextInt()) {
                n = fileIn.nextInt();
                t = fileIn.nextInt();
                N = fileIn.nextInt();
                k = fileIn.nextInt();

            }
            fileIn.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.n = n;
        this.t = t;
        this.N = N;
        this.k = k;
    }

    public void init() {
        Ladder l = null;
        delAllFile("log/");
        for (int i = 0; i < n; i++) {
            l = new Ladder(h, i);
            ladders.add(l);

        }
        // 开始生猴子
        new MonkeyGenerator(t, k, N, MV, ladders, monkeyTime);
    }

    public double fair() {
        List<Long> t = getMonkeyTime();
        int temp = 0;
        int sum = 0;
        for (int i = 1; i < t.size(); i += 2) {
            for (int j = i + 2; j < t.size(); j += 2) {
                if ((t.get(i) - t.get(j)) * (t.get(i - 1) - t.get(j -1)) >= 0) {
                    temp++;
                } else {
                    temp--;
                }
                sum++;
            }
        }
        return temp * 1.0 / sum;
    }

    public boolean delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return false;
        }
        if (!file.isDirectory()) {
            return false;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith("/")) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + '/' + tempList[i]);
            }

            temp.delete();

        }
        return true;
    }

    public static void main(String[] args) {
        // River river=new River(10, 2, 10, 3);

        River river = new River("test/0");
        long t = System.currentTimeMillis();
        river.init();
        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if (Thread.currentThread().getThreadGroup().activeCount() == 1) {
                break;
            }
        }
        t = System.currentTimeMillis() - t;
        System.out.println("吞吐率：" + river.getN() * 1000.0 / t);
        System.out.println("公平性：" + river.fair());

    }

    public int getN() {
        return N;
    }

    /**
     * @return the time
     */
    public List<Long> getMonkeyTime() {
        return monkeyTime;
    }
}
