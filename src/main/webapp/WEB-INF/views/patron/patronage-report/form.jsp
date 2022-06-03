
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form readonly="true">
    <acme:input-textbox code="patron.patronageReport.list.label.seqNumber" path="seqNumber"/>	
	<acme:input-textbox code="patron.patronageReport.list.label.createdAt" path="createdAt"/>	
	<acme:input-textbox code="patron.patronageReport.list.label.memorandum" path="memorandum"/>	
	<acme:input-url code="patron.patronageReport.list.label.link" path="link"/>	
</acme:form>
