package io.github.n4nlabs.quarkus.i18n.validation;

import java.util.Locale;

import io.github.n4nlabs.quarkus.i18n.MessageSource;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.MessageInterpolator;

@ApplicationScoped
public class CustomMessageInterpolator implements MessageInterpolator {

    @Inject
    MessageSource msg;

    @Override
    public String interpolate(String tpl, Context ctx) {
        return interpolate(tpl, ctx, LocaleContext.get());
    }

    @Override
    public String interpolate(String tpl, Context ctx, Locale fallback) {
        Locale locale = LocaleContext.get();
        String key = tpl.replaceAll("^\\{(.*)\\}$", "$1");
        Object[] args = ctx.getConstraintDescriptor().getAttributes().values().toArray();

        return msg.getMessage(key, args, locale);
    }

}
