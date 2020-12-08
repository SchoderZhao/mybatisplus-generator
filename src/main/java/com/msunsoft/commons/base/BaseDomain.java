package com.msunsoft.commons.base;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zxl
 * @create 2020/8/21 0021 16:52
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public abstract class BaseDomain implements Serializable {
    private static final long serialVersionUID = -6912501145172883332L;
}
