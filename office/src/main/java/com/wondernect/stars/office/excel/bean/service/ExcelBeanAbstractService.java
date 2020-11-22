package com.wondernect.stars.office.excel.bean.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.office.excel.bean.model.ExcelBean;
import com.wondernect.stars.office.excel.dto.bean.ExcelBeanResponseDTO;
import com.wondernect.stars.office.excel.dto.bean.ListExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.bean.PageExcelBeanRequestDTO;
import com.wondernect.stars.office.excel.dto.bean.SaveExcelBeanRequestDTO;
import org.hibernate.criterion.MatchMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * excel导入导出实体类服务抽象实现类
 *
 * @author chenxun 2020-11-17 16:18:30
 **/
@Service
public abstract class ExcelBeanAbstractService extends BaseStringService<ExcelBeanResponseDTO, ExcelBean> implements ExcelBeanInterface {

    @Transactional
    @Override
    public ExcelBeanResponseDTO create(SaveExcelBeanRequestDTO saveExcelBeanRequestDTO) {
        if (ESObjectUtils.isNotNull(findByBean(saveExcelBeanRequestDTO.getBean()))) {
            throw new BusinessException("excel导入导出实体类已存在");
        }
        ExcelBean excelBean = new ExcelBean();
        ESBeanUtils.copyProperties(saveExcelBeanRequestDTO, excelBean);
        return super.save(excelBean);
    }

    @Transactional
    @Override
    public ExcelBeanResponseDTO update(String id, SaveExcelBeanRequestDTO saveExcelBeanRequestDTO) {
        ExcelBean excelBean = super.findEntityById(id);
        if (ESObjectUtils.isNull(excelBean)) {
            throw new BusinessException("excel导入导出实体类不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveExcelBeanRequestDTO, excelBean);
        return super.save(excelBean);
    }

    @Override
    public ExcelBeanResponseDTO findByBean(String bean) {
        Criteria<ExcelBean> excelBeanCriteria = new Criteria<>();
        excelBeanCriteria.add(Restrictions.eq("bean", bean));
        ExcelBean excelBean = super.findOneEntity(excelBeanCriteria, new ArrayList<>());
        if (ESObjectUtils.isNull(excelBean)) {
            return null;
        }
        return generate(excelBean);
    }

    @Override
    public List<ExcelBeanResponseDTO> list(ListExcelBeanRequestDTO listExcelBeanRequestDTO) {
        Criteria<ExcelBean> excelBeanCriteria = new Criteria<>();
        excelBeanCriteria.add(Restrictions.eq("bean", listExcelBeanRequestDTO.getBean()));
        excelBeanCriteria.add(Restrictions.like("name", listExcelBeanRequestDTO.getName(), MatchMode.ANYWHERE));
        return super.findAll(excelBeanCriteria, listExcelBeanRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<ExcelBeanResponseDTO> page(PageExcelBeanRequestDTO pageExcelBeanRequestDTO) {
        Criteria<ExcelBean> excelBeanCriteria = new Criteria<>();
        excelBeanCriteria.add(Restrictions.eq("bean", pageExcelBeanRequestDTO.getBean()));
        excelBeanCriteria.add(Restrictions.like("name", pageExcelBeanRequestDTO.getName(), MatchMode.ANYWHERE));
        return super.findAll(excelBeanCriteria, pageExcelBeanRequestDTO.getPageRequestData());
    }

    @Override
    public ExcelBeanResponseDTO generate(ExcelBean excelBean) {
        ExcelBeanResponseDTO excelBeanResponseDTO = new ExcelBeanResponseDTO();
        ESBeanUtils.copyProperties(excelBean, excelBeanResponseDTO);
        return excelBeanResponseDTO;
    }
}