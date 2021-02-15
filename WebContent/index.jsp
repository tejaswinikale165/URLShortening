<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>URL Shortening API</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<meta name="viewport" content="width=device-width, initial-scale=1">
<%
int userid =1;
String username="";
if(session.getAttribute("userid")!= null)
{
	userid=Integer.parseInt((String) session.getAttribute("userid"));
	username=(String) session.getAttribute("username");
}
 %>

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

/* Style the content */

.content input {
  margin-top: 20px;
  width: 40%;
  padding: 10px;
  border-radius: 5px;
}
.content  input[type=submit] {
    width: 120px;  
    height: 50px;
    color: white;
    font-weight: bold;
    background-color: #005c99;
    border-radius: 5px;
    border-top-style: hidden;
  border-right-style: hidden;
  border-left-style: hidden;
  border-bottom-style: hidden;
    
    
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


.div1 {
  margin-top: 20px;
   color: #005c99;
   font-weight: bold;
}

#urltable {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse; 
 
   margin-top: 20px;
}

#urltable td, #urltable th {
  border: 1px solid #ddd;
  padding: 8px;
}

#urltable tr:nth-child(even){background-color: #f2f2f2;}

#urltable tr:hover {background-color: #ddd;}

#urltable th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #bfbfbf;
  color: #005c99;;
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
.fa {font-size:20px;color: #white;}
.modal-content {

 float: right;
  background-color: #fefefe;
  margin: auto;
  padding: 20px;
  border: 1px solid #888;
  width: 15%;
}

/* The Close Button */
.close {
  color: #aaaaaa;
  float: right;
  font-size: 28px;
  font-weight: bold;
}

.close:hover,
.close:focus {
  color: #000;
  text-decoration: none;
  cursor: pointer;
}

</style>

</head>
<body>

<div class="company">
 <h2>Neueda </h2>
</div>
<div class="navbar">
<a class="active" href="/index.jsp">URL Shortening</a>
<a href="/analysis">Analysis</a>
<a href="#" style="width:10%" id="userlogo"><i class="fa fa-user"></i></a>
</div>
<div id="myModal" class="modal" style="display:none;">

  <div class="modal-content">
    <span class="close">&times;</span>
   <%if(username==""){%>
  <form action="/analysis" method="post">
  <input type="submit" value="Login" class="loginbtn"> </form>
  <%} else{%><h4 style="margin-top:0;"> <%out.print(username);%></h4>
   <form action="/logout" method="post">
  <input type="submit" value="Logout" class="loginbtn"></form>
  <% }%>
 
  </div>

</div>
<form action="short" method="post">
<div class="content">
   <center><input type="text" placeholder="Enter URL" name="longurl"></center>
</div>

<div class="content">
   <center><input type="submit" value="Get Short URL" style=""></center>
</div>
</form>

	
</body>
<script>

var modal = document.getElementById("myModal");
var btn = document.getElementById("userlogo");
var span = document.getElementsByClassName("close")[0];

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

</html>