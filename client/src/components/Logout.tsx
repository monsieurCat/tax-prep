// Logout.tsx

import React from 'react';

const Logout = () => {
  const handleLogout = async () => {
    try {
      const response = await fetch('http://localhost:8282/api/auth/logout', {
        method: 'POST',
        credentials: 'include',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
      });
      if (response.ok) {
        localStorage.removeItem('accessToken'); // Clear any user-related data stored locally
        window.location.href = '/login'; // Redirect the user to the login page?
      } else {
        console.error('Logout failed');
      }
    } catch (error) {
      console.error('Error:', error);
    }
  };

  return (
    <button onClick={handleLogout}>Logout</button>
  );
};

export default Logout;
