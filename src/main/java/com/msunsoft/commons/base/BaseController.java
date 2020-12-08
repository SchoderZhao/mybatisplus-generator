package com.msunsoft.commons.base;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.msunsoft.commons.response.ResponseCode;
import com.msunsoft.commons.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author zxl
 * @create 2020/8/21 0021 17:02
 */
public abstract class BaseController<T extends BaseDomain, S extends IBaseService<T>> {

    @Resource
    protected HttpServletRequest request;

    @Autowired
    protected S service;

    /**
     * 新增
     *
     * @param domain 领域模型
     * @return {@link ResponseResult}
     */
    @PostMapping("create")
    public ResponseResult create(@Valid @RequestBody T domain) {
        // 业务逻辑
        boolean created = service.create(domain);
        if (created) {
            return ResponseResult.success("创建成功");
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 删除
     *
     * @param id {@code Long}
     * @return {@link ResponseResult}
     */
    @DeleteMapping("remove/{id}")
    public ResponseResult remove(@PathVariable Long id) {
        // 业务逻辑
        boolean deleted = service.remove(id);
        if (deleted) {
            return ResponseResult.success("删除成功");
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 修改
     *
     * @param domain 领域模型
     * @return {@link ResponseResult}
     */
    @PutMapping("update")
    public ResponseResult update(@Valid @RequestBody T domain) {
        // 业务逻辑
        boolean updated = service.update(domain);
        if (updated) {
            return ResponseResult.success("编辑成功");
        }

        return ResponseResult.failure(ResponseCode.INTERFACE_ADDRESS_INVALID);
    }

    /**
     * 获取
     *
     * @param id {@code Long}
     * @return {@link ResponseResult}
     */
    @GetMapping("get/{id}")
    public ResponseResult get(@PathVariable Long id) {
        T domain = service.get(id);
        return ResponseResult.success(domain);
    }

    /**
     * 分页
     *
     * @param current {@code int} 页码
     * @param size    {@code int} 笔数
     * @return {@link ResponseResult}
     */
    @GetMapping("page")
    public ResponseResult page(
            @RequestParam int current, @RequestParam int size, @ModelAttribute T domain) {
        IPage<T> page = service.page(current, size, domain);
        return ResponseResult.success(page);
    }
}

