package org.argszero.ec2.gistalk.dao;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by shaoaq on 3/1/15.
 */
@Entity
@Table(indexes = {@Index(columnList = "geoHash")})
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column
    private Long id;
    @Column
    private String geoHash;
    @Column
    private Double latitude;
    @Column
    private Double longitude;
    @Column
    private String content;
    @Column
    private String clientId;
    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGeoHash() {
        return geoHash;
    }

    public void setGeoHash(String geoHash) {
        this.geoHash = geoHash;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}

