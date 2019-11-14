package cn.scj.y2xm.controller;

import cn.scj.y2xm.entity.Article;
import cn.scj.y2xm.entity.Category;
import cn.scj.y2xm.pojo.ResponseCode;
import cn.scj.y2xm.service.ArticleService;
import cn.scj.y2xm.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @author by Shaochenjie
 * @Classname ArticleController
 * @Description TODO
 * @Date 2019/11/13 16:18
 */
@Controller
@RequestMapping("article")
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @Resource
    private CategoryService categoryService;

    /**
     * 添加文章
     * @param article
     * @param session
     * @return
     */
    @PostMapping
    @ResponseBody
    public ResponseCode insertArticle(@RequestBody Article article, HttpSession session){
        Object writeId = session.getAttribute("writeId");
        ResponseCode code = new ResponseCode();
        if(writeId==null){
            articleService.insertArticle(article);
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("添加文章成功");
            session.setAttribute("writeId",article.getId());
        }else{
            article.setId(Long.parseLong(writeId.toString()));
            Article article1 = articleService.findById(article.getId());
            article.setCreateTime(article1.getCreateTime());
            article1.setContent(article.getContent());
            article1.setTitle(article.getTitle());
            article1.setCategory(article.getCategory());
            article1.setLabels(article.getLabels());
            articleService.updateArticle(article1);
            code.setCode(ResponseCode.SUCCESS);
            code.setMsg("更新文章成功");
        }
        return code;
    }

    /**
     * 跳转到写文章界面
     * @return
     */
    @RequestMapping("write")
    public String toWrite(HttpSession session, Model model){
        List<Category>categoryList = categoryService.findAll();
        model.addAttribute("cate",categoryList);
        session.removeAttribute("writeId");
        return "write";
    }

    /**
     * 查看文章
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article", article);
        return "articl";
    }



}
