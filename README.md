# quarkus-i18n-validation

A Quarkus extension module providing **Bean Validation** message interpolation using your internationalization core (`quarkus-i18n`).

This module:
- Registers a **CustomMessageInterpolator** that reads messages from your resource bundles.
- Captures client locale from `Accept-Language` header via a lightweight JAX-RS filter.
- Propagates the locale to the interpolator using a `ThreadLocal` context.
- No additional configuration required — just include this dependency.

---

## Features

- **Automatic interpolation** of validation messages from `i18n/messages*.properties`.
- **Locale-aware**: uses `Accept-Language` header per request.
- **Hot integration**: automatically wires into Hibernate Validator.
- **Zero config** in your application (unless you want to override defaults).

---

## Installation

Add the repository and dependency:

```xml
<repositories>
  <repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
  </repository>
</repositories>

<dependencies>
  <dependency>
    <groupId>com.github.n4nlabs</groupId>
    <artifactId>quarkus-i18n-validation</artifactId>
    <version>0.0.1</version>
  </dependency>
</dependencies>
```

> This will transitively pull `quarkus-i18n` core.

---

## Usage

1. **Define validation** messages in your resource bundles:

   `src/main/resources/i18n/messages.properties`:
   ```properties
     user.name.not-blank=The name cannot be blank.
    ```
   
    `src/main/resources/i18n/messages_pt_BR.properties`:
    ```properties
     user.name.not-blank=O nome não pode ser em branco
    ```
3. **Annotate your class**:
   ```java
   public class User {
   
       @NotBlank(message = "{user.name.not-blank}")
       private String name;
   
       // ...
   }
   ```
4. **Send requests** with `Accept-Language: pt-BR` or any supported tag — validation error messages will appear in the correct language.

---

## How It Works

- **LocaleThreadFilter** reads the `Accept-Language` header and sets it in a `ThreadLocal`.
- **CustomMessageInterpolator** fetches the locale from the `ThreadLocal` and looks up the message key in your bundles.
- **ResourceBundle** fallback to `messages.properties` ensures a default message if translation is missing.

---

## Core

The core internationalization support is provided by the **quarkus-i18n** module. It includes utilities for resolving localized messages and is designed to be lightweight and extensible.  
See: https://github.com/n4nlabs/quarkus-i18n

---

## License

Apache License 2.0. See [LICENSE](LICENSE).
