package top.jjmaps.service.filter;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.bind.annotation.CrossOrigin;
import top.jjmaps.entity.User;
import top.jjmaps.utils.JwtUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 处理登陆成功(返回token)
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private JwtUtil jwtUtil;

    @CrossOrigin
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        JSONObject jo = new JSONObject();
        User user = (User) authentication.getPrincipal();
        String jwtToken = jwtUtil.generateToken(user);
        jo.put("code", 2000);
        jo.put("token", jwtToken);    //返回token    下次登录在headers里面添加token信息即可解析
        response.setCharacterEncoding("utf-8");
        response.getWriter().write(jo.toJSONString());
    }
}
