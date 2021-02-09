<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>URL Shortening API</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
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

/* Style the content */

.content input {
  margin-top: 20px;
  width: 50%;
  padding: 10px;
  border-radius: 5px;
}
.content  input[type=submit] {
    width: auto;  
    height: auto;
    color: #005c99;
    font-weight: bold;
    background-color: #bfbfbf;
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

</style>

</head>
<body>
<form action="short" method="get">
<div class="company">
 <h2>Neueda </h2>
</div>
<div class="heading"><h3><center>URL Shortening</center></h3></div>
<div class="content">
   <center><input type="text" placeholder="Enter URL" name="longurl"></center>
</div>

<div class="content">
   <center><input type="submit" value="Get Short URL" style=""></center>
</div>
</form>
	
</body>
</html>