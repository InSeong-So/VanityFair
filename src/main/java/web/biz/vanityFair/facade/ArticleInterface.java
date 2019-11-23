package web.biz.vanityFair.facade;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleComment;
import web.biz.vanityFair.domain.user.User;

import java.util.List;

public interface ArticleInterface {
    // 게시글 리스트 출력
    public Page<Article> getArticleList(Pageable pageable);

    // 글 읽기
    public Article articleInquiry(long seqNo);

    // 글쓰기
    public boolean articleResistration(User user, Article article);

    // 글 수정
    public Article articleUpdate(Article article);

    // 글 삭제
    public void articleDelete(Article article);

    // 댓글 리스트 출력
    public List<ArticleComment> getArticleComment(Article article);

    // 댓글 달기
    public void articleCommentResistration(Article article, String userId, String comment);

    // 댓글 수정
    public void articleCommentUpdate(long seqNo, User user, Article article);

    // 댓글 삭제
    public void articleCommentDelete(Article article, String userId, long commentNo);

}
