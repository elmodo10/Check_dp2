<%@page language="java"%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="urn:jsptagdir:/WEB-INF/tags"%>

<acme:form>


	<jstl:choose>
		<jstl:when test="${command == 'show'}">
			<acme:input-textbox code="inventor.item.form.label.name" path="name" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.code" path="code" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.technology" path="technology" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.description" path="description" readonly="true"/>
			<acme:input-money code="inventor.item.form.label.price" path="retailPrice" readonly="true"/>

		    <acme:input-money code="inventor.label.moneyExchange" path="moneyExchange" readonly="true" />

			<acme:input-textbox code="inventor.item.form.label.link" path="link" readonly="true"/>
			<acme:input-textbox code="inventor.item.form.label.status" path="status" readonly= "true"/>
			<acme:input-textbox code="inventor.item.form.label.type" path="type" readonly= "true"/>
			
		
			<jstl:if test="${status == 'NON_PUBLISHED' }">
				<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete?id=${id}"/>
				<acme:button code="inventor.item.form.button.update" action="/inventor/item/update?id=${id}"/>	
				<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>	
				
				<acme:button code="inventor.item.form.buttom.chimpum-list" action="/inventor/chimpum/list-by-item?id=${id}"/>
				<acme:button code="inventor.item.form.button.chimpum-create" action="/inventor/chimpum/create?id=${id}"/>
			</jstl:if>
			<!--
				<jstl:if test="${status == 'NON_PUBLISHED'  && type ==Tool}">
					<acme:submit code="inventor.item.form.button.delete" action="/inventor/item/delete?id=${id}"/>
					<acme:button code="inventor.item.form.button.update" action="/inventor/item/update?id=${id}"/>	
					<acme:submit code="inventor.item.form.button.publish" action="/inventor/item/publish"/>	
				</jstl:if>
			-->
		</jstl:when>
	
		<jstl:when test="${command == 'create'}">
			<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
			<acme:input-textbox code="inventor.item.form.label.code" path="code"/>
			<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
			<acme:input-textbox code="inventor.item.form.label.description" path="description"/>
			<acme:input-money code="inventor.item.form.label.price" path="retailPrice"/>
			<acme:input-textbox code="inventor.item.form.label.link" path="link"/>
			<acme:input-select code="inventor.item.form.label.type" path="type">
				<acme:input-option code="inventor.item.form.label.component" value="COMPONENT" selected="${ type == 'COMPONENT' }"/>
				<acme:input-option code="inventor.item.form.label.tool" value="TOOL" selected="${ type == 'TOOL' }"/>
			</acme:input-select>
			<acme:submit code="inventor.item.form.button.create" action="/inventor/item/create"/>
		</jstl:when>
		
		
		<jstl:when test="${command == 'update'}">
			<acme:input-textbox code="inventor.item.form.label.name" path="name"/>
			<acme:input-textbox code="inventor.item.form.label.code" path="code" />
			<acme:input-textbox code="inventor.item.form.label.technology" path="technology"/>
			<acme:input-textbox code="inventor.item.form.label.description" path="description"/>
			<acme:input-money code="inventor.item.form.label.price" path="retailPrice"/>
			<acme:input-textbox code="inventor.item.form.label.link" path="link"/>
	
			<acme:input-select code="inventor.item.form.label.type" path="type">
				<acme:input-option code="inventor.item.form.label.component" value="COMPONENT" selected="${ type == 'COMPONENT' }"/>
				<acme:input-option code="inventor.item.form.label.tool" value="TOOL" selected="${ type == 'TOOL' }"/>
			</acme:input-select>
			
			<acme:submit code="inventor.item.form.button.update" action="/inventor/item/update"/>
		</jstl:when>
		
	</jstl:choose>
			
			
</acme:form>