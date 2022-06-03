<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="authenticated.announcement.list.label.title" path="title" width="20%"/>
	<acme:list-column code="authenticated.announcement.list.label.creation" path="creation" width="20%"/>			
	<acme:list-column code="authenticated.announcement.list.label.link" path="link" width="20%"/>
	<acme:list-column code="authenticated.announcement.list.label.critic" path="critic" width="20%"/>					
</acme:list>
