package com.hlsofttech.controller.product;

import org.springframework.web.bind.annotation.RestController;
import com.hlsofttech.base.BaseController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.hlsofttech.service.product.DrugsInfoService;
import com.hlsofttech.entity.product.DrugsInfo;
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
 * @date 2019-08-14
 * @author suncy
 * @todo 药品库-药品信息表路由
 */
@RestController
@Api(tags="药品库-药品信息表",value = "药品库-药品信息表",description="药品库-药品信息表 @author suncy")
public class DrugsInfoController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(DrugsInfoController.class);

    @Reference(version = Constant.VERSION,group="com.hlsofttech.product",timeout=Constant.TIMEOUT)
    public DrugsInfoService iDrugsInfoService;
    
  
    /**
     * @date   @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表新增或者修改
     */
    @SuppressWarnings("rawtypes")
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "药品库-药品信息表新增或者修改", notes = "药品库-药品信息表新增或者修改", httpMethod = "POST")
	@PostMapping("/api/drugsInfo/creation")
	public Result creation(DrugsInfo entity) {
		if((entity=iDrugsInfoService.saveOrUpdate(entity))!=null){
    		return Result.newSuccessResult(entity);
    	}
    	return Result.newFailureResult();
	}
	
    /**
     * @date   @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表逻辑删除
     */
	@SuppressWarnings("rawtypes")
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "删除药品库-药品信息表", notes = "删除药品库-药品信息表", httpMethod = "DELETE")
	@DeleteMapping("/api/drugsInfo/delete/{id}")
	public Result deleteLogic(@PathVariable("id") String id) {
		return Result.newSuccessResult(iDrugsInfoService.deleteLogic(id));
	}
	/**
     * @date   @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表单条数据查询
     */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "药品库-药品信息表单条数据查询", notes = "药品库-药品信息表单条数据查询", httpMethod = "GET")
	@GetMapping(value = "/api/drugsInfo/{id}")
	public Result<DrugsInfo> getById(@PathVariable("id") String id) {
		if(iDrugsInfoService.deleteLogic(id)){
			return Result.newSuccessResult();
		}
		return Result.newFailureResult();
	}
	/**
     * @date   @date   2019-08-14
     * @author suncy
     * @todo   药品库-药品信息表数据分页查询
     */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "药品库-药品信息表数据分页查询", notes = "药品库-药品信息表数据分页查询", httpMethod = "GET")
	@GetMapping(value = "/api/drugsInfo/list")
	public Result<PageInfo<DrugsInfo>> pageList(DrugsInfo entity, @RequestParam(value="pageNum",required = false,defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",required = false,defaultValue="10") Integer pageSize) {
		return Result.newSuccessResult(iDrugsInfoService.pageList(entity,pageNum,pageSize));
	}
    
  
}

