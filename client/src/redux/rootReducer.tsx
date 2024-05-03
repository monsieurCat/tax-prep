// src/redux/rootReducer.js
import { combineReducers } from 'redux';
import authReducer from './slices/authSlice';

const rootReducer = combineReducers({
  auth: authReducer,
  // Add more reducers here if needed
});

export default rootReducer;
