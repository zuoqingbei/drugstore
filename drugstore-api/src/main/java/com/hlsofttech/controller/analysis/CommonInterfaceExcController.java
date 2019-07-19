package com.hlsofttech.controller.analysis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.annotation.Log;
import com.hlsofttech.base.CommonBaseController;
import com.hlsofttech.common.Constant;
import com.hlsofttech.common.PublicResult;
import com.hlsofttech.entity.analysis.CommonInterfaceExc;
import com.hlsofttech.req.analysis.CommonInterReq;
import com.hlsofttech.rsp.Result;
import com.hlsofttech.service.analysis.CommonInterfaceExcService;
/**
 *
 * @date 2019-03-13
 * @author zuoqb123
 * @todo 统一接口路由
 */
@RestController
@Api(tags="统一接口",value = "统一接口",description="统一接口 @author zuoqb123")
public class CommonInterfaceExcController extends CommonBaseController {
    private final Logger logger = LoggerFactory.getLogger(CommonInterfaceExcController.class);

    @Reference(version = Constant.VERSION,timeout=TIMEOUT,group="com.hlsofttech.middleground")
    public CommonInterfaceExcService iCommonInterfaceExcService;
    

    
    /**
	 * 
	 * @time   2018年9月13日 上午10:39:24
	 * @author zuoqb
	 * @todo   根据数据源与SQL查询数据 或者查询当前库中SQL结果
	 * isVertical-数据格式，true-垂直 false-横向数据
	 */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	 @ApiImplicitParams({
	    	@ApiImplicitParam(name="sql",value="执行的SQL语句",dataType="String",paramType="query",required = true),
	    	@ApiImplicitParam(name="dataSourceId",value="SQL要查询的数据库编码，默认为当前库",dataType="String",paramType="query",required = false),
	    	@ApiImplicitParam(name="params",value="动态参数 格式 time::20180731;;cbkCode::BD1011001",dataType="String",paramType="query",required = false),
	    	@ApiImplicitParam(name="isVertical",value="数据格式，true-垂直 false-横向数据",dataType="boolean",paramType="query",required = false,defaultValue="true")})
	@ApiOperation(value = "根据数据源与SQL查询数据", notes = "根据数据源与SQL查询数据", httpMethod = "GET")
	@GetMapping(value = "/api/common/interface/getBySql")
	public PublicResult<?> getBySql(@RequestParam(value="sql",required = true) String sql,@RequestParam(value="dataSourceId",required = false) String dataSourceId,
			@RequestParam(value="params",required = false) String params,@RequestParam(value="isVertical",required = false,defaultValue="true") boolean isVertical,
			HttpServletRequest request) {
		CommonInterReq inter=new CommonInterReq();
		inter.setSql(sql);
		inter.setParams(params);
		inter.setDataSourceId(dataSourceId);
		inter.setVertical(isVertical);
		return iCommonInterfaceExcService.getBySql(inter);
    	
    }
	
	/**
	 * @time   2018年9月27日 上午9:13:27
	 * @author zuoqb
	 * @todo   统一接口查询数据
	 * 平台-数据源-接口
	 * @param dataType-接口类型  params-动态参数 格式 time::20180731;;cbkCode::BD1011001,,startIndex::1;;pageSize::10
	 * SQL中不要带“;” 否则会报错
	 */
   	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
   	@ApiOperation(value = "统一接口查询数据", notes = "统一接口查询数据", httpMethod = "GET")
    @ApiImplicitParams({
    	@ApiImplicitParam(name="dataType",value="查询指标标识",dataType="String",paramType="query",required = true),
    	@ApiImplicitParam(name="params",value="动态参数 格式 time::20180731;;cbkCode::BD1011001",dataType="String",paramType="query",required = false)})
    @Log(description = "API接口:/api/common/innter")
	@GetMapping(value = "/api/common/innter")
    public PublicResult<?>  getByDataType(@RequestParam(value="dataType",required = true) String dataType,@RequestParam(value="params",required = false) String params
    		,HttpServletRequest request,HttpServletResponse response) {
   		CommonInterReq inter=new CommonInterReq();
		inter.setParams(params);
		inter.setDataType(dataType);
		return iCommonInterfaceExcService.getByDataType(inter);
    }
   	
   	
   	
