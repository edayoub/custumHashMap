package com.company.customHashMap;

import java.util.ArrayList;
import java.util.List;

public class CustomHashMap<K,T> {

    private   List<?>[] map ;
    private int size ;

    public CustomHashMap() {
        this.size = 500;
        this.map = new ArrayList<?>[this.size];
    }

    public CustomHashMap(int size) {
        this.size = size;
        this.map = new ArrayList<?>[this.size];
    }

    public void put(K key , T object){
        int index = getIndex(key);
        List<StoredObject> list =  (List<StoredObject>)map[index];
        if (list ==null){
            list = new ArrayList<>();
            map[index]=list;
        }
        T searchedObject =searchForObject(key,list);
        if(searchedObject!=null){
            throw new RuntimeException("allredy containing this key");
        }
        StoredObject storedObject = new StoredObject(key,object);
        list.add(storedObject);
    }
    public T get(K key){
        int index = getIndex(key);
        List<StoredObject> list =  (List<StoredObject>)map[index];
        if (list ==null){
            return null;
        }
        return searchForObject(key,list);
    }
    private int getIndex(K object ){
        return Math.abs(object.hashCode()%this.size);
    }
    private T searchForObject(K key, List<StoredObject> list) {
        for (StoredObject storedObject : list) {
            if (storedObject.getKey().equals(key)) {
                return storedObject.getObject();
            }
        }
        return null;
    }

    private class StoredObject {
        K key ;
        T object;

        private StoredObject(K key, T object) {
            this.key = key;
            this.object = object;
        }

        private K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        private T getObject() {
            return object;
        }

        public void setObject(T object) {
            this.object = object;
        }
    }
}


