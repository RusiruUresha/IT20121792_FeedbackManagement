<%@page import="com.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Feedback Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/feedbacks.js"></script>
</head>
<body>

<div class="container"><div class="row"><div class="col-6"> 
<h1>Feedback Management V10.1</h1>
<form id="formFeedback" name="formFeedback" method="post" action="Feedback.jsp">
 Feedback Type: 
 <input id="fbType" name="feedbackType" type="text" 
 class="form-control form-control-sm">
 <br> Feedback Description: 
 <input id="fbDesc" name="feesdbackDescription" type="text" 
 class="form-control form-control-sm">
 <br> Feedback Rate: 
 <input id="fbRate" name="feedbackRate" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidfeedbackIDSave" 
 name="hidfeedbackIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divFeddbacksGrid">
 <%
 Feedback feedbackObj = new Feedback(); 
 out.print(feedbackObj.readFeedbacks()); 
 %></div>
</div> </div> </div> 



</body>
</html>