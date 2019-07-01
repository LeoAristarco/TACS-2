package utn.tacs.grupo3.spring.controller.response;

@FunctionalInterface
public interface ResponseFunction<T> {
	
	public T apply();

}
