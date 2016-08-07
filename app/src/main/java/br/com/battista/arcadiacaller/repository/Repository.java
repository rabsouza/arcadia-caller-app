package br.com.battista.arcadiacaller.repository;


import java.util.List;

import br.com.battista.arcadiacaller.model.BaseEntity;

public interface Repository<Entity extends BaseEntity> {

    void saveAll(List<Entity> entities);

    void save(Entity entity);

    List<Entity> findAll();

    void deleteAll();
}
