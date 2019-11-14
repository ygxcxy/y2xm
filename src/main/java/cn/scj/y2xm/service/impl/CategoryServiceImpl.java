package cn.scj.y2xm.service.impl;

import cn.scj.y2xm.entity.Category;
import cn.scj.y2xm.repository.CategoryRepository;
import cn.scj.y2xm.service.CategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname CategoryServiceImpl
 * @Description TODO
 * @Date 2019/11/13 20:50
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> findAll() {
        Iterable<Category> all = categoryRepository.findAll();
        List<Category> list = new ArrayList<>();
        Iterator<Category> iterator = all.iterator();
        while (iterator.hasNext()){
            list.add(iterator.next());
        }
        return list;
    }
}
