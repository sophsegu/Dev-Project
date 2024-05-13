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
        <div id="myPlot"></div>
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
              <div class="change_button">
              <button class="button">Language</button>
              </div>
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
const xArray = ["Arabic", "Bambara", "Bangla", "Bangoli", "Creole", "Dari", "English", "Farsi", "French", "Georgian", "Goram", "Igbo", "Kalenjin", "Kikuyu", "Kinyarwanda", "Kirundi", "Kurdish", "Lingala", "Luganda", "Mandinka", "Ndebele", "Pashto", "Persian", "Portuguese", "Punjabi", "Runyankore", "Shona", "Somali", "Spanish", "Swahili", "Taglog", "Tigrinya", "Turkish", "Urdu", "Yoruba", "Other"];
const yArray = [<%=request.getAttribute("ARABIC")%>, <%=request.getAttribute("BAMBARA")%>, <%=request.getAttribute("BANGLA")%>, <%=request.getAttribute("BANGOLI")%>, <%=request.getAttribute("CREOLE")%>, <%=request.getAttribute("DARI")%>, <%=request.getAttribute("ENGLISH")%>, <%=request.getAttribute("FARSI")%>, <%=request.getAttribute("FRENCH")%>, <%=request.getAttribute("GEORGIAN")%>, <%=request.getAttribute("GORAM")%>, <%=request.getAttribute("IGBO")%>, <%=request.getAttribute("KALENJIN")%>, <%=request.getAttribute("KIKUYU")%>, <%=request.getAttribute("KINYARWANDA")%>, <%=request.getAttribute("KIRUNDI")%>, <%=request.getAttribute("KURDISH")%>, <%=request.getAttribute("LINGALA")%>, <%=request.getAttribute("LUGANDA")%>, <%=request.getAttribute("MANDINKA")%>, <%=request.getAttribute("NDEBELE")%>, <%=request.getAttribute("PASHTO")%>, <%=request.getAttribute("PERSIAN")%>, <%=request.getAttribute("PORTUGUESE")%>, <%=request.getAttribute("PUNJABI")%>, <%=request.getAttribute("RUNYANKORE")%>, <%=request.getAttribute("SHONA")%>, <%=request.getAttribute("SOMALI")%>, <%=request.getAttribute("SPANISH")%>, <%=request.getAttribute("SWAHILI")%>, <%=request.getAttribute("TAGLOG")%>, <%=request.getAttribute("TIGRINYA")%>, <%=request.getAttribute("TURKISH")%>, <%=request.getAttribute("TWI")%>, <%=request.getAttribute("URDU")%>, <%=request.getAttribute("YORUBA")%>, <%=request.getAttribute("OTHER")%>];

const xArray2 = ["None", "English", "French", "Other"];
const yArray2 = [<%=request.getAttribute("iLike '%N/A%'")%>, <%=request.getAttribute("iLike '%English%'")%>, <%=request.getAttribute("iLike '%French%'")%>, <%=request.getAttribute("NOT iLike '%N/A%' AND other_language NOT iLIKE '%English%' AND other_language NOT iLIKE '%French%'")%>];


var bar1 = {
  x:xArray,
  y:yArray,
  domain: {
	  row: 0,
	  column: 0
  },
  type:"bar",
  orientation:"v",
  marker: {color:"rgba(0,0,255,0.6)"}
  };

var bar2 = {
	x:xArray2,
	y:yArray2,
	domain:{
		row: 1,
		column: 0
	},
	type:"bar",
	orientation:"v",
	marker: {color:"rgba(0,0,255,0.6)"}
};

var data1 = [bar1];
var data2 = [bar2];

var layout1 = {
		title: 'Primary Languages'
};

var layout2 = {
		title: 'Secondary Languages'
};

Plotly.newPlot("myPlot", data1, layout1);
Plotly.newPlot("myPlot2", data2, layout2);
</script>

</body>
</html>