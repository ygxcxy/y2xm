package cn.scj.y2xm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author by Shaochenjie
 * @Classname Article
 * @Description TODO
 * @Date 2019/11/13 16:25
 */
@Entity
@Setter
@Getter
public class Article implements Serializable {

    @Id
    @GenericGenerator(strategy = "increment",name = "increment")
    @GeneratedValue(generator = "increment")
    private Long id;

    /**
     * 标题
     */
    private String title;

    /**
     * 摘要
     */
    private String Summary;

    /**
     * 内容
     */
    private String content;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "article_lab",joinColumns ={@JoinColumn(name = "article_id")},
            inverseJoinColumns = {@JoinColumn(name = "label_id")})
    private Set<Label> labels = new HashSet<>();
}
