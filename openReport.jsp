<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="writeReport.css?version=92">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <!-- Google Fonts -->
<link href="https://fonts.googleapis.com/css?family=Lato:300,400,700,900&display=swap" rel="stylesheet">
<!-- Bootstrap CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.3/css/bootstrap.min.css'>
<!-- Font Awesome CSS -->
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.12.1/css/all.min.css'>
  <title>Admin Portal</title>
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
  <div class="student-profile py-4">
  <div class="container">
    <div class="row">
      <div class="col-lg-8">
        <div class="card shadow-sm">
        <div class="card shadow-sm">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
        </div>
        <form method="post" action="Admin" enctype="multipart/form-data">
          <table class="table table-bordered">
          <tr>
          <th>Agent:</th>
          <td><input type="text" value=<%=request.getAttribute("agent") %> name="agent_name" required></td>
          </tr><tr>
          <th>Date:</th>
          <td><input type="date" value=<%=request.getAttribute("date_of_incident") %> name="date_of_incident" required></td>
            <th>Time:</th>
            <td><input type="time" name="time_of_incident"></td>
            </tr><tr>
            <th>Room:</th>
            <td><input type="text" name="room_num" value=<%=request.getAttribute("room_num") %> required></td>
            <th>Name:</th>
            <td><input type="text" name="individuals" value=<%=request.getAttribute("names") %> required></td>
            <tr>
            <th>Report Type:</th>
            <td><select name="report_type">
            <option value="Incident" ${report_type == 'Incident' ? 'selected' : '' }>Incident</option>
            <option value="Follow-up" ${report_type == 'Follow-up' ? 'selected' : '' }>Follow-up</option>
            <option value="Wellness Check" ${report_type == 'Wellness Check' ? 'selected' : '' }>Wellness Check</option>
            <option value="Object_Seized" ${report_type == 'Object_Seized' ? 'selected' : '' }>Object Seized</option>
            <option value="IRCC_Meeting" ${report_type == 'IRCC_Meeting' ? 'selected' : ''}>IRCC Meeting</option>
            <option value="TC_Warning" ${report_type == 'TC_Warning' ? 'selected' : '' }>TC Warning</option>
            <option value="Security_Issue" ${report_type == 'Security_Issue' ? 'selected' : '' }>Security Issue</option>
            <option value="Transportation" ${report_type == 'Transportation' ? 'selected' : '' }>Transportation</option>
            <option value="Other" ${report_type == 'Other' ? 'selected' : '' }>Other</option>
            </select></td>
            </tr>
            </table>
            Report:
          <textarea name="report" rows="8" cols="133"><%=request.getAttribute("report") %></textarea>
          Response:
          <textarea name="response" rows="8" cols="133"><%=request.getAttribute("response") %></textarea>
          <input type="hidden" name="method" value="submitResponse">
          <input type="hidden" name="identifier" value=<%=request.getAttribute("identifier") %>>
          <button class="button" type="submit">Submit Response</button>
          </form>
          </div>
        </div>
        </div>
      </div>
    </div>
</body>
</html>