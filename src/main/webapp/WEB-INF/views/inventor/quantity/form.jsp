<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>
		
	<jstl:choose>
	
		<jstl:when test="${acme:anyOf(command, 'show, update')}">
			<acme:input-integer code="inventor.quantity.form.label.number" path="number" readonly="${toolkit.status == 'PUBLISHED'}" />
			
			<jstl:if test="${toolkit.status == 'NON_PUBLISHED'}" >
				<acme:submit code="inventor.quantity.form.button.update" action="/inventor/quantity/update"/>
			</jstl:if>
			
			<acme:input-textbox code="inventor.quantity.form.label.name" path="item.name" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.code" path="item.code" readonly="true"/>
			<acme:input-textbox code="inventor.quantity.form.label.technology" path="item.technology" readonly="true"/>
			<acme:input-textarea code="inventor.quantity.form.label.description" path="item.description" readonly="true"/>
			<acme:input-money code="inventor.quantity.form.label.retailPrice" path="item.retailPrice" readonly="true"/>
			
			<jstl:if test="${command == 'show'}">
				<acme:input-money code="inventor.label.moneyExchange" path="moneyExchange" readonly="true"/>
			</jstl:if>

			<acme:input-url code="inventor.quantity.form.label.link" path="item.link" readonly="true"/>

			<acme:input-textbox code="inventor.quantity.form.label.status" path="item.status" readonly="true" />
			
			<acme:input-textbox code="inventor.quantity.form.label.type" path="item.type" readonly="true"/>
			
			<jstl:if test="${item.status == 'NON_PUBLISHED' && item.getInventor().getId() == inventorId}" >
				<acme:button code="inventor.quantity.form.button.updateItem" action="/inventor/item/update?id=${item.getId()}"/>
			</jstl:if>
			
		</jstl:when>	
		
		<jstl:when test="${command == 'create'}">
			<acme:input-integer code="inventor.quantity.form.label.number" path="number"/>
			<acme:input-select code="inventor.quantity.form.label.item" path="item">
				<jstl:forEach items="${items}" var = "i">
					<acme:input-option code="${i.getName()}" value="${i.getId()}" selected="${ i.getId() == item }"/>
				</jstl:forEach>
			</acme:input-select>
			<acme:submit code = "inventor.quantity.form.button.create" action = "/inventor/quantity/create?id=${param.id}"/>
			
		</jstl:when>
		
	</jstl:choose>
		
		
</acme:form>