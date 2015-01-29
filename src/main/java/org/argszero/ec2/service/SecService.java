package org.argszero.ec2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * Created by shaoaq on 10/30/14.
 */
@Service
public class SecService {
    @Autowired()
    private HttpSession session;

    public String checkUser() {
        String user = (String) session.getAttribute("_sec_user");
        if (user == null) {
            throw new SecurityException();
        }
        return user;
    }
}
