package com.hlsofttech.analysis.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.analysis.dao.CommonInterfaceExcDao;
import com.hlsofttech.analysis.dao.CommonInterfaceExcLogDao;
import com.hlsofttech.analysis.dao.DbDatasourceConfigDao;
import com.hlsofttech.analysis.dao.SysPlatInfoDao;
import com.hlsofttech.annotation.AuthPower;
import com.hlsofttech.common.Constant;
import com.hlsofttech.common.PublicResult;
import com.hlsofttech.entity.analysis.CommonInterfaceExc;
import com.hlsofttech.entity.analysis.CommonInterfaceExcLog;
import com.hlsofttech.entity.analysis.DbDatasourceConfig;
import com.hlsofttech.enumeration.PublicResultConstant;
import com.hlsofttech.req.analysis.CommonInterReq;
import com.hlsofttech.service.analysis.CommonInterfaceExcService;
import com.hlsofttech.utils.JdbcUtil;
import com.hlsofttech.utils.RexUtils;
import com.hlsofttech.utils.UUIDUtils;
/**
 * @date 2019-03-13
 * @author zuoqb123
 * 统一接口服务实现类
 */
@Service(version = Constant.VERSION,group="com.hlsofttech.middleground")
@org.springframework.stereotype.Service
public class CommonInterfaceExcServiceImpl implements CommonInterfaceExcService,Constant {

    @Autowired
    private CommonInterfaceExcDao commonInterfaceExcDao;
    
    @Autowired
    private SysPlatInfoDao sysPlatInfoDao;
    
    @Autowired
    private DbDatasourceConfigDao dbDatasourceConfigDao;
    
    @Autowired
    private CommonInterfaceExcLogDao commonInterfaceExcLogDao;
    @Autowired
	public JdbcTemplate jdbcTemplate;
    
    
    /**
	 * 
	 * @time   2019年3月13日17:29:58
	 * @author zuoqb
	 * @todo   根据数据源与SQL查询数据 或者查询当前库中SQL结果
	 * isVertical-数据格式，true-垂直 false-横向数据
	 */
    @Override
	public PublicResult<?> getBySql(CommonInterReq inter) {
    	String sql=inter.getSql();
    	String params=inter.getParams();
    	boolean isVertical=inter.isVertical();
    	String dataSourceId=inter.getDataSourceId();
    	/**
    	 * 开始处理SQL
    	 */
    	//解析SQL中的${}与#{}
    	//匹配所有${}与匹配所有#{}正则表达式
    	String rexg="\\#\\{([^\\}]+)\\}|\\$\\{([^\\}]+)\\}";
    	//按照顺序查找出所有# $ 比如  [#{time}, #{cbkCode}, ${startIndex}, ${pageSize}]
    	List<String> matcher=RexUtils.getString(sql, rexg);
    	PublicResult<Map<String,String>> dealParamsResult=JdbcUtil.dealParams(params);
    	PublicResult<Map<String, List<Map<String, Object>>>> formatSqlResult= JdbcUtil.formatSql(sql, matcher, dealParamsResult);
    	if(!PublicResultConstant.SUCCESS.msg.equals(formatSqlResult.getMsg())){
    		return new PublicResult<>(PublicResultConstant.INVALID_PARAM_EMPTY,formatSqlResult.getErrorMsg(), null);
    	}
    	//将SQL中所有${}与所有#{}替换为？
    	sql=RexUtils.getReplace(sql, rexg,"?");
    	/**
    	 * 处理SQL结束
    	 */
    	if(!isVertical){
    		return execeSqlVertical(sql, dataSourceId, matcher, dealParamsResult,"getBySql",VERTICAL_DATA_FORMAT);
    	}
    	return execeSqlVertical(sql, dataSourceId, matcher, dealParamsResult,"getBySql",null);
	}

