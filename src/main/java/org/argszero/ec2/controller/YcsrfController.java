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
@RequestMapping("/ycsrf")
public class YcsrfController {

    @RequestMapping( value ="get", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> get(String user,String pwd) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("input","01:abc");
        return result;
    }
}

