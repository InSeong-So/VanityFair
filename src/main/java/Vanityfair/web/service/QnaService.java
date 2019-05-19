package Vanityfair.web.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import Vanityfair.web.domain.qna.Answer;
import Vanityfair.web.domain.qna.Question;
import Vanityfair.web.domain.qna.QuestionRepository;
import Vanityfair.web.domain.user.User;
import lombok.extern.slf4j.Slf4j;

@Service("qnaService")
@Slf4j
public class QnaService
{
    @Resource(name = "questionRepository")
    private QuestionRepository questionRepository;
    
    public void create(User loginUser, Question question)
    {
        question.writeBy(loginUser);
        log.debug("question : {}", question);
        questionRepository.save(question);
    }
    
    public Question findById(long id)
    {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent())
        {
            return question.get();
        }
        else
        {
            return null;
        }
    }
    
    public Question update(User loginUser, long id, Question updatedQuestion)
    {
        Optional<Question> question = this.questionRepository.findById(id);
        if (question.isPresent())
        {
            Question getQuestion = question.get();
            getQuestion.update(loginUser, updatedQuestion);
            return questionRepository.save(getQuestion);
        }
        else
        {
            return null;
        }
    }
    
    public List<Question> findAll()
    {
        return questionRepository.findAll();
    }
    
    public Question addAnswer(User loginUser, long questionId, String contents)
    {
        Answer answer = new Answer(loginUser, contents);
        Optional<Question> question = this.questionRepository.findById(questionId);
        if (question.isPresent())
        {
            Question getQuestion = question.get();
            getQuestion.addAnswer(answer);
            return questionRepository.save(getQuestion);
        }
        else
        {
            return null;
        }
    }
}
