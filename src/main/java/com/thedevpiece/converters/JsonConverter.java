package com.thedevpiece.converters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import spark.ResponseTransformer;

import java.io.IOException;
import java.util.function.Function;

import static com.thedevpiece.utils.ExceptionUtils.doThrow;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class JsonConverter {
    private static final ObjectMapper mapper = new ObjectMapper();

    public static final ResponseTransformer jsonTransformer =
            t -> {
                try {
                    return mapper.writeValueAsString(t);
                } catch (JsonProcessingException e) {
                    doThrow(e);
                    return null;
                }
            };

    public static <T> Function<String, T> convertToObject(Class<T> type) {
        return t -> {
            try {
                return mapper.readValue(t, type);
            } catch (IOException e) {
                doThrow(e);
                return null;
            }
        };
    }
}
