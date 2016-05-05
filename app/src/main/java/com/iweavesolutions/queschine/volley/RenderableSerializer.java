package com.iweavesolutions.queschine.volley;

import android.util.Log;

import com.google.gson.JsonElement;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;

/**
 * Created by bharath.simha on 05/05/16.
 */
public class RenderableSerializer implements JsonSerializer<Renderable> {

    @Override
    public JsonElement serialize(Renderable renderable, Type type, JsonSerializationContext jsonSerializationContext) {
        try {
            String vType = renderable.getType();
            String PACKAGE_BASE = "com.bigmenu";
            Class classType = Class.forName(PACKAGE_BASE + vType);
            return jsonSerializationContext.serialize(classType.cast(renderable));
        } catch (Exception e) {
            Log.d("Test", " exception " + e.toString());
        }
        return null;
    }
}
