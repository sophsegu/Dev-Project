<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident2.css?version=47">
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
  <div class="container">
    <div class="row">
      <div class="col-lg-4">
        <div class="card shadow-sm">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
          <div class="card-header bg-transparent text-center">
            <img class="profile_img" src="<%=request.getAttribute("image") %>" alt="profile pic">
            <h3><%=request.getAttribute("nom")%></h3>
            <h3><%=request.getAttribute("surname") %></h3>
          </div>
          <div class="card-body">
            <p class="mb-0"><strong class="pr-1">Status:</strong><%=request.getAttribute("status") %></p>
            <p class="mb-0"><strong class="pr-1">UCI:</strong><%=request.getAttribute("uci") %></p>
            <p class="mb-0"><strong class="pr-1">Bracelet:</strong><%=request.getAttribute("bracelet") %></p>
            <p class="mb-0"><strong class="pr-1">Room Number:</strong><%=request.getAttribute("room_num") %></p>
            <p class="mb-0"><strong class="pr-1">Name:</strong><%=request.getAttribute("nom") %></p>
            <p class="mb-0"><strong class="pr-1">Surname:</strong><%=request.getAttribute("surname") %></p>
            <p class="mb-0"><strong class="pr-1">Date of Birth:</strong><%=request.getAttribute("dob") %></p>
            <p class="mb-0"><strong class="pr-1">Age:</strong><%=request.getAttribute("age") %></p>
            <p class="mb-0"><strong class="pr-1">Gender:</strong><%=request.getAttribute("gender") %></p>
            <p class="mb-0"><strong class="pr-1">Primary Language:</strong><%=request.getAttribute("primary_language") %></p>
            <p class="mb-0"><strong class="pr-1">Other Languages:</strong><%=request.getAttribute("other_language") %></p>
            <p class="mb-0"><strong class="pr-1">Citizenship:</strong><%=request.getAttribute("citizenship") %></p>
            <p class="mb-0"><strong class="pr-1">Phone Number:</strong><%=request.getAttribute("phone") %></p>
            <p class="mb-0"><strong class="pr-1">Email:</strong><%=request.getAttribute("email") %></p>
            <p class="mb-0"><strong class="pr-1">Family Composition:</strong><%=request.getAttribute("family_composition") %></p>
            <p class="mb-0"><form method="post" action="Agent"><input type="hidden" name = "method" value = "openFamily"> <input type="hidden" name="family_id" value = <%=request.getAttribute("family_id") %>><button>Open Family</button></form></p>
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Reports</h3>
          </div>
          <div class="card-body pt-0">
            <table class="table table-bordered">
              <%=request.getAttribute("rapports") %>
              </table>
          </div>
        </div>
          <div style="height: 26px"></div>
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Pagination</h3>
          </div>
          <div class="pagination">
              <p>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionA">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">General</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionB">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Claim Process</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionC">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Integration/Workshop</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionD">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Employment/Housing</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionE">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">DEV</button>
              </form>
              <div class="change_button">
              <button class="button">Reports</button>
              </div>
              </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>