public static void insertTables(String name,String password)
	{
		Statement statement=null;
		Connection connection=null;
		try 
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/"+name, "postgres",
					password);
			statement = connection.createStatement();
			if(name.equals("schema1"))
			{
				statement.executeUpdate(
						"CREATE TABLE department(dep_name VARCHAR(20) PRIMARY KEY, building NUMERIC, budget NUMERIC);"
						+ "CREATE TABLE instructor(ID INT PRIMARY KEY, name VARCHAR(20), salary NUMERIC, dep_name VARCHAR(20) REFERENCES department);\n" + 
						"CREATE TABLE classroom(building INT, room_number INT, capacity NUMERIC, PRIMARY KEY(building, room_number), UNIQUE(building), UNIQUE(room_number));\n" + 
						"CREATE TABLE time_slot(id INT PRIMARY KEY, day VARCHAR(10), start time, end_time time);\n" + 
						"CREATE TABLE student(id INT PRIMARY KEY, name VARCHAR(20), tot_credit INT, department VARCHAR(20) REFERENCES department, advisor_id INT REFERENCES instructor);\n" + 
						"CREATE TABLE course(course_id INT PRIMARY KEY, title VARCHAR(20), credits INT, department VARCHAR(20) REFERENCES department);\n" + 
						"CREATE TABLE pre_requiste(course_id INT, prereq_id INT,PRIMARY KEY(course_id, prereq_id));\n" + 
						"CREATE TABLE section(section_id INT PRIMARY KEY, semester INT, year INT, instructor_id INT REFERENCES instructor, course_id INT REFERENCES course,classroom_building INT REFERENCES classroom(building), classroom_room_no INT REFERENCES classroom(room_number));\n" + 
						"CREATE TABLE takes(student_id INT REFERENCES student, section_id INT REFERENCES section, grade real, PRIMARY KEY(student_id, section_id));\n" + 
						"CREATE TABLE section_time(time_slot INT REFERENCES time_slot, section_id INT REFERENCES section, PRIMARY KEY(time_slot, section_id));");
			}
			else if(name.equals("schema2"))
				statement.executeUpdate(
						"CREATE TABLE Employee(Fname CHAR(20), Minit CHAR(10), Lname CHAR(20), ssn INT PRIMARY KEY, Bdate date, address CHAR(20), sex CHARACTER(1), salary INT, Super_snn INT REFERENCES Employee(ssn), dno INT);\n" + 
						"CREATE TABLE Department(Dname CHAR(20), Dnumber INT PRIMARY KEY, Mgr_snn int REFERENCES employee, Mgr_start_date date );\n" + 
						"CREATE TABLE Dept_locations(Dnumber integer REFERENCES Department, Dlocation CHAR(20), PRIMARY KEY(Dnumber,Dlocation));\n" + 
						"CREATE TABLE Project(Pname CHAR(20), Pnumber INT PRIMARY KEY, Plocation CHAR(50), Dnumber INT REFERENCES Department);\n" + 
						"CREATE TABLE Works_on(Essn int REFERENCES Employee, Pno int REFERENCES Project, Hours int, PRIMARY KEY(Essn,Pno));\n" + 
						"CREATE TABLE Dependent(Essn INT REFERENCES Employee, Dependent_name CHAR(20), sex CHARACTER(1), Bdate date, Relationship CHAR(20), PRIMARY KEY(Essn, Dependent_name));\n" + 
						"");
			else if(name.equals("schema3"))
				statement.executeUpdate(
						"CREATE TABLE Sailors(sid INT PRIMARY KEY, sname CHAR(20), rating INT, age REAL);\n" + 
						"CREATE TABLE Boat(bid INT PRIMARY KEY, bname CHAR(20), color CHAR(10));\n" + 
						"CREATE TABLE Reserves(sid INT REFERENCES Sailors, bid INT REFERENCES Boat, day date, PRIMARY KEY(sid,bid));");
			else if(name.equals("schema4"))
				statement.executeUpdate(
						"CREATE TABLE Movie(mov_id INT PRIMARY KEY, mov_title CHAR(50), mov_year INT, mov_time INT, mov_lang CHAR(50), mov_dt_rel date, mov_rel_country CHAR(5));\n" + 
						"CREATE TABLE Reviewer(rev_id INT PRIMARY KEY, rev_name CHAR(30));\n" + 
						"CREATE TABLE Genres(gen_id INT PRIMARY KEY, gen_title CHAR(20));\n" + 
						"CREATE TABLE Actor(act_id INT PRIMARY KEY, act_fname CHAR(20), act_lname CHAR(20), act_gender CHAR(1));\n" + 
						"CREATE TABLE Director(dir_id INT PRIMARY KEY, dir_fname CHAR(20), dir_lname CHAR(20));\n" + 
						"CREATE TABLE movie_direction(dir_id INT REFERENCES Director, mov_id INT REFERENCES Movie, PRIMARY KEY(dir_id,mov_id));\n" + 
						"CREATE TABLE movie_cast(act_id INT REFERENCES Actor, mov_id INT REFERENCES Movie, role CHAR(30), PRIMARY KEY(act_id, mov_id));\n" + 
						"CREATE TABLE movie_genres(mov_id INT REFERENCES Movie, gen_id INT REFERENCES genres, PRIMARY KEY(mov_id,gen_id));\n" + 
						"CREATE TABLE Rating(mov_id INT REFERENCES Movie, rev_id INT REFERENCES Reviewer, rev_stars INT, num_o_ratings INT, PRIMARY KEY(mov_id,rev_id));\n" + 
						"");
			else if(name.equals("schema5"))
				statement.executeUpdate(
						"CREATE TABLE playing_position(position_id NUMERIC PRIMARY KEY, position_desc VARCHAR(15));\n" + 
						"CREATE TABLE soccer_country(country_id NUMERIC PRIMARY KEY, country_abbr VARCHAR(4), country_name VARCHAR(40));\n" + 
						"CREATE TABLE player_mast(player_id NUMERIC PRIMARY KEY, team_id NUMERIC REFERENCES soccer_country, jersey_no NUMERIC, player_name VARCHAR(40), posi_to_play NUMERIC REFERENCES playing_position, dt_of_bir date, age NUMERIC, playing_club VARCHAR(40));																			  \n" + 
						"CREATE TABLE coach_mast(coach_id NUMERIC PRIMARY KEY, coach_name VARCHAR(40));\n" + 
						"CREATE TABLE soccer_city(city_id NUMERIC PRIMARY KEY, city VARCHAR(25), country_id NUMERIC REFERENCES soccer_country);\n" + 
						"CREATE TABLE soccer_venue(venue_id NUMERIC PRIMARY KEY, venue_name VARCHAR(30), city_id NUMERIC REFERENCES soccer_city, aud_capacity NUMERIC);\n" + 
						"CREATE TABLE refree_mast(referee_id NUMERIC PRIMARY KEY, referee_name VARCHAR(40), country_id NUMERIC REFERENCES soccer_country);\n" + 
						"CREATE TABLE asst_referee_mast(ass_ref_id NUMERIC PRIMARY KEY, ass_ref_name VARCHAR(40), country_id NUMERIC REFERENCES soccer_country);\n" + 
						"CREATE TABLE match_mast(match_no NUMERIC PRIMARY KEY, play_stage CHARACTER(1), play_date date, results CHARACTER(5), decided_by CHARACTER(1), goal_score CHARACTER(5), venue_id NUMERIC REFERENCES soccer_venue, referee_id NUMERIC REFERENCES refree_mast, audonce NUMERIC, plr_of_match NUMERIC REFERENCES player_mast, stop1_sec NUMERIC, stop2_sec NUMERIC);\n" + 
						"CREATE TABLE match_details(match_no NUMERIC PRIMARY KEY, play_stage CHARACTER(1), team_id NUMERIC REFERENCES soccer_country, win_lose CHARACTER(1), decided_by VARCHAR(1), goal_score NUMERIC, penalty_score NUMERIC, ass_ref NUMERIC REFERENCES asst_referee_mast, player_gk NUMERIC REFERENCES player_mast);\n" + 
						"CREATE TABLE team_coaches(team_id NUMERIC REFERENCES soccer_country, coach_id NUMERIC REFERENCES coach_mast, PRIMARY KEY(team_id, coach_id));\n" + 
						"CREATE TABLE soccer_team(team_id NUMERIC REFERENCES soccer_country, team_group CHARACTER(1), match_played NUMERIC, won NUMERIC, draw NUMERIC, lost NUMERIC, goal_for NUMERIC, goal_agnst NUMERIC, goal_diff NUMERIC, points NUMERIC, group_position NUMERIC, PRIMARY KEY(team_id));\n" + 
						"CREATE TABLE player_in_out(match_no NUMERIC REFERENCES match_mast, team_id NUMERIC REFERENCES soccer_country, player_id NUMERIC REFERENCES player_mast, in_out CHARACTER(1), time_in_out NUMERIC, play_schedule CHARACTER(2), play_half NUMERIC, PRIMARY KEY(match_no, team_id, player_id));\n" + 
						"CREATE TABLE goal_details(goal_id NUMERIC PRIMARY KEY, match_no NUMERIC, player_id NUMERIC REFERENCES player_mast, team_id NUMERIC REFERENCES soccer_country,goal_time NUMERIC, goal_type CHARACTER(1), play_stage CHARACTER(1), goal_schedule CHARACTER(2),goal_half NUMERIC );\n" + 
						"CREATE TABLE penalty_shootout(kick_id NUMERIC PRIMARY KEY, match_no NUMERIC REFERENCES match_mast, team_id NUMERIC REFERENCES soccer_country, player_id NUMERIC REFERENCES player_mast, score_goal VARCHAR(1), kick_no NUMERIC);\n" + 
						"CREATE TABLE match_captain(match_no NUMERIC REFERENCES match_mast, team_id NUMERIC REFERENCES soccer_country, player_gk NUMERIC REFERENCES player_mast, PRIMARY KEY(match_no, team_id, player_gk));\n" + 
						"CREATE TABLE penalty_gk(match_no NUMERIC REFERENCES match_mast, team_id NUMERIC REFERENCES soccer_country, player_gk NUMERIC REFERENCES player_mast, PRIMARY KEY(match_no, team_id, player_gk));\n" + 
						"CREATE TABLE player_booked(match_no NUMERIC REFERENCES match_mast, team_id NUMERIC REFERENCES soccer_country, player_id NUMERIC REFERENCES player_mast, booking_time VARCHAR(40), sent_off CHARACTER(1), play_schedule CHARACTER(2), play_half NUMERIC, PRIMARY KEY(match_no, team_id, player_id));\n"
						);
			else
				System.out.println("Invalid schema name please check the name you wrote.");
		connection.close();
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
	}
	public static void refreshSchema(String name,String password)
	{
		Statement statement=null;
		Connection connection=null;
		//Connect to server
		try 
		{
			connection = DriverManager.getConnection(
					"jdbc:postgresql://127.0.0.1:5432/postgres", "postgres",
					password);
		}
		catch (SQLException e) {System.err.println("Failed to establish connection to postgres local server.");}
		//delete old database and create new one
		try 
		{
			statement = connection.createStatement();
			statement.executeUpdate("DROP DATABASE "+name);
			statement.executeUpdate("CREATE DATABASE "+name);
		}
		catch (SQLException e) //if the old one doesn't exist create it and warn if already open database
		{
			if(e.getSQLState().equals("3D000"))
			{
				System.err.println("The Table "+name+" doesn't exist, new DataBase with this name will be created");
				
				try {statement.executeUpdate("CREATE DATABASE "+name);} catch (SQLException e1) {e1.printStackTrace();}
			}
			else if(e.getSQLState().equals("55006"))
			{
				System.err.println("Please go to pgAdmin and right click on "+ name+" then disconnect before trying to run this code.");				
				return;
			}
		}
		//insert the tables to fresh database and close the connection
		try {connection.close();}
		catch (SQLException e){System.err.println("Failed to Close Connection");}
		insertTables(name,password);
	}
