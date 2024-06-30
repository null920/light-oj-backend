package com.light.oj.judge.codesandbox.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.light.oj.common.ErrorCode;
import com.light.oj.exception.BusinessException;
import com.light.oj.judge.codesandbox.CodeSandbox;
import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;
import org.apache.commons.lang3.StringUtils;

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
        String url = "http://localhost:9630/executeCode";
        /*
         * 1. 将用户输入的代码、编程语言、输入数据、题目信息封装成 JSON
         * 2. 调用远程接口
         * 3. 获取返回值
         * 4. 将返回值封装成 ExecuteCodeResponse
         * 5. 返回
         */
        String jsonStr = JSONUtil.toJsonStr(executeRequest);
        String responseStr = HttpUtil.createPost(url)
                .body(jsonStr)
                .execute()
                .body();
        if (StrUtil.isBlank(responseStr)) {
            throw new BusinessException(ErrorCode.API_REQUEST_ERROR, "executeCode remoteSandbox error , message = " + responseStr);
        }
        return JSONUtil.toBean(responseStr, ExecuteCodeResponse.class);
    }
}
