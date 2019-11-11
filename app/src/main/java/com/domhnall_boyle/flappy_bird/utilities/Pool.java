package com.domhnall_boyle.flappy_bird.utilities;

import java.util.ArrayList;
import java.util.List;

public class Pool<T> {

    public interface ObjectFactory<T> {
        T createObject();
    }
    private final ObjectFactory<T> factory;
    private final List<T> pool;
    private final int maxPoolSize;

    public Pool(ObjectFactory<T> factory, int maxPoolSize) {
        this.factory = factory;
        this.maxPoolSize = maxPoolSize;
        this.pool = new ArrayList<>(this.maxPoolSize);
    }

    public T get() {
        T object;

        if (this.pool.isEmpty())
            object = this.factory.createObject();
        else
            object = this.pool.remove(this.pool.size() - 1);

        return object;
    }

    public void add(T object) {
        if (this.pool.size() < this.maxPoolSize)
            this.pool.add(object);
    }
}
