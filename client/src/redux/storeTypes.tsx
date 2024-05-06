// src/redux/storeTypes.ts
import { AuthState } from './slices/authSlice';
import { TaxInfoState } from './slices/taxSlice';

export interface RootState {
  auth: AuthState;
  taxInfo: TaxInfoState; 
}


