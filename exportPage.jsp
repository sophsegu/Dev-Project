<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident2.css?version=48">
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
<input type="hidden" name="method" value="user">
<button class="btn"><i class="fa fa-user"></i></button>
</form>
</div>
</div>
  <div class="student-profile py-4">
  <div class="container">
    <div class="row">
      
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Meeting</h3>
            <form method="post" action="Admin">
            <table class="table table-bordered" style="width:100%">
		<tr>
		<td>Meetings between </td>
		<td><input type="date" name="first_date"></td>
		<td> and </td>
		<td><input type="date" name="second_date"></td>
		</tr>
		<tr>
		<td>Agent: </td>
		<td><input type="text" name="agent_name"></td>
		</tr>
		<tr>
		<td>Assigned Agent: </td>
		<td><input type="text" name="assigned_agent"></td>
		</tr>
		<tr>
		<td>Resident UCI: </td>
		<td><input type="text" name="uci"></td>
		</tr>
		<tr>
		<td>Positive IRB Decision</td>
		<td><input type="checkbox" value="yes_irb" name="irb_decision"></td>
		</tr>
		<tr>
		<td>Employment Status</td>
		<td><select name="employment_status">
		<option value="N/A">N/A</option>
		<option value="Employed">Employed</option>
		<option value="Unemployed">Unemployed</option>
		</select></td>
		</tr>
		<tr>
		<td>Looking for Housing</td>
		<td><select name="looking_housing">
		<option value="N/A">N/A</option>
		<option value="Yes">Yes</option>
		<option value="No">No</option>
		</select></td>
		</tr>
		<tr>
		<td>Move Out Date</td>
		<td><input type="date" name="moving_date"></td>
		</tr>
	</table>
	<input type="hidden" name="method" value="exporting">
	<button type="submit">Export</button>
	</form>
          </div>
          <div class="card-body pt-0">
          </div>
        </div>
          <div style="height: 26px"></div>
      </div>
    </div>
  </div>
</div>
</body>
</html>