package ua.sulima.mangaservletapp.dao;

import java.util.Optional;

public interface BasicDao <EntityType, ID>
        extends AutoCloseable{

    Optional<EntityType> findById(ID id);

    ID save(EntityType entity);
}
