package com.hlsofttech.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hlsofttech.common.Constant;
import com.hlsofttech.entity.product.DrugsInfo;
import com.hlsofttech.product.dao.DrugsInfoDao;
import com.hlsofttech.service.product.DrugsInfoService;
import com.hlsofttech.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import sun.plugin.services.WIExplorerBrowserService;

import java.util.Date;
import java.util.List;

/**
 * @author suncy
 * 药品库-药品信息表服务实现类
 * @date 2019-08-14
 */
@Slf4j
@Service(version = Constant.VERSION, group = "com.hlsofttech.product", timeout = Constant.TIMEOUT)
public class DrugsInfoServiceImpl implements DrugsInfoService, Constant {

    @Autowired
    private DrugsInfoDao drugsInfoDao;

    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表新增或者修改
     */
    @Override
    public DrugsInfo saveOrUpdate(DrugsInfo entity) {
        if (StringUtils.isBlank(entity.getId())) {
            //新增
            entity.setId(UUIDUtils.getUuid());
            entity.setCreateDate(new Date());
            if (drugsInfoDao.insert(entity) > 0) {
                return entity;
            }
            return null;
        } else {
            entity.setUpdateDate(new Date());
            if (drugsInfoDao.updateById(entity) > 0) {
                return entity;
            }
            return null;
        }
    }

    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表逻辑删除
     */
    @Override
    public boolean deleteLogic(String id) {
        DrugsInfo entity = new DrugsInfo();
        entity.setId(id);
        entity.setDelFlag(DEL_FLAG);
        entity.setUpdateDate(new Date());
        return drugsInfoDao.updateById(entity) > 0;
    }

    /**
     * @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表单条数据查询
     */
    @Override
    public DrugsInfo getById(String id) {
        return drugsInfoDao.selectById(id);
    }

    @Override
    public List<DrugsInfo> findList(DrugsInfo entity) {
        EntityWrapper<DrugsInfo> wrapper = searchWrapper(entity);
        return drugsInfoDao.selectList(wrapper);
    }

    /**
     * @date @date 2019-08-14
     * @author suncy
     * @todo 药品库-药品信息表分页查询
     */
    @Override
    public PageInfo<DrugsInfo> pageList(DrugsInfo entity, Integer pageNum,
                                        Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EntityWrapper<DrugsInfo> wrapper = searchWrapper(entity);
        List<DrugsInfo> list = drugsInfoDao.selectList(wrapper);
        PageInfo<DrugsInfo> page = new PageInfo<DrugsInfo>(list);
        return page;
    }


