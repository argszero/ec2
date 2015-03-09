package org.argszero.ec2.gistalk.dao;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by shaoaq on 3/1/15.
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
    @Query("from Message where geoHash like ? or geoHash like ? or geoHash like ? or geoHash like ? or geoHash like ? " +
            "or geoHash like ? or geoHash like ? or geoHash like ? or geoHash like ? ")
    List<Message> findByRange(String base, String s0, String s1, String s2, String s3, String s4, String s5, String s6,
                              String s7, PageRequest pageRequest);

}
