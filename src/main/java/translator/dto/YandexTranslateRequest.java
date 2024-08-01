package translator.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class YandexTranslateRequest {
    private String sourceLanguageCode;
    private String targetLanguageCode;
    private List<String> texts;
    private String folderId;
}

