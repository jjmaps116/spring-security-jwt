package top.jjmaps.service.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;
import top.jjmaps.utils.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * jwt拦截器
 */
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //获取请求头部 格式为 Authorization:Bearer(空格)+token
        String authHeader = request.getHeader("Authorization");
        String authToken = "";
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            authToken = authHeader.substring("Bearer ".length());
        } else if (request.getParameter("wsToken") != null && !"".equals(request.getParameter("wsToken"))) {
            authToken = request.getParameter("wsToken");//这一步是为了加入websocket权限验证   因为websocket不支持自定义请求头  现在就将token放入request param中
        }
        if(!"".equals(authToken)) {
            //通过util类解析token获取username
            String username = jwtUtil.getUsernameFromToken(authToken);
            //如果权限为空(有权限说明不需要进一步验证) 且 username不为空
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null&&jwtUtil.getExpirationDateFromToken(authToken).getTime()>new Date().getTime()) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                if (userDetails != null) {
                    //获取具体的权限信息  设置进security上下文
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    request.setAttribute("userDetails", userDetails);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        //继续下一步
        filterChain.doFilter(request, response);
    }
}
