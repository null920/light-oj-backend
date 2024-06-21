package com.light.oj.judge.codesandbox.impl;

import com.light.oj.judge.codesandbox.CodeSandbox;
import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 远程代码沙箱（实际调用接口的代码沙箱）
 *
 * @author null&&
 * @Date 2024/6/21 19:15
 */
public class RemoteCodeSandbox implements CodeSandbox {
    @Override
    public ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest) {
        System.out.println("远程代码沙箱");

        return null;
    }
}
