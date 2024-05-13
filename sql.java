package Tracker;

import java.time.LocalDate;
import java.util.*;

public class sql {
	
	public String searchResidentNoOffset(String field, String values) {
		String s = "SELECT * FROM resident WHERE "+field+" "+values+" LIMIT 12 OFFSET";
		return(s);
	}
	
	public String generateNOOFFSET() {
		String s = "SELECT * FROM resident LIMIT 12 OFFSET";
		return(s);
	}
	
	public String userLogin(String user, String Password) {
		String s = "SELECT * FROM account WHERE username = '"+user+"' AND mot_de_passe = '"+Password+"'";
		return(s);
	}
	
	public String generate(int offset) {
		String s = "SELECT * FROM resident LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String generate_agents(int offset) {
		String s = "SELECT * FROM account LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String generate_rooms(int offset) {
		String s = "SELECT * FROM room LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String generate_actions(int offset) {
		String s = "SELECT * FROM actions  ORDER BY date_of_modification DESC LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String generate_reports(int offset) {
		String s = "SELECT * FROM reports  ORDER BY date_of_writing DESC LIMIT 12 OFFSET "+offset;
		return(s);
	}
	public String generate_reportsNoOffset() {
		String s = "SELECT * FROM reports  ORDER BY date_of_writing DESC LIMIT 12 OFFSET ";
		return(s);
	}
	
	public String count() {
		String s = "SELECT COUNT(*) FROM resident";
		return(s);
	}
	
	public String count_search(String searchField, String search) {
		String s = "SELECT COUNT(*) FROM resident WHERE "+searchField+" "+search;
		return(s);
	}
	
	public String count_agent() {
		String s = "SELECT COUNT(*) FROM account";
		return(s);
	}
	
	public String count_agent(String field, String value) {
		String s = "SELECT COUNT(*) FROM account WHERE "+field+" = '"+value+"'";
		return(s);
	}
	
	public String count_room() {
		String s = "SELECT COUNT(*) FROM room";
		return(s);
	}
	
	public String count_actions() {
		String s = "SELECT COUNT(*) FROM actions";
		return(s);
	}
	
	public String count_reports() {
		String s = "SELECT COUNT(*) FROM reports";
		return(s);
	}
	
	public String count_room_search(String field, String value) {
		String s = "SELECT COUNT(*) FROM room WHERE "+field+""+value;
		return(s);
	}
	
	public String count_action_search(String field, String value) {
		String s = "SELECT COUNT(*) FROM actions WHERE "+field+" = "+value;
		return(s);
	}
	
	public String deleteResident(String identifier) {
		String s = "DELETE FROM resident WHERE identifier = "+identifier;
		return(s);
	}
	
	public String open(String id) {
		String s = "SELECT * FROM resident WHERE identifier = "+id;
		return(s);
	}
	
	public String openProfile(String username) {
		String s = "SELECT * FROM account WHERE username = '"+username+"'";
		return(s);
	}
	
	public String openRoom(String room_num) {
		String s = "SELECT * FROM room WHERE room_num = "+room_num;
		return(s);
	}
	
	public String update_opened(String id, String name) {
		String s = "UPDATE resident SET opened='true', agent="+name;
		return(s);
	}
	
	public String search(String field, String values, int offset) {
		String s = "SELECT * FROM resident WHERE "+field+" "+values+" LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String searchAgent(String field, String value, int offset) {
		String s = "SELECT * FROM account WHERE "+field+" iLike '%"+value+"%' LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String search_rooms(String field, String value, int offset) {
		String s = "SELECT * FROM room WHERE "+field+""+value+" LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String search_action(String field, String value, int offset) {
		String s = "SELECT * FROM actions WHERE "+field+" = "+value+"  ORDER BY date_of_modification DESC LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String search_actionNoOffset(String field, String value) {
		String s = "SELECT * FROM actions WHERE "+field+" = "+value+"  ORDER BY date_of_modification DESC LIMIT 12 OFFSET ";
		return(s);
	}
	
	public String updateAgent(String valToUpdate, String id, String val) {
		String s = "UPDATE account SET "+valToUpdate+"="+val+" WHERE username = '"+id+"'";
		return(s);
	}
	
