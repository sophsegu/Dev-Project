<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident5.css?version=8">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
  <title>Agent Portal</title>
</head>
<body>
<div class="top-row">
<form method="post" action="Client">
<input type="hidden" name="method" value="home">
<button class="btn"><i class="fa fa-home"></i></button>
</form>
</div>
  <div class="student-profile py-4" style="display: inline-block;">
  <div class="container">
  <div class="card">
    <div class="row">
      <div class="col-lg-4 fiche-container" style="display: inline-block;">
        <div class="card shadow-sm">
          <%=request.getAttribute("card") %>
      </div>
      </div>
      </div>
    </div>
  </div>
</div>
<div class="student-profile py-4" style="display: inline-block;">
  <div class="container">
  <div class="card">
    <div class="row">
      <div class="col-lg-4 fiche-container" style="display: inline-block;">
        <div class="card shadow-sm">
          <%=request.getAttribute("card3") %>
      </div>
      </div>
      </div>
    </div>
  </div>
</div>
<div class="student-profile py-4" style="display: inline-block;">
  <div class="container">
  <div class="card">
    <div class="row">
      <div class="col-lg-4 fiche-container" style="display: inline-block;">
        <div class="card shadow-sm">
          <%=request.getAttribute("card2") %>
      </div>
      </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>