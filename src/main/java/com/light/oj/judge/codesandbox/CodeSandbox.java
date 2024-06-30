package com.light.oj.judge.codesandbox;

import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;

/**
 * 代码沙箱
 *
 * @author null&&
 * @Date 2024/6/21 19:00
 */
public interface CodeSandbox {
    /**
     * 执行代码
     *
     * @param executeRequest
     * @return
     */
    ExecuteCodeResponse executeCode(ExecuteCodeRequest executeRequest);
}
