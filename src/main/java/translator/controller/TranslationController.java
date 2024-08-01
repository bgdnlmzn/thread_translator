package translator.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import translator.dto.TranslationRequest;
import translator.dto.TranslationResponse;
import translator.service.TranslationService;

@RestController
@RequestMapping("api")
@RequiredArgsConstructor
@Slf4j
public class TranslationController {

    private final TranslationService translationService;

    @PostMapping("/translate")
    public TranslationResponse translate(@RequestBody @Valid TranslationRequest body, HttpServletRequest request) throws Exception {
        String ipAddress = request.getRemoteAddr();
        return translationService.translate(body, ipAddress);
    }
}
