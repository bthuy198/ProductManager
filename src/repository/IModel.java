package repository;

public interface IModel<T> {
    long getId();
    T parseData(String line);
}
