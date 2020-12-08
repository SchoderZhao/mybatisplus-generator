package com.msunsoft.sptapi.user.controller;

import org.springframework.web.bind.annotation.RequestMapping;

    import org.springframework.web.bind.annotation.RestController;
    import com.msunsoft.commons.base.BaseController;

/**
* <p>
    * 文章表 前端控制器
    * </p>
*
* @author zxl
* @since 2020-08-26
*/
    @RestController
@RequestMapping("//core-post")
        public class CorePostController extends BaseController<CorePost, ICorePostService> {
    }
