package com.light.oj.judge;

import com.light.oj.judge.strategy.DefaultJudgeStrategy;
import com.light.oj.judge.strategy.JavaLanguageJudgeStrategy;
import com.light.oj.judge.strategy.JudgeContext;
import com.light.oj.judge.strategy.JudgeStrategy;
import com.light.oj.judge.codesandbox.model.JudgeInfo;
import com.light.oj.model.entity.QuestionSubmit;
import com.light.oj.model.enums.QuestionSubmitLanguageEnum;
import org.springframework.stereotype.Service;

/**
 * 判题管理（简化判题策略的调用）
 *
 * @author null&&
 * @Date 2024/6/23 17:08
 */
@Service
public class JudgeManager {

    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if (QuestionSubmitLanguageEnum.JAVA.getValue().equals(language)) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
