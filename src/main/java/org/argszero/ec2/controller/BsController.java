package org.argszero.ec2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shaoaq on 3/27/15.
 */
@Controller
@RequestMapping("/bs")
public class BsController {

    @RequestMapping(value = "get",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> get(@RequestParam(required = false) Long id, @RequestParam(required = false) Long index, HttpSession session) {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Map<String, Object>> itemList = new ArrayList<Map<String, Object>>();
        for (int i = 0; i < 4; i++) {
            add(itemList, Math.random() + "", true);
        }
        result.put("data", itemList);
        result.put("success", true);
        return result;
    }

    private void add(List<Map<String, Object>> itemList, String item, boolean isTarget) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("item", item);
        map.put("isTarget", isTarget);
        itemList.add(map);
    }
}