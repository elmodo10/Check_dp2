<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:list>
	<acme:list-column code="inventor.quantity.list.label.number" path="number" width="20%"/>
	<acme:list-column code="inventor.quantity.list.label.name" path="item.name" width="20%"/>			
	<acme:list-column code="inventor.quantity.list.label.code" path="item.code" width="20%"/>		
	<acme:list-column code="inventor.quantity.list.label.retailPrice" path="item.retailPrice" width="20%"/>	
	<acme:list-column code="inventor.label.moneyExchange" path="moneyExchange"/>
	<acme:list-column code="inventor.quantity.list.label.status" path="item.status" width="20%"/>		
</acme:list>


<acme:button code="inventor.toolkit.form.button.quantity" action="/inventor/quantity/create?id=${param.id}"/>
		