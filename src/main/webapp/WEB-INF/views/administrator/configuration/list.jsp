<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="administrator.configuration.list.label.weakSpamWords" path="weakSpamWords" />
	<acme:list-column code="administrator.configuration.list.label.weakSpamThreshold" path="weakSpamThreshold" />
	<acme:list-column code="administrator.configuration.list.label.strongSpamWords" path="strongSpamWords" />
	<acme:list-column code="administrator.configuration.list.label.strongSpamThreshold" path="strongSpamThreshold" />
	<acme:list-column code="administrator.configuration.list.label.acceptedCurr" path="acceptedCurr" />
	<acme:list-column code="administrator.configuration.list.label.defaultCurr" path="defaultCurr" />
</acme:list>
