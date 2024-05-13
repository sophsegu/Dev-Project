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
        <div id="Plot" style="width:100%;max-width:1000px"></div>
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
              <form method="post" action = "Admin">
              <input type="hidden" name="method" value="stats">
              <input type="hidden" name="id" value="family_comp">
              <button class="buttons">Family Composition</button>
              </form>
              <div class="change_button">
              <button class="button">Education</button>
              </div>
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
const xArray = ["None", "Elementary", "High School", "College", "University", "Masters", "PhD"];
const yArray = [<%=request.getAttribute("None")%>, <%=request.getAttribute("Elementary")%>, <%=request.getAttribute("High School")%>, <%=request.getAttribute("College")%>, <%=request.getAttribute("University")%>, <%=request.getAttribute("Masters")%>, <%=request.getAttribute("PhD")%>];

const data = [{
  x:xArray,
  y:yArray,
  type:"bar",
  orientation:"v",
  marker: {color:"rgba(0,0,255,0.6)"}
}];

const layout = {title:"Education"};

Plotly.newPlot("myPlot", data, layout);
</script>

</body>
</html>