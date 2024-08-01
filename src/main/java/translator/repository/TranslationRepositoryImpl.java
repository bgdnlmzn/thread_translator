package translator.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import translator.entity.Translation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TranslationRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Translation translation) {
        String sql = "INSERT INTO translation_requests (id, ip_address, source_lang, target_lang, input_text, translated_text) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, UUID.randomUUID(), translation.getIpAddress(), translation.getSourceLang(), translation.getTargetLang(), translation.getInputText(), translation.getTranslatedText());
    }

    @Override
    public Translation findById(UUID id) {
        String sql = "SELECT * FROM translation_requests WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, new Object[]{id}, new TranslationRowMapper());
    }

    @Override
    public List<Translation> findAll() {
        String sql = "SELECT * FROM translation_requests";
        return jdbcTemplate.query(sql, new TranslationRowMapper());
    }

    private static class TranslationRowMapper implements RowMapper<Translation> {
        @Override
        public Translation mapRow(ResultSet rs, int rowNum) throws SQLException {
            return Translation.builder()
                    .id(UUID.fromString(rs.getString("id")))
                    .ipAddress(rs.getString("ip_address"))
                    .sourceLang(rs.getString("source_lang"))
                    .targetLang(rs.getString("target_lang"))
                    .inputText(rs.getString("input_text"))
                    .translatedText(rs.getString("translated_text"))
                    .build();
        }
    }
}

