package com.taiji.dianthus.sso;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * @ClassName SsoService
 * @Description
 * @Author H.M
 * @Date 2020/4/1
 */
@WebService
public interface SsoService {
    @WebMethod
    public String SynchronizedInfo(int operateId, String operateCode);

}
