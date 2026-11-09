#!/bin/bash

if [[ $1 == "test" ]]
then
  PSQL="psql --username=postgres --dbname=worldcup_test -t --no-align -c"
else
  PSQL="psql --username=freecodecamp --dbname=worldcup -t --no-align -c"
fi

# Do not change code above this line. Use the PSQL variable above to query your database.

# Clear out existing data before running to prevent duplicate primary key errors on rerun
echo $($PSQL "TRUNCATE games, teams")

# Read the CSV file line by line, splitting by comma into specific variables
cat games.csv | while IFS="," read YEAR ROUND WINNER OPPONENT WINNER_GOALS OPPONENT_GOALS
do
  # STRATEGY A: Skip the header row by checking if the first column equals the literal text label
  if [[ $YEAR != "year" ]]
  then

    # ==========================================
    # 1. HANDLE THE WINNING TEAM
    # ==========================================

    # Check if the winning team already exists in the teams table
    WINNER_ID=$($PSQL "SELECT team_id FROM teams WHERE name='$WINNER'")

    # If the ID comes back empty (-z checks for an empty string), it means the team is unique
    if [[ -z $WINNER_ID ]]
    then
      # Insert the unique team into the database
      INSERT_WINNER_RESULT=$($PSQL "INSERT INTO teams(name) VALUES('$WINNER')")

      # Print status message to terminal
      if [[ $INSERT_WINNER_RESULT == "INSERT 0 1" ]]
      then
        echo "Inserted into teams: $WINNER"
      fi

      # Retrieve the newly generated ID for later use in the games table
      WINNER_ID=$($PSQL "SELECT team_id FROM teams WHERE name='$WINNER'")
    fi

    # ==========================================
    # 2. HANDLE THE OPPONENT TEAM
    # ==========================================

    # Check if the opponent team already exists in the teams table
    OPPONENT_ID=$($PSQL "SELECT team_id FROM teams WHERE name='$OPPONENT'")

    # If the ID comes back empty, insert the team
    if [[ -z $OPPONENT_ID ]]
    then
      INSERT_OPPONENT_RESULT=$($PSQL "INSERT INTO teams(name) VALUES('$OPPONENT')")

      if [[ $INSERT_OPPONENT_RESULT == "INSERT 0 1" ]]
      then
        echo "Inserted into teams: $OPPONENT"
      fi

      # Retrieve the newly generated ID
      OPPONENT_ID=$($PSQL "SELECT team_id FROM teams WHERE name='$OPPONENT'")
    fi

    # ==========================================
    # 3. INSERT THE GAME LOG
    # ==========================================

    # Now that we guaranteed both IDs exist, link them together in the games table
    INSERT_GAME_RESULT=$($PSQL "INSERT INTO games(year, round, winner_id, opponent_id, winner_goals, opponent_goals) VALUES($YEAR, '$ROUND', $WINNER_ID, $OPPONENT_ID, $WINNER_GOALS, $OPPONENT_GOALS)")

    if [[ $INSERT_GAME_RESULT == "INSERT 0 1" ]]
    then
      echo "Inserted into games: $YEAR - $ROUND"
    fi

  fi
done