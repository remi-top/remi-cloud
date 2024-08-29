package com.alibaba.nacos.console.paramcheck;

import com.alibaba.nacos.common.paramcheck.ParamInfo;
import com.alibaba.nacos.common.utils.StringUtils;
import com.alibaba.nacos.core.paramcheck.AbstractHttpParamExtractor;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Console default http param extractor.
 *
 * @author zhuoguang
 */
public class ConsoleDefaultHttpParamExtractor extends AbstractHttpParamExtractor {

	@Override
	public List<ParamInfo> extractParam(HttpServletRequest request) {
		ParamInfo paramInfo = new ParamInfo();
		paramInfo.setNamespaceId(getAliasNamespaceId(request));
		paramInfo.setNamespaceShowName(getAliasNamespaceShowName(request));
		ArrayList<ParamInfo> paramInfos = new ArrayList<>();
		paramInfos.add(paramInfo);
		return paramInfos;
	}

	private String getAliasNamespaceId(HttpServletRequest request) {
		String namespaceId = request.getParameter("namespaceId");
		if (StringUtils.isBlank(namespaceId)) {
			namespaceId = request.getParameter("customNamespaceId");
		}
		return namespaceId;
	}

	private String getAliasNamespaceShowName(HttpServletRequest request) {
		String namespaceShowName = request.getParameter("namespaceName");
		return namespaceShowName;
	}

}
