<!DOCTYPE html>
<html>
<script src="https://cdn.plot.ly/plotly-latest.min.js"></script>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="writeReport2.css?version=2">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
  
<body>
<div class="top-row">
<form method="post" action="Admin">
<input type="hidden" name="method" value="home">
<button class="btn"><i class="fa fa-home"></i></button>
</form>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="reports">
<button class="btn"><i class="fa fa-commenting"></i></button>
</form>
</div>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="stats">
<button class="btn"><i class="fa fa-pie-chart"></i></button>
</form>
</div>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="inventory">
<button class="btn"><i class="fa fa-tag"></i></button>
</form>
</div>
<div class="float-right">
<form method="post" action="Admin">
<input type="hidden" name="method" value="assigned">
<button class="btn"><i class="fa fa-address-card"></i></button>
</form>
</div>
</div>
<div class="student-profile py-4">
  <div class="container">
    <div class="row">
      <div class="col-lg-8">
        <div class="card shadow-sm">
        <div class="card shadow-sm">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
        </div>
        <div id="myDiv"></div>
        <div id="myPlot2"></div>
        <div class="pagination">
        <div>
        <h3>   </h3>
        </div>
              <p>
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="citizenship">
              <button class="buttons">Citizenship</button>
              </form>
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="language">
              <button class="buttons">Language</button>
              </form>
              <div class="change_button">
              <button class="button">Age/Gender</button>
              </div>
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="family_comp">
              <button class="buttons">Family Composition</button>
              </form>
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="Education">
              <button class="buttons">Education</button>
              </form>
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="challenges">
              <button class="buttons">Employment Challenges</button>
              </form>
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="employment">
              <button class="buttons">Employment</button>
              </form>
              </p>
              </div>
              </div>
              </div>
          </div>
      </div>
    </div>


<script>
const xArray = ["Under 5", "5 to 18", "18 to 50", "50 to 65", "Over 65"];
const yArray= [<%=request.getAttribute("age<5")%>, <%=request.getAttribute("age>4 AND age<18")%>, <%=request.getAttribute("age>17 AND age<50")%>, <%=request.getAttribute("age>50 AND age<65")%>, <%=request.getAttribute("age>64")%>];

var data = [{
	  type: "pie",
	  values: [<%=request.getAttribute("age < 18 AND gender = 'Female'")%>, <%=request.getAttribute("age<18 AND gender = 'Male'")%>, <%=request.getAttribute("age>17 AND age<35 AND gender = 'Female'")%>, <%=request.getAttribute("age>17 AND age<35 AND gender = 'Male'")%>, <%=request.getAttribute("age>35 AND age<55 AND gender ='Female'")%>, <%=request.getAttribute("age>35 AND age<55 AND gender = 'Male'")%>, <%=request.getAttribute("age>=55 AND gender='Female'")%>, <%=request.getAttribute("age>=55 AND gender='Male'")%>],
	  labels: ["Minor Females (Under 18)", "Minor Males (Under 18)", "Young Adult Females (18 to 35)", "Young Adult Males (18 to 35)", "Adult Females (35 to 55)", "Adult Males (35 to 55)", "Older Adult Females (55+)", "Older Adult Males(55+)"],
	  textinfo: "label+percent",
	  insidetextorientation: "radial",
	  marker: {colors: ["rbga(255,185,221,0.8)", "rbga(185,218,255,0.8)", "rbga(255,114,187,0.8)","rbga(125,213,255,0.8)","rbga(254,58,159,0.8)","rbga(64,193,255,0.8)","rbga(255,0,132,0.8)","rbga(0,173,255,0.8)"]},
	}];

	var layout = [{
		title: 'Age and Gender Breakdown',
	  height: 700,
	  width: 700
	}];
	
	
	var bar = {
			x:xArray,
			y:yArray,
			type:"bar",
			orientation:"v",
			marker: {color:"rgba(0,0,255,0.6)"}
	};
	
	var data1 = [bar];
	var layout1 = {
			title: 'Age Breakdown'
	};
	


Plotly.newPlot('myDiv', data, layout);
Plotly.newPlot("myPlot2", data1, layout1);

</script>

</body>
</html>