// src/redux/rootReducer.js
import { combineReducers } from 'redux';
import authReducer from './slices/authSlice';
import taxInfoReducer from './slices/taxSlice'; 

const rootReducer = combineReducers({
  auth: authReducer,
  taxInfo: taxInfoReducer, 
});

export default rootReducer;
