package com.light.oj.judge;

import com.light.oj.judge.codesandbox.CodeSandbox;
import com.light.oj.judge.codesandbox.CodeSandboxFactory;
import com.light.oj.judge.codesandbox.CodeSandboxProxy;
import com.light.oj.judge.codesandbox.model.ExecuteCodeRequest;
import com.light.oj.judge.codesandbox.model.ExecuteCodeResponse;
import com.light.oj.model.enums.QuestionSubmitLanguageEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author null&&
 * @Date 2024/6/30 19:32
 */
@SpringBootTest
public class CodeSandboxTest {

    @Value("${codesandbox.type}")
    private String type;

    @Test
    void executeCodeByProxy() {
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = "public class Main {\n" +
                "    public static void main(String[] args) {\n" +
                "        int x = Integer.parseInt(args[0]);\n" +
                "        int y = Integer.parseInt(args[1]);\n" +
                "        System.out.println(\"结果：\" + (x + y));\n" +
                "    }\n" +
                "}";
        String language = QuestionSubmitLanguageEnum.JAVA.getValue();
        List<String> inputList = Arrays.asList("1 2", "3 5");
        ExecuteCodeRequest executeCodeRequest = ExecuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse executeCodeResponse = codeSandbox.executeCode(executeCodeRequest);
        Assertions.assertNotNull(executeCodeResponse);
        System.out.println(executeCodeResponse);
    }
}
