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
        <div id="myPlot" style="width:100%;max-width:1000px"></div>
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
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="ageAndGender">
              <button class="buttons">Age/Gender</button>
              </form>
              <div class="change_button">
              <button class="button">Family Composition</button>
              </div>
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
const xArray = ["Solo", "Couple", "Family of 2", "Family of 3", "Family of 4", "Family of 5", "Family of 6", "Family of 7", "Family of 8", "Family of 9", "Family of 10", "Family of 11"];
const yArray = [<%=request.getAttribute("Solo")%>, <%=request.getAttribute("Couple")%>, <%=request.getAttribute("Family of 2")%>, <%=request.getAttribute("Family of 3")%>, <%=request.getAttribute("Family of 4")%>, <%=request.getAttribute("Family of 5")%>, <%=request.getAttribute("Family of 6")%>, <%=request.getAttribute("Family of 7")%>, <%=request.getAttribute("Family of 8")%>, <%=request.getAttribute("Family of 9")%>, <%=request.getAttribute("Family of 10")%>, <%=request.getAttribute("Family of 11")%>];

const data = [{  
	x:xArray,  
	y:yArray,  
	type:"bar",  
	orientation:"v", 
	marker: {color:"rgba(0,0,255,0.6)"}
	}];

const layout = {title:"Family Composition"};

Plotly.newPlot("myPlot", data, layout);
</script>

</body>
</html>