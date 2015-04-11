<!--A Design by W3layouts 
Author: W3layout
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
<head>
<title>Campus Connect</title>
<link href="<%=request.getContextPath()%>/css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="<%=request.getContextPath()%>/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css" media="all" />	
<!--//theme-style-->

</head>
<body> 
	  <!--header-->
	<div class="header">
		<div class="container">
			<div class="header-top">			
				<div class="logo">
					<a href="index.html"><img src="<%=request.getContextPath()%>/images/cbit.jpg" alt=" " ></a>
				</div>
				
				<div class="clearfix"> </div>
				</div>
			</div>	
			<div class="clearfix"> </div>
			</div>
			<div class="header-bottom-bottom">
				<div class="top-nav">
					<span class="menu"> </span>
					<ul>
						<li ><a href="about.html">About Us</a></li>
						
						
						<li><a href="contact.html" > Contact </a></li>
					</ul>	
				<script>
					$("span.menu").click(function(){
						$(".top-nav ul").slideToggle(500, function(){
						});
					});
				</script>			
				</div>	
				<div class="clearfix"> </div>
				</div>
		</div>
	</div>
	<!---->

		<!--content-->
		<div class="container">
			<div class="four">
				<h2>Your Message has been sent to all the relevant Students</h2>
				<h3>Congrats!</h3>
				
				<a href="<%=request.getContextPath()%>/message.jsp" class="btn  btn-1c">Go Back </a>
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