// src/redux/rootReducer.js
import { combineReducers } from 'redux';
import authReducer from './slices/authSlice';
import taxInfoReducer from './slices/taxSlice'; 
import { RootState } from './storeTypes';
import userReducer from './slices/userSlice';

const rootReducer = combineReducers({
  auth: authReducer,
  taxInfo: taxInfoReducer, 
  user: userReducer,
});

export default rootReducer;
