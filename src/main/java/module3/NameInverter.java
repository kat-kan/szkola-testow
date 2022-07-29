package module3;

public class NameInverter {
    public String invert(String name) {
        if ("".equals(name.trim())){
            return "";
        }
        throw new NullPointerException();
    }
}
