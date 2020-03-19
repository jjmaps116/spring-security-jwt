package top.jjmaps.service.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理无权登录的情况(禁止登陆)
 */
public class MyAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        JSONObject jo = new JSONObject();
        jo.put("code", 4002);
        jo.put("msg", "no login authentication!");
        response.getWriter().write(jo.toJSONString());
    }
}
