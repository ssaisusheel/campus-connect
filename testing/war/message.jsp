<%@ page import="com.google.appengine.api.blobstore.BlobstoreServiceFactory" %>
<%@ page import="com.google.appengine.api.blobstore.BlobstoreService" %>
<%! String kp;
	String idd;
	String cdd;
	String res;
%>
<%
idd=(String)request.getAttribute("id");
cdd=(String)request.getAttribute("cd");   
kp=(String)request.getSession().getAttribute("domain");



	
	BlobstoreService blobstoreService = BlobstoreServiceFactory.getBlobstoreService();
%>




<!DOCTYPE html>
<html>
<head>
<title>Campus-Connect</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />

<script src="js/jquery.min.js"></script>
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" media="all" />


</head>
<body> 
	  <!--header-->
	<div class="header">
		<div class="container">
			<div class="header-top">			
				<div class="logo">
					<a href="index.html"><img src="<%=request.getContextPath()%>/images/cbit.jpg" alt=" " ></a>
				</div>
				
				
			<div class="header-bottom-bottom">
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li ><a href="about.html">About Us</a></li>
						
						<li class="active"><a href="contact.html" > Contact </a></li>
					</ul>	
					
				
		</div>
	</div>
			<!-- 
			<form  >
				<div class="send ">
						
						<input type="hidden"  name="pk" value="<%= (String)request.getAttribute("") %>">
						
					</div>
				</form>
	 -->
	
		<!--content-->
		<div class="container">
			<div class="contact">
			<h3>ENTER THE MESSAGE HERE</h3>
			<form name="myform" action="<%= blobstoreService.createUploadUrl("/upload") %>" method="post" enctype="multipart/form-data">
					<%
						res=(String)request.getAttribute("myname");
						if(res==null)
						{
							res=" ";
						}
					%>
					<textarea cols="5" name="pattern" rows="7" name="pattern"  ><%= res  %></textarea>
						<div class="attach">
						<input type="file" value="Attach" name="myFile" >
						<input type="hidden" name="dm" value="<%=kp %>">
						<input type="hidden" name="id" value="<%=idd %>">
						<input type="hidden" name="cd" value="<%=cdd %>">
						<input type="submit" name="sss" value="UPLOAD" >
						
						<br>
						<input type="submit" name="sss" value="SEND">
						</div>
					
				</form>
				<!-- 				<form  action="/sender" method="post">
				<div class="send ">
						<input type="submit" value="send" name="s">
						<input type="hidden"  name="pk" value="<%= (String)request.getAttribute("myname") %>">
						
					</div>
				</form>
				 -->
				
				</div>
			</div>
				
			<!--footer-->
			<div class="footer">
				<div class="container">
					 <p class="footer-grid"><a href="http://cbit.ac.in/" target="_blank">CBIT</a> </p>
			 	</div> 	
			</div>

</body>
</html>