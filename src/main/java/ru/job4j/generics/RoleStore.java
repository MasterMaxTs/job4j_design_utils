package ru.job4j.generics;

public class RoleStore<T extends Base> {
    Store<T> store;

    public void setStore(Store<T> store) {
        this.store = store;
    }

    public void add(T model) {
        store.add(model);
    }

    public boolean replace(String id, T model) {
        return store.replace(id, model);
    }

    public boolean delete(String id) {
        return store.delete(id);
    }

    public T findById(String id) {
        return store.findById(id);
    }
}
