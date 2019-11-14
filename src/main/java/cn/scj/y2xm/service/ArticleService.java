package cn.scj.y2xm.service;

import cn.scj.y2xm.entity.Article;

/**
 * @author by Shaochenjie
 * @Classname ArticleService
 * @Description TODO
 * @Date 2019/11/13 17:00
 */
public interface ArticleService {
    void insertArticle(Article article);

    void updateArticle(Article article);

    Article findById(Long id);
}
