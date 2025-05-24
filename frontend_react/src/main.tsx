import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import { App } from './app/index'
import {Helmet, HelmetProvider} from 'react-helmet-async';
import './index.css'

createRoot(document.getElementById('root')!).render(
  <StrictMode>
    <HelmetProvider>
    <App />
    </HelmetProvider>
  </StrictMode>,
)
