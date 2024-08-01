package translator.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import translator.dto.YandexTranslateRequest;
import translator.dto.YandexTranslateResponse;
import translator.dto.TranslationRequest;
import translator.dto.TranslationResponse;
import translator.entity.Translation;
import translator.mapper.TranslationMapper;
import translator.repository.TranslationRepository;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

@Service
public class TranslationServiceImpl implements TranslationService {
    private final RestTemplate restTemplate;
    private final ExecutorService executorService;
    private final TranslationRepository translationRepository;
    private final TranslationMapper translationMapper;

    @Value("${yandex.folderId}")
    private String folderId;

    @Value("${yandex.apiKey}")
    private String apiKey;

    @Autowired
    public TranslationServiceImpl(RestTemplate restTemplate, TranslationRepository translationRepository, TranslationMapper translationMapper) {
        this.restTemplate = restTemplate;
        this.executorService = Executors.newFixedThreadPool(10);
        this.translationRepository = translationRepository;
        this.translationMapper = translationMapper;
    }

    @Override
    public TranslationResponse translate(TranslationRequest body, String ipAddress) throws Exception {
        Translation translation = translationMapper.mapTranslation(body, ipAddress);
        String translatedText = translateText(body.inputText(), body.sourceLang(), body.targetLang());
        translation.setTranslatedText(translatedText);
        translationRepository.save(translation);
        return translationMapper.mapTranslationToResponse(translation);
    }

    private String translateText(String text, String sourceLang, String targetLang) throws Exception {
        String[] words = text.split("\\s+");
        List<Future<String>> futures = executorService.invokeAll(
                List.of(words).stream().map(word -> (Callable<String>) () -> translateWord(word, sourceLang, targetLang)).collect(Collectors.toList())
        );

        return futures.stream().map(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                return "";
            }
        }).collect(Collectors.joining(" "));
    }

    private String translateWord(String word, String sourceLang, String targetLang) {
        String url = "https://translate.api.cloud.yandex.net/translate/v2/translate";
        YandexTranslateRequest request = new YandexTranslateRequest(sourceLang, targetLang, List.of(word), folderId);
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Api-Key " + apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<YandexTranslateRequest> entity = new HttpEntity<>(request, headers);
        ResponseEntity<YandexTranslateResponse> response = restTemplate.postForEntity(url, entity, YandexTranslateResponse.class);

        if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null && !response.getBody().getTranslations().isEmpty()) {
            return response.getBody().getTranslations().get(0).getText();
        }
        return word;
    }
}






