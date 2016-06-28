package com.thedevpiece.converters;

import com.thedevpiece.functions.ComposableFunction;
import org.bson.Document;
import spark.ResponseTransformer;

/**
 * @author Gabriel Francisco <peo_gfsilva@uolinc.com>
 */
public class JsonConverter {
    public static ComposableFunction<String, Document> asDocument = Document::parse;
    public static ResponseTransformer docToJsonTransformer = model -> model == null ? null : ((Document) model).toJson();
}
