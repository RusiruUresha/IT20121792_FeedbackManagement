<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.*, java.util.*, java.io.*" %> 
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<h1>Feedback management</h1>
<form  method="post" action="Feedback.jsp"  >
Feedback ID:<input type="text" name="UpdateID" size="100" value="<%=request.getParameter("FeedbackID")%>"><br>
Feedback Type:<input type="text" name="UpdateType" size="100" value="<%=request.getParameter("FeedbackType")%>"></br>
Feedback Description:<input type="text" name ="UpdateDescription" size="100" value="<%=request.getParameter("FeedbackDescription")%>"></br>
Feedback Rate:    <input type="text" name ="UpdateRate" size="100" value="<%=request.getParameter("FeedbackRate")%>"></br>
<input type="submit" value="Update" name="submitBtn">
</form>



<% out.print(session.getAttribute("statusMsg")); %><br>
Output table<br>
<% //Reading  
Feedback it=new Feedback();
out.print(it.readFeedbacks());
%>





</body>
</html>