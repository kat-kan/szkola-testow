package module3;

import java.util.*;
import java.util.stream.Collectors;

public class NameInverter {
    public static List<String> honorifics = List.of("pani", "pan");

    public String invert(String name) {
        if (name == null) {
            throw new NullPointerException();
        }
        if ("".equals(name.trim())) {
            return "";
        } else {
            return getInvertedNameElements(name);
        }

    }

    private String getInvertedNameElements(String name) {
        String[] nameElements = name.split(" ");
        List<String> result = Arrays.stream(nameElements).filter(c -> !honorifics.contains(c.toLowerCase(Locale.ROOT)))
                .collect(Collectors.collectingAndThen(Collectors.toList(), c -> {Collections.reverse(c);
                    return c;
                }));
        return String.join(", ", result);
    }
}
