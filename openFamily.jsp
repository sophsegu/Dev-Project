<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="writeReport3.css?version=23">
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
    <form method="post" action="Agent">
      <input type="hidden" name="method" value="home">
      <button class="btn"><i class="fa fa-home"></i></button>
    </form>
    <div class="float-right">
      <form method="post" action="Agent">
        <input type="hidden" name="method" value="openAssigned">
        <button class="btn"><i class="fa fa-user"></i></button>
      </form>
    </div>
    <div class="float-right">
      <form method="post" action="Agent">
        <input type="hidden" name="method" value="export">
        <button class="btn"><i class="fa fa-download"></i></button>
      </form>
    </div>
  </div>
  <div class="student-profile py-4">
        <!-- Your existing card HTML goes here -->
        <%=request.getAttribute("card") %>
    </div>
</body>
</html>
