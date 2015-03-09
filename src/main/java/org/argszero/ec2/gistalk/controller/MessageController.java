package org.argszero.ec2.gistalk.controller;

import com.github.davidmoten.geo.GeoHash;
import org.argszero.ec2.gistalk.dao.Message;
import org.argszero.ec2.gistalk.dao.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by shaoaq on 3/1/15.
 */
@Controller
public class MessageController {
    private static Logger logger = LoggerFactory.getLogger(MessageController.class);
    @Autowired
    protected MessageRepository messageRepository;

    @RequestMapping("/gistalk/message/add")
    @ResponseBody
    @Transactional
    public long add(String clientId, Double latitude, Double longitude, String content) {
        String geoHash = GeoHash.encodeHash(latitude, longitude);
        Message message = new Message();
        message.setUpdateTime(new Date());
        message.setClientId(clientId);
        message.setGeoHash(geoHash);
        message.setLatitude(latitude);
        message.setLongitude(longitude);
        message.setContent(content);
        Message messageInDb = messageRepository.save(message);
        return messageInDb.getId();
    }

    @RequestMapping("/gistalk/message/list")
    @ResponseBody
    @Transactional
    public List<Message> list(Double latitude, Double longitude) {
        String geoHash = GeoHash.encodeHash(latitude, longitude);
        String base = geoHash.substring(0, 9);//第9位, 加上邻居的话大约为方圆15米以内
        List<String> list = GeoHash.neighbours(base);
        List<Message> messages = messageRepository.findByRange(base + "%", list.get(0) + "%", list.get(1) + "%",
                list.get(2) + "%", list.get(3) + "%", list.get(4) + "%", list.get(5) + "%", list.get(6) + "%",
                list.get(7) + "%", new PageRequest(0, 100));
        return messages;
    }
}
