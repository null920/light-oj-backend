package com.light.oj.judge;

import com.light.oj.model.entity.QuestionSubmit;

/**
 * 判题服务
 *
 * @author null&&
 * @Date 2024/6/22 18:49
 */
public interface JudgeService {

    /**
     * 判题
     *
     * @param questionSubmitId 题目提交id
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
