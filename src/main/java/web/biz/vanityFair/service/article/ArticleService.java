package web.biz.vanityFair.service.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import web.biz.vanityFair.domain.article.Article;
import web.biz.vanityFair.domain.article.ArticleComment;
import web.biz.vanityFair.domain.file.File;
import web.biz.vanityFair.domain.user.User;
import web.biz.vanityFair.facade.ArticleInterface;
import web.biz.vanityFair.repository.article.ArticleCommentRepository;
import web.biz.vanityFair.repository.article.ArticleRepository;
import web.biz.vanityFair.repository.file.FileRepository;
import web.common.core.component.SisExtends;
import web.common.core.exception.SisRuntimeException;
import web.common.core.util.SisEncUtil;
import web.common.core.util.SisStringUtil;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ArticleService extends SisExtends implements ArticleInterface {
    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ArticleCommentRepository articleCommentRepository;

    @Autowired
    private FileRepository fileRepository;

    @Override
    public Page<Article> getArticleList(Pageable pageable) {
        int page = (pageable.getPageNumber() == 0) ? 0 : (pageable.getPageNumber() - 1);
        pageable = PageRequest.of(page, 5, new Sort(Direction.DESC, "seqNo"));

        return articleRepository.findAll(pageable);
    }

    public Article getArticleByArticleCd(String articleCd) {
        return articleRepository.getArticleByArticleCd(articleCd).get();
    }

    @Override
    public Article articleInquiry(long seqNo) {
        try {
            Optional<Article> article = articleRepository.findBySeqNo(seqNo);

            articleRepository.updateViewCnt(seqNo);

            return article.get();
        } catch (Exception e) {
            throw new SisRuntimeException("조회수 업데이트에 실패하였습니다. 사유 : " + e.getMessage());
        }
    }

    @Override
    public boolean articleResistration(User user, Article article, MultipartFile uploadFile) {
        // 세션값이 존재하지 않으면 튕겨냄
        if (SisStringUtil.isEmpty(user)) {
            throw new IllegalStateException("로그인이 필요한 서비스입니다.");
        }

        try {
            article.setArticleCd(codeCreator(true));
            article.setSeqNo(articleRepository.getSeqNo() + 1);
            article.setUser(user);
            article.setUserId(user.getUserId());
            article.setUserArtiNo(articleRepository.getUserArtiNo(user) + 1);
            article.setRegDateStr(SisStringUtil.getYmd(new SimpleDateFormat("yyyy-MM-dd")));
            article.setArticlePwd(SisEncUtil.SHA256(article.getArticlePwd()));
            article.setArticleDelYn("N");

            if (!SisStringUtil.isEmpty(uploadFile)) {
                // String uploadFileName = uploadFile.getOriginalFilename();
                // String uploadFileNameExtension = FilenameUtils.getExtension(uploadFileName).toLowerCase();
                // String destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + uploadFileNameExtension;
                String uploadFilePath = "/Users/soinseong/Documents/workspace/spring/Project/project-VanityFair/files/upload/images/attachments/";
                java.io.File destinationFile = new java.io.File(uploadFilePath, uploadFile.getOriginalFilename());
                // do {
                //      destinationFileName = RandomStringUtils.randomAlphanumeric(32) + "." + sourceFileNameExtension;
                //      destinationFile = new File("/files/upload/images/attachments/" + destinationFileName);
                // } while (destinationFile.exists());
                // destinationFile.getParentFile().mkdirs();
                uploadFile.transferTo(destinationFile);

                File file = File.builder().fileCategory(article.getArticleCategory()).fileCd(codeCreator(true)).fileNm(uploadFile.getOriginalFilename()).filePath(uploadFilePath).userId(user.getUserId()).build();

                article.setFileCd(fileRepository.save(file).getFileCd());
            }

            articleRepository.save(article);

            return true;

        } catch (Exception e) {
            throw new SisRuntimeException("게시글 등록에 실패하였습니다.");
        }
    }

    @Override
    public Article articleUpdate(Article article) {
        return articleRepository.save(article);
    }

    @Override
    public void articleDelete(Article article) {
        articleCommentRepository.deleteAll(articleCommentRepository.findByArticle(article));

        articleRepository.delete(article);
    }

    @Override
    public List<ArticleComment> getArticleComment(Article article) {
        List<ArticleComment> articleComment = articleCommentRepository.findByArticle(article);

        return articleComment;
    }

    @Override
    public void articleCommentResistration(Article article, String userId, String comment) {
        try {
            ArticleComment articleComment = ArticleComment.builder().article(article).comment(comment).userId(userId).commentNo(articleCommentRepository.getCommentNo(article) + 1).build();

            articleCommentRepository.save(articleComment);

        } catch (Exception e) {
            throw new SisRuntimeException("게시글 등록 중 문제가 발생하였습니다. 사유 : " + e.getMessage());
        }
    }

    @Override
    public void articleCommentUpdate(long seqNo, User user, Article article) {
        // TODO Auto-generated method stub
    }

    @Override
    public void articleCommentDelete(Article article, String userId, long commentNo) {
        articleCommentRepository.setDeleteComment(article, userId, commentNo);
    }

    @Override
    public File fileInquery(String fileCd) {
        try {
            Optional<File> file = fileRepository.findByFileCd(fileCd);

            return file.get();

        } catch (Exception e) {
            throw new SisRuntimeException("등록된 파일 조회에 실패하였습니다. 사유 : " + e.getMessage());
        }
    }
}
