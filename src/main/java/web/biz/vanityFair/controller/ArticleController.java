package web.biz.vanityFair.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleComment;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.service.article.ArticleService;
import web.common.core.component.SisExtends;
import web.common.core.exception.SisCheckedException;
import web.common.core.util.SisSessionUtil;
import web.common.core.util.SisStringUtil;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController extends SisExtends
{
    private String menuName = "app_articles/articles";
    
    @Autowired
    private ArticleService articleService;
    
    @GetMapping("/{articlesNumber}")
    public ModelAndView articles_left(@PathVariable("articlesNumber") String articlesNumber, @PageableDefault Pageable pageable, Model model)
    {
        ModelAndView view = new ModelAndView();
        
        Page<Article> articleList = articleService.getArticleList(pageable);
        
        model.addAttribute("articleList", articleList);
        
        view.setViewName(menuName);
        view.addObject("leftMenu", articlesNumber);
        
        return view;
    }
    
    @GetMapping("/{articlesNumber}/write")
    public ModelAndView articleWrite(@PathVariable("articlesNumber") String articlesNumber, String sendMenu, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        if (SisStringUtil.isEmpty(session.getAttribute(SisSessionUtil.USER_SESSION_KEY)))
        {
            view.setViewName(menuName);
            view.addObject("leftMenu", "need_login");
        }
        else
        {
            view.setViewName(menuName);
            view.addObject("leftMenu", "write");
            view.addObject("sendMenu", sendMenu);
        }
        
        return view;
    }
    
    @PostMapping("/{articlesNumber}/registration")
    public ModelAndView articleResigstration(@PathVariable("articlesNumber") String articlesNumber, Article article, MultipartFile soruceFile, HttpSession session)
    {
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        log.info(article.toString());
        
        articleService.articleResistration(user, article);
        
        ModelAndView view = new ModelAndView();
        
        view.setViewName("redirect:/articles/" + articlesNumber);
        view.addObject("leftMenu", articlesNumber);
        
        return view;
    }
    
    @GetMapping("/{articlesNumber}/{articleSeqNo}")
    public ModelAndView articleInquiry(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        Article article = articleService.articleInquiry(Long.parseLong(articleSeqNo));
        
        if (!SisStringUtil.isEmpty(user) && article.getUserId().equals(user.getUserId()))
            view.addObject("writer_yn", "Y");
        
        List<ArticleComment> articleComment = articleService.getArticleComment(article);
        
        view.setViewName(menuName);
        view.addObject("article", article);
        view.addObject("commentList", articleComment);
        view.addObject("leftMenu", "detail");
        
        return view;
    }
    
    @GetMapping("/{articlesNumber}/{articleSeqNo}/update")
    public ModelAndView articleUpdate(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, String sendMenu, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        Article article = articleService.articleInquiry(Long.parseLong(articleSeqNo));
        
        view.setViewName(menuName);
        view.addObject("article", article);
        view.addObject("leftMenu", "update");
        view.addObject("sendMenu", sendMenu);
        
        return view;
    }
    
    @PostMapping("/{articlesNumber}/{articleSeqNo}/update")
    public ModelAndView articleUpdateComplete(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, String title, String content, String sendMenu, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        Article article = articleService.articleInquiry(Long.parseLong(articleSeqNo));
        
        article.setTitle(title);
        article.setContent(content);
        
        article = articleService.articleUpdate(article);
        
        view.setViewName("redirect:/articles/" + articlesNumber + "/" + articleSeqNo);
        view.addObject("article", article);
        view.addObject("leftMenu", "update");
        view.addObject("sendMenu", sendMenu);
        
        return view;
    }
    
    @PostMapping("/{articlesNumber}/comment")
    public ModelAndView articleCommentResistration(@PathVariable("articlesNumber") String articlesNumber, HttpSession session, Article article, String inputComment) throws SisCheckedException
    {
        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);
        
        ModelAndView view = new ModelAndView();
        
        if (SisStringUtil.isEmpty(user))
        {
            view.setViewName(menuName);
            view.addObject("leftMenu", "need_login");
            return view;
        }
        
        try
        {
            articleService.articleCommentResistration(article, user.getUserId(), inputComment);
            
            view.setViewName("redirect:/articles/" + articlesNumber + "/" + article.getSeqNo());
            
            view.addObject("leftMenu", "detail");
        }
        catch (Exception e)
        {
            throw new SisCheckedException("댓글 등록 중 에러가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        return view;
    }
    
    @PostMapping("/{articlesNumber}/update")
    public ModelAndView articleDetailUpdate(@PathVariable("articlesNumber") String articlesNumber, HttpSession session, Article article)
    {
        
        ModelAndView view = new ModelAndView();
        
        return view;
    }
}
