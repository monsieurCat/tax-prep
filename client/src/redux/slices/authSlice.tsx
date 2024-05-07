
import { createAsyncThunk, createSlice, PayloadAction } from '@reduxjs/toolkit';



// async thunk for registering a new user
export const registerUser = createAsyncThunk(
  'auth/registerUser',
  async (userData: { email: string; password: string }, { rejectWithValue }) => {
      try {
          const response = await fetch('http://localhost:8282/api/auth/register', {
              method: 'POST',
              headers: {
                  'Content-Type': 'application/json',
              },
              body: JSON.stringify({
                username: userData.email,
                password: userData.email

                /*
                firstName: userData.firstName,
                middleName: userData.middleName,
                lastName: userData.lastName,
                email: userData.email,
                username: userData.email, // Assuming you use email as the username
                ssn: userData.ssn,
                birthday: userData.birthday, // Ensure date is in the correct format (ISO 8601 string)
                role: userData.role*/
              })
          });
          if (!response.ok) {
              throw new Error('Failed to register');
          }
          const data = await response.json();
          return data; 
      } catch (error) {
        return rejectWithValue((error as any).toString());
      }
  }
);

export interface AuthState {
  isAuthenticated: boolean;
  username: string | null;
}


const initialState: AuthState = {
  isAuthenticated: false,
  username: null,
};

const authSlice = createSlice({
  name: 'auth',
  initialState,
  reducers: {
    login: (state, action: PayloadAction<{ username: string }>) => {
      state.isAuthenticated = true;
      state.username = action.payload.username;
    },
    logout: (state) => {
      state.isAuthenticated = false;
      state.username = null;
    },
    setUsername: (state, action: PayloadAction<string>) => {
      state.username = action.payload;
    },
  },

  extraReducers: (builder) => {
    builder.addCase(registerUser.fulfilled, (state, action) => {
        state.isAuthenticated = true;  // Optionally set the user as authenticated upon registration
        state.username = action.payload.username;  // Set username from response if available
    });
},
});

export const { login, logout, setUsername } = authSlice.actions;
export default authSlice.reducer;
