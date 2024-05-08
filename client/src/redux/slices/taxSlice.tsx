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

export interface FilingStatus {
  status: string;
  numDependents: number;
}


export interface W2Income {
  income: number;
  withholdings: number;
  employerEin: string;
  employerStreet1: string;
  employerStreet2: string;
  employerCity: string;
  employerState: string;
  employerZipcode: string;
}

export interface UpdateW2IncomePayload {
  forms: Array<Partial<W2Income>>;
}

export interface Income1099 {
  income: number;
  withholdings: number;
  employerEin: string;
  employerStreet1: string;
  employerStreet2: string;
  employerCity: string;
  employerState: string;
  employerZipcode: string;
}
export interface UpdateIncome1099Payload {
  forms: Array<Partial<Income1099>>;
}

export interface Deductions {
  mortgageInterest: number;
  donations: number;
  propertyTax: number;
  medical: number;
  studentLoanInterest: number;
  otherDeduction: number;
  otherIncome: number;
}

export interface TaxInfoState {
    personalInfo: PersonalInfo;
    address: Address;
    filingStatus:FilingStatus;
    w2Income: W2Income[];
    income1099: Income1099[];
    deductions: Deductions; 
    loading: boolean; 
    error: string | null | unknown;  
}


// Define the initial state based on the interfaces.
const initialState: TaxInfoState = {
    personalInfo: { firstName: '', middleName: '', lastName: '', birthdate: '', ssn: '' },
    address: { street1: '', street2: '', city: '', state: '', zip: '' },
    filingStatus: {status: '', numDependents: 0},
    w2Income: [],
    income1099: [],
    deductions: { 
      mortgageInterest: 0,
      donations: 0,
      propertyTax: 0,
      medical: 0,
      studentLoanInterest: 0,
      otherDeduction: 0,
      otherIncome: 0
    },
    loading: false,
    error: null,
  };


  //THUNKS!!

//thunk to create tax info full
  export const submitFullTaxInfo = createAsyncThunk(
    'taxInfo/submitFullTaxInfo',
    async (taxInfo: TaxInfoState, { rejectWithValue }) => {
        try {
            const response = await api.postTaxInfo(taxInfo);
            return response.data;
        } catch (error) {
          return rejectWithValue((error as any).toString());
        }
    }
);

// Asynchronous thunk for updating tax info locally 
export const updateLocalTaxInfo = createAsyncThunk(
  'taxInfo/updateLocalTaxInfo',
  async (data, { getState, dispatch }) => {
      dispatch(setTaxInfo(data)); // Update local state
      
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

// async thunk for updating user information
export const updateUserInformation = createAsyncThunk(
  'taxInfo/updateUserInformation',
  async (userData: any, { rejectWithValue }) => {
      try {
          const response = await api.updateUser(userData);
          return response;
      } catch (error) {
          return rejectWithValue((error as any).toString());
      }
  }
);

export const fetchFilingStatus = createAsyncThunk(
  'taxInfo/fetchFilingStatus',
  async (_, { rejectWithValue }) => {
      try {
          return await api.fetchFilingStatusApi();
      } catch (error) {
        return rejectWithValue((error as any).toString());
      }
      }
  
);
/*
export const updateFilingStatus = createAsyncThunk(
  'taxInfo/updateFilingStatus',
  async ({ filingStatus }: { filingStatus: string; }, { rejectWithValue }) => {
      try {
          return await api.updateFilingStatusApi(filingStatus);
      } catch (error) {
        return rejectWithValue((error as any).toString());
      }
    }
  
);
*/


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
    setTaxInfo(state, action) {
      return { ...state, ...action.payload };
  },
  updateFilingStatus: (state, action: PayloadAction<FilingStatus>) => {
    state.filingStatus =  { ...state.filingStatus, ...action.payload };
    
  },
  updateDeductions: (state, action: PayloadAction<Partial<Deductions>>) => {
    state.deductions = { ...state.deductions, ...action.payload };
  },

    // Handling multiple W-2 and 1099 forms
    addW2Income: (state, action: PayloadAction<W2Income>) => {
      state.w2Income.push(action.payload);
    },
     // Update an existing W2 form in the state
     updateW2Income: (state, action: PayloadAction<UpdateW2IncomePayload>) => {
      // Assumes forms array is completely replacing the existing w2Income
      state.w2Income = action.payload.forms.map((form, index) => ({
          ...state.w2Income[index],
          ...form
      }));
  },

    addIncome1099: (state, action: PayloadAction<Income1099>) => {
      state.income1099.push(action.payload);
    },
    deleteIncome1099: (state, action: PayloadAction<number>) => {
      state.income1099.splice(action.payload, 1);
    },
    updateIncome1099: (state, action: PayloadAction<UpdateIncome1099Payload>) => {
      // Assumes forms array is completely replacing the existing w2Income
      state.income1099 = action.payload.forms.map((form, index) => ({
          ...state.income1099[index],
          ...form
      }));
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

       // Handle updating user information
       .addCase(updateUserInformation.pending, (state) => {
        state.loading = true;
    })
    .addCase(updateUserInformation.fulfilled, (state, action) => {
        state.personalInfo = action.payload; // Assuming the payload contains the updated user info
        state.loading = false;
    })
    .addCase(updateUserInformation.rejected, (state, action) => {
        state.error = action.error.message;
        state.loading = false;
    })




    //handle filing status changes
    .addCase(fetchFilingStatus.pending, (state) => {
      state.loading = true;
  })
  .addCase(fetchFilingStatus.fulfilled, (state, action) => {
      state.filingStatus = action.payload; // Assuming the payload contains the filing status
      state.loading = false;
  })
  .addCase(fetchFilingStatus.rejected, (state, action) => {
      state.error = action.error.message;
      state.loading = false;
  })


//this is for the taxinfoController  tax_info/full
  .addCase(submitFullTaxInfo.pending, (state) => {
    state.loading = true;
  })
  .addCase(submitFullTaxInfo.fulfilled, (state, action) => {
    // Assuming the backend returns the full updated tax info
    state.personalInfo = action.payload.personalInfo;
    state.address = action.payload.address;
    state.filingStatus = action.payload.filingStatus;
    state.w2Income = action.payload.w2Income;
    state.income1099 = action.payload.income1099;
    state.loading = false;
  })
  .addCase(submitFullTaxInfo.rejected, (state, action) => {
    state.error = action.error.message;
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
  updateFilingStatus,
  addW2Income,
  updateW2Income,
  addIncome1099,
  deleteIncome1099,
  updateIncome1099,
  updateDeductions,
  setTaxInfo
} = taxInfoSlice.actions;

export default taxInfoSlice.reducer;
