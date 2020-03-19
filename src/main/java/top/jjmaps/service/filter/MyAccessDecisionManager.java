package top.jjmaps.service.filter;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            // 该资源所需要的角色
            String needRole = configAttribute.getAttribute();
            // 获取用户所拥有的角色列表
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                // 将资源所需要的角色与用户拥有的角色比较
                if (needRole.equals(authority.getAuthority())) {
                    // 角色相同，直接放行
                    return;
                }
            }
        }
        throw new AccessDeniedException("no authentication!");
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
