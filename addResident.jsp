<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="openResident3.css?version=4">
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
        <div class="col-lg-8">
        
        <div class="card shadow-sm">
          <div class="card-header bg-transparent border-0">
        <img class="logo" src="img/globalys_logo.png" alt="logo">
            <h3 class="mb-0"><i class="far fa-clone pr-1"></i>Intake</h3>
          </div>
          <div class="card-body pt-0">
          <form method="post" action = "Agent">
            <table class="table table-bordered">
            <tr>
            <th width="20%">Room Number</th>
            <td width="2%">:</td>
            <td><input type="text" name="room_num" required></td>
              <tr>
                <th width="20%">Arrival Date to Canada</th>
                <td width="2%">:</td>
                <td><input type="date" name="arrival_canada" required></td>
                <th width="20%">Arrival to DEV Hotel</th>
                <td width="2%">:</td>
                <td><input type="date" name="arrival_dev" required></td>
              </tr>
              <tr>
                <th width="20%">Mode of Arrival</th>
                <td width="2%">:</td>
                <td><select name="mode_of_arrival" multiple>
                <option value="Pearson Airport" ${mode_of_arrival == 'Pearson Airport' ? 'selected' : '' }>Pearson Airport</option>
                <option value="Montreal Airport" ${mode_of_arrival == 'Montreal Airport' ? 'selected' : '' }>Montreal Airport</option>
                <option value="Other Airport" ${mode_of_arrival == 'Other Airport' ? 'selected' : '' }>Other Airport</option>
                <option value="Lacolle POE" ${mode_of_arrival ==  'Lacolle POE' ? 'selected' : '' }>Lacolle POE</option>
                <option value="Cornwall POE" ${mode_of_arrival == 'Cornwall POE' ? 'selected' : ''  }>Cornwall POE</option>
                <option value="Other Land POE" ${mode_of_arrival == 'Other Land POE' ? 'selected' : '' }>Other Land POE</option>
                <option value="Shelter" ${mode_of_arrival == 'Shelter' ? 'selected' : '' }>Shelter</option>
                <option value="Other Hotel" ${mode_of_arrival == 'Other Hotel' ? 'selected' : '' }>Other Hotel</option>
                <option value="Walk-in" ${mode_of_arrival == 'Walk-in' ? 'selected' : '' }>Walk-in</option>
                <option value="Cornwall City Request" ${mode_of_arrival == 'Cornwall City Request' ? 'selected' : '' }>Cornwall City Request</option>
                <option value="Reunificiation" ${mode_of_arrival == 'Reunification' ? 'selected' : '' }>Reunification</option>
                <option value="N/A" ${mode_of_arrival == 'N/A' ? 'selected' : '' }>N/A</option>
                </select></td>
                <th width="20%">Reunification Date</th>
                <td width="2%">:</td>
                <td><input type="date" name="reunification"></td>
              </tr>
              
              <tr>
                <th width="20%">Bracelet Number</th>
                <td width="2%">:</td>
                <td><input type="text" name="bracelet"></td>
                <th width="20%">UCI Number</th>
                <td width="2%">:</td>
                <td><input type="text" name="uci"></td>
              </tr>
              <tr>
                <th width="20%">Date of Birth</th>
                <td width="2%">:</td>
                <td><input type="date" name="dob" required></td>
                <th width="20%">Country of Origin</th>
                <td width="2%">:</td>
                <td><select name="citizenship" required> 
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
              </tr>
              <tr>
                <th width="20%">Family Name</th>
                <td width="2%">:</td>
                <td><input type="text" name="surname" required></td>
                <th width="20%">Given Name</th>
                <td width="2%">:</td>
                <td><input type="text" name="name" required></td>
              </tr>
              <tr>
                <th width="20%">Telephone</th>
                <td width="2%">:</td>
                <td><input type="text" name="phone"></td>
                <th width="20%">Email</th>
                <td width="2%">:</td>
                <td><input type="text" name="email"></td>
              </tr><tr>
                <th width="20%">Gender Identity</th>
                <td width="2%">:</td>
                <td><select name="gender" required> 
                <option value="Female">Female</option>
                <option value="Male">Male</option>
                <option value="Other">Other</option>
                </select></td>
                <th width="20%">Family Composition</th>
                <td width="2%">:</td>
                <td><select name="family_composition" required> 
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
              <tr>
                <th width="20%">Primary Language</th>
                <td width="2%">:</td>
                <td><select name="primary_language" required> 
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
              </tr>
            </table>
            <input type="hidden" name="method" value="NextIntake">
              <button type="submit" class="button" style="float: right;">Next</button>
            </form>
          </div>
        </div>
        </div>
      </div>
    </div>
  </div>
</div>
</body>
</html>