package com.wondernect.stars.app.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.*;
import com.wondernect.stars.app.model.App;
import com.wondernect.stars.app.model.AppAuth;
import org.hibernate.criterion.MatchMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用服务抽象实现类
 *
 * @author chenxun 2020-09-13 23:02:00
 **/
public abstract class AppAbstractService extends BaseStringService<AppResponseDTO, App> implements AppInterface {

    @Autowired
    private AppAuthService appAuthService;

    @Transactional
    @Override
    public AppResponseDTO create(SaveAppRequestDTO saveAppRequestDTO) {
        App app = new App();
        ESBeanUtils.copyProperties(saveAppRequestDTO, app);
        app = super.saveEntity(app);
        AppAuth appAuth = appAuthService.saveEntity(new AppAuth(app.getId(), saveAppRequestDTO.getSecret(), saveAppRequestDTO.getUserId(), 2, false));
        app.setAppAuthId(appAuth.getId());
        return super.save(app);
    }

    @Transactional
    @Override
    public AppResponseDTO update(String id, SaveAppRequestDTO saveAppRequestDTO) {
        App app = super.findEntityById(id);
        if (ESObjectUtils.isNull(app)) {
            throw new BusinessException("应用不存在");
        }
        AppAuth appAuth = appAuthService.findEntityById(app.getAppAuthId());
        if (ESObjectUtils.isNull(appAuth)) {
            throw new BusinessException("应用认证信息不存在");
        }
        appAuth.setSecret(saveAppRequestDTO.getSecret());
        appAuth.setUserId(saveAppRequestDTO.getUserId());
        appAuthService.saveEntity(appAuth);
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveAppRequestDTO, app);
        return super.save(app);
    }

    @Override
    public void auth(AuthAppRequestDTO authAppRequestDTO) {
        App app = super.findEntityById(authAppRequestDTO.getAppId());
        if (ESObjectUtils.isNull(app)) {
            throw new BusinessException("应用不存在");
        }
        appAuthService.auth(authAppRequestDTO);
    }

    @Override
    public List<AppResponseDTO> list(ListAppRequestDTO listAppRequestDTO) {
        Criteria<App> appCriteria = new Criteria<>();
        appCriteria.add(Restrictions.like("name", listAppRequestDTO.getName(), MatchMode.ANYWHERE));
        appCriteria.add(Restrictions.eq("createUser", listAppRequestDTO.getUserId()));
        return super.findAll(appCriteria, listAppRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<AppResponseDTO> page(PageAppRequestDTO pageAppRequestDTO) {
        Criteria<App> appCriteria = new Criteria<>();
        appCriteria.add(Restrictions.like("name", pageAppRequestDTO.getName(), MatchMode.ANYWHERE));
        appCriteria.add(Restrictions.eq("createUser", pageAppRequestDTO.getUserId()));
        return super.findAll(appCriteria, pageAppRequestDTO.getPageRequestData());
    }

    @Override
    public AppResponseDTO generate(App app) {
        AppResponseDTO appResponseDTO = new AppResponseDTO();
        ESBeanUtils.copyProperties(app, appResponseDTO);
        AppAuth appAuth = appAuthService.findEntityById(app.getAppAuthId());
        appResponseDTO.setSecret(appAuth.getSecret());
        appResponseDTO.setUserId(appAuth.getUserId());
        return appResponseDTO;
    }
}