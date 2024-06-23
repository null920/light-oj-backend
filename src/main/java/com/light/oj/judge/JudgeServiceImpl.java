package com.light.oj.judge;

import cn.hutool.json.JSONUtil;
import com.light.oj.common.ErrorCode;
import com.light.oj.exception.BusinessException;
import com.light.oj.judge.codesandbox.CodeSandbox;
import com.light.oj.judge.codesandbox.CodeSandboxFactory;
import com.light.oj.judge.codesandbox.CodeSandboxProxy;
import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.light.oj.judge.strategy.JudgeContext;
import com.light.oj.model.dto.question.JudgeCase;
import com.light.oj.judge.codesandbox.model.JudgeInfo;
import com.light.oj.model.entity.Question;
import com.light.oj.model.entity.QuestionSubmit;
import com.light.oj.model.enums.QuestionSubmitStatusEnum;
import com.light.oj.service.QuestionService;
import com.light.oj.service.QuestionSubmitService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 判题服务实现
 *
 * @author null&&
 * @Date 2024/6/22 18:56
 */
@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    private QuestionService questionService;

    @Resource
    private QuestionSubmitService questionSubmitService;

    @Resource
    private JudgeManager judgeManager;

    @Value("${codesandbox.type}")
    private String type;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {
        // 根据传入的题目提交 id 获取题目的提交，并且判断题目是否存在
        QuestionSubmit questionSubmit = questionSubmitService.getById(questionSubmitId);
        if (questionSubmit == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "判题任务不存在");
        }
        Long questionId = questionSubmit.getQuestionId();
        Question question = questionService.getById(questionId);
        if (question == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR, "题目不存在");
        }
        // 如果状态不为等待中，不能判题
        if (!QuestionSubmitStatusEnum.WAITING.getValue().equals(questionSubmit.getStatus())) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "正在判题中或已结束");
        }
        // 更新题目的提交状态为判题中
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean updateResult = questionSubmitService.updateById(questionSubmitUpdate);
        if (!updateResult) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目提交状态失败");
        }

        // 调用代码沙箱，获取到执行结果
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String language = questionSubmit.getLanguage();
        String code = questionSubmit.getCode();
        // 获取输入用例
        String judgeCase = question.getJudgeCase();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(judgeCase, JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        // 根据沙箱的执行结果，设置题目的判题状态和信息
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(executeCodeResponse.getJudgeInfo());
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestion(question);
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(executeCodeResponse.getOutputList());
        judgeContext.setQuestionSubmit(questionSubmit);
        JudgeInfo judgeInfoResponse = judgeManager.doJudge(judgeContext);

        // 修改数据库中的判题结果
        questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(questionSubmitId);
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfoResponse));
        boolean res = questionSubmitService.updateById(questionSubmitUpdate);
        if (!res) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "更新题目提交状态失败");
        }
        return questionSubmitService.getById(questionSubmitId);
    }
}
