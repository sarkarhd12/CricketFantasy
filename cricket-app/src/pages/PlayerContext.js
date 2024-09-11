import React, { createContext, useContext, useState } from 'react';

// Create the context
const PlayerContext = createContext();

// Create a provider component
export const PlayerProvider = ({ children }) => {
  const [teams, setTeams] = useState([[], []]); // Only allow exactly two teams
  const [currentTeamIndex, setCurrentTeamIndex] = useState(0);
  const [countryName, setCountryName] = useState('');

  const updateCurrentTeam = (newTeam) => {
    const updatedTeams = [...teams];
    updatedTeams[currentTeamIndex] = newTeam;
    setTeams(updatedTeams);
  };

  return (
    <PlayerContext.Provider value={{
      teams,
      setTeams,
      currentTeamIndex,
      setCurrentTeamIndex,
      countryName,
      setCountryName,
      updateCurrentTeam,
    }}>
      {children}
    </PlayerContext.Provider>
  );
};

export const usePlayerContext = () => useContext(PlayerContext);