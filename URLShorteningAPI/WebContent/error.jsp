<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Page</title>
<style>
* {
  box-sizing: border-box;
  font-family: Arial, Helvetica, sans-serif;
}

body {
  margin: 0;
  font-family: Arial, Helvetica, sans-serif;
}

/* Style the top navigation bar */
.company {
  overflow: hidden;
  background-color: #333;
}

/* Style the topnav links */
.company h2 {
  float: left;
  display: block;
  
  text-align: center;
  padding: 14px 16px;
  text-decoration: none;
}
.heading h3{
  
  display: block;
  color: #005c99;
  text-align: center;
  
  text-decoration: none;
}
/* Change color on hover */
.company {
  background-color: #bfbfbf;
  color: #005c99;
}
</style>
</head>
<body>
<div class="company">
 <h2>Neueda </h2>
</div>
<div class="heading"><h3><center><%out.print(request.getAttribute("errormsg")); %></center></h3></div>

</body>
</html>