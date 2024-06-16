-- 创建库
create database if not exists light_oj;

-- 切换库
use light_oj;

-- 用户表
create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    union_id      varchar(256)                           null comment '微信开放平台id',
    mp_open_id    varchar(256)                           null comment '公众号openId',
    user_name     varchar(256)                           null comment '用户昵称',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted       tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (union_id)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 题目表
create table if not exists question
(
    id           bigint auto_increment comment 'id' primary key,
    title        varchar(512)                       null comment '题目标题',
    content      text                               null comment '题目内容',
    tags         varchar(1024)                      null comment '标签列表（JSON 数组）',
    answer       text                               null comment '题目答案',
    submit_num   int      default 0                 not null comment '题目提交数',
    accepted_num int      default 0                 not null comment '题目通过数',
    judge_config text                               null comment '判题配置（JSON 对象）',
    judge_case   text                               null comment '判题用例（JSON 数组）',
    thumb_num    int      default 0                 not null comment '点赞数',
    favour_num   int      default 0                 not null comment '收藏数',
    user_id      bigint                             not null comment '创建用户 id',
    create_time  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted      tinyint  default 0                 not null comment '是否删除',
    index idx_userId (user_id)
) comment '题目' collate = utf8mb4_unicode_ci;

-- 题目提交表
create table if not exists question_submit
(
    id          bigint auto_increment comment 'id' primary key,
    language    varchar(128)                       not null comment '编程语言',
    question_id bigint                             not null comment '题目 id',
    user_id     bigint                             not null comment '提交用户 id',
    code        text                               not null comment '用户代码',
    judge_info  text                               null comment '判题信息（JSON 对象）',
    status      varchar(128)                       not null comment '判题状态（0-待判题 1-判题中 2-成功 3-失败）',
    createTime  datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime  datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted     tinyint  default 0                 not null comment '是否删除',
    index idx_questionId (question_id),
    index idx_userId (user_id)
) comment '题目提交';