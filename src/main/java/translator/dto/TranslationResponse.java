package translator.dto;

import lombok.Builder;

@Builder
public record TranslationResponse(
        String translatedText
) {
}