	public String updateRoom(String valToUpdate, String id, String val) {
		String s = "UPDATE room SET "+valToUpdate+"="+val+" WHERE room_num = '"+id+"'";
		return(s);
	}
	
	public String delete_user(String username) {
		String s = "DELETE FROM account WHERE username = '"+username+"'";
		return(s);
	}
	
	public String deleteRoom(String room_num) {
		String s = "DELETE FROM room WHERE room_num = "+room_num;
		return(s);
	}
	
	public String createUser(String account_role, String username, String name, String surname, String password, String email) {
		String s = "INSERT INTO account VALUES('"+account_role+"', '"+username+"', '"+name+"', '"+surname+"', '"+password+"', '"+email+"')";
		return(s);
	}
	
	public String createRoom(String room_num, String max_occupancy, String number_of_beds, String room_type) {
		String s = "INSERT INTO room VALUES ("+room_num+", "+max_occupancy+", "+number_of_beds+", 0, '"+room_type+"', 'false')";
		return(s);
	}
	
	public String createResident(int id, String room_num, String arrival_canada, String arrival_dev, String mode_of_arrival, String reunification, String bracelet, String uci, String dob, String citizenship, String surname, String name, String phone, String email, String gender, String family_composition, String primary_language, String other_language) {
		String s = "INSERT INTO resident (status, identifier, room_num, arrival_canada, mode_of_arrival, reunification, arrival_dev, bracelet, uci, dob, citizenship, surname, nom, phone, email, gender, family_composition, primary_language, other_language, image_path) VALUES('IN', "+id+", "+room_num+", "+arrival_canada+", '"+mode_of_arrival+"', "+reunification+", "+arrival_dev+", '"+bracelet+"', '"+uci+"', "+dob+", $$"+citizenship+"$$, $$"+surname+"$$, $$"+name+"$$, '"+phone+"', '"+email+"', '"+gender+"', '"+family_composition+"', $$"+primary_language+"$$, $$"+other_language+"$$, 'img/blankprofilepic.png')";
		return(s);
	}
	
	public String addResident(int id, String employed, String employer, String start_employment, String vehicle, String insurance, String registration, String education, String certifications, String work_experience, String initial_assessment, String destination, String medical, String ontario_works, String date_ow, String ime, String rpcd, String eapp, String boc, String healthcare, String work_permit, String date_wp, String legal, String bank, String irb_decision, String date_irb) {
		String s = "UPDATE resident SET employment = '"+employed+"', employer = $$"+employer+"$$, start_employment = "+start_employment+", vehicle = $$"+vehicle+"$$, insurance = '"+insurance+"', registration = '"+registration+"', education = '"+education+"', certifications = $$"+certifications+"$$, initial_mployment_assessment = $$"+work_experience+"$$, employment_assessment = $$"+initial_assessment+"$$, destination_preference = '"+destination+"', medical_condition = $$"+medical+"$$, ontario_works = '"+ontario_works+"', length_ow = "+date_ow+", ime = '"+ime+"', rpcd = '"+rpcd+"', eapp = '"+eapp+"', boc = '"+boc+"', healthcare = '"+healthcare+"', work_permit = '"+work_permit+"', wp_date = "+date_wp+", legal = '"+legal+"', bank = '"+bank+"', irb_decision = '"+irb_decision+"', length_irb = "+date_irb+", family_id = (SELECT MAX(family_id) FROM resident)+1 WHERE identifier = "+id;
		return(s);
	}
	public String updateResident(String id, String setting) {
		String s = "UPDATE resident SET "+setting+"WHERE identifier = "+id;
		return(s);
	}
	
	public String updateAction(Object agent, String action, LocalDate date, String time, String room_num, String name, String surname, int identifier, String original, String modified) {
		String s = "INSERT INTO actions VALUES ('"+agent+"', '"+action+"', '"+date+"', '"+time+"', "+room_num+", $$"+name+"$$, $$"+surname+"$$, "+identifier+", "+original+", "+modified+")";
		return(s);
	}
	
