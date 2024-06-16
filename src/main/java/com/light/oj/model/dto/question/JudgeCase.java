package com.light.oj.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 *
 * @author null&&
 * @Date 2024/6/16 16:26
 */
@Data
public class JudgeCase {
    /**
     * 输入用例
     */
    private String input;
    /**
     * 输出用例
     */
    private String output;
}
