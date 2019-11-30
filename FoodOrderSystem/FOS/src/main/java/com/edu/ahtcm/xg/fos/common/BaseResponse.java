package com.edu.ahtcm.xg.fos.common;

/**
 * 响应数据基类.
 */
public class BaseResponse {


    /**
     * common共同部数据.
     */
    private CommonInfo common;

    public BaseResponse() {
    }

    public BaseResponse(CommonInfo common) {
        this.common = common;
    }

    /**
     * 共同数据取得.
     * @return  共同数据.
     */
    public CommonInfo getCommon() {
        return common;
    }

    /**
     * 设置共通数据.
     * @param common 共通数据.
     */
    public void setCommon(CommonInfo common) {
        this.common = common;
    }
}
