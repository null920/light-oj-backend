package com.light.oj.model.dto.questionsubmit;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author null&&
 * @Date 2024/6/16 19:24
 */
@Data
public class QuestionSubmitAddRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 编程语言
     */
    private String language;

    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 提交用户 id
     */
    private Long userId;

    /**
     * 用户代码
     */
    private String code;


}
