package cn.scj.y2xm.repository;

import cn.scj.y2xm.entity.Label;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author by Shaochenjie
 * @Classname LabelRepository
 * @Description TODO
 * @Date 2019/11/13 20:34
 */
@Repository
public interface LabelRepository extends CrudRepository<Label,Long> {
    Label findByName(String name);
}
