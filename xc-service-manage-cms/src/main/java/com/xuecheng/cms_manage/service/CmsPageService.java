package com.xuecheng.cms_manage.service;

import com.xuecheng.cms_manage.dao.CmsPageResponsitory;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.domain.cms.response.CmsCode;
import com.xuecheng.framework.domain.cms.response.CmsPageResult;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.request.QueryPageRequest;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.framework.model.response.QueryResponseResult;
import com.xuecheng.framework.model.response.QueryResult;
import com.xuecheng.framework.model.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CmsPageService {

    @Autowired
    private CmsPageResponsitory cmsPageResponsitory;

    public QueryResponseResult findList(int page, int size, QueryPageRequest queryPageRequest) {
        if (queryPageRequest==null){
            queryPageRequest = new QueryPageRequest();
        }

        if (page<=0){
            page=1;
        }
        if (size<=0){
            size = 20;
        }
        PageRequest pageRequest = new PageRequest(page, size);
        Page<CmsPage> all = cmsPageResponsitory.findAll(pageRequest);
        QueryResult<CmsPage> queryResult = new QueryResult<>();
        queryResult.setList(all.getContent());
        queryResult.setTotal(all.getTotalElements());
        return new QueryResponseResult(CommonCode.SUCCESS,queryResult);


    }

    public String test(String name){
        CmsPage byPageName = cmsPageResponsitory.findByPageName(name);
        return byPageName.getPageName();
    }

    public CmsPageResult addPage(CmsPage cmsPage){
        //判断是否存在
        //校验页面名称、站点Id、页面webpath的唯一性
        CmsPage result = cmsPageResponsitory.findByPageNameAndSiteIdAndPageWebPath(cmsPage.getPageName(), cmsPage.getSiteId(), cmsPage.getPageWebPath());
        if (result!=null){
            //存在抛出异常
            ExceptionCast.cast(CmsCode.CMS_ADDPAGE_EXISTSNAME);
        }
        cmsPageResponsitory.save(cmsPage);
        return new CmsPageResult(CommonCode.SUCCESS,cmsPage);
    }

    public CmsPageResult updatePage(String id, CmsPage cmsPage) {
        CmsPage cmsResult = getById(id);
        if (cmsResult!=null){
            //准备更新数据
            //设置要修改的数据
            //更新模板id
            cmsResult.setTemplateId(cmsPage.getTemplateId());
            //更新所属站点
            cmsResult.setSiteId(cmsPage.getSiteId());
            //更新页面别名
            cmsResult.setPageAliase(cmsPage.getPageAliase());
            //更新页面名称
            cmsResult.setPageName(cmsPage.getPageName());
            //更新访问路径
            cmsResult.setPageWebPath(cmsPage.getPageWebPath());
            //更新物理路径
            cmsResult.setPagePhysicalPath(cmsPage.getPagePhysicalPath());
            //提交修改
            cmsPageResponsitory.save(cmsResult);
            return new CmsPageResult(CommonCode.SUCCESS,cmsResult);
        }

        return new CmsPageResult(CommonCode.FAIL,cmsResult);
    }

    public ResponseResult deletePage( String id) {
        //先查询一下
        Optional<CmsPage> optional = cmsPageResponsitory.findById(id);
        if(optional.isPresent()){
            cmsPageResponsitory.deleteById(id);
            return new ResponseResult(CommonCode.SUCCESS);
        }
        return new ResponseResult(CommonCode.FAIL);
    }

    public CmsPage getById(String id){
        Optional<CmsPage> optional = cmsPageResponsitory.findById(id);
        if(optional.isPresent()){
            return optional.get();
        }
        return null;
    }
}
