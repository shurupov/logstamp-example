package com.logging.presentation.logging.starter.cross.identifier;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IdentifierConverter {

    private static final Pattern CAMEL_CASE_PATTERN = Pattern.compile("(?<=[a-z])([A-Z])");

    // camelCase -> kebab-case
    public static String toKebabCase(String camelCase) {
        Matcher matcher = CAMEL_CASE_PATTERN.matcher(camelCase);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "-" + matcher.group(1).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toLowerCase();
    }

    // kebab-case -> camelCase
    public static String toCamelCase(String kebabCase) {
        String[] parts = kebabCase.split("-");
        StringBuilder camel = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            String part = parts[i];
            if (!part.isEmpty()) {
                camel.append(part.substring(0, 1).toUpperCase())
                        .append(part.substring(1).toLowerCase());
            }
        }
        return camel.toString();
    }
}
