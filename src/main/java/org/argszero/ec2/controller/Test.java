package org.argszero.ec2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by shaoaq on 10/29/14.
 */
@Controller
@RequestMapping("/test")
public class Test {
    @RequestMapping(value = "/a", method = RequestMethod.GET)
    @ResponseBody
    public String createNewBook() throws SecExceptionController.SecException {
        throw  new SecExceptionController.SecException();

    }
    @RequestMapping(value = "/b", method = RequestMethod.GET)
    @ResponseBody
    public String b(HttpServletResponse response) throws IOException {
        response.getWriter().write("aaaaaaaa");
        return "b";
    }
}
