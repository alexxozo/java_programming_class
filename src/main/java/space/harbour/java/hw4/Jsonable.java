package space.harbour.java.hw4;

import javax.json.JsonObject;

public interface Jsonable {
    JsonObject toJsonObject();

    String toJsonString();
}