	public String findResident(String room_num, String nom) {
		String s = "SELECT identifier FROM resident WHERE (nom iLike $$%"+nom+"%$$ OR surname iLike $$%"+nom+"%$$) AND room_num = "+room_num;
		return(s);
	}
	
	public String findRoom(String room_num) {
		String s = "SELECT room_num FROM room WHERE room_num = "+room_num;
		return(s);
	}
	
	public String reportCreated(String identifier, String title, String path, Object agent, LocalDate date, String responded, String report, String date_of_incident, String report_type, String room_num, String names) {
		String s = "INSERT INTO reports VALUES ("+identifier+", DEFAULT, $$"+title+"$$, '"+path+"', '"+agent+"', '"+responded+"', '"+date+"', $$"+report+"$$, '"+date_of_incident+"', '"+report_type+"', '"+room_num+"', $$"+names+"$$)";
		return(s);
	}
	
	public String openReport(String identifier) {
		String s = "SELECT * FROM reports WHERE report_identifier = "+identifier;
		return(s);
	}
	
	public String getEmail(String name) {
		String s = "SELECT email FROM account WHERE nom = '"+name+"'";
		return(s);
	}
	
	public String opened(String identifier, String response) {
		String s = "UPDATE reports SET responded = 'Yes', response = $$"+response+"$$ WHERE report_identifier = "+identifier;
		return(s);
	}
	
	public String findReport(String title, String rooms, Object agent, String individuals) {
		String s = "SELECT report_identifier FROM reports WHERE title = $$"+title+"$$ AND room_num = '"+rooms+"' AND agent = '"+agent+"' AND nom = $$"+individuals+"$$";
		return(s);
	}
	
	public String getReports(String identifier) {
		String s = "SELECT report_ids FROM resident WHERE identifier = "+identifier;
		return(s);
	}
	
	public String updateReports(String identifiers, String reports) {
		String s = "UPDATE resident SET report_ids = '"+reports+"' WHERE identifier = "+identifiers;
		return(s);
	}
	
	public String ageAndGender() {
		String s = "WITH AgeData AS (SELECT nom, Gender, EXTRACT(YEAR FROM AGE(NOW(), DOB)) AS Age FROM resident) SELECT Gender, CASE WHEN Age < 18 THEN 'age<18' WHEN Age BETWEEN 18 AND 34 THEN 'age>17 AND age<35' WHEN Age BETWEEN 35 AND 54 THEN 'age>35 AND age<55' WHEN Age >= 55 THEN 'age>55' ELSE 'Unknown' END AS AgeGroup, COUNT(*) AS NumberOfPeople FROM AgeData GROUP BY Gender, AgeGroup ORDER BY Gender, AgeGroup";
		return(s);
	}
	
	public String ageTrial() {
		String s = "WITH AgeData AS (SELECT identifier, EXTRACT(YEAR FROM AGE(NOW(), DOB)) AS Age FROM resident) SELECT CASE WHEN Age<5 THEN 'age<5' WHEN Age BETWEEN 5 AND 17 THEN 'age>4 AND age<18' WHEN Age BETWEEN 18 AND 49 THEN 'age>17 AND age<50' WHEN Age BETWEEN 50 AND 64 THEN 'age>50 AND age<65' WHEN Age>= 65 THEN 'age>64' ELSE 'Unknown' END AS AgeGroup, COUNT(*) AS NumberOfPeople FROM AgeData GROUP BY AgeGroup ORDER BY AgeGroup;";
		return(s);
	}
	
	public String searchReports(String field, String value, int offset) {
		String s = "SELECT * FROM reports WHERE "+field+" "+value+"  ORDER BY date_of_writing DESC LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String searchReportsNoOffset(String field, String value) {
		String s = "SELECT * FROM reports WHERE "+field+" "+value+"  ORDER BY date_of_writing DESC LIMIT 12 OFFSET ";
		return(s);
	}
	
	public String countReports(String field, String value) {
		String s = "SELECT COUNT(*) FROM reports WHERE "+field+" "+value;
		return(s);
	}
	
	public String countReports(String counted) {
		String s = "SELECT COUNT(*) FROM resident WHERE "+counted;
		return(s);
	}
	
