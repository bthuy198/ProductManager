package repository.memory;

import repository.IModel;
import repository.ISearch;

import java.util.ArrayList;
import java.util.List;

public abstract class MemoryContext<T> {
    protected List<T> list;

    public MemoryContext() {
        init();
    }

    public abstract void init();

    public List<T> getAll() {
        return list;
    }

    // Lam theo kieu comparable
    public List<T> searchByName(String name) {
        List<T> searchNameResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> imodel = (ISearch<T>) list.get(i);
            if (imodel.getProductName().contains(name)) {
                searchNameResult.add(list.get(i));
            }
        }
        if (searchNameResult.size() == 0) {
            System.out.println("Not found this name");
            return null;
        }
        return searchNameResult;
    }

    public void deleteById(long id) {
        for (int i = 0; i < list.size(); i++) {
            IModel<T> imodel = (IModel<T>) list.get(i);
            if (imodel.getId() == id) {
                list.remove(i);
                break;
            }
        }
    }

    public T findById(long id) {
        for (int i = 0; i < list.size(); i++) {
            IModel<T> imodel = (IModel<T>) list.get(i);
            if (imodel.getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }

    public T findByName(String name) {
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> imodel = (ISearch<T>) list.get(i);
            if (imodel.getProductName().equals(name)) {
                return list.get(i);
            }
        }
        return null;
    }

    public void add(T newObj) {
        list.add(newObj);
    }
}
