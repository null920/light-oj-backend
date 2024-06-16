package com.light.oj.model.dto.question;

import lombok.Data;

/**
 * 题目配置
 * @author null&&
 * @Date 2024/6/16 16:28
 */
@Data
public class JudgeConfig {
    /**
     * 时间限制（ms）
     */
    private Long timeLimit;
    /**
     * 内存限制（KB）
     */
    private Long memoryLimit;

    /**
     * 堆栈限制（KB）
     */
    private Long stackLimit;

}
