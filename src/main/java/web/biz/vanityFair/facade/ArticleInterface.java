package web.biz.vanityFair.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleComment;
import web.biz.vanityFair.domain.file.File;
import web.biz.vanityFair.domain.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * 1. 게시글을 등록한다.
 * 2. 게시글을 수정한다.
 * 3. 게시글을 삭제한다.
 * 4. 게시글을 조회한다.
 * 5. 게시글에 댓글을 단다.
 * 6. 게시글의 댓글을 수정한다.
 * 7. 게시글의 댓글을 삭제한다.
 */
public interface ArticleInterface {
    // 게시글 리스트 출력
    Page<Article> getArticleList(Pageable pageable);

    // 글 읽기
    Article articleInquiry(long seqNo, HttpServletRequest request, HttpServletResponse response);

    // 게시글 찾기
    Article getArticleByArticleCd(String articleCd);

    // 글쓰기
    boolean articleResistration(User user, Article article, MultipartFile file);

    // 글 수정
    Article articleUpdate(Article article);

    // 글 삭제
    void articleDelete(Article article);

    // 댓글 리스트 출력
    List<ArticleComment> getArticleComment(Article article);

    // 댓글 달기
    void articleCommentResistration(Article article, String userId, String comment);

    // 댓글 수정
    void articleCommentUpdate(long seqNo, User user, Article article);

    // 댓글 삭제
    void articleCommentDelete(Article article, String userId, long commentNo);

    // 파일 검색
    File fileInquery(String fileCd);
}
