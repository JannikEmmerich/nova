package net.getnova.framework.core;

import org.springframework.data.repository.CrudRepository;

public abstract class AbstractSmallCommonIdCrudService<D, S, I, SM, M>
  extends AbstractSmallCrudService<D, S, I, M, SM, I> {

  private final String name;

  public AbstractSmallCommonIdCrudService(
    final String name,
    final CrudRepository<M, I> repository,
    final CrudRepository<SM, I> smallRepository,
    final Converter<M, D> converter,
    final Converter<SM, S> smallConverter
  ) {
    super(repository, smallRepository, converter, smallConverter);
    this.name = name;
  }

  @Override
  public D findById(final I id) {
    return this.converter.toDto(
      this.repository.findById(id)
        .orElseThrow(() -> new NotFoundException(this.name))
    );
  }

  @Override
  public boolean exist(final I id) {
    return this.repository.existsById(id);
  }

  @Override
  public D save(final I id, final D dto) {
    final M model = this.repository.findById(id)
      .orElseThrow(() -> new NotFoundException(this.name));

    this.converter.override(model, dto);

    return this.converter.toDto(model);
  }

  @Override
  public void delete(final I id) {
    if (!this.repository.existsById(id)) {
      throw new NotFoundException(this.name);
    }

    this.repository.deleteById(id);
  }
}
