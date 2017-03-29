package org.owasp.csrfguard.util;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexander.serbin on 3/29/2017.
 */
public class ClassesMapHolder<T> {

    private Map<String, Class<? extends T>> map;

    protected void init(Class<? extends T> ... classes) {
        Map<String, Class<? extends T>> aMap
                = new HashMap<String, Class<? extends T>>();
        for (Class<? extends T> clazz: classes) {
            aMap.put(clazz.getName(), clazz);
        }
        map = Collections.unmodifiableMap(aMap);
    }

    public Class<? extends T> getClass(String className) {
        return map.get(className);
    }

}
