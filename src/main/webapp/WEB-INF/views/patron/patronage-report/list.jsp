
<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="patron.patronageReport.list.label.seqNumber" path="seqNumber" width="10%"/>
	<acme:list-column code="patron.patronageReport.list.label.createdAt" path="createdAt" width="10%"/>
	<acme:list-column code="patron.patronageReport.list.label.memorandum" path="memorandum" width="50%"/>
	<acme:list-column code="patron.patronageReport.list.label.link" path="link" width="30%"/>
</acme:list>