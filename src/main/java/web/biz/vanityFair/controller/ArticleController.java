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

import core.util.VanityFairSessionUtil;
import core.util.VanityFairStringUtil;
import lombok.extern.slf4j.Slf4j;
import web.biz.vanityFair.domain.article.child.ArticleAttribute;
import web.biz.vanityFair.domain.article.child.ArticleComment;
import web.biz.vanityFair.domain.article.child.ArticleDetail;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.service.article.ArticleService;
import web.common.core.component.VanityFairExtends;
import web.common.core.exception.VanityfairCException;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController extends VanityFairExtends
{
    private String menuName = "app_articles/articles";
    
    @Autowired
    private ArticleService articleService;
    
    @GetMapping("/{articlesNumber}")
    public ModelAndView articles_left(@PathVariable("articlesNumber") String articlesNumber, @PageableDefault Pageable pageable, Model model)
    {
        ModelAndView view = new ModelAndView();
        
        view.setViewName(menuName);
        view.addObject("leftMenu", articlesNumber);
        
        Page<ArticleDetail> articleList = articleService.getArticleList(pageable);
        model.addAttribute("articleList", articleList);
        
        //        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}", articleList.getTotalElements(), articleList.getTotalPages(), articleList.getSize(), articleList.getNumber(), articleList.getNumberOfElements());
        
        return view;
    }
    
    @GetMapping("/{articlesNumber}/write")
    public ModelAndView articleWrite(@PathVariable("articlesNumber") String articlesNumber, String sendMenu, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        if (VanityFairStringUtil.isEmpty(session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY)))
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
    public ModelAndView articleResigstration(@PathVariable("articlesNumber") String articlesNumber, ArticleDetail articleDetail, ArticleAttribute articleAttribute, MultipartFile soruceFile, HttpSession session)
    {
        User user = (User) session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY);
        
        articleService.articleResistration(user, articleDetail, articleAttribute);
        
        ModelAndView view = new ModelAndView();
        
        view.setViewName("redirect:/articles/" + articlesNumber);
        view.addObject("leftMenu", articlesNumber);
        
        return view;
    }
    
    @GetMapping("/{articlesNumber}/{articleSeqNo}")
    public ModelAndView articleDetailView(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, HttpSession session)
    {
        ModelAndView view = new ModelAndView();
        
        ArticleDetail ad = articleService.getArticleDetail(Long.parseLong(articleSeqNo));
        
        List<ArticleComment> ac = articleService.getArticleComment(ad.getArticle());
        
        log.info("ArticleDetail 정보 : " + ad);
        view.setViewName(menuName);
        view.addObject("article", ad);
        view.addObject("commentList", ac);
        view.addObject("leftMenu", "detail");
        
        return view;
    }
    
    @PostMapping("/{articlesNumber}/comment")
    public ModelAndView articleCommentResistration(@PathVariable("articlesNumber") String articlesNumber, HttpSession session, ArticleDetail articleDetail, String inputComment) throws VanityfairCException
    {
        ModelAndView view = new ModelAndView();
        
        if (VanityFairStringUtil.isEmpty(session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY)))
        {
            view.setViewName(menuName);
            view.addObject("leftMenu", "need_login");
            return view;
            //            throw new VanityfairCException("로그인이 필요한 서비스입니다.");
        }
        
        try
        {
            articleService.articleCommentResistration(articleDetail.getSeqNo(), (User) session.getAttribute(VanityFairSessionUtil.USER_SESSION_KEY), inputComment);
            
            view.setViewName("redirect:/articles/" + articlesNumber + "/" + articleDetail.getSeqNo());
            view.addObject("leftMenu", "detail");
        }
        catch (Exception e)
        {
            throw new VanityfairCException("에러가 발생했습니다. 사유 : " + e.getMessage());
        }
        
        return view;
    }
    
    @PostMapping("/{articlesNumber}/update")
    public ModelAndView articleDetailUpdate(@PathVariable("articlesNumber") String articlesNumber, HttpSession session, ArticleDetail articleDetail)
    {
        
        ModelAndView view = new ModelAndView();
        
        return view;
    }
}
