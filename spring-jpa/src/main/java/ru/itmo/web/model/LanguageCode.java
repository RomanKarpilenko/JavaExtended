package ru.itmo.web.model;

import java.util.Locale;

public enum LanguageCode {
    RU,
    EN;

    @Override
    public String toString() {
        return this.name().toLowerCase(Locale.ROOT);
    }
}
