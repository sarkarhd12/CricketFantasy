import React from 'react';
import backgroundImage from '../HomeImage/backgroundimage.png';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <main className="flex-grow">
            {/* Full-Screen Image Section */}
            <div
                className="relative w-full h-screen bg-cover bg-center"
                style={{
                    backgroundImage: `url(${backgroundImage})`,
                }}
            >
                <div className="absolute inset-0 bg-black bg-opacity-50 flex flex-col justify-center items-center text-white p-4">
                    <h1 className="text-4xl font-bold mb-4">Create Your Fantasy Team</h1>
                    <p className="mb-6 text-lg">Build your dream cricket team and compete with others!</p>
                    <Link to="/fantasy">
                    <button className="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded">
                        Create Team
                    </button>
                </Link>
                </div>
            </div>
        </main>
    );
};

export default Home;

