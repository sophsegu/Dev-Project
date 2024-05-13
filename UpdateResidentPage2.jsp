<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident2.css?version=32">
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
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Claim Process</h3>
          </div>
          <form method="post" action="Agent">
          <div class="card-body pt-0">
          <div class="Body">
            <table class="table table-bordered" style="width:100%">
              <tr>
                <th width="20%">Biometrics</th>
                <td width="2%">:</td>
                <td><select name="biometrics" multiple>
                <option value="Initial" ${biometrics == 'Initial' ? 'selected' : '' }>Initial</option>
                <option value="Yes" ${biometrics == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="No" ${biometrics == 'No' ? 'selected' : '' }>No</option>
                <option value="N/A" ${biometrics == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">IME</th>
                <td width="2%">:</td>
                <td><select name="ime">
                <option value="Assessed(Received)" ${ime == 'Assessed(Received)' ? 'selected' : '' }>Assessed(Received)</option>
                <option value="Not Started" ${ime == 'Not Started' ? 'selected' : '' }>Not Started</option>
                <option value="In Progress(Applied)" ${ime == 'In Progress(Applied)' ? 'selected' : '' }>In Progress(Applied)</option>
                <option value="N/A" ${ime == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">AoC</th>
                <td width="2%">:</td>
                <td><select name="aoc">
                <option value="Yes" ${aoc == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="No" ${aoc == 'No' ? 'selected' : '' }>No</option>
                <option value="N/A" ${aoc == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">RPCD</th>
                <td width="2%">:</td>
                <td><select name="rpcd">
                <option value="Yes" ${rpcd == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="No" ${rpcd == 'No' ? 'selected' : '' }>No</option>
                <option value="N/A" ${rpcd == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">eApp</th>
                <td width="2%">:</td>
                <td><select name="eapp">
                <option value="Yes" ${eapp == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="No" ${eapp == 'No' ? 'selected' : ''}>No</option>
                <option value="N/A" ${eapp == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Basis of Claim</th>
                <td width="2%">:</td>
                <td><select name="boc">
                <option value="Submitted" ${boc == 'Submitted' ? 'selected' : '' }>Submitted</option>
                <option value="Waiting for Lawyer" ${boc == 'Waiting for Lawyer' ? 'selected' : '' }>Waiting for Lawyer</option>
                <option value="Not Submitted" ${boc == 'Not Submitted' ? 'selected' : '' }>Not Submitted</option>
                <option value="Completed" ${boc == 'Completed' ? 'selected' : '' }>Completed</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Work Permit</th>
                <td width="2%">:</td>
                <td><select name="work_permit">
                <option value="Yes" ${work_permit == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="No" ${work_permit == 'No' ? 'selected' : '' }>No</option>
                <option value="N/A" ${work_permit == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Work Permit Date</th>
                <td width="2%">:</td>
                <td>
                <input type="date" name="wp_date" value=<%=request.getAttribute("wp_date") %>></td>
              </tr>
              <tr>
                <th width="20%">IRB Decision</th>
                <td width="2%">:</td>
                <td><div class="school"><select name="irb_decision">
                <option value="Protected Person - Convention Refugee" ${irb_decision == 'Protected Person - Convention Refugee' ? 'selected' : '' }>Protected Person - Convention Refugee</option>
                <option value="Deemed Not Protected Person" ${irb_decision == 'Deemed Not Protected Person' ? 'selected' : '' }>Deemed Not Protected Person</option>
                <option value="Permanent Resident" ${irb_decision == 'Permanent Resident' ? 'selected' : '' }>Permanent Resident</option>
                <option value="Abandoned Claim" ${irb_decision == 'Abandoned Claim' ? 'selected' : '' }>Abandoned Claim</option>
                <option value="Withdrawn Claim" ${irb_decision == 'Withdrawn Claim' ? 'selected' : '' }>Withdrawn Claim</option>
                <option value="N/A" ${irb_decision == 'N/A' ? 'selected' : '' }>N/A</option>
                </select>
                </div></td>
                <th width="20%">Date RPD Decision Received</th>
                <td width="2%">:</td>
                <td><input type="date" name="length_irb"  value=<%=request.getAttribute("length_irb") %>></td>
              </tr>
              <tr>
              <th width="20%">PRRA</th>
                <td width="2%">:</td>
                <td><select name="prra">
                <option value="false" ${prra == 'false' ? 'selected' : '' }>No</option>
                <option value="true" ${prra == 'true' ? 'selected' : ''}>Yes</option>
                </select></td>
                <th width="20%">Healthcare Coverage</th>
                <td width="2%">:</td>
                <td><select name="healthcare" >
                <option value="IFHP" ${healthcare == 'IFHP' ? 'selected' : '' }>IFHP</option>
                <option value="Provincial Healthcare Coverage" ${healthcare == 'Provincial Healthcare Coverage' ? 'selected' : '' }>Provincial Healthcare Coverage</option>
                <option value="No Healthcare Coverage" ${healthcare ==  'No Healthcare Coverage' ? 'selected' : '' }>No Healthcare Coverage</option>
                <option value="N/A" ${healthcare == 'N/A' ? 'selected' : ''  }>N/A</option>
                </select></td>
                
              </tr>
            </table>
            </div>
          </div>
          <input type="hidden" name="method" value="saveChanges2">
            <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
            <button type="submit" class="button" style="float: right;">Save Resident</button>
            </form>
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
              <button class="buttons">General</button>
              </form>
              <div class="change_button">
              <button class="button">Claim Process</button>
              </div>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionC">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">Integration/Workshop</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionD">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">Employment/Housing</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionE">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">DEV</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionF">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">Reports</button>
              </form>
              </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<script>
var expanded = false;

function showCheckboxes() {
  var checkboxes = document.getElementById("checkboxes");
  if (!expanded) {
    checkboxes.style.display = "block";
    expanded = true;
  } else {
    checkboxes.style.display = "none";
    expanded = false;
  }
}
</script>
</body>
</html>