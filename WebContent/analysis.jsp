<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.neueda.bean.Analysis" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Analysis</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

   		
<%
int userid =1;
String username="";
if(session.getAttribute("userid")!= null)
{
	userid=Integer.parseInt((String) session.getAttribute("userid"));
	username=(String) session.getAttribute("username");
}
int nourlcreated=(int)request.getAttribute("nourlcreated");

int noclicks =(int)request.getAttribute("noclicks");


int avgclicksperhr =(int)request.getAttribute("avgclicksperhr");

String lineClicksByDate =(String)request.getAttribute("lineclicksbydate");

String barClicksByUrl=(String)request.getAttribute("barclicksbyurl");
String linebydateurl=(String)request.getAttribute("linebydateurl");

String doughnutchart=(String)request.getAttribute("doughnutchart");

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
  width: 45%; 
  text-align: center;
}

.navbar a:hover {
  background-color: #111;
}

.navbar a.active {
  background-color: #005c99;
}


.analysis  input[type=submit] {
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
    
    /* Float four columns side by side */
.column {
  float: left;
  width: 33.33%;
  padding: 3px ;
}

.row {margin: 0 -5px;margin-top: 10px;}

/* Clear floats after the columns */
.row:after {
  content: "";
  display: table;
  clear: both;
  
}

@media screen and (max-width: 600px) {
  .column {
    width: 100%;
   
    display: block;
    margin-bottom: 10px;
  }
}


.card {
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
  padding: 16px;
  text-align: center;
  background-color: #005c99;
  color: white;
}


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
.fa {font-size:50px;}
    
</style> 
</head>
<body>

<div class="company">
 <h2>Neueda </h2>
</div>
<div class="navbar">

 <a href="/">URL Shortening</a>
<a class="active"  href="/analysis">Analysis</a>
<a href="#" style="width:10%" id="userlogo"><i id="userlogo" class="fa fa-user" style="font-size:20px;color: #white;"></i></a>
</div>
<div class="row">
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
</div>

<div class="row">
  <div class="column">
    <div class="card">
      <p><i class="fa fa-link"></i></p>
      <h4>URLs Created</h4>
      <h2><%=nourlcreated %></h2>
    </div>
  </div>

  <div class="column">
    <div class="card">
      <p><i class="fa fa-hand-pointer-o"></i></p>
      <h4>Total Clicks</h4>
     <h2><%=noclicks %></h2>
    </div>
  </div>
  
  <div class="column">
    <div class="card">
      <p><i class="fa fa-bar-chart"></i></p>
      <h4>Average Clicks Per Hour</h4>
      <h2><%=avgclicksperhr %></h2>
    </div>
  </div>
  

</div>



</body>


<script type="text/javascript">
window.onload = function() { 
	
	var chart = new CanvasJS.Chart("multiSeriesChart", {
		theme: "light2",
		showInLegend: true,
		animationEnabled: true,
		exportEnabled: true,
		 legend: {
		     horizontalAlign: "right", // left, center ,right 
		     verticalAlign: "center",  // top, center, bottom
		   },
		title: {
			text: "Total Clicks By Date and URLs",
			 fontWeight: "normal"
		},
		data: <%=linebydateurl%>
	});
	chart.render();
	 
	var chart = new CanvasJS.Chart("barChartContainer", {
		animationEnabled: true,
		exportEnabled: true,
		title: {
			text: "Number of clicks per URL",
			 fontWeight: "normal"
		},
		axisY:{
			includeZero: true
		},
		axisX:{
			interval: 1
		},
		data: [{
			type: "bar", //change type to bar, line, area, pie, etc
			indexLabel: "{y}", //Shows y value on all Data Points
			indexLabelFontColor: "#005c99",
			color: "#005c99",
			indexLabelPlacement: "outside",
			dataPoints:<%= barClicksByUrl%>
		       }]
	});
	chart.render();
	

	 
	var chart = new CanvasJS.Chart("doughnutChart", {
		showInLegend: true,
		animationEnabled: true,
		exportEnabled: true,
		 legend: {
			 cursor: "pointer",
		     horizontalAlign: "right", // left, center ,right 
		     verticalAlign: "center",  // top, center, bottom
		   },

		title: {
			text: "Total Clicks In Last 5 Years",
			 fontWeight: "normal"
		},
		data: [{
			type: "doughnut",
				showInLegend: true,
			indexLabel: "#percent%",
			toolTipContent: "<b>Year : {name} - </b> Clicks : {y} (#percent%)",
			dataPoints : <%=doughnutchart%>
		}]
	});
	chart.render();	
}

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
<center><div id="multiSeriesChart" style="margin-top:20px;height: 350px; width: 70%;"></div></center>
<div class="row"> 
<div class="column" id="barChartContainer" style="margin-top:20px;margin-left:20px;height: 350px; width: 55%;"></div>

<div class="column" id="doughnutChart" style="margin-top:20px;margin-right:5px;height: 350px; width: 40%;"></div>
</div>

<script src="https://canvasjs.com/assets/script/canvasjs.min.js"></script>
</html>