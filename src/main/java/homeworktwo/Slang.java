package homeworktwo;

import java.util.Map;

public class Slang {

    private Map<String, String> slangDict = Map.of(
            "PLZ", "please",
            "FYI", "for your information",
            "GTFO", "please, leave me alone",
            "ASAP", "as soon as possible",
            ":\\)", "[smiling]",
            ":\\(", "[sad]",
            "¯\\\\_\\(ツ\\)\\_/¯", "[such is life]"
    );

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
