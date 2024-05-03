// src/redux/storeTypes.ts
import { AuthState } from './slices/authSlice';

export interface RootState {
  auth: AuthState;
  // other state slices go here
}