    @Override
    public DrugsInfo getDrugsInfo(DrugsInfo entity) {
        EntityWrapper<DrugsInfo> wrapper = searchWrapper(entity);
        try {
            return drugsInfoDao.selectList(wrapper).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public DrugsInfo getByApprovalNumber(String approvalNumber) {
        DrugsInfo entity = new DrugsInfo();
        entity.setApprovalNumber(approvalNumber);
        return getDrugsInfo(entity);
    }

    /**
     * @date 2019-08-15
     * @author suncy123
     * @todo   药品库-药品信息表构建查询条件-以后扩展
     */
    private EntityWrapper<DrugsInfo> searchWrapper(DrugsInfo entity) {
        EntityWrapper<DrugsInfo> wrapper = new EntityWrapper<DrugsInfo>();
        wrapper.where("del_flag={0}", UN_DEL_FLAG);
        //根据主键模糊查询
        if(entity.getId()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getId()))){
            wrapper.like("id", String.valueOf(entity.getId()));
        }
        //根据唯一码模糊查询
        if(entity.getGuid()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getGuid()))){
            wrapper.like("guid", String.valueOf(entity.getGuid()));
        }
        //根据药品名称模糊查询
        if(entity.getDrugName()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDrugName()))){
            wrapper.like("drug_name", String.valueOf(entity.getDrugName()));
        }
        //根据一级分类模糊查询
        if(entity.getC1Id()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getC1Id()))){
            wrapper.like("c1_id", String.valueOf(entity.getC1Id()));
        }
        //根据二级分类模糊查询
        if(entity.getC2Id()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getC2Id()))){
            wrapper.like("c2_id", String.valueOf(entity.getC2Id()));
        }
        //根据三级分类模糊查询
        if(entity.getC3Id()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getC3Id()))){
            wrapper.like("c3_id", String.valueOf(entity.getC3Id()));
        }
        //根据是否外用药模糊查询
        if(entity.getIsExternalUsed()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getIsExternalUsed()))){
            wrapper.like("is_external_used", String.valueOf(entity.getIsExternalUsed()));
        }
        //根据0=OTC1=RX模糊查询
        if(entity.getOtcOrRx()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getOtcOrRx()))){
            wrapper.like("otc_or_rx", String.valueOf(entity.getOtcOrRx()));
        }
        //根据OTC级别模糊查询
        if(entity.getOtcLevel()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getOtcLevel()))){
            wrapper.like("otc_level", String.valueOf(entity.getOtcLevel()));
        }
        //根据价格模糊查询
        if(entity.getPrice()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPrice()))){
            wrapper.like("price", String.valueOf(entity.getPrice()));
        }
        //根据通用名模糊查询
        if(entity.getCommonName()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCommonName()))){
            wrapper.like("common_name", String.valueOf(entity.getCommonName()));
        }
        //根据汉语拼音模糊查询
        if(entity.getFullPinyin()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getFullPinyin()))){
            wrapper.like("full_pinyin", String.valueOf(entity.getFullPinyin()));
        }
        //根据拼音简码模糊查询
        if(entity.getPinyinShortCode()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPinyinShortCode()))){
            wrapper.like("pinyin_short_code", String.valueOf(entity.getPinyinShortCode()));
        }
        //根据英文名称模糊查询
        if(entity.getEnglishName()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getEnglishName()))){
            wrapper.like("english_name", String.valueOf(entity.getEnglishName()));
        }
        //根据商品名称模糊查询
        if(entity.getProductName()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getProductName()))){
            wrapper.like("product_name", String.valueOf(entity.getProductName()));
        }
        //根据批准文号模糊查询
        if(entity.getApprovalNumber()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getApprovalNumber()))){
            wrapper.like("approval_number", String.valueOf(entity.getApprovalNumber()));
        }
        //根据生产企业模糊查询
        if(entity.getManufacturer()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getManufacturer()))){
            wrapper.like("manufacturer", String.valueOf(entity.getManufacturer()));
        }
        //根据产品图片模糊查询
        if(entity.getProductImages()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getProductImages()))){
            wrapper.like("product_images", String.valueOf(entity.getProductImages()));
        }
        //根据规格模糊查询
        if(entity.getSpecification()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getSpecification()))){
            wrapper.like("specification", String.valueOf(entity.getSpecification()));
        }
        //根据有效期（月）模糊查询
        if(entity.getValidityPeriod()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getValidityPeriod()))){
            wrapper.like("validity_period", String.valueOf(entity.getValidityPeriod()));
        }
        //根据剂型模糊查询
        if(entity.getFormulation()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getFormulation()))){
            wrapper.like("formulation", String.valueOf(entity.getFormulation()));
        }
        //根据适应症模糊查询
        if(entity.getIndication()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getIndication()))){
            wrapper.like("indication", String.valueOf(entity.getIndication()));
        }
        //根据用法用量模糊查询
        if(entity.getDosage()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDosage()))){
            wrapper.like("dosage", String.valueOf(entity.getDosage()));
        }
        //根据不良反应模糊查询
        if(entity.getAdverseReactions()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getAdverseReactions()))){
            wrapper.like("adverse_reactions", String.valueOf(entity.getAdverseReactions()));
        }
        //根据温馨提示模糊查询
        if(entity.getTips()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTips()))){
            wrapper.like("tips", String.valueOf(entity.getTips()));
        }
        //根据成份模糊查询
        if(entity.getChocolateIngredient()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getChocolateIngredient()))){
            wrapper.like("chocolate_ingredient", String.valueOf(entity.getChocolateIngredient()));
        }
        //根据性状模糊查询
        if(entity.getTraits()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTraits()))){
            wrapper.like("traits", String.valueOf(entity.getTraits()));
        }
        //根据作用类别模糊查询
        if(entity.getEffectCategory()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getEffectCategory()))){
            wrapper.like("effect_category", String.valueOf(entity.getEffectCategory()));
        }
        //根据禁忌模糊查询
        if(entity.getTaboo()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getTaboo()))){
            wrapper.like("taboo", String.valueOf(entity.getTaboo()));
        }
        //根据注意事项模糊查询
        if(entity.getPrecautions()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPrecautions()))){
            wrapper.like("precautions", String.valueOf(entity.getPrecautions()));
        }
        //根据药物相互作用模糊查询
        if(entity.getMedicineInteractions()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getMedicineInteractions()))){
            wrapper.like("medicine_interactions", String.valueOf(entity.getMedicineInteractions()));
        }
        //根据药理作用模糊查询
        if(entity.getPharmacologicalAction()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPharmacologicalAction()))){
            wrapper.like("pharmacological_action", String.valueOf(entity.getPharmacologicalAction()));
        }
        //根据贮藏模糊查询
        if(entity.getStorage()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getStorage()))){
            wrapper.like("storage", String.valueOf(entity.getStorage()));
        }
        //根据包装模糊查询
        if(entity.getSubpackage()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getSubpackage()))){
            wrapper.like("subpackage", String.valueOf(entity.getSubpackage()));
        }
        //根据执行标准模糊查询
        if(entity.getExecutiveStandard()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getExecutiveStandard()))){
            wrapper.like("executive_standard", String.valueOf(entity.getExecutiveStandard()));
        }
        //根据品牌模糊查询
        if(entity.getBrand()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getBrand()))){
            wrapper.like("brand", String.valueOf(entity.getBrand()));
        }
        //根据孕妇及哺乳期妇女用药模糊查询
        if(entity.getPregnantAndLactatingWomen()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPregnantAndLactatingWomen()))){
            wrapper.like("pregnant_and_lactating_women", String.valueOf(entity.getPregnantAndLactatingWomen()));
        }
        //根据儿童用药模糊查询
        if(entity.getChildMedication()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getChildMedication()))){
            wrapper.like("child_medication", String.valueOf(entity.getChildMedication()));
        }
        //根据老年用药模糊查询
        if(entity.getElderlyPatientsMedication()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getElderlyPatientsMedication()))){
            wrapper.like("elderly_patients_medication", String.valueOf(entity.getElderlyPatientsMedication()));
        }
        //根据药理毒理模糊查询
        if(entity.getPharmacologyAndToxicology()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPharmacologyAndToxicology()))){
            wrapper.like("pharmacology_and_toxicology", String.valueOf(entity.getPharmacologyAndToxicology()));
        }
        //根据药代动力学模糊查询
        if(entity.getPharmacokinetics()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getPharmacokinetics()))){
            wrapper.like("pharmacokinetics", String.valueOf(entity.getPharmacokinetics()));
        }
        //根据说明书模糊查询
        if(entity.getInstructionManual()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getInstructionManual()))){
            wrapper.like("instruction_manual", String.valueOf(entity.getInstructionManual()));
        }
        //根据原地址模糊查询
        if(entity.getOriginalUrl()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getOriginalUrl()))){
            wrapper.like("original_url", String.valueOf(entity.getOriginalUrl()));
        }
        //根据状态（0启用1=停用）模糊查询
        if(entity.getStatus()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getStatus()))){
            wrapper.like("status", String.valueOf(entity.getStatus()));
        }
        //根据创建人模糊查询
        if(entity.getCreateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateBy()))){
            wrapper.like("create_by", String.valueOf(entity.getCreateBy()));
        }
        //根据创建时间模糊查询
        if(entity.getCreateDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getCreateDate()))){
            wrapper.like("create_date", String.valueOf(entity.getCreateDate()));
        }
        //根据修改人模糊查询
        if(entity.getUpdateBy()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateBy()))){
            wrapper.like("update_by", String.valueOf(entity.getUpdateBy()));
        }
        //根据修改时间模糊查询
        if(entity.getUpdateDate()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getUpdateDate()))){
            wrapper.like("update_date", String.valueOf(entity.getUpdateDate()));
        }
        //根据备注模糊查询
        if(entity.getRemarks()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getRemarks()))){
            wrapper.like("remarks", String.valueOf(entity.getRemarks()));
        }
        //根据删除标识（1=已删除0未删除）模糊查询
        if(entity.getDelFlag()!=null&&StringUtils.isNotBlank(String.valueOf(entity.getDelFlag()))){
            wrapper.like("del_flag", String.valueOf(entity.getDelFlag()));
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
