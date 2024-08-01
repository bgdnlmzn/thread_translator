package translator.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;


@Builder
public record TranslationRequest(
        @NotNull(message = "Введите текст для перевода.")
        String inputText,

        @NotNull(message = "Введите исходный язык.")
        String sourceLang,

        @NotNull(message = "Введите целевой язык.")
        String targetLang
) {
}
