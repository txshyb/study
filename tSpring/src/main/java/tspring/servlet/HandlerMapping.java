package tspring.servlet;

import java.util.HashMap;

/**
 * @author: tangxiaoshuang
 * @date: 2018/10/30 11:14
 * @desc: url映射
 */
public class HandlerMapping {

    private static HashMap<String, HandlerAdaper> handlerMapping = new HashMap<>();

    public static HashMap<String, HandlerAdaper> getHandlerMapping() {
        return handlerMapping;
    }

    public static HandlerAdaper getHandlerAdapter(String uri) {
        return handlerMapping.get(uri);
    }

    public static void addHandlerMapping(String uri, HandlerAdaper handlerAdaper) {
        handlerMapping.put(uri, handlerAdaper);
    }
}
