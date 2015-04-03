package dear.zr.utils;

import com.google.common.collect.Sets;

import java.util.*;

public class CollectionsUtil {

    public static <T> Collection<T> intersection(Collection<T>... collection) {
        Set<T> result = Sets.newHashSet(collection[0]);
        for (Collection<T> numbers : collection) {
            result = Sets.intersection(result, Sets.newHashSet(numbers));
        }
        return result;
    }
}
