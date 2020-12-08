package com.msunsoft.commons.base;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.msunsoft.commons.response.ResponseCode;
import com.msunsoft.sptapi.apiserver.exceptions.BusinessException;

/**
 * @author zxl
 * @create 2020/8/21 0021 16:56
 */
public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseDomain> extends ServiceImpl<M, T> implements IBaseService<T> {

    /**
     * 通过反射调用 getId()
     */
    private static final String INVOKE_ID = "getId";

    /**
     * 检查字段：ID
     */
    protected static final String ID = "id";

    @Override
    public boolean create(T domain) {
        return super.save(domain);
    }

    @Override
    public boolean remove(Long id) {
        if (checkId(id)) {
            return super.removeById(id);
        }
        return false;
    }

    @Override
    public boolean update(T domain) {
        try {
            if (checkId((Long) domain.getClass().getMethod(INVOKE_ID).invoke(domain))) {
                return super.updateById(domain);
            }
            return false;
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }

    }

    @Override
    public T get(Long id) {
        T domain = super.getById(id);
        if (null == domain) {
            throw new BusinessException(ResponseCode.RESULT_DATA_NONE);
        }
        return domain;
    }

    @Override
    public IPage<T> page(int current, int size, T domain) {
        Page<T> page = new Page<>(current, size);
        LambdaQueryWrapper<T> wrapper = new LambdaQueryWrapper<>();
        return super.page(page, wrapper);
    }

    /**
     * 检查 ID 是否存在
     *
     * @param id {@code Long} ID
     * @return {@code boolean} ID 不存在则抛出异常
     */
    protected boolean checkId(Long id) {
        if (!checkUniqueness(ID, id)) {
//            throw new BusinessException(ResponseCode.RESULT_DATA_NONE);
        }
        return true;
    }

    protected boolean checkUniqueness(String column, Object value) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        wrapper.eq(column, value);
        return super.count(wrapper) > 0;
    }
}
