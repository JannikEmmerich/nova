package net.getnova.framework.core;

import java.util.Set;

public interface SmallCrudService<D, S, I> {

  Set<S> findAll();

  D findById(I id);

  boolean exist(I id);

  D save(D dto);

  D save(I id, D dto);

  void delete(I id);
}
