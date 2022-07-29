package module3;

import java.util.ArrayList;
import java.util.List;

public class NameInverter {
    public String invert(String name) {
        if (name == null){
            throw new NullPointerException();
        }
        if ("".equals(name.trim())){
            return "";
        } else {
            return getInvertedElements(name);
        }

    }

    private String getInvertedElements(String name){
        String[] nameElements = name.split(" ");
        List<String> result = new ArrayList<>();
        for (int i = nameElements.length-1; i >= 0; i--) {
            result.add(nameElements[i]);
        }
        return String.join(", ", result);
    }
}
