<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident.css?version=40">
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
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Employment</h3>
          </div>
          <form method="post" action="Agent">
          <div class="card-body pt-0">
          <div class="Body">
            <table class="table table-bordered" style="width:100%">
              <tr>
                <th width="20%">Employment Status</th>
                <td width="2%">:</td>
                <td><select name="employment">
                <option value="Unemployed" ${employment == 'Unemployed' ? 'selected' : '' }>Unemployed</option>
                <option value="Employed" ${employment == 'Employed' ? 'selected' : '' }>Employed</option>
                <option value="N/A" ${employment == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Part Time/Full Time</th>
                <td width="2%">:</td>
                <td><select name="part_full_time">
                <option value="N/A" ${aoc == 'N/A' ? 'selected' : '' }>N/A</option>
                <option value="Full Time" ${part_full_time == 'Full Time' ? 'selected' : '' }>Full Time</option>
                <option value="Part Time" ${part_full_time == 'Part Time' ? 'selected' : '' }>Part Time</option>
                <option value="Seasonal" ${part_full_time == 'Seasonal' ? 'selected' : '' }>Seasonal</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Resume/CV</th>
                <td width="2%">:</td>
                <td><select name="resume">
                <option value="No" ${resume == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${resume == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${resume == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Employment Challenges</th>
                <td width="2%">:</td>
                <td><select name="employment_challenges" multiple>
                <option value="Childcare" ${employment_challenges.contains('Childcare') ? 'selected' : '' }>Childcare</option>
                <option value="ODSP" ${employment_challenges.contains('ODSP') ? 'selected' : '' }>ODSP</option>
                <option value="Pregnant" ${employment_challenges.contains('Pregnant') ? 'selected' : '' }>Pregnant</option>
                <option value="Medical" ${employment_challenges.contains('Medical') ? 'selected' : '' }>Medical</option>
                <option value="Transportation" ${employment_challenges.contains('Transportation') ? 'selected' : '' }>Transportation</option>
                <option value="Language" ${employment_challenges.contains('Language') ? 'selected' : '' }>Language</option>
                <option value="Still in School" ${employment_challenges.contains('Still in School') ? 'selected' : '' }>Still in School</option>
                <option value="Recertification" ${employment_challenges.contains('Recertification') ? 'selected' : '' }>Recertification</option>
                <option value="Layoff" ${employment_challenges.contains('Layoff') ? 'selected' : '' }>Layoff</option>
                <option value="Other" ${employment_challenges.contains('Other') ? 'selected' : '' }>Other</option>
                <option value="N/A" ${employment_challenges.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Employer</th>
                <td width="2%">:</td>
                <td><textarea name="employer" rows="1" style="resize: none;"><%=request.getAttribute("employer") %></textarea></td>
                <th width="20%">Start of Employment</th>
                <td width="2%">:</td>
                <td><input type="date" name="start_employment" value=<%=request.getAttribute("start_employment") %>></td>
              </tr>
              <tr>
                <th width="20%">Length Employment</th>
                <td width="2%">:</td>
                <td><%=request.getAttribute("length_employment") %></td>
                <th width="20%">Ontario Works Notified</th>
                <td width="2%">:</td>
                <td><select name="ow_notified">
                <option value="No" ${ow_notified == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${ow_notified == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${ow_notified == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Referral to JobZone</th>
                <td width="2%">:</td>
                <td><select name="refferal_jobzone">
                <option value="No" ${refferal_jobzone == 'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${refferal_jobzone == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${refferal_jobzone == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Date of Referral to JobZone</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_jobzone" value=<%=request.getAttribute("date_jobzone") %>></td>
              </tr>
              <tr>
                <th width="20%">Referral to Drake International</th>
                <td width="2%">:</td>
                <td><select name="refferal_drake" >
                <option value="No" ${refferal_drake ==  'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${refferal_drake == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${refferal_drake == 'N/A' ? 'selected' : ''  }>N/A</option>
                </select></td>
                <th width="20%">Date of Referral to Drake International</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_drake" value=<%=request.getAttribute("date_drake") %>></td>
              </tr>
              <tr>
                <th width="20%">Referral to DEV Centre</th>
                <td width="2%">:</td>
                <td><select name="refferal_dev" >
                <option value="No" ${refferal_dev ==  'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${refferal_dev == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${refferal_dev == 'N/A' ? 'selected' : ''  }>N/A</option>
                </select></td>
                <th width="20%">Date of Referral to DEV Centre</th>
                <td width="2%">:</td>
                <td><input type="date" name="dev_date" value=<%=request.getAttribute("dev_date") %>></td>
              </tr>
              <tr>
                <th width="20%">Referral to NEWS</th>
                <td width="2%">:</td>
                <td><select name="refferal_news" >
                <option value="No" ${refferal_news ==  'No' ? 'selected' : '' }>No</option>
                <option value="Yes" ${refferal_news == 'Yes' ? 'selected' : '' }>Yes</option>
                <option value="N/A" ${refferal_news == 'N/A' ? 'selected' : ''  }>N/A</option>
                </select></td>
                <th width="20%">Date of Referral to NEWS</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_news" value=<%=request.getAttribute("date_news") %>></td>
              </tr>
              <tr>
                <th width="20%">Employment Assessment</th>
                <td width="2%">:</td>
                <td><select name="employment_assessment" multiple>
                <option value="In their field" ${employment_assessment.contains('In their field') ? 'selected' : '' }>In their field</option>
                <option value="Looking for same salary range" ${employment_assessment.contains('Looking for same salary range') ? 'selected' : '' }>Looking for same salary range</option>
                <option value="Working on re-certification" ${employment_assessment.contains('Working on re-certification') ? 'selected' : '' }>Working on re-certification</option>
                <option value="Training" ${employment_assessment.contains('Training') ? 'selected' : '' }>Training</option>
                <option value="Language Classes" ${employment_assessment.contains('Language Classes') ? 'selected' : '' }>Language Classes</option>
                <option value="School" ${employment_assessment.contains('School') ? 'selected' : '' }>School</option>
                <option value="N/A" ${employment_assessment.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Initial Employment Assessment</th>
                <td width="2%">:</td>
                <td><textarea name="initial_mployment_assessment" rows="1" style="resize: none;"><%=request.getAttribute("initial_mployment_assessment") %></textarea></td>
                <th width="20%">Employment Updates and Referral Follow Ups</th>
                <td width="2%">:</td>
                <td><textarea name="employment_updates" rows="1" style="resize: none;"><%=request.getAttribute("employment_updates") %></textarea></td>
              </tr>
            </table>
            <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Housing</h3>
          </div>
          <table class="table table-bordered" style="width:100%">
          <tr>
                <th width="20%">Housing Assessment</th>
                <td width="2%">:</td>
                <td><select name="housing_assessment" multiple>
                <option value="Bachelor Apartment" ${housing_assessment.contains('Bachelor Apartment') ? 'selected' : '' }>Bachelor Apartment</option>
                <option value="1 Bedroom Apartment" ${housing_assessment.contains('1 Bedroom Apartment') ? 'selected' : '' }>1 Bedroom Apartment</option>
                <option value="2 Bedroom Apartment" ${housing_assessment.contains('2 Bedroom Apartment') ? 'selected' : ''  }>2 Bedroom Apartment</option>
                <option value="3 Bedroom Apartment" ${housing_assessment.contains('3 Bedroom Apartment') ? 'selected' : ''  }>3 Bedroom Apartment</option>
                <option value="House" ${housing_assessment.contains('House') ? 'selected' : ''  }>House</option>
                <option value="Looking for roommate" ${housing_assessment.contains('Looking for roommate') ? 'selected' : ''  }>Looking for roommate</option>
                <option value="Budget limitation" ${housing_assessment.contains('Budget limitation') ? 'selected' : ''  }>Budget limitation</option>
                <option value="N/A" ${housing_assessment.contains('N/A') ? 'selected' : ''  }>N/A</option>
                </select></td>
                <th width="20%">Housing Updates</th>
                <td width="2%">:</td>
                <td><select name="housing_updates" multiple>
                <option value="Landlord Refferal" ${housing_updates.contains('Landlord Refferal') ? 'selected' : '' }>Landlord Refferal</option>
                <option value="Filing applications" ${housing_updates.contains('Filing applications') ? 'selected' : '' }>Filing applications</option>
                <option value="Visitation" ${housing_updates.contains('Visitation') ? 'selected' : '' }>Visitation</option>
                <option value="First/Last Month" ${housing_updates.contains('First/Last Month') ? 'selected' : '' }>First/Last Month</option>
                <option value="N/A" ${housing_updates.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Housing Application</th>
                <td width="2%">:</td>
                <td><select name="housing_application" multiple>
                <option value="Social Housing - Cornwall" ${housing_application.contains('Social Housing - Cornwall') ? 'selected' : '' }>Social Housing - Cornwall</option>
                <option value="Social Housing - Ottawa" ${housing_application.contains('Social Housing - Ottawa') ? 'selected' : '' }>Social Housing - Ottawa</option>
                <option value="Social Housing - GTA" ${housing_application.contains('Social Housing - GTA') ? 'selected' : ''  }>Social Housing - GTA</option>
                <option value="Social Housing - Hamilton" ${housing_application.contains('Social Housing - Hamilton') ? 'selected' : ''  }>Social Housing - Hamilton</option>
                <option value="Social Housing - Windsor" ${housing_application.contains('Social Housing - Windsor') ? 'selected' : ''  }>Social Housing - Windsor</option>
                <option value="Social Housing - Alberta" ${housing_application.contains('Social Housing - Alberta') ? 'selected' : ''  }>Social Housing - Alberta</option>
                <option value="Social Housing - Alberta" ${housing_application.contains('Social Housing - Alberta') ? 'selected' : ''  }>Social Housing - Alberta</option>
                <option value="Social Housing - Atlantic" ${housing_application.contains('Social Housing - Atlantic') ? 'selected' : ''  }>Social Housing - Atlantic</option>
                <option value="Social Housing - BC" ${housing_application.contains('Social Housing - BC') ? 'selected' : ''  }>Social Housing - BC</option>
                <option value="Rental" ${housing_application.contains('Rental') ? 'selected' : ''  }>Rental</option>
                <option value="COHB" ${housing_application.contains('COHB') ? 'selected' : ''  }>COHB</option>
                <option value="Yes" ${housing_application.contains('Yes') ? 'selected' : ''  }>Yes</option>
                <option value="No" ${housing_application.contains('No') ? 'selected' : ''  }>No</option>
                <option value="N/A" ${housing_application.contains('N/A') ? 'selected' : ''  }>N/A</option>
                </select></td>
                <th width="20%">Date of Refferal for Housing Support</th>
                <td width="2%">:</td>
                <td><input type="date" name="date_housing_support" value=<%=request.getAttribute("date_housing_support") %>></td>
              </tr>
              <tr>
                <th width="20%">Projected Move Out Date</th>
                <td width="2%">:</td>
                <td><input type="date" name="projected_move_out" value=<%=request.getAttribute("projected_move_out") %>></td>
                <th width="20%">First/Last Month Deposit Date</th>
                <td width="2%">:</td>
                <td><input type="date" name="first_last_month" value=<%request.getAttribute("first_last_month"); %>></td>
              </tr>
              <tr>
                <th width="20%">Networth</th>
                <td width="2%">:</td>
                <td><input type="text" name="networth" value=<%=request.getAttribute("networth") %>></td>
                <th width="20%">First/Last Month Deposit Date</th>
                <td width="2%">:</td>
                <td><input type="date" name="first_last_month" value=<%request.getAttribute("first_last_month"); %>></td>
              </tr>
          </table>
            </div>
          </div>
          <input type="hidden" name="method" value="saveChanges4">
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
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionB">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">Claim Process</button>
              </form>
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionC">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button class="buttons">Integration/Workshop</button>
              </form>
              <div class="change_button">
              <button class="button">Employment/Housing</button>
              </div>
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