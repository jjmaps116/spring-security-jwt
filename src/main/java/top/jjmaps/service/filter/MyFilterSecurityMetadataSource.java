package top.jjmaps.service.filter;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import top.jjmaps.utils.UrlToRole;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自定义权限数据源  将角色所拥有的权限信息设置成相应格式已用于security比对
 */
public class MyFilterSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    /**
     * 具体设置方法
     * @param object
     * @return 返回相应的格式
     * @throws IllegalArgumentException
     */
    @Override
    public List<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        FilterInvocation fi = (FilterInvocation) object;
        //获取请求的url
        String requestUrl = fi.getRequestUrl();
        PathMatcher pathMatcher = new AntPathMatcher();
        List<ConfigAttribute> attributes = new ArrayList<>();
        //访问常量类获取信息
        for (Map.Entry<String, List<String>> entry : UrlToRole.urlRole.entrySet()) {
            if (pathMatcher.match(entry.getKey(), requestUrl)) {
                for (String s : entry.getValue()) {
                    attributes.addAll(SecurityConfig.createListFromCommaDelimitedString(s));
                }
            }
        }
        return attributes;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}
