package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.config.ExamConfig;
import ru.otus.spring.service.interfaces.Localization;

import java.util.Locale;

@Service
public class LocalizationImpl implements Localization {

    private final MessageSource msg;
    private String language;
    private String country;


    @Autowired
    public LocalizationImpl(ExamConfig config, MessageSource msg) {
        this.msg = msg;
        this.language = config.getLocaleLanguage();
        this.country = config.getLocaleCountry();
    }

    @Override
    public String getExamPropertiesValue(String key, Object ...args) {
        return msg.getMessage(key, args, Locale.forLanguageTag(this.country + "-" + this.language));
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}