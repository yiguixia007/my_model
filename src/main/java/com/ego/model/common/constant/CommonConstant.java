package com.ego.model.common.constant;

/**
 * @author ego
 * @since 2018-10-27 16:07
 */
public interface CommonConstant {

    /** scope: Session,Cookie */
    String USER_LANGUAGE_NAME = "language";

    /** 返回码 */
    int RESPONSE_CODE_SUCCESS = 0;
    int RESPONSE_CODE_FAILURE = 1;
    int RESPONSE_CODE_RESOURCE_NOT_EXIST = 2;
    int RESPONSE_CODE_ANONYMOUS = 10;
    int RESPONSE_CODE_ACCESS_DENIED = 20;
    int RESPONSE_CODE_LOGIN_OFF = 100;

    /** 分页参数 */
    int DEFAULT_PAGE_NUMBER = 0; // 默认页数：0代表第1页
    int DEFAULT_PAGE_SIZE = 15; // 默认分页大小：15
}
