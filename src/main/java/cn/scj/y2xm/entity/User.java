package cn.scj.y2xm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author by Shaochenjie
 * @Classname User
 * @Description TODO
 * @Date 2019/11/13 16:32
 */
@Entity
@Setter
@Getter
public class User implements Serializable {
    @Id
    @GenericGenerator(strategy = "increment",name = "increment")
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "user_name")
    private String userName;

    private String password;

    private String name;

}
