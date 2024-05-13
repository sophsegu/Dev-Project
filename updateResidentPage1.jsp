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
          <div>
            <h3 class="mb-0" style="display: inline-block;"><i class="far fa-clone pr-1" style="display: inline-block;"></i>General Information</h3>
            <div style="display: flex;" >
            <form method="post" action="Agent">
            <input type="hidden" name="method" value="depart">
            <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
            <input type="hidden" name="status" value=<%=request.getAttribute("status")%>>
            <input type="hidden" name="uci" value=<%=request.getAttribute("uci") %>>
            <input type="hidden" name="bracelet" value=<%=request.getAttribute("bracelet") %>>
            <input type="hidden" name="room_num" value=<%=request.getAttribute("room_num") %>>
            <input type="hidden" name="nom" value=<%=request.getAttribute("nom") %>>
            <input type="hidden" name="surname" value=<%=request.getAttribute("surname") %>>
            <input type="hidden" name="dob" value=<%=request.getAttribute("dob") %>>
            <input type="hidden" name="age" value=<%=request.getAttribute("age") %>>
            <input type="hidden" name="gender" value=<%=request.getAttribute("gender") %>>
            <input type="hidden" name="primary_language" value=<%=request.getAttribute("primary_language")%>>
            <input type="hidden" name="other_language" value=<%=request.getAttribute("other_language") %>>
            <input type="hidden" name="citizenship" value=<%=request.getAttribute("citizenship") %>>
            <input type="hidden" name="phone" value=<%=request.getAttribute("phone") %>>
            <input type="hidden" name="email" value=<%=request.getAttribute("email") %>>
            <input type="hidden" name="family_composition" value=<%=request.getAttribute("family_composition") %>>
            <input type="hidden" name="image" value=<%=request.getAttribute("image") %>>
             <button type="submit" class="button">Depart</button>
            </form>
            <form method="post" action="Agent">
            <input type="hidden" name="method" value="meeting">
            <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
             <button type="submit" class="button" style="float: right; display: inline-block;">Meeting</button>
            </form>
            </div>
            </div>
          </div>
          <form method="post" action="Agent">
          <div class="card-body pt-0">
            <table class="table table-bordered">
              <tr>
                <th width="20%">Status</th>
                <td width="2%">:</td>
                <td><%=request.getAttribute("status")%></td>
                <th width="20%">Bracelet</th>
                <td width="2%">:</td>
                <td><input type="text" name="bracelet" value=<%=request.getAttribute("bracelet")%>></td>
              </tr>
              <tr>
                <th width="20%">Room Number</th>
                <td width="2%">:</td>
                <td><input type="text" name="room_num" value=<%=request.getAttribute("room_num")%>></td>
                <th width="20%">UCI</th>
                <td width="2%">:</td>
                <td><input type="text" name="uci" value=<%=request.getAttribute("uci")%>></td>
              </tr>
              <tr>
                <th width="20%">Name</th>
                <td width="2%">:</td>
                <td><textarea name="nom" rows="1" style="resize: none;"><%=request.getAttribute("nom")%></textarea></td>
                <th width="20%">Surname</th>
                <td width="2%">:</td>
                <td><textarea name="surname" rows="1" style="resize: none;"><%=request.getAttribute("surname")%></textarea></td>
              </tr>
              <tr>
                <th width="20%">Arrival to Canada</th>
                <td width="2%">:</td>
                <td><input type="date" name="arrival_canada" value=<%=request.getAttribute("arrival_canada")%>></td>
                <th width="20%">Time in Canada</th>
                <td width="2%">:</td>
                <td><%=request.getAttribute("length_canada")%></td>
              </tr>
              <tr>
                <th width="20%">Arrival to Dev</th>
                <td width="2%">:</td>
                <td><input type="date" name="arrival_dev" value=<%=request.getAttribute("arrival_dev")%>></td>
                <th width="20%">Time at Dev</th>
                <td width="2%">:</td>
                <td><%=request.getAttribute("lengt_dev") %></td>
              </tr>
              <tr>
                <th width="20%">Mode of Arrival</th>
                <td width="2%">:</td>
                <td><select name="mode_of_arrival" multiple>
                <option value="Pearson Airport" ${mode_of_arrival.contains('Pearson Airport') ? 'selected' : '' }>Pearson Airport</option>
                <option value="Montreal Airport" ${mode_of_arrival.contains('Montreal Airport') ? 'selected' : '' }>Montreal Airport</option>
                <option value="Other Airport" ${mode_of_arrival.contains('Other Airport') ? 'selected' : '' }>Other Airport</option>
                <option value="Lacolle POE" ${mode_of_arrival.contains('Lacolle POE') ? 'selected' : '' }>Lacolle POE</option>
                <option value="Cornwall POE" ${mode_of_arrival.contains('Cornwall POE') ? 'selected' : ''  }>Cornwall POE</option>
                <option value="Other Land POE" ${mode_of_arrival.contains('Other Land POE') ? 'selected' : '' }>Other Land POE</option>
                <option value="Shelter" ${mode_of_arrival.contains('Shelter') ? 'selected' : '' }>Shelter</option>
                <option value="Other Hotel" ${mode_of_arrival.contains('Other Hotel') ? 'selected' : '' }>Other Hotel</option>
                <option value="Walk-in" ${mode_of_arrival.contains('Walk-in') ? 'selected' : '' }>Walk-in</option>
                <option value="Cornwall City Request" ${mode_of_arrival.contains('Cornwall City Request') ? 'selected' : '' }>Cornwall City Request</option>
                <option value="Reunificiation" ${mode_of_arrival.contains('Reunification') ? 'selected' : '' }>Reunification</option>
                <option value="N/A" ${mode_of_arrival.contains('N/A') ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Reunification Date</th>
                <td width="2%">:</td>
                <td><input type="date" name="reunification" value=<%=request.getAttribute("reunification") %>></td>
              </tr>
              
              <tr>
                <th width="20%">Date of Birth</th>
                <td width="2%">:</td>
                <td><input type="date" name="dob" value=<%=request.getAttribute("dob")%>></td>
                <th width="20%">Age</th>
                <td width="2%">:</td>
                <td><%=request.getAttribute("age") %></td>
              </tr>
              <tr>
                <th width="20%">Primary Language</th>
                <td width="2%">:</td>
                <td><select name="primary_language" > 
                <option value="ARABIC" ${primary_language == 'ARABIC' ? 'selected' : ''}>ARABIC</option>
                <option value="BAMBARA" ${primary_language == 'BAMBARA' ? 'selected' : ''}>BAMBARA</option>
                <option value="BANGLA" ${primary_language == 'BANGLA' ? 'selected' : ''}>BANGLA</option>
                <option value="BANGOLI" ${primary_language == 'BANGOLI' ? 'selected' : ''}>BANGOLI</option>
                <option value="CREOLE" ${primary_language == 'CREOLE' ? 'selected' : ''}>CREOLE</option>
                <option value="DARI" ${primary_language == 'DARI' ? 'selected' : ''}>DARI</option>
                <option value="ENGLISH" ${primary_language == 'ENGLISH' ? 'selected' : ''}>ENGLISH</option>
                <option value="FARSI" ${primary_language == 'FARSI' ? 'selected' : ''}>FARSI</option>
                <option value="FRENCH" ${primary_language == 'FRENCH' ? 'selected' : ''}>FRENCH</option>
                <option value="GEORGIAN" ${primary_language == 'GEORGIAN' ? 'selected' : ''}>GEORGIAN</option>
                <option value="GORAM" ${primary_language == 'GORAM' ? 'selected' : ''}>GORAM</option>
                <option value="IGBO" ${primary_language == 'IGBO' ? 'selected' : ''}>IGBO</option>
                <option value="KALENJIN" ${primary_language == 'KALENJIN' ? 'selected' : ''}>KALENJIN</option>
                <option value="KIKUYU" ${primary_language == 'KIKUYU' ? 'selected' : ''}>KIKUYU</option>
                <option value="KINYARWANDA" ${primary_language == 'KINYARWANDA' ? 'selected' : ''}>KINYARWANDA</option>
                <option value="KIRUNDI" ${primary_language == 'KIRUNDI' ? 'selected' : ''}>KIRUNDI</option>
                <option value="KURDISH" ${primary_language == 'KURDISH' ? 'selected' : ''}>KURDISH</option>
                <option value="LINGALA" ${primary_language == 'LINGALA' ? 'selected' : ''}>LINGALA</option>
                <option value="LUGANDA" ${primary_language == 'LUGANDA' ? 'selected' : ''}>LUGANDA</option>
                <option value="MANDINKA" ${primary_language == 'MANDINKA' ? 'selected' : ''}>MANDINKA</option>
                <option value="NDEBELE" ${primary_language == 'NDEBELE' ? 'selected' : ''}>NDEBELE</option>
                <option value="PASHTO" ${primary_language == 'PASHTO' ? 'selected' : ''}>PASHTO</option>
                <option value="PERSIAN" ${primary_language == 'PERSIAN' ? 'selected' : ''}>PERSIAN</option>
                <option value="PORTUGUESE" ${primary_language == 'PORTUGUESE' ? 'selected' : ''}>PORTUGUESE</option>
                <option value="PUNJABI" ${primary_language == 'PUNJABI' ? 'selected' : ''}>PUNJABI</option>
                <option value="RUNYANKORE" ${primary_language == 'RUNYANKORE' ? 'selected' : ''}>RUNYANKORE</option>
                <option value="SHONA" ${primary_language == 'SHONA' ? 'selected' : ''}>SHONA</option>
                <option value="SOMALI" ${primary_language == 'SOMALI' ? 'selected' : ''}>SOMALI</option>
                <option value="SPANISH" ${primary_language == 'SPANISH' ? 'selected' : ''}>SPANISH</option>
                <option value="SWAHILI" ${primary_language == 'SWAHILI' ? 'selected' : ''}>SWAHILI</option>
                <option value="TAGLOG" ${primary_language == 'TAGLOG' ? 'selected' : ''}>TAGLOG</option>
                <option value="TIGRINYA" ${primary_language == 'TIGRINYA' ? 'selected' : ''}>TIGRINYA</option>
                <option value="TURKISH" ${primary_language == 'TURKISH' ? 'selected' : ''}>TURKISH</option>
                <option value="TWI" ${primary_language == 'TWI' ? 'selected' : ''}>TWI</option>
                <option value="URDU" ${primary_language == 'URDU' ? 'selected' : ''}>URDU</option>
                <option value="YORUBA" ${primary_language == 'YORUBA' ? 'selected' : ''}>YORUBA</option>
                <option value="OTHER" ${primary_language == 'OTHER' ? 'selected' : ''}>OTHER</option>
                </select></td>
                <th width="20%">Other Languages</th>
                <td width="2%">:</td>
                <td><select name="other_language" multiple> 
                <option value="ARABIC" ${other_language.contains('ARABIC') ? 'selected' : ''}>ARABIC</option>
                <option value="BAMBARA" ${other_language.contains('BAMBARA') ? 'selected' : ''}>BAMBARA</option>
                <option value="BANGLA" ${other_language.contains('BANGLA') ? 'selected' : ''}>BANGLA</option>
                <option value="BANGOLI" ${other_language.contains('BANGOLI') ? 'selected' : ''}>BANGOLI</option>
                <option value="CREOLE" ${other_language.contains('CREOLE') ? 'selected' : ''}>CREOLE</option>
                <option value="DARI" ${other_language.contains('DARI') ? 'selected' : ''}>DARI</option>
                <option value="ENGLISH" ${other_language.contains('ENGLISH') ? 'selected' : ''}>ENGLISH</option>
                <option value="FARSI" ${other_language.contains('FARSI') ? 'selected' : ''}>FARSI</option>
                <option value="FRENCH" ${other_language.contains('FRENCH') ? 'selected' : ''}>FRENCH</option>
                <option value="GEORGIAN" ${other_language.contains('GEORGIAN') ? 'selected' : ''}>GEORGIAN</option>
                <option value="GORAM" ${other_language.contains('GORAM') ? 'selected' : ''}>GORAM</option>
                <option value="IGBO" ${other_language.contains('IGBO') ? 'selected' : ''}>IGBO</option>
                <option value="KALENJIN" ${other_language.contains('KALENJIN') ? 'selected' : ''}>KALENJIN</option>
                <option value="KIKUYU" ${other_language.contains('KIKUYU') ? 'selected' : ''}>KIKUYU</option>
                <option value="KINYARWANDA" ${other_language.contains('KINYARWANDA') ? 'selected' : ''}>KINYARWANDA</option>
                <option value="KIRUNDI" ${other_language.contains('KIRUNDI') ? 'selected' : ''}>KIRUNDI</option>
                <option value="KURDISH" ${other_language.contains('KURDISH') ? 'selected' : ''}>KURDISH</option>
                <option value="LINGALA" ${other_language.contains('LINGALA') ? 'selected' : ''}>LINGALA</option>
                <option value="LUGANDA" ${other_language.contains('LUGANDA') ? 'selected' : ''}>LUGANDA</option>
                <option value="MANDINKA" ${other_language.contains('MANDINKA') ? 'selected' : ''}>MANDINKA</option>
                <option value="NDEBELE" ${other_language.contains('NDEBELE') ? 'selected' : ''}>NDEBELE</option>
                <option value="PASHTO" ${other_language.contains('PASHTO') ? 'selected' : ''}>PASHTO</option>
                <option value="PERSIAN" ${other_language.contains('PERSIAN') ? 'selected' : ''}>PERSIAN</option>
                <option value="PORTUGUESE" ${other_language.contains('PORTUGUESE') ? 'selected' : ''}>PORTUGUESE</option>
                <option value="PUNJABI" ${other_language.contains('PUNJABI') ? 'selected' : ''}>PUNJABI</option>
                <option value="RUNYANKORE" ${other_language.contains('RUNYANKORE') ? 'selected' : ''}>RUNYANKORE</option>
                <option value="SHONA" ${other_language.contains('SHONA') ? 'selected' : ''}>SHONA</option>
                <option value="SOMALI" ${other_language.contains('SOMALI') ? 'selected' : ''}>SOMALI</option>
                <option value="SPANISH" ${other_language.contains('SPANISH') ? 'selected' : ''}>SPANISH</option>
                <option value="SWAHILI" ${other_language.contains('SWAHILI') ? 'selected' : ''}>SWAHILI</option>
                <option value="TAGLOG" ${other_language.contains('TAGLOG') ? 'selected' : ''}>TAGLOG</option>
                <option value="TIGRINYA" ${other_language.contains('TIGRINYA') ? 'selected' : ''}>TIGRINYA</option>
                <option value="TURKISH" ${other_language.contains('TURKISH') ? 'selected' : ''}>TURKISH</option>
                <option value="TWI" ${other_language.contains('TWI') ? 'selected' : ''}>TWI</option>
                <option value="URDU" ${other_language.contains('URDU') ? 'selected' : ''}>URDU</option>
                <option value="YORUBA" ${other_language.contains('YORUBA') ? 'selected' : ''}>YORUBA</option>
                <option value="OTHER" ${other_language.contains('OTHER') ? 'selected' : ''}>OTHER</option>
                </select></td>
              </tr>
              <tr>
                <th width="20%">Phone Number</th>
                <td width="2%">:</td>
                <td><input type="text" name="phone" value=<%=request.getAttribute("phone")%>></td>
                <th width="20%">Email</th>
                <td width="2%">:</td>
                <td><input type="text" name="email" value=<%=request.getAttribute("email")%>></td>
              </tr>
              <tr>
                <th width="20%">Gender</th>
                <td width="2%">:</td>
                <td><select name="gender">
                <option value="Female" ${gender == 'Female' ? 'selected' : '' }>Female</option>
                <option value="Male" ${gender == 'Male' ? 'selected' : '' }>Male</option>
                <option value="Unspecified" ${gender == 'Unspecified' ? 'selected' : '' }>Unspecified</option>
                </select></td>
                <th width="20%">Citizenship</th>
                <td width="2%">:</td>
                <td><select name="citizenship" > 
                <option value="AFGHANISTAN" ${citizenship == 'AFGHANISTAN' ? 'selected' : ''}>AFGHANISTAN</option>
                <option value="ANGOLA" ${citizenship == 'ANGOLA' ? 'selected' : ''}>ANGOLA</option>
                <option value="BANGLADESH" ${citizenship == 'BANGLADESH' ? 'selected' : ''}>BANGLADESH</option>
                <option value="BARBADOS" ${citizenship == 'BARBADOS' ? 'selected' : ''}>BARBADOS</option>
                <option value="BRAZIL" ${citizenship == 'BRAZIL' ? 'selected' : ''}>BRAZIL</option>
                <option value="BURKINA FASO" ${citizenship == 'BURKINA FASO' ? 'selected' : ''}>BURKINA FASO</option>
                <option value="BURUNDI" ${citizenship == 'BURUNDI' ? 'selected' : ''}>BURUNDI</option>
                <option value="CAMEROON" ${citizenship == 'CAMEROON' ? 'selected' : ''}>CAMEROON</option>
                <option value="Canada" ${citizenship == 'Canada' ? 'selected' : ''}>CANADA</option>
                <option value="CHILE" ${citizenship == 'CHILE' ? 'selected' : ''}>CHILE</option>
                <option value="CHINA" ${citizenship == 'CHINA' ? 'selected' : ''}>CHINA</option>
                <option value="COLOMBIA" ${citizenship == 'COLOMBIA' ? 'selected' : ''}>COLOMBIA</option>
                <option value="CONGO" ${citizenship == 'CONGO' ? 'selected' : ''}>CONGO</option>
                <option value="ECUADOR" ${citizenship == 'ECUADOR' ? 'selected' : ''}>ECUADOR</option>
                <option value="ERITREA" ${citizenship == 'ERITREA' ? 'selected' : ''}>ERITREA</option>
                <option value="ETHIOPIA" ${citizenship == 'ETHIOPIA' ? 'selected' : ''}>ETHIOPIA</option>
                <option value="FRANCE" ${citizenship == 'FRANCE' ? 'selected' : ''}>FRANCE</option>
                <option value="GAMBIA" ${citizenship == 'GAMBIA' ? 'selected' : ''}>GAMBIA</option>
                <option value="GEORGIA" ${citizenship == 'GEORGIA' ? 'selected' : ''}>GEORGIA</option>
                <option value="GUINEA" ${citizenship == 'GUINEA' ? 'selected' : ''}>GUINEA</option>
                <option value="GUINEA-BISSAU" ${citizenship == 'GUINEA-BISSAU' ? 'selected' : ''}>GUINEA-BISSAU</option>
                <option value="HAITI" ${citizenship == 'HAITI' ? 'selected' : ''}>HAITI</option>
                <option value="IRAN" ${citizenship == 'IRAN' ? 'selected' : ''}>IRAN</option>
                <option value="KABERRA" ${citizenship == 'KABERRA' ? 'selected' : ''}>KABERRA</option>
                <option value="KENYA" ${citizenship == 'KENYA' ? 'selected' : ''}>KENYA</option>
                <option value="KIVINDYO" ${citizenship == 'KIVINDYO' ? 'selected' : ''}>KIVINDYO</option>
                <option value="MALI" ${citizenship == 'MALI' ? 'selected' : ''}>MALI</option>
                <option value="MAURITANIA" ${citizenship == 'MAURITANIA' ? 'selected' : ''}>MAURITANIA</option>
                <option value="MEXICO" ${citizenship == 'MEXICO' ? 'selected' : ''}>MEXICO</option>
                <option value="NICARAGUA" ${citizenship == 'NICARAGUA' ? 'selected' : ''}>NICARAGUA</option>
                <option value="NIGERIA" ${citizenship == 'NIGERIA' ? 'selected' : ''}>NIGERIA</option>
                <option value="PAKISTAN" ${citizenship == 'PAKISTAN' ? 'selected' : ''}>PAKISTAN</option>
                <option value="PALESTINE" ${citizenship == 'PALESTINE' ? 'selected' : ''}>PALESTINE</option>
                <option value="PANAMA" ${citizenship == 'PANAMA' ? 'selected' : ''}>PANAMA</option>
                <option value="PERU" ${citizenship == 'PERU' ? 'selected' : ''}>PERU</option>
                <option value="PHILIPPINES" ${citizenship == 'PHILIPPINES' ? 'selected' : ''}>PHILIPPINES</option>
                <option value="REP. DOM. DU CONGO" ${citizenship == 'REP. DOM. DU CONGO' ? 'selected' : ''}>REP. DOM. DU CONGO</option>
                <option value="RWANDA" ${citizenship == 'RWANDA' ? 'selected' : ''}>RWANDA</option>
                <option value="SAUDI ARABIA" ${citizenship == 'SAUDI ARABIA' ? 'selected' : ''}>SAUDI ARABIA</option>
                <option value="SENEGAL" ${citizenship == 'SENEGAL' ? 'selected' : ''}>SENEGAL</option>
                <option value="SOMALIA" ${citizenship == 'SOMALIA' ? 'selected' : ''}>SOMALIA</option>
                <option value="SOUTH SUDAN" ${citizenship == 'SOUTH SUDAN' ? 'selected' : ''}>SOUTH SUDAN</option>
                <option value="SUDAN" ${citizenship == 'SUDAN' ? 'selected' : ''}>SUDAN</option>
                <option value="SYRIA" ${citizenship == 'SYRIA' ? 'selected' : ''}>SYRIA</option>
                <option value="TANZANIA" ${citizenship == 'TANZANIA' ? 'selected' : ''}>TANZANIA</option>
                <option value="TCHAD" ${citizenship == 'TCHAD' ? 'selected' : ''}>TCHAD</option>
                <option value="TOGO" ${citizenship == 'TOGO' ? 'selected' : ''}>TOGO</option>
                <option value="TURKEY" ${citizenship == 'TURKEY' ? 'selected' : ''}>TURKEY</option>
                <option value="UGANDA" ${citizenship == 'UGANDA' ? 'selected' : ''}>UGANDA</option>
                <option value="UNITED ARAB EMIRATES" ${citizenship == 'UNITED ARAB EMIRATES' ? 'selected' : ''}>UNITED ARAB EMIRATES</option>
                <option value="UNITED STATES" ${citizenship == 'UNITED STATES' ? 'selected' : ''}>UNITED STATES</option>
                <option value="VENEZUELA" ${citizenship == 'VENEZUELA' ? 'selected' : ''}>VENEZUELA</option>
                <option value="YEMEN" ${citizenship == 'YEMEN' ? 'selected' : ''}>YEMEN</option>
                <option value="ZIMBABWE" ${citizenship == 'ZIMBABWE' ? 'selected' : ''}>ZIMBABWE</option>
                </select></td>
              </tr><tr>
                <th width="20%">Family Composition</th>
                <td width="2%">:</td>
                <td><select name="family_composition" > 
                <option value="Solo" ${family_composition == 'Solo' ? 'selected' : ''}>Solo</option>
                <option value="Family of 2" ${family_composition == 'Family of 2' ? 'selected' : ''}>Family of 2</option>
                <option value="Family of 3" ${family_composition == 'Family of 3' ? 'selected' : ''}>Family of 3</option>
                <option value="Family of 4" ${family_composition == 'Family of 4' ? 'selected' : ''}>Family of 4</option>
                <option value="Family of 5" ${family_composition == 'Family of 5' ? 'selected' : ''}>Family of 5</option>
                <option value="Family of 6" ${family_composition == 'Family of 6' ? 'selected' : ''}>Family of 6</option>
                <option value="Family of 7" ${family_composition == 'Family of 7' ? 'selected' : ''}>Family of 7</option>
                <option value="Family of 8" ${family_composition == 'Family of 8' ? 'selected' : ''}>Family of 8</option>
                <option value="Family of 9" ${family_composition == 'Family of 9' ? 'selected' : ''}>Family of 9</option>
                <option value="Family of 10" ${family_composition == 'Family of 10' ? 'selected' : ''}>Family of 10</option>
                <option value="Family of 11" ${family_composition == 'Family of 11' ? 'selected' : ''}>Family of 11</option>
                <option value="Family of 12" ${family_composition == 'Family of 12' ? 'selected' : ''}>Family of 12</option>
                <option value="Family of 13" ${family_composition == 'Family of 13' ? 'selected' : ''}>Family of 13</option>
                </select></td>
              </tr>
            </table>
          </div>
          <input type="hidden" name="method" value="saveChanges1">
            <input type="hidden" name="id" value=<%=request.getAttribute("id") %>>
            <button type="submit" class="button" style="float: right; display: flex;">Save Resident</button>
            </form>
        </div>
          <div style="height: 26px"></div>
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Pagination</h3>
          </div>
          <div class="pagination">
              <p>
              <div class="change_button">
              <button class="button">General</button>
              </div>
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
              <form method="post" action = "Agent">
              <input type="hidden" name="method" value="SectionF">
              <input type="hidden" name="id" value=<%=request.getAttribute("id")%>>
              <button type="submit" class="buttons">Reports</button>
              </form>
              </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>