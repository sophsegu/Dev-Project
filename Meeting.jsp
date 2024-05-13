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
          </div>
        </div>
      </div>
      <div class="col-lg-8">
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
          <form method="post" action="Agent">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Meeting</h3>
            <table class="table table-bordered" style="width:100%">
            <tr>
            <th width="20%">Agent Name</th>
            <td width="2%">:</td>
            <td><input type="text" name="agent" required></td>
            <th width="20%">Meeting Date</th>
            <td width="2%">:</td>
            <td><input type="date" name="meeting_date" required></td>
            </tr>
            <tr>
            <th width="20%">Meeting Type</th>
            <td width="2%">:</td>
            <td><select name="meeting_type">
            <option value="Impromptu">Impromptu</option>
            <option value="Scheduled">Scheduled</option>
            <option value="No Show">No Show</option>
            <option value="File Update">File Update</option>
            </select>
            </td>
            <th width="20%">Meeting Notes</th>
            <td width="2%">:</td>
            <td><textarea name="meeting_notes" rows="1"></textarea></td>
            </tr>
            </table>
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Employment</h3>
            <table class="table table-bordered" style="width:100%">
            <tr>
            <th width="20%">Employment Status</th>
            <td width="2%">:</td>
            <td><select id="employment_status" name="employment_status" onchange="changeFunc();">
            <option value="N/A">N/A</option>
            <option value="Yes">Yes</option>
            <option value="No">No</option>
            </select></td>
            <th id="employment_type" width="20%" style="display:none;">Employment Type</th>
            <td id="employment_type1" width="2%" style="display:none;">:</td>
            <td id="employment_type2" style="display:none;"><select name="employment_type">
            <option value="Full Time">Full Time</option>
            <option value="Part Time">Part Time</option>
            <option value="Seasonal">Seasonal</option>
            <option value="Self-Employed">Self-Employed</option>
            <option value="Volunteering">Volunteering</option>
            <option value="Other (Notes)">Other (Notes)</option>
            </select>
            </tr>
            <tr id="looking/resume" style="display:none;">
            <th width="20%">Looking for Job</th>
            <td width="2%">:</td>
            <td><select name="looking_job">
            <option value="Yes">Yes</option>
            <option value="No (Pregnant)">No (Pregnant)</option>
            <option value="No (Young Kids)">No (Young Kids)</option>
            <option value="No (Work Permit)">No (Work Permit)</option>
            <option value="No Other (Notes)">No Other (Notes)</option>
            </select>
            <th width="20%">Resume</th>
            <td width="2%">:</td>
            <td><select name="resume">
            <option value="Yes">Yes</option>
            <option value="No">No</option>
            <option value="N/A">N/A</option>
            </select>
            </td>
            </tr>
            <tr id="actions/obstacle" style="display:none;">
            <th width="20%">Actions Taken</th>
            <td width="2%">:</td>
            <td><div class="actions"><select name="actions" multiple>
            <option value="Cold calls to potential employers">Cold calls to potential employers</option>
            <option value="In person visits to potential workplaces">In person visits to potential workplaces</option>
            <option value="Applied for jobs online">Applied for jobs online</option>
            <option value="Followed up on previous applications">Followed up on previous applications</option>
            <option value="Went to job fairs">Went to job fairs</option>
            <option value="Consulted employment services">Consulted employment services</option> 
            </select></div></td>
            <th width="20%">Job Search Obstacles</th>
            <td width="2%">:</td>
            <td><select name="obstacles" multiple>
            <option value="No Canadian Experience">No Canadian Experience</option>
            <option value="Discrimination">Discrimination</option>
            <option value="No Childcare">No Childcare</option>
            <option value="Transporation">Transportation</option>
            <option value="Medical Issues">Medical Issues</option>
            <option value="Diploma Recognition">Diploma Recognition</option>
            <option value="Job Scarcity">Job Scarcity</option>
            <option value="Language Barrier">Language Barrier</option>
            <option value="Other (Notes)">Other (Notes)</option>
            </select></td></tr>
            <tr><th width="20%">Jobs on JobBank.gc.ca</th>
            <td width="2%">:</td>
            <td><select name="jobBank">
            <option value="Yes">Yes</option>
            <option value="No">No</option>
            </select></td>
            <th width="20%">Industry of Certification</th>
            <td width="2%">:</td>
            <td><select name="certificateIndustry">
            <option value="Agriculture">Agriculture</option>
            <option value="Construction">Construction</option>
            <option value="Education">Education</option>
            <option value="Healthcare">Healthcare</option>
            <option value="Hospitality/Tourism">Hospitality/Tourism</option>
            <option value="Industrial">Industrial</option>
            <option value="Logistics">Logistics</option>
            <option value="Media/Communications">Media/Communications</option>
            <option value="Retail & Service">Retail/Services</option>
            <option value="Security & Defense">Security & Defense</option>
            <option value="Technology">Technology</option>
            <option value="None/Unknown">None/Unknown</option>
            </select></td>
            </tr>
            <tr>
            <th width="20%">Experience</th>
            <td width="2%">:</td>
            <td><select name="experience">
            <option value="Agriculture">Agriculture</option>
            <option value="Construction">Construction</option>
            <option value="Education">Education</option>
            <option value="Healthcare">Healthcare</option>
            <option value="Hospitality/Tourism">Hospitality/Tourism</option>
            <option value="Industrial">Industrial</option>
            <option value="Logistics">Logistics</option>
            <option value="Media/Communications">Media/Communications</option>
            <option value="Retail & Service">Retail/Services</option>
            <option value="Security & Defense">Security & Defense</option>
            <option value="Technology">Technology</option>
            <option value="None/Unknown">None/Unknown</option>
            </select></td>
            <th width="20%">Income</th>
            <td width="2%">:</td>
            <td><select name="income">
            <option value="Employment Income">Employment Income</option>
            <option value="Social Assistance">Social Assistance</option>
            <option value="Child Benefits">Child Benefits</option>
            <option value="Disability">Disability</option>
            <option value="Other (Notes)">Other (Notes)</option>
            </select></td></tr>
            <tr>
            <th width="20%">Transportation</th>
            <td width="2%">:</td>
            <td><select name="transportation">
            <option value="My Car">My Car</option>
            <option value="Carpooling (Not my Car)">Carpooling (Not my Car)</option>
            <option value="My Motorcycle">My Motorcycle</option>
            <option value="My Bicycle">My Bicycle</option>
            <option value="Public Transportation">Public Transportation</option>
            <option value="Other (Notes)">Other (Notes)</option>
            </select></td></tr>
            <tr></tr>
            </table>
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Housing</h3>
            <table class="table table-bordered" style="width:100%">
            <tr>
            <th width="20%">Lodging Found</th>
            <td width="2%">:</td>
            <td><select name="lodging" id="lodging" onchange="changeFunc2();">
            <option value="N/A">N/A</option>
            <option value="Yes">Yes</option>
            <option value="No">No</option>
            </select></td>
            <th id="moving_date" style="display:none;" width="20%">Moving Date</th>
            <td id="moving_date1" style="display:none;" width="2%">:</td>
            <td id="moving_date2" style="display:none;"><input type="date" name="moving_date"></td>
            </tr>
            <tr id="search/urban" style="display:none;">
            <th width="20%">Housing Search</th>
            <td width="2%">:</td>
            <td><select name="housing_search">
            <option value="Yes">Yes</option>
            <option value="No (Going Back to Country)">No (Going Back to Country)</option>
            <option value="No (Feels No Rush)">No (Feels No Rush)</option>
            <option value="No (Waiting for RPCD)">No (Waiting for RPCD)</option>
            <option value="No (Language Barrier)">No (Language Barrier)</option>
            <option value="No Others (Notes)">No Others (Notes)</option>
            </select></td>
            <th width="20%">Housing Search Outside Urban Area (35+ km)</th>
            <td width="2%">:</td>
            <td><select name="housing_outside">
            <option value="Yes">Yes</option>
            <option value="No">No</option>
            </select></td></tr>
            <tr id="colocation/challenge" style="display:none;">
            <th width="20%">Co-location</th>
            <td width="2%">:</td>
            <td><select name="colocation">
            <option value="Yes (and Looking Together)">Yes (and Looking Together)</option>
            <option value="Yes (but Found No Coloc)">Yes (but Found No Coloc)</option>
            <option value="Yes (but Didn't Work)">Yes (but Didn't Work)</option>
            <option value="No (but Could Consider it)">No (but Could Consider it)</option>
            <option value="No (Family Size)">No (Family Size)</option>
            <option value="No Other (Notes)">No Other (Notes)</option>
            <option value="No Way">No Way</option>
            </select></td>
            <th width="20%">Housing Challenges</th>
            <td width="2%">:</td>
            <td><select name="housing_obstacles" multiple>
            <option value="Don''t Pass Credit Check">Don't Pass Credit Check</option>
            <option value="Scarcity">Scarcity</option>
            <option value="Medical Issues">Medical Issues</option>
            <option value="Don''t Have A Job">Don't Have A Job</option>
            <option value="On Wellfare">On Wellfare</option>
            <option value="Size of our Family">Size of our Family</option>
            <option value="Discrimination">Discrimination</option>
            <option value="Can''t Afford">Can't Afford</option>
            <option value="Language Barrier">Language Barrier</option>
            <option value="Other (Notes)">Other (Notes)</option>
            </select></td></tr>
            <tr>
            <th width="20%">Housing Search Support</th>
            <td width="2%">:</td>
            <td><select name="housing_support">
            <option value="Yes (Provincial)">Yes (Provincial)</option>
            <option value="Yes (Municipality)">Yes (Municipality)</option>
            <option value="Yes (Globalys Support)">Yes (Globalys Support)</option>
            <option value="Yes (Community Groups)">Yes (Community Groups)</option>
            <option value="Yes (Social Media)">Yes (Social Media)</option>
            <option value="No (Don''t Know Where to Start)">No (Don't Know Where to Start)</option>
            <option value="No Other (Notes)">No Other (Notes)</option>
            <option value="Just No">Just No</option>
            </select></td>
            <th id="foundLodging1" style="display:none;" width="20%">Way of Finding Housing</th>
            <td id="foundLodging2" style="display:none;" width="2%">:</td>
            <td id="foundLodging3" style="display:none;" width="20%"><select name="wayOfFindingLodging">
            <option value="On my own">On my own</option>
            <option value="Through friends/family">Through friends/family</option>
            <option value="Through a Service (Globalys)">Through a Service (Globalys)</option>
            <option value="Through a Service (Name in notes)">Through a Service (Name in notes)</option>
            </select></td>
            </tr>
            </table>
            <input type="hidden" name="method" value="saveMeeting">
            <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
            <button type="submit" class="button" style="float: right; display: flex;">Submit Meeting Tracker</button>
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
<script>
function changeFunc() {
	  var selectBox = document.getElementById("employment_status");
	  var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	  
	  if (selectedValue === "Yes") {
	    document.getElementById("employment_type").style.display = "table-cell";
	    document.getElementById("employment_type1").style.display = "table-cell";
	    document.getElementById("employment_type2").style.display = "table-cell";
	    document.getElementById("looking/resume").style.display = "none";
		  document.getElementById("actions/obstacle").style.display = "none";
	  } else if (selectedValue === "No"){
		  document.getElementById("looking/resume").style.display = "table-row";
		  document.getElementById("actions/obstacle").style.display = "table-row";
		  document.getElementById("employment_type").style.display = "none";
		    document.getElementById("employment_type1").style.display = "none";
		    document.getElementById("employment_type2").style.display = "none";
	  }else {
	    document.getElementById("employment_type").style.display = "none";
	    document.getElementById("employment_type1").style.display = "none";
	    document.getElementById("employment_type2").style.display = "none";
	    document.getElementById("looking/resume").style.display = "none";
		  document.getElementById("actions/obstacle").style.display = "none";
	  }
	}
function changeFunc2() {
	  var selectBox = document.getElementById("lodging");
	  var selectedValue = selectBox.options[selectBox.selectedIndex].value;
	  
	  if (selectedValue === "Yes") {
	    document.getElementById("moving_date").style.display = "table-cell";
	    document.getElementById("moving_date1").style.display = "table-cell";
	    document.getElementById("moving_date2").style.display = "table-cell";
	    document.getElementById("foundLodging1").style.display = "table-cell";
	    document.getElementById("foundLodging2").style.display = "table-cell";
	    document.getElementById("foundLodging3").style.display = "table-cell";
	    document.getElementById("search/urban").style.display = "none";
	    document.getElementById("colocation/challenge").style.display = "none";
	  } else if (selectedValue === "No"){
	    document.getElementById("search/urban").style.display = "table-row";
	    document.getElementById("colocation/challenge").style.display = "table-row";
	    document.getElementById("foundLodging1").style.display = "none";
	    document.getElementById("foundLodging2").style.display = "none";
	    document.getElementById("foundLodging3").style.display = "none";
	    document.getElementById("moving_date").style.display = "none";
	    document.getElementById("moving_date1").style.display = "none";
	    document.getElementById("moving_date2").style.display = "none";
	  } else{
		  document.getElementById("search/urban").style.display = "none";
		    document.getElementById("colocation/challenge").style.display = "none";
		    document.getElementById("foundLodging1").style.display = "none";
		    document.getElementById("foundLodging2").style.display = "none";
		    document.getElementById("foundLodging3").style.display = "none";
		    document.getElementById("moving_date").style.display = "none";
		    document.getElementById("moving_date1").style.display = "none";
		    document.getElementById("moving_date2").style.display = "none";
	  }
	}
</script>
</body>
</html>