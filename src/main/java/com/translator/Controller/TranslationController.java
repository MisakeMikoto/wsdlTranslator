package com.translator.Controller;

/**
 * @Author MisakiMikoto
 * @Date 2023/11/01
 */
import com.translator.Service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TranslationController {

    @Autowired
    private TranslationService translationService;

    @GetMapping("/translator")
    public List<Object> translate(@RequestParam String text) {
        List<Object> translation = translationService.translate(text);
        return translation;
    }

    @GetMapping("/translatorString")
    public List<String> translatorToString(@RequestParam String text) {
        List<String> translation = translationService.translatorToString(text);
        return translation;
    }

    @GetMapping("/translatorReferString")
    public List<String> translatorReferString(@RequestParam String text) {
        List<String> translation = translationService.translatorReferString(text);
        return translation;
    }

    @GetMapping("/translatorSentenceString")
    public List<String> translatorSentenceString(@RequestParam String text) {
        List<String> translation = translationService.translatorSentenceString(text);
        return translation;
    }

    @GetMapping("/suggestWord")
    public List<String> suggestWord(@RequestParam String text) {
        List<String> translation = translationService.suggestWord(text);
        return translation;
    }

    @GetMapping("/getMp3")
    public byte[] getMp3(@RequestParam String text) {
        byte[] translation = translationService.getMp3(text);
        return translation;
    }
}