	public String openFamily(String id) {
		String s = "SELECT * FROM resident WHERE family_id = "+id;
		return(s);
	}
	
	public String countfamily(String id) {
		String s = "SELECT COUNT(identifier) FROM resident WHERE family_id = "+id;
		return(s);
	}
	
	public String countstats(String country) {
		String s = "SELECT COUNT(identifier) FROM resident WHERE "+country;
		return(s);
	}
	

	public String passport(String family_identifier) {
		String s = "SELECT * FROM store WHERE family_id = "+family_identifier+" ORDER BY date_of_visit DESC";
		return(s);
	}
	
	public String getInventory() {
		String s = "SELECT * FROM stock";
		return(s);
	}
	
	public String setInventory(String val) {
		String s = "UPDATE stock SET "+val;
		return(s);
	}
	
	public String nameAndFamilyID(String identifier) {
		String s = "SELECT nom, family_id, room_num FROM resident WHERE identifier = "+identifier;
		return(s);
	}
	
	public String createTransaction(String name, String identifier, String family_id, String room_num, java.sql.Date date) {
		String s = "INSERT INTO store (nom, identifier, family_id, date_of_visit, room_num, transaction_id, items) VALUES ($$"+name+"$$, "+identifier+", "+family_id+", '"+date+"', "+room_num+", DEFAULT, 'false')";
		return(s);
	}
	
	public String getLastTransaction() {
		String s = "SELECT MAX(transaction_id) FROM store";
		return(s);
	}
	
	public String addItems(String setting, String identifier) {
		String s = "UPDATE store SET "+setting+", items='true' WHERE transaction_id = "+identifier;
		return(s);
	}
	
	public String removeEmptyRows() {
		String s = "DELETE FROM store WHERE items='false'";
		return(s);
	}
	
	public String updateStock(String stock) {
		String s = "UPDATE stock SET "+stock;
		return(s);
	}
	
	public String getInfo(String identifier) {
		String s = "SELECT uci, bracelet, room_num, nom, surname, family_composition, arrival_dev, (CURRENT_DATE - dob)/365, agent, family_id, irb_decision FROM resident WHERE identifier = "+identifier;
		return(s);
	}
	
	public String insertMeeting(String uci, String bracelet, String room_num, String nom, String family_composition, String arrival_date, String adultORminor, String agent, String meeting_date, String meeting_type, String meeting_notes, String employment_status, String employment_type, String looking_job, String resume, String actions_values, String obstacles_values, String experience, String income, String transportation, String lodging, String moving_date, String housing_search, String housing_outside, String colocation, String housing_ob, String housing_support, String assigned_agent, String family_id, String irb_decision, String jobBank, String certificateIndustry, String wayOfFindingLodging) {
		String s = "INSERT INTO meeting VALUES ('"+uci+"', '"+bracelet+"', "+room_num+", $$"+nom+"$$, '"+family_composition+"', '"+arrival_date+"', '"+adultORminor+"', $$"+agent+"$$, '"+meeting_date+"', '"+meeting_type+"', $$"+meeting_notes+"$$, '"+employment_status+"', '"+employment_type+"', '"+looking_job+"', '"+resume+"', '"+actions_values+"', '"+obstacles_values+"', '"+experience+"', '"+income+"', '"+transportation+"', '"+lodging+"', "+moving_date+", '"+housing_search+"', '"+housing_outside+"', '"+colocation+"', '"+housing_ob+"', '"+housing_support+"', $$"+assigned_agent+"$$, "+family_id+", DEFAULT, '"+irb_decision+"', '"+jobBank+"', '"+certificateIndustry+"', '"+wayOfFindingLodging+"')";
		return(s);
	}
	
	public String generateMeeting(int offset) {
		String s = "SELECT * FROM resident LEFT JOIN meeting ON resident.family_id = meeting.family_id ORDER BY meeting_date DESC LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String searchMeeting(String field, String value) {
		String s = "SELECT * FROM resident LEFT JOIN meeting ON resident.family_id = meeting.family_id WHERE "+field+" "+value+"ORDER BY meeting_date DESC LIMIT 12 OFFSET 0";
		return(s);
	}
	
