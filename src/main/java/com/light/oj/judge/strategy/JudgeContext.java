package com.light.oj.judge.strategy;

import com.light.oj.model.dto.question.JudgeCase;
import com.light.oj.judge.codesandbox.model.JudgeInfo;
import com.light.oj.model.entity.Question;
import com.light.oj.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 上下文（用于定义在策略模式中要传递的参数）
 *
 * @author null&&
 * @Date 2024/6/23 16:15
 */
@Data
public class JudgeContext {

    private JudgeInfo judgeInfo;

    private List<JudgeCase> judgeCaseList;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private QuestionSubmit questionSubmit;
}
