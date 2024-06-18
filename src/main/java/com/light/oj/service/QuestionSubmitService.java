package com.light.oj.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.light.oj.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.light.oj.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.light.oj.model.entity.QuestionSubmit;
import com.light.oj.model.entity.User;
import com.light.oj.model.vo.QuestionSubmitVO;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Ycri
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-06-15 22:53:37
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {

    /**
     * 校验
     *
     * @param questionSubmit 提交信息
     * @param add
     */
    void validQuestionSubmit(QuestionSubmit questionSubmit, boolean add);


    /**
     * 题目提交
     *
     * @param questionSubmitAddRequest 题目信息
     * @param loginUser                登录用户
     * @return 提交id
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionSubmitAddRequest, User loginUser);


    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest 查询条件封装类
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);

    /**
     * 获取题目封装
     *
     * @param questionSubmit
     * @param request
     * @return
     */
    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, HttpServletRequest request);

    /**
     * 分页获取题目封装
     *
     * @param questionSubmitPage
     * @param request
     * @return
     */
    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, HttpServletRequest request);
}
