package repository.interfaces;

import java.util.List;
import java.util.Optional;

public interface Repository<ENTITY, ID> {

    default List<ENTITY> encontrarTodos() {
        return null;
    }

    default Optional<ENTITY> encontrarPorId(ID id) {
        return Optional.empty();
    }

    default ENTITY salvar(ENTITY entity) {
        return entity;
    }

}
