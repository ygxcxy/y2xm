package cn.scj.y2xm.repository;

import cn.scj.y2xm.entity.Article;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by Shaochenjie
 * @Classname ArticleRepository
 * @Description TODO
 * @Date 2019/11/13 16:59
 */
@Repository
public interface ArticleRepository extends CrudRepository<Article,Long> {
}
