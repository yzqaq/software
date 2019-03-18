package v2.ladder;

import java.util.List;

public class ChoiceNoMonkey implements Choice {

    @Override
    public int getLadder(List<Ladder> list,boolean direction, int v) {
        // TODO Auto-generated method stub
        for(int i=0;i<list.size();i++) {
            if(list.get(i).getMonkeys()[0]==0) {
                return i;
            }
        }
        return -1;
    }

}
