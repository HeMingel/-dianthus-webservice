package com.taiji.dianthus.sso;

/**
 * @ClassName SsoServiceImp
 * @Description
 * @Author H.M
 * @Date 2020/4/1
 */

import com.taiji.dianthus.config.BeanConfig;
import com.taiji.dianthus.dao.sys.OrgRepository;
import com.taiji.dianthus.dao.sys.RoleUserRepository;
import com.taiji.dianthus.dao.sys.UserRepository;
import com.taiji.dianthus.domain.sys.DSysOrg;
import com.taiji.dianthus.domain.sys.DSysUser;
import org.apache.axis2.AxisFault;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.rpc.client.RPCServiceClient;
import org.apache.axis2.transport.http.HTTPConstants;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;
import javax.jws.WebService;
import javax.xml.namespace.QName;
import java.util.List;
import java.util.Map;

import static com.taiji.dianthus.common.Constant.DEFAULT_PASSWORD;

@WebService(targetNamespace = "http://renshi/services", name = "sso", serviceName = "ssoWebservice")
public class SsoServiceImpl implements SsoService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Value("${platform_info_webserver.url}")
    private String platformInfoWebserverUrl;

    @Value("${platform_user_webserver_url_qname}")
    private String userWebserverUrlQname;

    @Autowired
    BeanConfig beanIns;
    @Autowired
    private UserRepository userRepository;
    @Resource
    private OrgRepository orgRepository;
    @Resource
    private RoleUserRepository roleUserRepository;

    @Override
    public String SynchronizedInfo(int operateId, String operateCode) {
        String msg = "";
        try {
            if (operateId == 11 || operateId == 12) {
                Map map = getUserOrOrg(operateCode, "getUserInfoByXml");
                msg = saveOrUpdateUser(map);
            } else if (operateId == 13) {
                msg = deleteUser(operateCode);
            } else if (operateId == 41 || operateId == 42) {
                Map map = getUserOrOrg(operateCode, "getDeptInfoByXml");
                msg = saveOrUpdateDept(map);
            } else if (operateId == 43) {
                msg = deleteDept(operateCode);
            } else {
                msg = "-1002";
            }
        } catch (Exception e) {
            e.printStackTrace();
            msg = "-1001";
        }
        return msg;
    }

    /**
     * 回调支撑平台webservice
     *
     * @param operateCode
     * @param localPart
     * @return
     * @throws AxisFault
     * @throws DocumentException
     */

    private Map getUserOrOrg(String operateCode, String localPart) throws AxisFault, DocumentException {
        RPCServiceClient serviceClient;
        serviceClient = new RPCServiceClient();
        Options options = serviceClient.getOptions();
        options.setProperty(HTTPConstants.CONNECTION_TIMEOUT, new Integer(48000000));
        EndpointReference targetEPR = new EndpointReference(platformInfoWebserverUrl + "?temp=00");
        options.setTo(targetEPR);
        options.setManageSession(true);
        options.setProperty(HTTPConstants.REUSE_HTTP_CLIENT, true);
        QName opGetAllLegalInfor = new QName(userWebserverUrlQname, localPart);
        Object[] opGetAllLegalInforArgs = new Object[]{operateCode};
        Class[] returnTypes = new Class[]{String.class};
        Object[] response = serviceClient.invokeBlocking(opGetAllLegalInfor, opGetAllLegalInforArgs, returnTypes);
        serviceClient.cleanupTransport();
        String result = (String) response[0];
        result = result.replace("<userinfo>", "").replace("</userinfo>", "");
        return ResXmlUtil.parseXmlStr(result);
    }

    /**
     * 新增和修改用户
     *
     * @param map
     * @return
     */
    private String saveOrUpdateUser(Map map) {
        //用户登录名
        String logname = (String) map.get("logname");
        System.out.println("logname==" + logname);
        //用户名
        String name = (String) map.get("name");
        System.out.println("name==" + name);
        //座机
        String officePhone = (String) map.get("officephone");
        System.out.println("officePhone==" + officePhone);
        //手机
        String mobilePhone = (String) map.get("mobilephone");
        System.out.println("mobilePhone==" + mobilePhone);
        //职位
        String userDuty = (String) map.get("userduty");
        System.out.println("userDuty==" + userDuty);
        //组织的唯一标识
        String orgCode = (String) map.get("orgcode");
        System.out.println("orgCode==" + orgCode);
        //用户排序
        String userOrder = (String) map.get("userorder");
        System.out.println("userOrder==" + userOrder);
        //职务
        String userpotitle = (String) map.get("userpotitle");
        System.out.println("userPost==" + userpotitle);
        //性别
        String sex = (String) map.get("sex");
        System.out.println("sex==" + sex);

        //用户32位码
        String usercode = (String) map.get("usercode");
        System.out.println("usercode==" + usercode);
//        //分管多个部门
//        List<Map<String, Object>> list = (List<Map<String, Object>>) map.get("partTimes");
        try {
            DSysOrg org = orgRepository.getByPushId(orgCode);
            if (org == null) {
                return "-403";
            }
            DSysUser user = userRepository.getById(usercode);
            //新建用户
            if (null == user ) {
                user = new DSysUser();
                user.setUserLoginName(logname);
                user.setName(name);
                user.setOfficePhone(officePhone);
                user.setUserPhone(mobilePhone);
                user.setUserDuty(userDuty);
                user.setUserOrder(Integer.parseInt(userOrder));
                user.setUserPosition(userpotitle);
                user.setUserSex(sex);
                user.setPushId(usercode);
                user.setOrgId(org.getId());
                user.setUserPassword(passwordEncoder.encode(DEFAULT_PASSWORD));
                //添加用户信息
                userRepository.saveAndFlush(user);
                System.out.println("添加用户信息成功：" + user.toString());
            } else {
                String userId = user.getId();
                user.setUserPhone(mobilePhone);
                user.setUserOrder(Integer.parseInt(userOrder));
                user.setUserPosition(userpotitle);
                user.setUserLoginName(logname);
                user.setOfficePhone(officePhone);
                user.setName(name);
                user.setOrgId(org.getId());
                user.setUserDuty(userDuty);
                user.setPushId(usercode);
                //修改用户信息
                userRepository.save(user);
                System.out.println("更新用户信息成功：" + user.toString());
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        return "-000";
    }



    /**
     * 组织推送增加修改
     *
     * @param map
     * @return
     */
    private String saveOrUpdateDept(Map map) {
        boolean newFlag = false;
        //初始根目录直接返回成功不处理
        //判断组织code是否存在
       DSysOrg dept = orgRepository.getByPushId((String) map.get("orgcode"));
        if (null==  dept) {
            newFlag = true;
            dept = new  DSysOrg();
        }
        //parentcode为0的是一级目录，不检查父节点
        if (!"0".equals(map.get("parentcode"))) {
            DSysOrg parentDept = orgRepository.getByPushId((String) map.get("parentcode"));
            if (parentDept == null) {
                return "-102";
            }
            dept.setParentId(parentDept.getId());
        }
        dept.setOrgName((String) map.get("name"));
        dept.setPushId((String) map.get("orgcode"));
        String orgOrder = (String) map.get("orgorder");
        if (orgOrder == null) {
            dept.setOrgOrder(0);
        } else {
            dept.setOrgOrder(Integer.parseInt(orgOrder));
        }

        orgRepository.save(dept);
        return "-000";
    }

    /**
     * 删除组织
     *
     * @param code
     * @return
     */
    private String deleteDept(String code) {
        DSysOrg dept  = orgRepository.getByPushId(code);
        if (null == dept) {
            return "-201";
        }
        List<DSysUser> userList= userRepository.getUserCodeByOrgId(code);
        if ( null != userList && userList.size() > 0) {
            return "-203";
        }
        List<DSysOrg> organizes = orgRepository.findByParentCode(dept.getId());
        if (organizes != null && organizes.size() > 0) {
            return "-202";
        }
        orgRepository.updateFlag(dept.getId());
        return "-000";
    }

    /**
     * 删除用户
     *
     * @param code
     * @return
     */
    private String deleteUser(String code) {
        DSysUser  user = userRepository.getUserByPushId(code);
        //用户不存在
        if (user == null) {
            return "-601";
        }
        roleUserRepository.deleteByUserId(user.getId());
        userRepository.updateFlag(user.getId());
        return "-000";
    }
}
