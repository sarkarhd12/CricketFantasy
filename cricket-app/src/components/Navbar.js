import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';

const Navbar = () => {
    const [searchQuery, setSearchQuery] = useState('');
    const [searchResults, setSearchResults] = useState([]);
    const [showModal, setShowModal] = useState(false);
    const [noPlayerFound, setNoPlayerFound] = useState(false);
    const [loading, setLoading] = useState(false);

    const location = useLocation();

    useEffect(() => {
        // Close the modal and clear search results when the location changes
        setShowModal(false);
        setSearchQuery('');
        setSearchResults([]);
        setNoPlayerFound(false);
    }, [location]);

    useEffect(() => {
        const fetchResults = async () => {
            if (searchQuery.trim() === '') {
                setSearchResults([]);
                return;
            }

            setLoading(true);

            try {
                const response = await fetch(`http://localhost:8080/v1/api/search?query=${encodeURIComponent(searchQuery)}`);
                if (response.ok) {
                    const data = await response.json();
                    console.log('Data:', data);
                    if (data.length === 0) {
                        setNoPlayerFound(true);
                        setSearchResults([]);
                    } else {
                        setNoPlayerFound(false);
                        setSearchResults(data.map(player => ({
                            playerName: player.player || "Unknown",
                            country: player.countryName || "Unknown",
                        })));
                    }
                } else {
                    setNoPlayerFound(true);
                    setSearchResults([]);
                }
            } catch (error) {
                console.error('Error fetching data:', error);
                setNoPlayerFound(true);
                setSearchResults([]);
            } finally {
                setLoading(false);
            }
        };

        const debounceFetch = setTimeout(fetchResults, 300); // Add debounce to avoid excessive API calls

        return () => clearTimeout(debounceFetch); // Cleanup timeout on component unmount or searchQuery change
    }, [searchQuery]);

    const handleSearchChange = (e) => {
        setSearchQuery(e.target.value);
        if (e.target.value.trim() === '') {
            setShowModal(false); // Hide modal if query is empty
        } else {
            setShowModal(true); // Show modal if query is not empty
        }
    };

    const closeModal = () => {
        setShowModal(false);
        setSearchResults([]);
        setSearchQuery('');
        setNoPlayerFound(false);
    };

    return (
        <div>
            <nav className="bg-gray-800 p-4 fixed top-0 w-full z-50">
                <div className="container mx-auto flex items-center">
                    {/* Logo section */}
                    <div className="flex-1 flex justify-center">
                        <Link to="/" className="text-white text-2xl font-bold hover:text-gray-400">
                            Cricket Predictor
                        </Link>
                    </div>

                    {/* Search form */}
                    <form className="flex items-center space-x-2 mr-8" onSubmit={(e) => e.preventDefault()}>
                        <input
                            type="text"
                            value={searchQuery}
                            onChange={handleSearchChange}
                            placeholder="Search by player name..."
                            className="p-2 rounded border border-gray-300"
                        />
                        <button
                            type="button"
                            onClick={() => setShowModal(true)}
                            className="bg-blue-500 text-white p-2 rounded hover:bg-blue-600"
                        >
                            Search
                        </button>
                    </form>

                    {/* Navigation links */}
                    <div className="flex space-x-4 ml-2">
                        <Link to="/" className="text-white hover:text-gray-400">Home</Link>
                        <Link to="/contact" className="text-white hover:text-gray-400">Contact</Link>
                        <Link to="/blogs" className="text-white hover:text-gray-400">Blogs</Link>
                        <Link to="/fantasy" className="text-white hover:text-gray-400">Create Fantasy Team</Link>
                        <Link to="/team" className="text-white hover:text-gray-400">Create Team</Link>

                    </div>
                </div>
            </nav>

            {/* Modal for displaying search results */}
            {showModal && (
                <div className="fixed inset-0 bg-gray-900 bg-opacity-50 flex justify-center items-start pt-20" style={{ zIndex: 9999 }}>
                    <div className="bg-white p-6 rounded-lg shadow-lg max-w-lg w-full mx-4 max-h-screen overflow-auto">
                        <button
                            onClick={closeModal}
                            className="bg-red-500 text-white px-3 py-1 rounded hover:bg-red-600 mb-4"
                        >
                            Close
                        </button>
                        <div>
                            {loading && <div className="text-center">Loading...</div>}
                            {noPlayerFound ? (
                                <div className="text-red-500 text-center">No player found with the name "{searchQuery}"</div>
                            ) : (
                                <div>
                                    <h2 className="text-gray-800 text-xl font-bold mb-4">Search Results:</h2>
                                    <ul className="list-disc pl-5">
                                        {searchResults.length === 0 ? (
                                            <p className="text-center">No results to display.</p>
                                        ) : (
                                            searchResults.map((player, index) => (
                                                <li key={index} className="mb-2">
                                                    <Link
                                                        to={`/player/${encodeURIComponent(player.playerName)}`}
                                                        className="text-blue-600 hover:underline"
                                                    >
                                                        {player.playerName} - {player.country}
                                                    </Link>
                                                </li>
                                            ))
                                        )}
                                    </ul>
                                </div>
                            )}
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Navbar;