    /**
   	 * @time   2018年9月27日 上午9:13:27
   	 * @author zuoqb
   	 * @todo   统一接口插入数据
   	 * 平台-数据源-接口
   	 * @param dataType-接口类型  params-动态参数 格式 time::20180731;;cbkCode::BD1011001,,startIndex::1;;pageSize::10
   	 * SQL中不要带“;” 否则会报错
   	 */
   /*@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
   @ApiOperation(value = "统一接口查询数据", notes = "统一接口查询数据", httpMethod = "POST")
   @ApiImplicitParams({
   @ApiImplicitParam(name="dataType",value="查询指标标识",dataType="String",paramType="query",required = true),
   @ApiImplicitParam(name="params",value="动态参数 格式 time::20180731;;cbkCode::BD1011001",dataType="String",paramType="query",required = false)})
   @Log(description = "API接口:/api/{version}/common/interface/insertDate")
   @GetMapping(value = "/insertDate")
   public PublicResult<?>  insertDate(@RequestParam(value="dataType",required = true) String dataType,@RequestParam(value="params",required = false) String params
   		,HttpServletRequest request,HttpServletResponse response) {
	    CommonInterReq inter=new CommonInterReq();
	    inter.setParams(params);
		inter.setDataType(dataType);
		return iCommonInterfaceExcService.insertDate(inter);
   }*/
   	
   	
   	
   	
  
    /**
     * @date   @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口新增或者修改
     */
    /*@SuppressWarnings("rawtypes")
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "统一接口新增或者修改", notes = "统一接口新增或者修改", httpMethod = "POST")
	@PostMapping("/api/commonInterfaceExc/saveOrUpdate")
	public Result saveOrUpdate(CommonInterfaceExc entity) {
		return Result.newSuccessResult(iCommonInterfaceExcService.saveOrUpdate(entity));
	}*/
	
    /**
     * @date   @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口逻辑删除
     */
	/*@SuppressWarnings("rawtypes")
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "删除统一接口", notes = "删除统一接口", httpMethod = "POST")
	@PostMapping("/api/commonInterfaceExc/delete/{id}")
	public Result deleteLogic(@PathVariable("id") String id) {
		return Result.newSuccessResult(iCommonInterfaceExcService.deleteLogic(id));
	}*/
	/**
     * @date   @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口单条数据查询
     */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "统一接口单条数据查询", notes = "统一接口单条数据查询", httpMethod = "GET")
	@GetMapping(value = "/api/commonInterfaceExc/get/{id}")
	public Result<CommonInterfaceExc> getById(@PathVariable("id") String id) {
		return Result.newSuccessResult(iCommonInterfaceExcService.getById(id));
	}
	/**
     * @date   @date   2019-03-13
     * @author zuoqb123
     * @todo   统一接口数据分页查询
     */
	@AuthPower(avoidVersion = false, avoidPower = true, avoidSign = true, avoidLogin = true, avoidPlatform = true)
	@ApiOperation(value = "统一接口数据分页查询", notes = "统一接口数据分页查询", httpMethod = "GET")
	@GetMapping(value = "/api/commonInterfaceExc/list")
	public Result<PageInfo<CommonInterfaceExc>> pageList(CommonInterfaceExc entity, @RequestParam(value="pageNum",required = false,defaultValue="1") Integer pageNum,
			@RequestParam(value="pageSize",required = false,defaultValue="10") Integer pageSize) {
		return Result.newSuccessResult(iCommonInterfaceExcService.pageList(entity,pageNum,pageSize));
	}
    
  
}

