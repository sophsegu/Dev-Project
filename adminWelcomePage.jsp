<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="smallQuestionnaire.css">
<title> Login </title>
</head>
<body>
  <body>
    <div class="admin-page">
      <div class="form">
        <div class="admin">
          <div class="admin-header">
          <img src="img/globalys_logo.png" style="width:200px;"><h3>Welcome <%=request.getAttribute("fname")%></h3>
            <p>Please chose your user portal.</p>
          </div>
        </div>
        <form class="admin-form" method="post" action="Admin">
        <input type="hidden" name="method" value="administrator">
          <button>Administrator Portal</button>
        </form>
        <form class="admin-form" method="post" action="Agent">
        <input type="hidden" name="method" value="agent">
          <button>Agent Portal</button>
        </form>
      </div>
    </div>
</body>
</body>
</html>
