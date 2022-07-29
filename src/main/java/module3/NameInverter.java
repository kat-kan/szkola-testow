package module3;

public class NameInverter {
    public String invert(String name) {
        if (name == null){
            throw new NullPointerException();
        }
        if ("".equals(name.trim())){
            return "";
        } else {
            return name;
        }

    }
}
