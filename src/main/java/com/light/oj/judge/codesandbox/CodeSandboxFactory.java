package com.light.oj.judge.codesandbox;

import com.light.oj.judge.codesandbox.impl.ExampleCodeSandbox;
import com.light.oj.judge.codesandbox.impl.RemoteCodeSandbox;
import com.light.oj.judge.codesandbox.impl.ThirdPartyCodeSandbox;

/**
 * 代码沙箱工厂（线程安全的单例工厂模式）
 *
 * @author null&&
 * @Date 2024/6/21 19:27
 */
public class CodeSandboxFactory {
    private static volatile CodeSandbox codeSandbox = null;

    public static CodeSandbox newInstance(String type) {
        if (codeSandbox == null) {
            // 添加同步块
            synchronized (CodeSandboxFactory.class) {
                // 双检锁
                if (codeSandbox == null) {
                    codeSandbox = createCodeSandbox(type);
                }
            }
        }
        return codeSandbox;
    }

    // 移动实例化逻辑到单独的方法以便于同步
    private static CodeSandbox createCodeSandbox(String type) {
        CodeSandbox instance;
        switch (type) {
            case "example":
                instance = new ExampleCodeSandbox();
                break;
            case "remote":
                instance = new RemoteCodeSandbox();
                break;
            case "thirdParty":
                instance = new ThirdPartyCodeSandbox();
                break;
            default:
                instance = new ExampleCodeSandbox();
        }
        return instance;
    }
}
