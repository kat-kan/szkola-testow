package module3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NameInverter {
    public String invert(String name) {
        if (name == null){
            throw new NullPointerException();
        }
        if ("".equals(name.trim())){
            return "";
        } else {
            return getInvertedNameElements(name);
        }

    }

    private String getInvertedNameElements(String name){
        String[] nameElements = name.split(" ");
        nameElements = Arrays.stream(nameElements).filter(c-> !List.of("Pan").contains(c))
                .toArray(String[]::new);
        List<String> result = new ArrayList<>();
        for (int i = nameElements.length-1; i >= 0; i--) {
            result.add(nameElements[i]);
        }
        return String.join(", ", result);
    }
}
