// src/redux/storeTypes.ts
import { AuthState } from './slices/authSlice';
import { TaxInfoState } from './slices/taxSlice';
import { UserInfoState } from './slices/userSlice';


export interface RootState {
  auth: AuthState;
  taxInfo: TaxInfoState; 
  user: UserInfoState;
}



