import React, { useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import playerimage from "../HomeImage/playerimage.avif";

const PlayerDetail = () => {
    const { name } = useParams();
    const [player, setPlayer] = useState(null);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const fetchPlayerDetails = async () => {
            try {
                const response = await fetch(`http://localhost:8080/v1/api/find?name=${name}`);
                if (response.ok) {
                    const data = await response.json();
                    console.log('API Data:', data); // Log API response

                    if (Array.isArray(data) && data.length > 0) {
                        setPlayer(data[0]); // Assuming data is an array with player details
                    } else {
                        setPlayer(null);
                    }
                } else {
                    console.error('Failed to fetch player details');
                    setPlayer(null);
                }
            } catch (error) {
                console.error('Error fetching data:', error);
                setPlayer(null);
            } finally {
                setLoading(false);
            }
        };

        fetchPlayerDetails();
    }, [name]);

    if (loading) {
        return <div className="container mx-auto p-4 pt-20">Loading...</div>;
    }

    return (
        <div className="container mx-auto p-4 pt-20"> {/* Added padding-top to push below navbar */}
            {player ? (
                <div className="flex flex-col md:flex-row items-center md:items-start justify-center">
                    {/* Player Image */}
                    <div className="md:w-1/3 w-3/4 mb-4 md:mb-0">
                        <img
                            src={playerimage} // Path to player image
                            alt={player.player || 'Player'}
                            className="w-full h-auto rounded-lg shadow-lg"
                        />
                    </div>
                    {/* Player Details */}
                    <div className="md:w-2/3 md:ml-6 w-full mt-4 md:mt-0 text-center md:text-left">
                        <h1 className="text-3xl font-bold mb-4">{player.player || 'No name'}</h1>
                        <p><strong>Country:</strong> {player.countryName || 'Unknown'}</p>
                        <p><strong>Matches:</strong> {player.matches || 0}</p>
                        <p><strong>Innings:</strong> {player.innings || 0}</p>
                        <p><strong>Runs:</strong> {player.runs || 0}</p>
                        <p><strong>Highest Score:</strong> {player.highestScore || '0'}</p>
                        <p><strong>Batting Average:</strong> {player.battingAverage || 0}</p>
                        <p><strong>Ball Faced:</strong> {player.ballFaced || 0}</p>
                        <p><strong>Batting Strike Rate:</strong> {player.battingStrickRate || 0}</p>
                        <p><strong>Hundreds:</strong> {player.hundreds || 0}</p>
                        <p><strong>Fifties:</strong> {player.fifties || 0}</p>
                        <p><strong>Ducks:</strong> {player.ducks || 0}</p>
                    </div>
                </div>
            ) : (
                <div className="text-red-500 text-center">No player found with the name "{name}".</div>
            )}
        </div>
    );
};

export default PlayerDetail;