import { createSlice, PayloadAction, createAsyncThunk } from '@reduxjs/toolkit';
import * as api from '../../api/taxApi';

// Define interfaces for clarity and type safety.

export interface PersonalInfo {
  firstName: string;
  middleName: string;
  lastName: string;
  email: string;
  ssn: string;
  username: string;
  birthday: string;
  role: string;
}

export interface Address {
  street1: string;
  street2: string;
  city: string;
  state: string;
  postalCode: string;
}


export interface FilingStatus {
  status: string;
 
}


export interface W2Income {
  id: number;
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
  id: number;
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

/*
export interface Deductions {
  mortgageInterest: number;
  donations: number;
  propertyTax: number;
  medical: number;
  studentLoanInterest: number;
  otherDeduction: number;
  otherIncome: number;
}
*/

export interface TaxInfoState {
    
    filingStatus:FilingStatus;
    numDependents: number;
    incomeW2: W2Income[];
    income1099: Income1099[];
    mortgageInterest: number;
    donations: number;
    propertyTax: number;
    medical: number;
    studentLoanInterest: number;
   otherDeduction: number;
   otherIncome: number;
    loading: boolean; 
    error: string | null | unknown;  
}


// Define the initial state based on the interfaces.
const initialState: TaxInfoState = {
    
    filingStatus: {status: '',},
    numDependents: 0,
    incomeW2: [],
    income1099: [],
   
      mortgageInterest: 0,
      donations: 0,
      propertyTax: 0,
      medical: 0,
      studentLoanInterest: 0,
      otherDeduction: 0,
      otherIncome: 0,
    
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

export const fetchUserInfo = createAsyncThunk(
  'userInfo/fetchUserInfo',
  async (_, { rejectWithValue }) => {
      try {
          const data = await api.fetchUserInfoApi();
          return data;
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
    /*
    updatePersonalInfo: (state, action: PayloadAction<Partial<PersonalInfo>>) => {
      state.personalInfo = { ...state.personalInfo, ...action.payload };
    },
    updateAddress: (state, action: PayloadAction<Partial<Address>>) => {
      state.address = { ...state.address, ...action.payload };
    },
    */
    setTaxInfo(state, action) {
      return { ...state, ...action.payload };
  },
  updateFilingStatus: (state, action: PayloadAction<FilingStatus>) => {
    state.filingStatus = action.payload; 
    
  },
  updateNumDependents: (state, action: PayloadAction<number>) => {
    state.numDependents =  action.payload;
    
  },
  updateMortgageInterest: (state, action: PayloadAction<number>) => {
      state.mortgageInterest = action.payload;
    },
    updateDonations: (state, action: PayloadAction<number>) => {
      state.donations = action.payload;
    },
    updatePropertyTax: (state, action: PayloadAction<number>) => {
      state.propertyTax = action.payload;
    },
    updateMedical: (state, action: PayloadAction<number>) => {
      state.medical = action.payload;
    },
    updateStudentLoanInterest: (state, action: PayloadAction<number>) => {
      state.studentLoanInterest = action.payload;
    },
    updateOtherDeduction: (state, action: PayloadAction<number>) => {
      state.otherDeduction = action.payload;
    },
    updateOtherIncome: (state, action: PayloadAction<number>) => {
      state.otherIncome = action.payload;
    },
  /*
  updateDeductions: (state, action: PayloadAction<Partial<Deductions>>) => {
    state.deductions = { ...state.deductions, ...action.payload };
  },*/


    // Handling multiple W-2 and 1099 forms
    addW2Income: (state, action: PayloadAction<W2Income>) => {
      state.incomeW2.push(action.payload);
    },
     // Update an existing W2 form in the state
     updateW2Income: (state, action: PayloadAction<UpdateW2IncomePayload>) => {
      // Assumes forms array is completely replacing the existing w2Income
      state.incomeW2 = action.payload.forms.map((form, index) => ({
          ...state.incomeW2[index],
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
     
      .addCase(fetchTaxInfo.rejected, (state, action) => {
        state.error = action.error.message;
        state.loading = false;
      })
      .addCase(updateTaxAddress.pending, (state) => {
        state.loading = true;
      })
     
      .addCase(updateTaxAddress.rejected, (state, action) => {
        state.error = action.payload;
        state.loading = false;
      })

       // Handle updating user information
       .addCase(updateUserInformation.pending, (state) => {
        state.loading = true;
    })
   
    .addCase(updateUserInformation.rejected, (state, action) => {
        state.error = action.error.message;
        state.loading = false;
    })

    //get request from user/info in usercontroller
    .addCase(fetchUserInfo.pending, (state) => {
      state.loading = true;
      state.error = null;
  })
 
  .addCase(fetchUserInfo.rejected, (state, action) => {
      state.error = action.payload as string;
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
    /*
    state.personalInfo = action.payload.personalInfo;
    state.address = action.payload.address;
    */
    

      if (action.payload) {

        console.log("Payload:", action.payload);
    console.log("Filing Status in Payload:", action.payload.filingStatus);
    console.log("Income W2 in Payload:", action.payload.incomeW2);
    console.log("Income 1099 in Payload:", action.payload.income1099);
          // Update all fields based on the payload
          state.filingStatus = action.payload.filingStatus; // Updates filing status
          state.numDependents = action.payload.numDependents; // Updates the number of dependents
          state.donations = action.payload.donations; // Updates donations
          state.mortgageInterest = action.payload.mortgageInterest; // Updates mortgage interest
          state.propertyTax = action.payload.propertyTax; // Updates property tax
          state.medical = action.payload.medical; // Updates medical expenses
          state.studentLoanInterest = action.payload.studentLoanInterest; // Updates student loan interest
          state.otherDeduction = action.payload.otherDeduction; // Updates other deductions
          state.otherIncome = action.payload.otherIncome; // Updates other income
          state.incomeW2 = action.payload.incomeW2; // Updates W2 income
          state.income1099 = action.payload.income1099; // Updates 1099 income
          state.loading = false; // Updates loading status
          state.error = null; // Resets any errors
      } else {
          // Optionally handle cases where the payload might be missing or invalid
          state.error = 'Failed to receive full tax information';
      }
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
  /*
  updatePersonalInfo,
  updateAddress,
  */
  updateFilingStatus,
  addW2Income,
  updateW2Income,
  addIncome1099,
  deleteIncome1099,
  updateIncome1099,
  updateMortgageInterest,
  updateDonations,
  updatePropertyTax,
  updateMedical,
  updateStudentLoanInterest,
  updateNumDependents,
  updateOtherDeduction,
  updateOtherIncome,
  setTaxInfo
} = taxInfoSlice.actions;

export default taxInfoSlice.reducer;
