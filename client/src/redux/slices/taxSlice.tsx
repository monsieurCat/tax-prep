import { createSlice, PayloadAction } from '@reduxjs/toolkit';

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
  }


// Define the initial state based on the interfaces.
const initialState: TaxInfoState = {
    personalInfo: { firstName: '', middleName: '', lastName: '', birthdate: '', ssn: '' },
    address: { street1: '', street2: '', city: '', state: '', zip: '' },
    w2Income: [],
    income1099: []
  };

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
