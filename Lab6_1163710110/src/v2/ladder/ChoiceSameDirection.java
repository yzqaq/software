package v2.ladder;

import java.util.List;

public class ChoiceSameDirection implements Choice{

    @Override
    public int getLadder(List<Ladder> list,boolean direction,int v) {
        // TODO Auto-generated method stub
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getMonkeys()[0]==0) {
                return i;
            }
        }
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getDirection()==direction) {
                return i;
            }
        }
        return -1;
    }

}
