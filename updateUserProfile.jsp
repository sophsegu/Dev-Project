<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="smallQuestionnaire.css?version=9">
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
</div>
  <div class="agent-page">
    <div class="form">
      <div class="agent">
      <img src="img/globalys_logo.png" style="width:200px;">
      <form method="post" action="Admin">
  <input type="hidden" name="method" value="updateAgent">
  <input type="hidden" name="username" value= <%=request.getAttribute("username") %>>
  
  <h2>Update agent</h2>
  Account Role:<br>
  <select name = "account_role">  
  	<option value=<%=request.getAttribute("account_role")%>><%=request.getAttribute("account_role")%></option>
  	<option value=<%=request.getAttribute("account_role2")%>><%=request.getAttribute("account_role2")%></option>
  	<option value=<%=request.getAttribute("account_role3")%>><%=request.getAttribute("account_role3")%></option>
  	</select>
  <br><br>
  Username:<br>
  <input type="text" name="username" value = <%=request.getAttribute("username")%>>  
  <br><br>
  Email:<br>
  <input type="text" name="email" value = <%=request.getAttribute("email")%>>  
  <br><br>
  Name:<br>
  <input type="text" name="nom" value = <%=request.getAttribute("nom")%>>  
  <br><br>
  Surname:<br>
  <input type="text" name="surnom" value = <%=request.getAttribute("surnom")%>>  
  <br><br>
  Password:<br>
  <input type="text" name="mot_de_passe" value = <%=request.getAttribute("mot_de_passe")%>>  
  <br><br>
  <button>Update Agent</button>
</form>
<form method="post" action="Admin" value="delete">
<input type="hidden" name="method" value="delete">
<input type="hidden" name="username" value= <%=request.getAttribute("username") %>>
  <button>Delete Agent</button>
  </form>
      </div>
      <br>
	<br>
    </div>
  </div>

</body>
</html>