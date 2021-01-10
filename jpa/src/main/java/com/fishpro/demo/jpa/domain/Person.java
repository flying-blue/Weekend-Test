package com.fishpro.demo.jpa.domain;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName : Person
 * @Author : Administrator
 * @Date: 2021/1/2 20:35
 * @Description : Person
 */
@Data
@Entity
@Table(name="t_person")
public class Person {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name="person_id",columnDefinition = "varchar(32) COMMENT 'personId'")
    private String personId;
    @Column(name="person_name",columnDefinition = "varchar(200) COMMENT '名字'")
    private String personName;
    @Column(name="address",columnDefinition = "varchar(200) COMMENT '住址'")
    private String address;
    @Column(name="sex",columnDefinition = "int(2) COMMENT '性别'")
    private Integer sex;
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @Column(name="create_time",updatable = false,columnDefinition = "timestamp default current_timestamp COMMENT '创建时间'")
    private Date createTime;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="last_login_time",insertable = false,columnDefinition = "timestamp default current_timestamp COMMENT '更新时间'")
    private Date lastLoginTime;

}
