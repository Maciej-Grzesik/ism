import React, { useState } from 'react';
import image from '../../../assets/bg.jpg';
// import { useNavigate } from 'react-router-dom';

const LoginRoute = () => {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  // const navigate = useNavigate();

  const handleUsernameChange = (event: any) => {
    setUsername(event.target.value);
  };

  const handlePasswordChange = (event: any) => {
    setPassword(event.target.value);
  };

  const handleSubmit = async (event: any) => {
    event.preventDefault();


    try {
      const response = await fetch('http://localhost:8080/auth/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      });

      const data = await response.json();

      if (!response.ok) {
        
      }

      if (data.token) {
        localStorage.setItem('uid', data.uid);
        // navigate('/dashboard');

      } else {

      }

    } catch (e) {

    } finally {
 
    }
  };

  return (
    <main
      className="flex h-screen items-center justify-center bg-gray-900 bg-cover bg-center bg-no-repeat"
      style={{ backgroundImage: `url(${image})` }}
    >
      <div className="flex h-full w-full items-center justify-center">
        <div className="flex w-full max-w-sm flex-col space-y-6 rounded-3xl bg-white/10 p-6 shadow-xl backdrop-blur-md sm:max-w-md sm:p-8 md:p-12 lg:max-w-lg">
          <h1 className="bg-gradient-to-r from-purple-500 to-pink-500 bg-clip-text text-center text-3xl font-semibold text-transparent sm:text-4xl md:text-5xl">
            DreamSellers
          </h1>
          <form onSubmit={handleSubmit}>
            <div className="space-y-4">
              <div>
                <label
                  htmlFor="email"
                  className="block text-xl font-medium text-white sm:text-2xl"
                >
                  Username
                </label>
                <input
                  id="username"
                  type="username"
                  value={username}
                  onChange={handleUsernameChange}
                  required
                  className="mt-2 w-full rounded-md border border-gray-300 bg-transparent px-4 py-3 text-white placeholder-gray-400 focus:border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none"
                />
              </div>
              <div>
                <label
                  htmlFor="password"
                  className="block text-xl font-medium text-white sm:text-2xl"
                >
                  Password
                </label>
                <input
                  id="password"
                  type="password"
                  value={password}
                  onChange={handlePasswordChange}
                  required
                  className="mt-2 w-full rounded-md border border-gray-300 bg-transparent px-4 py-3 text-white placeholder-gray-400 focus:border-transparent focus:ring-2 focus:ring-purple-500 focus:outline-none"
                />
              </div>
            </div>
            <button
              type="submit"
              className="bg-purple-600 hover:bg-purple-700 focus:scale-95 transition hover:bg-gradient-to-r hover:from-purple-500 hover:to-pink-500 focus:bg-gradient-to-r focus:from-purple-500 focus:to-pink-500 duration-700 ease-out hover:scale-105 mt-6 w-full rounded-md px-4 py-3 font-semibold text-white focus:ring-2 focus:ring-purple-500 focus:outline-none"
            >
              Login
            </button>
          </form>
          <p className="text-center text-lg text-gray-300  sm:text-xl">
            Don't have an account?
            <a href="#" className="text-purple-400 hover:underline mx-4">
              Register
            </a>
          </p>
        </div>
      </div>
    </main>
  );
};

export default LoginRoute;
