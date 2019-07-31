package com.xuecheng.cms_manage.controller;

import com.xuecheng.api.cms.CmsPageControllerApi;
import com.xuecheng.cms_manage.service.CmsPageService;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cms/page")
public class CmsPageController implements CmsPageControllerApi {

    @Autowired
    private CmsPageService cmsPageService;

    @Override
    @GetMapping("/list/{page}/{size}")
    public QueryResponseResult findList(@PathVariable("page") int page, @PathVariable("size") int size, QueryPageRequest queryPageRequest) {

        return cmsPageService.findList(page, size, queryPageRequest);
    }

    @Override
    @PostMapping("/addPage")
    public CmsPageResult addPage(@RequestBody  CmsPage cmsPage) {
        return cmsPageService.addPage(cmsPage);
    }

    @Override
    @PostMapping("/updatePage")
    public CmsPageResult updatePage(@RequestBody CmsPage cmsPage) {
        return cmsPageService.updatePage(cmsPage);
    }

    @Override
    @DeleteMapping("/deletePage/{id}")
    public ResponseResult deletePage(@PathVariable("id") String id) {
        return cmsPageService.deletePage(id);
    }


    @GetMapping("/test/{name}")
    public String test(@PathVariable("name") String name) {

        return cmsPageService.test(name);
    }

}
