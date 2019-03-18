package v1.ladder;
/**
 * 梯子
 * @author yz
 *
 */
public class Ladder {
    /**
     * 构造个梯子
     * @param h 梯子长度
     * @param id 梯子id
     */
    public Ladder(int h, int id) {
        this.h = h;
        this.id = id;
        setMonkeys(new long[h+1]);
    }

    private final int h;
    private final int id;
    private boolean direction;
    private int minv;
    private long[] monkeys;

    public int getH() {
        return h;
    }

    public int getId() {
        return id;
    }

    /**
     * @return the direction 是从左到右
     */
    public boolean getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(boolean direction) {
        this.direction = direction;
    }

    /**
     * @return the monkeys 梯子上猴子的状态，其中第一个元素表示梯子上猴子的个数
     */
    public synchronized long[]   getMonkeys() {
        return monkeys;
    }

    /**
     * @param monkeys the monkeys to set
     */
    public void setMonkeys(long[] monkeys) {
        this.monkeys = monkeys;
    }
    public static void main(String [] args) {
        Ladder lader=new Ladder(3, 1);
        System.out.println(lader.getDirection());
        for(int i=0;i<lader.getMonkeys().length;i++) {
            System.out.println(lader.getMonkeys()[i]);
        }
        lader.getMonkeys()[0]=5;
        System.out.println("after");
        for(int i=0;i<lader.getMonkeys().length;i++) {
            System.out.println(lader.getMonkeys()[i]);
        }
    }

    /**
     * @return the minv
     */
    public int getMinv() {
        return minv;
    }

    /**
     * @param minv the minv to set
     */
    public void setMinv(int minv) {
        this.minv = minv;
    }

}
