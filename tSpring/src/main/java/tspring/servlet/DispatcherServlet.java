package tspring.servlet;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: tangxiaoshuang
 * @date: 2018/10/30 11:00
 * @desc:
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        dispatcher(req, resp);
    }

    /**
     * 分发请求
     *
     * @param req
     * @param resp
     */
    private void dispatcher(HttpServletRequest req, HttpServletResponse resp) {
        String uri = req.getRequestURI();
        HandlerAdaper handlerAdapter = HandlerMapping.getHandlerAdapter(uri);

    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }
}
