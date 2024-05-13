<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="smallQuestionnaire.css?version=8">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <title>Update Agent</title>
</head>
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
  <div class="agent-page">
    <div class="form">
      <div class="agent">
      <img src="img/globalys_logo.png" style="width:200px;">
      <form method="post" action="Admin">
  <input type="hidden" name="method" value="createAgent">
  
  <h2>Create agent</h2>
  Account Role:<br>
  <select name = "account_role">  
  	<option value="Agent">Agent</option>
  	<option value="Administrator">Administrator</option>
  	<option value="Client">Client</option>
  	</select>
  <br><br>
  Username:<br>
  <input type="text" name="username" placeholder = "Username">  
  <br><br>
   Email:<br>
  <input type="text" name="email" placeholder = "Email">  
  <br><br>
  Name:<br>
  <input type="text" name="nom" placeholder = "Name">  
  <br><br>
  Surname:<br>
  <input type="text" name="surnom" placeholder = "Surname">  
  <br><br>
  Password:<br>
  <input type="text" name="mot_de_passe" placeholder = "Password">  
  <br><br>
  <button>Create Agent</button>
</form>
      </div>
      <br>
	<br>
    </div>
  </div>

</body>
</html>