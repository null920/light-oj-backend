package com.light.oj.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.light.oj.model.entity.QuestionSubmit;
import com.light.oj.model.entity.User;

/**
 * @author Ycri
 * @description 针对表【question_submit(题目提交)】的数据库操作Service
 * @createDate 2024-06-15 22:53:37
 */
public interface QuestionSubmitService extends IService<QuestionSubmit> {


    /**
     * 题目提交
     *
     * @param questionId 题目id
     * @param loginUser  登录用户
     * @return 提交id
     */

    int doQuestionSubmit(long questionId, User loginUser);


    /**
     * 题目提交（内部服务）
     *
     * @param userId     用户 id
     * @param questionId 题目 id
     * @return
     */
    int doQuestionSubmitInner(long userId, long questionId);

}
