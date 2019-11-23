package web.biz.vanityFair.ajax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.biz.vanityFair.ajax.bean.AjaxResponseBody;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.service.article.ArticleService;
import web.common.core.exception.SisRuntimeException;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AjaxController {
    @Autowired
    ArticleService articleService;

    @PostMapping("/articleDelete")
    public ResponseEntity<?> articleDelete(@RequestBody Map<String, Object> params, Errors errors, HttpSession session) {
        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        try {
            articleService.articleDelete(articleService.articleInquiry((long) params.get("seqNo")));
        } catch (Exception e) {
            throw new SisRuntimeException("게시글 삭제 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }

        result.setMsg("변경 완료!");

        return ResponseEntity.ok(result);
    }

    @PostMapping("/commentDelete")
    public ResponseEntity<?> commentDelete(@RequestBody Map<String, Object> params, Errors errors) {
        AjaxResponseBody result = new AjaxResponseBody();

        if (errors.hasErrors()) {
            result.setMsg(errors.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.joining(",")));
            return ResponseEntity.badRequest().body(result);
        }

        try {
            Article article = articleService.getArticleByArticleCd(String.valueOf(params.get("articleCd")));
            String userId = String.valueOf(params.get("userId"));
            long commentNo = Long.parseLong(String.valueOf(params.get("commentNo")));

            articleService.articleCommentDelete(article, userId, commentNo);

        } catch (Exception e) {
            throw new SisRuntimeException("댓글 삭제 중 오류가 발생했습니다. 사유 : " + e.getMessage());
        }

        result.setMsg("변경 완료!");

        return ResponseEntity.ok(result);
    }
}
