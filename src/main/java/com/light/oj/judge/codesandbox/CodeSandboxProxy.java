package com.light.oj.judge.codesandbox;

import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 代码沙箱代理
 *
 * @author null&&
 * @Date 2024/6/21 22:50
 */
@Slf4j
public class CodeSandboxProxy implements CodeSandbox {

    private final CodeSandbox codeSandbox;

    public CodeSandboxProxy(CodeSandbox codeSandbox) {
        this.codeSandbox = codeSandbox;
    }

    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        log.info("代码沙箱请求开始");
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeRequest);
        log.info("代码沙箱请求结束");
        return executeCodeResponse;
    }
}
