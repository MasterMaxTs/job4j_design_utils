package ru.job4j.generics.store;

public class RoleStore implements Store<Role> {
    private final Store<Role> roleStore = new MemStore<>();

    @Override
    public void add(Role model) {
        this.roleStore.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        return this.roleStore.replace(id, model);
    }

    public boolean delete(String id) {
        return this.roleStore.delete(id);
    }

    public Role findById(String id) {
        return this.roleStore.findById(id);
    }
}
