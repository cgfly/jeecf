package org.jeecf.manager.common.utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.jeecf.common.enums.SplitCharEnum;
import org.jeecf.common.lang.StringUtils;
import org.jeecf.manager.common.model.PermissionEntity;
import org.jeecf.manager.common.properties.PowerProperties;
import org.jeecf.manager.module.userpower.facade.SecurityFacade;
import org.jeecf.manager.module.userpower.model.domain.SysPower;
import org.jeecf.manager.module.userpower.model.po.SysPowerPO;
import org.jeecf.manager.module.userpower.model.query.SysPowerQuery;
import org.jeecf.manager.module.userpower.model.result.SysPowerResult;
import org.jeecf.manager.module.userpower.service.SysPowerService;

/**
 * 权限工具类
 * 
 * @author jianyiming
 *
 */
public class PermissionUtils {

	private static SecurityFacade securityFacade = SpringContextUtils.getBean("securityFacade", SecurityFacade.class);
	
    private static SysPowerService sysPowerService = SpringContextUtils.getBean("sysPowerService", SysPowerService.class);
    
    private static PowerProperties powerProperties = SpringContextUtils.getBean("powerProperties", PowerProperties.class);
    
    public static final String MATCH_PERMISSION = powerProperties.getSuffixBaseName();

	public static final String[] RESOLVE_PERMISSION = { powerProperties.getSuffixEditName(), powerProperties.getSuffixViewName()};

	/**
	 * 权限格式验证
	 */
	public static boolean isPermission(String permission) {
		if (StringUtils.isNotEmpty(permission)) {
			if (StringUtils.split(permission, SplitCharEnum.COLON.getName()).length > 1) {
				return true;
			}
			return false;
		}
		return false;

	}

	/**
	 * 权限通配符解析
	 * 
	 * @param permission
	 * @return
	 */
	public static String[] getResolvePermissions(String permission) {
		if (StringUtils.isNotEmpty(permission)) {
			String matchPermission = StringUtils.substringAfterLast(permission, ":");
			if (powerProperties.getSuffixBaseName().equals(matchPermission)) {
				String[] resolvePermission = new String[RESOLVE_PERMISSION.length];
				String prefixPermission = StringUtils.substringBeforeLast(permission, ":");
				for (int i = 0; i < RESOLVE_PERMISSION.length; i++) {
					resolvePermission[i] = prefixPermission + SplitCharEnum.COLON.getName() + RESOLVE_PERMISSION[i];
				}
				return resolvePermission;
			}
		}
		return new String[] { permission };
	}

	/**
	 * 权限过滤
	 */
	public static <T extends PermissionEntity> List<T> filter(List<T> permissions) {
		String userId = UserUtils.getCurrentUserId();
		Set<String> sysPermissionSet = securityFacade.findPermission(userId).getData();
		if (CollectionUtils.isNotEmpty(permissions) && CollectionUtils.isNotEmpty(sysPermissionSet)) {
			return permissions.stream().filter(t -> {
				String[] tPermissions = PermissionUtils.getResolvePermissions(t.getPermission());
				for (String tPermission : tPermissions) {
					for (String permission : sysPermissionSet) {
						if (permission.equals(tPermission)) {
							return true;
						}
					}
				}
				return false;
			}).collect(Collectors.toList());
		}
		return null;
	}

	/**
	 * 判断用户是否具备该权限
	 * 
	 * @param permission
	 * @return
	 */
	public static boolean isExist(String permission) {
		String userId = UserUtils.getCurrentUserId();
		Set<String> sysPermissionSet = securityFacade.findPermission(userId).getData();
		if (CollectionUtils.isNotEmpty(sysPermissionSet)) {
			for (String permiss : sysPermissionSet) {
				if (permiss.equals(permission)) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 判断用户是否具备该权限
	 * 
	 * @param permission
	 * @return
	 */
	public static boolean isNotExist(String permission) {
		return !PermissionUtils.isExist(permission);
	}

	/**
	 * 判断用户是否具备该权限
	 * 
	 * @param permission
	 * @return
	 */
	public static boolean isPrefixExist(String permission, List<SysPower> sysPowerList) {
		boolean flag = true;
		String beforeLast = StringUtils.substringBeforeLast(permission, ":");
		for (SysPower power : sysPowerList) {
			String tempBeforeLast = StringUtils.substringBeforeLast(power.getPermission(), ":");
			if (tempBeforeLast.equals(beforeLast) && !power.getPermission().equals(permission)) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	public static void addPower(boolean flag,String prefixName,String permission) {
		// 如果为插入操作则插入相关权限
		if (flag) {
			String prefixPermission = StringUtils.substringBefore(permission, SplitCharEnum.COLON.getName());
			String suffixPermission = StringUtils.substringAfterLast(permission, SplitCharEnum.COLON.getName());
			String queryPermission = prefixPermission + SplitCharEnum.COLON.getName()
					+ powerProperties.getSuffixBaseName();
			SysPowerQuery sysPowerQuery = new SysPowerQuery();
			sysPowerQuery.setPermission(queryPermission);
			List<SysPowerResult> sysPowerResultList = sysPowerService.findList(new SysPowerPO(sysPowerQuery)).getData();
			if (CollectionUtils.isNotEmpty(sysPowerResultList)) {
				SysPowerResult sysPowerResult = sysPowerResultList.get(0);
				SysPower sysPower = new SysPower();
				sysPower.setLevel(sysPowerResult.getLevel() + 1);
				sysPower.setPermission(permission);
				sysPower.setParentId(sysPowerResult.getId());
				sysPower.setParentIds(sysPowerResult.getId());
				sysPower.setName(prefixName + suffixPermission + powerProperties.getFunctionName());
				sysPower.setSort(10);
				sysPowerService.save(sysPower);
			}
		} else {
			SysPowerQuery sysPowerQuery = new SysPowerQuery();
			sysPowerQuery.setPermission(permission);
			List<SysPowerResult> sysPowerResultList = sysPowerService.findList(new SysPowerPO(sysPowerQuery)).getData();
			if (CollectionUtils.isNotEmpty(sysPowerResultList)) {
				SysPowerResult sysPowerResult = sysPowerResultList.get(0);
				sysPowerResult.setPermission(permission);
				sysPowerService.save(sysPowerResult);
			}
		}
	}

	public static void deletePower(String permission) {
		SysPowerQuery sysPowerQuery = new SysPowerQuery();
		sysPowerQuery.setPermission(permission);
		List<SysPowerResult> sysPowerResultList = sysPowerService.findList(new SysPowerPO(sysPowerQuery)).getData();
		if (CollectionUtils.isNotEmpty(sysPowerResultList)) {
			SecurityFacade securityFacade = SpringContextUtils.getBean("securityFacade", SecurityFacade.class);
			securityFacade.deletePower(sysPowerResultList.get(0).getId());
		}
	}

}
