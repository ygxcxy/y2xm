package cn.scj.y2xm.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author by Shaochenjie
 * @Classname Label
 * @Description TODO
 * @Date 2019/11/13 20:08
 */
@Entity
@Setter
@Getter
public class Label implements Serializable {
    @Id
    @GenericGenerator(strategy = "increment",name = "increment")
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(nullable = false,unique = true)
    private String name;

    @ManyToMany
    @JoinTable(name = "article_lab",joinColumns ={@JoinColumn(name = "label_id")},
            inverseJoinColumns = {@JoinColumn(name = "article_id")})
    private Set<Article> articles;
}
