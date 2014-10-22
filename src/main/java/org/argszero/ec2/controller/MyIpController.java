package org.argszero.ec2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoaq on 10/22/14.
 */
@Controller
@RequestMapping("/myip")
public class MyIpController {

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> get(HttpServletRequest request) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("remoteAddr", request.getRemoteAddr());
        result.put("X-Real-IP", request.getHeader("X-Real-IP"));
        result.put("X-Forwarded-For", request.getHeader("X-Forwarded-For"));
        result.put("Proxy-Client-IP", request.getHeader("Proxy-Client-IP"));
        result.put("WL-Proxy-Client-IP", request.getHeader("WL-Proxy-Client-IP"));
        result.put("HTTP_CLIENT_IP", request.getHeader("HTTP_CLIENT_IP"));
        result.put("HTTP_X_FORWARDED_FOR", request.getHeader("HTTP_X_FORWARDED_FOR"));
        return result;
    }
}

