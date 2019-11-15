package cn.scj.y2xm.controller;

import cn.scj.y2xm.entity.Article;
import cn.scj.y2xm.entity.Category;
import cn.scj.y2xm.pojo.ResponseCode;
import cn.scj.y2xm.service.ArticleService;
import cn.scj.y2xm.service.CategoryService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    @Resource
    private RestTemplate restTemplate;

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
     * 剪切图片
     * @return
     */
    @RequestMapping("pic")
    @ResponseBody
    public ResponseEntity<String> image(@RequestParam("file")MultipartFile multipartFile) throws IOException {
        System.out.println(multipartFile);
        //构建HTTP的头,构造请求，调用第三方服务
        HttpHeaders httpHeaders = new HttpHeaders();
        //文件上传必须是MULTIPART_FORM_DATA,form表单中也有
        httpHeaders.setContentType(MediaType.MULTIPART_FORM_DATA);
        //设置客户端,模拟客户端行为
        httpHeaders.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        //构建一个键值对的形式
        MultiValueMap<String,Object> multiValueMap = new LinkedMultiValueMap<>();
        //获取原始的文件
        Path path = Paths.get(multipartFile.getOriginalFilename());
        //文件上传,可能会有一个问题就是在项目中创建一个image图片，可以在后面直接delete
        multipartFile.transferTo(path);
        // 传递文件，通过网络传递，不能直接使用Java.io.File，而是用FileSystemResource 来充当文件资源对象
        //FileSystemResource需要用到原始的文件对象，而PAth.toFile()可以得到原始的文件对象
        //path.toFile()被当做构造参数
        FileSystemResource resource = new FileSystemResource(path.toFile());
        //将resource添加到构造好的键值对中去,键必须为smfile
        multiValueMap.add("smfile",resource);
        //构造一个HTTp的实体对象,使用数据集multiValueMap，与浏览器行为httpHeaders，一同构造出这个实体对象
        HttpEntity<MultiValueMap<String,Object>>httpEntity = new HttpEntity<>(multiValueMap,httpHeaders);
        //指定第三方调用的服务
        String url ="https://sm.ms/api/v2/upload";
        //使用restTemplate进行交互
        ResponseEntity<String> responseEntity =restTemplate.exchange(url, HttpMethod.POST,httpEntity,String.class);
        return responseEntity;
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
