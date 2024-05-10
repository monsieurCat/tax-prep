
import { createSlice, createAsyncThunk } from '@reduxjs/toolkit';
import * as api from '../../api/taxApi';



// Define separate interfaces for clarity and modularity
export interface PersonalInfo {
    firstName: string;
    middleName?: string;
    lastName: string;
    email: string;
    ssn: string;
    username: string;
    birthday: string;
    role: string;
  }
  
  export interface Address {
    street1: string;
    street2?: string;
    city: string;
    state: string;
    postalCode: string;
  }

  // Unified state interface
export interface UserInfoState {
    personalInfo: PersonalInfo;
    address: Address;
    loading: boolean;
    error: string | null;
  }
/*

export interface UserInfoState {
    personalInfo: {
    firstName: string;
    middleName?: string;
    lastName: string;
    email: string;
    ssn: string;
    username: string;
    birthday: string;
    role: string;
    },
    address: {
    street1: string;
    street2: string;
    city: string;
    state: string;
    postalCode: string;
};
    loading: boolean;
    error: string | null;
  }


*/


  const initialState: UserInfoState = {
    personalInfo: {
        firstName: '',
        middleName: '',
        lastName: '',
        email: '',
        ssn: '',
        username: '',
        birthday: '',
        role: '',
    },
    address: {
        street1: '',
        street2: '',
        city: '',
        state: '',
        postalCode: '',
    },
    loading: false,
    error: null
};
// Async thunks
export const fetchUserInfo = createAsyncThunk('user/fetchUserInfo', async (_, { rejectWithValue }) => {
    try {
        const response = await api.fetchUserInfoApi();
        console.log("api:" ,response); 
        if (response) {
            
        }
        return response;
    } catch (error) {
        return rejectWithValue((error as any).toString());
      }
    
});
export const updateUserInformation = createAsyncThunk('user/updateUserInformation', async (userData: any, { rejectWithValue }) => {
    try {
        const response = await api.updateUser(userData); // Ensure this API endpoint expects an AppUserDTO object
        
        return response.data;
    } catch (error) {
        return rejectWithValue((error as any).toString());
    }
});

export const updateAddress = createAsyncThunk('user/updateAddress', async (addressData: any, { rejectWithValue }) => {
    try {
        const response = await api.updateAddress(addressData); // Ensure this API endpoint expects an AddressDTO object
        return response.data;
    } catch (error) {
        return rejectWithValue((error as any).toString());
    }
});
export const fetchAddress = createAsyncThunk('user/fetchAddress', async (_, { rejectWithValue }) => {
    try {
        const response = await api.fetchAddress();
        return response;
    } catch (error) {
        return rejectWithValue((error as any).toString());
      }
    
});

const userSlice = createSlice({
    name: 'user',
    initialState,
    reducers: {
        resetUserState: (state) => initialState
    },
    extraReducers: (builder) => {
        builder


        .addCase(fetchUserInfo.fulfilled, (state, action) => {
            // Assuming action.payload directly contains the structure to fit personalInfo
            state.personalInfo=action.payload; // Ensure this matches the data structure
  state.loading = false;
  state.error = null;
        })
        .addCase(fetchAddress.fulfilled, (state, action) => {
            // Assuming action.payload contains the structure to fit address
            state.address = action.payload; // Ensure this matches the data structure
            state.loading = false;
            state.error = null;
        })
        .addCase(fetchUserInfo.pending, (state) => {
            state.loading = true;
        })
        .addCase(fetchAddress.pending, (state) => {
            state.loading = true;
        })
        .addCase(fetchUserInfo.rejected, (state, action) => {
            state.loading = false;
            // Make sure that error is a string. Adjust as per your actual error handling
            state.error = action.error.message || 'Failed to fetch user info';
        })
        .addCase(fetchAddress.rejected, (state, action) => {
            state.loading = false;
            state.error = action.error.message || 'Failed to fetch address';
        })
        .addCase(updateUserInformation.fulfilled, (state, action) => {
            // Update personalInfo with payload, assume payload is structured correctly
            state.personalInfo = {
                ...state.personalInfo,
                ...action.payload
            };
            state.loading = false;
            state.error = null;
        })
        .addCase(updateAddress.fulfilled, (state, action) => {
            // Update address with payload, assume payload is structured correctly
            state.address = {
                ...state.address,
                ...action.payload
            };
            state.loading = false;
            state.error = null;
        });

    }
});

export const { resetUserState } = userSlice.actions;
export default userSlice.reducer;
