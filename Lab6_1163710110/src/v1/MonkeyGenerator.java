package v1;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import v1.ladder.Ladder;

/**
 * 定时产生一定数量的猴子
 * 
 * @author yz
 *
 */
public class MonkeyGenerator {
    private int k;
    private int mv;
    private int num;
    // 猴子总数量
    private int n;
    private List<Ladder> list;
    private List<Long> monkeyTime;
    private Timer timer = new Timer();

    /**
     * 
     * @param t
     *            每隔t 秒
     * @param k
     *            产生k个猴子
     * @param mv
     *            猴子的最大速度
     */
    public MonkeyGenerator(int t, int k, int n, int mv, List<Ladder> list, List<Long> monkeyTime) {
        // TODO Auto-generated constructor stub
        this.k = k;
        this.n = n;
        this.mv = mv;
        this.list = list;
        this.monkeyTime = monkeyTime;
        TimeTask task = new TimeTask();
        timer.scheduleAtFixedRate(task, 0, t * 1000);
    }

    class TimeTask extends TimerTask {

        @Override
        public void run() {

            long time = new Date().getTime();
            long id = 0;
            for (int i = 0; i < k; i++) {
                id = i * 1000000 + time;
                new Monkey(id, new Random().nextBoolean(), new Random().nextInt(mv) + 1, list, monkeyTime);
                num++;
                if (num == n) {
                    timer.cancel();
                }
            }
        }

    }

    public static void main(String[] args) {
        Logger log = Logger.getLogger("lavasoft");
        log.setLevel(Level.INFO);
        Logger log1 = Logger.getLogger("lavasoft");
        System.out.println(log == log1); // true
        System.out.println(System.console());
        Logger log2 = Logger.getLogger("lavasoft.blog");
        // log2.setLevel(Level.WARNING);
        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        log.addHandler(consoleHandler);
        FileHandler fileHandler = null;
        ;
        try {
            fileHandler = new FileHandler("log/testlog.log");
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        fileHandler.setLevel(Level.ALL);
        log.addHandler(fileHandler);
        // log2.addHandler(fileHandler);
        log.info("aaa3");
        log2.info("bbb");
        log2.fine("fine");

    }
}
