package com.translator.Service;

/**
 * @Author MisakiMikoto
 * @Date 2023/11/01
 */
import com.alibaba.fastjson.JSON;
import com.translator.Utils.*;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.jaxws.endpoint.dynamic.JaxWsDynamicClientFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TranslationService {

    EnglishChinese englishChinese = new EnglishChinese();

    EnglishChineseSoap soap = englishChinese.getEnglishChineseSoap();

    public List<Object> translate(String wordKey) {


        TranslatorResponse.TranslatorResult translator = soap.translator(wordKey);

        List<Object> any = translator.getAny();
        return any;


    }

    public List<String> translatorToString(String text) {
        ArrayOfString arrayOfString = soap.translatorString(text);
        return arrayOfString.getString();
    }

    public List<String> translatorReferString(String text) {
        ArrayOfString arrayOfString = soap.translatorReferString(text);
        return arrayOfString.getString();
    }

    public List<String> translatorSentenceString(String text) {
        ArrayOfString arrayOfString = soap.translatorSentenceString(text);
        return arrayOfString.getString();
    }

    public List<String> suggestWord(String text) {
        ArrayOfString arrayOfString = soap.suggestWord(text);
        return arrayOfString.getString();
    }

    public byte[] getMp3(String text) {
        byte[] mp3 = soap.getMp3(text);
        return mp3;
    }
}
