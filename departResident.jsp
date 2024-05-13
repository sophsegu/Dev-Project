<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident.css?version=36">
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
</div>
  <div class="student-profile py-4">
  <div class="container">
    <div class="row">
      <div class="col-lg-4">
        <div class="card shadow-sm">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
          <div class="card-header bg-transparent text-center">
            <img class="profile_img" src="img/blankprofilepic.png" alt="profile pic">
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
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
          <div>
            <h3 class="mb-0" style="display: inline-block;"><i class="far fa-clone pr-1" style="display: inline-block;"></i>Departure</h3>
            </div>
          </div>
          <form method="post" action="Agent">
          <div class="card-body pt-0">
            <table class="table table-bordered">
              <tr>
                <th width="20%">Date of Departure</th>
                <td width="2%">:</td>
                <td><input type="date" name="departure_date"></td>
                <th width="20%">Departure Type</th>
                <td width="2%">:</td>
                <td><select name="departure_type">
                <option value="Self Departure">Self Departure</option>
                <option value="IRCC Departure">IRCC Departure</option>
                <option value="Eviction">Eviction</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Exit Interview</th>
                <td width="2%">:</td>
                <td><select name="exit_interview">
                <option value="Yes">Yes</option>
                <option value="No">No</option>
                <option value="Work at Destination">Work at Destination</option>
                <option value="School at Destination">School at Destination</option>
                <option value="Housing at Destination">Housing at Destination</option>
                <option value="N/A">N/A</option>
                </select></td>
                <th width="20%">New Address</th>
                <td width="2%">:</td>
                <td><input type="text" name="address"></td>
              </tr>
            </table>
          </div>
          <input type="hidden" name="method" value="departResident">
            <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
            <button type="submit" class="button" style="float: right; display: flex;">Depart Resident</button>
            </form>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>