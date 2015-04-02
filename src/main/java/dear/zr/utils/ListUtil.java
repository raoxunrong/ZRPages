package dear.zr.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListUtil {

    public static <E> List<E> findSameElem(List<List<E>> lists) {
        if (lists.size() == 0) {
            return Collections.emptyList();
        }
        List<E> sameElements = new ArrayList<>(lists.get(0));
        for (int i = 1; i < lists.size(); i++) {
            sameElements.retainAll(lists.get(i));
        }
        return sameElements;
    }
}
