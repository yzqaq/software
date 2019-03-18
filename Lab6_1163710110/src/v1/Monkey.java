package v1;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

import v1.ladder.ChoiceContext;
import v1.ladder.ChoiceNoMonkey;
import v1.ladder.ChoiceSameDirection;
import v1.ladder.Ladder;

/**
 * 一个monkey类， 并生成一个线程
 * 
 * @author yz
 *
 */
public class Monkey implements Runnable {

    private final long id;
    private final boolean direction;
    private final int v;
    private final List<Long> monkeyTime;
    private List<Ladder> list;
    private static Logger logger = Logger.getLogger("monkey");

    /**
     * 
     * @param id
     *            monkey的id
     * @param direction
     *            方向是否为从左到右
     * @param v
     *            monkey的速度
     * @param list
     *            梯子
     * @param monkeyTime
     *            用来储存每个猴子的开始与结束时间
     */
    public Monkey(long id, boolean direction, int v, List<Ladder> list, List<Long> monkeyTime) {
        this.id = id;
        this.direction = direction;
        this.v = v;
        this.list = list;
        this.monkeyTime = monkeyTime;
        Thread thread = new Thread(this);
        thread.start();
    }

    public long getId() {
        return id;
    }

    public boolean getDirection() {
        return direction;
    }

    public int getV() {
        return v;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        // 初始策略
        synchronized (this) {

            String msg;
            String direction;
            FileHandler fileHandler = null;
            try {
                fileHandler = new FileHandler("log/monkey.log");
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            fileHandler.setLevel(Level.ALL);
            logger.addHandler(fileHandler);
            ChoiceContext cct = new ChoiceContext(new ChoiceNoMonkey());
            if (new Random().nextBoolean()) {
                cct.setChoice(new ChoiceSameDirection());
            }
            int ladderNum = cct.getChoice().getLadder(list, getDirection(), getV());
            long start = System.currentTimeMillis();
            long now = System.currentTimeMillis();
            while (ladderNum == -1 || !(list.get(ladderNum).getMonkeys()[1] == 0)) {
                try {
                    direction = getDirection() ? "左" : "右";
                    now = System.currentTimeMillis();
                    msg = new Date().toString() + "猴子" + getId() + "正在" + direction + "岸等待,离出生已"
                            + (now - start) / 1000.0 + "秒";
                    logger.info(msg);
                    // System.out.println(msg);
                    // Thread.sleep(1000);
                    wait(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                ladderNum = cct.getChoice().getLadder(list, getDirection(), getV());

            }
            long[] status = list.get(ladderNum).getMonkeys();
            list.get(ladderNum).setDirection(getDirection());
            status[0]++;

            int flag = 1;
            status[flag] = getId();
            //
            direction = getDirection() ? "自左向右" : "自右向左";
            now = System.currentTimeMillis();
            msg = new Date().toString() + "猴子" + getId() + "正在第" + ladderNum + "架梯子的第" + flag + "个踏板上," + direction
                    + "行进,离出生已" + (now - start) / 1000.0 + "秒";
            logger.info(msg);
            // System.out.println(msg);
            try {
                wait(0);
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            label1: while (true) {
                synchronized (this) {
                    status[flag] = 0;
                    // System.out.print(new Date().toString() + "猴子" + getId() + "从ladder" +
                    // ladderNum + "的" + flag);
                    for (int j = 0; j < v; j++) {
                        if (status[flag] == 0) {
                            flag++;
                            if (flag == status.length) {
                                status[0]--;
                                //
                                direction = getDirection() ? "已从从左岸到达右岸" : "已从从右岸到达左岸";
                                now = System.currentTimeMillis();
                                monkeyTime.add(start);
                                monkeyTime.add(now);
                                msg = new Date().toString() + "猴子" + getId() + direction + ",共耗时"
                                        + (now - start) / 1000.0 + "秒";
                                logger.info(msg);
                                // System.out.println(msg);
                                try {
                                    wait(0);
                                } catch (InterruptedException e) {
                                    // TODO Auto-generated catch block
                                    e.printStackTrace();
                                }
                                break label1;
                            }
                        } else {
                            break;
                        }
                    }
                    status[flag] = getId();
                    //
                    direction = getDirection() ? "自左向右" : "自右向左";
                    now = System.currentTimeMillis();
                    msg = new Date().toString() + "猴子" + getId() + "正在第" + ladderNum + "架梯子的第" + flag + "个踏板上,"
                            + direction + "行进,离出生已" + (now - start) / 1000.0 + "秒";
                    logger.info(msg);
                    // System.out.println(msg);
                    // try {
                    // Thread.sleep(1000);
                    // } catch (InterruptedException e) {
                    // // TODO Auto-generated catch block
                    // e.printStackTrace();
                    // }
                    try {
                        wait(1000);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
