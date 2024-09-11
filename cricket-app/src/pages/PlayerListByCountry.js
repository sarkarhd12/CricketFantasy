import React, { useState, useEffect, useCallback } from 'react';
import { Link } from 'react-router-dom';
import { usePlayerContext } from './PlayerContext';

const PlayerListByCountry = () => {
  const {
    teams,
    currentTeamIndex,
    setCurrentTeamIndex,
    countryName,
    setCountryName,
    updateCurrentTeam,
  } = usePlayerContext();

  const [players, setPlayers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState('');
  const [page, setPage] = useState(0);
  const [totalPages, setTotalPages] = useState(0);
  const [showTeam, setShowTeam] = useState(false);
  const [winner, setWinner] = useState(null);
  const [winningPercentage, setWinningPercentage] = useState(null); // New state for winning percentage
  const [showModal, setShowModal] = useState(false); // New state for modal visibility

  const fetchPlayersByCountry = useCallback(async () => {
    setLoading(true);
    setError('');
    try {
      const encodedCountryName = encodeURIComponent(countryName.trim());
      const response = await fetch(
        `http://localhost:8080/v1/api/findByCountry?country=${encodedCountryName}&page=${page}&size=10`
      );
      if (response.ok) {
        const data = await response.json();
        if (data.content && data.content.length > 0) {
          setPlayers(data.content);
          setTotalPages(data.totalPages || 0);
        } else {
          setPlayers([]);
          setTotalPages(0);
          setError('No players found.');
        }
      } else {
        setError('Failed to fetch players.');
        setPlayers([]);
      }
    } catch (error) {
      setError('Error fetching players. Please try again.');
      setPlayers([]);
    } finally {
      setLoading(false);
    }
  }, [countryName, page]);

  useEffect(() => {
    if (countryName) {
      fetchPlayersByCountry();
    }
  }, [countryName, page, fetchPlayersByCountry]);

  const handleSubmitCountry = (e) => {
    e.preventDefault();
    if (countryName.trim()) {
      setPage(0);
      fetchPlayersByCountry();
    }
  };

  const addPlayerToTeam = (player) => {
    const currentTeam = teams[currentTeamIndex];

    if (currentTeam.length > 0) {
      if (currentTeam[0].countryName !== player.countryName) {
        alert(`All players in a team must be from the same country: ${currentTeam[0].countryName}`);
        return;
      }
    }

    if (currentTeam.length < 11 && !currentTeam.some((p) => p.player === player.player)) {
      updateCurrentTeam([...currentTeam, player]);
    }
  };

  const removePlayerFromTeam = (player) => {
    const currentTeam = teams[currentTeamIndex];
    updateCurrentTeam(currentTeam.filter((p) => p.player !== player.player));
  };

  const toggleTeamView = () => {
    setShowTeam(!showTeam);
  };

  const switchTeam = () => {
    setCurrentTeamIndex(currentTeamIndex === 0 ? 1 : 0);
  };

  const playGame = async () => {
    if (teams[0].length !== 11 || teams[1].length !== 11) {
      alert('Both teams need to have 11 players each to play the game!');
      return;
    }

    try {
      const response = await fetch('http://localhost:8080/v1/api/determineWinner', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ team1: teams[0], team2: teams[1] }),
      });

      if (response.ok) {
        const result = await response.json();
        setWinner(result.winner);
        setWinningPercentage(result.winningPercentage); // Assuming the backend sends winning percentage
        setShowModal(true); // Show the modal
      } else {
        alert('Error determining winner. Please try again.');
        setWinner(null);
        setWinningPercentage(null);
      }
    } catch (error) {
      alert('Error sending data to backend. Please check your connection.');
      setWinner(null);
      setWinningPercentage(null);
    }
  };

  const handleCloseModal = () => {
    setShowModal(false);
  };

  const handlePlayAgain = () => {
    handleCloseModal();
    window.location.reload(); // Refresh the page
  };

  return (
    <div className="container mx-auto p-4 pt-20">
      <h2 className="text-2xl font-bold mb-4">Search Players by Country</h2>

      <form onSubmit={handleSubmitCountry} className="flex mb-6">
        <input
          type="text"
          value={countryName}
          onChange={(e) => setCountryName(e.target.value)}
          placeholder="Enter country name"
          className="p-2 border border-gray-300 rounded mr-2"
          required
        />
        <button type="submit" className="bg-blue-500 text-white py-2 px-4 rounded">
          Search
        </button>
      </form>

      {loading && <div>Loading...</div>}
      {error && <div className="text-red-500 mb-4">{error}</div>}

      {players.length > 0 && (
        <div>
          <div className="grid grid-cols-2 sm:grid-cols-3 lg:grid-cols-6 gap-4 mb-4">
            {players.map((player) => (
              <div
                key={player.player}
                className="border p-4 rounded-lg shadow-lg flex flex-col justify-between"
              >
                <Link
                  to={`/player/${encodeURIComponent(player.player)}`}
                  className="text-xl font-bold text-blue-500 hover:underline"
                >
                  {player.player}
                </Link>
                <p>{player.countryName}</p>
                <div className="mt-4">
                  {teams[currentTeamIndex].find((p) => p.player === player.player) ? (
                    <button
                      onClick={() => removePlayerFromTeam(player)}
                      className="bg-red-500 text-white py-1 px-3 rounded"
                    >
                      Remove
                    </button>
                  ) : (
                    <button
                      onClick={() => addPlayerToTeam(player)}
                      className="bg-green-500 text-white py-1 px-3 rounded"
                    >
                      Add
                    </button>
                  )}
                </div>
              </div>
            ))}
          </div>

          <div className="flex justify-between mt-4">
            <button
              className="bg-gray-500 text-white py-2 px-4 rounded"
              onClick={() => setPage((prevPage) => Math.max(prevPage - 1, 0))}
              disabled={page === 0}
            >
              Previous
            </button>
            <span>
              Page {page + 1} of {totalPages}
            </span>
            <button
              className="bg-gray-500 text-white py-2 px-4 rounded"
              onClick={() => setPage((prevPage) => Math.min(prevPage + 1, totalPages - 1))}
              disabled={page >= totalPages - 1}
            >
              Next
            </button>
          </div>
        </div>
      )}

      {teams[currentTeamIndex].length > 0 && (
        <div className="mt-6">
          <button
            onClick={toggleTeamView}
            className="bg-blue-500 text-white py-2 px-4 rounded"
          >
            {showTeam ? 'Hide Team' : 'See Team'}
          </button>
        </div>
      )}

      {showTeam && teams[currentTeamIndex].length > 0 && (
        <div className="mt-6">
          <h3 className="text-2xl font-bold mb-4">Your Selected Team</h3>
          <ul className="list-disc pl-6">
            {teams[currentTeamIndex].map((player) => (
              <li key={player.player}>{player.player} ({player.countryName})</li>
            ))}
          </ul>
          <button
            onClick={switchTeam}
            className="mt-4 bg-yellow-500 text-white py-2 px-4 rounded"
          >
            Switch Team
          </button>
          <button
            onClick={playGame}
            className="mt-4 ml-4 bg-purple-500 text-white py-2 px-4 rounded"
          >
            Play Game
          </button>
        </div>
      )}

      {showModal && (
        <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-center">
          <div className="bg-white p-6 rounded-lg shadow-lg max-w-sm mx-auto">
            <h3 className="text-2xl font-bold mb-4">Game Result</h3>
            <div className="flex justify-end">
              <button
                onClick={handleCloseModal}
                className="bg-gray-500 text-white py-2 px-4 rounded mr-2"
              >
                Close
              </button>
              <button
                onClick={handlePlayAgain}
                className="bg-blue-500 text-white py-2 px-4 rounded"
              >
                Play Again
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
};

export default PlayerListByCountry;
