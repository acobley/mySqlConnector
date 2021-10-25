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
        <title>Comments from users</title>
    </head>
    <body>
        <h1>Comments from users</h1>
        <p><a href="/MySQLConnector/comments">List Comments</a></p>
        <%

List<commentStore> lcomment = (List<commentStore>)request.getAttribute("comments");
List<commentStore> logs = (List<commentStore>)request.getAttribute("logs");
if (lcomment==null){
 %>
	<p>No comments found</p>
	<% 
            Iterator<commentStore> iterator;
            if (logs==null){
            %>
	<p>No logs found !</p>
	<% }else{
            iterator = logs.iterator();
            while (iterator.hasNext()){
	commentStore md = (commentStore)iterator.next();
	
	%>
	<%=md.getComment() %><br/><%
            }
}
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
