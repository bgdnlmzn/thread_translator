package translator.repository;

import translator.entity.Translation;

import java.util.List;
import java.util.UUID;

public interface TranslationRepository {
    void save(Translation translation);
    Translation findById(UUID id);
    List<Translation> findAll();
}

