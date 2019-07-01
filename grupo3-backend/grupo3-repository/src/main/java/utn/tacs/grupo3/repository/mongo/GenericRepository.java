package utn.tacs.grupo3.repository.mongo;

import java.util.List;


public interface GenericRepository<T, ID> {
	
	List<T> findAll();
	
	T save(T object);

	T findById(ID id);

	List<T> findBy(String key, Object value);
	
	boolean exists(ID id);
	
	boolean existsBy(String key, Object value);
	
	boolean existsBy(List<KeyValue> keyValue);

}
