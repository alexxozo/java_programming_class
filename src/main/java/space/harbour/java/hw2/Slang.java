package space.harbour.java.hw2;

import java.util.HashMap;
import java.util.Map;


public class Slang {
    Map<String, String> slangDict = new HashMap<String, String>();

    public Slang() {
        slangDict.put("PLZ", "please");
        slangDict.put("FYI", "for your information");
        slangDict.put("GTFO", "please, leave me alone");
        slangDict.put("ASAP", "as soon as possible");
        slangDict.put(":\\)", "[smiling]");
        slangDict.put(":\\(", "[sad]");
        slangDict.put("¯\\\\_\\(ツ\\)\\_/¯", "[such is life]");
    }

    public String fixAbbreviations(String sentence) {
        for (Map.Entry<String, String> entry : this.slangDict.entrySet()) {
            sentence = sentence.replaceAll(entry.getKey(), entry.getValue());
        }
        return sentence;
    }

    public static void main(String[] args) {
        Slang slang = new Slang();

        try {
            System.out.println(slang.fixAbbreviations("FYI my dog is very :(. ¯\\_(ツ)_/¯"));
            System.out.println(slang.fixAbbreviations("Hey, Johnny, GTFO! I’m too tired :)"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
