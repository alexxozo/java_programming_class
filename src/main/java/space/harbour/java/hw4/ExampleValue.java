package space.harbour.java.hw4;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class ExampleValue implements Jsonable {
    public Integer integer = 10;
    private String string = "ABC";
    protected float floatValue = .9f;
    private InsideClass hiddenClass = new InsideClass();

    static class InsideClass implements Jsonable {
        String string = "XYZ";
        Integer integer = 1050;

        @Override
        public JsonObject toJsonObject() {
            return Json.createObjectBuilder()
                    .add("s", string)
                    .add("i", integer)
                    .build();
        }

        @Override
        public String toJsonString() {
            return toJsonObject().toString();
        }
    }

    @Override
    public JsonObject toJsonObject() {
        return Json.createObjectBuilder()
                .add("i", integer)
                .add("s", string)
                .add("f", floatValue)
                .add("hiddenClass", hiddenClass.toJsonObject())
                .build();
    }

    @Override
    public String toJsonString() {
        return toJsonObject().toString();
    }

    public void fromJson(String json) {
        JsonReader reader = Json.createReader(new StringReader(json));

        JsonObject jsonObject = reader.readObject();
        this.integer = jsonObject.getInt("i");
        this.string = jsonObject.getString("s");
        this.floatValue = (float) jsonObject.getJsonNumber("f").doubleValue();


        JsonObject jsonHiddenObject = jsonObject.getJsonObject("hiddenClass");
        this.hiddenClass = new InsideClass();
        this.hiddenClass.string = jsonHiddenObject.getString("s");
        this.hiddenClass.integer = jsonHiddenObject.getInt("i");
    }

    public static void main(String... args) {
        // Output to json
        ExampleValue value = new ExampleValue();
        System.out.println(value.toJsonString());

        // Get from json
        value.fromJson(value.toJsonString());
        System.out.println(value.toJsonString());
    }
}
