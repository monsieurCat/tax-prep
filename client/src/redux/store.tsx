// src/redux/store.js
import { configureStore } from '@reduxjs/toolkit';
import rootReducer from './rootReducer';



export const store = configureStore({
  reducer: rootReducer as any,

  middleware: (getDefaultMiddleware) => getDefaultMiddleware({
    serializableCheck: false,
  }),
});

export type AppDispatch = typeof store.dispatch;