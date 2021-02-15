<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Page</title>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<%int userid =1;
String username="";
if(session.getAttribute("userid")!= null)
{
	userid=Integer.parseInt((String) session.getAttribute("userid"));
	username=(String) session.getAttribute("username");
} %>
<style>
* {
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

.heading h3{
  
  display: block;
  color: #005c99;
  text-align: center;
  
  text-decoration: none;
}
/* Change color on hover */
.company {
overflow: hidden;
  background-color: white;
  color: #005c99;
  padding: 10px;
}



.navbar  {
  width: 100%;
  background-color: #555;
  overflow: auto;
}


.navbar a {
 float: left;
  padding: 12px;
  color: white;
  text-decoration: none;
  font-size: 17px;
  width: 45%; /* two links of equal widths */
  text-align: center;
}

.navbar a:hover {
  background-color: #111;
}

.navbar a.active {
  background-color: #005c99;
}

.div1 {
  margin-top: 20px;
   color: #005c99;
   font-weight: bold;
}


input[type=text], input[type=password] ,input[type=email]{
  width: 60%;
  padding: 12px 20px;
  margin: 8px 0;
  display: inline-block;
  border: 1px solid #ccc;
  box-sizing: border-box;
  
}

button {
  background-color: #005c99;
  color: white;
  padding: 14px 20px;
  margin: 8px 0;
  border: none;
  cursor: pointer;
  width: 60%;
}

button:hover {
  opacity: 0.8;
}

.signupbtn {
  width: auto;
  padding: 10px 18px;
  background-color: #005c99;
}

.imgcontainer {
  text-align: center;
 
}

img.avatar {
  width: 40%;
  border-radius: 50%;
}

.container {
  padding: 16px;
  margin: auto;
  margin-top: 20px;
  width: 50%;
 
  
}

span.psw {
  float: right;
  padding-top: 16px;
}

/* Change styles for span and cancel button on extra small screens */
@media screen and (max-width: 300px) {
  span.psw {
     display: block;
     float: none;
  }
  .cancelbtn {
     width: 100%;
  }
}





/* The Modal (background) */
.modal {
  display: none; /* Hidden by default */
  position: fixed; /* Stay in place */
  z-index: 1; /* Sit on top */
  left: 0;
  top: 0;
  width: 100%; /* Full width */
  height: 100%; /* Full height */
  overflow: auto; /* Enable scroll if needed */
  background-color: rgb(0,0,0); /* Fallback color */
  background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
  padding-top: 60px;
}

/* Modal Content/Box */
.modal-content {
  background-color: #fefefe;
  margin: 7% auto 5% auto; /* 5% from the top, 15% from the bottom and centered */
  border: 1px solid #888;
  width: 50%; /* Could be more or less, depending on screen size */
  height: 50%; 
}

/* The Close Button (x) */
.close {
  position: relative;
  left: 320px;
  
  top:  9px;
  color: #000;
  font-size: 35px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: red;
  cursor: pointer;
}



.modal-content-user {

 float: right;
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 15%;
}

/* The Close Button */
.close-user {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close-user:hover,
.close-user:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}
.loginbtn {
    width: 120px;  
    height: 40px;
    color: white;
    font-weight: bold;
    background-color: #005c99;
    border-radius: 5px;
    border-top-style: hidden;
  border-right-style: hidden;
  border-left-style: hidden;
  border-bottom-style: hidden;
    
    
}
.fa {font-size:50px;color: #005c99;}
</style>
</head>
<body>
	<div class="company">
		<h2>Neueda </h2>
	</div>
	<div class="navbar">
		<a class="active" href="/index.jsp">URL Shortening</a>
		<a href="/login.jsp">Analysis</a>
		<a href="#" style="width:10%" id="userlogo"><i id="userlogo" class="fa fa-user" style= "font-size:20px;color: white;"></i></a>
	</div>
	
	<div id="myModal" style="display:none;">

  <div class="modal-content-user">
    <span class="close-user">&times;</span>
   <%if(username==""){%>
  <form action="/analysis" method="post">
  <input type="submit" value="Login" class="loginbtn"> </form>
  <%} else{%><h4 style="margin-top:0;"> <%out.print(username);%></h4>
   <form action="/logout" method="post">
  <input type="submit" value="Logout" class="loginbtn"></form>
  <% }%>
 
  </div>

</div>
	
	<form action="/login" method="post">
 		

  		<div class="container">
    	<center><i class="fa fa-user"></i><br>
    		<input type="text" placeholder="Enter Username" name="username" required>
			<br>
   			
    		<input type="password" placeholder="Enter Password" name="password" required>
        
    		<button type="submit">Login</button></center>
  		</div>
	</form>	
  		<div class="container" style="background-color:#f1f1f1; margin-top:5px;width: 29%;">
    		
    		<button type="button" class="signupbtn" onclick="document.getElementById('id01').style.display='block'" style="width:auto;">Sign up</button>
    		<span class="psw">Forgot <a href="#">password?</a></span>
  		</div>
  		
  		
  		
  		
  <div id="id01" class="modal">
  
  <form class="modal-content animate" action="/signup" method="post">
     <div class="imgcontainer">
      <div onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</div>
      <i class="fa fa-user" style="margin-bottom:0px;"></i>
    </div>
    

    <div class="container" style="margin-top:0px;">
      <center>
      <input type="email" placeholder="Enter email" name="username" required  style="width: 100%;">

      <input type="password" placeholder="Enter Password" name="password" required  style="width: 100%;">
        
      <button type="submit"  style="width: 100%;">Sign Up</button></center>
    </div>
  </form>
</div>

<script>
// Get the modal
var modalsignup = document.getElementById('id01');

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modalsignup) {
    	modalsignup.style.display = "none";
    }
}

var modal = document.getElementById("myModal");
var btn = document.getElementById("userlogo");
var span = document.getElementsByClassName("close-user")[0];

btn.onclick = function() {
  modal.style.display = "block";
}
span.onclick = function() {
  modal.style.display = "none";
}
window.onclick = function(event) {
  if (event.target == modal) {
    modal.style.display = "none";
  }
}
</script>
  		
  		
  		

	
</body>
</html>