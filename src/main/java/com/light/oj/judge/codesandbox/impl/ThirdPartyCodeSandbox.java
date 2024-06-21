package com.light.oj.judge.codesandbox.impl;

import com.light.oj.judge.codesandbox.CodeSandbox;
import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 第三方代码沙箱（调用网上现成的代码沙箱）
 *
 * @author null&&
 * @Date 2024/6/21 19:15
 */
public class ThirdPartyCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("第三方代码沙箱");
        return null;
    }
}
