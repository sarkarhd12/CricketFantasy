import React, { useState } from 'react';

const FantasyTeam = () => {
    const [countryName, setCountryName] = useState('');
    const [batsmen, setBatsmen] = useState([]);
    const [bowlers, setBowlers] = useState([]);
    const [isTeamCreated, setIsTeamCreated] = useState(false);

    const handleAddPlayer = async (e) => {
        e.preventDefault();
        if (!countryName) {
            alert('Please enter a country name.');
            return;
        }

        try {
            const response = await fetch(`http://localhost:8080/v1/api/team?countryName=${countryName}`);
            if (!response.ok) {
                throw new Error('Failed to fetch player data');
            }
            const data = await response.json();
            console.log('Fetched data:', data); // Log the data

            // Extract batsmen and bowlers from FantasyTeamDto
            setBatsmen(data.batsmen || []);
            setBowlers(data.bowlers || []);
            setIsTeamCreated(true);
        } catch (error) {
            console.error('Error fetching player data:', error);
        }
    };

    return (
        <div className="p-4">
            <h1 className="text-3xl font-bold mb-4">Create Your Fantasy Team</h1>
            <form onSubmit={handleAddPlayer} className="mb-4">
                <div className="mb-4">
                    <label htmlFor="countryName" className="block text-lg font-medium">Country Name:</label>
                    <input
                        type="text"
                        id="countryName"
                        value={countryName}
                        onChange={(e) => setCountryName(e.target.value)}
                        className="mt-1 p-2 border border-gray-300 rounded"
                        required
                    />
                </div>
                <button type="submit" className="bg-blue-500 text-white p-2 rounded">Fetch Players</button>
            </form>

            {isTeamCreated && (
                <div>
                    <h2 className="text-2xl font-bold mb-4">Batsmen from {countryName}</h2>
                    <div className="overflow-x-auto">
                        <table className="min-w-full bg-white border border-gray-300 rounded-lg shadow-md">
                            <thead>
                                <tr>
                                    <th className="py-2 px-4 border-b text-left">Batsman Name</th>
                                    <th className="py-2 px-4 border-b text-left">Matches</th>
                                </tr>
                            </thead>
                            <tbody>
                                {batsmen.length > 0 ? (
                                    batsmen.map((batsman) => (
                                        <tr key={batsman.player}> {/* Use player name as key */}
                                            <td className="py-2 px-4 border-b">{batsman.player}</td>
                                            <td className="py-2 px-4 border-b">{batsman.matches}</td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan="2" className="py-2 px-4 text-center">No batsmen found</td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>

                    <h2 className="text-2xl font-bold mb-4">Bowlers from {countryName}</h2>
                    <div className="overflow-x-auto">
                        <table className="min-w-full bg-white border border-gray-300 rounded-lg shadow-md">
                            <thead>
                                <tr>
                                    <th className="py-2 px-4 border-b text-left">Bowler Name</th>
                                    <th className="py-2 px-4 border-b text-left">Matches</th>
                                </tr>
                            </thead>
                            <tbody>
                                {bowlers.length > 0 ? (
                                    bowlers.map((bowler) => (
                                        <tr key={bowler.player}> {/* Use player name as key */}
                                            <td className="py-2 px-4 border-b">{bowler.player}</td>
                                            <td className="py-2 px-4 border-b">{bowler.matches}</td>
                                        </tr>
                                    ))
                                ) : (
                                    <tr>
                                        <td colSpan="2" className="py-2 px-4 text-center">No bowlers found</td>
                                    </tr>
                                )}
                            </tbody>
                        </table>
                    </div>
                </div>
            )}
        </div>
    );
};

export default FantasyTeam;
