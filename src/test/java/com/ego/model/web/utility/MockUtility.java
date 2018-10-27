package com.ego.model.web.utility;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author ego
 * @since 2018-10-27 17:10
 **/
public class MockUtility {
    private static ObjectMapper mapper = new ObjectMapper();

    private static String convertObjectToJson(Object model) {
        String jsonParam;
        try {
            jsonParam = mapper.writeValueAsString(model);
        } catch (Exception e) {
            jsonParam = null;
        }
        return jsonParam;
    }

    public static MockHttpServletRequestBuilder populatePutBuilder(String urlTemplate, Object model,
                                                                   Object... urlVariables) {
        String jsonParam = convertObjectToJson(model);
        return MockMvcRequestBuilders.put(urlTemplate, urlVariables).contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam);
    }

    public static MockHttpServletRequestBuilder populatePostBuilder(String urlTemplate, Object model,
                                                                    Object... urlVariables) {
        String jsonParam = convertObjectToJson(model);
        return MockMvcRequestBuilders.post(urlTemplate, urlVariables).contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam);
    }

    public static MockHttpServletRequestBuilder populateGetBuilder(String urlTemplate, Object model,
                                                                   Object... urlVariables) {
        String jsonParam = convertObjectToJson(model);
        return MockMvcRequestBuilders.get(urlTemplate, urlVariables).contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam);
    }

    public static MockHttpServletRequestBuilder populateGetBuilder(String urlTemplate, Object... urlVariables) {
        return MockMvcRequestBuilders.get(urlTemplate, urlVariables).contentType(MediaType.APPLICATION_JSON);
    }

    public static MockHttpServletRequestBuilder populateDeleteBuilder(String urlTemplate, Object model,
                                                                      Object... urlVariables) {
        String jsonParam = convertObjectToJson(model);
        return MockMvcRequestBuilders.delete(urlTemplate, urlVariables).contentType(MediaType.APPLICATION_JSON)
                .content(jsonParam);
    }
}
