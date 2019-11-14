package cn.scj.y2xm.repository;

import cn.scj.y2xm.entity.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author by Shaochenjie
 * @Classname CategoryRepository
 * @Description TODO
 * @Date 2019/11/13 20:50
 */
@Repository
public interface CategoryRepository extends CrudRepository<Category,Long> {
}
