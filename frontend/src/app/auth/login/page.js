"use client";

import { useState } from 'react';
import Layout from '../../components/Layout';
import { loginUser } from '../../utils/api';

export default function Login() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();
    const token = await loginUser({ username, password });
    localStorage.setItem('token', token);
    alert('Logged in successfully');
  };

  return (
    <Layout>
      <h1 className="text-2xl font-bold mb-4">Login</h1>
      <form onSubmit={handleSubmit} className="max-w-md mx-auto">
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
          className="w-full p-2 mb-4 border rounded"
          required
        />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
          className="w-full p-2 mb-4 border rounded"
          required
        />
        <button type="submit" className="w-full bg-blue-600 text-white px-4 py-2 rounded">
          Login
        </button>
      </form>
    </Layout>
  );
}