package com.yyd.bytedance.open.demo.controller.query.info;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author yangyidian
 * @date 2020/07/06
 **/
@Data
public class InfoQrCodeQuery {
    /**
     * 入参：current 或 audit 或 latest
     * current 线上版
     * audit 审核版
     * latest 测试版
     */
    @Pattern(regexp = "current|audit|latest", message = "current 线上版 audit 审核版 latest 测试版")
    @NotBlank(message = "版本不能为空")
    private String version;

    /**
     * 小程序启动参数，包含两部分: 页面路径?页面参数
     *
     * 如果设置，则扫描二维码后直接跳转到该页面；否则，跳转到首页。
     */
    private String path;
}
