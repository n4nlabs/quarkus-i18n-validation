package io.github.n4nlabs.quarkus.i18n.validation;

import java.io.IOException;
import java.util.Locale;

import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.ext.Provider;

@Provider
@Priority(Priorities.USER)
public class LocaleThreadFilter implements ContainerRequestFilter, ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        String al = requestContext.getHeaderString(HttpHeaders.ACCEPT_LANGUAGE);
        Locale locale;

        if (al != null && !al.isBlank()) {
            String range = al.split(",")[0].trim();
            locale = Locale.forLanguageTag(range);
        } else {
            locale = Locale.getDefault();
        }

        LocaleContext.set(locale);
    }

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext)
            throws IOException {
        LocaleContext.clear();
    }

}
