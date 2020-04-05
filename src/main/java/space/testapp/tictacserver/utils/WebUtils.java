package space.testapp.tictacserver.utils;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;

import static org.apache.logging.log4j.util.Strings.isBlank;

@Slf4j
public class WebUtils {
    public static String getClientIp(HttpServletRequest request) {
        String remoteAddr = "";
        if (request != null) {
            remoteAddr = request.getHeader("X-FORWARDED-FOR");
            if (isBlank(remoteAddr)) {
                remoteAddr = request.getRemoteAddr();
            }
        } else {
            log.warn("couldn't get client ip because of request is null");
        }
        if (isBlank(remoteAddr)) {
            log.warn("couldn't get ip");
        }
        return remoteAddr;
    }
}