    /**
	 * @time   2018年9月27日 上午9:13:27
	 * @author zuoqb
	 * @todo   统一接口查询数据
	 * 平台-数据源-接口
	 * @param dataType-接口类型  params-动态参数 格式 time::20180731;;cbkCode::BD1011001,,startIndex::1;;pageSize::10
	 * SQL中不要带“;” 否则会报错
	 */
	@Override
	public PublicResult<?> getByDataType(CommonInterReq inter) {
		String dataSpace="";
		CommonInterfaceExcLog log=new CommonInterfaceExcLog();
		String dataType=inter.getDataType();
    	String params=inter.getParams();
    	log.setBeginTime(new Date());
    	log.setDataType(dataType);
    	log.setParams(params);
		if(StringUtils.isBlank(dataType)){
			return new PublicResult<>(PublicResultConstant.INVALID_PARAM_EMPTY,"dataType参数不能为空!", null);
		}
    	//根据dataType查询接口配置信息
    	EntityWrapper<CommonInterfaceExc> inteWrapper = new EntityWrapper<CommonInterfaceExc>();
    	inteWrapper.where("del_flag={0}", UN_DEL_FLAG);
    	inteWrapper.eq("data_type", dataType);
    	Map<String,Object> result=new HashMap<String,Object>();
    	List<CommonInterfaceExc> excList=commonInterfaceExcDao.selectList(inteWrapper);
    	for(CommonInterfaceExc entity:excList){
    		/**
        	 * 开始处理SQL
        	 */
        	String sql=entity.getDataSql();
        	//解析SQL中的${}与#{}
        	//匹配所有${}与匹配所有#{}正则表达式
        	String rexg="\\#\\{([^\\}]+)\\}|\\$\\{([^\\}]+)\\}";
        	//按照顺序查找出所有# $ 比如  [#{time}, #{cbkCode}, ${startIndex}, ${pageSize}]
        	List<String> matcher=RexUtils.getString(sql, rexg);
        	PublicResult<Map<String,String>> dealParamsResult=JdbcUtil.dealParams(params);
        	PublicResult<Map<String, List<Map<String, Object>>>> formatSqlResult= JdbcUtil.formatSql(sql, matcher, dealParamsResult);
        	if(!PublicResultConstant.SUCCESS.msg.equals(formatSqlResult.getMsg())){
        		return formatSqlResult;
        	}
        	//将SQL中所有${}与所有#{}替换为？
        	sql=RexUtils.getReplace(sql, rexg,"?");
        	/**
        	 * 处理SQL结束
        	 */
        	String dataSourceId=entity.getDbDatasourceId();
        	PublicResult<?> execeResult=execeSqlVertical(sql, dataSourceId, matcher, dealParamsResult,"getByDataType",entity.getTransformData());
        	if(!PublicResultConstant.SUCCESS.msg.equals(execeResult.getMsg())){
        		return new PublicResult<>(PublicResultConstant.PARAM_ERROR,execeResult.getErrorMsg(), null);
        	}
        	if(execeResult.getData() instanceof List){
        		List<Map<String, Object>> verticalData=(List<Map<String, Object>>) execeResult.getData();
        		result.put(entity.getDataSpace(),verticalData);
        	}else{
        		Map<String,List<Object>> horizontalData= (Map<String, List<Object>>) execeResult.getData();
        		result.put(entity.getDataSpace(),horizontalData);
        	}
        	dataSpace+=entity.getDataSpace()+",";
    	}
    	log.setEndTime(new Date());
    	log.setExcStatus("S");
    	log.setResult(JSON.toJSONString(result));
    	log.setId(UUIDUtils.getUuid());
    	log.setDataSpace(dataSpace);
    	log.setDateDt("yeesight");
    	try {
    		commonInterfaceExcLogDao.insert(log);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    	return new PublicResult<>(PublicResultConstant.SUCCESS, result);
	}
	
	 /**
		 * @time   2018年9月27日 上午9:13:27
		 * @author zuoqb
		 * @todo   统一接口插入数据
		 * 平台-数据源-接口
		 * @param dataType-接口类型  params-动态参数 格式 time::20180731;;cbkCode::BD1011001,,startIndex::1;;pageSize::10
		 * SQL中不要带“;” 否则会报错
		 */
		@Override
		public PublicResult<?> insertDate(CommonInterReq inter) {
			String dataType=inter.getDataType();
	    	String params=inter.getParams();
			if(StringUtils.isBlank(dataType)){
				return new PublicResult<>(PublicResultConstant.INVALID_PARAM_EMPTY,"dataType参数不能为空!", null);
			}
	    	//根据dataType查询接口配置信息
	    	EntityWrapper<CommonInterfaceExc> inteWrapper = new EntityWrapper<CommonInterfaceExc>();
	    	inteWrapper.where("del_flag={0}", UN_DEL_FLAG);
	    	inteWrapper.eq("data_type", dataType);
	    	Map<String,Object> result=new HashMap<String,Object>();
	    	List<CommonInterfaceExc> excList=commonInterfaceExcDao.selectList(inteWrapper);
	    	for(CommonInterfaceExc entity:excList){
	    		/**
	        	 * 开始处理SQL
	        	 */
	        	String sql=entity.getDataSql();
	        	//解析SQL中的${}与#{}
	        	//匹配所有${}与匹配所有#{}正则表达式
	        	String rexg="\\#\\{([^\\}]+)\\}|\\$\\{([^\\}]+)\\}";
	        	//按照顺序查找出所有# $ 比如  [#{time}, #{cbkCode}, ${startIndex}, ${pageSize}]
	        	List<String> matcher=RexUtils.getString(sql, rexg);
	        	PublicResult<Map<String,String>> dealParamsResult=JdbcUtil.dealParams(params);
	        	PublicResult<Map<String, List<Map<String, Object>>>> formatSqlResult= JdbcUtil.formatInsertSql(sql, matcher, dealParamsResult);
	        	if(!PublicResultConstant.SUCCESS.msg.equals(formatSqlResult.getMsg())){
	        		return formatSqlResult;
	        	}
	        	//将SQL中所有${}与所有#{}替换为？
	        	sql=RexUtils.getReplace(sql, rexg,"?");
	        	/**
	        	 * 处理SQL结束
	        	 */
	        	String dataSourceId=entity.getDbDatasourceId();
	        	if(StringUtils.isNotBlank(dataSourceId)){
	        		DbDatasourceConfig config =dbDatasourceConfigDao.selectById(dataSourceId);
	        		if(config==null){
	        			return new PublicResult<>(PublicResultConstant.PARAM_ERROR,"数据源无效，请配置数据源！", null);
	        		}
	        		//查询平台信息 校验平台合法性
	            	/*SysPlatInfo sysPlatInfo=sysPlatInfoDao.selectById(config.getSysPlatId());
	            	if(sysPlatInfo==null){
	        			return new PublicResult<>(PublicResultConstant.PARAM_ERROR,"平台无效，请联系管理员！", null);
	        		}*/
	        		Connection conn = JdbcUtil.getConn(config);
	        		if(conn==null){
	        			return new PublicResult<>(PublicResultConstant.FAILED,"数据库连接失败！",null);
	        		}
	        		PreparedStatement pstmt = null;
	        		ResultSet rs = null;
	        		try {
	        			//将where后面的‘与’替换
	        			Pattern p=Pattern.compile("where",Pattern.CASE_INSENSITIVE);
	        			Matcher m=p.matcher(sql);
	        			while(m.find()){
	        	            String whereStr=m.group();
	        	            String[] splits=sql.split(whereStr);
	        	            sql=splits[0];
	        	            for(int x=0;x<splits.length;x++){
	        	            	if(x>0){
	        	            		sql+=whereStr+splits[x].replaceAll("‘", "'").replaceAll("’", "'");
	        	            	}
	        	            }
	        	        }
	        			pstmt = conn.prepareStatement(sql);
	        			//设置参数 外层循环
	        			for(int index=0;index<matcher.size();index++){
	        				String matcherKey=matcher.get(index);
	        				String variableValue = JdbcUtil.getParamValueByKey(dealParamsResult, matcherKey);
	        				if(matcherKey.trim().startsWith("#")){
	        					//#替换sql语句中的参数，只能是字符串
	        					pstmt.setString(index+1, variableValue);
	        				}
	        				if(matcherKey.trim().startsWith("$")){
	        					//$替换sql语句中的内容，只能是数字
	        					pstmt.setInt(index+1, Integer.valueOf(variableValue));
	        				}
	        			}
	        			pstmt.execute();
	        			return new PublicResult<>(PublicResultConstant.SUCCESS,null);
	        		} catch (Exception e) {
	        			e.printStackTrace();
	        			return new PublicResult<>(PublicResultConstant.FAILED,e.getMessage(),null);
	        		}finally {
	        			try {
	        				JdbcUtil.close(conn, pstmt, rs);
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				return new PublicResult<>(PublicResultConstant.FAILED,e.getMessage(),null);
	        			}
	        		}
	        	}
	    	}
	    	return new PublicResult<>(PublicResultConstant.SUCCESS, result);
		}
	
	/**
     * 
     * @time   2018年9月27日 下午2:38:39
     * @author zuoqb
     * @todo   执行SQL返回执行垂直格式数据结果[{},{},{}]  
     */
    public  PublicResult<?> execeSqlVertical(String sql, String dataSourceId, List<String> matcher,
			PublicResult<Map<String, String>> dealParamsResult,String methodName,String tranType) {
	    	AuthPower authPower =null;
		try {
			/*Method[] methods=CommonInterfaceController.class.getMethods();
			for(Method method:methods){
				if(method.getName().equals(methodName)){
					authPower = method.getAnnotation(AuthPower.class);
					break;
				}
			}*/
		} catch (Exception e) {
			e.printStackTrace();
			return new PublicResult<>(PublicResultConstant.ERROR,e.getMessage(), null);
		}
		if(StringUtils.isNotBlank(dataSourceId)){
    		DbDatasourceConfig config =dbDatasourceConfigDao.selectById(dataSourceId);
    		if(config==null){
    			return new PublicResult<>(PublicResultConstant.PARAM_ERROR,"数据源无效，请配置数据源！", null);
    		}
    		//查询平台信息 校验平台合法性
        	/*SysPlatInfo sysPlatInfo=sysPlatInfoDao.selectById(config.getSysPlatId());
        	if(sysPlatInfo==null){
    			return new PublicResult<>(PublicResultConstant.PARAM_ERROR,"平台无效，请联系管理员！", null);
    		}*/
        	/**
        	 * 验证平台秘钥 需要以后扩展
        	 */
        /*	if(authPower!=null&&!authPower.avoidSign()){
				//不免签名认证
        		String signAuth = request.getHeader(AppInterceptors.DEFAULT_AUTH_NAME);//签名
        		if(StringUtils.isNotBlank(signAuth)){
        			if(!signAuth.equals(sysPlatInfo.getSecretKey())){
        				return new PublicResult<>(PublicResultConstant.PARAM_ERROR,"平台签名"+signAuth+"无效，请联系管理员！", null);
        			}
        		}
			}*/
    		Connection conn = JdbcUtil.getConn(config);
    		if(conn==null){
    			return new PublicResult<>(PublicResultConstant.FAILED,"数据库连接失败！",null);
    		}
    		PreparedStatement pstmt = null;
    		ResultSet rs = null;
    		try {
    			//将where后面的‘与’替换
    			Pattern p=Pattern.compile("where",Pattern.CASE_INSENSITIVE);
    			Matcher m=p.matcher(sql);
    			while(m.find()){
    	            String whereStr=m.group();
    	            String[] splits=sql.split(whereStr);
    	            sql=splits[0];
    	            for(int x=0;x<splits.length;x++){
    	            	if(x>0){
    	            		sql+=whereStr+splits[x].replaceAll("‘", "'").replaceAll("’", "'");
    	            	}
    	            }
    	        }
    			pstmt = conn.prepareStatement(sql);
    			//设置参数 外层循环
    			for(int index=0;index<matcher.size();index++){
    				String matcherKey=matcher.get(index);
    				String variableValue = JdbcUtil.getParamValueByKey(dealParamsResult, matcherKey);
    				if(matcherKey.trim().startsWith("#")){
    					//#替换sql语句中的参数，只能是字符串
    					pstmt.setString(index+1, variableValue);
    				}
    				if(matcherKey.trim().startsWith("$")){
    					//$替换sql语句中的内容，只能是数字
    					pstmt.setInt(index+1, Integer.valueOf(variableValue));
    				}
    			}
    			rs = pstmt.executeQuery();
    			List<Map<String, Object>> verticalData = JdbcUtil.parseResultSet2List(rs);
    			//转成横向数据格式
    	    	Map<String,List<Object>> horizontalData=new HashMap<String, List<Object>>();
    	    	if(VERTICAL_DATA_FORMAT.equals(tranType)){
    	    		return JdbcUtil.verticalToHorizontal(verticalData, horizontalData);
    	    	}else{
    	    		return new PublicResult<>(PublicResultConstant.SUCCESS, verticalData);
    	    	}
    		} catch (Exception e) {
    			e.printStackTrace();
    			return new PublicResult<>(PublicResultConstant.FAILED,e.getMessage(),null);
    		}finally {
    			try {
    				JdbcUtil.close(conn, pstmt, rs);
    			} catch (Exception e) {
    				e.printStackTrace();
    				return new PublicResult<>(PublicResultConstant.FAILED,e.getMessage(),null);
    			}
    		}
    	}else{
    		try {
    			//数据源为空 默认当前数据库
    			/**
            	 * 验证平台秘钥 需要以后扩展
            	 */
    			/*if(authPower!=null&&!authPower.avoidSign()){
    				//不免签名认证
    				String signAuth = request.getHeader(AppInterceptors.DEFAULT_AUTH_NAME);//签名
    				if(StringUtils.isNotBlank(signAuth)){
    					if(!signAuth.equals(Default_X_SIGN)){
    						return new PublicResult<>(PublicResultConstant.PARAM_ERROR,"平台签名"+signAuth+"无效，请联系管理员！", null);
    					}
    				}
    			}*/
        		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
        		if(dealParamsResult.getData()!=null&&dealParamsResult.getData().size()>0){
        			Object[] values=new Object[matcher.size()];//这边必须为Object[]
        			//设置参数 外层循环
        			for(int index=0;index<matcher.size();index++){
        				String matcherKey=matcher.get(index);
        				if(matcherKey.trim().startsWith("#")){
        					//#替换sql语句中的参数，只能是字符串
        					values[index]=JdbcUtil.getParamValueByKey(dealParamsResult, matcherKey);
        				}
        				if(matcherKey.trim().startsWith("$")){
        					//$替换sql语句中的内容，只能是数字
        					values[index]=Integer.valueOf(JdbcUtil.getParamValueByKey(dealParamsResult, matcherKey));
        				}
        			}
        			list=jdbcTemplate.queryForList(sql,values);
        		}else{
        			list=jdbcTemplate.queryForList(sql);
        		}
        		//转成横向数据格式
    	    	Map<String,List<Object>> horizontalData=new HashMap<String, List<Object>>();
    	    	if(VERTICAL_DATA_FORMAT.equals(tranType)){
    	    		return JdbcUtil.verticalToHorizontal(list, horizontalData);
    	    	}else{
    	    		return new PublicResult<>(PublicResultConstant.SUCCESS, list);
    	    	}
			} catch (Exception e) {
				e.printStackTrace();
				return new PublicResult<>(PublicResultConstant.FAILED,e.getMessage(),null);
			}
    	}
	}

	
    
     /**
     * @date 2019-03-13
     * @author zuoqb123
     * @todo   统一接口新增或者修改
     */
    @Override
	public boolean saveOrUpdate(CommonInterfaceExc entity) {
		if(StringUtils.isBlank(entity.getId())){
			//新增
			entity.setId(UUIDUtils.getUuid());
			entity.setCreateDate(new Date());
			return commonInterfaceExcDao.insert(entity)>0;
		}else{
			entity.setUpdateDate(new Date());
			return commonInterfaceExcDao.updateById(entity)>0;
		}
	}

	/**
     * @date 2019-03-13
     * @author zuoqb123
     * @todo   统一接口逻辑删除
     */
	@Override
	public boolean deleteLogic(String id) {
		CommonInterfaceExc entity=new CommonInterfaceExc();
		entity.setId(id);
		entity.setDelFlag(DEL_FLAG);
		entity.setUpdateDate(new Date());
		return commonInterfaceExcDao.updateById(entity)>0;
	}

	/**
     * @date 2019-03-13
     * @author zuoqb123
     * @todo   统一接口单条数据查询
     */
	@Override
	public CommonInterfaceExc getById(String id) {
		return commonInterfaceExcDao.selectById(id);
	}

	/**
     * @date   @date 2019-03-13
     * @author zuoqb123
     * @todo   统一接口分页查询
     */
	@Override
	public PageInfo<CommonInterfaceExc> pageList(CommonInterfaceExc entity, Integer pageNum,
			Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		EntityWrapper<CommonInterfaceExc> wrapper = searchWrapper(entity);
		List<CommonInterfaceExc> list = commonInterfaceExcDao.selectList(wrapper);
		PageInfo<CommonInterfaceExc> page = new PageInfo<CommonInterfaceExc>(list);
		return page;
	}
    
	
	 /**
     * @date 2019-03-13
     * @author zuoqb123
     * @todo   统一接口构建查询条件-以后扩展
     */
    private EntityWrapper<CommonInterfaceExc> searchWrapper(CommonInterfaceExc entity) {
		EntityWrapper<CommonInterfaceExc> wrapper = new EntityWrapper<CommonInterfaceExc>();
		wrapper.where("del_flag={0}", UN_DEL_FLAG);
		//根据模糊查询
		if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
			wrapper.like("id", String.valueOf(entity.getId()));
		}
		//根据归属数据源 默认当前数据库模糊查询
		if(entity.getDbDatasourceId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDbDatasourceId()))){
			wrapper.like("db_datasource_id", String.valueOf(entity.getDbDatasourceId()));
		}
		//根据查询指标标识模糊查询
		if(entity.getDataType()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataType()))){
			wrapper.like("data_type", String.valueOf(entity.getDataType()));
		}
		//根据命名空间模糊查询
		if(entity.getDataSpace()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataSpace()))){
			wrapper.like("data_space", String.valueOf(entity.getDataSpace()));
		}
		//根据需要执行的sql语句，需要传参的位置使用#{参数名}，动态日期类型date_dt_kpi固定参数名称。模糊查询
		if(entity.getDataSql()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDataSql()))){
			wrapper.like("data_sql", String.valueOf(entity.getDataSql()));
		}
		//根据横竖数据格式转换，默认纵向排列（V：垂直排列，H水平排列）模糊查询
		if(entity.getTransformData()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTransformData()))){
			wrapper.like("transform_data", String.valueOf(entity.getTransformData()));
		}
		if(entity.getStartDate()!=null){
			wrapper.ge("create_date", entity.getStartDate());
		}
		if(entity.getEndDate()!=null){
			wrapper.le("create_date", entity.getEndDate());
		}
		if(StringUtils.isNotBlank(entity.getOrderBy())){
			wrapper.orderBy(entity.getOrderBy(), entity.isAsc());
		}else{
			wrapper.orderBy("create_date", false);
		}
		//System.out.println(wrapper.originalSql());
		return wrapper;
	}

	
   
}
