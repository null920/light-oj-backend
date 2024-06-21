package com.light.oj.judge.codesandbox.impl;

import com.light.oj.judge.codesandbox.CodeSandbox;
import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 示例代码沙箱（仅为了跑通业务流程）
 *
 * @author null&&
 * @Date 2024/6/21 19:15
 */
public class ExampleCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("示例代码沙箱");

        return null;
    }
}
