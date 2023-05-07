package gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.time.LocalDateTime;

public abstract class GsonContainer {
    private static Gson gson;

    static {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDateTime.class, LocalDateTimeSerializer.getInstance());
        gsonBuilder.setPrettyPrinting();
        gson = gsonBuilder.create();
    }


    public static Gson getGson() {
        return gson;
    }
}
