package com.wondernect.stars.app.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.easyoffice.excel.ESExcelItem;
import com.wondernect.elements.easyoffice.excel.ESExcelItemHandler;
import com.wondernect.elements.easyoffice.excel.ESExcelUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.AppResponseDTO;
import com.wondernect.stars.app.dto.ListAppRequestDTO;
import com.wondernect.stars.app.dto.PageAppRequestDTO;
import com.wondernect.stars.app.dto.SaveAppRequestDTO;
import com.wondernect.stars.app.model.App;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 应用服务抽象实现类
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
@Service
public abstract class AppAbstractService extends BaseStringService<AppResponseDTO, App> implements AppInterface {

    @Transactional
    @Override
    public AppResponseDTO create(SaveAppRequestDTO saveAppRequestDTO) {
        App app = new App();
        ESBeanUtils.copyProperties(saveAppRequestDTO, app);
        return super.save(app);
    }

    @Transactional
    @Override
    public AppResponseDTO update(String id, SaveAppRequestDTO saveAppRequestDTO) {
        App app = super.findEntityById(id);
        if (ESObjectUtils.isNull(app)) {
            throw new BusinessException("应用不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveAppRequestDTO, app);
        return super.save(app);
    }

    @Override
    public List<AppResponseDTO> list(ListAppRequestDTO listAppRequestDTO) {
        Criteria<App> appCriteria = new Criteria<>();
        appCriteria.add(Restrictions.eq("userId", listAppRequestDTO.getUserId()));
        return super.findAll(appCriteria, listAppRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<AppResponseDTO> page(PageAppRequestDTO pageAppRequestDTO) {
        Criteria<App> appCriteria = new Criteria<>();
        appCriteria.add(Restrictions.eq("userId", pageAppRequestDTO.getUserId()));
        return super.findAll(appCriteria, pageAppRequestDTO.getPageRequestData());
    }

    @Override
    public List<ESExcelItem> excelItemList() {
        return super.excelItemList(AppResponseDTO.class);
    }

    @Override
    public void excelDataExport(String exportServiceIdentifier, ListAppRequestDTO listAppRequestDTO, HttpServletRequest request, HttpServletResponse response) {
        super.excelDataExport(exportServiceIdentifier, excelItemList(), list(listAppRequestDTO), "应用信息导出", "应用信息导出", "应用信息导出", request, response);
    }

    @Override
    public AppResponseDTO generate(App app) {
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        ESBeanUtils.copyProperties(app, appResponseDTO);
        appResponseDTO.setId(app.getId());
        return appResponseDTO;
    }

    @Override
    public List<ESExcelItemHandler> generateExcelExportItemHandlerList(String exportServiceIdentifier) {
        switch (exportServiceIdentifier) {
            default: {
                return new ArrayList<>();
            }
        }
    }
}