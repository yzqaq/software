package v2.ladder;

import java.util.List;

public interface Choice {
    /**
     * 选择合适的梯子
     * @return 梯子的id,返回-1若无梯子
     */
    public int getLadder(List<Ladder> list,boolean direction,int v);


}
