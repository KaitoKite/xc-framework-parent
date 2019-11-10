package com.xuecheng.api.cms;

import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.model.request.QueryPageRequest;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "cms页面管理接口",description = "cms页面管理接口,提供页面曾删查改")
public interface CmsPageControllerApi {
    /**
     * 分页查询
     * @param page
     * @param size
     * @param queryPageRequest
     * @return
     */
    @ApiOperation("分页查询列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page",value = "页码",required = true,paramType = "path",dataType = "int"),
            @ApiImplicitParam(name = "size",value = "每页数量",required = true,paramType = "path",dataType = "int")
    })
    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest);


    /**
     * 新增页面
     * @param cmsPage
     * @return
     */
    public CmsPageResult addPage(CmsPage cmsPage);


    /**
     * 修改页面
     * @param cmsPage
     * @return
     */
    public CmsPageResult updatePage(String id,CmsPage cmsPage);


    /**
     * 删除页面
     * @param id
     * @return
     */
    public ResponseResult deletePage(String id);




}
