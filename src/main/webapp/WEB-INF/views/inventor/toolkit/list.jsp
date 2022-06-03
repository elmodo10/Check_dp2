<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.toolkit.list.label.title" path="title" width="20%"/>
	<acme:list-column code="inventor.toolkit.list.label.descripcion" path="descripcion" width="20%"/>
	<acme:list-column code="inventor.toolkit.list.label.link" path="link" width="20%"/>
</acme:list>

<acme:button code="inventor.toolkit.create" action="/inventor/toolkit/create"/>		