	public String searchMeetingNoOffset(String field, String value) {
		String s = "SELECT CASE WHEN (CURRENT_DATE - resident.dob)/365 < 18 THEN meeting1.meeting_date ELSE meeting2.meeting_Date END as selected_meeting_Date, resident.*, meeting1.*, meeting2.* FROM resident WHERE "+field+" "+value+" LEFT JOIN meeting AS meeting1 ON resident.family_id = meeting1.family_id AND (CURRENT_DATE - resident.dob)/365 < 18 LEFT JOIN meeting AS meeting2 ON resident.uci = meeting2.uci AND (CURRENT_DATE - resident.dob)/365 >=18 ORDER BY selected_meeting_Date DESC LIMIT12 OFFSET";
		return(s);
	}
	
	public String generateMeetingNoOffset() {
		String s = "SELECT CASE WHEN(CURRENT_DATE - resident.dob)/365 < 18 THEN meeting1.meeting_date ELSE meeting2.meeting_date END as selected_meeting_date, resident.*, meeting1.*, meeting2.* FROM resident LEFT JOIN meeting AS meeting1 ON resident.family_id = meeting1.family_id AND (CURRENT_DATE - resident.dob)/365 < 18 LEFT JOIN meeting AS meeting2 ON resident.uci = meeting2.uci AND (CURRENT_DATE - resident.dob)/365 >=18 ORDER BY selected_meeting_Date DESC LIMIT 12 OFFSET";
		return(s);
	}
	
	public String kidsMostRecentMeeting(String identifier) {
		String s = "SELECT meeting_date, meeting_agent FROM meeting WHERE meeting_date = ( SELECT MAX(meeting_date) FROM meeting WHERE family_id = "+identifier+") AND family_id = "+identifier;
		return(s);
	}
	
	public String mostRecentMeeting(String identifier) {
		String s = "SELECT meeting_date, meeting_agent FROM meeting WHERE meeting_date = ( SELECT MAX(meeting_date) FROM meeting WHERE uci = "+identifier+") AND uci = "+identifier;
		System.out.println(s);
		return(s);
	}
	
	public String countMeeting() {
		String s = "SELECT COUNT(identifier) FROM resident";
		return(s);
	}
	
	public String departFromStore(String identifier) {
		String s = "DELETE FROM store WHERE identifier = "+identifier;
		return(s);
	}
	
	public String countMeetingSearch(String field, String value) {
		String s = "SELECT COUNT(identifier) FROM resident WHERE "+field+" "+value;
		return(s);
	}
	
	public String openMeeting(String identifier) {
		String s = "SELECT * FROM meeting WHERE meeting_date = (SELECT MAX(meeting_date) FROM meeting WHERE identifier = "+identifier+") AND identifier = "+identifier;
		return(s);
	}
	
	public String openAssignedNoOffset(String name) {
		String s = "SELECT * FROM resident WHERE agent iLike $$%"+name+"%$$ LIMIT 12 OFFSET ";
		return(s);
	}
	
	public String findMeetings(String setters) {
		String s = "SELECT * FROM meeting "+setters;
		return(s);
	}
	
	public String getEducation(String uci) {
		String s = "SELECT education FROM resident WHERE uci = '"+uci+"'";
		return(s);
	}
	
	public String openAssignedResident(String name, int offset) {
		String s = "SELECT * FROM resident WHERE agent iLike '%"+name+"%' LIMIT 12 OFFSET "+offset;
		return(s);
	}
	
	public String countAssigned(String name) {
		String s = "SELECT COUNT(*) FROM resident WHERE agent iLike '%"+name+"%'";
		return(s);
	}
	
	public String generate_actionsNoOffset() {
		String s = "SELECT * FROM actions ORDER BY date_of_modification DESC LIMIT 12 OFFSET ";
		return(s);
	}
	
	public String getAgent(String identifier) {
		String s = "SELECT agent, family_id FROM resident WHERE identifier = "+identifier;
		return(s);
	}
	
	public String reassignAgent(String agent, String identifier) {
		String s = "UPDATE resident SET agent = $$"+agent+"$$ WHERE family_id = "+identifier;
		System.out.println(s);
		return(s);
	}
}
