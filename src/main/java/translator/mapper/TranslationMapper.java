package translator.mapper;

import org.springframework.stereotype.Component;
import translator.dto.TranslationRequest;
import translator.dto.TranslationResponse;
import translator.entity.Translation;

@Component
public class TranslationMapper {
    public static Translation mapTranslation(TranslationRequest body, String ipAddress) {
        Translation translation = new Translation();
        translation.setInputText(body.inputText());
        translation.setSourceLang(body.sourceLang());
        translation.setTargetLang(body.targetLang());
        translation.setIpAddress(ipAddress);
        return translation;
    }

    public static TranslationResponse mapTranslationToResponse(Translation translation) {
        return new TranslationResponse(
                translation.getTranslatedText()
        );
    }
}
