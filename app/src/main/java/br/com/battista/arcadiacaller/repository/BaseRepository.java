package br.com.battista.arcadiacaller.repository;


import java.util.List;

import br.com.battista.arcadiacaller.model.BaseEntity;

public interface BaseRepository<Entity extends BaseEntity> {

    void saveAll(List<Entity> entities);

    void save(Entity entity);

    Entity findById(Long id);

    void update(Entity entity);

    void deleteById(Long id);

    List<Entity> findAll();

    void deleteAll();
}
