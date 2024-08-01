package translator.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Translation {

    private UUID id;

    private String ipAddress;

    private String sourceLang;

    private String targetLang;

    private String inputText;

    private String translatedText;
}
