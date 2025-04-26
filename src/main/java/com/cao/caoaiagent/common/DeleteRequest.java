package com.cao.caoaiagent.common;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除请求包装类
 */
@Data
public class DeleteRequest implements Serializable {

    private static final long serialVersionUID = 1945582874642307529L;
    /**
     * id
     */
    private Long id;
}
