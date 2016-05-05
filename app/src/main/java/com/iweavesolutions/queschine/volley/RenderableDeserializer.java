package com.iweavesolutions.queschine.volley;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by bharath.simha on 05/15/16.
 */
public class RenderableDeserializer implements JsonDeserializer<Renderable> {

    @Override
    public Renderable deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

        try {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            String vType = jsonObject.get("type").getAsString();
            String PACKAGE_BASE = "com.bigmenu";
            Type objType = Class.forName(PACKAGE_BASE + vType);
            if (vType.equals("ProductBundle")) {
                JsonObject newJsonElement = new JsonObject();
                newJsonElement.add("productBundles", jsonElement);
                jsonElement = newJsonElement;
            }
            return jsonDeserializationContext.deserialize(jsonElement, objType);
        } catch (Exception e) {
            Log.d("Test", e.toString());
            return null;
        }

    }
}
