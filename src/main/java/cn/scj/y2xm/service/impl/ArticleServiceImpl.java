package cn.scj.y2xm.service.impl;

import cn.scj.y2xm.entity.Article;
import cn.scj.y2xm.entity.Category;
import cn.scj.y2xm.entity.Label;
import cn.scj.y2xm.repository.ArticleRepository;
import cn.scj.y2xm.repository.CategoryRepository;
import cn.scj.y2xm.repository.LabelRepository;
import cn.scj.y2xm.service.ArticleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author by Shaochenjie
 * @Classname ArticleServiceImpl
 * @Description TODO
 * @Date 2019/11/13 17:00
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleRepository articleRepository;
    @Resource
    private LabelRepository labelRepository;

    @Resource
    private CategoryRepository categoryRepository;
    @Override
    public void insertArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        //添加标签
        Set<Label> labels = iterable(article.getLabels());
        //处理类型
        Category category = cate(article.getCategory());
        //处理summary
        String summary = dealSummary(article.getContent());
        article.setSummary(summary);
        article.setCategory(category);
        article.setLabels(labels);
        articleRepository.save(article);
    }

    private String dealSummary(String title) {
        String ti = title;
        if(title.length()>50){
            ti = ti.substring(0,50);
        }
        return ti;
    }

    private Category cate(Category category) {
        Optional<Category> byId = categoryRepository.findById(category.getId());
        return byId.get();
    }

    private Set<Label> iterable(Set<Label> labels) {
        Set<Label> labelSet = new HashSet<>();
        for(Label label : labels){
            Label label1 = labelRepository.findByName(label.getName());
            if(label1==null){
                labelRepository.save(label);
                labelSet.add(label);
            }else{
                labelSet.add(label1);
            }
        }
        return labelSet;
    }

    @Override
    public void updateArticle(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        //添加标签
        Set<Label> labels = iterable(article.getLabels());
        //处理类型
        Category category = cate(article.getCategory());
        //处理summary
        String summary = dealSummary(article.getContent());
        article.setSummary(summary);
        article.setCategory(category);
        article.setLabels(labels);
        articleRepository.save(article);
    }

    @Override
    public Article findById(Long id) {
        Optional<Article> byId = articleRepository.findById(id);
        return byId.get();

    }
}
