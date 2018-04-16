package edu.jxufe.tiamo.util;

import java.util.List;

public class ListUtil<T> {

    public T getFirst(List<T> lst){
        if(lst.size()>0){
            return lst.get(0);
        }
        return null;
    }
}
