import { createSlice, PayloadAction, createAsyncThunk } from '@reduxjs/toolkit';
import * as api from '../../api/taxApi';

// Define interfaces for clarity and type safety.

export interface PersonalInfo {
  firstName: string;
  middleName: string;
  lastName: string;
  birthdate: string;
  ssn: string;
}

export interface Address {
  street1: string;
  street2: string;
  city: string;
  state: string;
  zip: string;
}

export interface W2Income {
  employerName: string;
  income: number;
  federalTaxWithheld: number;
}

export interface Income1099 {
  companyName: string;
  amount: number;
}

export interface TaxInfoState {
    personalInfo: PersonalInfo;
    address: Address;
    w2Income: W2Income[];
    income1099: Income1099[];
    loading: boolean; 
    error: string | null | unknown;  
}


// Define the initial state based on the interfaces.
const initialState: TaxInfoState = {
    personalInfo: { firstName: '', middleName: '', lastName: '', birthdate: '', ssn: '' },
    address: { street1: '', street2: '', city: '', state: '', zip: '' },
    w2Income: [],
    income1099: [],
    loading: false,
    error: null,
  };

//thunk to create tax info
  export const submitTaxInfo = createAsyncThunk(
    'taxInfo/submitTaxInfo',
    async (personalInfo: PersonalInfo, { rejectWithValue }) => {
        try {
            const response = await api.postTaxInfo(personalInfo);
            return response.data;
        } catch (error) {
          return rejectWithValue((error as any).toString());
        }
    }
);

  // thunk for fetching tax info
  export const fetchTaxInfo = createAsyncThunk(
    'taxInfo/fetchTaxInfo',
    async (_, { rejectWithValue }) => {
      try {
        const response = await api.fetchTaxInfo();
        return response;
      } catch (error) {
        return rejectWithValue((error as any).toString());
      }
    }
  );


//thunk for updating taxinfo
/*
export const updateTaxInfo = createAsyncThunk(
  'taxInfo/updateTaxInfo',
  async ({ taxData, token }: { taxData: TaxInfoState, token: string }, { rejectWithValue }) => {
    try {
      const response = await api.updateTaxInfo(taxData, token);
      if (response.status === 200) {
        return response.data;
      } else {
        throw new Error('Failed to update tax information');
      }
    } catch (error) {
      return rejectWithValue((error as any).toString());
    }
  }
);
*/

// Async thunk for updating address

export const updateTaxAddress = createAsyncThunk(
  'taxInfo/updateAddress',
  async ({ addressData }: { addressData: Address }, { rejectWithValue }) => {
    try {
      const response = await api.updateAddress(addressData);
      if (response.status === 200) {
        return response.data;
      } else {
        throw new Error('Failed to update address');
      }
    } catch (error) {
      return rejectWithValue((error as any).toString());
    }
  }
);



const taxInfoSlice = createSlice({
  name: 'taxInfo',
  initialState,
  reducers: {
    // Updates for single attributes
    updatePersonalInfo: (state, action: PayloadAction<Partial<PersonalInfo>>) => {
      state.personalInfo = { ...state.personalInfo, ...action.payload };
    },
    updateAddress: (state, action: PayloadAction<Partial<Address>>) => {
      state.address = { ...state.address, ...action.payload };
    },
    
    // Handling multiple W-2 and 1099 forms
    addW2Income: (state, action: PayloadAction<W2Income>) => {
      state.w2Income.push(action.payload);
    },
    updateW2Income: (state, action: PayloadAction<{ index: number, data: Partial<W2Income> }>) => {
      const { index, data } = action.payload;
      if (state.w2Income[index]) {
        state.w2Income[index] = { ...state.w2Income[index], ...data };
      }
    },
    addIncome1099: (state, action: PayloadAction<Income1099>) => {
      state.income1099.push(action.payload);
    },
    updateIncome1099: (state, action: PayloadAction<{ index: number, data: Partial<Income1099> }>) => {
      const { index, data } = action.payload;
      if (state.income1099[index]) {
        state.income1099[index] = { ...state.income1099[index], ...data };
      }
    },
  },
  extraReducers: (builder) => {
    builder
      .addCase(fetchTaxInfo.pending, (state) => {
        state.loading = true;
      })
      .addCase(fetchTaxInfo.fulfilled, (state, action) => {
        state.personalInfo = action.payload.personalInfo;
        state.address = action.payload.address;
        state.loading = false;
      })
      .addCase(fetchTaxInfo.rejected, (state, action) => {
        state.error = action.error.message;
        state.loading = false;
      })
      .addCase(updateTaxAddress.pending, (state) => {
        state.loading = true;
      })
      .addCase(updateTaxAddress.fulfilled, (state, action) => {
        state.address = action.payload; // Assuming the payload contains the updated address
        state.loading = false;
      })
      .addCase(updateTaxAddress.rejected, (state, action) => {
        state.error = action.payload;
        state.loading = false;
      })
      /*
      .addCase(updateTaxInfo.pending, (state: { loading: boolean; }) => {
        state.loading = true;
      })
      .addCase(updateTaxInfo.fulfilled, (state: { personalInfo: any; address: any; loading: boolean; }, action: { payload: { personalInfo: any; address: any; }; }) => {
        // Update the state with the received updated tax info
        state.personalInfo = action.payload.personalInfo;
        state.address = action.payload.address;
        // Add other parts of tax info as necessary
        state.loading = false;
      })
      .addCase(updateTaxInfo.rejected, (state: { error: any; loading: boolean; }, action: { error: { message: any; }; }) => {
        state.error = action.error.message;
        state.loading = false;
      });
      */
  },
});

export const {
  updatePersonalInfo,
  updateAddress,
  addW2Income,
  updateW2Income,
  addIncome1099,
  updateIncome1099
} = taxInfoSlice.actions;

export default taxInfoSlice.reducer;
