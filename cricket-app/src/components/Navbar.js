import React, { useState } from 'react';
import { Link } from 'react-router-dom';

const Navbar = () => {
    const [searchQuery, setSearchQuery] = useState('');

    const handleSearch = (e) => {
        e.preventDefault();
        // Handle the search logic here
        console.log('Search query:', searchQuery);
    };

    return (
        <nav className="bg-gray-800 p-4">
            <div className="container mx-auto flex items-center">
                {/* Logo section */}
                <div className="flex-1 flex justify-center">
                    <Link to="/" className="text-white text-2xl font-bold hover:text-gray-400">
                        Cricket Predictor
                    </Link>
                </div>
                
                {/* Search form */}
                <form onSubmit={handleSearch} className="flex items-center space-x-2 mr-8a">
                    <input
                        type="text"
                        value={searchQuery}
                        onChange={(e) => setSearchQuery(e.target.value)}
                        placeholder="Search..."
                        className="p-2 rounded border border-gray-300"
                    />
                    <button
                        type="submit"
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
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
