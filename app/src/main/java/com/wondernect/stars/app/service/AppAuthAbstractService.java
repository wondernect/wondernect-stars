package com.wondernect.stars.app.service;

import com.wondernect.elements.common.exception.BusinessException;
import com.wondernect.elements.common.utils.ESBeanUtils;
import com.wondernect.elements.common.utils.ESObjectUtils;
import com.wondernect.elements.common.utils.ESStringUtils;
import com.wondernect.elements.rdb.base.service.BaseStringService;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.app.dto.AuthAppRequestDTO;
import com.wondernect.stars.app.dto.auth.AppAuthResponseDTO;
import com.wondernect.stars.app.dto.auth.ListAppAuthRequestDTO;
import com.wondernect.stars.app.dto.auth.PageAppAuthRequestDTO;
import com.wondernect.stars.app.dto.auth.SaveAppAuthRequestDTO;
import com.wondernect.stars.app.manager.AppAuthManager;
import com.wondernect.stars.app.model.App;
import com.wondernect.stars.app.model.AppAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 应用访问认证服务抽象实现类
 *
 * @author chenxun 2020-12-29 16:28:23
 **/
public abstract class AppAuthAbstractService extends BaseStringService<AppAuthResponseDTO, AppAuth> implements AppAuthInterface {

    @Autowired
    private AppService appService;

    @Autowired
    private AppAuthManager appAuthManager;

    @Transactional
    @Override
    public AppAuthResponseDTO create(SaveAppAuthRequestDTO saveAppAuthRequestDTO) {
        if (ESObjectUtils.isNotNull(appAuthManager.findByAppIdAndUserId(saveAppAuthRequestDTO.getAppId(), saveAppAuthRequestDTO.getUserId()))) {
            throw new BusinessException("应用已绑定指定用户访问权限");
        }
        AppAuth appAuth = new AppAuth();
        ESBeanUtils.copyProperties(saveAppAuthRequestDTO, appAuth);
        appAuth.setVisible(true);
        return super.save(appAuth);
    }

    @Transactional
    @Override
    public AppAuthResponseDTO update(String id, SaveAppAuthRequestDTO saveAppAuthRequestDTO) {
        AppAuth appAuth = super.findEntityById(id);
        if (ESObjectUtils.isNull(appAuth)) {
            throw new BusinessException("应用访问认证不存在");
        }
        ESBeanUtils.copyWithoutNullAndIgnoreProperties(saveAppAuthRequestDTO, appAuth);
        return super.save(appAuth);
    }

    @Override
    public AppAuthResponseDTO existByAppIdAndUserId(String appId, String userId) {
        AppAuth appAuth = appAuthManager.findByAppIdAndUserId(appId, userId);
        if (ESObjectUtils.isNull(appAuth)) {
            throw new BusinessException("应用认证信息不存在");
        }
        AppAuthResponseDTO appAuthResponseDTO = new AppAuthResponseDTO();
        ESBeanUtils.copyProperties(appAuth, appAuthResponseDTO);
        return appAuthResponseDTO;
    }

    void auth(AuthAppRequestDTO authAppRequestDTO) {
        AppAuth appAuth = appAuthManager.findByAppIdAndUserId(authAppRequestDTO.getAppId(), authAppRequestDTO.getUserId());
        if (ESObjectUtils.isNull(appAuth)) {
            throw new BusinessException("应用认证信息不存在");
        }
        if (!ESStringUtils.equals(authAppRequestDTO.getSecret(), appAuth.getSecret())) {
            throw new BusinessException("应用密钥认证失败");
        }
    }

    @Override
    public List<AppAuthResponseDTO> list(ListAppAuthRequestDTO listAppAuthRequestDTO) {
        Criteria<AppAuth> appAuthCriteria = new Criteria<>();
        appAuthCriteria.add(Restrictions.eq("createUser", listAppAuthRequestDTO.getCreateUser()));
        appAuthCriteria.add(Restrictions.eq("userId", listAppAuthRequestDTO.getUserId()));
        appAuthCriteria.add(Restrictions.eq("visible", true));
        return super.findAll(appAuthCriteria, listAppAuthRequestDTO.getSortDataList());
    }

    @Override
    public PageResponseData<AppAuthResponseDTO> page(PageAppAuthRequestDTO pageAppAuthRequestDTO) {
        Criteria<AppAuth> appAuthCriteria = new Criteria<>();
        appAuthCriteria.add(Restrictions.eq("createUser", pageAppAuthRequestDTO.getCreateUser()));
        appAuthCriteria.add(Restrictions.eq("userId", pageAppAuthRequestDTO.getUserId()));
        appAuthCriteria.add(Restrictions.eq("visible", true));
        return super.findAll(appAuthCriteria, pageAppAuthRequestDTO.getPageRequestData());
    }

    @Override
    public AppAuthResponseDTO generate(AppAuth appAuth) {
        AppAuthResponseDTO appAuthResponseDTO = new AppAuthResponseDTO();
        ESBeanUtils.copyProperties(appAuth, appAuthResponseDTO);
        App app = appService.findEntityById(appAuth.getAppId());
        appAuthResponseDTO.setName(ESObjectUtils.isNotNull(app) ? app.getName() : null);
        appAuthResponseDTO.setLogo(ESObjectUtils.isNotNull(app) ? app.getLogo() : null);
        appAuthResponseDTO.setBrief(ESObjectUtils.isNotNull(app) ? app.getBrief() : null);
        appAuthResponseDTO.setWebsite(ESObjectUtils.isNotNull(app) ? app.getWebsite() : null);
        return appAuthResponseDTO;
    }
}