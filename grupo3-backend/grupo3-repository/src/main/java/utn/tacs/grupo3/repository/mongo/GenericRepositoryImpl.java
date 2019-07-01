package utn.tacs.grupo3.repository.mongo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public abstract class GenericRepositoryImpl<T, ID> implements GenericRepository<T,ID>{


	@Autowired
	protected MongoOperations mongoOps;
	
	protected abstract Class<T> getClassType();
	protected abstract String getCollectionName();

	
	@Override
	public List<T> findAll() {
		return mongoOps.findAll(getClassType());
	}
	
	@Override
	public T save(T object) {
		return mongoOps.save(object);
	}

	@Override
	public T findById(ID id) {
		return mongoOps.findById(id, getClassType());
	}

	@Override
	public List<T> findBy(String key, Object value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).is(value));
		
		return mongoOps.find(query, getClassType());
	}

	@Override
	public boolean exists(ID id) {
		return existsBy("id", id);
	}

	@Override
	public boolean existsBy(String key, Object value) {
		Query query = new Query();
		query.addCriteria(Criteria.where(key).is(value));
		
		return mongoOps.exists(query, getClassType(), getCollectionName());
	}
	
	@Override
	public boolean existsBy(List<KeyValue> keyValues) {
		Query query = new Query();
		
		keyValues.forEach(kv -> query.addCriteria(
				Criteria.where(kv.getKey()).is(kv.getValue()))
				);
		
		return mongoOps.exists(query, getClassType(), getCollectionName());
	}

	
	
}
