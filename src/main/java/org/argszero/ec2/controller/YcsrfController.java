package org.argszero.ec2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by shaoaq on 10/22/14.
 */
@Controller
@RequestMapping("/ycsrf")
public class YcsrfController {
    private Map<String, Input> inputMap = new HashMap<String, Input>();

    @RequestMapping(value = "get", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> get(String user, String pwd) {
        Map<String, Object> result = new HashMap<String, Object>();
        Input input = inputMap.get(user + ":" + pwd);
        if(input!=null){
            result.put("input", "01:"+input.input);
        }
        return result;
    }

    @RequestMapping(value = "put", method = RequestMethod.GET)
    @ResponseBody
    public void get(String user, String pwd, String input) {
        inputMap.put(user + ":" + pwd, new Input(input, System.currentTimeMillis()));
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Input> test(String user, String pwd, String input) {
      return inputMap;
    }

    private static class Input {
        private String input;
        private long time;

        public Input(String input, long time) {
            this.input = input;
            this.time = time;
        }
    }


    public static void main(String[] args) {
        String a = "{\n" +
                "  \"input\" : \"01:abc\"\n" +
                "}";
        System.out.println(a.substring(15, a.length() - 3));
    }
}

