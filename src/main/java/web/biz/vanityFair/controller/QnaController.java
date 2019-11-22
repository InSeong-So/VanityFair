package web.biz.vanityFair.controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import web.common.core.component.SisExtends;

@Controller
@RequestMapping("/qna")
public class QnaController extends SisExtends
{
    private String menuName = "app_qna/qna";
    
//    @Autowired
//    private ArticleService articleService;
    
    @GetMapping("/{qnaNumbers}")
    public ModelAndView articles_left(@PathVariable("qnaNumbers") String qnaNumbers, @PageableDefault Pageable pageable, Model model)
    {
        ModelAndView view = new ModelAndView();
        
        view.setViewName(menuName);
        view.addObject("leftMenu", qnaNumbers);
        
//        Page<Article> articleList = articleService.getArticleList(pageable);
//        model.addAttribute("articleList", articleList);
        
        //        log.info("총 element 수 : {}, 전체 page 수 : {}, 페이지에 표시할 element 수 : {}, 현재 페이지 index : {}, 현재 페이지의 element 수 : {}", articleList.getTotalElements(), articleList.getTotalPages(), articleList.getSize(), articleList.getNumber(), articleList.getNumberOfElements());
        
        return view;
    }
}
