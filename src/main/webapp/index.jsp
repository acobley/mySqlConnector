<%-- 
    Document   : index.jsp
    Created on : 12-Oct-2016, 15:55:48
    Author     : andy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="aec.computing.dundee.ac.uk.stores.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comments</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%

List<commentStore> lcomment = (List<commentStore>)request.getAttribute("comments");
if (lcomment==null){
 %>
	<p>No faults found</p>
	<% 
}else{
%>


<% 
Iterator<commentStore> iterator;


iterator = lcomment.iterator();     
while (iterator.hasNext()){
	commentStore md = (commentStore)iterator.next();
	
	%>
	<%=md.getComment() %><br/><%
	
}
}
%>
    </body>
</html>
