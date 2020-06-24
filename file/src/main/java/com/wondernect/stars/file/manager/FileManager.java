package com.wondernect.stars.file.manager;

import com.wondernect.elements.rdb.base.manager.BaseStringManager;
import com.wondernect.elements.rdb.criteria.Criteria;
import com.wondernect.elements.rdb.criteria.Restrictions;
import com.wondernect.elements.rdb.request.PageRequestData;
import com.wondernect.elements.rdb.request.SortData;
import com.wondernect.elements.rdb.response.PageResponseData;
import com.wondernect.stars.file.model.File;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created on 2018/4/9.
 * wondernect.com
 * @author sunbeam
 */
@Service
public class FileManager extends BaseStringManager<File> {

    public List<File> list(String userId, Boolean deleted, List<SortData> sortDataList) {
        Criteria<File> fileCriteria = new Criteria<>();
        fileCriteria.add(Restrictions.eq("createUser", userId));
        fileCriteria.add(Restrictions.eq("deleted", deleted));
        return super.findAll(fileCriteria, sortDataList);
    }

    public PageResponseData<File> page(String userId, Boolean deleted, PageRequestData pageRequestData) {
        Criteria<File> fileCriteria = new Criteria<>();
        fileCriteria.add(Restrictions.eq("createUser", userId));
        fileCriteria.add(Restrictions.eq("deleted", deleted));
        return super.findAll(fileCriteria, pageRequestData);
    }
}
