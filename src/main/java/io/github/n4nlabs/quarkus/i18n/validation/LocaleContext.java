package io.github.n4nlabs.quarkus.i18n.validation;

import java.util.Locale;

public class LocaleContext {

    private static final ThreadLocal<Locale> THREAD_LOCALE = new ThreadLocal<>();

    public static void set(Locale locale) {
        THREAD_LOCALE.set(locale);
    }

    public static Locale get() {
        Locale loc = THREAD_LOCALE.get();
        return loc != null ? loc : Locale.getDefault();
    }

    public static void clear() {
        THREAD_LOCALE.remove();
    }

}
