package web.biz.vanityFair.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleComment;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.facade.ArticleInterface;
import web.common.core.component.SisExtends;
import web.common.core.exception.SisCheckedException;
import web.common.core.util.SisSessionUtil;
import web.common.core.util.SisStringUtil;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/articles")
public class ArticleController extends SisExtends {
    private String menuName = "app_articles/articles";

    @Autowired
    private ArticleInterface articleInterface;

    @GetMapping("/{articlesNumber}")
    public ModelAndView articles_left(@PathVariable("articlesNumber") String articlesNumber, @PageableDefault Pageable pageable, Model model) {
        ModelAndView view = new ModelAndView();

        Page<Article> articleList = articleInterface.getArticleList(pageable);

        model.addAttribute("articleList", articleList);

        view.setViewName(menuName);
        view.addObject("leftMenu", articlesNumber);

        return view;
    }

    @GetMapping("/{articlesNumber}/write")
    public ModelAndView articleWrite(@PathVariable("articlesNumber") String articlesNumber, String sendMenu, HttpSession session) {
        ModelAndView view = new ModelAndView();

        if (SisStringUtil.isEmpty(session.getAttribute(SisSessionUtil.USER_SESSION_KEY))) {
            view.setViewName(menuName);
            view.addObject("leftMenu", "need_login");
        } else {
            view.setViewName(menuName);
            view.addObject("leftMenu", "write");
            view.addObject("sendMenu", sendMenu);
        }

        return view;
    }

    // TODO: fileupload implements
    @PostMapping("/{articlesNumber}/registration")
    public ModelAndView articleResigstration(@PathVariable("articlesNumber") String articlesNumber, Article article, @RequestPart MultipartFile uploadFile, HttpSession session) throws IOException {
        ModelAndView view = new ModelAndView();

        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);

        articleInterface.articleResistration(user, article, uploadFile);

        view.setViewName("redirect:/articles/" + articlesNumber);
        view.addObject("leftMenu", articlesNumber);

        return view;
    }

    @GetMapping("/{articlesNumber}/{articleSeqNo}")
    public ModelAndView articleInquiry(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, HttpSession session) {
        ModelAndView view = new ModelAndView();

        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);

        Article article = articleInterface.articleInquiry(Long.parseLong(articleSeqNo));

        if (!SisStringUtil.isEmpty(user) && article.getUserId().equals(user.getUserId()))
            view.addObject("writer_yn", "Y");

        List<ArticleComment> articleComment = articleInterface.getArticleComment(article);

        view.setViewName(menuName);
        view.addObject("article", article);

        if (!SisStringUtil.isNull(article.getFileCd()))
            view.addObject("file", articleInterface.fileInquery(article.getFileCd()));

        view.addObject("commentList", articleComment);
        view.addObject("leftMenu", "detail");

        return view;
    }

    @GetMapping("/{articlesNumber}/{articleSeqNo}/update")
    public ModelAndView articleUpdate(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, String sendMenu, HttpSession session) {
        ModelAndView view = new ModelAndView();

        Article article = articleInterface.articleInquiry(Long.parseLong(articleSeqNo));

        view.setViewName(menuName);
        view.addObject("article", article);

        if (!SisStringUtil.isNull(article.getFileCd()))
            view.addObject("file", articleInterface.fileInquery(article.getFileCd()));

        view.addObject("leftMenu", "update");
        view.addObject("sendMenu", sendMenu);

        return view;
    }

    @PostMapping("/{articlesNumber}/{articleSeqNo}/update")
    public ModelAndView articleUpdateComplete(@PathVariable("articlesNumber") String articlesNumber, @PathVariable("articleSeqNo") String articleSeqNo, String title, String content, String sendMenu, MultipartFile uploadFile, HttpSession session) {
        ModelAndView view = new ModelAndView();

        Article article = articleInterface.articleInquiry(Long.parseLong(articleSeqNo));

        article.setTitle(title);
        article.setContent(content);

        article = articleInterface.articleUpdate(article);

        view.setViewName("redirect:/articles/" + articlesNumber + "/" + articleSeqNo);
        view.addObject("article", article);
        view.addObject("leftMenu", "update");
        view.addObject("sendMenu", sendMenu);

        return view;
    }

    @PostMapping("/{articlesNumber}/comment")
    public ModelAndView articleCommentResistration(@PathVariable("articlesNumber") String articlesNumber, HttpSession session, Article article, String inputComment) throws SisCheckedException {
        ModelAndView view = new ModelAndView();

        User user = (User) session.getAttribute(SisSessionUtil.USER_SESSION_KEY);

        if (SisStringUtil.isEmpty(user)) {
            view.setViewName(menuName);
            view.addObject("leftMenu", "need_login");
            return view;
        }

        try {
            articleInterface.articleCommentResistration(article, user.getUserId(), inputComment);

            view.setViewName("redirect:/articles/" + articlesNumber + "/" + article.getSeqNo());

            view.addObject("leftMenu", "detail");
        } catch (Exception e) {
            throw new SisCheckedException("댓글 등록 중 에러가 발생했습니다. 사유 : " + e.getMessage());
        }

        return view;
    }

    @PostMapping("/{articlesNumber}/update")
    public ModelAndView articleDetailUpdate(@PathVariable("articlesNumber") String articlesNumber, HttpSession session, Article article) {
        ModelAndView view = new ModelAndView();

        return view;
    }
}
