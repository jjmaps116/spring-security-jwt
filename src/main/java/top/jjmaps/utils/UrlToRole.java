package top.jjmaps.utils;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class UrlToRole {
    public static Map<String, List<String>> urlRole;

    public static void refresh() {
        urlRole = new HashMap<>();
    }

    @PostConstruct
    public void init() {
        refresh();
    }
}
