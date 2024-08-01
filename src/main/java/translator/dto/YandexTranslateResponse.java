package translator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YandexTranslateResponse {
    private List<YandexTranslation> translations;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class YandexTranslation {
        private String text;
    }
}

