package com.xuecheng.framework.domain.cms.ext;

import com.xuecheng.framework.domain.cms.CmsPage;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * @Author: mrt.
 * @Description:
 * @Date:Created in 2018/1/24 10:04.
 * @Modified By:
 */

@Data
@ToString
@Builder
public class CmsPageExt extends CmsPage {
    private String htmlValue;

}
