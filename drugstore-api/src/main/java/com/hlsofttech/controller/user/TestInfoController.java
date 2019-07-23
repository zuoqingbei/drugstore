package com.hlsofttech.controller.user;

import org.springframework.web.bind.annotation.RestController;
import com.hlsofttech.base.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hlsofttech.service.user.TestInfoService;
import com.hlsofttech.entity.user.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.pagehelper.PageInfo;
import com.alibaba.dubbo.config.annotation.Reference;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;
/**
 *
 * @date 2019-07-23
 * @author suncy123
 * @todo 新闻收藏路由
 */
@RestController
@Api(tags="新闻收藏",value = "新闻收藏",description="新闻收藏 @author suncy123")
public class TestInfoController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(TestInfoController.class);

    @Reference(version = Constant.VERSION,group="com.hlsofttech.user",timeout=Constant.TIMEOUT)
    public TestInfoService iTestInfoService;
    
  
    /**
     * @date   @date   2019-07-23
     * @author suncy123
     * @todo   新闻收藏新增或者修改
     */
    @SuppressWarnings("rawtypes")
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "新闻收藏新增或者修改", notes = "新闻收藏新增或者修改", httpMethod = "POST")
	@PostMapping("/api/testInfo/creation")
	public Result creation(TestInfo entity) {
		if((entity=iTestInfoService.saveOrUpdate(entity))!=null){
    		return Result.newSuccessResult(entity);
    	}
    	return Result.newFailureResult();
	}
	
    /**
     * @date   @date   2019-07-23
     * @author suncy123
     * @todo   新闻收藏逻辑删除
     */
	@SuppressWarnings("rawtypes")
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "删除新闻收藏", notes = "删除新闻收藏", httpMethod = "DELETE")
	@DeleteMapping("/api/testInfo/delete/{id}")
	public Result deleteLogic(@PathVariable("id") String id) {
		return Result.newSuccessResult(iTestInfoService.deleteLogic(id));
	}
	/**
     * @date   @date   2019-07-23
     * @author suncy123
     * @todo   新闻收藏单条数据查询
     */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "新闻收藏单条数据查询", notes = "新闻收藏单条数据查询", httpMethod = "GET")
	@GetMapping(value = "/api/testInfo/{id}")
	public Result<TestInfo> getById(@PathVariable("id") String id) {
		if(iTestInfoService.deleteLogic(id)){
			return Result.newSuccessResult();
		}
		return Result.newFailureResult();
	}
	/**
     * @date   @date   2019-07-23
     * @author suncy123
     * @todo   新闻收藏数据分页查询
     */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "新闻收藏数据分页查询", notes = "新闻收藏数据分页查询", httpMethod = "GET")
	@GetMapping(value = "/api/testInfo/list")
	public Result<PageInfo<TestInfo>> pageList(TestInfo entity, @RequestParam(value="pageNum",required = false,defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",required = false,defaultValue="10") Integer pageSize) {
		return Result.newSuccessResult(iTestInfoService.pageList(entity,pageNum,pageSize));
	}
    
  
}

