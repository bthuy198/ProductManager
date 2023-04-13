package repository.file;

import repository.IModel;
import repository.ISearch;

import java.util.ArrayList;
import java.util.List;

public class FileContext<T> {
    protected  Class<T> tClass;
    protected String filePath;
    private FileService fileService;
    public FileContext(){
        fileService = new FileService();
    }
    public List<T> getAll(){
        return fileService.readData(filePath,tClass);
    }
    public T findById(long id) {
        List<T> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            IModel<T> imodel = (IModel<T>) list.get(i);
            if (imodel.getId() == id) {
                return list.get(i);
            }
        }
        return null;
    }
    public void deleteById(long id) {
        List<T> list = getAll();
        for (int i = 0; i < list.size(); i++) {
            IModel<T> imodel = (IModel<T>) list.get(i);
            if (imodel.getId() == id) {
                list.remove(i);
                break;
            }
        }
        fileService.writeData(filePath,list);
    }
    public void add(T newObj) {
        List<T> list = getAll();
        list.add(newObj);
        fileService.writeData(filePath,list);
    }
    // chức năng search theo tên
    public List<T> searchByName(String name){
        List<T> list = getAll();
        List<T> searchNameResult = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            ISearch<T> iSearch = (ISearch<T>) list.get(i);
            if (iSearch.getProductName().contains(name)) {
                searchNameResult.add(list.get(i));
            }
        }
        return null;
    }
    public void addList(List<T> list){
        fileService.writeData(filePath,list);
    }
}
