package translator.service;

import translator.dto.TranslationRequest;
import translator.dto.TranslationResponse;

public interface TranslationService {
    TranslationResponse translate(TranslationRequest body, String ipAddress) throws Exception;
}
